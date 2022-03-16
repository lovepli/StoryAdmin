package com.story.storyadmin.framework.rpcDemo2;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BasicHttpHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        // 获取对象
        try {
            //从流里面获取数据
            InputStream is = req.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(is);
            //从流中读取数据反序列话成实体类。
            Invocation invocation = (Invocation) objectInputStream.readObject();
            //拿到服务的名字
            String interfaceName = invocation.getInterfaceName();
            //从注册中心里面拿到接口的实现类
            Class interfaceImplClass = BasicLocalRegistry.getInstance().get(interfaceName);
            //获取类的方法
            Method method = interfaceImplClass.getMethod(invocation.getMethodName(), invocation.getParamtypes());
            //反射调用方法
            String result = (String) method.invoke(interfaceImplClass.newInstance(), invocation.getObjects());
            //把结果返回给调用者
            IOUtils.write(result, resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
