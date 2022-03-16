package com.story.storyadmin.Interceptor.demo2;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisServer {

    //将JedisPool对象注入到Spring容器中去
    @Autowired
    JedisPool jedisPool;


    public <T> T get(KeyPrefix prefix,String key,Class<T> clazz){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            String str = jedis.get(realKey);
            T t = stringToBean(str,clazz);
            return t;
        }finally {
            if (jedis != null) {
                //将Jedis连接返回给连接池,并不会关闭连接
                 jedis.close();
            }
        }
    }

    public static <T> T stringToBean(String str,Class<T> clazz) {
        if(str == null || str.length() <= 0 || clazz == null){
            return null;
        }
        if(clazz ==int.class || clazz == Integer.class){
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class){
            return (T)str;
        }else if(clazz ==long.class || clazz == Long.class){
            return (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    public <T> boolean set(KeyPrefix keyPrefix,String key,T value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if(str == null || str.length() <= 0){
                return false;
            }
            //Redsi中存储key的生成法则,前缀+key(可以唯一标识一个Redis中的key)
            String realKey = keyPrefix.getPrefix()+key;
            int seconds =  keyPrefix.expireSeconds();
            //设置key的过期时间
            if(seconds <= 0){
                jedis.set(realKey,str);
            }else{
                jedis.setex(realKey,seconds,str);
            }
            return true;
        }finally {
            if (jedis != null) {
                //将Jedis连接返回给连接池,并不会关闭连接
                jedis.close();
            }
        }
    }

    //将value对象使用fastJson序列化为String对象,存储到Redis中
    public static  <T> String beanToString(T value) {
        if(value == null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz ==int.class || clazz == Integer.class){
            return ""+value;
        }else if(clazz == String.class){
            return (String) value;
        }else if(clazz ==long.class || clazz == Long.class){
            return ""+value;
        }else{
            return JSON.toJSONString(value);
        }
    }

    //判断是否存在
    public <T> boolean exists(KeyPrefix keyPrefix,String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            String realKey = keyPrefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            if (jedis != null) {
                //将Jedis连接返回给连接池,并不会关闭连接
                jedis.close();
            }
        }
    }

    //增加key所对应的value值
    //incr是原子操作
    public <T> Long incr(KeyPrefix keyPrefix,String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            String realKey = keyPrefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            if (jedis != null) {
                //将Jedis连接返回给连接池,并不会关闭连接
                jedis.close();
            }
        }
    }

    //减少key所对应的value值
    //decr是原子操作
    public <T> Long decr(KeyPrefix keyPrefix,String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            String realKey = keyPrefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            if (jedis != null) {
                //将Jedis连接返回给连接池,并不会关闭连接
                jedis.close();
            }
        }
    }

    //删除缓存
    public boolean delete(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix()+key;
            long ret = jedis.del(realKey);
            return ret > 0;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }



}

