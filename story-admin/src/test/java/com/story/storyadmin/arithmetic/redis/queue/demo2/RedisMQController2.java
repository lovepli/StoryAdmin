package com.story.storyadmin.arithmetic.redis.queue.demo2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 田哥 手把手教 你用 Redis 做延迟消息队列 https://mp.weixin.qq.com/s/_Dkj-DsbQ7B0J1GYzJ8Zeg
 * 本质上用定时任务去判断当前时间与delayTime做对比，然后发送消息
 */
@RestController
public class RedisMQController2 {
    @Resource
    private MessageProvider messageProvider;

    @PostMapping("/delay/produce")
    public String produce() {
        //延迟20秒
        messageProvider.sendMessage("同时发送消息1", 20);
        messageProvider.sendMessage("同时发送消息2", 20);
        return "ok";
    }
}