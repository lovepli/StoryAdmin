package com.story.storyadmin.basic.io.demo;

import cn.hutool.core.thread.ThreadUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: 59688
 * @date: 2021/10/13
 * @description:
 */
public class NioEchoServer {
    static List<SocketChannel> channels = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        // 将Channel设为非阻塞!!!
        serverSocketChannel.configureBlocking(false);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                socketChannel.configureBlocking(false);
                channels.add(socketChannel);
            }
            // 处理数据读操作
            Iterator<SocketChannel> iterator = channels.iterator();
            while (iterator.hasNext()) {
                SocketChannel channel = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int readSize = channel.read(byteBuffer);
                if (readSize > 0) {
                    // 翻转，可读 > 可写
                    byteBuffer.flip();
                    channel.write(byteBuffer);
                } else if (readSize < 0) {
                    iterator.remove();
                    channel.close();
                }
            }
            // 避免CPU空转，sleep一会
            ThreadUtil.sleep(10);
        }
    }
}