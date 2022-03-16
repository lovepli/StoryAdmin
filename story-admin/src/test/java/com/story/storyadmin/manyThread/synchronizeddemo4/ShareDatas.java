package com.story.storyadmin.manyThread.synchronizeddemo4;

/**
 * @author: 59688
 * @date: 2021/7/27
 * @description: 共享数据类
 */
public class ShareDatas {

    private int num = 10;

    //同步方法 是一种互斥锁，一次只能允许一个线程进入被锁住的方法/代码块
    public synchronized void inc() {
        num++;
        System.out.println(Thread.currentThread().getName() + ": invoke inc method num =" + num);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
