package com.story.storyadmin.manyThread.JUC.AQS.locks.lockDemo15;

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
 * @date: 2021/7/29
 * @description:
 *  * 11、同一个类中的 2 个方法都加了同步锁，多个线程能同时访问同一个类中的这两个方法吗？
 *  *  分析;这个问题需要考虑到 Lock 与 synchronized关键字 两种实现锁的不同情形。因为这种情况下使用 Lock 和 synchronized关键字
 *  * 会有截然不同的结果。 Lock 可以让等待锁的线程响应中断， Lock 获取锁，之后需要释放锁。
 *  *
 *  * 如下代码，多个线程不可访问同一个类中的 2 个加了 Lock 锁的方法。而 synchronized关键字 却行，使用 synchronized关键字 时，
 *  * 当我们访问同一个类对象的时候，是同一把锁，所以可以访问该对象的其他 synchronized关键字 方法。
 *
 *
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
        final LockDemo lockDemo= new LockDemo();
         threadPoolTaskExecutor.execute(new LockRunnable1(lockDemo));
         threadPoolTaskExecutor.execute(new LockRunnable2(lockDemo));

        System.out.println("主线程调用结束");
        //可以看到子线程比较耗时，主线程结束之后，子线程还没有执行完；所以我们为了看到所有执行的结果输出，给主线程睡眠时间大于子线程执行总耗时，如下
        Thread.sleep(4000); // 为什么主线程这么快就结束了？是因为异步的原因么？TODO
    }
}
