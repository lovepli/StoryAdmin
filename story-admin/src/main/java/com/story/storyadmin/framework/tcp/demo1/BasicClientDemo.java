package com.story.storyadmin.framework.tcp.demo1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * @Author Nxy 基于 socket 手写一个 TCP 服务端及客户端 https://www.cnblogs.com/niuyourou/p/12542209.html
 * @Date 2020/3/21 17:54
 * @Description socket 客户端
 */

public class BasicClientDemo {
    public static void main(String[] args) {
        Socket socket;
        BufferedOutputStream out;
        BufferedInputStream in;
        try {
            socket = new Socket("127.0.0.1", 80);
            out = new BufferedOutputStream(socket.getOutputStream());
            in = new BufferedInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Object[] params = new Object[2];
        Class[] paramTypes = new Class[2];
        Invocation invocation = new Invocation(BasicClientDemo.class.getName(), "main", paramTypes, params);
        byte[] invocationBytes = IOUtil.toByteArray(invocation);
        int length = invocationBytes.length;
        try {
            System.out.println("from client:" + length);
            out.write(ByteBuffer.allocate(4).putInt(length).array());
            out.flush();
            out.write(invocationBytes);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
