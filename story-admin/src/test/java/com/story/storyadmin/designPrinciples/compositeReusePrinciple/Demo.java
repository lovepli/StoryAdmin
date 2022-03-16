package com.story.storyadmin.designPrinciples.compositeReusePrinciple;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class Demo {
    /**
     * 合成复用原则
     * 定义
     * 合成复用原则（Composite/Aggregate Reuse Principle，CARP）指尽量使用对象组合（has-a）或对象聚合（contanis-a）的方式实现代码复用，而不是用继承关系达到代码复用的目的。
     *
     * 合成复用原则可以使系统更加灵活，降低类与类之间的耦合度，一个类的变化对其他类造成的影响相对较小。继承，又被称为白箱复用，相当于把所有实现细节暴露给子类。
     *
     * 组合/聚合又被称为黑箱复用，对类以外的对象是无法获取实现细节的。我们要根据具体的业务场景来做代码设计，其实也都需要遵循面向对象编程（Object OrientedProgramming，OOP）模型。
     */
}
