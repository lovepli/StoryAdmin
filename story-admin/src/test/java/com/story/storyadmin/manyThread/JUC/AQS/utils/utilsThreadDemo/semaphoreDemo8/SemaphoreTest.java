package com.story.storyadmin.manyThread.JUC.AQS.utils.utilsThreadDemo.semaphoreDemo8;

import com.story.storyadmin.config.threadpool.VisiableThreadPoolTaskExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

/**
 * @author: 59688
 * @date: 2021/7/28
 * @description: 9、如何控制某个方法允许并发访问线程的个数？
 */
@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("单元测试")
public class SemaphoreTest {

    @Autowired(required=true)
    private VisiableThreadPoolTaskExecutor threadPoolTaskExecutor;

    @org.junit.jupiter.api.Test
    @DisplayName("分析线程并发访问代码解释原因")
    public void test() throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getThreadGroup() + "----main线程------" + Thread.currentThread().getName());
        final SemaphoreDemo semaphoreDemo= new SemaphoreDemo();
        for (int i = 0; i < 10; i++) {//开启10个子线程
            threadPoolTaskExecutor.execute(new SemaphoreRunnable(semaphoreDemo));
        }
        System.out.println("主线程调用结束");
        //可以看到子线程比较耗时，主线程结束之后，子线程还没有执行完；所以我们为了看到所有执行的结果输出，给主线程睡眠时间大于子线程执行总耗时，如下
        Thread.sleep(3000); // 为什么主线程这么快就结束了？是因为异步的原因么？TODO
    }


}
