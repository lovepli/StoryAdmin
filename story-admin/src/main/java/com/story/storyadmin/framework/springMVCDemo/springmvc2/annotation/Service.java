package com.story.storyadmin.framework.springMVCDemo.springmvc2.annotation;


import java.lang.annotation.*;

/**
 * @Service 业务层注解
 */
@Documented //javadoc
@Target(ElementType.TYPE) //作用于类上
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

    public String value();
}
