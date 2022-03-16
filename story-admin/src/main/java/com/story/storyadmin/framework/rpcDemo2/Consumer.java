package com.story.storyadmin.framework.rpcDemo2;

public class Consumer {
    public static void main(String[] args) {
        MyRpcService helloService =
                new ProxyFactory<MyRpcService, String>(ProxyFactory.InvokeType.TCP).getProxy(MyRpcService.class);
        String result = helloService.sayHellow("liuy");
        System.out.println(result);
    }
}