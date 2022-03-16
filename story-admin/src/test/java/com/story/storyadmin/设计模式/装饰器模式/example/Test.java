package com.story.storyadmin.设计模式.装饰器模式.example;

import java.math.BigDecimal;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 * 整个过程，大家有没有发现，我们并没动原有的代码，仅仅只是做了功能增强。
 *
 * 装饰器模式在新项目中基本上不会用到，通常都是在老项目中使用，因为已有的功能不变，只是做了一些功能增强。
 */
public class Test {
    public static void main(String[] args) {
        IOrderPayService orderPayService = new OrderPayServiceImpl();
        orderPayService.payment(10001L,new BigDecimal("5000"));
    }
    //发起支付，订单号：10001， 支付金额：5000
    //支付结果：订单id=10001支付完成
}
