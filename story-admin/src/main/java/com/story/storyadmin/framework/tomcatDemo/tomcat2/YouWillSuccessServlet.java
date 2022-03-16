package com.story.storyadmin.framework.tomcatDemo.tomcat2;

import java.io.IOException;

/**
 * @author :dongshuo
 * @date : 2018/12/16 13:12
 * @desc :
 */
public class YouWillSuccessServlet extends MyServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("method:GET 今年你一定会成功!");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("method:POST 今年你一定会成功!");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

