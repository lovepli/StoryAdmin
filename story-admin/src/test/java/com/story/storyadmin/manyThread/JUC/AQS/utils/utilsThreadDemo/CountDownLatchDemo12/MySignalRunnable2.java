package com.story.storyadmin.manyThread.JUC.AQS.utils.utilsThreadDemo.CountDownLatchDemo12;

/**
 * @author: 59688
 * @date: 2021/7/29
 * @description:
 */
public class MySignalRunnable2 implements Runnable{

    private MySignal mySignal;

    public MySignalRunnable2(MySignal mySignal){
        this.mySignal =mySignal;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running...");
        //线程 2 取这个值 hasDataToProcess
        mySignal.getHasDataToProcess();
        System.out.println("MySignalRunnable1改变以后的值： " + mySignal.getHasDataToProcess());  //true
    }
}
