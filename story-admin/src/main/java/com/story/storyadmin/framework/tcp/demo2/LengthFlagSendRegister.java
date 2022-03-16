package com.story.storyadmin.framework.tcp.demo2;

import com.story.storyadmin.framework.tcp.demo1.IOUtil;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * @Author Nxy
 * @Date 2020/3/22 15:16
 * @Description 按长度标识定界的发送策略
 */
public class LengthFlagSendRegister implements SendRegister {
    private final ReciveRegister reciveRegister;

    public LengthFlagSendRegister(ReciveRegister reciveRegister) {
        this.reciveRegister = reciveRegister;
    }

    @Override
    public byte[] send(Socket socket, Object object) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
        byte[] objectBytes = IOUtil.toByteArray(object);
        int length = objectBytes.length;
        System.out.println("from client:" + length);
        //写入长度
        out.write(ByteBuffer.allocate(4).putInt(length).array());
        //写入数据
        out.write(objectBytes);
        out.flush();
        //如果未传入接收报文定界策略，则不需要接收回复数据
        if (reciveRegister == null) {
            return null;
        }
        while (socket.getInputStream().available() <= 0) {
        }
        byte[] re = reciveRegister.read(socket.getInputStream());
        return re;
    }
}