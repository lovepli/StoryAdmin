package com.story.storyadmin.config.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: lipan
 * @date: 2021年08月31日 7:32 下午
 * @description: 测试线程池的拒绝策略,来自码出高效例子
 */
public class RejectedTest {

    public static void main(String[] args) {
        // 缓存队列设置固定长度为2，为了快速触发rejectHandler
        BlockingQueue queue=new LinkedBlockingQueue(2);

        //假设外部任务线程的来源由机房1和机房2的混合使用
        UserThreadFactory factory1=new UserThreadFactory("第一机房");
        UserThreadFactory factory2=new UserThreadFactory("第二机房");

        UserRejectHandler rejectHandler=new UserRejectHandler();


        // 核心线程为1，最大线程为2，为了保证触发rejectHandler
        ThreadPoolExecutor threadPoolFirst= new ThreadPoolExecutor(
          1,2,60, TimeUnit.SECONDS,queue,factory1,rejectHandler
        );
        // 利用第二个线程工厂实例创建第二个线程池
        ThreadPoolExecutor threadPoolSecond= new ThreadPoolExecutor(
                1,2,60, TimeUnit.SECONDS,queue,factory2,rejectHandler
        );

        // 创建400个线程
        Runnable task = new Task();
        for(int i=0;i<200;i++){
            threadPoolFirst.execute(task);
            threadPoolSecond.execute(task);
        }


    }

}

// 任务执行体
class Task implements Runnable{
     private final AtomicLong count =new AtomicLong(0L);

    @Override
    public void run() {
        System.out.println("running_"+count.getAndIncrement());
    }
}
