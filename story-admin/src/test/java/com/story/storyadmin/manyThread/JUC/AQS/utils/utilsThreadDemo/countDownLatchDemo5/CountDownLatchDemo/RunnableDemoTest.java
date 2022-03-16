package com.story.storyadmin.manyThread.JUC.AQS.utils.utilsThreadDemo.countDownLatchDemo5.CountDownLatchDemo;

import com.story.storyadmin.config.threadpool.VisiableThreadPoolTaskExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * @author: 59688
 * @date: 2021/7/27
 * @description: 多线程共享数据
 *  * 2. 多个线程行为不一致共同操作一个数据
 *  * 如果每个线程执行的代码不同，这时候需要用不同的 Runnable 对象，有如下两种方式来实现这些 Runnable 对
 *  * 象之间的数据共享：
 *  * 1) 将共享数据封装在另外一个对象中，然后将这个对象逐一传递给各个 Runnable 对象。每个线程对共享
 *  * 数据的操作方法也分配到那个对象身上去完成，这样容易实现针对该数据进行的各个操作的互斥和通信
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
        CountDownLatch latch = new CountDownLatch(2);
        //RunnableCusToInc,RunnableCusToDec都处于可执行状态，但 JVM 线程调度先执行哪个线程是不确定的。
        //RunnableCusToInc 的 run()可能在 RunnableCusToDec 的 run()之前运行，也可能在之后执行
        threadPoolTaskExecutor.execute(new RunnableCusToInc(shareData,latch));
        threadPoolTaskExecutor.execute(new RunnableCusToDec(shareData,latch));
        latch.await();//等待两个线程运行完继续执行主线程未完成代代码 参考：https://mp.weixin.qq.com/s/6coxAWHYIVb3I4CTCwnzug

        System.out.println("继续执行主线程代码");
        System.out.println("主线程调用结束");
    }


    /**
     * CountDownLatch
     * 让一些线程阻塞直到另外一些线程完成后才别唤醒
     *
     * CountDownLatch 主要有两个方法，当一个或多个线程调用 await 方法时，调用线程会被阻塞，其他线程调用 countDown 方法计数器减 1（调用 countDown 方法时线程不会阻塞），当计数器的值变为 0，因调用 await 方法被阻塞的线程会被唤醒，进而继续执行。
     *
     * 关键方法：
     * 1）await()  方法
     * 2） countDown()  方法
     */

}
