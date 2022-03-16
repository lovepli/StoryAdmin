package com.story.storyadmin.basicTest.manystatus.demotest2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lipan
 * @date: 2019-12-10
 * @description: 多态面试题
 *
 * 知识点：构造方法执行得早还是代码初始化更早？
 * 解答：
 * 对象在初始化的时候会先调用构造方法，这是毫无疑问的，只不过，构造方法在执行的时候会把代码初始化块放在构造方法中其他代码之前，所以，先看到了‘代码初始化块’，后看到了‘’构造方法’。”
 *
 * 对于代码初始化来说，它有三个规则：
 * 类实例化的时候执行代码初始化块；
 * 实际上，代码初始化块是放在构造方法中执行的，只不过比较靠前；
 * 代码初始化块里的执行顺序是从前到后的。
 *
 * 总结：代码初始化块是放在构造方法中执行的，只不过比较靠前。
 *
 * class A {
 *
 *     A() {
 *         System.out.println("1A类的构造方法");
 *     }
 *
 *     {
 *         System.out.println("2A类的构造快");
 *     }
 * }
 *
 * 在编辑器中其实是这样执行的：
 * class A {
 *
 *     A() {
 *
 *         {
 *             System.out.println("2A类的构造快");
 *         }
 *
 *         System.out.println("1A类的构造方法");
 *         }
 * }
 *
 * static静态代码块，程序启动过程中只会加载一次，而且是在最开始的时候首先执行
 *
 */
class A {

    A() {
        System.out.println("1A类的构造方法");
    }

    {
        System.out.println("2A类的构造快");
    }

    static {
        System.out.println("3A类的静态块");
    }
}

public class B extends A {

    B() {
        System.out.println("4B类的构造方法");
    }

    {
        System.out.println("5B类的构造快");
    }

    static {
        System.out.println("6B类的静态块");
    }


    public static void main(String[] args) {
        System.out.println("7");
        new B();
        new B();
        System.out.println("8");

        //736215421548 错误！！

        //36 7 21542154 8
    }
}

// 代码初始化的应用：
//代码初始化块用于初始化一些成员变量。对象在创建的时候会执行代码初始化块。
//可以直接通过‘=’操作符对成员变量进行初始化，但通过代码初始化块可以做更多的事情，比如说打印出成员变量初始化后的值。
class C{

    int i=0; //1、直接通过 = 操作符对成员变量进行初始化

    List<String> list; //2、我们可以通过代码初始化块执行一个更复杂的操作，比如为集合填充值
    //“如果只使用‘=’操作符的话，是没办法完成集合初始化的，对吧？‘=’ 后面只能 new 出集合，却没办法填充值，代码初始化就可以完成这项工作。

    {
        list = new ArrayList<>();
        list.add("沉默王二");
        list.add("沉默王三");
    }

    static Map<String,String> map;

    static {
        map=new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
    }

    public static void f(){
        System.out.println(map.get("key1"));
    }

    public void fun2(){
        // 子类中可以直接使用父类的static 静态变量map,项目中的应用如继承父类 logger变量
        System.out.println( map.get("key1")+"--父类方法");
    }




    public static void main(String[] args) {
        C c=new C();
        System.out.println(c.list);//每通过无参构造方法创造的对象，其list属性都充值了两个值
        System.out.println(C.map); //类初始化的时候就加载了Map,比list初始化要早
        c.f();
    }
}

class D extends C {

    //子类独有的方法
    public void fun(){
        // 子类中可以直接使用父类的static 静态变量map,项目中的应用如继承父类 logger变量
        System.out.println( map.get("key1"));
    }

    public void fun2(){
        // 子类中可以直接使用父类的static 静态变量map,项目中的应用如继承父类 logger变量
        System.out.println( map.get("key1")+"--子类方法");
    }

    public static void main(String[] args) {
        C c=new D();
        c.f();
        // c.fun();//不能调用子类独有的方法
        c.fun2();
    }
}