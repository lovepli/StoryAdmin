package com.story.storyadmin.framework.tcp.demo2;

import java.net.Socket;

public interface Client {
    public Socket getSocket();

    /**
     * @Author Nxy
     * @Date 2020/3/21 22:12
     * @Param data:要发送的数据
     * @Return
     * @Exception
     * @Description 发送数据
     */
    public byte[] send(Object data);

    public void close();
}