package com.story.storyadmin.manyThread.ThreadSafe;

/**
 * @author: lipan
 * @date: 2021年08月26日 2:19 下午
 * @description: 使用 synchronized 修饰方法
 */
public class SynchronizedTest3 {
    static int number = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread sThread = new Thread(() -> addNumber());
        Thread sThread2 = new Thread(() -> addNumber());
        sThread.start();
        sThread2.start();
        sThread.join();
        sThread2.join();
        System.out.println("number：" + number);
    }
    public synchronized static void addNumber() {
        for (int i = 0; i < 10000; i++) {
            ++number;
        }
    }
}