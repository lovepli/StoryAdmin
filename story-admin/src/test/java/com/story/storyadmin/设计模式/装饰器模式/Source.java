package com.story.storyadmin.设计模式.装饰器模式;

/**
 * @author: lipan
 * @date: 2020-04-24
 * @description: 被装饰者
 */
public class Source implements Sourceable{
    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
