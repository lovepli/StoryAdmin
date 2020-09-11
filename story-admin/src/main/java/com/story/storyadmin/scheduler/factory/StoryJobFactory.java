package com.story.storyadmin.scheduler.factory;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

/**
 * 让Quartz的Job使用Spring注入的Bean 参考：https://blog.csdn.net/a67474506/article/details/38402059
 * SpringBoot集成Quartz使用工厂Bean生成Bean的方式。https://www.sundayfine.com/springboot-quartz/
 * 首先需要配置工厂Bean，负责生成实现了Job接口的类的实例对象Bean。
 */
@Component("storyJobFactory")
public class StoryJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

	private transient AutowireCapableBeanFactory beanFactory;

    /**
     * 重写ApplicationContextAware接口的setApplicationContext方法
     * @param context
     * @throws BeansException
     */
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		beanFactory = context.getAutowireCapableBeanFactory();
	}

    /**
     * 继承SpringBeanJobFactory重写他的createJobInstance方法
     * @param bundle
     * @return
     * @throws Exception
     */
	@Override
	protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
        //把Job交给Spring来管理，这样Job就能使用由Spring产生的Bean了
        //调用父类的方法
		final Object job = super.createJobInstance(bundle);
		//进行注入
		beanFactory.autowireBean(job);
		return job;
	}

    /**
     * 当Spring在加载配置文件时，如果配置文件中有Bean实现了ApplicationContextAware接口时
     * Spring会自动调用setApplicationContext方法
     * 我们可以通过这个获取Spring上下文然后在创建Job时让Job自动注入到Spring容器中
     */

}

/**
public class JobFactory extends AdaptableJobFactory {
    @Autowired 
    private AutowireCapableBeanFactory capableBeanFactory;  

    @Override 
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {  
        //调用父类的方法  
        Object jobInstance = super.createJobInstance(bundle);  
        //进行注入  
        capableBeanFactory.autowireBean(jobInstance);  
        return jobInstance;  
    }
}
**/
