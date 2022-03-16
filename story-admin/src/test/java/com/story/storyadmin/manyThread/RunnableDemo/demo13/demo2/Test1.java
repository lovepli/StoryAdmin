package com.story.storyadmin.manyThread.RunnableDemo.demo13.demo2;

/**
 * @author: 59688
 * @date: 2021/8/4      wait 和 notify 有坑。。 https://mp.weixin.qq.com/s/KCu3iEG1xbqQVw1GviXOjA
 * @description: 也许我们只知道 wait 和 notify 是实现线程通信的，同时要使用 synchronized 包住，其实在开发中知道这个是远远不够的。
 *
 * 接下来看看两个常见的问题。
 * 问题一：通知丢失
 * 问题二：假唤醒
 */
public class Test1 {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        //先启动获取计算结果线程
        new ReaderResult(calculator).start();
        calculator.start();

    }
}
