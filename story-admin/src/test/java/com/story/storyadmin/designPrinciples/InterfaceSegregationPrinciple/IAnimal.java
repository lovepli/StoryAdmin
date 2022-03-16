package com.story.storyadmin.designPrinciples.InterfaceSegregationPrinciple;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 * 我们来写一个动物行为的抽象。
 *
 * IAnimal接口的代码如下：
 */
public interface IAnimal{
    //吃
    void eat();
    //飞
    void fly();
    //游泳
    void swim();
}
