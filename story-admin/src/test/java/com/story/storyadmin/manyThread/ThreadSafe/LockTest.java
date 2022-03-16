package com.story.storyadmin.manyThread.ThreadSafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: lipan
 * @date: 2021年08月26日 2:20 下午
 * @description:
 */
public class LockTest {
    static int number = 0;
    public static void main(String[] args) throws InterruptedException {
        // ReentrantLock 使用
        Lock lock = new ReentrantLock();
        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();
                addNumber();
            } finally {
                lock.unlock();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                lock.lock();
                addNumber();
            } finally {
                lock.unlock();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("number：" + number);
    }
    public static void addNumber() {
        for (int i = 0; i < 10000; i++) {
            ++number;
        }
    }
}
