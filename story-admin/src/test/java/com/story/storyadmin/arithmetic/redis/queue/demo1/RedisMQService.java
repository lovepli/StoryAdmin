package com.story.storyadmin.arithmetic.redis.queue.demo1;

/**
 * @author: 59688
 * @date: 2021/10/13
 * @description:
 */
public interface RedisMQService {
    void produce(String string);
    void consume();
}