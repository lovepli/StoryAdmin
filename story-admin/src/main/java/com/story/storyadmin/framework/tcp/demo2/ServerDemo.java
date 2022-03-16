package com.story.storyadmin.framework.tcp.demo2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @Author Nxy 手写一个模块化的 TCP 服务端客户端 https://www.cnblogs.com/niuyourou/p/12547511.html
 * @Date 2020/3/22 15:37
 * @Description 工厂模式构建服务端样例
 * 传入 监听端口，socket 类型，接收数据报文定界策略，发送数据报文定界策略，以及报文处理函数。
 * 报文处理函数为一个 lamda 对象，return 的数据会直接回复给请求方
 */
public class ServerDemo {
    public static void main(String[] args) {
        Server server = ServerFactory.getServer(80, SocketType.BIO, DelimitType.LengthFlag, DelimitType.LengthFlag,
                (socket, bytes) -> {
                    Invocation obj = null;
                    String exception = null;
                    try {
                        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                        ObjectInputStream ois = new ObjectInputStream(bis);
                        obj = (Invocation) ois.readObject();
                        ois.close();
                        bis.close();
                        exception = "200";
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        exception = ex.getMessage();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(obj.getInterfaceName() + ":" + obj.getMethodName());
                    return exception;
                });
        server.start();
    }
}