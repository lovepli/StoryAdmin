package com.story.storyadmin.designPatterns.adutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author: lipan
 * @date: 2021/8/17
 * @description: 获取配置参数
 */
@PropertySource(value = "classpath:itemrecommend.properties")// @PropertiesSource注解读取配置文件中的数据
@Component
@ServletComponentScan
@Lazy(false)
public class PropertyUtil2 {

    @Autowired
    private Environment environment;

    public  String get(String key){
        System.out.println("获取到到参数："+environment.getProperty(key));
        return environment.getProperty(key);
    }
}
