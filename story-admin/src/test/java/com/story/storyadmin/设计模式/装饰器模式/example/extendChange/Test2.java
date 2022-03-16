package com.story.storyadmin.设计模式.装饰器模式.example.extendChange;

import com.story.storyadmin.设计模式.装饰器模式.example.IOrderPayService;
import com.story.storyadmin.设计模式.装饰器模式.example.OrderPayServiceImpl;

import java.math.BigDecimal;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class Test2 {
    public static void main(String[] args) {
        IOrderPayService orderPayService =new OrderPayDecorator(new OrderPayServiceImpl());
        orderPayService.payment(10001L,new BigDecimal("5000"));
    }
    //把这个订单信息（发起支付）订单id=10001支付金额=5000 【发送给MQ】
    //发起支付，订单号：10001， 支付金额：5000
    //支付结果：订单id=10001支付完成
    //把订单支付结果信息订单id=10001支付完成 【发送给MQ】
}
