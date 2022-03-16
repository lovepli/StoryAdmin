package com.story.storyadmin.basic.juc.functionInterface;

/**
 * @author: lipan
 * @date: 2019-06-27
 * @description:
 *
 *1） default 方法
 * 接口里在 java8 后容许有接口的实现， default 方法默认实现 (default方法可以有多个， 没有限制)
 * 2） 静态方法实现
 * 静态方法实现： 接口新增 （注意静态的叫类方法，能用 foo 去调吗？ 要改成 Foo  ，方法可以有多个， 没有限制）
 *
 */

@FunctionalInterface
interface Foo{
    // public void sayHello() ;
    // public void say886() ;

    public int add(int x, int y); //普通接口方法

    default int div(int x, int y) { //default方法
        return x / y;
    }

    public static int sub(int x, int y) {  //静态类方法
        return x - y;
    }
}