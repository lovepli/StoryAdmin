package com.story.storyadmin.framework.iocAndAop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname Autowired
 * @Description 自动注入注解
 * @Date 2020/8/6 14:28
 * @Created by zhangtianci
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
    String value() default "";
}
