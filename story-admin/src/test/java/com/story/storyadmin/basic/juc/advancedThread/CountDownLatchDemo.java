package com.story.storyadmin.basic.juc.advancedThread;

import java.util.concurrent.CountDownLatch;

/**
 * @author: lipan
 * @date: 2019-06-27
 * @description: 让一些线程阻塞直到另一些线程完成一系列操作后才被唤醒
 *
 * CountDownLatch 主要有两个方法， 当一个或多个线程调用 await 方法时， 这些线程会阻塞。
 * * 其它线程调用 countDown 方法会将计数器减 1(调用 countDown 方法的线程不会阻塞)，
 * * 当计数器的值变为 0 时， 因 await 方法阻塞的线程会被唤醒， 继续执行。
 *
 *
 *解释： 6 个同学陆续离开教室后值班同学才可以关门。
 * * *
 * main 主线程必须要等前面 6 个线程完成全部工作后， 自己才能开干
 */

public class CountDownLatchDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <=6; i++) //6 个上自习的同学， 各自离开教室的时间不一致
        {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 号同学离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t****** 班长关门走人， main 线程是班长");
    }
}
