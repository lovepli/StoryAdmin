package com.story.storyadmin.basic.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: lipan
 * @date: 2019-06-05
 * @description:  代码有缺损！！！
 */
public class BIOPlainEchoServer {

    public void serve(int port) throws IOException {
        //将绑定到指定到端口里
        final ServerSocket socket = new ServerSocket(port);
        while (true) {
            //阻塞直到收到新的客户端的请求
            final Socket clientSocket=socket.accept();
            System.out.println("Accepted connected from "+ clientSocket);
            //创建一个子线程去处理客户端的请求
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    try {
//                        BufferedReader reader =new BufferedReader(new InputStreamReader(clientSocket));
//                        PrintWriter writer=new PrintWriter(clientSocket.getOutputStream(),);
//                        //从客户端读取数据并原封不动的写回去
//                        while (true) {
//                            writer.println(reader.readLine());
//                            writer.flush();
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }).start();
        }
    }

    public void improvedServe(int port) throws IOException {
        //将绑定到指定到端口里
        final ServerSocket socket = new ServerSocket(port);

        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(6);


    }


}
