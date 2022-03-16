package com.story.storyadmin.framework.tcp.demo2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;

/**
 * @Author Nxy
 * @Date 2020/3/21 17:16
 * @Description socket TCP BIO 服务端
 */
public class BIOSever implements Server {
    //接受数据的报文定界策略
    private final ReciveRegister reciveRegister;
    //回复数据的报文定界策略
    private final SendRegister sendRegister;
    //接收数据处理业务逻辑
    private final BiFunction<Socket, byte[], Object> dataHandler;
    //任务线程池
    private final ExecutorService threadPool;
    // 主线程线程对象
    private final Thread mainThread;
    //监听端口
    private int port;

    public BIOSever(int port, ReciveRegister reciveRegister, SendRegister sendRegister, BiFunction<Socket, byte[], Object> dataHandler) {
        this.reciveRegister = reciveRegister;
        this.sendRegister = sendRegister;
        this.dataHandler = dataHandler;
        this.threadPool = Executors.newCachedThreadPool();
        mainThread = Thread.currentThread();
        this.port = port;
    }

    @Override
    public void start() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("recive start!");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (!Thread.currentThread().isInterrupted()) {
            Socket socket;
            BufferedInputStream in;
            BufferedOutputStream out;
            try {
                //阻塞等待连接请求
                socket = server.accept();
                System.out.println("建立连接：" + socket.getInetAddress());
                in = new BufferedInputStream(socket.getInputStream());
                out = new BufferedOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("连接建立失败！");
                continue;
            }
            threadPool.execute(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    byte[] result;
                    try {
                        //通过策略模式解耦报文定界策略与本类
                        result = reciveRegister.read(in);
                        //由调用者传入业务处理逻辑，如需返回数据，可通过 socket 对象获取发送缓冲区并写入返回数据
                        Object returnMsg = dataHandler.apply(socket, result);
                        if (returnMsg != null) {
                            //需回复数据不为 null 则回复数据
                            sendRegister.send(socket, returnMsg);
                        }
                    } catch (IOException e) {
                        System.out.println("an connect is closed witn IOException:" + e.getMessage());
                        return;
                    }
                }
            });
        }
    }

    public Thread getMainThread() {
        return this.mainThread;
    }

    @Override
    public void stop() {
        System.out.println("Stop recive :" + this.port);
        threadPool.shutdown();
        mainThread.interrupt();
    }
}
