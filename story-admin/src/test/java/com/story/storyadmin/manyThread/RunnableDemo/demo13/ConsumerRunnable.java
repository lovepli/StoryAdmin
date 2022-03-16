package com.story.storyadmin.manyThread.RunnableDemo.demo13;

/**
 * @author: 59688
 * @date: 2021/7/29
 * @description: 消费者
 */
public class ConsumerRunnable implements Runnable{
    private Resource res;

    ConsumerRunnable(Resource res) {
        this.res = res;
    }

    //消费者消费资源
    public void run() {
        while (true) {
            res.out();
        }
    }
}
