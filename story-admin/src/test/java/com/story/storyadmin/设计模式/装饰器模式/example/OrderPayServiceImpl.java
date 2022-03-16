package com.story.storyadmin.设计模式.装饰器模式.example;

import java.math.BigDecimal;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class OrderPayServiceImpl implements IOrderPayService {

    @Override
    public String payment(Long orderId, BigDecimal amount) {
        //先调用余额查询是否足够
        System.out.println("发起支付，订单号：" + orderId + "， 支付金额：" + amount.toString());
        //调用支付系统
        String result = "订单id=" + orderId + "支付完成";
        System.out.println("支付结果：" + result);
        return result;
    }
}
