package com.story.storyadmin.manyThread.RunnableDemo.demo13;



/**
 * @author: 59688
 * @date: 2021/7/29
 * @description: 生产者
 */
public class ProducerRunnable implements Runnable{

    private Resource res;

    ProducerRunnable(Resource res) {
        this.res = res;
    }

    //生产者生产资源
    public void run() {
        while (true) {
            res.set("商品");
        }
    }
}
