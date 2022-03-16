package com.story.storyadmin.arithmetic.redis.redisLock;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: 59688
 * @date: 2021/10/13
 * @description:
 */
public class Test {

    /**
     * 　测试，10 个线程通过分布式锁依次打印 0-1000000：
     * 测试结果，没有出现变量污染的问题，加锁解锁保证了线程操作 ArrayList 以及打印操作的原子性：
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("172.16.55.2");
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.afterPropertiesSet();
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        RedisRockUtil util = new ProtoRedisRockUtil(redisTemplate, 10 * 1000);
        String key = "testKey11", uuid = "123456";
        final List<Integer> arr = new ArrayList<Integer>();
        arr.add(0);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    while (arr.size() <= 100000) {
                        String randomId = UUID.randomUUID().toString();
                        RedisRockUtil lock = new ProtoRedisRockUtil(redisTemplate, 10000);
                        if (lock.lock(key, randomId)) {
                            if (arr.size() > 100000) break;
                            System.out.println("当前持锁线程：" + Thread.currentThread().getId());
                            int len = arr.size();
                            int pre = arr.get(len - 1);
                            int current = pre + 1;
                            arr.add(current);
                            System.out.println(current);
                            lock.unlock(key, randomId);
                        }
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(5 * 60 * 1000);
        int len = arr.size();
        for (int i = 0; i < arr.size() - 1; i++) {
            int next = arr.get(i + 1);
            int curr = arr.get(i);
            if (next - curr != 1) {
                System.out.println(i + "出错！:" + curr + "," + next);
                break;
            }
        }
        System.out.println("检查 " + len + " 个数据，无错误");
    }
}
