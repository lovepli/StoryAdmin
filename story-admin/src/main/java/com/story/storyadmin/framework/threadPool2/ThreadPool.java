package com.story.storyadmin.framework.threadPool2;

/**
 * @author: 59688
 * @date: 2021/10/13
 * @description:  手写一个 JAVA 线程池  https://www.cnblogs.com/niuyourou/p/12494982.html
 */
public interface ThreadPool {

    public void excute(Runnable runnable);

    public void shutdown();

    public int getInitSize();

    public int getMaxSize();

    public int getQueSize();

    public int getActiveCount();

    public boolean isShutDown();
}
