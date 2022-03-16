package com.story.storyadmin.designPatterns.vipService.impl;

import com.story.storyadmin.designPatterns.vipService.VipRechargeStrategy;
import org.springframework.stereotype.Component;

/**
 * @author: lipan
 * @date: 2021/8/17
 * @description:
 */
@Component
public class OneMonthVipStrategy implements VipRechargeStrategy {
    @Override
    public void recharge(String priceCode) {
        System.out.println("充值一个月wwe体育会员到账");
    }
}
