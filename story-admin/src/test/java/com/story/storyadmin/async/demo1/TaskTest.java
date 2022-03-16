package com.story.storyadmin.async.demo1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Future;

/**
 * @author: lipan
 * @date: 10:24 下午
 * @description: https://blog.didispace.com/springbootasync/
 */
@Slf4j
@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("异步元测试")
public class TaskTest {

    @Autowired
    private TaskDemo task;

    @Autowired
    private TaskDemo2 taskk;

    /**
     * 测试异步调用
     * @throws Exception
     */
    @Test
    @DisplayName("测试异步调用")
    public void test() throws InterruptedException {
        System.out.println(Thread.currentThread().getThreadGroup() + "----当前线程------" + Thread.currentThread().getName());
        try {
            task.doTaskOne();
            task.doTaskTwo();
            task.doTaskThree();
        }  catch (InterruptedException e) {
           log.error("async error");
        }
        //给主线程睡眠时间大于子线程执行总耗时，如下
        Thread.sleep(10*1000);

    }

    /**
     * 测试异步回调
     *
     * 在测试用例一开始记录开始时间
     * 在调用三个异步函数的时候，返回Future<String>类型的结果对象
     * 在调用完三个异步函数之后，开启一个循环，根据返回的Future<String>对象来判断三个异步函数是否都结束了。若都结束，就结束循环；若没有都结束，就等1秒后再判断。
     * 跳出循环之后，根据结束时间 - 开始时间，计算出三个任务并发执行的总耗时。
     * @throws Exception
     */
    @Test
    @DisplayName("测试异步回调")
    public void test2() throws Exception {

        long start = System.currentTimeMillis();

        Future<String> task1 = taskk.doTaskOne();
        Future<String> task2 = taskk.doTaskTwo();
        Future<String> task3 = taskk.doTaskThree();

        while(true) {
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");

    }
}
