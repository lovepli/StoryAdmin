package com.story.storyadmin.framework.http.demo2;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 1.服务器类#
 * 首先我们需要建立一个服务器类，负责不断监听端口，获得Socket，然后再利用获得Socket新建一个分发器(Dispatcher)，该分发器支持多线程，所以一个分发器专门负责处理一个连接，而服务器只负责不断接收连接，建立分发器。
 */
public class Server {

    private boolean flag=false;
    private ServerSocket server;

    public static void main(String[] args) throws IOException {
        Server myserver=new Server(8888);
        myserver.start();
    }

    public Server(int port) {
        try {
            server=new ServerSocket(port);
        } catch (IOException e) {
            this.stop();
        }
    }

    public void start() throws IOException {
        this.flag=true;
        this.revice(server);
    }

    public void revice(ServerSocket server) throws IOException {
        while(flag) {
            Socket client=server.accept();
            new Thread(new Dispatcher(client)).start();
        }
    }

    public void stop() {
        flag=false;

    }
}

