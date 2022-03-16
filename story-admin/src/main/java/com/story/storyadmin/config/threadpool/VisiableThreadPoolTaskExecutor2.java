package com.story.storyadmin.config.threadpool;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.*;

/**
 * @author: 59688
 * @date: 2021/7/21
 * @description: 虽然我们已经用上了线程池，但是还不清楚线程池当时的情况，有多少线程在执行，多少在队列中等待呢？
 * 这里我创建了一个ThreadPoolTaskExecutor的子类，在每次提交线程的时候都会将当前线程池的运行状况打印出来
 *
 * showThreadPoolInfo方法中将任务总数、已完成数、活跃线程数，队列大小都打印出来了，然后Override了父类的execute、submit等方法，
 * 在里面调用showThreadPoolInfo方法，这样每次有任务被提交到线程池的时候，都会将当前线程池的基本情况打印到日志中；
 */
@Data
public class VisiableThreadPoolTaskExecutor2 extends ThreadPoolTaskExecutor {

    private static final Logger logger = LoggerFactory.getLogger(VisiableThreadPoolTaskExecutor2.class);

    public VisiableThreadPoolTaskExecutor2(){}

    // 线程池7个核心参数
    private  int corePoolSize; //线程池的基本大小
    private int maximumPoolSize;//线程池的最大数量
    private long keepAliveTime; //线程活动保持时间
    private TimeUnit unit;//线程活动保持时间的单位，可选单位有DAYS、HOURS、MINUTES、毫秒、微秒、纳秒。
    private BlockingQueue<Runnable> workQueue;//工作队列
    private ThreadFactory threadFactory; //构建线程的工厂类
    private RejectedExecutionHandler handler;//饱和策略，或者又称拒绝策略

    public VisiableThreadPoolTaskExecutor2(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.workQueue = workQueue;
        this.threadFactory = threadFactory;
        this.handler = handler;
    }


    /**
     * 获取当前线程和线程池基本信息并打印出来
     * @param prefix
     */
    private void showThreadPoolInfo(String prefix) {
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();

        if (null == threadPoolExecutor) {
            return;
        }

        logger.info("{}, {},总线程数:[{}],当前线程的数量:[{}],核心线程数量:[{}], 允许最大的线程数:[{}],执行完成线程数:[{}], 当前活动线程数:[{}], 当前排队线程数:[{}]",
                //当前已经提交了几个任务，完成了几个，当前有几个线程在处理任务，还剩几个任务在队列中等待，
                this.getThreadNamePrefix(),
                prefix,
                threadPoolExecutor.getTaskCount(),
                threadPoolExecutor.getPoolSize(),
                threadPoolExecutor.getCorePoolSize(),
                threadPoolExecutor.getMaximumPoolSize(),
                threadPoolExecutor.getCompletedTaskCount(),
                threadPoolExecutor.getActiveCount(),
                threadPoolExecutor.getQueue().size());
    }

    /**
     * https://blog.csdn.net/w_t_y_y/article/details/102817576
     * 线程池提交任务有两种方法：
     * 无返回值的任务使用public void execute(Runnable command) 方法提交；
     * 有返回值的任务使用public <T> Future<T> submit(Callable) 方法提交。
     *
     * 下两者的应用以及区别。
     * 一、与主线程执行顺序的区别：
     * （1）public void execute(Runnable command) 方法提交,子线程可能在主线程结束之后结束；
     * （2）public <T> Future<T> submit(Callable) 方法提交，因为提交任务后有个取数据的过程，在从Future取数据的过程中，
     * Callable自带的阻塞机制，这个机制保证主线程一定在子线程结束之后结束。反之如果没有取数据，子线程可能会在主线程结束之后才结束。
     * @param task
     */
    @Override
    public void execute(Runnable task) {
        showThreadPoolInfo("1.执行execute");
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        showThreadPoolInfo("2.执行execute");
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        showThreadPoolInfo("1.执行submit");
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        showThreadPoolInfo("2.执行submit");
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        showThreadPoolInfo("1.执行submitListenable");
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        showThreadPoolInfo("2.执行submitListenable");
        return super.submitListenable(task);
    }
}