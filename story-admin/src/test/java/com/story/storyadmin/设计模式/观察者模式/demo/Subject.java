package com.story.storyadmin.设计模式.观察者模式.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 被观察者（目标）进行抽象：
 */

//抽象目标
public abstract class Subject {
    protected List<Observer> observers = new ArrayList<Observer>();
    //增加观察者方法
    public void add(Observer observer) {
        observers.add(observer);
    }
    //删除观察者方法
    public void remove(Observer observer) {
        observers.remove(observer);
    }
    public abstract void notifyObserver(); //通知观察者方法
}
