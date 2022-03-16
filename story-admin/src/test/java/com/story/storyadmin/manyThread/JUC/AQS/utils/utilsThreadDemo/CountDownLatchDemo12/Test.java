package com.story.storyadmin.manyThread.JUC.AQS.utils.utilsThreadDemo.CountDownLatchDemo12;

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
 * @author: 59688
 * @date: 2021/7/29
 * @description:
 *      * Java 中多线程间的通信怎么实现?
 *      *
 *      * 线程通信的方式：
 *      * 1.共享变量
 *      * 线程间通信可以通过发送信号，发送信号的一个简单方式是在共享对象的变量里设置信号值。线程 A 在一个
 *      * 同步块里设置 boolean 型成员变量 hasDataToProcess 为 true，线程 B 也在同步块里读取 hasDataToProcess
 *      * 这个成员变量。这个简单的例子使用了一个持有信号的对象，并提供了 set 和 get 方法:
 *      *
 *      * 2.wait/notify 机制
 *      * 以资源为例，生产者生产一个资源，通知消费者就消费掉一个资源，生产者继续生产资源，消费者消费资源，以
 *      * 此循环。
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
        System.out.println(Thread.currentThread().getThreadGroup() + "----main线程------" + Thread.currentThread().getName());
        CountDownLatch latch = new CountDownLatch(1);
        final  MySignal mySignal=new MySignal();
        threadPoolTaskExecutor.execute(new MySignalRunnable1(mySignal,latch));
        latch.await();
        threadPoolTaskExecutor.execute(new MySignalRunnable2(mySignal));
        System.out.println("主线程调用结束");  //等待线程MySignalRunnable1 完成然后取值
        //可以看到子线程比较耗时，主线程结束之后，子线程还没有执行完；所以我们为了看到所有执行的结果输出，给主线程睡眠时间大于子线程执行总耗时，如下
        Thread.sleep(4000); // 为什么主线程这么快就结束了？是因为异步的原因么？TODO
    }
}
