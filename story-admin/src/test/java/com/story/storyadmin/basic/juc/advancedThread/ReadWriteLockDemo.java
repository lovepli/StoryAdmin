package com.story.storyadmin.basic.juc.advancedThread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: lipan
 * @date: 2019-06-27
 * @description:
 *
 * JUC 强大的辅助类讲解:
 * 1. ReentrantReadWriteLock 读写锁
 * 2. CountDownLatch 减少计数
 * 原理：
 * CountDownLatch 主要有两个方法， 当一个或多个线程调用 await 方法时， 这些线程会阻塞。
 * 其它线程调用 countDown 方法会将计数器减 1(调用 countDown 方法的线程不会阻塞)， 当计数器的值变为 0 时，
 * 因 await 方法阻塞的线程会被唤醒， 继续执行。
 *
 * 3. CyclicBarrier 循环栅栏
 *  * 原理：
 *  * CyclicBarrier 的字面意思是可循环（Cyclic） 使用的屏障（Barrier）。 它要做的事情是， 让一组线程到达一个屏障
 *  * （也可以叫同步点） 时被阻塞,直到最后一个线程到达屏障时， 屏障才会开门， 所有被屏障拦截的线程才会继续干活。
 *  * 线程进入屏障通过 CyclicBarrier 的 await()方法
 *
 *  4. Semaphore 信号灯
 *  原理：
 *  在信号量上我们定义两种操作：
 * acquire（获取） 当一个线程调用 acquire 操作时， 它要么通过成功获取信号量（信号量减 1）， 要么一直等下
 * 去， 直到有线程释放信号量， 或超时。
 * release（释放） 实际上会将信号量的值加 1， 然后唤醒等待的线程。 信号量主要用于两个目的， 一个是用于多
 * 个共享资源的互斥使用， 另一个用于并发线程数的控制
 *
 * 一个线程写入,100 个线程读取
 *
 */

class MyQueue
{
    private Object obj;
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    public void readObj()
    {
        rwLock.readLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t"+obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }
    public void writeObj(Object obj)
{
    rwLock.writeLock().lock();
    try
    {
        this.obj = obj;
        System.out.println(Thread.currentThread().getName()+"writeThread:\t"+obj);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        rwLock.writeLock().unlock();
    }
}
}

public class ReadWriteLockDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        MyQueue q = new MyQueue();
        new Thread(() -> {
            q.writeObj("ClassName1221");
        }, "AAAAA").start();
        for (int i = 1; i <=100; i++)
        {
            new Thread(() -> {
                q.readObj();
            },String.valueOf(i)).start();
        }
    }
}
