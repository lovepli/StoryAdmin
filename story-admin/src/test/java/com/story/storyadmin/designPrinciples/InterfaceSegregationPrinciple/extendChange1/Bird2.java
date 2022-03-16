package com.story.storyadmin.designPrinciples.InterfaceSegregationPrinciple.extendChange1;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description: Bird只实现IEatAnimal和IFlyAnimal接口。
 */
public class Bird2 implements IEatAnimal,IFlyAnimal{
    //吃
    @Override
    public void eat() {
     //小鸟吃东西
    }

    //飞
    @Override
    public void fly() {
     // 小鸟飞
    }
}
