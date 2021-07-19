package com.story.storyadmin.filter.xss;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author: 59688
 * @date: 2021/7/12
 * @description: 注册 CrosXssFilter
 */
@ConditionalOnWebApplication // 注意，这里用了@ConditionalOnWebApplication注解，没有直接使用@Configuration注解。
public class CrosXssFilterConfig {

    @Bean
    public CrosXssFilter timeFilter() {
        return new CrosXssFilter();
    }
}
