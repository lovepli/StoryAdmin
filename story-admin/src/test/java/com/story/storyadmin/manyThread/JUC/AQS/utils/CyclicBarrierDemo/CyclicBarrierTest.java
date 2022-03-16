package com.story.storyadmin.manyThread.JUC.AQS.utils.CyclicBarrierDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: lipan
 * @date: 2021年08月26日 10:05 上午
 * @description: CyclicBarrier 介绍和使用
 * CyclicBarrier（循环屏障）通过它可以实现让一组线程等待满足某个条件后同时执行。
 *
 * CyclicBarrier 经典使用场景是公交发车，为了简化理解我们这里定义，每辆公交车只要上满 4 个人就发车，后面来的人都会排队依次遵循相应的标准。
 *
 * 它的构造方法为 CyclicBarrier(int parties,Runnable barrierAction) 其中，parties 表示有几个线程来参与等待，barrierAction 表示满足条件之后触发的方法。CyclicBarrier 使用 await() 方法来标识当前线程已到达屏障点，然后被阻塞。
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException {
        //当线程数满4时，才会并发执行run()发车方法。
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {
                System.out.println("发车了");
            }
        });
        for (int i = 0; i < 4; i++) {
            //创建四个线程
            new Thread(new CyclicWorker(cyclicBarrier)).start();
        }
    }
    static class CyclicWorker implements Runnable {
        private CyclicBarrier cyclicBarrier;
        CyclicWorker(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }
        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                System.out.println("乘客：" + i);
                try {
                    cyclicBarrier.await();//当前线程已到达屏障点，然后被阻塞。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
