package com.story.storyadmin.manyThread.executorThreadPoolDemo17.threadPool2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;

/**
 * @author: 59688
 * @date: 2021/9/24
 * @description: 任务数量超过线程池负荷了怎么办？拒绝策略安排起来！ https://mp.weixin.qq.com/s/ejjbAnJjN9SD_n0Pb5533g
 * 验证线程拒绝策略
 */

@Slf4j
@SpringBootTest
public class AsyncTasksTest {

    @Autowired
    private AsyncTasks asyncTasks;

    @Test
    public void test2() throws Exception {
        // 线程池配置：core-2,max-2,queue=2，同时有5个任务，出现下面异常：
        // org.springframework.core.task.TaskRejectedException: Executor [java.util.concurrent.ThreadPoolExecutor@59901c4d[Running, pool size = 2,
        // active threads = 0, queued tasks = 2, completed tasks = 4]] did not accept task: java.util.concurrent.CompletableFuture$AsyncSupply@408e96d9

        long start = System.currentTimeMillis();

        // 线程池1
        CompletableFuture<String> task1 = asyncTasks.doTaskOne("1");
        CompletableFuture<String> task2 = asyncTasks.doTaskOne("2");
        CompletableFuture<String> task3 = asyncTasks.doTaskOne("3");
        CompletableFuture<String> task4 = asyncTasks.doTaskOne("4");
        CompletableFuture<String> task5 = asyncTasks.doTaskOne("5");

        // 一起执行
        CompletableFuture.allOf(task1, task2, task3, task4, task5).join();

        long end = System.currentTimeMillis();

        log.info("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }
}
