package com.story.storyadmin.utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * @author: 59688
 * @date: 2021/8/5
 * @description: springboot启动时执行任务CommandLineRunner
 * 平常开发中有可能需要实现在项目启动后执行的功能，SpringBoot提供的一种简单的实现方案就是添加一个model并实现CommandLineRunner接口
 * 如果有多个类实现CommandLineRunner接口，如何保证顺序
 * > SpringBoot在项目启动后会遍历所有实现CommandLineRunner的实体类并执行run方法，如果需要按照一定的顺序去执行，那么就需要在实体类上使用一个@Order注解（或者实现Order接口）来表明顺序
 * @Order 注解的执行优先级是按value值从小到大顺序。
 */
@Slf4j
@Component
//@Order(value=1)
public class StoryadminCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
    }
}
