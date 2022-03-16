package com.story.storyadmin.framework.rpcDemo2;

import java.net.URL;

/**
 * @Author Nxy
 * @Date 2020/3/23 21:10
 * @Description 进行远程方法调用
 */
public interface InvokeRegister<T> {
    public T invoke(URL url, Invocation invocation);
}