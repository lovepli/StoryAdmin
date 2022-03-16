package com.story.storyadmin.myDatastucture.threadLocal;

import java.util.HashMap;
import java.util.Map;
/**
 * @author: 59688
 * @date: 2021/9/26
 * @description: https://blog.csdn.net/weixin_38380858/article/details/107118902
 */
public class MyThreadLocal3<T> {

    private Map<Thread, Map<MyThreadLocal3, Object>> mapMap;
    //private Map<Thread,ThreadLocal.ThreadLocalMap> mapMap2;
    private T initValue;

    public MyThreadLocal3() {
        this.mapMap = new HashMap<>();
    }

    public MyThreadLocal3(T initValue) {
        this.mapMap = new HashMap<>();
        this.initValue = initValue;
    }

    public void set(T value) {
        Thread thread = Thread.currentThread();
        Map<MyThreadLocal3, Object> currentThreadMap = mapMap.get(thread);
        if (currentThreadMap == null) {
            currentThreadMap = new HashMap<>();
            mapMap.put(thread, currentThreadMap);
        }
        currentThreadMap.put(this, value);
    }

    public T get() {
        Thread thread = Thread.currentThread();
        Map<MyThreadLocal3, Object> currentThreadMap = mapMap.get(thread);
        if (currentThreadMap == null) {
            return initValue;
        }
        return (T) currentThreadMap.get(this);
    }

    public void remove() {
        Thread thread = Thread.currentThread();
        Map<MyThreadLocal3, Object> currentThreadMap = mapMap.get(thread);
        if (currentThreadMap != null) {
            currentThreadMap.remove(this);
        }
    }

    /**
     * set，get 的基本功能有了，还可以通过带初始值的构造方法来设置初始值，可以当做 JDK ThreadLocal 一样来用。
     *
     * 基本原理与 JDK ThreadLocal 一致。
     *
     * 如果把里面的那层 Map 替换成 ThreadLocal.ThreadLocalMap，即变成 Map<Thread, ThreadLocalMap>；
     *
     * 再把 ThreadLocalMap 放到 Thread 对象里面去，而不是全都放在一个 ThreadLocal 对象里面以 Thread 做映射，就变成 JDK ThreadLocal 了。



     * ThreadLocal： 存放每个线程单独的局部变量
     * jdk原理：每个Thread对象中，都有一个成员变量ThreadLocalMap，用来存放ThreadLocal存放的值。ThreadLocal是作为Map中的键。
     *
     * 应用场合：解决数据库连接，或Session管理等
     * ThreadLocal主要用于解决静态变量,且每个线程私有化变量值
     *
     * 使用ThreadLocal的数据库连接建立优化:
     * 1)一个数据库操作线程类可能被多个线程同时代理
     * 2)线程类中获取连接的方法,如果使用static方法,多线程同时获取一个连接操作不方便,容易出错.
     * 3)若不使用static,则每次使用都需要创建对象,执行不同的操作都需要创建对象,每个线程都会创建多个连接对象,增加服务器压力.
     * 4)使用static修饰的ThreadLocal,使方法成为静态方法.但每个线程的连接都是保存在ThreadLocal中,互不干扰,一个线程只需要建立一个连接.
     */
}

