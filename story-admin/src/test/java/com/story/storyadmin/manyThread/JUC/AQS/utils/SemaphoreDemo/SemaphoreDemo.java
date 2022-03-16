package com.story.storyadmin.manyThread.JUC.AQS.utils.SemaphoreDemo;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


/**
 * @author: lipan
 * @date: 2021年08月26日 9:44 上午
 * @description: 信号量机制
 * Semaphore 俗称「信号量」，用于控制在同一时间内共享资源被多少线程共享。可以作为流浪控制。
 *
 * 如下代码，我用Semaphore来控制semaphoreDemo.service(vipNo)的请求数量。如果并发时间请求数量超过5，弹出"获取令牌失败..."。
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
        int count = 9;    //数量

        //循环屏障 当线程数满9时，才会并发执行semaphoreDemo.service(vipNo)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);

        Semaphore semaphore = new Semaphore(5);//限制请求数量
        for (int i = 0; i < count; i++) {
            String vipNo = "vip-00" + i;
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                    //semaphore.acquire();//获取令牌
                    boolean tryAcquire = semaphore.tryAcquire(3000L, TimeUnit.MILLISECONDS);
                    if (!tryAcquire) {
                        System.out.println("获取令牌失败：" + vipNo);
                    }

                    //执行操作逻辑
                    System.out.println("当前线程：" + Thread.currentThread().getName());
                    semaphoreDemo.service(vipNo);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }

    }

    // 限流控制5个线程同时访问
    public void service(String vipNo) throws InterruptedException {
        System.out.println("楼上出来迎接贵宾一位，贵宾编号" + vipNo + ",...");
        Thread.sleep(new Random().nextInt(3000));
        System.out.println("欢送贵宾出门，贵宾编号" + vipNo);
    }

}
