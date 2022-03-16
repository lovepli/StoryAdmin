package com.story.storyadmin.basic.jvm.memoryLeaks;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 59688
 * @date: 2021/9/29
 * @description: 1、大量使用静态变量
 * 静态变量的生命周期与程序一致。因此常驻内存。
 *
 */
public class StaticTest {
    public static List<Integer> list = new ArrayList<>();
    public void populateList() {
        for (int i = 0; i < 10000000; i++) {
            list.add((int)Math.random());
        }
        System.out.println("running......");
    }
    public static void main(String[] args) {
        System.out.println("before......");
        new StaticTest().populateList();
        System.out.println("after......");
    }
}
