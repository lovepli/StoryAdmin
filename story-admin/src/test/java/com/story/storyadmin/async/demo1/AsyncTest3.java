package com.story.storyadmin.async.demo1;

import com.story.storyadmin.async.AsyncAnnotationCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: lipan
 * @date: 1:06 上午
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncTest3 {

    @Autowired
    private AsyncAnnotationCode asyncAnnotationCode;

    /**
     * 测试线程异步方法
     */
    @Test
    public  void asyncTest() {

        try {
            long startTime = System.currentTimeMillis();
            System.out.println("执行方法 当前线程名称:"+Thread.currentThread().getName());
            asyncAnnotationCode.step1();
            asyncAnnotationCode.step2();
            asyncAnnotationCode.step3();
            long endTime = System.currentTimeMillis();
            System.out.println("流程耗时："+(endTime-startTime));

            while (true){

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
