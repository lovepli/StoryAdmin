package com.story.storyadmin.manyThread.JUC.AQS.locks.lockDemo15;



/**
 * @author: 59688
 * @date: 2021/7/29
 * @description:
 */
public class LockRunnable1 implements Runnable{

    private LockDemo lockDemo;

    public LockRunnable1(LockDemo lockDemo) {
        this.lockDemo = lockDemo;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running...");
       // lockDemo.add();
        for (int i=0;i<100;i++){
            lockDemo.add2(10);
            System.out.println(i + "账户余额为：" + lockDemo.getAccount());
        }

    }
}
