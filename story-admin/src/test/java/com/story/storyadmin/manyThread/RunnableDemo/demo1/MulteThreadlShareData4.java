package com.story.storyadmin.manyThread.RunnableDemo.demo1;

/**
 * @author: 59688
 * @date: 2021/8/4
 * @description: 4、上面两种方式的结合：将共享数据封装到另一个对象中，各个线程对共享数据操作的方法也分配到那个对象上去完成，对象作为外部类的成员变量或方法的局部变量，每个runnable对象作为外部类中的成员内部类或局部内部类。
 * 总之：要同步和互斥的几段代码最好放在几个独立的方法中，这些方法在放在同一个类中，这样容易实现他们之间的同步互斥和通信。
 */
public class MulteThreadlShareData4 {
    public static void main(String[] args) {
        final ShareData shareData = new ShareData();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    shareData.decrease();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    shareData.increment();
                }

            }
        }).start();
    }

    static class ShareData{
        int count = 100;
        public synchronized void decrease(){
            count--;
            System.out.println(Thread.currentThread().getName()+"this count: "+count);
        }
        public synchronized void increment(){
            count++;
            System.out.println(Thread.currentThread().getName()+"this count: "+count);
        }
    }
}