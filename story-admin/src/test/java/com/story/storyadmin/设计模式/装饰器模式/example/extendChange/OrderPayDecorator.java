package com.story.storyadmin.设计模式.装饰器模式.example.extendChange;

import com.story.storyadmin.设计模式.装饰器模式.example.IOrderPayService;

import java.math.BigDecimal;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 * 新需求，需要把这些请求参数和相应结果进行单独搜集处理，此时为了不影响原有功能，于是我们可以对其进行功能增强。
 */
public class OrderPayDecorator implements IOrderPayService {

    private IOrderPayService orderPayService;

    public OrderPayDecorator(IOrderPayService orderPayService) {
        this.orderPayService = orderPayService;
    }

    @Override
    public String payment(Long orderId, BigDecimal amount) {
        System.out.println("把这个订单信息（发起支付）" + "订单id=" + orderId + "支付金额=" + amount.toString() + " 【发送给MQ】");
        String result = orderPayService.payment(orderId, amount);
        System.out.println("把订单支付结果信息" + result + " 【发送给MQ】");
        return result;
    }
}
