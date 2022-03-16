package com.story.storyadmin.basic.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: lipan
 * @date: 2019-06-27
 * @description:
 * Lock 实现提供更广泛的锁定操作可以比使用 synchronized 获得方法和声明更好。 他们允许更灵活的结构， 可以
 * 有完全不同的特性， 可以支持多个相关的 Condition 对象。
 */

class Ticket //实例例 eld +method
{
    private int number = 30;
 //1 同步 public synchronized void sale()
 //2 同步 synchronized(this) {}
// {
//     if (number > 0) {
//         System.out.println(Thread.currentThread().getName() + "卖出" + (number--) + "\t 还剩 number");
//     }
// }
// Lock implementations provide more extensive locking operations
// than can be obtained using synchronized methods and statements.
    private Lock lock = new ReentrantLock();//List list = new ArrayList()

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出" + (number--) + "\t 还剩 number");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadLock {

    public static void main(String[] args) {//main 所有程序
        Ticket ticket = new Ticket();
//Thread(Runnable target, String name) Allocates a new Thread object.
//        new Thread(() -> {
//            for (int i = 1; i < 40; i++) ticket.sale();
//        }, "AA").start();
//        new Thread(() -> {
//            for (int i = 1; i < 40; i++) ticket.sale();
//        }, "BB").start();
//        new Thread(() -> {
//            for (int i = 1; i < 40; i++) ticket.sale();
//        }, "CC").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "AA").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "BB").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "CC").start();
    }

}