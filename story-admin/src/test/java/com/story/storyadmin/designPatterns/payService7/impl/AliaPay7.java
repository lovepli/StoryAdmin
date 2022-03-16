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
public class AliaPay7 implements IPay7 {
    @Override
    public void pay() {
        System.out.println("===发起支付宝支付===");
    }

}