package com.story.storyadmin.framework.rpcDemo2;

import com.story.storyadmin.framework.tcp.demo2.Client;
import com.story.storyadmin.framework.tcp.demo2.ClientFactory;
import com.story.storyadmin.framework.tcp.demo2.DelimitType;
import com.story.storyadmin.framework.tcp.demo2.SocketType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;

/**
 * @Author Nxy
 * @Date 2020/3/23 21:11
 * @Description 通过 TCP 进行远程调用
 */
public class TcpInvokeRegister<T> implements InvokeRegister<T> {
    @Override
    public T invoke(URL url, Invocation invocation) {
        Client client =
                ClientFactory.getClient(url.getHost(), url.getPort(), SocketType.BIO,
                        DelimitType.LengthFlag, DelimitType.LengthFlag);
        byte[] re = client.send(invocation);
        try {
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(re));
            T o = (T) in.readObject();
            return o;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
        return null;
    }
}