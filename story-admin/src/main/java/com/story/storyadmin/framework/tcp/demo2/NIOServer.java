package com.story.storyadmin.framework.tcp.demo2;

import java.net.Socket;
import java.util.function.BiFunction;

/**
 * @author: 59688
 * @date: 2021/10/13
 * @description:
 */
public class NIOServer implements Server {
    public NIOServer(int port, ReciveRegister reciveRegister, SendRegister sendRegister, BiFunction<Socket, byte[], Object> dataHandler) {
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
