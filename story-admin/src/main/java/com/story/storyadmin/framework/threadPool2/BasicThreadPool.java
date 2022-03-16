package com.story.storyadmin.framework.threadPool2;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Nxy
 * @Date 2020/3/14 16:55
 * @Description 线程池
 */
public class BasicThreadPool extends Thread implements ThreadPool {
    //初始线程数
    private final int initSize;
    //最大线程数
    private final int maxSize;
    //最大任务数
    private final int maxTaskSzie;
    //核心线程数
    private final int coreSize;
    //当前活跃线程数
    private int activeCount;
    //任务队列
    private final TaskQueue taskQueue;
    //关闭控制位
    private volatile boolean isShutDown = false;
    //主线程扫描线程池状态时间间隔
    private final long keepAliveTime;
    //睡眠工具类
    private final TimeUnit timeUtil;

    //线程队列
    private final LinkedList<TaskThread> taskPool = new LinkedList<TaskThread>();

    //任务拒绝策略
    private final DenyPolicy denyPolicy;

    //默认参数
    private static final int defaultInitSize = 1;
    private static final int defaultMaxSize = 8;
    private static final int defaultCoreSize = 3;
    private static final DenyPolicy defaultDenyPolicy = new DenyPolicy.throwExceptionDenyPolicy();
    private static final int defaultMaxTaskSize = 1000;
    private static final TimeUnit defaultTimeUtil = TimeUnit.MILLISECONDS;
    private static final int defaultKeepAlive = 1;

    //默认参数构造
    public BasicThreadPool() {
        this(defaultInitSize, defaultMaxSize, defaultCoreSize, defaultKeepAlive, defaultDenyPolicy, defaultMaxTaskSize, defaultTimeUtil);
    }

    //自定义拒绝策略构造
    public BasicThreadPool(DenyPolicy denyPolicy) {
        this(defaultInitSize, defaultMaxSize, defaultCoreSize, defaultKeepAlive, denyPolicy, defaultMaxTaskSize, defaultTimeUtil);
    }

    //自定义参数构造
    public BasicThreadPool(int initSize, int maxSize, int coreSize, long keepAliveTime, DenyPolicy denyPolicy, int maxTaskSzie, TimeUnit timeUtil) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.denyPolicy = denyPolicy;
        this.keepAliveTime = keepAliveTime;
        this.maxTaskSzie = maxTaskSzie;
        this.timeUtil = timeUtil;
        taskQueue = new LinkedTaskQueue(this, maxTaskSzie, denyPolicy);
        this.init();
    }

    /**
     * @Author Nxy
     * @Date 2020/3/14 16:03
     * @Description 静态线程工厂
     */
    static class ThreadFactory {
        private static final AtomicInteger groupCounter = new AtomicInteger(1);
        private static final AtomicInteger counter = new AtomicInteger(0);
        private static final ThreadGroup group = new ThreadGroup("BasicThreadPool group : " + groupCounter.getAndIncrement());

        public static Thread createThread(Runnable runnable) {
            return new Thread(group, runnable, "BasicThreadPool group " + groupCounter.get() + " : " + counter.getAndIncrement());
        }
    }

    /**
     * @Author Nxy
     * @Date 2020/3/14 15:48
     * @Description 线程池内线程，poolTask 与 thread 的结合，poolTask 携带run，thread 携带池中参数
     */
    class TaskThread {
        final PoolTask poolTask;
        final Thread thread;

        TaskThread(PoolTask poolTask, Thread thread) {
            this.thread = thread;
            this.poolTask = poolTask;
        }

        public void shutdown() {
            poolTask.shutdown();
            /**
             *   @Author Nxy
             *   @Date 2020/3/14 22:04
             *   @Description 很重要，仅仅给 poolTask 发送interrupt，所在线程并不会收到信号
             *   线程与 Thread 或 runnable 对象是绑定的，但我们用 runnable 提交任务到 Thread 时，线程绑定的
             *   是 Thread 对象，因此要通过 Thread 对象发送 interrupt 信号
             */
            thread.interrupt();
        }
    }

    /**
     * @Author Nxy
     * @Date 2020/3/14 16:17
     * @Description 新增一个活动线程
     */
    private void addThread() {
        PoolTask poolTask = new PoolTask(taskQueue);
        Thread thread = BasicThreadPool.ThreadFactory.createThread(poolTask);
        TaskThread taskThread = new TaskThread(poolTask, thread);
        synchronized (this) {
            activeCount++;
            taskPool.addLast(taskThread);
        }
        thread.start();
    }

    /**
     * @Author Nxy
     * @Date 2020/3/14 16:35
     * @Description 关闭一个活动线程
     */
    private void removeThread() {
        synchronized (this) {
            TaskThread taskThread = taskPool.removeLast();
            taskThread.shutdown();
            activeCount--;
        }
    }

    /**
     * @Author Nxy
     * @Date 2020/3/14 16:22
     * @Description 不断检查线程数，动态调整活动线程数量
     */
    @Override
    public void run() {
        while (!this.isShutDown && !Thread.currentThread().isInterrupted()) {
            try {
                timeUtil.sleep(10);
            } catch (InterruptedException e) {
                //休眠时间响应 interrupt 信号
                this.isShutDown = true;
                break;
            }
            //有任务且线程数小于核心线程数
            synchronized (this) {
                if (this.isShutDown) {
                    //DCL 检查
                    break;
                }
                //任务数超过当前线程数的两倍，线程池马力全开
                if (taskQueue.getSize() >= activeCount * 2 && activeCount < maxSize) {
                    int beforeActive = activeCount;
                    for (int i = activeCount; i < maxSize; i++) {
                        addThread();
                    }
                    System.out.println("add : actice->" + beforeActive + " ---> " + activeCount);
                    continue;
                }
                //任务数不为0且活动线程数小于核心线程数，新增线程数过核心数线
                if (taskQueue.getSize() > 0 && activeCount < coreSize) {
                    for (int i = activeCount; i < coreSize; i++) {
                        addThread();
                    }
                    System.out.println("add : actice->" + activeCount);
                    continue;
                }
                //无任务且线程数大于核心线程数，关闭线程，仅留下核心线程数的线程
                if (taskQueue.getSize() == 0 && activeCount > coreSize) {
                    for (int i = coreSize; i < activeCount; i++) {
                        removeThread();
                        System.out.println("remove : actice->" + activeCount);
                    }
                }
            }
        }
        System.out.println("******线程池主线程关闭******");
    }

    private void init() {
        this.start();
        for (int i = 0; i < initSize; i++) {
            addThread();
        }
    }

    /**
     * @Author Nxy
     * @Date 2020/3/14 16:53
     * @Description 向线程池中提交新任务
     */
    @Override
    public void excute(Runnable runnable) {
        if (this.isShutDown || Thread.currentThread().isInterrupted()) {
            throw new RuntimeException("threadPool is closed!");
        }
        taskQueue.putTask(runnable);
    }

    /**
     * @Author Nxy
     * @Date 2020/3/14 16:53
     * @Description 关闭线程池
     */
    @Override
    public void shutdown() {
        synchronized (this) {
            this.isShutDown = true;
        }
        System.out.println("正在等待处理任务队列剩余任务");
        while (taskQueue.getSize() != 0) {
            //等待任务队列中的任务全部被处理完
        }
        System.out.println("开始关闭线程池中线程");
        taskPool.forEach(threadTask -> {
                    threadTask.shutdown();
                }
        );
        this.interrupt();
        System.out.println("******线程池中所有任务线程已关闭******");
    }

    @Override
    public int getInitSize() {
        if (this.isShutDown || Thread.currentThread().isInterrupted()) {
            throw new RuntimeException("threadPool is closed!");
        }
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (this.isShutDown || Thread.currentThread().isInterrupted()) {
            throw new RuntimeException("threadPool is closed!");
        }
        return this.maxSize;
    }

    @Override
    public int getQueSize() {
        if (this.isShutDown || Thread.currentThread().isInterrupted()) {
            throw new RuntimeException("threadPool is closed!");
        }
        return this.taskQueue.getSize();
    }

    @Override
    public int getActiveCount() {
        if (this.isShutDown || Thread.currentThread().isInterrupted()) {
            throw new RuntimeException("threadPool is closed!");
        }
        synchronized (this) {
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutDown() {
        if (this.isShutDown || Thread.currentThread().isInterrupted()) {
            throw new RuntimeException("threadPool is closed!");
        }
        synchronized (this) {
            return this.isShutDown;
        }
    }
}