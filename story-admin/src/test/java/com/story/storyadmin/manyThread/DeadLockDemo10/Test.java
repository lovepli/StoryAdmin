package com.story.storyadmin.manyThread.DeadLockDemo10;

import com.story.storyadmin.config.threadpool.VisiableThreadPoolTaskExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * @author: lipan
 * @date: 11:33 下午
 * @description:
 *  * 如何避免死锁？
 *  * 1）加锁顺序（线程按照一定的顺序加锁） https://mp.weixin.qq.com/s/6coxAWHYIVb3I4CTCwnzug
 *  * 2）加锁时限（线程尝试获取锁的时候加上一定的时限，超过时限则放弃对该锁的请求，并释放自己占有的锁）
 */
@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("单元测试")
public class Test {

    @Autowired(required=true)
    private VisiableThreadPoolTaskExecutor threadPoolTaskExecutor;

    @org.junit.jupiter.api.Test
    @DisplayName("线程测试")
    public void test() throws ExecutionException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        System.out.println(Thread.currentThread().getThreadGroup() + "----main线程------" + Thread.currentThread().getName());
        final DeadLock semaphoreDemo1 = new DeadLock();
        final DeadLock semaphoreDemo2 = new DeadLock();
        semaphoreDemo1.flag =1;
        semaphoreDemo2.flag =0;
        threadPoolTaskExecutor.execute(new DeadLockRunnable1(semaphoreDemo1,latch));
        latch.await(); //让 线程DeadLockRunnable2 等待 线程DeadLockRunnable1 执行完才开始执行,参考：https://mp.weixin.qq.com/s/6coxAWHYIVb3I4CTCwnzug
        threadPoolTaskExecutor.execute(new DeadLockRunnable2(semaphoreDemo2));
        System.out.println("主线程调用结束");
        //可以看到子线程比较耗时，主线程结束之后，子线程还没有执行完；所以我们为了看到所有执行的结果输出，给主线程睡眠时间大于子线程执行总耗时，如下
        Thread.sleep(4000); // 为什么主线程这么快就结束了？是因为异步的原因么？TODO
    }
}
