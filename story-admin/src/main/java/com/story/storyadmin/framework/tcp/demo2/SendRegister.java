package com.story.storyadmin.framework.tcp.demo2;

import java.io.IOException;
import java.net.Socket;

public interface SendRegister {
    public byte[] send(Socket socket, Object object) throws IOException;
}