package com.story.storyadmin.设计模式.观察者模式.demo;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description:
 */
public class ObserveTest {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer obs1 = new ConcreteObserver1();
        Observer obs2 = new ConcreteObserver2();
        subject.add(obs1);
        subject.add(obs2);
        subject.notifyObserver();
    }
}