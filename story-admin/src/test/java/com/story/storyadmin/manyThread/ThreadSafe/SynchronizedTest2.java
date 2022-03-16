package com.story.storyadmin.manyThread.ThreadSafe;

/**
 * @author: lipan
 * @date: 2021年08月26日 2:18 下午
 * @description: 使用 synchronized 修饰代码块
 * synchronized 实现原理
 * synchronized 本质是通过进入和退出的 Monitor 对象来实现线程安全的。
 */
public class SynchronizedTest2 {
    static int number = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread sThread = new Thread(() -> {
            // 同步代码
            synchronized (ThreadSafeTest.class) {
                addNumber();
            }
        });
        Thread sThread2 = new Thread(() -> {
            // 同步代码
            synchronized (ThreadSafeTest.class) {
                addNumber();
            }
        });
        sThread.start();
        sThread2.start();
        sThread.join();
        sThread2.join();
        System.out.println("number：" + number);
    }
    public static void addNumber() {
        for (int i = 0; i < 10000; i++) {
            ++number;
        }
    }
}