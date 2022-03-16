package com.story.storyadmin.设计模式.门面模式;

/**
 * @author: lipan
 * @date: 2021/8/22
 * @description:
 */
public class Client {
    public static void main(String[] args) {
        ServiceA serviceA=new ServiceA();
        ServiceB serviceB=new ServiceB();
        ServiceC serviceC=new ServiceC();

        serviceA.doA();
        serviceB.doB();
        serviceC.doC();
    }
}
