package com.story.storyadmin.async;

import com.story.storyadmin.service.async.AsyncService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: lipan
 * @date: 12:35 上午
 * @description:
 */
@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("异步元测试")
public class AsyncTest {

    @Autowired
    private AsyncService asyncService;

    @Test
    @DisplayName("测试异步方法")
    public void testAsync(){
        asyncService.executeAsync();
        /**
         *      * 通过以上日志可以发现，[async-service-]是有多个线程的，显然已经在我们配置的线程池中执行了，并且每次请求中，
         *      * controller的起始和结束日志都是连续打印的，表明每次请求都快速响应了，而耗时的操作都留给线程池中的线程去异步执行；
         */
    }
}
