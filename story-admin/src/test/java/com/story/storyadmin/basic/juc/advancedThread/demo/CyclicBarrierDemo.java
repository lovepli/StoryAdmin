package com.story.storyadmin.basic.juc.advancedThread.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: lipan
 * @date: 2019-06-04
 * @description:
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws InterruptedException {

        new CyclicBarrierDemo().go();
    }

    private void go() throws  InterruptedException{
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        //依次启动三个线程，并启动
        new Thread(new Task(cyclicBarrier),"Thread1").start();

        Thread.sleep(1000);
        new Thread(new Task(cyclicBarrier),"Thread2").start();
        Thread.sleep(1000);
        new Thread(new Task(cyclicBarrier),"Thread3").start();

    }

    class Task implements Runnable {
        private  CyclicBarrier cyclicBarrier;
        public Task( CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier=cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"已经到达"+System.currentTimeMillis());

            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("线程"+Thread.currentThread().getName()+"开始处理"+System.currentTimeMillis());


        }
    }

}
