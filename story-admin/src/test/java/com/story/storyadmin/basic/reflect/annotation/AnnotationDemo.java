package com.story.storyadmin.basic.reflect.annotation;



/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 *
 * 注解
 * 注解(Annotation)是放在Java源码的类、方法、字段、参数前的一种标签。
 * 注解本身对代码逻辑没有任何影响，如何使用注解由工具决定。
 *
 * 编译器可以使用的注解:
 * @Override : 让编译器检查该方法是否正确地实现了覆写
 * @Deprecated ：告诉编译器该方法已经被标记为"作废" ，再其他地方引用将会出现编译警告
 * @SuppressWarnings ；告诉编译器在这个方法内部，如果又一些代码出现警告，会忽略这些警告
 *
 *   注解可以定义配置参数和默认值。
 */
public class AnnotationDemo {

    @SuppressWarnings({"unused","rawtypes"})
    public static void main(String[] args) {
        Class cls = AnnotationDemo.class;
        hello();//已作废


    }

    @Deprecated
    public static void hello() {
        System.out.println("Hello!");
    }

    @Override
    public String toString() {
        return "Hello world!";

    }
}
