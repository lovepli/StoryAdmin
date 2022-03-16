package com.story.storyadmin.manyThread.JUC.AQS.locks.lockDemo15;


/**
 * @author: 59688
 * @date: 2021/7/29
 * @description:
 */
public class LockDemo {

    private int account = 100;

    public int getAccount() {
        return account;
    }

    //同步代码块
    public void add(){
        synchronized (this) { //设置关键字 synchronized关键字，以当前类为锁
            while (account < 1000) {
                    //打印是否执行该方法
                    System.out.println(Thread.currentThread().getName() + " run1: " + account++);
            }
        }
    }

    // 同步方法
    public synchronized void add2( int money){
               account += money;
    }
    public synchronized void del(int money){
        account -= money;
    }

    /**
     * 同步代码块和同步方法有什么区别？
     * 同步方法就是在方法前加关键字 synchronized；同步代码块则是在方法内部使用 synchronized
     * 加锁对象相同的话，同步方法锁的范围大于等于同步方法块。一般加锁范围越大，性能越差
     * 同步方法如果是 static 方法，等同于同步方法块加锁在该 Class 对象上
     */

}
