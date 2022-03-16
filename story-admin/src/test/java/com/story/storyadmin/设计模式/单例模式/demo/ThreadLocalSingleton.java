package com.story.storyadmin.设计模式.单例模式.demo;

/**
 * @author: lipan
 * @date: 2021年08月26日 12:13 下午
 * @description: 本地线程实现，利用ThreadLocal线程隔离的特性实现（线程安全）。
 * ThreadLocal 会为每一个线程提供一个独立的变量副本，从而隔离了多个线程对数据的访问冲突。对于多线程资源共享的问题，同步机制采用了 “以时间换空间” 的方式，而 ThreadLocal 采用了 “以空间换时间” 的方式。前者仅提供一份变量，让不同的线程排队访问，而后者为每一个线程都提供了一份变量，因此可以同时访问而互不影响。
 */
public class ThreadLocalSingleton {
    private static final ThreadLocal<ThreadLocalSingleton> threadLocalInstance = ThreadLocal.withInitial(() -> new ThreadLocalSingleton());

    //    private static final ThreadLocal<Singleton> tlSingleton =
    //            new ThreadLocal<Singleton>() {
    //                @Override
    //                protected Singleton initialValue() {
    //                    return new Singleton();
    //                }
    //            };

    private ThreadLocalSingleton(){}

    public static ThreadLocalSingleton getInstance(){
        return threadLocalInstance.get();
    }

    public static void main(String[] args){
        ThreadLocalSingleton instance1 = ThreadLocalSingleton.getInstance();
        ThreadLocalSingleton instance2 = ThreadLocalSingleton.getInstance();

        System.out.println(instance1 == instance2);

    }

}
