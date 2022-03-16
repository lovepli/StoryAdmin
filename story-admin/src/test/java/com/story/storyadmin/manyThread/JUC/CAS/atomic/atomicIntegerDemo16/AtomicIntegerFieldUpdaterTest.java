package com.story.storyadmin.manyThread.JUC.CAS.atomic.atomicIntegerDemo16;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author: lipan
 * @date: 12:10 上午
 * @description: 3. 作为类属性的使用
 */
public class AtomicIntegerFieldUpdaterTest {
    static class A {
        volatile int intValue = 100;
    }
    public final static AtomicIntegerFieldUpdater<A> ATOMIC_INTEGER_UPDATER = AtomicIntegerFieldUpdater.newUpdater(A.class, "intValue");
    public static void main(String []args) {
        final A a = new A();
        for(int i = 0 ; i < 10 ; i++) {
            new Thread() {
                public void run() {
                    if(ATOMIC_INTEGER_UPDATER.compareAndSet(a, 100, 120)) {
                        System.out.println(Thread.currentThread().getName() + " 对对应的值做了修改！");
                    }
                }
            }.start();
        }
    }
}