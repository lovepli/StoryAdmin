package com.story.storyadmin.framework.tcp.demo2;

import java.net.Socket;
import java.util.function.BiFunction;

/**
 * @Author Nxy
 * @Date 2020/3/21 21:19
 * @Description 构建 socket 服务端的简单工厂
 */
public class ServerFactory {
    /**
     * @Author Nxy
     * @Date 2020/3/21 21:43
     * @Param port:监听端口，serverType:服务端类型，reciveRegisterType：划分报文边界策略，dataHandler：业务逻辑函数
     * @Return Server：构建的服务端对象，通过 start 方法启动
     * @Exception
     * @Description
     */
    public static Server getServer(int port, SocketType serverType, DelimitType reciveDelimitType,
                                   DelimitType sendDelimitType, BiFunction<Socket, byte[], Object> dataHandler) {
        Server re = null;
        ReciveRegister reciveRegister = null;
        SendRegister sendRegister = null;
        //接收报文定界策略构建
        switch (reciveDelimitType) {
            case commonFlag:
                reciveRegister = new CommonFlagRegister();
                break;
            case fixedLength:
                reciveRegister = new FixedLengthRegister();
                break;
            case LengthFlag:
                reciveRegister = new LengthFlagReciveRegister();
                break;
        }
        //发送报文定界策略构建
        switch (sendDelimitType) {
            case commonFlag:
                sendRegister = (SendRegister) new CommonFlagRegister(null);
                break;
            case fixedLength:
                sendRegister = (SendRegister) new FixedLengthRegister(null);
                break;
            case LengthFlag:
                sendRegister = new LengthFlagSendRegister(null);
                break;
        }
        //服务端构建
        switch (serverType) {
            case BIO:
                re = new BIOSever(port, reciveRegister, sendRegister, dataHandler);
                break;
            case NIO:
                re = new NIOServer(port,reciveRegister,sendRegister,dataHandler);
                break;
        }
        return re;
    }
}
