package com.story.storyadmin.manyThread.RunnableDemo.demo13.demo2;

/**
 * @author: 59688
 * @date: 2021/8/4
 * @description:
 */
public class Calculator extends Thread {
    int total;

    @Override
    public void run() {
        synchronized (this){
            for(int i = 0; i < 101; i++){
                total += i;
            }
            this.notify();
        }

    }
}
