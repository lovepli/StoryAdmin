package com.story.storyadmin.framework.tomcatDemo.tomcat2;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: 59688
 * @date: 2021/9/27
 * @description:
 */
public class MyResponse {
    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html\n")
                .append("\r\n")
                .append("<html><meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\"><body>")
                .append(content)
                .append("</body</html>");

        outputStream.write(new String(httpResponse.toString().getBytes(),"UTF-8").getBytes());
        outputStream.close();

    }
}
