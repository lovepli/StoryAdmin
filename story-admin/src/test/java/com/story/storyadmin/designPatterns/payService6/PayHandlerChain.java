package com.story.storyadmin.designPatterns.payService6;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description: 责任链模式
 * 这种方法对于在代码重构过程中消除 if...else 非常有效。
 * 责任链模式：将请求的处理对象像长链一样组合成对象链。请求不知道具体的执行请求是哪个对象，从而实现了请求与处理对象的解耦。
 *
 * 常用的filter和spring aop使用的是责任链模型。
 * 这段代码的关键是每个PayHandler子类定义了下一个需要执行的PayHandler子类，形成链式调用，这个链式结构是通过PayHandlerChain组装起来的
 */
//@Service
@Component
public class PayHandlerChain implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private PayHandler header;

    public void handlePay(String code) {
        header.pay(code);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, PayHandler> beansOfTypeMap = applicationContext.getBeansOfType(PayHandler.class);
        if (beansOfTypeMap == null || beansOfTypeMap.size() == 0) {
            return;
        }
        List<PayHandler> handlers = beansOfTypeMap.values().stream().collect(Collectors.toList());
        for (int i = 0; i < handlers.size(); i++) {
            PayHandler payHandler = handlers.get(i);
            if (i != handlers.size() - 1) {
                payHandler.setNext(handlers.get(i + 1));
            }
        }
        header = handlers.get(0);
    }
}
