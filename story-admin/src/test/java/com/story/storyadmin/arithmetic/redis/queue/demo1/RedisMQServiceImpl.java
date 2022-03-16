package com.story.storyadmin.arithmetic.redis.queue.demo1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisMQServiceImpl implements RedisMQService {
    private static Logger log = LoggerFactory.getLogger(RedisMQServiceImpl.class);

    private static final String MESSAGE_KEY = "message:queue";

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void produce(String string) {
        //生产者把消息丢到消息队列中
        redisTemplate.opsForList().leftPush(MESSAGE_KEY, string);
    }

    @Override
    public void consume() {
        String string = (String) redisTemplate.opsForList().rightPop(MESSAGE_KEY);
        //消费方拿到消息后进行业务处理
        log.info("consume : {}", string);
    }
}