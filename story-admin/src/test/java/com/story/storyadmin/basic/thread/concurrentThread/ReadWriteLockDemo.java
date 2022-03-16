package com.story.storyadmin.basic.thread.concurrentThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: lipan
 * @date: 2019-06-11
 * @description:
 *
 * 使用ReadWriteLock可以解决：
 * 只允许一个线程写入(其他线程既不能写入也不能读取)
 * 没有写入时，多个线程允许同时读（提高性能）
 *
 * ReadWriteLock适用条件
 * 同一个实例，有大量线程读取，仅有少数线程修改
 * 例如一个论坛的帖子，回复是少量线程写入的，浏览是大量线程读取的
 *
 * 使用ReadWriteLock可以提高读取效率:
 * ReadWriteLock只允许一个线程写入
 * ReadWriteLock允许多个线程同时读取
 * ReadWriteLock适合读多写少的场景
 *
 */

class Counterr {

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock rlock = lock.readLock(); //获取读锁
    private Lock wlock = lock.writeLock();

    private int value = 0;

    public void add(int m) {
        wlock.lock();  //加锁
        try {
            this.value += m;
        } finally {
            wlock.unlock();
        }
    }

    public void dec(int m) {
        wlock.lock();
        try {
            this.value -= m;
        } finally {
            wlock.unlock();
        }
    }

    public int get() {
        rlock.lock(); //加锁
        try {
            return this.value;
        } finally {
            rlock.unlock();
        }
    }
}

public class ReadWriteLockDemo {
    final static int LOOP = 100;

    public static void main(String[] args) throws Exception {
        Counterr counter = new Counterr();
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
