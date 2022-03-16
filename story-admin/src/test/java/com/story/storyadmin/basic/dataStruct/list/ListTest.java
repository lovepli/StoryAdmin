package com.story.storyadmin.basic.dataStruct.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:  list查找元素需要正确覆写equals方法1！！
 */
public class ListTest {


    public static void fun1() {
        //为什么要重写equals方法？
        //在List中使用contains()和indexOf()方法可以判断元素是否存在
        List<String> list2 = new ArrayList<>();
        list2.add("A");
        list2.add("B");
        list2.add("c");
        list2.add(new String("D"));

        String e=new String("E");
        list2.add(e);


        // list2.contains("c");
        System.out.println(list2.contains("c")); //true

        String ee=new String("E");
        System.out.println(list2.contains(ee)); //true ?? e与ee比较，从底层代码来看实际上是两个不同的对象做比较 调用的是equals()方法
        System.out.println("ee和e进行比较，结果为："+ee.equals(e));

        // list2.contains(new String("D"));
        System.out.println(list2.contains(new String("D"))); //true

        // 为什么这里的两个new String("D")不是同一个对象，但是也返回的是true？？
        //因为在List内部使用的是equals()    if (o.equals(elementData[i]))  方法判断两个元素是否相等的,而不是使用 == 做比较
        // 这里又因为两个元素e和ee的数据类型都是String，所以equals比较的结果是字符串相等所以返回true

        //所以要正确调用contains()/indexOf()方法，放入的实例要正确实现equals()方法

    }

    public static void fun2() {

        List<Student> list = new ArrayList<>();
        list.add(new Student("Bob", 11));
        list.add(new Student("Alice", 12));
        list.add(new Student("jack", 13));
        System.out.println(list);
        //重写equals方法
        System.out.println(list.contains(new Student("Bob",11))); //false
        // 这里两个Student对像 实际上执行的是Object的equals()方法
        // public boolean equals(Object obj) {
        //        return (this == obj);
        //    }
        //因为==判断的是两个对象的逻辑地址是否相等，所以结果为false

        //但是根据我们实际情况判断现实的生活中，名字和年龄相同的学生就是同一个对象，所以必须重写equals()方法
        // 重写equals方法后返回true
    }

    public static void main(String[] args) {

  //  fun1();
   fun2();
    }
}
