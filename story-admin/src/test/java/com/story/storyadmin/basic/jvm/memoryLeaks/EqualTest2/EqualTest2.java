package com.story.storyadmin.basic.jvm.memoryLeaks.EqualTest2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 59688
 * @date: 2021/9/29
 * @description:
 */
public class EqualTest2 {
        public static void main(String[] args) {
            Map<User, Integer> map = new HashMap<>();
            for(int i=0; i<100; i++) {
                map.put(new User(""), 1);
            }
            System.out.println(map.size() == 1);//输出为false
        }
}

class User{
    public static String name;
    public User(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return User.name.equals(name);
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

