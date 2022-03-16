package com.story.storyadmin.manyThread.JUC.AQS.utils.SemaphoreDemo;

import java.time.LocalDateTime;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: lipan
 * @date: 2021年08月26日 11:13 上午
 * @description: Semaphore 介绍和使用
 * Semaphore（信号量）用于管理多线程中控制资源的访问与使用。Semaphore 就好比停车场的门卫，可以控制车位的使用资源。比如来了 5 辆车，只有 2 个车位，门卫可以先放两辆车进去，等有车出来之后，再让后面的车进入。
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        ThreadPoolExecutor semaphoreThread = new ThreadPoolExecutor(10, 50, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 5; i++) {
            semaphoreThread.execute(() -> {
                try {
                    // 堵塞获取许可
                    semaphore.acquire();
                    System.out.println("Thread：" + Thread.currentThread().getName() + " 时间：" + LocalDateTime.now());
                    TimeUnit.SECONDS.sleep(2);
                    // 释放许可
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
