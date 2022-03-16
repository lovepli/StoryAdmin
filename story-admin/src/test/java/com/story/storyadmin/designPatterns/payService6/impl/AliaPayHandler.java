package com.story.storyadmin.designPatterns.payService6.impl;

import com.story.storyadmin.designPatterns.payService6.PayHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 */
//@Service
@Component
public class AliaPayHandler extends PayHandler {

    @Override
    public void pay(String code) {
        if ("alia".equals(code)) {
            System.out.println("===发起支付宝支付===");
        } else {
            getNext().pay(code);
        }
    }
}
