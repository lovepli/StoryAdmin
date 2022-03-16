package com.story.storyadmin.framework.jdkProxyInvocation.invockProxy1.demo;

import com.story.storyadmin.framework.jdkProxyInvocation.invockProxy1.Man;
import com.story.storyadmin.framework.jdkProxyInvocation.invockProxy1.Zhangfengzhe;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @author: 59688
 * @date: 2021/9/29
 * @description: 纯手写实现JDK动态代理
 * https://www.jianshu.com/p/58759fef38b8
 */
public class Test {

    public static void main(String[] args) throws Throwable {
        Man man = new Zhangfengzhe();

        ManHandler manHandler = new ManHandler(man);
        Man proxyMan = (Man) Proxy.newProxyInstance(man.getClass().getClassLoader(),
                new Class[]{Man.class},manHandler);
        System.out.println(proxyMan.getClass().getName());
        proxyMan.findObject();
        // 打印JVM在内存中生成的动态代理类
        createProxyClassFile(Man.class);

    }

    private static void  createProxyClassFile(Class c){
        byte[] data = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{c});

        try {
            // 写入文件中
            FileOutputStream fileOutputStream = new FileOutputStream("$Proxy0.class");
            fileOutputStream.write(data);
            fileOutputStream.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
