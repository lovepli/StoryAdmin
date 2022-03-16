package com.story.storyadmin.manyThread.threadLocalDemo7.demo2;

import com.story.storyadmin.config.threadpool.VisiableThreadPoolTaskExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author: 59688
 * @date: 2021/7/30
 * @description: 假如我们有一个需求，那就是在多线程环境下，去格式化时间为指定格式yyyy-MM-dd HH:mm:ss，假设一开始只有两个线程需要这么做，代码如下：
 */
@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("单元测试")
public class Test {

    @Autowired(required=true)
    private VisiableThreadPoolTaskExecutor threadPoolTaskExecutor;

    @org.junit.jupiter.api.Test
    @DisplayName("多线程共享数据")
    public void test() throws ExecutionException, InterruptedException {
        System.out.println("-----线程-----" + Thread.currentThread().getName());

        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();

        Future<String> future1 = threadPoolTaskExecutor.submit(new DateFormatDemoRunnable(new DateFormatDemo(10)));
        Future<String> future2 = threadPoolTaskExecutor.submit(new DateFormatDemoRunnable(new DateFormatDemo(100)));
        list.add(future1);
        list.add(future2);

        // 获取所有并发任务的运行结果
        for (Future f : list) {
            // 从 Future 对象上获取任务的返回值，并输出到控制台
            System.out.println(">>>" + f.get().toString());
        }


    }
}
