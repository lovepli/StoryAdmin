package com.story.storyadmin.framework.tomcatDemo.tomcat2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author :dongshuo
 * @date : 2018/12/16 13:57
 * @desc : 写一个迷你版的Tomcat  https://www.jianshu.com/p/dce1ee01fb90
 */
public class MyTomcat {
    private int port = 8080;

    private Map<String,String> urlServletMap = new ConcurrentHashMap<>();

    public MyTomcat(int port) {
        this.port = port;
    }

    private void initServletMapping() {
        for (ServletMapping servletMapping : ServletMappingConfig.servletMappingList) {
            urlServletMap.put(servletMapping.getUrl(),servletMapping.getClazz());
        }
    }

    public void start() {
        initServletMapping();
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("MyTomcat is start..........");
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                MyRequest myRequest = new MyRequest(inputStream);
                MyResponse myResponse = new MyResponse(outputStream);
                dispatch(myRequest,myResponse);
                socket.close();
            }
        }catch (Exception e) {
            System.out.println(e);
        }finally {
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    public void dispatch(MyRequest myRequest,MyResponse myResponse) {
        String clazz = urlServletMap.get(myRequest.getUrl());
        try {
            Class<MyServlet> myServletClass = (Class<MyServlet>)Class.forName(clazz);
            MyServlet myServlet = myServletClass.newInstance();
            myServlet.service(myRequest,myResponse);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (IllegalAccessException e) {
            System.out.println(e);
        } catch (InstantiationException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new MyTomcat(8080).start();
    }
}

