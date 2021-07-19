package com.story.storyadmin.formatter;

import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

/**
 * @author: 59688
 * @date: 2021/7/14
 * @description: 注册DateFormatter
 */
//@Component
public class MyFormatterRegisterar implements FormatterRegistrar {

    @Override
    public void registerFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addFormatter(new StringToDateFormatter());
    }
}
