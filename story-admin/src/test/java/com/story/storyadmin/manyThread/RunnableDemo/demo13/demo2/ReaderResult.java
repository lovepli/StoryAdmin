package com.story.storyadmin.manyThread.RunnableDemo.demo13.demo2;

/**
 * @author: 59688
 * @date: 2021/8/4
 * @description:
 */
public  class ReaderResult extends Thread {
    Calculator c;
    public ReaderResult(Calculator c) {
        this.c = c;
    }

    @Override
    public void run() {
        synchronized (c) {
            try {
                System.out.println(Thread.currentThread() + "等待计算结...");
                c.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "计算结果为:" + c.total);
        }
    }
}
