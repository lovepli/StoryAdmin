package com.story.storyadmin.basic.juc.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: lipan
 * @date: 2019-06-27
 * @description:
 * condition的使用 java.util.concurrent.locks.Condition;
 */
class ShareData//资源类
{
    private int number = 0;//初始值为零的一个变量
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment() throws InterruptedException
    {
        lock.lock();
        try {
//判断
            while(number!=0) {
                condition.await();
            }

//干活
            ++number;
            System.out.println(Thread.currentThread().getName()+" \t "+number);
//通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException
    {
        lock.lock();
        try {
//判断
            while(number!=1) {
                condition.await();
            }
//干活
            --number;
            System.out.println(Thread.currentThread().getName()+" \t "+number);
//通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            lock.unlock();
        }
    }
    /*
            public synchronized void increment() throws InterruptedException
    {
//判断
        while(number!=0) {
            this.wait();
        } /
/干活
            ++number;
        System.out.println(Thread.currentThread().getName()+" \t "+number);
//通知
        this.notifyAll();;
    } p
        ublic synchronized void decrement() throws InterruptedException
    {
//判断
        while(number!=1) {
            this.wait();
        } /
/干活
            --number;
        System.out.println(Thread.currentThread().getName()+" \t "+number);
        //通知
        this.notifyAll();
    }*/
}
