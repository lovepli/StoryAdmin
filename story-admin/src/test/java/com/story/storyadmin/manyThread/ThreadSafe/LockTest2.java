package com.story.storyadmin.manyThread.ThreadSafe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: lipan
 * @date: 2021年08月26日 2:20 下午
 * @description: 尝试获取锁
 *
 * ReentrantLock 可以无阻塞尝试访问锁，使用 tryLock() 方法，具体使用如下：
 * 以上代码执行结果如下：
 *
 * false
 *
 * true
 */
public class LockTest2 {

    public static void main(String[] args) throws InterruptedException {
        Lock reentrantLock = new ReentrantLock();
// 线程一
        new Thread(() -> {
            try {
                reentrantLock.lock();
                Thread.sleep(2 * 1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }).start();
// 线程二
        new Thread(() -> {
            try {
                Thread.sleep(1 * 1000);
                System.out.println(reentrantLock.tryLock());
                Thread.sleep(2 * 1000);
                System.out.println(reentrantLock.tryLock());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
