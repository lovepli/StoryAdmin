package com.story.storyadmin.config;

import com.story.storyadmin.config.threadpool.VisiableThreadPoolTaskExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: 59688
 * @date: 2021/7/12
 * @description:  自定义线程池配置类，替代springboot默认线程池
 *
 *  默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
 *	当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
 *  当队列满了，就继续创建线程，当线程数量大于等于maxPoolSize后，开始使用拒绝策略拒绝
 */
@Configuration
//@EnableAsync //需要在Spring启动类上添加注解@EnableAsyn或者在你们线程池配置类添加@EnableAsyn
public class ThreadPoolConfig implements AsyncConfigurer {

    private static final Logger log = LoggerFactory.getLogger(ThreadPoolConfig.class);

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
        //ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //在这里修改,实现对线程池的监控，方便查看
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(corePoolSize);
        //配置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        //配置缓冲队列大小
        executor.setQueueCapacity(queueCapacity);
        //空闲线程存活时间
        executor.setKeepAliveSeconds(keepAliveSeconds);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(threadNamePrefix);
        // rejection-policy线程池对拒绝任务的处理策略:pool已经达到max size的时候，如何处理新任务?
        // 4种拒绝策略
        //new ThreadPoolExecutor.AbortPolicy(); // 默认，丢弃任务并抛出RejectExecutionExceptio异常
        //new ThreadPoolExecutor.DiscardPolicy();// 丢弃任务，但不抛出异常，这是不推荐的做法
        //new ThreadPoolExecutor.DiscardOldestPolicy(); // 抛弃队列中等待最久的任务，然后把当前任务加入队列中
        new ThreadPoolExecutor.CallerRunsPolicy(); //调用任务的run方法绕过线程池直接执行 (不在新线程中执行任务，而是由调用者所在的线程来执行。【由调用线程（提交任务的线程）处理该任务】)

        // 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
        //executor.setAwaitTerminationSeconds(awaitTerminationSeconds);

        //等待任务在关机时完成--表明等待所有线程执行完
        //executor.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);

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


/**
 * 五种线程池，四种拒绝策略，三种阻塞队列  https://mp.weixin.qq.com/s/FYCwsig9fJAJaM8t8AaWWA
 *
 * 一、五种线程池：
 * ExecutorService threadPool = null;
 *  //1、有缓冲的线程池，线程数 JVM 控制
 * threadPool = Executors.newCachedThreadPool();
 * //2、固定大小的线程池
 * threadPool = Executors.newFixedThreadPool(3);
 * //3、定时任务线程池
 * threadPool = Executors.newScheduledThreadPool(2);
 * //4、单线程的线程池，只有一个线程在工作
 * threadPool = Executors.newSingleThreadExecutor();
 * //5、默认线程池，可控制参数比较多
 * threadPool = new ThreadPoolExecutor();
 *
 *
 * 二、四种拒绝策略：
 * RejectedExecutionHandler rejected = null;
 * rejected = new ThreadPoolExecutor.AbortPolicy();
 * //默认，队列满了丢任务抛出异常
 * rejected = new ThreadPoolExecutor.DiscardPolicy();
 * //队列满了丢任务不抛异常
 * rejected = new ThreadPoolExecutor.DiscardOldestPolicy();
 * //将最早进入队列的任务删，之后再尝试加入队列
 * rejected = new ThreadPoolExecutor.CallerRunsPolicy();
 * //如果添加到线程池失败，那么主线程会自己去执行该任务；如果执行程序已关闭（主线程运行结束），则会丢弃该任务
 *
 * 三、三种阻塞队列：
 * BlockingQueue<Runnable> workQueue = null;
 * workQueue = new ArrayBlockingQueue<>(5);
 * //基于数组的先进先出队列，有界
 * workQueue = new LinkedBlockingQueue<>();
 * //基于链表的先进先出队列，无界
 * workQueue = new SynchronousQueue<>();
 * //无缓冲的等待队列，无界
 *
 *
 * 线程池的工作原理：
 *
 * ThreadPoolExecutor(int corePoolSize,// 核心线程数
 *                    int maximumPoolSize,//最大线程数
 *                    long keepAliveTime,//空闲线程存活时间
 *                    TimeUnit unit,//存活时间单位
 *                    BlockingQueue<Runnable> workQueue,//阻塞队列
 *                    RejectedExecutionHandler handler)//拒绝策略
 *
 *  当ThreadPoolExecutor线程池被创建的时候，里边是没有工作线程的，直到有任务进来（执行了execute方法）才开始创建线程去工作，工作原理如下（即execute方法运行原理）：
 * 调用线程池的execute方法的时候如果当前的工作线程数 小于 核心线程数，则创建新的线程执行任务；
 * 否则将任务加入阻塞队列。如果队列满了则根据最大线程数去创建额外（核心线程数以外）的工作线程去执行任务；
 * 如果工作线程数达到了最大线程数，则根据拒绝策略去执行。存活时间到期的话只是回收核心线程（maximumPoolSize - corePoolSize）以外的线程
 *
 * // 分3个步骤进行:
 * // 1. 如果运行的线程少于corePoolSize，请尝试使用给定的命令作为第一个线程启动一个新线程的任务。对addWorker的调用会自动检查runState和workerCount，这样可以防止虚假警报的增加当它不应该的时候，返回false。
 * // 2. 如果任务可以成功排队，那么我们仍然需要来再次检查我们是否应该添加线程(因为自从上次检查后，现有的已经死了)或者那样自进入此方法后池就关闭了。所以我们重新检查状态，并在必要时回滚队列停止，或启动一个新线程(如果没有线程)。
 * // 3.如果我们不能将任务放入队列，那么我们尝试添加一个新的线程。如果它失败了，我们知道我们被关闭或饱和了所以拒绝这个任务。
 *
 */
