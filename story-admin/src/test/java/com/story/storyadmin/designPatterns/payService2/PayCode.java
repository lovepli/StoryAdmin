package com.story.storyadmin.designPatterns.payService2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description: 方式1、使用注解
 * 之所以在代码中使用code来决定使用哪个支付类，是因为code和支付类之间没有绑定关系。如果存在绑定关系，则无需判断。
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PayCode {
    String value();
    String name();
}