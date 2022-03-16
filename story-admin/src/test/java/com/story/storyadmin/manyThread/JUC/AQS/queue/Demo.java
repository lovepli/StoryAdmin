package com.story.storyadmin.manyThread.JUC.AQS.queue;

import java.util.concurrent.*;

/**
 * @author: 59688
 * @date: 2021/10/13
 * @description: 线程池的三种队列区别：SynchronousQueue、LinkedBlockingQueue 和ArrayBlockingQueue
 */
public class Demo {

    //使用方法：
    //
    //1.SynchronousQueue
    //SynchronousQueue没有容量，是无缓冲等待队列，是一个不存储元素的阻塞队列，会直接将任务交给消费者，必须等队列中的添加元素被消费后才能继续添加新的元素。
    //拥有公平（FIFO）和非公平(LIFO)策略，非公平侧罗会导致一些数据永远无法被消费的情况？
    //使用SynchronousQueue阻塞队列一般要求maximumPoolSizes为无界(Integer.MAX_VALUE)，避免线程拒绝执行操作。
    private static ExecutorService cachedThreadPool = new ThreadPoolExecutor(4, Runtime.getRuntime().availableProcessors() * 2, 0, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), r -> new Thread(r, "ThreadTest"));


    //2.LinkedBlockingQueue
    //LinkedBlockingQueue是一个无界缓存等待队列。当前执行的线程数量达到corePoolSize的数量时，剩余的元素会在阻塞队列里等待。（所以在使用此阻塞队列时maximumPoolSizes就相当于无效了），每个线程完全独立于其他线程。生产者和消费者使用独立的锁来控制数据的同步，即在高并发的情况下可以并行操作队列中的数据。
    //注：这个队列需要注意的是，虽然通常称其为一个无界队列，但是可以人为指定队列大小，而且由于其用于记录队列大小的参数是int类型字段，所以通常意义上的无界其实就是队列长度为 Integer.MAX_VALUE，且在不指定队列大小的情况下也会默认队列大小为 Integer.MAX_VALUE，等同于如下：
   // private static ExecutorService cachedThreadPool = new ThreadPoolExecutor(4, Runtime.getRuntime().availableProcessors() * 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(Integer.MAX_VALUE), r -> new Thread(r, "ThreadTest"));
    private static ExecutorService cachedThreadPool2 = new ThreadPoolExecutor(4, Runtime.getRuntime().availableProcessors() * 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), r -> new Thread(r, "ThreadTest"));


    //3.ArrayBlockingQueue
    //ArrayBlockingQueue是一个有界缓存等待队列，可以指定缓存队列的大小，当正在执行的线程数等于corePoolSize时，多余的元素缓存在ArrayBlockingQueue队列中等待有空闲的线程时继续执行，当ArrayBlockingQueue已满时，加入ArrayBlockingQueue失败，会开启新的线程去执行，当线程数已经达到最大的maximumPoolSizes时，再有新的元素尝试加入ArrayBlockingQueue时会执行拒绝策略。
    private static ExecutorService cachedThreadPool3 = new ThreadPoolExecutor(4, Runtime.getRuntime().availableProcessors() * 2, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(32), r -> new Thread(r, "ThreadTest"));
}
