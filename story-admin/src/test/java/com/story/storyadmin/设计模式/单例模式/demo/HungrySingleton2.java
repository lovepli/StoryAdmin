package com.story.storyadmin.设计模式.单例模式.demo;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 饿汉式
 * 从名字上就能看出，饿汉：饿了就得先吃饱，所以，一开始就搞定了。
 *
 * 饿汉式主要是使用了static，饿汉式也有两种写法，但本质可以理解为是一样的。
 *
 * 饿汉式有个致命的缺点：浪费空间，不需要也实例化。如果是成千上万个，也这么玩，想想有多恐怖。
 *
 * 于是，就会想到，能不能在使用的时候在实例化，从而引出了懒汉式。
 */
public class HungrySingleton2 {

    private static final HungrySingleton2 INSTANCE;
    static {
        INSTANCE=new HungrySingleton2();
    }
    //    private static final HungrySingleton INSTANCE=new HungrySingleton();
    private HungrySingleton2(){

    }

    public static HungrySingleton2 getInstance(){
        return INSTANCE;
    }
}