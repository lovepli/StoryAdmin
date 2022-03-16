package com.story.storyadmin.designPrinciples.InterfaceSegregationPrinciple.extendChange1;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description: Dog只实现IEatAnimal和ISwimAnimal接口。
 */
public class Dog2 implements IEatAnimal,ISwimAnimal{
    //吃
    @Override
    public void eat(){
        //小狗吃东西
    }
    //游泳
    @Override
    public void swim(){
        //小狗在游泳
    }
}
