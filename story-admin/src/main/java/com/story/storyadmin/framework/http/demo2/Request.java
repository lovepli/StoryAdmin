package com.story.storyadmin.framework.http.demo2;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 2.封装Request和Response#
 * 为了方便解析Request和返回Response，我们需要抽象出两个对象(Request类和Response对象)。
 *
 * 首先封装Request
 *
 * 　　Request对象的作用是解析请求信息，将请求方式，请求资源路径，请求参数解析分离出来，构建一个Request对象我们需要传入一个参数------>输入流。这样我们就可以从输入流中读入请求信息然后开始解析。
 */
public class Request {
    private String url;
    private String method;

    private String info;
    private Map<String,List<String>> paramterMapValues;
    private InputStream is;



    public static final String CRLF="\r\n";
    public static final String BANK=" ";

    private Request() {
        url=null;
        method=null;
        is=null;
        info=null;
        paramterMapValues=new HashMap<String,List<String>>();

    }

    public Request(InputStream is) {
        this();
        this.is=is;
        try {
            create();
        } catch (IOException e) {
            this.is=null;
            System.out.println("Request 创建错误");
            return;
        }

    }

    public String getMethod() {
        return this.method;
    }



    public String getUrl() {
        return url;
    }

    private void create() throws IOException{
        getInfo();
        getUrlAndParamter();

    }

    /**
     * 根据页面name获取对应所有值
     * @return String[]
     */
    public String[] getparamterValues(String name) {
        List<String> paramterValues=null;
        if((paramterValues=paramterMapValues.get(name))==null) {
            return null;
        }else {
            return paramterValues.toArray(new String[0]);
        }
    }

    /**
     * 根据页面name获取单个值
     * @return String[]
     */
    public String getparamterValue(String name) {
        String values[]=getparamterValues(name);
        if(values==null)
            return null;
        else
            return values[0];
    }
    /**
     * 得到请求信息
     * @throws IOException
     */
    private void getInfo() throws IOException {
        byte bytes[]=new byte[20480];
        int len=is.read(bytes);
        info=new String(bytes,0,len);
    }
    /**
     * 处理得到url资源请求路径和请求参数值
     * @return
     */
    private void getUrlAndParamter(){
        String firstline=info.substring(0,info.indexOf(CRLF));
        //System.out.println("FirstLine:  "+firstline);
        String paramter="";
        this.method=firstline.substring(0,firstline.indexOf("/")).trim();
        String tempurl=
                firstline.substring(firstline.indexOf("/"),firstline.indexOf("HTTP/")).trim();
        System.out.println("tempurl:  "+tempurl);
        if(this.method.equalsIgnoreCase("post")) {
            this.url=tempurl;
            paramter=info.substring(info.lastIndexOf(CRLF)).trim();
        }else {
            if(tempurl.contains("?")) {
                //split函数里面的参数实际上需要的是正则表达式,普通字符串还好，?号是特殊字符
                String[] urlarry=tempurl.split("\\?");
                this.url=urlarry[0];
                paramter=urlarry[1];
            }else {
                this.url=tempurl;
            }
        }
        //解析参数
        parseParmter(paramter);
        return;
        //System.out.println(this.url);
        //System.out.println(paramter);
    }
    /**
     * 解析请求参数,转换成键值对形式
     * @param str
     */
    private void parseParmter(String str) {
        if(str==null||str.equals("")||str.trim().equals(""))
            return;
        StringTokenizer st=new StringTokenizer(str,"&");
        while(st.hasMoreTokens()) {
            String temp=st.nextToken();
            String[] KeyAndValues=temp.split("=");
            if(KeyAndValues.length==1) {
                KeyAndValues=Arrays.copyOf(KeyAndValues,2);
                KeyAndValues[1]=null;
            }
            String key=KeyAndValues[0].trim();
            String value=KeyAndValues[1]==null?null:KeyAndValues[1].trim();
            if(!paramterMapValues.containsKey(KeyAndValues[0])){
                paramterMapValues.put(key,new ArrayList<String>());
            }
            paramterMapValues.get(key).add(decode(value, "gbk"));
        }
    }
    /**
     * 解决中文编码问题
     * @param value
     * @param code
     * @return
     */
    private String decode(String value,String code) {
        try {
            return java.net.URLDecoder.decode(value, code);
        } catch (UnsupportedEncodingException e) {

        }
        return null;
    }
}
