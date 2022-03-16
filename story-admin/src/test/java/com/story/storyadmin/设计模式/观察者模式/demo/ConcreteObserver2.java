package com.story.storyadmin.设计模式.观察者模式.demo;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description:
 */
//观察者2
public class ConcreteObserver2 implements Observer {
    @Override
    public void response() {
        System.out.println("具体观察者2作出反应！");
    }
}
