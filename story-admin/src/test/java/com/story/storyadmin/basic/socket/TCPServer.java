package com.story.storyadmin.basic.socket;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP服务端 客户端与服务端消息传送过程一直保持连接状态
 */
public class TCPServer {
    public static void main(String[] args) throws Exception {
        //创建socket,并将socket绑定到65000端口
        ServerSocket ss = new ServerSocket(65000);
        //死循环，使得socket一直等待并处理客户端发送过来的请求
        while (true) {
            //监听65000端口，直到客户端返回连接信息后才返回
            Socket socket = ss.accept();
            //获取客户端的请求信息后，执行相关业务逻辑
            new LengthCalculator(socket).start();
        }
    }
}
