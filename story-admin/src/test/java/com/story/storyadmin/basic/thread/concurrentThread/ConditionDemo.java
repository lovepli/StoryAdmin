package com.story.storyadmin.basic.thread.concurrentThread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: lipan
 * @date: 2019-06-11
 * @description:
 *
 * Condition:
 * Condition可以替代wait / notify
 * Condition对象必须从ReentrantLock对象获取
 * ReentrantLock+Condition可以替代synchronized + wait / notify
 */

class TaskQueue1{

    final Queue<String> queue = new LinkedList<>();

    final Lock lock = new ReentrantLock();
    final Condition notEmpty = lock.newCondition();


    public  String getTask() throws InterruptedException{
        lock.lock();
        try{
            while (this.queue.isEmpty()) {  //注意这里为什么不使用if，因为可能有多个线程在等待
                notEmpty.await();  //线程进入等待状态
            }
            return queue.remove();
        }finally {
            lock.unlock();
        }
    }

    public  void addTask(String name) {
        lock.lock();
        try{
            this.queue.add(name);
            notEmpty.signalAll();  //唤醒正在等待的线程
        }finally {
            lock.unlock();
        }
    }
}

class WorkerThread1 extends Thread{

    TaskQueue1 taskQueue;

    public WorkerThread1(TaskQueue1 taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            String name;
            try {
                name = taskQueue.getTask();
            } catch (InterruptedException e) {
                break;
            }
            String result = "Hello, " + name + "!";
            System.out.println(result);
        }

    }
}
public class ConditionDemo {

    public static void main(String[] args) throws Exception{
        TaskQueue1 taskQueue = new TaskQueue1();
        WorkerThread1 worker = new WorkerThread1(taskQueue);
        worker.start();
        //add.task:
        taskQueue.addTask("Bob");
        Thread.sleep(1000);
        taskQueue.addTask("Alice");
        Thread.sleep(1000);
        taskQueue.addTask("Tom");
        Thread.sleep(1000);
        worker.interrupt();  //中断线程
        worker.join();  //等待线程结束
        System.out.println("END");
    }
}

