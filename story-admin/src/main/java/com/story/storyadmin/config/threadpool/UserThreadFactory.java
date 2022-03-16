package com.story.storyadmin.config.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: lipan
 * @date: 2021年08月27日 11:31 下午
 * @description: 自定义线程工厂
 */
public class UserThreadFactory implements ThreadFactory {

    private final String namePrefix;

    private final AtomicInteger nextId = new AtomicInteger(1);

    // 定义线程诅名称，在使用jstack来排查线程问题的时候，非常有帮助
    public UserThreadFactory(String whatFeatureOfGroup) {
        this.namePrefix = "来自自定义线程工厂的"+whatFeatureOfGroup+"-workder-";
    }


    @Override
    public Thread newThread(Runnable task) {
        String name =namePrefix+nextId.getAndIncrement();
        Thread thread =new Thread(null,task,name,0);
        System.out.println(thread.getName());
        return thread;
    }
}
