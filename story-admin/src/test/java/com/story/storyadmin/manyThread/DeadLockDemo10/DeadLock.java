package com.story.storyadmin.manyThread.DeadLockDemo10;

/**
 * @author: lipan
 * @date: 11:24 下午
 * @description:
 *  * 如何避免死锁？
 *  * 1）加锁顺序（线程按照一定的顺序加锁）
 *  * 2）加锁时限（线程尝试获取锁的时候加上一定的时限，超过时限则放弃对该锁的请求，并释放自己占有的锁）
 */
public class DeadLock {

    public int flag = 1;

    //静态对象是类的所有对象共享的
    private static Object o1 = new Object(), o2 = new Object();

    public void money(int flag) {
        this.flag=flag;
        if( flag ==1){
            synchronized (o1) {
                System.out.println("当前的线程是"+ Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("当前的线程是"+ Thread.currentThread().getName()+" "+"flag 的值"+"1");
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(flag ==0){
            synchronized (o2) {
                System.out.println("当前的线程是"+ Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("当前的线程是"+ Thread.currentThread().getName()+" "+"flag 的值"+"0");
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }


}
