package com.story.storyadmin.basic.thread.concurrentThread;

/**
 * @author: lipan
 * @date: 2019-06-11
 * @description:
 *
 * CompletableFuture
 *
 * CompletableFuture CompletableFuture的优点:
 *      异步任务结束时，会自动回调某个对象的方法
 *      异步任务出错时，会自动回调某个对象的方法
 *      主线程设置好回调后，不再关心异步任务的执行
 * CompletableFuture对象可以指定异步处理流程:
 * thenAccept()处理正常结果
 * exceptional()处理异常结果
 * thenApplyAsync() 用于串行化另一个CompletableFuture anyOf / allOf 用于并行化两个CompletableFuture
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {

    }
}
