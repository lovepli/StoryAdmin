package com.story.storyadmin.manyThread.JUC.AQS.utils.utilsThreadDemo.countDownLatchDemo5.CountDownLatchDemo;


import java.util.concurrent.CountDownLatch;

/**
 * @author: 59688
 * @date: 2021/7/27
 * @description:
 */
public class RunnableCusToDec implements Runnable{
    //共享数据类
    private ShareDatas shareData;

    private CountDownLatch latch;

    public RunnableCusToDec(ShareDatas shareData, CountDownLatch latch) {
        this.shareData = shareData;
        this.latch = latch;
    }


    @Override
    public void run() {
        System.out.println("-----子线程-----" + Thread.currentThread().getName());
        for (int i = 0; i < 5; i++) { // 得到共享数据的线程将执行5次相加操作，并不一定是连续执行
            shareData.dec();
        }
        latch.countDown();
    }
}
