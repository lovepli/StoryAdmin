package com.story.storyadmin.designPatterns.payService.impl;

import com.story.storyadmin.designPatterns.payService.IPay;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 */
@Service
@Component
public class WeixinPay implements IPay {
    @Override
    public void pay() {
        System.out.println("===发起微信支付===");
    }
}
