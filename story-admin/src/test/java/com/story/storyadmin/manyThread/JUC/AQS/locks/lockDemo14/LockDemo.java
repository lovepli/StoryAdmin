package com.story.storyadmin.manyThread.JUC.AQS.locks.lockDemo14;


/**
 * @author: 59688
 * @date: 2021/7/29
 * @description:
 */
public class LockDemo {

    private int count = 0;

    public void add(){
        while (count < 100) {
            try {
                //打印是否执行该方法
                System.out.println(Thread.currentThread().getName() + " run1: " + count++);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void del(){
        while (count < 100) {
            try {
                //打印是否执行该方法
                System.out.println(Thread.currentThread().getName() + " run1: " + count--);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
