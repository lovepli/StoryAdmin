package com.story.storyadmin.manyThread.DeadLockDemo11;

/**
 * @author: 59688
 * @date: 2021/7/29
 * @description:
 */
public class DeadLock {

    public int flag = 1;
    //静态对象是类的所有对象共享的
    private static Object o1 = new Object(), o2 = new Object();
    public void money(int flag) throws InterruptedException {
        this.flag=flag;
        if( flag ==1){
            synchronized (o1) {
                Thread.sleep(500);
                synchronized (o2) {
                    System.out.println("当前的线程是"+
                            Thread.currentThread().getName()+" "+"flag 的值"+"1");
                }
            }
        }
        if(flag ==0){
            synchronized (o2) {
                Thread.sleep(500);
                synchronized (o1) {
                    System.out.println("当前的线程是"+
                            Thread.currentThread().getName()+" "+"flag 的值"+"0");
                }
            }
        }
    }
}
