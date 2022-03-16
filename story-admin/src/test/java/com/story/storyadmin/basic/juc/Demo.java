package com.story.storyadmin.basic.juc;

/**
 * @author: lipan
 * @date: 2021年08月31日 9:47 上午
 * @description: Java5之后新增加java.util.concurrent 包
 */
public class Demo {

    /**
     * 1) java.util.concurrent 包 (多线程并发库)
     *     包含核心组件：执行程序(线程池) ，并发队列，同步器，并发Condition
     * 2) java.util.concurrent.atomic 包 (多线程的原子性操作提供的工具类)
     * 3) java.util.concurrent.lock 包 (多线程的锁机制)
     *   Lock 接口,ReadWriteLock 接口,Condition 接口
     */


    /**
     * Java5 中并发库中，线程池创建线程大致可以分为下面三种：
     * //创建固定大小的线程池
     * ExecutorService fPool = Executors.newFixedThreadPool(3);
     * //创建缓存大小的线程池
     * ExecutorService cPool = Executors.newCachedThreadPool();
     * //创建单一的线程池
     * ExecutorService sPool = Executors.newSingleThreadExecutor();
     */

    /**
     * 补充：在 java 的多线程中，一但线程关闭，就会成为死线程。关闭后死线程就没有办法在启动了。再次启动就会出现
     * 异常信息： Exception in thread "main" java.lang.IllegalThreadStateException。那么如何解决这个问题呢？
     * 我们这里就可以使用 Executors.newSingleThreadExecutor()来再次启动一个线程。（面试）
     */
}
