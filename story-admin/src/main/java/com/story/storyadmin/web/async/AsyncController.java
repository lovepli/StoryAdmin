package com.story.storyadmin.web.async;

import com.story.storyadmin.service.async.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: 59688
 * @date: 2021/7/21
 * @description: https://mp.weixin.qq.com/s/sX9UP8nxzYjhoKlN-IOBFQ
 */
public class AsyncController {


    @Autowired
    private AsyncService asyncService;

    @GetMapping("/async")
    public void async(){
        asyncService.executeAsync();
    }

    /**
     * 通过以上日志可以发现，[async-service-]是有多个线程的，显然已经在我们配置的线程池中执行了，并且每次请求中，
     * controller的起始和结束日志都是连续打印的，表明每次请求都快速响应了，而耗时的操作都留给线程池中的线程去异步执行；
     */
}
