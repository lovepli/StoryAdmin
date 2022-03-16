package com.story.storyadmin.manyThread.DeadLockDemo11;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author: lipan
 * @date: 11:26 下午
 * @description:
 */
public class DeadLockRunnable1 implements Runnable{

    private DeadLock deadLock;

    private Lock lock;

    public DeadLockRunnable1(DeadLock deadLock){
        this.deadLock=deadLock;
    }

    public DeadLockRunnable1(DeadLock deadLock, Lock lock){
        this.deadLock=deadLock;
        this.lock=lock;
    }

    @Override
    public void run() {

        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running...");

        // TODO Auto-generated method stub
        String tName = Thread.currentThread().getName();

        deadLock.flag = 1;
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
            deadLock.money(1);
        } catch (Exception e) {
            System.out.println(tName + "出错了！！！ ");
        } finally {
            System.out.println("当前的线程是"+Thread.currentThread().getName()+"释放锁！！");
            lock.unlock();
        }

    }
}
