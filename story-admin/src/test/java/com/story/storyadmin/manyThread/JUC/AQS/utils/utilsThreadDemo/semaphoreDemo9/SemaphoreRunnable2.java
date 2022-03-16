package com.story.storyadmin.manyThread.JUC.AQS.utils.utilsThreadDemo.semaphoreDemo9;


/**
 * @author: 59688
 * @date: 2021/7/28
 * @description:
 */
public class SemaphoreRunnable2 implements  Runnable{

    private SemaphoreDemo semaphoreDemo;

    public SemaphoreRunnable2(SemaphoreDemo semaphoreDemo){
        this.semaphoreDemo=semaphoreDemo;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running...");
        try {
            semaphoreDemo.test2();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
