package com.story.storyadmin.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 应用上下文
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      ApplicationContextUtil.applicationContext = applicationContext;
    }

    /**
     *
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls){
        return applicationContext.getBean(cls);
    }

    /**
     * 通过类名获取对象
     * @param beanName
     * @return
     */
    public static Object getBeanObj(String beanName){
        return applicationContext.getBean(beanName);
    }
}

