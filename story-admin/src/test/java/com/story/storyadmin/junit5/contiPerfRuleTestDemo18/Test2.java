package com.story.storyadmin.junit5.contiPerfRuleTestDemo18;

import com.story.storyadmin.config.threadpool.VisiableThreadPoolTaskExecutor;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.service.sysmgr.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: 59688
 * @date: 2021/8/5
 * @description:
 */
@RunWith(SpringRunner.class)
//SpringBootTest 是springboot 用于测试的注解，可指定启动类或者测试环境等，这里直接默认。
@SpringBootTest
public class Test2 {

    @Autowired(required=true)
    private VisiableThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private UserService userService;

    @Test
    public  void  test2()throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(10);
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    User user = userService.selectUserById(1l);
                    System.out.println("用户手机号码："+user.getPhone());
                    semaphore.release();
                } catch (Exception e) {
                    // log.error("exception" , e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long a = System.currentTimeMillis();
        System.out.println(a-l);

        executorService.shutdown();

        //log.info("size:{}" , map.size());
    }
}




