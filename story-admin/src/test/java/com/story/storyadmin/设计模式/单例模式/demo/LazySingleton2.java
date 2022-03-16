package com.story.storyadmin.设计模式.单例模式.demo;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 线程安全的懒加载版本
 *
 * 给getInstance方法加锁同步锁标志synchronized，但是又涉及到锁的问题了，同步锁是对系统性能优影响的，尽管JDK1.6后，对其做了优化，但它毕竟还是涉及到锁的开销。
 *
 * 每个线程调用getInstance方法时候，都会涉及到锁，所以又对此进行了优化成为了大家耳熟能详的双重检查锁。
 */
public class LazySingleton2 {

    private static LazySingleton2 lazySingleton = null;

    private LazySingleton2() {
    }

    //简单粗暴的线程安全问题解决方案
    //依然存在性能问题
    public synchronized static LazySingleton2 getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton2();
        }
        return lazySingleton;
    }
}
