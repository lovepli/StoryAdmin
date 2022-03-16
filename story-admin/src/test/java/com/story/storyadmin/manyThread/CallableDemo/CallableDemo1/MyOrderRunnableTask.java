package com.story.storyadmin.manyThread.CallableDemo.CallableDemo1;

/**
 * @author: 59688
 * @date: 2021/7/23
 * @description:
 */
public class MyOrderRunnableTask implements Runnable{

    private String name;

    public MyOrderRunnableTask(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("-----线程-----" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        name = "MyOrderRunnableTask";
        System.out.println("MyOrderRunnableTask已执行");
    }

}
