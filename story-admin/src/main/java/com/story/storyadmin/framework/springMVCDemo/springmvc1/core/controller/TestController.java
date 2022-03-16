package com.story.storyadmin.framework.springMVCDemo.springmvc1.core.controller;

import com.story.storyadmin.framework.springMVCDemo.springmvc1.annotation.MyController;
import com.story.storyadmin.framework.springMVCDemo.springmvc1.annotation.MyRequestMapping;
import com.story.storyadmin.framework.springMVCDemo.springmvc1.annotation.MyRequestParam;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自己手写一个SpringMVC框架(简化) https://my.oschina.net/liughDevelop/blog/1622646
 * https://github.com/qq53182347/liughMVC
 */
@MyController
@MyRequestMapping("/test")
public class TestController {



    @MyRequestMapping("/doTest")
    public void test1(HttpServletRequest request, HttpServletResponse response,
                      @MyRequestParam("param") String param){
        System.out.println(param);
        try {
            response.getWriter().write( "doTest method success! param:"+param);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @MyRequestMapping("/doTest2")
    public void test2(HttpServletRequest request, HttpServletResponse response){
        try {
            response.getWriter().println("doTest2 method success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}