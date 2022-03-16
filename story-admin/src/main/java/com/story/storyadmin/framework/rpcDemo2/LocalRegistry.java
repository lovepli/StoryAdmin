package com.story.storyadmin.framework.rpcDemo2;

/**
 * @Author Nxy
 * @Date 2020/3/19 11:00
 * @Description 服务端通过服务名找到对应实现
 */
public interface LocalRegistry {

    /**
     * @Author Nxy
     * @Date 2020/3/19 11:03
     * @Description 注册到服务中心
     */
    void register(String interfaceName, Class interfaceImplClass);

    /**
     * @Author Nxy
     * @Date 2020/3/19 11:03
     * @Description 根据服务名称获取实现类
     */
    Class get(String interfaceName);

}