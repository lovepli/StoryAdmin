package com.story.storyadmin.basic.juc.functionInterface;

/**
 * @author: lipan
 * @date: 2019-06-27
 * @description:
 * lambda表达式：
 *
 *  1 拷贝小括号(形参列表)， 写死右箭头 ->， 落地大括号 {方法实现}
 *  * * 2 有且只有一个 public 方法@FunctionalInterface 注解增强定义
 *  * * 3 default 方法默认实现
 *  * * 4 静态方法实现
 */
public class LambdaDemo
{
    public static void main(String[] args){

//        Foo foo = new Foo() {
//            @Override
//            public void sayHello() {
//                System.out.println("Hello!!");
//            }
//
//            @Override
//            public void say886() {
//                // TODO Auto-generated method stub
//
//            }
//        };
//        foo.sayHello();
//        System.out.println("============");
//        foo = () -> {
//            System.out.println("Hello!! lambda !!");
//        };
//        foo.sayHello();


        Foo foo = (x,y)->{
            System.out.println("Hello!! lambda !!");
            return x+y;
        };
        int result = foo.add(3,5);
        System.out.println("******result="+result);
        System.out.println("******result div="+foo.div(10, 2));
        System.out.println("******result sub="+Foo.sub(10, 2));

    }
}