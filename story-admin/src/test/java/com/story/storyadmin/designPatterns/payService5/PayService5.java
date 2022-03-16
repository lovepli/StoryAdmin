package com.story.storyadmin.designPatterns.payService5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description: 策略+工厂模式
 *
 * 这种方法也用在代码有业务意义的场景中：
 * 1、策略模式定义了一组算法，将它们一个一个封装起来，使它们可以互换。
 * 2、工厂模式用于封装和管理对象的创建，是一种创建模式
 *
 * 这段代码的关键是 PayStrategyFactory 类，它是一个策略工厂，它定义了一个全局映射，
 * 并将当前实例注册到所有 IPay 实现类中的映射中。
 * 然后使用PayStrategyFactory类根据调用处的代码从地图中获取支付类实例。
 *
 */
//@Service
@Component
public class PayService5 {

    public void toPay(String code) {
        PayStrategyFactory.get(code).pay();
    }
}
