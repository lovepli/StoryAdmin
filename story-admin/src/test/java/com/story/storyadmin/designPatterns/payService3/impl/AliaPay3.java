package com.story.storyadmin.designPatterns.payService3.impl;

import com.story.storyadmin.designPatterns.payService.IPay;
import com.story.storyadmin.designPatterns.payService3.IPay3;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 */

//@Service
@Component
public class AliaPay3 implements IPay3 {
    @Override
    public void pay() {
        System.out.println("===发起支付宝支付===");
    }

}
