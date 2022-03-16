package com.story.storyadmin.framework.http.demo1;

/**
 * 手写Http服务器 https://blog.csdn.net/qq_32099833/article/details/109397967
 * 4、编写Bootstrap启动类，启动HTTP服务。
 */
public class Bootstrap {
    public static void main(String[] args) {
        new HttpServer().start();
    }
}
