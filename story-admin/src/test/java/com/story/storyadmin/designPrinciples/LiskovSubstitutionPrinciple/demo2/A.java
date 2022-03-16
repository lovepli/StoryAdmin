package com.story.storyadmin.designPrinciples.LiskovSubstitutionPrinciple.demo2;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
//举例说明继承的风险，我们需要完成一个两数相减的功能，由类A来负责。
//运行结果：
//
//100-50=50
//100-80=20
public class A{
    public int func1(int a, int b){
        return a-b;
    }
}

 class Client1{
    public static void main(String[] args){
        A a = new A();
        System.out.println("100-50="+a.func1(100, 50));
        System.out.println("100-80="+a.func1(100, 80));
    }
}

