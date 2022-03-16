package com.story.storyadmin.designPrinciples.InterfaceSegregationPrinciple.extendChange1;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 * 由上面代码可以看出，Bird类的swim（）方法可能只能空着，Dog类的fly（）方法显然是不可能的。这时候，我们针对不同动物的行为来设计不同的接口，分别设计IEatAnimal、IFlyAnimal和ISwimAnimal接口。
 */
public interface IEatAnimal{
    //吃
    void eat();
}
