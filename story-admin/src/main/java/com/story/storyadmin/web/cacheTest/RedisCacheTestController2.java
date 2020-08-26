package com.story.storyadmin.web.cacheTest;

import com.story.storyadmin.utils.JedisUtils;
import org.junit.Assert;
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
public class RedisCacheTestController2 {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisUtils jedisUtils;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/set")
    public String set() {

        //使用StringRedisTemplate模板类存储字符串。在redis中是以字符存储的
        stringRedisTemplate.opsForValue().set("one", "1");

        // redisTemplate 保存的是字节序列，因为 RedisCacheConfig中自定义redisTemplate的时候指定了 key 和 value 的序列化器。
        jedisUtils.saveString("two","2");
        //这个person对象的value值在redis中是二进制的字节流的形式存储
        jedisUtils.saveObject("person",new Person(1L,"luffy","123456789"));

        // 测试线程安全
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i -> {
            executorService.execute(() -> stringRedisTemplate.opsForValue().increment("num", 1));
        });
        return "Ok!";
    }

    @GetMapping("/get")
    public String get() {
        String one = stringRedisTemplate.opsForValue().get("one");
        if ("1".equals(one)) {
            System.out.println("key: one" + " || value: " + one);
        }
        //或者用断言判断
        //Assert.assertEquals("one", stringRedisTemplate.opsForValue().get("1"));

//        Object two = jedisUtils.getObject("two");//这里应该获取的是string
//        if ("2".equals(two.toString())) {
//            System.out.println("key: two" + " || value: " + two);
//        }

        String two=jedisUtils.get("two");
        if ("2".equals(two)) {
            System.out.println("key: two" + " || value: " + two);
        }

        Person user = (Person) jedisUtils.getObject("person");
        if ("luffy".equals(user.getUsername())) {
            System.out.println("key: person" + " || value: " + user);
        }
        return "Ok!";
    }
}
