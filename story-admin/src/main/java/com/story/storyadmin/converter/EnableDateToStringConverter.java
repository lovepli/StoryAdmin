package com.story.storyadmin.converter;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: 59688
 * @date: 2021/7/12
 * @description:  是否开启类型转换开关，全局开启则需要再springboot启动类上添加注解@EnableDateToStringConverter，即可以全局启动
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DateToStringConverterConfig.class)
public @interface EnableDateToStringConverter {
}
