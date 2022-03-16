package com.story.storyadmin.framework.springMVCDemo.springmvc2.annotation;

import java.lang.annotation.*;

/**
 *
 * @RequestMapping 提供URL地址处理映射
 */
@Documented
@Target({ElementType.METHOD,ElementType.TYPE}) //可作用于类和方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    public String value();
}
