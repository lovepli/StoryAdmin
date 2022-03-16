package com.story.storyadmin.manyThread.volatileDemo8;

/**
 * @author: 59688
 * @date: 2021/7/28
 * @description:
 */
public class Counter {

    // volatile 修饰，是得count对所有线程可见
    private volatile int count = 0;

    public void inc() {
        count++;
        System.out.println(Thread.currentThread().getName() + ": invoke inc method num =" + count);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "[count=" + count + "]";
    }
}
