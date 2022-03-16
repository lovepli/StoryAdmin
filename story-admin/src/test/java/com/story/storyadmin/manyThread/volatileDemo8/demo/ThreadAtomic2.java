package com.story.storyadmin.manyThread.volatileDemo8.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: lipan
 * @date: 2021年08月27日 11:17 上午
 * @description: 原子性验证
 * 看下面一段代码，number变量加了volatile修饰。创建了10个子线程，每个线程循环1000次执行number++。
 * 按理说number最终应该是10000，但是这边执行后，结果始终不为10000
 *
 * 原子性问题解决
 * 方法一：使用 synchronized 关键字
 * 方法二：使用AtomicInteger
 */
public class ThreadAtomic2 {
    static class MyTest {
        public volatile AtomicInteger number = new AtomicInteger();
        public void incr(){
            number.getAndIncrement();
        }
    }

    public static void main(String[] args) {
        MyTest myTest = new MyTest();
        for (int i = 1; i <= 10; i++){
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++){
                    myTest.incr();
                }
            }, "Thread"+String.valueOf(i)).start();
        }

        //等线程执行结束了，输出number值
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println("当前number：" + myTest.number);
    }
}
