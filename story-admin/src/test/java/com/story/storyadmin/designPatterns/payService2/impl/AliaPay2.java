package com.story.storyadmin.designPatterns.payService2.impl;

import com.story.storyadmin.designPatterns.payService2.IPay2;
import com.story.storyadmin.designPatterns.payService2.PayCode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 */


@PayCode(value = "alia", name = "支付宝支付")
//@Service
@Component
public class AliaPay2 implements IPay2 {
    @Override
    public void pay() {
        System.out.println("===发起支付宝支付===");
    }

}