package com.story.storyadmin.manyThread.RunnableDemo.demo1;

/**
 * @author: 59688
 * @date: 2021/8/4
 * @description: 多线程访问共享对象和数据的方式
 * 在多线程访问共享对象和数据时候大致可以分为两大类。
 *
 * 1：如果每个线程执行的代码相同，可以使用同一个runnable对象，这个runnable对象中有那个共享对象。如：买票系统。
 */
public class MulteThreadlShareData {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(shareData).start();
        new Thread(shareData).start();
    }

    static class ShareData implements Runnable{
        int count = 100;
        @Override
        public void run() {
            while(count>0){
                decrease();
            }
        }
        public synchronized void decrease(){
            count--;
            System.out.println(Thread.currentThread().getName()+"this count: "+count);
        }

    }
}