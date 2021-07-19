package com.story.storyadmin.hash;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: lipan
 * @date: 2020-05-07
 * @description:
 *
 *      HashMap 遍历从大的方向来说，可分为以下 4 类：
 *      迭代器（Iterator）方式遍历；
 *      For Each 方式遍历；
 *      Lambda 表达式遍历（JDK 1.8+）;
 *      Streams API 遍历（JDK 1.8+）。
 *
 *      但每种类型下又有不同的实现方式，因此具体的遍历方式又可以分为以下 7 种：
 *
 *      使用迭代器（Iterator）EntrySet 的方式进行遍历；
 *      使用迭代器（Iterator）KeySet 的方式进行遍历；
 *      使用 For Each EntrySet 的方式进行遍历；
 *      使用 For Each KeySet 的方式进行遍历；
 *      使用 Lambda 表达式的方式进行遍历；
 *      使用 Streams API 单线程的方式进行遍历；
 *      使用 Streams API 多线程的方式进行遍历。
 *

 */
public class HashMapTest1 {

    /**
     * 1.迭代器 EntrySet
     */
    public static void fun1() {
        // 创建并赋值 HashMap
        Map<Integer, String> map = new HashMap();
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java中文社群");
        // 遍历
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    /**
     * 2.迭代器 KeySet
     */
    public static void fun2() {
        // 创建并赋值 HashMap
        Map<Integer, String> map = new HashMap();
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java中文社群");
        // 遍历
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            System.out.println(key);
            System.out.println(map.get(key));
        }
    }

    /**
     * 3.ForEach EntrySet
     */
    public static void fun3() {
        // 创建并赋值 HashMap
        Map<Integer, String> map = new HashMap();
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java中文社群");
        // 遍历
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    /**
     * 4.ForEach KeySet
     */
    public static void fun4() {
        // 创建并赋值 HashMap
        Map<Integer, String> map = new HashMap();
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java中文社群");
        // 遍历
        for (Integer key : map.keySet()) {
            System.out.println(key);
            System.out.println(map.get(key));
        }
    }

    /**
     * 5.Lambda
     */
    public static void fun5() {
        // 创建并赋值 HashMap
        Map<Integer, String> map = new HashMap();
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java中文社群");
        // 遍历
        map.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });
    }

    /**
     * 6.Streams API 单线程
     */
    public static void fun6() {
        // 创建并赋值 HashMap
        Map<Integer, String> map = new HashMap();
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java中文社群");
        // 遍历
        map.entrySet().stream().forEach((entry) -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });
    }

    /**
     * 7.Streams API 多线程
     */
    public static void fun7() {
// 创建并赋值 HashMap
        Map<Integer, String> map = new HashMap();
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java中文社群");
        // 遍历
        map.entrySet().parallelStream().forEach((entry) -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });
    }

    public static void main(String[] args) {

        /**
         * 本文我们讲了 HashMap 4 种遍历方式：迭代器、for、lambda、stream，以及具体的 7 种遍历方法，综合性能和安全性来看，我们应该尽量使用迭代器（Iterator）来遍历 EntrySet 的遍历方式来操作 Map 集合，这样就会既安全又高效了。
         */
    }
}
