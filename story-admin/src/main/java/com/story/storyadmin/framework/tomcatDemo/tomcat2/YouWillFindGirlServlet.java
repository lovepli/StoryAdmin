package com.story.storyadmin.framework.tomcatDemo.tomcat2;


/**
 * @author :dongshuo
 * @date : 2018/12/16 13:15
 * @desc :
 */
public class YouWillFindGirlServlet extends MyServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("method:GET 你今年一定会找到女朋友!");
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("method:POSt 你今年一定会找到女朋友!");
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}

