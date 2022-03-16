package com.story.storyadmin.arithmetic.redis.queue.demo1;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Redis实现消息队列
 */
@RestController
public class RedisMQController {
    @Resource
    private RedisMQService redisMQService;

    @PostMapping("/produce")
    public String produce() {
        String[] names = {"java后端技术全栈", "老田", "面试专栏"};
        for (String name : names) {
            redisMQService.produce(name);
        }
        return "ok";
    }

    /**
     * 访问：http://localhost:8080/consume从消息队列中取出消息，然后就可以拿着消息继续做相关业务了。
     */
    @PostMapping("/consume")
    public void consume() {
        int i = 0;
        while (i < 3) {
            redisMQService.consume();
            i++;
        }
    }
}