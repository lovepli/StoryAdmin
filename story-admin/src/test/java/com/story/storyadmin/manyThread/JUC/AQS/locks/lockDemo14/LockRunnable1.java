package com.story.storyadmin.manyThread.JUC.AQS.locks.lockDemo14;

import java.util.concurrent.locks.Lock;

/**
 * @author: 59688
 * @date: 2021/7/29
 * @description:
 */
public class LockRunnable1 implements Runnable{

    private LockDemo lockDemo;

    private Lock lock;

    public LockRunnable1(LockDemo lockDemo) {
        this.lockDemo = lockDemo;
    }

    public LockRunnable1(LockDemo lockDemo, Lock lock) {
        this.lockDemo = lockDemo;
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock(); //加锁
        lockDemo.add();
        lock.unlock();
    }
}
