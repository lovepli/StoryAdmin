package com.story.storyadmin.framework.rpcDemo2;

import java.net.URL;

/**
 * @Author Nxy
 * @Date 2020/3/23 21:11
 * @Description 通过 http 协议进行远程调用
 */
public class HttpInvokeRegister<T> implements InvokeRegister<T> {
    @Override
    public T invoke(URL url, Invocation invocation) {
        try {
            T re = (T) HttpUtil.httpPostSerialObject("http://" + url.getHost() + ":" + url.getPort(),
                    1000, 1000, invocation).toString();
            return re;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}