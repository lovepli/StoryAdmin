package com.story.storyadmin.basic.thread.demo;

/**
 * @author: lipan
 * @date: 2019-06-10
 * @description:
 */


class HelloThread extends Thread {

    String name;

    public HelloThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Hello, " + name + "!");
            try {
                Thread.sleep(100);  //让当前线程暂停100毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class ThreadDem1 {

    public static void main(String[] args) throws Exception {
        Thread t1 = new HelloThread("Bob");
        t1.start();
        Thread t2 = new HelloThread("Bob");
        t2.start();
        for (int i = 0; i < 3; i++) {
            System.out.println("JunitTestSuite!");
            Thread.sleep(100);
        }
    }
}
