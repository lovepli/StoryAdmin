package com.story.storyadmin.framework.jdkProxyInvocation.invockProxy1.demo;

import com.story.storyadmin.framework.jdkProxyInvocation.invockProxy1.Man;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: 59688
 * @date: 2021/9/29
 * @description: 业务处理类
 */
public class ManHandler implements InvocationHandler {
    private Man man;

    public ManHandler(Man man){
        this.man = man;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      before();
      method.invoke(man,null);
      after();
      return null;
    }

    private void before(){
        System.out.println("没有找到你之前，要从内而外的做好自己");
    }

    private void after(){
        System.out.println("找到你之后，要相亲相爱过一辈子");
    }


}
