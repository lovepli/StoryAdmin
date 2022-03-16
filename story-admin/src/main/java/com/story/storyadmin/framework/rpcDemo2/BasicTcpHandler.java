package com.story.storyadmin.framework.rpcDemo2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.util.function.BiFunction;

public class BasicTcpHandler implements BiFunction {

    @Override
    public Object apply(Object o, Object o2) {
        byte[] bytes = (byte[]) o2;
        Invocation invocation = null;
        String exception = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            invocation = (Invocation) ois.readObject();
            ois.close();
            bis.close();

            String interfaceName = invocation.getInterfaceName();
            String methodName = invocation.getMethodName();
            System.out.println("客户端调用：" + interfaceName + " : " + methodName);
            //从注册中心里面拿到接口的实现类
            Class interfaceImplClass = BasicLocalRegistry.getInstance().get(interfaceName);
            //获取类的方法
            Method method = interfaceImplClass.getMethod(invocation.getMethodName(), invocation.getParamtypes());
            //反射调用方法
            String result = (String) method.invoke(interfaceImplClass.newInstance(), invocation.getObjects());
            return result;
        } catch (IOException ex) {
            ex.printStackTrace();
            exception = ex.getMessage();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ie) {
            ie.printStackTrace();
        } catch (Exception illE) {
            illE.printStackTrace();
        }
        return "500";
    }
}