package com.story.storyadmin.basic.juc.threadCommunication;

/**
 * @author: lipan
 * @date: 2019-06-27
 * @description:
 *
 * 现在两个线程，
 * * 可以操作初始值为零的一个变量，
 * * 实现一个线程对该变量加 1， 一个线程对该变量减 1，
 * * 交替， 来 10 轮。
 *
 * 多线程变成模板（套路）
 * 1 判断
 * 2 干活
 * 3 通知
 *
 * 换成 4 个线程会导致错误， 虚假唤醒
 * 原因： 在 java 多线程判断时， 不能用 if， 程序出事出在了判断上面，
 * 突然有一天加的线程进到 if 了， 突然中断了交出控制权，
 * 没有进行验证， 而是直接走下去了， 加了两次， 甚至多次
 *解决虚假唤醒： 查看 API， java.lang.Object
 * 中断和虚假唤醒是可能产生的， 所以要用 loop 循环， if 只判断一次， while 是只要唤醒就要拉回来再判断一次。
 * if 换成 while
 */

class ShareDataOne {//资源类
    private int number = 0;//初始值为零的一个变量

    public synchronized void increment() throws InterruptedException {

        if (number != 0) {//1 判断
            this.wait();
        }
        ++number;//2 干活
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();//3 通知
    }

    public synchronized void decrement() throws InterruptedException {

        if (number == 0) {// 1 判断
            this.wait();
        }
        --number;//2 干活
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();// 3 通知
    }
}

public class NotifyWaitDemoOne {
    public static void main(String[] args) {
        ShareDataOne sd = new ShareDataOne();
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                try {
                    sd.increment();
                } catch (InterruptedException e) {
                     // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                try {
                    sd.decrement();
                } catch (InterruptedException e) {
                      // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}