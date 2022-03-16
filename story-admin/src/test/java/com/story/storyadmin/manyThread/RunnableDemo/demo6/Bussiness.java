package com.story.storyadmin.manyThread.RunnableDemo.demo6;

/**
 * @author: lipan
 * @date: 11:12 下午
 * @description: 要求： 子线程运行执行 10 次后，主线程再运行 5 次。这样交替执行三遍
 */
public class Bussiness {

    private boolean subFlag = true;


    /**
     * 同步方法
     * 2、在 java 中 wait 和 sleep 方法的不同？
     * 最大的不同是在等待时 wait 会释放锁，而 sleep 一直持有锁。 wait 通常被用于线程间交互， sleep 通常被用于暂停执行。
     */
    public synchronized void mainMethod() {
        System.out.println("----mainMethod线程------" + Thread.currentThread().getName());
        while (subFlag) {
            try {
                wait();  //为true 的时候当前main线程释放锁资源，进入锁池 ，子线程获得了锁资源，程序不再继续执行下面的程序了，子线程开始执行他的操作了
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " : main thread running loop count -- " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        subFlag = true;
        notify();
    }

    //同步方法
    public synchronized void subMethod() {
        System.out.println( "----subMethod线程------" + Thread.currentThread().getName());
        while (!subFlag) {
            try {
                wait();  //为true 的时候当前子线程释放锁资源，进入锁池 ，主线程获得了锁资源，程序不再继续执行下面的程序了，主线程开始执行他的操作了
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //子线程执行操作
        for (int i = 0; i < 10; i++) {
            System.err.println(Thread.currentThread().getName()
                    + " : sub thread running loop count -- " + i);
            try {
                Thread.sleep(1000);  //线程休眠1秒，但是不释放锁资源
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        subFlag = false;
        notify();  //唤醒等待的线程main主线程
    }
}
