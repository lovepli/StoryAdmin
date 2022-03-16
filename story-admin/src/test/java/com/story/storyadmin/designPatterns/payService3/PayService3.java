package com.story.storyadmin.designPatterns.payService3;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description: 动态拼接名称 该方法主要针对 code 是有业务含义的场景。
 * 我们可以看到支付 bean 的名称是由代码和后缀组成的，例如：aliaPay、weixinPay 和 jingDongPay。
 *
 *  这个在命名支付类的时候需要特别注意，上一段要和代码保持一致。
 *
 *  被调用的支付类的实例直接从ApplicationContext实例中获取。默认情况下，bean 是单例并放置在内存中的映射中，因此不会出现性能问题。
 *
 *  特别是这个方法实现了ApplicationContextAware接口，与上面的ApplicationListener接口不同。我想告诉你，获取 ApplicationContext 实例的方法不止一种。
 */

//@Service
@Component
public class PayService3 implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private static final String SUFFIX = "Pay3";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    public void toPay(String payCode) {
        ((IPay3) applicationContext.getBean(getBeanName(payCode))).pay();
    }
    public String getBeanName(String payCode) {
        return payCode + SUFFIX;
    }
}
