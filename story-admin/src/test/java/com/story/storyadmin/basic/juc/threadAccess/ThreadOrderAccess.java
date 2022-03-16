package com.story.storyadmin.basic.juc.threadAccess;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: lipan
 * @date: 2019-06-27
 * @description:
 *
 * 线程间定制化调用通信
 * 实现步骤：
 * （1） 有顺序通知， 需要有标识位
 * （2） 有一个锁 Lock， 3 把钥匙 Condition
 * （3） 判断标志位
 * （4） 输出线程名+第几次+第几轮
 * （5） 修改标志位， 通知下一个
 *
 *
 * 多线程之间按顺序调用， 实现 A->B->C
 * * 三个线程启动， 要求如下：
 * * *
 * AA 打印 5 次， BB 打印 10 次， CC 打印 15 次
 * * 接着
 * * AA 打印 5 次， BB 打印 10 次， CC 打印 15 次
 * * ......来 10 轮
 */

class ShareResource {
    private int number = 1;//1:A 2:B 3:C
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(int totalLoopNumber) {
        lock.lock();
        try {
//1 判断
            while (number != 1) {
//A 就要停止
                c1.await();
            }
//2 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t totalLoopNumber:" +
                        totalLoopNumber);
            }
//3 通知
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(int totalLoopNumber) {
        lock.lock();
        try {
//1 判断
            while (number != 2) {
//A 就要停止
                c2.await();
            }
//2 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t totalLoopNumber:" +
                        totalLoopNumber);
            }
//3 通知
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15(int totalLoopNumber) {
        lock.lock();
        try {
//1 判断
            while (number != 3) {
//A 就要停止
                c3.await();
            }
//2 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t totalLoopNumber:" +
                        totalLoopNumber);
            }
//3 通知
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource sr = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                sr.print5(i);
            }
        }, "AA").start();
        new Thread(() -> {

            for (int i = 1; i <= 10; i++) {
                sr.print10(i);
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                sr.print15(i);
            }
        }, "CC").start();
    }
}
