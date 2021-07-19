package com.story.storyadmin.filter.xss;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: 59688
 * @date: 2021/7/12
 * @description: Enable 开关
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CrosXssFilterConfig.class)
public @interface EnableCrosXssFilter {
}


