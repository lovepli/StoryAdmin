package com.story.storyadmin.manyThread.DeadLockDemo11;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author: lipan
 * @date: 11:26 下午
 * @description:
 */
public class DeadLockRunnable2 implements Runnable{

    private DeadLock deadLock1;

    private DeadLock deadLock2;

    private Lock lock;

    public DeadLockRunnable2(DeadLock deadLock1, DeadLock deadLock2, Lock lock) {
        this.deadLock1=deadLock1;
        this.deadLock2=deadLock2;
        this.lock=lock;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running...");
        String tName = Thread.currentThread().getName();
        // TODO Auto-generated method stub
        deadLock1.flag = 1;
        try {
        //获取不到锁，就等 5 秒，如果 5 秒后还是获取不到就返回 false
            if (lock.tryLock(5000, TimeUnit.MILLISECONDS)) {
                System.out.println(tName + "获取到锁！ ");
            } else {
                System.out.println(tName + "获取不到锁！ ");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            deadLock2.money(0);
        } catch (Exception e) {
            System.out.println(tName + "出错了！！！ ");
        } finally {
            System.out.println("当前的线程是"+Thread.currentThread().getName()+"释放锁！！");
            lock.unlock();
        }
    }
}
