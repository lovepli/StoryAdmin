package com.story.storyadmin.manyThread.volatileDemo8.demo;

/**
 * @author: lipan
 * @date: 2021年08月27日 11:11 上午
 * @description: Java进阶：volatile使用详解 https://blog.csdn.net/qq_28834355/article/details/108623535
 */
public class Demo {

    /**
     * 概述
     * volatile是Java提供的轻量级的同步机制，保证了可见性，不保证原子性。
     * 了解volatile工作机制，首先要对Java内存模型（JMM）有初步的认识：
     *
     * 每个线程创建时，JVM会为其创建一份私有的工作内存（栈空间），不同线程的工作内存之间不能直接互相访问
     * JMM规定所有的变量都存在主内存，主内存是共享内存区域，所有线程都可以访问
     * 线程对变量进行读写，会从主内存拷贝一份副本到自己的工作内存，操作完毕后刷新到主内存。所以，线程间的通信要通过主内存来实现。
     * volatile的作用是：线程对副本变量进行修改后，其他线程能够立刻同步刷新最新的数值。这个就是可见性。
     *
     *
     * */
}
