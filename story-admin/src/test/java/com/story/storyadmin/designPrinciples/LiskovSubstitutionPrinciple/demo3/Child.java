package com.story.storyadmin.designPrinciples.LiskovSubstitutionPrinciple.demo3;

import java.util.Map;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class Child extends Base {
    //当子类的方法重载父类的方法时，方法的前置条件(即方法的输入/入参)要比父类方法的输入参数更加宽松。
    public void method(Map map) {
        System.out.println("子类HashMap入参方法被执行");
    }
}

