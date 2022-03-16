package com.story.storyadmin.basic.reflect.annotation.example2;

import java.lang.annotation.*;

/**
 * @author: lipan
 * @date: 2019-06-13
 * @description:
 * desc:自定义权限管理注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
    String authorities() default "我是默认值";
}
