package com.story.storyadmin.manyThread.threadLocalDemo7.demo1;

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
 * @description:
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
        StudentShareData bussiness = new StudentShareData();
        //主线程
        for (int i = 0; i < 2; i++) { //开启2个线程
            threadPoolTaskExecutor.execute(new ThreadLocalRunnable(bussiness));
        }

        System.out.println("主线程调用结束");
        //可以看到子线程比较耗时，主线程结束之后，子线程还没有执行完；所以我们为了看到所有执行的结果输出，给主线程睡眠时间大于子线程执行总耗时，如下
        Thread.sleep(2000); // 为什么主线程这么快就结束了？是因为异步的原因么？TODO

        // TODO 怎么实现将这两个线程的数值相加起来？？
    }

    /**
     * TODO 使用单例模式，则导致结果出现问题,单例模式本来就是为了满足多线程调用同一个实例，共享对象使用了单例，就不会存在多线程直接竞争。？？？
     * 单例模式是指单线程情况下
     * 单例模式与多线程： https://www.cnblogs.com/yoga21/p/9224557.html
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @org.junit.jupiter.api.Test
    @DisplayName("多线程共享数据")
    public void test2() throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getThreadGroup() + "----main线程------" + Thread.currentThread().getName());
        StudentShareData2 bussiness = StudentShareData2.getUniqueInstance();
        //主线程
        for (int i = 0; i < 2; i++) { //开启2个线程
            threadPoolTaskExecutor.execute(new ThreadLocalRunnable2(bussiness));
        }

        System.out.println("主线程调用结束");
        //可以看到子线程比较耗时，主线程结束之后，子线程还没有执行完；所以我们为了看到所有执行的结果输出，给主线程睡眠时间大于子线程执行总耗时，如下
        Thread.sleep(2000); // 为什么主线程这么快就结束了？是因为异步的原因么？TODO

        // TODO 怎么实现将这两个线程的数值相加起来？？
    }
}