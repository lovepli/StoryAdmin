package com.story.storyadmin.basic.reflect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 */


@Target({ElementType.FIELD,ElementType.PARAMETER})  //该注解可以使用在属性和参数上
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {

}
