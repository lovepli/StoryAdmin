package com.story.storyadmin.basic.juc.advancedThread.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: lipan
 * @date: 2019-06-05
 * @description:
 */
public class SemaphoreDemo {
    public static void main(String[] args) {

        //线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        //只能五个线程同时访问  设置初始化为5
        final Semaphore semp = new Semaphore(5);

        //模拟20个客户端访问
        for (int index=0;index<20;index++) {
            final int NO =index;
            Runnable run=new Runnable() {
                @Override
                public void run() {

                    try {
                        //获取许可
                        semp.acquire();
                        System.out.println("Accessing:"+NO);
                        Thread.sleep((long) (Math.random()*10000));
                        //访问完后释放
                        semp.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        //退出线程池
        exec.shutdown();

    }






}
