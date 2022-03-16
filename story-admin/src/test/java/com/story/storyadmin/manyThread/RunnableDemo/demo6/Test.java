package com.story.storyadmin.manyThread.RunnableDemo.demo6;

import com.story.storyadmin.config.threadpool.VisiableThreadPoolTaskExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

/**
 * @author: lipan
 * @date: 11:12 下午
 * @description: 要求： 子线程运行执行 10 次后，主线程再运行 5 次。这样交替执行三遍
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
        System.out.println(Thread.currentThread().getThreadGroup() + "----main线程------" + Thread.currentThread().getName());
        final Bussiness bussiness = new Bussiness();
        threadPoolTaskExecutor.execute(new BussinessRunnableTask(bussiness));
        //主线程
        for (int i = 0; i < 3; i++) {
            bussiness.mainMethod();
        }
    }


}
