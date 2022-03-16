package com.story.storyadmin.manyThread.ThreadSafe;

/**
 * @author: lipan
 * @date: 2021年08月26日 2:15 下午
 * @description:  JAVA 并发编程（五）线程安全之 synchronized 和 ReentrantLock
 * 非线程安全代码示例
 *
 * 以下程序执行结果如下：
 *
 * number：12085
 * 每次执行的结果可能略有差异，不过几乎不会等于（正确的）累计之和 20000。
 *
 * 3）线程安全的解决方案
 * 线程安全的解决方案有以下几个维度：
 *
 * 1、数据不共享，单线程可见，比如 ThreadLocal 就是单线程可见的；
 * 2、使用线程安全类，比如 StringBuffer 和 JUC（java.util.concurrent）下的安全类（后面文章会专门介绍）；
 * 3、使用同步代码或者锁。
 */
public class ThreadSafeTest {
    static int number = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> addNumber());
        Thread thread2 = new Thread(() -> addNumber());
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