package com.story.storyadmin.basic.thread.concurrentThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author: lipan
 * @date: 2019-06-11
 * @description:
 *
 *  Blocking
 *
 *  补充：
 *  java.util.Collections工具还提供了旧的线程安全转换器:
 *      Map unsafeMap = new HashMap<>();
 *     Map threadSafe = Collections.synchronizedMap(unsafeMap);
 *     实际上是使用了一个包装类，对所有的方法都使用了synchronized 但是性能很低，比推荐使用
 *
 * Concurrent集合
 * 使用java.util.concurrent提供的Blocking集合可以简化多线程编程:
 * ArrayList(线程不安全) --> CopyOnWriteArrayList(线程安全)
 * HashMap(线程不安全) --> ConcurrentHashMap(线程安全)
 * HashSet/TreeSet --> CopyOnWriteArraySet
 * ArrayDeque/LinkedList --> ArrayBlockingQueue LinkedBlockingQueue
 * ArrayDeque/LinkedList --> LinkedBlockingDeque
 *
 * 多线程同时访问Blocking集合是安全的
 * 尽量使用JDK提供的concurrent集合，避免自己编写同步代码
 */


class WorkThread extends Thread{

    BlockingQueue<String> taskQueue;

    public WorkThread(BlockingQueue<String> taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void run() {
        while (!isInterrupted()) {
            String name;
            try {
                name = taskQueue.take();
            } catch (InterruptedException e) {
                break;
            }
            String result = "Hello, " + name + "!";
            System.out.println(result);
        }
    }
}
public class ConcurrentDemo {
    public static void main(String[] args) throws Exception{
        BlockingQueue<String> taskQueue = new ArrayBlockingQueue<>(100);  // ArrayBlockingQueue<>()
        WorkThread worker = new WorkThread(taskQueue);
        worker.start();
        //add.task:
        taskQueue.put("Bob");  //put方法
        Thread.sleep(1000);
        taskQueue.put("Alice");
        Thread.sleep(1000);
        taskQueue.put("Tom");
        Thread.sleep(1000);
        worker.interrupt();  //中断线程
        worker.join();  //等待线程结束
        System.out.println("END");
    }
}
