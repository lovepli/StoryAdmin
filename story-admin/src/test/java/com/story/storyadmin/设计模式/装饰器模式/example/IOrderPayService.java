package com.story.storyadmin.设计模式.装饰器模式.example;

import java.math.BigDecimal;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 * 在实际开发中，都会存在系统与系统之间的调用，假如说我们现在有个支付功能，现在一切都是没问题的，
 * 但是 我们此时需要对发起支付前的请求参数和支付后的相应参数。进行统一处理，原功能不变，
 * 只是在原功能上做了一点扩展（增强）。
 */
public interface IOrderPayService {
    String payment(Long orderId, BigDecimal amount);
}
