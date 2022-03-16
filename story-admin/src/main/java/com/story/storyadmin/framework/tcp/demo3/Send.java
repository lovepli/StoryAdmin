package com.story.storyadmin.framework.tcp.demo3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable{

    //负责写出，将信息传输到服务端
    private DataOutputStream os;
    //负责读取控制台输入
    private BufferedReader console;
    //线程标识
    private boolean isRun=true;

    //通过死循环保证线程一直进行
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(isRun) {
            send(getMsgFromConsole());
        }
    }

    //构造方法，利用Socket类获得流
    public Send(Socket client,String name) {
        // TODO Auto-generated constructor stub
        try {
            os=new DataOutputStream(client.getOutputStream());
            console=new BufferedReader(new InputStreamReader(System.in));
            send(name);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            isRun=false;
            CloseUtil.closeAll(os,console);
        }
    }

    //发送函数
    public void send(String msg) {

        try {
            if(msg!=null&&!msg.equals("")) {
                os.writeUTF(msg);
                os.flush();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            isRun=false;
            CloseUtil.closeAll(os,console);
        }
    }

    //从控制台不断读取信息
    public String getMsgFromConsole() {
        String msg=null;
        try {
            msg=console.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            isRun=false;
            CloseUtil.closeAll(os,console);
        }
        return msg;
    }
}
