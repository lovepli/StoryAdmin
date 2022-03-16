package com.story.storyadmin.framework.springMVCDemo.springmvc2.annotation;


import java.lang.annotation.*;

/**
 * @Repository 持久化Dao层注解
 */
@Documented //javadoc
@Target(ElementType.TYPE) //作用于类上
@Retention(RetentionPolicy.RUNTIME)
public @interface Repository {
    public String value();
}
