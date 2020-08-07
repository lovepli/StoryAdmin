package com.story.storyadmin.config.mongo;

import java.lang.annotation.*;

/**
 * 系统日志注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
public @interface SysLogAnnotation {


}
