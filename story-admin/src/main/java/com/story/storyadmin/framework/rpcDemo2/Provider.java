package com.story.storyadmin.framework.rpcDemo2;


/**
 * 手写一个 RPC 通信框架
 * https://www.cnblogs.com/niuyourou/p/12556285.html
 */
public class Provider {
    public static void main(String[] args) {
        RpcServer sever = ServerFactory.getServer(80, ServerFactory.ServerType.TCP);
        //获取 远程调度中心 和 本地调度中心 实例
        WebRegistry webRegistry = BasicWebRegistry.getInstance();
        LocalRegistry localRegistry = BasicLocalRegistry.getInstance();
        try {
            //远程注册中心注册本地提供的接口
            webRegistry.register(MyRpcService.class.getName(), UrlUtil.getLocalUrl());
            //本地缓存注册接口对应的实现类
            localRegistry.register(MyRpcService.class.getName(), MyFirstService.class);
        } catch (Exception e) {
            System.out.println("向注册中心注册服务期间发生异常： " + e.getMessage());
            return;
        }
        sever.start();
    }
}