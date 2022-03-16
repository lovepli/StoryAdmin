package com.story.storyadmin.basic.reflect.annotation.example2.controller;


import com.story.storyadmin.basic.reflect.annotation.example2.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lipan
 * @date: 2019-06-13
 * @description:
 */

//@RestController
@Slf4j
public class UserController {

    //带注解，需要校验权限
    @GetMapping(value = "/user")
    @Permission
    public String user(@RequestParam String name) {
        return "你好:"+name+",您有管理权限";
    }

    //不带注解，不需要安全校验
    @GetMapping(value = "/user2")
    public String user2(@RequestParam String name) {
        return "不用检查权限，直接返回的数据";
    }

}

