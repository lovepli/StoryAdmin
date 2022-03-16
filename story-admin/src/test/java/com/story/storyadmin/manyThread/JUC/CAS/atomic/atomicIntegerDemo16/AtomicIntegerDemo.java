package com.story.storyadmin.manyThread.JUC.CAS.atomic.atomicIntegerDemo16;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子性操作类的使用
 * https://mp.weixin.qq.com/s/uXBVQbgH7QTMRUfocIMFAQ
 * 1. 基本类型的使用
 */
public class AtomicIntegerDemo {
    public final static AtomicInteger TEST_INTEGER = new AtomicInteger(1);
    public static void main(String []args) {
        for(int i = 0 ; i < 10 ; i++) { //开启10个线程
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int now = TEST_INTEGER.incrementAndGet(); //自增
                    System.out.println(Thread.currentThread().getName() + " get value : " + now);
                }
            }.start();
        }
    }
}