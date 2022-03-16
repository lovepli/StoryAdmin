package com.story.storyadmin.basic.juc.callableInterface;

import java.util.concurrent.Callable;

/**
 * @author: lipan
 * @date: 2019-06-27
 * @description:
 *
 * 获得多线程的方法几种？
 * 传统的是继承 thread 类和实现 runnable 接口， java5 以后又有实现 callable 接口和 java 的线程池获得
 * Callable接口是一个功能接口， 因此可以用作 lambda 表达式或方法引用的赋值对象。
 *
 * callable 接口与 runnable 接口的区别？
 * （1） 是否有返回值
 * （2） 是否抛异常
 * （3） 落地方法不一样， 一个是 run， 一个是 call
 */
class MyThread implements Runnable{
    @Override
    public void run() {
    }
}
//新类 MyThread2 实现 callable 接口
class MyThread2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 200;
    }
}