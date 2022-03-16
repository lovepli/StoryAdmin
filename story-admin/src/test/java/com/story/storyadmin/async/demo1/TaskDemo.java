package com.story.storyadmin.async.demo1;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author: lipan
 * @date: 10:19 下午
 * @description: 定义Task类并添加@Component注入spring，创建三个处理函数分别模拟三个执行任务的操作，操作消耗时间随机取（10秒内）
 */
@Component
public class TaskDemo {

    public static Random random = new Random();

    @Async("taskExecutor")
    public void doTaskOne() throws  InterruptedException {
        System.out.println(Thread.currentThread().getThreadGroup() + "----任务1------" + Thread.currentThread().getName());

        System.out.println("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(100));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
    }

    @Async("taskExecutor")
    public void doTaskTwo() throws InterruptedException{
        System.out.println(Thread.currentThread().getThreadGroup() + "-----任务2-----" + Thread.currentThread().getName());

        System.out.println("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(100));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
    }

    @Async("taskExecutor")
    public void doTaskThree() throws InterruptedException{
        System.out.println(Thread.currentThread().getThreadGroup() + "-----任务3-----" + Thread.currentThread().getName());

        System.out.println("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(100));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
    }

}
