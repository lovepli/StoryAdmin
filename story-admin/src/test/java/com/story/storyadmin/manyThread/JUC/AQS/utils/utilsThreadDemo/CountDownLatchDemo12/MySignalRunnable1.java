package com.story.storyadmin.manyThread.JUC.AQS.utils.utilsThreadDemo.CountDownLatchDemo12;

import java.util.concurrent.CountDownLatch;

/**
 * @author: 59688
 * @date: 2021/7/29
 * @description:
 */
public class MySignalRunnable1 implements Runnable{

    private MySignal mySignal;

    CountDownLatch latch;

    public MySignalRunnable1(MySignal mySignal){
        this.mySignal =mySignal;
    }

    public MySignalRunnable1(MySignal mySignal, CountDownLatch latch) {
        this.mySignal = mySignal;
        this.latch = latch;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running...");
        //线程 1 设置 hasDataToProcess 值为 true
        mySignal.setHasDataToProcess(true);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}
