package com.story.storyadmin.设计模式.委派模式;

/**
 * @author: lipan
 * @date: 2021/8/22
 * @description: 开发同事有很多，但是有个统一的属性，那就是码代码：
 */
//开发的同事进行抽象
public interface IEmployee {
    void doing(String command);
}
