package com.story.storyadmin.basic.thread.demo;

/**
 * @author: lipan
 * @date: 2019-06-10
 * @description: 中断线程
 *
 * volatile关键字的目的是是告诉虚拟机:
 * 每次访问变量时，总是获取主内存的最新值
 * 每次修改变量后，立刻回写到主内存
 *volatile关键字解决的是可见性问题
 * 当一个线程修改了某个共享变量的值，其他线程能够立刻看到修改后的值
 *
 */
class HelloThead extends Thread{

    volatile  boolean running = true;

    @Override
    public void run() {
       // while (!isInterrupted()) {
        while (running) {
            System.out.println("Hello");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Interrupted!");
                break;
            }
        }
        System.out.println("Thread end");
    }
}
public class ThreadDemo5 {

    public static void main(String[] args) throws Exception {
        HelloThead t = new HelloThead();
        t.start();
        Thread.sleep(1000);
       // t.interrupt();  //中断线程
        t.running = false;  //通过设置标志位中断线程
        System.out.println("JunitTestSuite end");
    }
}
