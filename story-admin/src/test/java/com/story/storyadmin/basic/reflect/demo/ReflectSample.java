package com.story.storyadmin.basic.reflect.demo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射就是将Java中的各种成分映射成一个一个的Java对象，像这里的映射成 Method Field Class
 */
public class ReflectSample {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {

        Class rc = Class.forName("reflect.Robot");  //获取Robot对象
        Robot robot = (Robot) rc.newInstance();//创建的rc的实例对象，因为返回值是一个泛型，所以强转

        System.out.println("Class name is " + rc.getName());
        //通过反射调用rc对象的throwHello()方法
        Method getHello = rc.getDeclaredMethod("throwHello", String.class); //getDeclaredMethod()d方法：获取对象的所有方法，包括私有的和共有的，但是不能获取继承的以及所实现的接口的方法，
        getHello.setAccessible(true);  //由于该方法为private  ,所以设置为true
        Object str = getHello.invoke(robot, "Bob");  //传递参数并且接收返回值 这里我们用Object来接
        System.out.println("getHello result is " + str);//打印输出方法的返回值

        Method sayHi = rc.getMethod("sayHi", String.class);//getMethod()只能获取这个类的public方法 以及继承的和接口的方法
        sayHi.invoke(robot, "Welcome");

        Field name = rc.getDeclaredField("name");//getDeclaredField()这个方法的作用:获取对象的私有属性
        name.setAccessible(true);
        name.set(robot, "Alice");  //给属性name设置值
        sayHi.invoke(robot, "Welcome");

        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));

    }
}
