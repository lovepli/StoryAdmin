package com.story.storyadmin.Interceptor.demo2;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author: 59688
 * @date: 2021/7/28 https://www.cnblogs.com/codeclock/p/12093604.html
 * @description: 一个注解搞定 SpringBoot 接口防刷，还有谁不会？ https://mp.weixin.qq.com/s/KlMGqFOqiTMDdtQ_7WYuwg
 * 实现思路：
 *
 * 定义限流注解，定义时间段 second 和 最大访问次数 maxCount
 * 在目标方法上使用限流注解
 * 定义拦截器，第一次访问方法A时，在 redis 中记录key,value为访问次数count=1，并设定过期时间为 second
 * 后面每次访问方法A,从 redis中根据 key取 count值. 判断 count 值 与定义的限流方法注解设定值 maxCount 对比，如果小于maxCount，则 count加1，如果大于maxCount 则直接返回false
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {

    int seconds();
    int maxCount();
    boolean needLogin()default true;
}
