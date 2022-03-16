package com.story.storyadmin.manyThread.RunnableDemo.demo1;

/**
 * @author: 59688
 * @date: 2021/8/4
 * @description:
 * 2：如果每个线程执行的代码不相同，就要用不同的runnable对象了。这种方式又有两种来实现这些runnable对象之间的数据共享。
 *
 * 　　将共享数据封装在另一个对象中，然后将这个对象逐一传递给各个runnable对象中。每个线程共享数据的操作方法也分配到了这个对象身上去完成，这样容易实现针对该数据进行共享数据的互斥和通信。代码实现如下：
 */
public class MulteThreadlShareData2 {
    public static void main(String[] args) {
        final ShareData shareData = new ShareData();
        new Thread(new Decrease(shareData)).start();
        new Thread(new Increment(shareData)).start();
    }

    static class Decrease implements Runnable{
        private ShareData shareData;
        public Decrease(ShareData shareData){
            this.shareData=shareData;
        }
        @Override
        public void run() {
            shareData.decrease();
        }

    }
    static class Increment implements Runnable{
        private ShareData shareData;
        public Increment(ShareData shareData){
            this.shareData=shareData;
        }
        @Override
        public void run() {
            shareData.increment();
        }

    }

    static class ShareData{
        int count = 100;
        public synchronized void decrease(){
            count--;
            System.out.println(Thread.currentThread().getName()+"decrease this count: "+count);
        }
        public synchronized void increment(){
            count++;
            System.out.println(Thread.currentThread().getName()+"increment this count: "+count);
        }
    }
}
