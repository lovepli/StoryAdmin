package com.story.storyadmin.basic.net.http.demo2;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.charset.Charset;

/**
 * 2、编写HTTP处理器，处理客户端请求。
 */
public class HttpProcessor {
    // HTTP服务根目录
    private static final String ROOT_DIR = System.getProperty("user.dir") + File.separator + "web";

    public void process(HttpRequest request, HttpResponse response) {
        String uri = request.getRemoteURI();
        File file = new File(ROOT_DIR, uri);
        if (file.exists()) {
            response.write("HTTP/1.1 200 OK\n");
            response.write("content-length: " + file.length() + "\n\n");
            response.write(FileUtil.readString(file, Charset.defaultCharset()));
        }else {
            // 文件不存在，返回404页
            file = new File(ROOT_DIR, "404.html");
            response.write("HTTP/1.1 404 Not Found\n");
            response.write("content-length: " + file.length() + "\n\n");
            response.write(FileUtil.readString(file, Charset.defaultCharset()));
        }
    }
}

