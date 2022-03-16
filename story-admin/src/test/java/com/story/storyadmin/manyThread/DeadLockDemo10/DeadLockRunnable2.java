package com.story.storyadmin.manyThread.DeadLockDemo10;

import java.util.concurrent.CountDownLatch;

/**
 * @author: lipan
 * @date: 11:26 下午
 * @description:
 */
public class DeadLockRunnable2 implements Runnable{

    private DeadLock deadLock;

    CountDownLatch latch;

    public DeadLockRunnable2(DeadLock deadLock){
        this.deadLock=deadLock;
    }

    public DeadLockRunnable2(DeadLock deadLock,CountDownLatch latch){
        this.deadLock=deadLock;
        this.latch=latch;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running...");
        //try {
        //
        //    latch.await();
        //    //让 t2 等待 t1 执行完 TODO 线程池中不能这样写！！
        //   // new Thread(new DeadLockRunnable1(deadLock)).join();//核心代码，让 t1 执行完后 t2 才会执行
        //} catch (InterruptedException e) {
        //    // TODO Auto-generated catch block
        //    e.printStackTrace();
        //}

        deadLock.flag =0;
        deadLock.money(0);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
