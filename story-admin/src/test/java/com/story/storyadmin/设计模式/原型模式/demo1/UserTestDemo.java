package com.story.storyadmin.设计模式.原型模式.demo1;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description:
 *///测试
public class UserTestDemo {
    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setAge(20);
        user.setName("田维常");
        UserAddress userAddress = new UserAddress("贵州", "梵净山");
        user.setUserAddress(userAddress);

        User clone = (User) user.clone();

        System.out.println("克隆前后UserAddress比较：" + (user.getUserAddress() == clone.getUserAddress()));
    }
}
