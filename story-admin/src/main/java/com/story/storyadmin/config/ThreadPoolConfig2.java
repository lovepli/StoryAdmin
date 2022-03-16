package com.story.storyadmin.config;
import com.story.storyadmin.config.threadpool.UserRejectHandler;
import com.story.storyadmin.config.threadpool.UserThreadFactory;
import com.story.storyadmin.config.threadpool.VisiableThreadPoolTaskExecutor2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: 59688
 * @date: 2021/7/12
 * @description:  自定义线程池配置类，替代springboot默认线程池
 *
 *  默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
 *	当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
 *  当队列满了，就继续创建线程，当线程数量大于等于maxPoolSize后，开始使用拒绝策略拒绝
 */
//@Configuration 暂时不启用这个配置类
public class ThreadPoolConfig2 implements AsyncConfigurer {

    private static final Logger log = LoggerFactory.getLogger(ThreadPoolConfig2.class);

    /**
     * 1、核心线程数（默认线程数）
     */
    @Value("${thread.pool.corePoolSize:15}")
    private int corePoolSize;

    /**
     * 2、最大线程数
     */
    @Value("${thread.pool.maxPoolSize:50}")
    private int maxPoolSize;

    /**
     * 3、缓冲队列大小
     */
    @Value("${thread.pool.queueCapacity:200}")
    private int queueCapacity;

    /**
     * 4、允许线程空闲时间（单位：默认为秒）
     */
    @Value("${thread.pool.keepAliveSeconds:30000}")
    private int keepAliveSeconds;

    /**
     * 线程名前缀
     */
    @Value("${thread.pool.threadNamePrefix:async-service-}")
    private String threadNamePrefix;

    /**
     *  等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
     */
    @Value("${async.executor.await-termination-seconds:60}")
    private int awaitTerminationSeconds;

    /**
     * 等待任务在关机时完成--表明等待所有线程执行完
      */
    @Value("${async.executor.wait-for-tasks-to-complete-on-shutdown:true}")
    private boolean waitForTasksToCompleteOnShutdown;


    //将ThreadPoolTaskExecutor做为一个bean，通过spring的注入，保证只会初始化一次。
    @Bean(name = "taskExecutor") // bean的名称，默认为首字母小写的方法名
    @Override
    public ThreadPoolTaskExecutor getAsyncExecutor() {
        //自定义缓存队列,设置固定长度为20
        //线程池4种工作队列
        //1、ArrayBlockingQueue： 基于数组结构的有界阻塞队列，按FIFO（先进先出）原则对任务进行排序。使用该队列，线程池中能创建的最大线程数为maximumPoolSize。
        //2、LinkedBlockingQueue： 基于链表结构的无界阻塞队列，按FIFO（先进先出）原则对任务进行排序，吞吐量高于ArrayBlockingQueue。使用该队列，线程池中能创建的最大线程数为corePoolSize。静态工厂方法 Executor.newFixedThreadPool()使用了这个队列。
        //3、SynchronousQueue： 一个不存储元素的阻塞队列。添加任务的操作必须等到另一个线程的移除操作，否则添加操作一直处于阻塞状态。静态工厂方法 Executor.newCachedThreadPool()使用了这个队列。
        //4、PriorityBlokingQueue： 一个支持优先级的无界阻塞队列。使用该队列，线程池中能创建的最大线程数为corePoolSize。
        //注意：如果使用的阻塞队列为无界队列，则永远不会调用拒绝策略，因为再多的任务都可以放在队列中。
        BlockingQueue queue= new LinkedBlockingQueue(20);
        //自定义线程工厂
        UserThreadFactory factory = new UserThreadFactory("第一机房");
        //自定义拒绝策略
        // 4种拒绝策略
        //new ThreadPoolExecutor.AbortPolicy(); // 默认，丢弃任务并抛出RejectExecutionExceptio异常
        //new ThreadPoolExecutor.DiscardPolicy();// 丢弃任务，但不抛出异常，这是不推荐的做法
        //new ThreadPoolExecutor.DiscardOldestPolicy(); // 抛弃队列中等待最久的任务，然后把当前任务加入队列中
        //new ThreadPoolExecutor.CallerRunsPolicy(); //调用任务的run方法绕过线程池直接执行 (不在新线程中执行任务，而是由调用者所在的线程来执行。【由调用线程（提交任务的线程）处理该任务】)
        UserRejectHandler handler=new UserRejectHandler();
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor2(
                corePoolSize,
                maxPoolSize,
                keepAliveSeconds,
                TimeUnit.SECONDS,
                queue,
                factory,
                handler);
        // 初始化
        executor.initialize();
        System.out.println("--------------------------》》》开启线程池");
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {

        return new MyAsyncExceptionHandler();
    }


    /**
     * 自定义异常处理类
     * @author hry
     *
     */
    class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

        //手动处理捕获的异常
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
            System.out.println("-------------》》》捕获线程异常信息");
            log.info("Exception message - " + throwable.getMessage());
            log.info("Method name - " + method.getName());
            for (Object param : obj) {
                log.info("Parameter value - " + param);
            }
        }
    }
}



