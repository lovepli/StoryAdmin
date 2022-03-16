package com.story.storyadmin.framework.iocAndAop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 创建代理类：
 */
public class ProxyCreator {
    /**
     * 创建动态代理对象并返回
     * @param targetClass 被代理的Class对象
     * @param methodInterceptor 方法拦截器
     * @return
     */
    public static Object createProxy(Class<?> targetClass, MethodInterceptor methodInterceptor){
        return Enhancer.create(targetClass, methodInterceptor);
    }
}