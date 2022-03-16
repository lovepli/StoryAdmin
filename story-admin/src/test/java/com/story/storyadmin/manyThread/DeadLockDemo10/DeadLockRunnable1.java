package com.story.storyadmin.manyThread.DeadLockDemo10;

import java.util.concurrent.CountDownLatch;

/**
 * @author: lipan
 * @date: 11:26 下午
 * @description:
 */
public class DeadLockRunnable1 implements Runnable{

    private DeadLock deadLock;

    CountDownLatch latch;

    public DeadLockRunnable1(DeadLock deadLock){
        this.deadLock=deadLock;
    }

    public DeadLockRunnable1(DeadLock deadLock,CountDownLatch latch){
        this.deadLock=deadLock;
        this.latch=latch;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running...");
        deadLock.flag =1;
        deadLock.money(1);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}
