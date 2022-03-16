package com.story.storyadmin.manyThread.JUC.AQS.utils.utilsThreadDemo.countDownLatchDemo5.CountDownLatchDemo;


import java.util.concurrent.CountDownLatch;

/**
 * @author: lipan
 * @date: 10:58 下午
 * @description:
 */
public class RunnableCusToInc implements Runnable{
    //共享数据类
    private ShareDatas shareData;

    public RunnableCusToInc(ShareDatas shareData, CountDownLatch latch) {
        this.shareData = shareData;
        this.latch = latch;
    }

    private CountDownLatch latch;


    @Override
    public void run() {
        System.out.println("-----子线程-----" + Thread.currentThread().getName());
        for (int i = 0; i < 5; i++) { // 得到共享数据的线程将执行5次相加操作，并不一定是连续执行
            shareData.inc();
        }
        latch.countDown();
    }
}