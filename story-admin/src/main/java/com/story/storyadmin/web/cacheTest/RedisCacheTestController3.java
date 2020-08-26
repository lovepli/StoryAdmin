package com.story.storyadmin.web.cacheTest;

import com.story.storyadmin.utils.JedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author: lipan
 * @date: 2020/7/3
 * @description: 测试缓存工具类 JedisUtils
 */
@RestController
@RequestMapping(value="/redisCacheTest")
public class RedisCacheTestController3 {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisUtils jedisUtils;


    @GetMapping("/set3")
    public String set() {

        // redisTemplate 保存的是字节序列，因为 RedisCacheConfig中自定义redisTemplate的时候指定了 key 和 value 的序列化器。
        jedisUtils.saveString("two","2");
        //这个person对象的value值在redis中是二进制的字节流的形式存储
        jedisUtils.saveObject("person",new Person(1L,"luffy","123456789"));

        return "Ok!";
    }

    @GetMapping("/get3")
    public String get() {
        //或者用断言判断
        //Assert.assertEquals("one", stringRedisTemplate.opsForValue().get("1"));
        String two=jedisUtils.get("two");
        if ("2".equals(two)) {
            System.out.println("key: two" + " || value: " + two);
        }

        Person user = (Person) jedisUtils.getObject("person");
        if ("luffy".equals(user.getUsername())) {
            System.out.println("key: person" + " || value: " + user);
        }

    // 从redis可视化工具中可以看到，存储的person的value值为json格式的字符串，如下
    // [
    //    "com.story.storyadmin.web.cacheTest.Person",
    //    {
    //        "userId": 1,
    //        "username": "luffy",
    //        "password": "123456789"
    //    }
    // ]
    return "Ok!";
    }
}
