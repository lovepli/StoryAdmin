package com.story.storyadmin.设计模式.单例模式.demo;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 懒汉式
 * 顾名思义，就是需要的时候再创建，因为懒，你不调用我方法，我是不会干活的。
 *
 * 进入getInstance方法，先判断lazySingleton是否为空，为空，则创建一个对象，然后返回此对象。
 *
 * 但是，问题来了：
 *
 * 两个线程同时进入getInstance方法，然后都去执行01这行代码，都是true，然后各自进去创建一个对象，然后返回自己创建的对象。
 *
 * 这岂不是不满足只有唯一 一个对象的了吗？所以这类存在线程安全的问题，那怎么解决呢？
 *
 * 第一印象肯定都是想到加锁。于是，就有了下面的线程安全的懒加载版本：
 */
public class LazySingleton {

    private static LazySingleton lazySingleton = null;

    private LazySingleton() {
    }

    //调用方法的时候才new，这种方式需要考虑线程安全问题
    public static LazySingleton getInstance() {
        if (lazySingleton == null) {//01
            lazySingleton = new LazySingleton();//02
        }
        return lazySingleton;
    }
}
