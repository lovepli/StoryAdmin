package com.story.storyadmin.framework.tcp.demo2;

import com.story.storyadmin.framework.tcp.demo1.IOUtil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * @Author Nxy
 * @Date 2020/3/21 20:25
 * @Description 按长度标识读取报文的读取策略。每次报文头四个字节标识本次
 * 请求的报文长度，用于划定请求边界
 */
public class LengthFlagReciveRegister implements ReciveRegister {
    @Override
    public byte[] read(InputStream in) throws IOException {
        if (in == null) {
            throw new NullPointerException("inputStream is null!");
        }

        BufferedInputStream bufferIn = new BufferedInputStream(in);
        byte[] result;
        //读取头四个字节，转换为 int 即为报文长度
        byte[] lengthByte = IOUtil.readBytesFromInputStream(bufferIn, 4);
        int length = ByteBuffer.wrap(lengthByte).getInt();
        System.out.println("from recive:" + length);
        //读取指定长度字节
        result = IOUtil.readBytesFromInputStream(bufferIn, length);
        return result;
    }
}