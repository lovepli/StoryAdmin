package com.story.storyadmin.enumtest.demo2;

/**
 * @author: lipan
 * @date: 2021/8/20
 * @description: 用枚举实现单列
 */
public enum  EnumSingleton {
    INSTANCE;

    public  EnumSingleton getInstance() {
        return INSTANCE;
    }
}
