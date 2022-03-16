package com.story.storyadmin.basic.thread.demo;

/**
 * @author: lipan
 * @date: 2019-06-10
 * @description:
 */

class Counter{  //数据封装；把同步的逻辑封装到持有数据的实例中

    int count = 0;

    //同步方法
    public synchronized void add(int n) {
        count += n;
    }

    //同步代码块
    public synchronized void add1(int n) {
        synchronized (this) {
            count += n;
        }

    }


    public synchronized void dec(int n) {
        count -= n;
    }

    public int get() {
        return count;
    }
}

class AddThread2 extends Thread{

    Counter counter;

    public AddThread2(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < Demo2.Loop; i++) {
            counter.add(1);
        }
    }
}

class DecThread2 extends Thread{

    Counter counter;

    public DecThread2(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < Demo2.Loop; i++) {
            counter.dec(1);
        }
    }
}


public class Demo2 {

    final static int Loop = 10000;

    public static void main(String[] args) throws Exception{
        Counter counter = new Counter();
        Thread t1 = new AddThread2(counter);
        Thread t2 = new DecThread2(counter);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.get());

    }


}
