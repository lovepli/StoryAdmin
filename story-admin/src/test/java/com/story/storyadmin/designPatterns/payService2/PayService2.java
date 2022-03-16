package com.story.storyadmin.designPatterns.payService2;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 * PayService2 类实现了ApplicationListener 接口，这样就可以在onApplicationEvent 方法中获取ApplicationContext 的一个实例。
 *
 *  然后我们获取 PayCode 注释类并将其放入映射中。  map中key为PayCode注解中定义的value，与code参数一致，value为支付类的一个实例。
 *
 *  这样每次都可以直接通过代码获取支付类实例，而不用if...else判断。
 *
 *  如果要添加新的支付方式，只需在支付类上添加一个PayCode注解，定义一个新的code即可。
 *
 * 注：这种方式的代码可以没有商业意义，可以是纯数字，不需要重复。
 */

//@Service
@Component
public class PayService2 implements ApplicationListener<ContextRefreshedEvent> {

    private static Map<String, IPay2> payMap = null;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(PayCode.class);
        if (beansWithAnnotation != null) {
            payMap = new HashMap<>();
            beansWithAnnotation.forEach((key, value) -> {
                String bizType = value.getClass().getAnnotation(PayCode.class).value();
                payMap.put(bizType, (IPay2) value);
            });
        }
    }

    public void toPay(String code) {
        payMap.get(code).pay();
    }
}

