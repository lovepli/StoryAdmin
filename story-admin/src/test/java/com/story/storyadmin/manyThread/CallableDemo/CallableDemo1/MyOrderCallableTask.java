package com.story.storyadmin.manyThread.CallableDemo.CallableDemo1;

import java.util.concurrent.Callable;

/**
 * @author: 59688
 * @date: 2021/7/23
 * @description:
 */

public class MyOrderCallableTask implements Callable<String> {

    private String name;

    public MyOrderCallableTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.println("-----线程-----" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        name = "MyOrderCallableTask";
        System.out.println("MyOrderCallableTask已执行");
        return name;
    }

}
