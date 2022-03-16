package com.story.storyadmin.basic.io.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: 59688
 * @date: 2021/10/13
 * @description:
 */
// Bio版本的Echo服务
public class BioEchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
            final Socket accept = serverSocket.accept();
            new Thread(() -> {
                try {
                    InputStream inputStream = accept.getInputStream();
                    OutputStream outputStream = accept.getOutputStream();
                    while (true) {
                        byte[] bytes = new byte[1024];
                        int size = inputStream.read(bytes);
                        if (size <= -1) {
                            accept.shutdownOutput();
                            accept.close();
                            break;
                        }
                        outputStream.write(bytes);
                        outputStream.flush();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}