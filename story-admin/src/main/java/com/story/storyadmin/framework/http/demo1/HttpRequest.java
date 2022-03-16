package com.story.storyadmin.framework.http.demo1;

import java.io.InputStream;


/**   https://blog.csdn.net/qq_32099833/article/details/109397967
 * 1、编写Request和Response类，为请求和响应建模。
 */
public class HttpRequest {
    private InputStream inputStream;
    private String requestData;// 请求数据

    public HttpRequest(InputStream inputStream) {
        try {
            this.inputStream = inputStream;
            // 读取请求数据
            byte[] bytes = new byte[1024 * 2];
            int len = inputStream.read(bytes);
            requestData = new String(bytes, 0, len);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 解析请求的URI
     * @return
     */
    public String getRemoteURI(){
        int oneSpacing = requestData.indexOf(" ");
        int twoSpacing = requestData.indexOf(" ", oneSpacing + 1);
        return requestData.substring(oneSpacing, twoSpacing).trim();
    }
}


