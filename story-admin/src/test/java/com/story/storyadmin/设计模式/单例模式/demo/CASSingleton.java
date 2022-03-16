package com.story.storyadmin.设计模式.单例模式.demo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: lipan
 * @date: 2021年08月26日 12:16 下午
 * @description: 利用CAS实现
 */
public class CASSingleton {

    private CASSingleton() {
    }

    /** 利用AtomicReference */
    private static AtomicReference<CASSingleton> singletonAtomicReference = new AtomicReference<>();
    /**
     * 用CAS确保线程安全
     */
    public static CASSingleton getInstance() {
        while (true) {
            CASSingleton singleton = singletonAtomicReference.get();// 获得singleton
            if (singleton != null) {// 如果singleton不为空，就返回singleton
                return singleton;
            }
            // 如果singleton为空，创建一个singleton
            singleton = new CASSingleton();
            // CAS操作，预期值是NULL，新值是singleton
            // 如果成功，返回singleton
            // 如果失败，进入第二次循环，singletonAtomicReference.get()就不会为空了
            if (singletonAtomicReference.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }
}
