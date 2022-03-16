package com.story.storyadmin.manyThread;

/**
 * @author: lipan
 * @date: 2021年08月27日 3:54 下午
 * @description: Java多线程之Java内存模型 https://www.cnblogs.com/losemyfuture/p/9375336.html
 */
public class Demo1 {

    /**
     * Java多线程之Java内存模型
     * 如果要了解Java内存模型，就得对多线程的三大特性有初步的了解。
     *
     * 1、原子性：独一无二、一个操作或者多个操作 要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。比如i = i+1；其中就包括，读取i的值，计算i，写入i。这行代码在Java中是不具备原子性的，则多线程运行肯定会出问题，所以也需要我们使用同步和lock这些东西来确保这个特性了。原子性其实就是保证数据一致、线程安全。
     *
     * 2、可见性：当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够“立即”看得到修改的值。若两个线程在不同的cpu，那么线程1改变了i的值还没刷新到主存，线程2又使用了i，那么这个i值肯定还是之前的，线程1对变量的修改线程没看到这就是可见性问题。
     *
     * 3、有序性：主要是用于线程间的通讯，比如join、wait等操作。
     *
     * 共享内存模型指的就是Java内存模型(简称JMM)，JMM决定一个线程对共享变量的写入时,能对另一个线程可见。从抽象的角度来看，JMM定义了线程和主内存之间的抽象关系：线程之间的共享变量存储在主内存（main memory）中，每个线程都有一个私有的本地内存（local memory），本地内存中存储了该线程以读/写共享变量的副本。本地内存是JMM的一个抽象概念，并不真实存在。它涵盖了缓存，写缓冲区，寄存器以及其他的硬件和编译器优化。
     *
     *
     *
     * 从上图来看，线程A与线程B之间如要通信的话，必须要经历下面2个步骤：
     *
     * 1. 首先，线程A把本地内存A中更新过的共享变量刷新到主内存中去。
     *
     * 2. 然后，线程B到主内存中去读取线程A之前已更新过的共享变量。
     *
     * 从整体来看，这两个步骤实质上是线程A在向线程B发送消息，而且这个通信过程必须要经过主内存。JMM通过控制主内存与每个线程的本地内存之间的交互，来为java程序员提供内存可见性保证。
     *
     * 总结：什么是Java内存模型：java内存模型简称jmm，定义了一个线程对另一个线程可见。共享变量存放在主内存中，每个线程都有自己的本地内存，当多个线程同时访问一个数据的时候，可能本地内存没有及时刷新到主内存，所以就会发生线程安全问题。
     *
     * 注意：Java内存模型jmm和Java内存结构jvm不是一个概念，切记切记
     *
     *
     *
     *
     * java中JVM和JMM之间的区别:参考 https://blog.csdn.net/zhaomengszu/article/details/80270696
     */
}