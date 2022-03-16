package com.story.storyadmin.设计模式.单例模式.demo;

/**
 * @author: lipan
 * @date: 2021年08月26日 12:07 下午
 * @description:
 * 个人觉得最简单，类在加载时就 new出来了。
 * 优点：线程安全、逻辑简单 缺点：如果这种写法被大量使用的话，导致内存开销增加
 */
public class HungrySingleton {

    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return instance;
    }
}
