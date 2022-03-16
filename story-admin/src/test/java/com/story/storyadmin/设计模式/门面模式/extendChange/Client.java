package com.story.storyadmin.设计模式.门面模式.extendChange;

/**
 * @author: lipan
 * @date: 2021/8/22
 * @description:
 */
public class Client {
    public static void main(String[] args) {
        //轻轻松松的搞定，只需要创建门面这个对象即可
        Facade facade=new Facade();
        facade.doA();
        facade.doB();
        facade.doC();
    }
}
