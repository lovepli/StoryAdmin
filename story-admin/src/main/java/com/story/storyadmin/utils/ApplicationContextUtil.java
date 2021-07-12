package com.story.storyadmin.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 应用上下文
 * 获取 spring 容器对象的方式：实现ApplicationContextAware接口，然后重写setApplicationContext方法，就能从该方法中获取到 spring 容器对象
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      ApplicationContextUtil.applicationContext = applicationContext;
    }

    /**
     * 根据类class获取对象
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls){
        return applicationContext.getBean(cls);
    }

    /**
     * 根据类名获取对象
     * @param beanName
     * @return
     */
    public static Object getBeanObj(String beanName){
        return applicationContext.getBean(beanName);
    }
}

