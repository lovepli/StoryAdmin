package com.story.storyadmin.basic.thread.concurrentThread;

/**
 * @author: lipan
 * @date: 2019-06-11
 * @description:
 *
 * 调用Thread.currentThread()获取当前线程。
 * JDK提供了ThreadLocal，在一个线程中传递同一个对象。
 * ThreadLocal表示线程的“局部变量”，它确保每个线程的ThreadLocal变量都是各自独立的。
 * ThreadLocal适合在一个线程的处理流程中保持上下文(避免了同一参数在所有方法中传递) 使用ThreadLocal要用try ... finally结构。
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {

    }
}
