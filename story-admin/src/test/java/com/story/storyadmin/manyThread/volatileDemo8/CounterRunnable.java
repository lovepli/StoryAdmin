package com.story.storyadmin.manyThread.volatileDemo8;

/**
 * @author: 59688
 * @date: 2021/7/28
 * @description:
 */
public class CounterRunnable implements Runnable{

    private  Counter counter;

    public CounterRunnable(Counter counter){
        this.counter=counter;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running...");
        counter.inc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
