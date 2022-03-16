package com.story.storyadmin.basic.jvm.memoryLeaks;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 59688
 * @date: 2021/9/29
 * @description:
 */
public class EqualTest {
    public static void main(String[] args) {
        Map<User, Integer> map = new HashMap<>();
        for(int i=0; i<100; i++) {
            map.put(new User("", 1), 1);
        }
        System.out.println(map.size() == 1);//输出为false
    }
}

class User{
    public String name;
    public int age;
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}