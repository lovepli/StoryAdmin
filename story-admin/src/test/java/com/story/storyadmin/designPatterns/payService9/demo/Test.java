package com.story.storyadmin.designPatterns.payService9.demo;

/**
 * @author: lipan
 * @date: 2021/8/20
 * @description: 枚举高级特性：https://mp.weixin.qq.com/s/cdfiJ3bJxOIZCrFbbtc99w
 */
public class Test {

    public static void main(String[] args) {
        String type = "CAT";
        String eat = AnimalEnum.valueOf(type).eat();
        System.out.println(eat);
        //吃鱼
    }
}
