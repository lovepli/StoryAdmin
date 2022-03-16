package com.story.storyadmin.framework.http.demo2;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * 然后需要一个Webapp类
 * 该类负责读入xml文件并且进行解析，根据xml文件配置的内容，为分发器生成不同的servlet处理类。
 * 生成不同的类利用的Java的类加载机制，可以在代码中获取class信息然后new一个类出来
 * 解析xml文件我们使用的是SAXParser解析器，为了利用该解析器，我们还需要实现一个继承于DefaultHandler的类
 */

/***
 * 利用Java手写简单的httpserver  https://www.cnblogs.com/DLKKILL/p/10368975.html
 */
public class Webapp {
    private static ServletContext servletcontext;
    static{
        servletcontext=new ServletContext();
        Map<String,String> servlet=servletcontext.getServlet();
        Map<String,String> map=servletcontext.getMap();
//		servlet.put("index", "top.dlkkill.httpserver.indexServlet");
//		servlet.put("login", "top.dlkkill.httpserver.loginServlet");
//		map.put("/login", "login");
//		map.put("/index", "index");
        SAXParserFactory parserfactor=SAXParserFactory.newInstance();
        WebHandler hd=new WebHandler();
        SAXParser parser;
        try {
            parser=parserfactor.newSAXParser();
            if(null==Thread.currentThread().getContextClassLoader().getResourceAsStream("top/dlkkill/httpserver/web.xml"))
                System.out.println("error");
            parser.parse(
                    Thread.currentThread().getContextClassLoader()
                            .getResourceAsStream("top/dlkkill/httpserver/web.xml"),//更改目录 com.story.storyadmin.framework.http.demo2
                    hd);
            List<Entity> entityList=hd.getEntityList();
            List<Mapping> mappingList=hd.getMappingList();
            for (Mapping mapping : mappingList) {
                String name=mapping.getName();
                List<String> urlList=mapping.getUrl();
                for (String url:urlList) {
                    map.put(url, name);
                }
            }
            for (Entity entity:entityList) {
                String servletname=entity.getName();
                String clz=entity.getClz();
                servlet.put(servletname, clz);
            }
        } catch (ParserConfigurationException | SAXException |IOException e) {

        }


    }
    public static Servlet getServlet(String url) {
        Map<String,String> servlet=servletcontext.getServlet();
        Map<String,String> map=servletcontext.getMap();
        String className=servlet.get(map.get(url));
        Servlet temp=null;
        Class<?> clz=null;
        try {
            System.out.println("classname:"+className);
            if(className!=null)
                clz=Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
        try {
            if(clz!=null)
                temp=(Servlet)clz.newInstance();
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
        return temp;
    }
}

