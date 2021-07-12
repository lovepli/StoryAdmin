package com.story.storyadmin.utils;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisUtilsTest extends TestCase {

    /**
     * @Resource(name=”name”,type=”type”)：
     * 没有括号内内容的话，默认 byName。与 @Autowired 干类似的事；
     */

    /** 引入自定义Integer 缓存 */
    @Resource(name = "intRedisTemplate")
    private RedisTemplate<String, Integer> intTemplate;

    /** 引入自定义String 缓存 */
    @Resource(name = "strRedisTemplate")
    private RedisTemplate<String, String> strTemplate;


    /**
     * 存入缓存
     * @param key
     * @param field
     * @param value
     */
    public void incrHash(String key, String field, Integer value) {
        intTemplate.opsForHash().increment(key, field, value);
    }

    /**
     * 存入缓存
     * @param key
     * @param value
     */
    public void incrKey(String key, Integer value) {
        if(null == key || null == value){
            return;
        }
        intTemplate.opsForValue().increment(key,value);
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public Integer getInteger(String key) {
        if(null == key){
            return null;
        }
        return intTemplate.opsForValue().get(key);
    }

    @Test
    public void test(){
//测试方法

    }




}