package com.story.storyadmin.designPatterns.payService5;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description: 工厂方法类
 */
public class PayStrategyFactory {

    private static Map<String, IPay5> PAY_REGISTERS = new HashMap<>();

    public static void register(String code, IPay5 iPay) {
        if (null != code && !"".equals(code)) {
            PAY_REGISTERS.put(code, iPay);
        }
    }
    public static IPay5 get(String code) {
        return PAY_REGISTERS.get(code);
    }
}
