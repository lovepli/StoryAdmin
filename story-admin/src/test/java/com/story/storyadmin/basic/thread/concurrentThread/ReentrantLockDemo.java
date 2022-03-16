package com.story.storyadmin.basic.thread.concurrentThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: lipan
 * @date: 2019-06-11
 * @description:
 * 多线程编程的特点:
 *   多线程需要读写共享数据
 *   多线程经常需要同步
 *   多线程编程的复杂度高，调试更困难
 *
 *   java.util.concurrentThread.locks.ReentrantLock
 *
 *   tryLock()方法可设置超时时间
 *
 *   ReentrantLock 总结：
 *   ReentrantLock 可以替代synchroized
 *   ReentrantLock 获取锁更安全
 *   必须使用 try...finally 保证正确获取和释放锁
 *

 */

class Countr{

    private Lock lock = new ReentrantLock();  //new 一个ReentrantLock实例向上转型为Lock接口

    private int value = 0;

    public void add(int m) {
        lock.lock();  //获取锁
        try {
            this.value += m;  //业务逻辑
        }finally {
            lock.unlock(); //释放锁
        }
    }

    public void dec(int m) {
        lock.lock();
        try{
            this.value -=m;
        }finally {
            lock.unlock();
        }
    }

    public int get() {
        lock.lock();
        try{
            return this.value;
        }finally {
            lock.unlock();
        }
    }
}


public class ReentrantLockDemo {

    final static int LOOP = 100;

    public static void main(String[] args) throws Exception {
        Countr counter = new Countr();
        Thread t1 =new Thread(){
            public void run() {
                for (int i = 0; i < LOOP; i++) {
                    counter.add(1);
                }
            }
        };
        Thread t2=new Thread(){
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
