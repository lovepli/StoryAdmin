package com.story.storyadmin.framework.tcp.demo2;

import java.io.Serializable;

/**
 * @Author Nxy
 * @Date 2020/3/19 10:57
 * @Description rpc 远程调用参数封装
 */
public class Invocation implements Serializable {
    private static final long serialVersionUID = 75929334234892747L;
    //远程调用接口名称
    private String interfaceName;
    //远程调用方法名称
    private String methodName;
    //方法参数类型列表
    private Class[] paramtypes;
    //方法参数列表
    private Object[] objects;

    /**
     * @param interfaceName 接口名字
     * @param methodName    方法名字
     * @param paramtypes    参数类型列表
     * @param objects       参数列表
     */
    public Invocation(String interfaceName, String methodName, Class[] paramtypes, Object[] objects) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.paramtypes = paramtypes;
        this.objects = objects;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamtypes() {
        return paramtypes;
    }

    public void setParamtypes(Class[] paramtypes) {
        this.paramtypes = paramtypes;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }
}
