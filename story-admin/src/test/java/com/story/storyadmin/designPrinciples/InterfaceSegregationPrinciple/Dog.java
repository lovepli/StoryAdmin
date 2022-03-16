package com.story.storyadmin.designPrinciples.InterfaceSegregationPrinciple;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class Dog implements IAnimal{
    //吃
    @Override
    public void eat(){
        //小狗吃东西
    }
    //飞
    @Override
    public void fly(){
        //空着，因为小狗不会飞
    }
    //游泳
    public void swim(){
        //小狗在游泳
    }
}
