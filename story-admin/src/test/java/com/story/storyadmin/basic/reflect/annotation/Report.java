package com.story.storyadmin.basic.reflect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 *
 * 定义注解
 * 使用@interface定义注解(Annotation)。
 * 注解的参数类似无参方法
 * 可设定一个默认值
 * 把最常用的参数命名为value（推荐）
 *
 * 使用元注解定义注解:
 *  @Target
 *  @Retention ：定义生命周期 通常自定义的注解都是定义在运行期Runtime  RetentionPolicy.RUNTIME
 *  @Repeatable
 *  @Inherited
 * 定义Annotation的步骤:
 * 1. 用@interface定义注解
 * 2. 用元注解(meta annotation)配置注解
 *   Target:必须设置
 *   Retention:一般设置为RUNTIME
 *   通常不必写@Inherited, @Repeatable等等
 * 3. 定义注解参数和默认值
 *
 * 如何读取RUNTIME类型的注解？
 * Annotation也是calss
 * 所有Annotation继承自java.lang.Annotation
 * 使用反射API
 */
//@Target(ElementType.METHOD)  //Report注解只能用于方法
@Target({
        ElementType.METHOD,ElementType.FIELD   //Report注解可以用在方法和字段上
})
@Retention(RetentionPolicy.RUNTIME)  //代码运行是才能读取到该注解
public @interface Report {

    int type() default 0;

    String level() default "info";

    String value() default "";
}
