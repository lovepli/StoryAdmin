package com.story.storyadmin.manyThread.volatileDemo8;

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
 * @description:  4、分析线程并发访问代码解释原因
 */
@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("单元测试")
public class VolatileTest {

    @Autowired(required=true)
    private VisiableThreadPoolTaskExecutor threadPoolTaskExecutor;

    @org.junit.jupiter.api.Test
    @DisplayName("分析线程并发访问代码解释原因")
    public void test() throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getThreadGroup() + "----main线程------" + Thread.currentThread().getName());
        final Counter counter=new Counter();
        for (int i = 0; i < 10; i++) {//开启10个子线程
            threadPoolTaskExecutor.execute(new CounterRunnable(counter));
        }
        System.out.println(counter);
        System.out.println("主线程调用结束");
        //可以看到子线程比较耗时，主线程结束之后，子线程还没有执行完；所以我们为了看到所有执行的结果输出，给主线程睡眠时间大于子线程执行总耗时，如下
       Thread.sleep(3000); // 为什么主线程这么快就结束了？是因为异步的原因么？TODO
    }


    /**
     * 解释说明：
     *在 java 的内存模型中每一个线程运行时都有一个线程栈，线程栈保存了线程运行时候变量值信息。当线程访问某
     * 一个对象时候值的时候，首先通过对象的引用找到对应在堆内存的变量的值，然后把堆内存变量的具体值 load 到线程
     * 本地内存中，建立一个变量副本，之后线程就不再和对象在堆内存变量值有任何关系，而是直接修改副本变量的值，在
     * 修改完之后的某一个时刻（线程退出之前），自动把线程变量副本的值回写到对象在堆中变量。这样在堆中的对象的值
     * 就产生变化了。
     * 也就是说上面主函数中开启了 10 个子线程，每个线程都有一个变量副本，每个线程修改变量只是临时修改了
     * 自己的副本，当线程结束时再将修改的值写入在主内存中，这样就出现了线程安全问题。因此结果就不可能等于 10
     * 了，一般都会小于 10
     */
}
