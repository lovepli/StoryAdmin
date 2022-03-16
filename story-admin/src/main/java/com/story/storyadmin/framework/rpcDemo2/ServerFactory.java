package com.story.storyadmin.framework.rpcDemo2;

public class ServerFactory {
    public static RpcServer getServer(int port, ServerType serverType) {
        RpcServer server = null;
        switch (serverType) {
            case HTTP:
                server = new HttpServer(port);
            case TCP:
                server = new TcpServer(port);
        }
        return server;
    }

    public enum ServerType {
        TCP, HTTP
    }
}