package com.story.storyadmin.framework.rpcDemo2;

import com.story.storyadmin.framework.tcp.demo2.DelimitType;
import com.story.storyadmin.framework.tcp.demo2.Server;
import com.story.storyadmin.framework.tcp.demo2.ServerFactory;
import com.story.storyadmin.framework.tcp.demo2.SocketType;

public class TcpServer implements RpcServer {
    private final int port;

    public TcpServer(int port) {
        this.port = port;
    }

    @Override
    public void start() {
        Server server = ServerFactory.getServer(port, SocketType.BIO, DelimitType.LengthFlag, DelimitType.LengthFlag,
                new BasicTcpHandler());
        server.start();
    }
}