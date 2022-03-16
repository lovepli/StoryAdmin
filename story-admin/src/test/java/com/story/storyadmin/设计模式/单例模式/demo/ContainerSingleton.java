package com.story.storyadmin.设计模式.单例模式.demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: lipan
 * @date: 2021年08月26日 12:14 下午
 * @description: Map实现，仿照Spring的Ioc容器实现。
 */
public class ContainerSingleton {
    private ContainerSingleton(){}

    private static Map<String,Object> ioc = new ConcurrentHashMap<>();

    public static Object getInstance(String className) {
        synchronized (ioc) {
            if (!ioc.containsKey(className)) {
                Object obj = null;

                try {
                    Class.forName(className).newInstance();
                    ioc.put(className,obj);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return obj;
            } else {
                return ioc.get(className);
            }
        }
    }
}
