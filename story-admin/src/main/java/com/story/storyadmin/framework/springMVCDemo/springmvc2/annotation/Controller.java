package com.story.storyadmin.framework.springMVCDemo.springmvc2.annotation;

import java.lang.annotation.*;

/**
 * 模拟springmvc 的@controller注解
 */
@Documented //javadoc
@Target(ElementType.TYPE) //作用于类上
@Retention(RetentionPolicy.RUNTIME) // 限制annotation的生命周期，这里自定义注意显然需要运行时保留
public @interface Controller {
    //作用于该类上的注解有一个value属性，说白了就是controller的名称
    public String value();
}
