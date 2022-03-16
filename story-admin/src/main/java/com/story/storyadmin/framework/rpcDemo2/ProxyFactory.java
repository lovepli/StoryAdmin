package com.story.storyadmin.framework.rpcDemo2;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;

/**
 * @Author Nxy
 * @Date 2020/3/23 21:09
 * @Description 通过 JDK 动态代理获取远程调用方法的接口实例
 */
public class ProxyFactory<T, R> {
    final InvokeRegister<R> invokeRegister;

    public ProxyFactory(InvokeType invokeType) {
        switch (invokeType) {
            case TCP:
                invokeRegister = new TcpInvokeRegister<R>();
                break;
            case HTTP:
                invokeRegister = new HttpInvokeRegister<R>();
                break;
            default:
                invokeRegister = null;
        }
    }

    /**
     * @Author Nxy
     * @Date 2020/3/23 21:14
     * @Param interfaceClass：调用接口，invokeType：远程调用方式
     * @Return
     * @Exception
     * @Description
     */
    public T getProxy(final Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass},
                (Object proxy, Method method, Object[] args) -> {
                    Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
                    WebRegistry remoteRegister = BasicWebRegistry.getInstance();
                    URL randomURL = remoteRegister.getRandomURL(interfaceClass.getName());
                    System.out.println("调用地址host:" + randomURL.getHost() + ",port:" + randomURL.getPort());
                    return invokeRegister.invoke(randomURL, invocation);
                }
        );
    }

    public enum InvokeType {
        HTTP, TCP
    }
}