package com.story.storyadmin.basic.juc.futureTask;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: lipan
 * @date: 2019-06-27
 * @description:
 *
 * 原理：在主线程中需要执行比较耗时的操作时， 但又不想阻塞主线程时， 可以把这些作业交给 Future 对象在后台完成，
 * 当主线程将来需要时， 就可以通过 Future 对象获得后台作业的计算结果或者执行状态
 *   一般 FutureTask 多用于耗时的计算， 主线程可以在完成自己的任务后， 再去获取结果。
 *
 *   仅在计算完成时才能检索结果； 如果计算尚未完成， 则阻塞 get 方法。 一旦计算完成，
 * 就不能再重新开始或取消计算。 get 方法而获取结果只有在计算完成时获取， 否则会一直阻塞直到任务转入完成状
 * 态， 然后会返回结果或者抛出异常。
 *
 *   只计算一次：get 方法放到最后
 *
 */
public class CallableDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<Integer> ft = new FutureTask<Integer>(new MyThread());
        new Thread(ft, "AA").start();
//        FutureTask<Integer> ft2 = new FutureTask<Integer>(new MyThread());
//        new Thread(ft2, "BB").start();
        System.out.println(Thread.currentThread().getName() + "------main");
        Integer result = ft.get();
//        Integer result2 = ft2.get();
        System.out.println("**********result: " + result);
    }
}