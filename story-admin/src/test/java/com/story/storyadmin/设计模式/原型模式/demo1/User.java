package com.story.storyadmin.设计模式.原型模式.demo1;

import lombok.Data;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 浅克隆
 * 比如，我现在相对用户信息User进行克隆，但是User中有用户地址信息UserAddress属性。
 *
 * 两个对象属性 UserAddress 指向的是同一个地址。
 * 这就是所谓的浅克隆，只是克隆了对象，对于该对象的非基本类型属性，仍指向原来对象的属性所指向的对象的内存地址。
 *
 * 以下是代码的实现：
 */
//用户信息
@Data
public class User implements Cloneable {
    private int age;
    private String name;
    //用户地址信息
    private UserAddress userAddress;

    //getter setter 省略

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
