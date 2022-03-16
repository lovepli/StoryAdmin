package com.story.storyadmin.config;

import com.story.storyadmin.scheduler.factory.StoryJobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * 定时任务配置
 * @author sunnj
 * SchedulerConfig类通过 @Configuration 自动装载配置。
 * SchedulerFactoryBean是Quartz为我们提供的一个生成Scheduler实例Bean的工厂类。Scheduler主要负责调度任务。
 * quartzProperties配置参数可以自由配置Quartz的一些基本参数属性。
 */
@Configuration
public class SchedulerConfig {

    /**
     * @Qualifier:
     * 当有多个同一类型的 Bean 时，可以用 @Qualifier(“name”) 来指定。与 @Autowired 配合使用；
     */
	@Autowired
	@Qualifier("storyJobFactory")
    private StoryJobFactory storyJobFactory;
	
	@Bean(name="storySchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(quartzProperties());
        factory.setOverwriteExistingJobs(true);
        factory.setJobFactory(storyJobFactory);
        return factory;
    }
	
	@Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactory = new PropertiesFactoryBean();
        propertiesFactory.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactory.afterPropertiesSet(); // 必须手动触发
        return propertiesFactory.getObject();
    }
}
