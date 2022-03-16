package com.story.storyadmin.manyThread.JUC.AQS.utils.CountDownLatchDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: lipan
 * @date: 2021年08月26日 10:39 上午
 * @description: CountDownLatch 介绍和使用
 * CountDownLatch（闭锁）可以看作一个只能做减法的计数器，可以让一个或多个线程等待执行。
 * CountDownLatch 有两个重要的方法：
 *
 * countDown()：使计数器减 1；
 *
 * await()：当计数器不为 0 时，则调用该方法的线程阻塞，当计数器为 0 时，可以唤醒等待的一个或者全部线程。
 *
 * CountDownLatch 使用场景：
 *
 * 以生活中的情景为例，比如去医院体检，通常人们会提前去医院排队，但只有等到医生开始上班，才能正式开始体检，医生也要给所有人体检完才能下班，这种情况就要使用 CountDownLatch，流程为：患者排队 → 医生上班 → 体检完成 → 医生下班。
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        // 医院闭锁
        CountDownLatch hospitalLatch = new CountDownLatch(1);
        // 患者闭锁
        CountDownLatch patientLatch = new CountDownLatch(5);
        System.out.println("患者排队");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            final int j = i;
            executorService.execute(() -> {
                try {
                    hospitalLatch.await();//当计数器不为 0 时，则调用该方法的线程阻塞，当计数器为 0 时，可以唤醒等待的一个或者全部线程。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("体检：" + j);
                patientLatch.countDown();//使计数器减1
            });
        }
        System.out.println("医生上班");
        hospitalLatch.countDown();//使计数器减1
        patientLatch.await();//当计数器不为 0 时，则调用该方法的线程阻塞，当计数器为 0 时，可以唤醒等待的一个或者全部线程。
        System.out.println("医生下班");
        executorService.shutdown(); //关闭线程池
    }
}
