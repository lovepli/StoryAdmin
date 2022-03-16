package com.story.storyadmin.manyThread.ThreadSafe;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: lipan
 * @date: 2021年08月26日 2:20 下午
 * @description: 尝试一段时间内获取锁
 *
 * tryLock() 有一个扩展方法 tryLock(long timeout, TimeUnit unit) 用于尝试一段时间内获取锁，具体实现代码如下：
 * 以上代码执行结果如下：
 *
 * 2019-07-05 19:53:51
 *
 * true
 *
 * 2019-07-05 19:53:53
 * 可以看出锁在休眠了 2 秒之后，就被线程二直接获取到了，所以说 tryLock(long timeout, TimeUnit unit) 方法内的 timeout 参数指的是获取锁的最大等待时间。
 *
 * (3) ReentrantLock 注意事项
 * 使用 ReentrantLock 一定要记得释放锁，否则该锁会被永久占用。
 */
public class LockTest3 {

    public static void main(String[] args) throws InterruptedException {
        Lock reentrantLock = new ReentrantLock();
// 线程一
        new Thread(() -> {
            try {
                reentrantLock.lock();
                System.out.println(LocalDateTime.now());
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
                System.out.println(reentrantLock.tryLock(3, TimeUnit.SECONDS));
                System.out.println(LocalDateTime.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
}
}
