package com.story.storyadmin.manyThread.RunnableDemo.demo1;

/**
 * @author: 59688
 * @date: 2021/8/4
 * @description: 　3、将这些runnable对象作为某个类的内部类，共享数据作为这个外部类的成员变量，每个线程对共享数据的操作也分配到外部类，以便实现对共享数据进行的各个操作进行互斥和通信，作为内部类的各个runnable对象调用外部类的这些方法。
 */
public class MulteThreadlShareData3 {
    static int count = 100;
    public static void main(String[] args) {
        new Thread(new Decrease()).start();
        new Thread(new Increment()).start();

    }
    public synchronized static void decrease(){
        count--;
        System.out.println(Thread.currentThread().getName()+"decrease this count: "+count);
    }
    public synchronized static void increment(){
        count++;
        System.out.println(Thread.currentThread().getName()+"increment this count: "+count);
    }
    static class Decrease implements Runnable{
        @Override
        public void run() {
            decrease();
        }

    }
    static class Increment implements Runnable{
        @Override
        public void run() {
            increment();
        }

    }
}
