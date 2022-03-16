package com.story.storyadmin.basic.thread.demo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: lipan
 * @date: 2019-06-10
 * @description:
 * wait / notify
 * wait / notify用于多线程协调运行:
 * 在synchronized内部可以调用wait()使线程进入等待状态
 * 必须在已获得的锁对象上调用wait()方法
 * 在synchronized内部可以调用notify()/notifyAll()唤醒其他等待线程
 * 必须在已获得的锁对象上调用notify()/notifyAll()方法
 *
 *案例：
 * 能够实现后台程序在有任务的时候，顺序下载多个图片的功能
 */

class TaskQueue{

    final Queue<String> queue = new LinkedList<>();

    public synchronized String getTask() throws InterruptedException{
        while (this.queue.isEmpty()) {  //注意这里为什么不使用if，因为可能有多个线程在等待
            this.wait();
        }
        return queue.remove();
    }

    public synchronized void addTask(String name) {
        this.queue.add(name);
        this.notifyAll();
    }
}

class WorkerThread extends Thread{

    TaskQueue taskQueue;

    public WorkerThread(TaskQueue taskQueue) {
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

public class Demo4 {

    public static void main(String[] args) throws Exception{
        TaskQueue taskQueue = new TaskQueue();
        WorkerThread worker = new WorkerThread(taskQueue);
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
