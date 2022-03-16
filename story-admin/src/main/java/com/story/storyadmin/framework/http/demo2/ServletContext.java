package com.story.storyadmin.framework.http.demo2;

import java.util.HashMap;
import java.util.Map;

/**
 * 5.处理类生成工厂#
 * 为了编程的灵活性，我们将该httpserver写出可以根据一个xml配置文件知道有多少种分别处理什么url请求的类，该xml就负责记录这种映射关系
 * 首先需要一个ServletContext类，该类有两个属性private Map<String,String> servlet和private Map<String,String> map，分别用来记录名称到类存储地址之间的映射和url到名称之间的映射
 */
public class ServletContext {

    //名称到类存储地址之间的映射
    private Map<String,String> servlet;
    //url到名称之间的映射
    private Map<String,String> map;


    public void setServlet(Map<String, String> servlet) {
        this.servlet = servlet;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
    public ServletContext() {
        servlet=new HashMap<String, String>();
        map=new HashMap<String, String>();
    }

    public Map<String, String> getServlet() {
        return servlet;
    }

    public Map<String, String> getMap() {
        return map;
    }

}
