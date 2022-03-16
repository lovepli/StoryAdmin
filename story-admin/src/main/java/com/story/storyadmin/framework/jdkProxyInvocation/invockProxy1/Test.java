package com.story.storyadmin.framework.jdkProxyInvocation.invockProxy1;

/**
 * @author: 59688
 * @date: 2021/9/29
 * @description: 纯手写实现JDK动态代理 https://www.jianshu.com/p/58759fef38b8
 */
public class Test {

    public static void main(String[] args) throws Throwable {
        Man man = new Zhangfengzhe();
        MyHandler myHandler = new MyHandler(man);
        Man proxyMan = (Man) MyProxy.newProxyInstance(
                new MyClassLoader("C:\\Users\\59688\\Documents\\GitHub\\StoryAdmin\\story-admin\\src\\main\\java\\com\\story\\storyadmin\\framework\\jdkProxyInvocation\\invockProxy1","com.story.storyadmin.framework.jdkProxyInvocation.invockProxy1"),
                Man.class,myHandler
        );
        System.out.println(proxyMan.getClass().getName());
        proxyMan.findObject();

    }
}
