package com.story.storyadmin.converter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: 59688
 * @date: 2021/7/12
 * @description: 自定义全局转换器，将日期字符串转换为Date类型数据
 */
//@Configuration // 自动注入
@ConditionalOnWebApplication // 配置手动开关,使用注解了才启动注入
public class StringToDateConverterConfig extends WebMvcConfigurerAdapter {
    /**
     * 将新定义的类型转换器注入到 spring 容器中
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter());
    }

}
