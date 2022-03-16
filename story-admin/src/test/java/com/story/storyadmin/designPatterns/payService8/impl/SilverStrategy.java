package com.story.storyadmin.designPatterns.payService8.impl;

import com.story.storyadmin.designPatterns.payService8.Strategy8;
import com.story.storyadmin.designPatterns.payService8.UserType;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class SilverStrategy implements Strategy8 {

    @Override
    public double compute(long money) {

        System.out.println("白银会员 优惠50元");
        return money - 50;
    }

    // type 返回
    @Override
    public int getType() {
        return UserType.SILVER_VIP.getValue();
    }
}