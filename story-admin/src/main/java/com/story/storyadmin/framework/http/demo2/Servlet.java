package com.story.storyadmin.framework.http.demo2;

import java.net.Socket;

/**
 * 4.抽象处理类Servlet#
 * 首先我们将该处理类抽象成一个abstract Servlet类，该类负责根据不同的请求进行处理
 * 该抽象类提供多个抽象方法doGet、doPost方法等分别处理不同的请求，传入参数为(Request,Response)这两个参数，在该方法内进行处理。
 * 提供一个service方法根据不同的请求调用不同的方法
 */
public abstract class Servlet {


    public Servlet() {

    }

    public void service(Request req,Response rep) {
        if(req.getMethod().equalsIgnoreCase("get")) {
            this.doGet(req, rep);
        }else {
            this.doPost(req, rep);
        }
    }

    public abstract void doGet(Request req,Response rep);

    public abstract void doPost(Request req,Response rep);
}

