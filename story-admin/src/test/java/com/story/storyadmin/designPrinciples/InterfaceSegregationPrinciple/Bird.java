package com.story.storyadmin.designPrinciples.InterfaceSegregationPrinciple;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class Bird implements IAnimal {
    //吃
    @Override
    public void eat() {
        //小鸟吃东西
    }

    //飞
    @Override
    public void fly() {
        //小鸟在飞
    }

    //游泳
    public void swim() {
        //空着，因为小鸟不游泳
    }
}

