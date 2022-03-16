package com.story.storyadmin.framework.springMVCDemo.springmvc2.controller;

import com.story.storyadmin.framework.springMVCDemo.springmvc2.annotation.Qualifier;
import com.story.storyadmin.framework.springMVCDemo.springmvc2.annotation.RequestMapping;
import com.story.storyadmin.framework.springMVCDemo.springmvc2.service.UserService;

/**
 * @author: 59688
 * @date: 2021/9/28
 * @description:
 */
@RequestMapping("/user")
public class UserController {

    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping("/insert")
    public void insert(){
        userService.insert();
    }
}
