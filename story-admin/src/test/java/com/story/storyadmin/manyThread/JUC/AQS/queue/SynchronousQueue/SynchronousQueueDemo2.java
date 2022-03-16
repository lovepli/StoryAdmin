package com.story.storyadmin.manyThread.JUC.AQS.queue.SynchronousQueue;


import java.util.Date;
import java.util.concurrent.SynchronousQueue;


/**
 * 无缓冲等待队列
 * java并发之SynchronousQueue实现原理  https://blog.csdn.net/yanyan19880509/article/details/52562039
 *
 * SynchronousQueue 也是一个队列来的，但它的特别之处在于它内部没有容器，一个生产线程，当它生产产品（即put的时候），如果当前没有人想要消费产品(即当前没有线程执行take)，此生产线程必须阻塞，
 * 等待一个消费线程调用take操作，take操作将会唤醒该生产线程，同时消费线程会获取生产线程的产品（即数据传递），这样的一个过程称为一次配对过程(当然也可以先take后put,原理是一样的)。
 */
public class SynchronousQueueDemo2 {

    public static void main(String[] args) {
        SynchronousQueue queue = new SynchronousQueue();

        // 入队
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    System.out.println(new Date() + "，元素入队");
                    queue.put("Data " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        // 出队
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println(new Date() + "，元素出队：" + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
