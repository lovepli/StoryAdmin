package com.story.storyadmin.设计模式.观察者模式.demo;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 具体被观察者：
 */
public class ConcreteSubject extends Subject {
    @Override
    public void notifyObserver() {
        System.out.println("具体目标发生改变...");
        System.out.println("--------------");
        for (Object obs : observers) {
            ((Observer) obs).response();
        }
    }
}
