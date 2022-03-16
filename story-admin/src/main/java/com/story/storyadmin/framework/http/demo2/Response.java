package com.story.storyadmin.framework.http.demo2;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * 然后我们进行封装Response.
 * 　　Response主要分为两部分(响应头和响应体)
 * 　　构建Response需要传入通过Socket获得的输出流，利用这个输出流我们才可以写回信息
 * 响应头格式较为固定，所有我们在Response中应该有一个私有方法根据响应码自动构建响应头信息。
 * 　　然后我们还需要一个共有方法void println(String msg)，其他类利用该方法写入对应的响应体部分。
 * 最后需要有一个send()方法，自动整合响应体和响应体内容，并将所有内容发送出去。
 */
public class Response {

    public static final String CRLF="\r\n";
    public static final String BANK=" ";

    private StringBuilder headerinfo;
    private StringBuilder content;
    private BufferedWriter wr;
    private int len;

    public Response() {
        // TODO Auto-generated constructor stub
        headerinfo=new StringBuilder();
        content=new StringBuilder();
        if(content==null)
            System.out.println("error");
        len=0;
    }

    public Response(OutputStream os){
        this();
        wr=new BufferedWriter(new OutputStreamWriter(os));
    }

    public void createHeaderinfo(int code) {
        System.out.println("code is "+code);
        headerinfo.append("HTTP/1.1").append(BANK);
        switch (code) {
            case 200:
                headerinfo.append(code).append(BANK).append("OK");
                break;
            case 404:
                headerinfo.append(code).append(BANK).append("404 not found");
                break;
            case 500:
                headerinfo.append(code).append(BANK).append("error");
                break;
            default:
                headerinfo.append(code).append(BANK).append("error");
                break;
        }
        headerinfo.append(CRLF);
        headerinfo.append("Server:dlkkill server/0.1").append(CRLF);
        headerinfo.append("Date:").append(new Date()).append(CRLF);
        headerinfo.append("Content-type:text/html;charset=GBK").append(CRLF);
        //正文长度，字节长度
        headerinfo.append("Content-Length:").append(len).append(CRLF);
        //空行分隔符
        headerinfo.append(CRLF);
        //System.out.println(headerinfo.toString());
    }

    public Response println(String msg) {
        //System.out.println(msg);
        if(content==null)
            System.out.println(msg);
        content.append(msg);
        len+=msg.getBytes().length;
        return this;
    }

    public void pushToClient(int code) throws IOException {
        if(wr==null) {
            code=500;
        }
        createHeaderinfo(code);
        wr.write(headerinfo.toString());
        wr.write(content.toString());
        wr.flush();
    }
}
