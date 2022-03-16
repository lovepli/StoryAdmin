package com.story.storyadmin.framework.http.demo2;

public class loginServlet extends Servlet {

    @Override
    public void doGet(Request req, Response rep) {
        rep.println("<head>" +
                "    <title>test</title>" +
                "</head>" +
                "<body>" +
                "<p>hellow</p>"+
                "<form action=\"http://localhost:8888/index\" method=\"POST\">" +
                "name: <input type=\"text\" name=\"name\">" +
                "password: <input type=\"password\" name=\"pwd\">" +
                "<input type=\"submit\" value=\"submit\">" +
                "</form>" +
                "</body>");
    }

    @Override
    public void doPost(Request req, Response rep) {
        this.doGet(req, rep);
    }

}