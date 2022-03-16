package com.story.storyadmin.framework.jdkProxyInvocation.invockProxy1;

import java.lang.reflect.Method;

/**
 * @author: 59688
 * @date: 2021/9/29
 * @description:
 */
public interface MyInvocationHandler {

    public Object invoke(Object proxy, Method method,Object[] args) throws Throwable;
}
