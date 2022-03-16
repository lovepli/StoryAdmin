package com.story.storyadmin.framework.jdkProxyInvocation.invockProxy1;

import org.aspectj.lang.annotation.Before;

import java.lang.reflect.Method;

/**
 * @author: 59688
 * @date: 2021/9/29
 * @description:
 */
public class MyHandler implements MyInvocationHandler {
   private Man man;

   public MyHandler(Man man){
       this.man =man;
   }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(man,null);
        after();
        return invoke;
    }

    private void before(){
        System.out.println("before find you,I will make self good.");
    }

    private void after(){
        System.out.println("after find you,I will make ourself good.");
    }

}
