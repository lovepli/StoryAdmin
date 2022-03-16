package com.story.storyadmin.myDatastucture.blockingQuery;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yz
 * @version 1.0
 * @date 2020/10/31 11:24 手写堵塞队列 https://blog.csdn.net/qq_38306425/article/details/109332045
 *
 * 什么是堵塞队列?
 * 当队列为空时，消费者挂起，队列已满时，生产者挂起，这就是生产-消费者模型，堵塞其实就是将线程挂起。
 * 因为生产者的生产速度和消费者的消费速度之间的不匹配，就可以通过堵塞队列让速度快的暂时堵塞,
 * 如生产者每秒生产两个数据，而消费者每秒消费一个数据，当队列已满时，生产者就会堵塞（挂起），等待消费者消费后，再进行唤醒。
 *
 * 堵塞队列会通过挂起的方式来实现生产者和消费者之间的平衡，这是和普通队列最大的区别。
 *
 */
public class MyBlockingQuery { //参照了ArrayBlockingQueue的源码写的

    private Object[] tab; //队列容器

    private int takeIndex; //出队下标

    private int putIndex; //入队下标

    private int size;//元素数量

    private ReentrantLock reentrantLock = new ReentrantLock();

    private Condition notEmpty;//读条件

    private Condition notFull;//写条件

    public MyBlockingQuery(int tabCount) {
        if (tabCount <= 0) {
            new NullPointerException();
        }

        tab = new Object[tabCount];
        notEmpty = reentrantLock.newCondition();
        notFull = reentrantLock.newCondition();
    }

    public boolean offer(Object obj) {
        if (obj == null) { throw new NullPointerException(); }
        try {
            //获取锁
            reentrantLock.lock();
            //队列已满
            while (size==tab.length){
                System.out.println("队列已满");
                //堵塞
                notFull.await();
            }
            tab[putIndex]=obj;
            if(++putIndex==tab.length){
                putIndex=0;
            }
            size++;
            //唤醒读线程
            notEmpty.signal();
            return true;
        } catch (Exception e) {
            //唤醒读线程
            notEmpty.signal();
        } finally {
            reentrantLock.unlock();
        }
        return false;
    }


    public Object take(){
        try {
            reentrantLock.lock();
            while (size==0){
                System.out.println("队列空了");
                //堵塞
                notEmpty.await();
            }
            Object obj= tab[takeIndex];
            //如果到了最后一个，则从头开始
            if(++takeIndex==tab.length){
                takeIndex=0;
            }
            size--;
            //唤醒写线程
            notFull.signal();
            return obj;
        }catch (Exception e){
            //唤醒写线程
            notFull.signal();
        }finally {
            reentrantLock.unlock();
        }
        return null;
    }


    public static void main(String[] args) {
        Random random = new Random(100);
        MyBlockingQuery yzBlockingQuery=new MyBlockingQuery(5);
        Thread thread1 = new Thread(() -> {
            for (int i=0;i<100;i++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                yzBlockingQuery.offer(i);
                System.out.println("生产者生产了："+i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i=0;i<100;i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Object take = yzBlockingQuery.take();
                System.out.println("消费者消费了："+take);
            }
        });

        thread1.start();
        thread2.start();
    }
}

