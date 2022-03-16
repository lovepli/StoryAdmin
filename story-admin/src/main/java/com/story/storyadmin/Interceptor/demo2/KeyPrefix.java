package com.story.storyadmin.Interceptor.demo2;

/**
 * @author: lipan
 * @date: 12:39 上午
 * @description:
 */
public interface KeyPrefix {
    int expireSeconds();    //获取过期时间
    String getPrefix();		//获取前缀
}