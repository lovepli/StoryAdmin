package com.story.storyadmin.framework.tcp.demo2;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * @Author Nxy
 * @Date 2020/3/21 17:54
 * @Description socket 客户端
 */
public class ClientDemo {
    public static void main(String[] args) throws Exception {
        //简单工厂获取客户端实例
        Client client =
                ClientFactory.getClient("127.0.0.1", 80, SocketType.BIO,
                        DelimitType.LengthFlag, DelimitType.LengthFlag);
        client.getSocket().setKeepAlive(true);
        //要发送的数据准备
        Object[] params = new Object[2];
        Class[] paramTypes = new Class[2];
        Invocation invocation = new Invocation(ClientDemo.class.getName(), "main", paramTypes, params);
        for (int i = 0; i < 10; i++) {
            //发送数据
            byte[] re = client.send(invocation);
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(re));
            String o = (String) in.readObject();
            System.out.println("服务端回复数据 ：" + o);
        }
    }

}