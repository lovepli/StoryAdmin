package com.story.storyadmin.manyThread.JUC.AQS.utils.utilsThreadDemo.countDownLatchDemo5.CountDownLatchDemo;

/**
 * @author: 59688
 * @date: 2021/7/27
 * @description: 共享数据类
 */
public class ShareDatas {

    private int num = 10;

    //同步方法
    public synchronized void inc() {
        num++;
        System.out.println(Thread.currentThread().getName() + ": invoke inc method num =" + num);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //同步方法
    public synchronized void dec() {
        num--;
        System.out.println(Thread.currentThread().getName() + ": invoke inc method num =" + num);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
