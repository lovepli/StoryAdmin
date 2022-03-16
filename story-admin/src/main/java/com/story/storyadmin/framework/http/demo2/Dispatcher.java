package com.story.storyadmin.framework.http.demo2;

import com.story.storyadmin.framework.tcp.demo3.CloseUtil;

import java.io.IOException;
import java.net.Socket;

/**
 * 3.创建分发器#
 * 　　我们需要有一个类专门一对一处理一个连接，并且该类要支持多线程。所以我们抽象出来一个分发器类。该类负责专门一对一处理一个连接
 * 　　该类拥有一个私有属性Socket client。利用该属性，该类可以创建一个Request和一个Response，然后该类再根据请求的url，利用Webapp类（该类用于生成处理不同请求的不同的类）获得对应的类，启动该类进行处理。
 * 最后该类再调用Response提供的pushToClient方法将所有信息推送给浏览器，然后关闭连接。
 */
public class Dispatcher implements Runnable {
    private Socket client;
    private Request req;
    private Response rep;
    private int code=200;

    public Dispatcher(Socket client) {
        this.client=client;
        try {
            req=new Request(client.getInputStream());
            rep=new Response(client.getOutputStream());
        } catch (IOException e) {
            code=500;
        }
    }

    @Override
    public void run() {
        System.out.println(req.getUrl()+"   ***");
        Servlet servlet=Webapp.getServlet(req.getUrl());
        if(servlet!=null)
            servlet.service(req, rep);
        else
            code=404;
        try {
            rep.pushToClient(code);
        } catch (IOException e) {
            code=500;
        }
        try {
            rep.pushToClient(code);
        } catch (IOException e) {

        }
        CloseUtil.closeAll(client);
    }
}
