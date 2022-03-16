package com.story.storyadmin.framework.threadPool2;


import java.util.LinkedList;

public class LinkedTaskQueue implements TaskQueue {
    //队列最大长度
    private final int maxSize;
    //任务达到最大数后的拒绝策略
    private final DenyPolicy denyPolicy;
    //任务队列，链表实现
    private final LinkedList<Runnable> queue = new LinkedList<Runnable>();

    private final ThreadPool threadPool;

    LinkedTaskQueue(ThreadPool threadPool, int maxSize, DenyPolicy denyPolicy) {
        this.maxSize = maxSize;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    /**
     * @Author Nxy
     * @Date 2020/3/14 21:07
     * @Description 以下互斥区域使用 threadPool 对象锁，因为在 threadPool 中存在对这些方法的调用，
     * 在调用情况复杂，不好判断调用次序的情况下（并且存在持有等待、不可抢占的特性）用两把锁容易造成死锁
     */
    @Override
    public void putTask(Runnable runnable) {
        synchronized (threadPool) {
            //超出最大任务数或者线程池已关闭，采取拒绝策略
            if (getSize() >= maxSize || threadPool.isShutDown()) {
                denyPolicy.reject(runnable, threadPool);
                return;
            }
            //任务追加到任务队列结尾
            queue.addLast(runnable);
            //唤醒等待在任务队列的工作线程
            threadPool.notify();
        }
    }

    @Override
    public Runnable getTask() throws InterruptedException {
        Runnable returnRunnable;
        synchronized (threadPool) {
            if (queue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " 等待在缓冲区");
                threadPool.wait();
            }
            //先进先出队列
            returnRunnable = queue.removeFirst();
        }
        return returnRunnable;
    }

    @Override
    public int getSize() {
        synchronized (queue) {
            return queue.size();
        }
    }
}