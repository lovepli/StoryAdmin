package com.story.storyadmin.framework.rpcDemo2;

import java.net.URL;

/**
 * @Author Nxy
 * @Date 2020/3/19 11:02
 * @Description 服务调用方通过服务名找到调用地址
 */
public interface WebRegistry {

    /**
     * @Author Nxy
     * @Date 2020/3/19 11:02
     * @Description 注册到治理中心
     */
    void register(String interfaceName, URL host);

    /**
     * @Author Nxy
     * @Date 2020/3/19 11:02
     * @Description 根据服务名找到调用地址，当调用地址有多个时，采用随机选取的负载均衡策略
     */
    URL getRandomURL(String interfaceName);

}