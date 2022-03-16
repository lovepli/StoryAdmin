package com.story.storyadmin.basic.thread.concurrentThread;

import java.util.concurrent.*;

/**
 * @author: lipan
 * @date: 2019-06-11
 * @description:
 *
 *
ExecutorService
JDK提供了ExecutorService实现了线程池功能
线程池内部维护一组线程，可以高效执行大量小任务
Executors提供了静态方法创建不同类型的ExecutorService

常用ExecutorService:
FixedThreadPool:线程数固定
CachedThreadPool:线程数根据任务动态调整
SingleThreadExecutor:仅单线程执行
必须调用shutdown()关闭ExecutorService

ScheduledThreadPool可以定期调度多个任务(可取代Timer)
 */

class PrintTask implements Runnable{
    String name;

    public PrintTask(String name) {
        this.name = name;
    }
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Hello, " + name + "!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }
}
public class ExecutorServiceDemo {
    public static void main(String[] args) throws Exception{
       // ExecutorService executor = Executors.newFixedThreadPool(3);  //创建newFixedThreadPool线程池 线程数量为3
       // ExecutorService executor = Executors.newSingleThreadExecutor();
       // ExecutorService executor = Executors.newCachedThreadPool(); //会根据我们的任务动态的调整线程的数量，向ExecutorService中提交了四个任务，所有这四个任务提交上去后，线程池会立刻创建4个线程池来执行他
        ExecutorService executor = new ThreadPoolExecutor(0, 10,    //设置线程池的大小为10
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        executor.submit(new PrintTask("Bob"));
        executor.submit(new PrintTask("Alice"));
        executor.submit(new PrintTask("Tom"));
        executor.submit(new PrintTask("Robot"));
        Thread.sleep(1000);  //主线程休息
        executor.shutdown();  //结束线程
    }

    //ExecutorService executor = Executors.newFixedThreadPool(3);
//前面三个任务都按顺序执行了，而第四个任务是只有线程有空闲的时候才执行
//    Hello, Bob!
//    Hello, Alice!
//    Hello, Tom!
//    Hello, Bob!
//    Hello, Tom!
//    Hello, Alice!
//    Hello, Bob!
//    Hello, Tom!
//    Hello, Alice!
//    Hello, Robot!
//    Hello, Robot!
//    Hello, Robot!

    // ExecutorService executor = Executors.newSingleThreadExecutor();
    //所有线程都是以串行方式在执行的，因为线程池只有一个线程
//    Hello, Bob!
//    Hello, Bob!
//    Hello, Bob!
//    Hello, Alice!
//    Hello, Alice!
//    Hello, Alice!
//    Hello, Tom!
//    Hello, Tom!
//    Hello, Tom!
//    Hello, Robot!
//    Hello, Robot!
//    Hello, Robot!

    //ExecutorService executor = Executors.newCachedThreadPool();  //CachedThreadPool的底层实现其实就是ThreadPoolExecutor
//    Hello, Bob!
//    Hello, Tom!
//    Hello, Alice!
//    Hello, Robot!
//    Hello, Alice!
//    Hello, Robot!
//    Hello, Tom!
//    Hello, Bob!
//    Hello, Robot!
//    Hello, Bob!
//    Hello, Alice!
//    Hello, Tom!

    //ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());




}
