package com.story.storyadmin.designPatterns.payService4.impl;

import com.story.storyadmin.designPatterns.payService3.IPay3;
import com.story.storyadmin.designPatterns.payService4.IPay4;
import org.springframework.stereotype.Service;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 */
@Service
public class JingDongPay4 implements IPay4 {
    @Override
    public void pay() {
        System.out.println("===发起京东支付===");
    }

    @Override
    public boolean support(String code) {
        return "jingdong".equals(code);
    }

}
