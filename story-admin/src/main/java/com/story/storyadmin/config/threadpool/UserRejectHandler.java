package com.story.storyadmin.config.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: lipan
 * @date: 2021年08月27日 11:42 下午
 * @description: 自定义线程拒绝处理
 *
线程池的拒绝策略，是指当任务添加到线程池中被拒绝，而采取的处理措施。
当任务添加到线程池中之所以被拒绝，可能是由于：第一，线程池异常关闭。第二，任务数量超过线程池的最大限制。

 */
public class UserRejectHandler implements RejectedExecutionHandler {

    public UserRejectHandler(){

    }

    public UserRejectHandler(ThreadPoolExecutor executor){

    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        System.out.println("自定义拒绝策略.....");
        System.out.println("当前被拒绝的任务------"+Thread.currentThread().getName()+":"+r.toString());
        System.out.println("任务拒绝."+executor.toString());
    }
}
