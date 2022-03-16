package com.story.storyadmin.manyThread.JUC.AQS.utils.utilsThreadDemo.semaphoreDemo8;

import java.util.concurrent.Semaphore;

/**
 * @author: 59688
 * @date: 2021/7/28
 * @description:
 */
public class SemaphoreDemo {

    /**
     * Semaphore
     * 信号量的主要用户两个目的，一个是用于共享资源的相互排斥使用 ，另一个是用于并发资源数的控制。
     */
     Semaphore semaphore = new Semaphore(5,true);

    public  void test(){
        try {
            //申请一个请求
            semaphore.acquire();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"进来了");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"走了");
        //释放一个请求
        semaphore.release();
    }
}
