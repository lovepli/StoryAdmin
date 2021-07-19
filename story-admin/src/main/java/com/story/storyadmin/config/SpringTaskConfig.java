package com.story.storyadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.springframework.core.env.Environment;

/**
 * @author: 59688
 * @date: 2021/7/12
 * @description: Spring基于注解的定时任务类
 * 参考：SpringBoot 定时任务（自定义线程池） https://blog.csdn.net/Muscleheng/article/details/106838086
 *
 * springBoot 已经为我们提供了线程池，如果我们不自定义线程池，在使用@Async注解的时候，会默认使用SpringBoot的默认线程池；
 * 当我们自定义线程的时候，线程池类型为ThreadPoolTaskExecutor ，就会使用我们自定义线程池；如果自定义线程池不是ThreadPoolTaskExecutor，
 * 可能需要指定线程池名称，例如：@Async("线程池名称")，否则还是会使用springBoot的默认线程池；
 *
 * 关于注解：
 * @EnableAsync 开启异步执行，添加到启动类或者配置上；
 * @Async 写在需要异步执行方法上，@Async("线程池名称")，可以通过设置参数指定使用哪个线程池，线程池名称为线程池的bean名称；
 */
@PropertySource(value = "classpath:task.properties")// @PropertiesSource注解读取配置文件中的数据
@Component
@ServletComponentScan
@Lazy(false)
public class SpringTaskConfig {

    @Autowired
    private Environment environment;

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 定时任务 2秒执行一次
     */
    private static final String times1 = "0/2 * * * * ?";

    @Value(value = "${name}")
    private String name;

    /**
     * 从配置文件读取参数
     */
    @Async // 异步执行，线程之间不会互相干扰
    @PostConstruct // 加上该注解项目启动时就执行一次该方法,与springboot启动类是否有@EnableScheduling注解无关，总是能够执行一次
   // @Scheduled(cron = "${task.cron}") // cron表达式
    @Scheduled(cron = "0/1 * * * * ? ")
    public void teskTestp() {
        System.out.println("定时任务teskTestp开始执行");
        String url = environment.getProperty("demo.url");
        System.out.println("我的名字"+name);
        System.out.println("我执行了:"+url);
        System.out.println("执行");

    }

    /**
     * 定时任务方法1
     */
    @Async // 异步执行，线程之间不会互相干扰，任务自动提交到线程池
    @PostConstruct // 加上该注解项目启动时就执行一次该方法
    @Scheduled(cron=times1)
    public void teskTest() {

        //logger.info("定时任务开始执行。。。");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sdf.format(new Date())+"执行定时任务1执行");
        //logger.info("定时任务执行结束。。。");
    }

    // 注册bean
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }


}
