package com.story.storyadmin.framework.rpcDemo2;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Nxy
 * @Date 2020/3/23 23:03
 * @Description 本地注册中心，常驻内存，存储接口与实现类的对应关系
 */
public class BasicLocalRegistry implements LocalRegistry {
    private Map<String, Class> registerMap = new HashMap<String, Class>(1024);

    private static BasicLocalRegistry basicLocalRegister;

    @Override
    public void register(String interfaceName, Class interfaceImplClass) {
        registerMap.put(interfaceName, interfaceImplClass);
    }

    @Override
    public Class get(String interfaceName) {
        return registerMap.get(interfaceName);
    }

    public static BasicLocalRegistry getInstance() {
        if (basicLocalRegister == null) {
            synchronized (BasicLocalRegistry.class) {
                if (basicLocalRegister == null) {
                    basicLocalRegister = new BasicLocalRegistry();
                }
            }
        }
        return basicLocalRegister;
    }

    private Object readResolve() {
        return getInstance();
    }
}