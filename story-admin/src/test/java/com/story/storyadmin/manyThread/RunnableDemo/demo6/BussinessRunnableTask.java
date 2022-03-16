package com.story.storyadmin.manyThread.RunnableDemo.demo6;

/**
 * @author: lipan
 * @date: 11:12 下午
 * @description: 要求： 子线程运行执行 10 次后，主线程再运行 5 次。这样交替执行三遍
 */
public class BussinessRunnableTask implements Runnable{

    private Bussiness bussiness;

    public BussinessRunnableTask(Bussiness bussiness) {
        this.bussiness = bussiness;
    }

    @Override
    public void run() { //创建线程
        System.out.println("-----子线程-----" + Thread.currentThread().getName());
        for (int i = 0; i < 3; i++) {
            bussiness.subMethod();
        }
    }
}
