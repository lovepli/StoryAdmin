package com.story.storyadmin.designPatterns.payService4;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description: 模板方法判断
 *  * 其实我们可以通过定义一个接口或者抽象类来做到这一点，其中有一个支持方法来判断参数传递的代码是否可以自己处理，如果可以处理，支付逻辑 用来。
 *  * 每个支付类都有一个 support 方法，判断传过来的 code 是否和自己定义的相等
 * 在这段代码中，实现了IPay接口的支付类实例被初始化为一个列表集，调用支付接口时，通过列表集循环返回。
 * 如果代码与自己定义的相同，则调用当前支付类实例的支付方法。
 */

//@Service
@Component
public class PayService4 implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private List<IPay4> payList = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (payList == null) {
            payList = new ArrayList<>();
            Map<String, IPay4> beansOfType = applicationContext.getBeansOfType(IPay4.class);
            beansOfType.forEach((key, value) -> payList.add(value));
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    public void toPay(String code) {
        for (IPay4 iPay : payList) {
            if (iPay.support(code)) {
                iPay.pay();
            }
        }
    }
}
