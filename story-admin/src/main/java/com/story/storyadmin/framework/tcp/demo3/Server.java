package com.story.storyadmin.framework.tcp.demo3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 * Java利用TCP编程实现简单聊天室 https://www.cnblogs.com/DLKKILL/p/10368968.html
 * 一、实现思路#
 * 　　 实现聊天室的最核心部分就是JAVA的TCP网络编程。
 * 　　TCP 传输控制协议是一种面向连接的、可靠的、基于字节流的传输层通信协议 ，在Java中我们利用ServerSocket类来建立服务端，利用Socket类来建立客户端。这里要注意，在TCP中，Socket实际上是指
 * Server端与Client端建立的一个双向的流通道，我们利用这个流通道实现数据的传输。
 * 　　我们将聊天室分为两部分，客户端和服务端．
 * 　　对于客户端，主要有两个功能，信息的收与信息的发。因为这两个功能需要并行进行，并且要不停的进行收和发，所以将这两个功能抽象成两个实现Runnable接口的(Send,Recevice)类，每次客户端Client启动，建立一个Socket，并利用这个Socket建立一个收线程(Recevice类)和发线程(Send)类
 *
 * 　　对于服务器端，因为我们要不停的监听是否有新的连接进来，所有要通过一个循环不停的接收，这里的接收函数是阻塞式的。因为我们要对所有的连接进行同时处理，所有我们将新得到连接抽象成一个实现Runnable接口的User类，利用多线程进程对每一个连接并行处理。为了方便多个连接之间的交互，我们将User类作为Server类的一个内部类使用。
 */
public class Server { //2.服务端 Server类(内部有一个User类)

    //保存所有的连接
    private HashSet<User> users;
    //线程标识
    private boolean run=true;

    public static void main(String[] args) {

        //创建一个Server类
        Server server=new Server();
        try {
            //启动服务器
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server() {
        run=true;
        users=new HashSet<User>();
    }

    public void start() throws IOException {
        //创建一个服务器端
        ServerSocket server=new ServerSocket(8888);
        while(run) {
            //不断接收一个新的连接，利用新连接创建一个User线程进行处理
            Socket client= server.accept();
            User user=new User(client);
            users.add(user);
            new Thread(user).start();
        }
    }

    public void stop() {
        run=false;
    }


    //代表一个连接，负责信息的接收与转发
    private class User implements Runnable{

        //记录连接用户的名字
        private String name;

        public String getName() {
            return name;
        }
        //负责接收
        private DataInputStream is;
        //负责发送
        private DataOutputStream os;
        //线程标识
        private boolean isRun=true;

        public User(Socket client) {

            try {
                is=new DataInputStream(client.getInputStream());
                os=new DataOutputStream(client.getOutputStream());
                isRun=true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                isRun=false;
                CloseUtil.closeAll(is,os);
            }
            try {
                name=is.readUTF();
                this.sendOther(new String("欢迎"+name+"进入聊天室"),true);
                this.send(new String("系统：您已经进入了聊天室"));
            }catch (Exception e) {
                // TODO: handle exception
            }
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(isRun) {
                this.sendOther(this.revice(),false);
            }
        }

        //接收信息
        public String revice() {
            String msg = null;
            try {
                msg=is.readUTF();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return msg;
        }

        //发送信息
        public void send(String msg) {
            try {
                os.writeUTF(msg);
                os.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        //将信息转发给其他用户,同时实现了私聊功能和系统信息功能
        //因为是内部类，所以可以访问Server类中的private HashSet<User> users
        //@XX:代表向XX发送私聊信息
        public void sendOther(String msg,boolean admin) {
            if(msg.startsWith("@")&&msg.contains(":")) {
                String toname=msg.substring(1, msg.indexOf(":"));
                String newmsg=msg.substring(msg.indexOf(":")+1);
                for (User user : users) {
                    if(user.getName().equals(toname)) {
                        user.send(this.name+"悄悄的对你说:"+newmsg);
                    }
                }
            }else {
                for (User client : users) {
                    if(client!=this) {
                        if(admin)
                            client.send("系统："+":"+msg);
                        else
                            client.send(this.name+":"+msg);
                    }
                }
            }
        }
    }
}

