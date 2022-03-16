package com.story.storyadmin.designPatterns.payService8.impl;

import com.story.storyadmin.designPatterns.payService8.Strategy8;
import com.story.storyadmin.designPatterns.payService8.UserType;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class OrdinaryStrategy implements Strategy8 {

    @Override
    public double compute(long money) {
        System.out.println("普通会员 不打折");
        return money;
    }

    // 添加 type 返回
    @Override
    public int getType() {
        return UserType.SILVER_NOMAL.getValue();
    }
}
