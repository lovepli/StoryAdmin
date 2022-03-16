package com.story.storyadmin.designPatterns.payService7.impl;

import com.story.storyadmin.designPatterns.payService7.IPay7;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 */
@Service
@Component
public class WeixinPay7 implements IPay7 {
    @Override
    public void pay() {
        System.out.println("===发起微信支付===");
    }
}
