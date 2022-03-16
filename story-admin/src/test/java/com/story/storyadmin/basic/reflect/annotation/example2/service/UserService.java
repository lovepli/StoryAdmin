package com.story.storyadmin.basic.reflect.annotation.example2.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author: lipan
 * @date: 2019-06-13
 * @description: 这里简单起见，就用一个本地的数组来维护管理员，正常应该是把管理员相关信息存到数据库里。
 */
@Service
public class UserService {
    private String[] admins = {"qiushi", "weixin", "xiaoshitou"};

    //是否是管理员
    public boolean isAdmin(String name) {
        return Arrays.asList(admins).contains(name);
    }
}
