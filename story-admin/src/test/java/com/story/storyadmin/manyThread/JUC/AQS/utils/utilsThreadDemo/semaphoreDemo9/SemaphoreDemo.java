package com.story.storyadmin.manyThread.JUC.AQS.utils.utilsThreadDemo.semaphoreDemo9;

import java.util.concurrent.Semaphore;

/**
 * @author: 59688
 * @date: 2021/7/28
 * @description:
 */
public class SemaphoreDemo {

    private static int num;

    private static Semaphore semaphore = new Semaphore(0);

    public  void test(){
        num = 1;
        //初始化完参数后释放两个 permit
        semaphore.release(2);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public  void test2() throws InterruptedException {
        //获取 permit，如果 semaphore 没有可用的 permit 则等待，如果有则消耗一个
        semaphore.acquire();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "获取到 num 的值为： " + num);
    }
}
