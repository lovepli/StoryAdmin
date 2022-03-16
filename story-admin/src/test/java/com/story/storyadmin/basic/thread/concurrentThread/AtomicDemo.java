package com.story.storyadmin.basic.thread.concurrentThread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: lipan
 * @date: 2019-06-11
 * @description:
 *
 * atomic
 * 使用java.util.concurrentThread.atomic提供的原子操作可以简化多线程编程
 * AtomicInteger / AtomicLong /AtomicIntegerArray等
 * 原子操作实现了无锁的线程安全
 * 适用于计数器。累加器等
 */

class Counter2{

    private AtomicInteger value = new AtomicInteger(0);

    public int add(int m) {
        return this.value.addAndGet(m);  //注意这里使用的方法并没有定义？？？
    }

    public int dec(int m) {
        return this.value.addAndGet(-m);
    }
    public int get() {
        return this.value.get();
    }
}

public class AtomicDemo {
    static final int LOOP = 100;

    public static void main(String[] args) throws Exception{
        Counter2 counter = new Counter2();
        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < LOOP; i++) {
                    counter.add(1);
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < LOOP; i++) {
                    counter.dec(1);
                }
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.get());
    }
}
