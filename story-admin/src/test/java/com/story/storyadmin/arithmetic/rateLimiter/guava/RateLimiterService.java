package com.story.storyadmin.arithmetic.rateLimiter.guava;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author: lipan
 * @date: 2021年10月05日 10:50 上午
 * @description: 【Guava RateLimiter】针对于限流器的入门到精通 https://www.jianshu.com/p/876dc72d3627
 */
@Slf4j
public class RateLimiterService {
    // 每秒发出5个令牌
    private static RateLimiter rateLimiter = RateLimiter.create(5);
    /**
     * 尝试获取令牌
     */
    public static boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }
    public  static void acquire() {
        rateLimiter.acquire();
    }

    public static String fun(){
        if (tryAcquire()) {
            log.info("start");
            // 模拟业务执行500毫秒
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "access success [" + LocalDateTime.now() + "]";
        } else {
            //log.warn("限流");
            return "access limit [" + LocalDateTime.now() + "]";
        }
    }


    public void testMethod() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        RateLimiter rateLimiter = RateLimiter.create(5); // rate is "5 permits per second"
        IntStream.range(0, 10).forEach(i -> pool.submit(() -> {
            if (rateLimiter.tryAcquire()) {
                try {
                    log.info("start");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            } else {
                log.warn("限流");
            }
        }));
    }

    public void testMethod2(){
        ExecutorService pool = Executors.newFixedThreadPool(10);
        RateLimiter rateLimiter = RateLimiter.create(5); // rate is "5 permits per second"
        IntStream.range(0, 10).forEach(i -> pool.submit(() -> {
            rateLimiter.acquire();
            log.info("start");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        pool.shutdown();
    }

    public static void main(String[] args){
         // fun();
        System.out.println(fun());
    }


}




