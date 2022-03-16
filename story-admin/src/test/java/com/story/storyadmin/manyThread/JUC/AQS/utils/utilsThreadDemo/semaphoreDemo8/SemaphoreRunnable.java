package com.story.storyadmin.manyThread.JUC.AQS.utils.utilsThreadDemo.semaphoreDemo8;

/**
 * @author: 59688
 * @date: 2021/7/28
 * @description:
 */
public class SemaphoreRunnable implements  Runnable{

    public SemaphoreDemo semaphoreDemo;

    public SemaphoreRunnable(SemaphoreDemo semaphoreDemo){
        this.semaphoreDemo=semaphoreDemo;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running...");
        semaphoreDemo.test();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
