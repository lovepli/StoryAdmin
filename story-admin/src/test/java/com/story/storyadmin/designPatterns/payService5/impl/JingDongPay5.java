package com.story.storyadmin.designPatterns.payService5.impl;


import com.story.storyadmin.designPatterns.payService5.IPay5;
import com.story.storyadmin.designPatterns.payService5.PayStrategyFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 */
//@Service
@Component
public class JingDongPay5 implements IPay5 {

    @PostConstruct
    public void init() {
        PayStrategyFactory.register("jingDongPay5", this);
    }

    @Override
    public void pay() {
        System.out.println("===发起京东支付===");
    }



}
