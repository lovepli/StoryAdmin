package com.story.storyadmin.enumtest.demo3;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * @author: lipan
 * @date: 2021/8/20
 * @description: EnumMap
 * 如果你需要存储key-value格式的数据，并且这个key来源于一个枚举类，那么使用EnumMap而不是HashMap。EnumMap拥有更优良的性能。
 *
 */
public class Test {

    public static void main(String[] args) {
        //使用
        EnumMap<TestEnum, String> enumMap = new EnumMap<>(TestEnum.class);
        enumMap.put(TestEnum.TEACHER,"教书");
        enumMap.put(TestEnum.STUDENT,"学习");
        enumMap.put(TestEnum.PARENT,"培育小孩");

        String result = enumMap.get(TestEnum.PARENT);
        System.out.println(result);
        //我们可以通过EnumMap给枚举的成员属性赋予特定行为。如上代码，result = "培育小孩"。

        //我们定义了枚举TestEnum，如何获取所有成员属性？这是可以使用EnumSet。
        EnumSet enumSet = EnumSet.allOf(TestEnum.class);
        System.out.println(enumSet);
        //控制台打印
        //
        //[TEACHER, STUDENT, PARENT]
        //与HashSet相比，EnumSet性能更优良。
    }
}
