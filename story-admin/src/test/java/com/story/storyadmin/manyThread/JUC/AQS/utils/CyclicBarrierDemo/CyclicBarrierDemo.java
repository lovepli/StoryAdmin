package com.story.storyadmin.manyThread.JUC.AQS.utils.CyclicBarrierDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: lipan
 * @date: 2021年08月26日 9:48 上午
 * @description: CyclicBarrier
 * 俗称「栅栏」机制，用于控制线程在同一时间点并发请求。
 *
 * 当线程数满20时，才会并发执行orderService.createOrder()。
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        //并发数
        int currency = 20;

        //循环屏障
        CyclicBarrier cyclicBarrier = new CyclicBarrier(currency);

        for (int i = 0; i < currency; i++) {
            new Thread(() -> {
               // OrderServiceImplWithDisLock orderService = new OrderServiceImplWithDisLock();
                System.out.println(Thread.currentThread().getName() + "====start====");
                //等待一起出发
                try {
                    cyclicBarrier.await();//当前线程已到达屏障点，然后被阻塞。
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
              //  orderService.createOrder();
            }).start();
        }
    }
}

