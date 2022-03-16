package com.story.storyadmin.framework.tomcatDemo.tomcat2;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: 59688
 * @date: 2021/9/27
 * @description: 写一个迷你版的Tomcat https://www.jianshu.com/p/dce1ee01fb90
 */
public class MyRequest {
    private String url;
    private String method;

    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];
        int length = 0;
        if ((length  = inputStream.read(httpRequestBytes)) > 0) {
            httpRequest = new String(httpRequestBytes , 0 , length);
        }
        String httpHead = httpRequest.split("\n")[0];
        url = httpHead.split("\\s")[1];
        method = httpHead.split("\\s")[0];
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "MyRequest{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}

