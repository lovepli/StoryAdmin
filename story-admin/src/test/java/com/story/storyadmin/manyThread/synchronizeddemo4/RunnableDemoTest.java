package com.story.storyadmin.manyThread.synchronizeddemo4;

import com.story.storyadmin.config.threadpool.VisiableThreadPoolTaskExecutor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

/**
 * @author: 59688
 * @date: 2021/7/27
 * @description: 多线程共享数据,对共享数据对象添加 synchronized关键字，防止竞争
 */
@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("单元测试")
public class RunnableDemoTest {

    @Autowired(required=true)
    private VisiableThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    @DisplayName("多线程共享数据")
    public void test() throws ExecutionException, InterruptedException {
        System.out.println("-----线程-----" + Thread.currentThread().getName());
        ShareDatas shareData = new ShareDatas();
        for (int i = 0; i < 4; i++) { //开启四个线程
            threadPoolTaskExecutor.execute(new RunnableTask(shareData));
        }
        System.out.println("主线程调用结束");
        //可以看到子线程比较耗时，主线程结束之后，子线程还没有执行完；所以我们为了看到所有执行的结果输出，给主线程睡眠时间大于子线程执行总耗时，如下
        Thread.sleep(20000); // 为什么主线程这么快就结束了？是因为异步的原因么？TODO
    }

}
