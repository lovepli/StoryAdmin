package com.story.storyadmin.web.interceptorTestController;

import com.story.storyadmin.Interceptor.demo2.UnInterception;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: 59688
 * @date: 2021/8/2
 * @description: 测试自定义拦截器
 */
@Controller
@RequestMapping("/interceptor")
public class InterceptorController {

    @UnInterception
    @RequestMapping("/test")
    public String test() {
        return "hello";
    }
}