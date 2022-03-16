package com.story.storyadmin.framework.threadPool;

import java.util.stream.IntStream;

/**
 * @author binghe
 * @version 1.0.0
 * @description 测试自定义线程池
 */
public class ThreadPoolTest {

    public static void main(String[] args){
        MyThreadPool threadPool = new MyThreadPool(10);
        IntStream.range(0, 10).forEach((i) -> {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "--->> Hello ThreadPool");
            });
        });
    }
}