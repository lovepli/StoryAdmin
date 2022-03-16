package com.story.storyadmin.manyThread.CallableDemo.CallableDemo3;

import com.story.storyadmin.config.threadpool.VisiableThreadPoolTaskExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: lipan
 * @date: 2020-04-22
 * @description: 创建有返回值的线程
 */
@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("单元测试")
public class CallableDemoTest {


    @Autowired(required=true)
    private VisiableThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    @DisplayName("创建有返回值的线程")
    public void test() throws ExecutionException, InterruptedException {
        System.out.println("-----线程1-----" + Thread.currentThread().getName());
        System.out.println("----程序开始运行----");
        Date date1 = new Date();

        // 线程数
        int taskSize = 5;
        // 创建一个线程池
        //ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            CallableTask c = new CallableTask(i + " ");
            // 执行任务并获取 Future 对象
            //Future f = pool.submit(c);
            Future<String> future = threadPoolTaskExecutor.submit(c);
            // System.out.println(">>>" + f.get().toString());
            list.add(future);
        }
        // 关闭线程池
        //pool.shutdown();
        //threadPoolTaskExecutor.shutdown();

        // 获取所有并发任务的运行结果
        for (Future f : list) {
            // 从 Future 对象上获取任务的返回值，并输出到控制台
            System.out.println(">>>" + f.get().toString());
        }

        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【" + (date2.getTime() - date1.getTime()) + "毫秒】 ");
    }
}


