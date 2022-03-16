package com.story.storyadmin.basic.thread.demo;



/**
 * @author: lipan
 * @date: 2019-06-10
 * @description:  Thread 的join方法
 */

class Hellothread extends Thread{

    private  String name;

    public Hellothread(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println("Hello, " + name + "!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadDemo2 {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Hellothread("Bob");
        System.out.println("start");
        t1.start();
        t1.join();  //等待t1线程执行结束  此处因为是两个线程，所以main主线程将等待t1执行完
        System.out.println("end");
    }
//    start
//    Hello, Bob!
//    end
}
