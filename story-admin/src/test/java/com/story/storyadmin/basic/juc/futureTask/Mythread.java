package com.story.storyadmin.basic.juc.futureTask;

import java.util.concurrent.Callable;

/**
 * @author: lipan
 * @date: 2019-06-27
 * @description:
 */
class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception
    {
        Thread.sleep(4000);
        System.out.println(Thread.currentThread().getName()+" *****come in call");
        return 200;
    }
}
