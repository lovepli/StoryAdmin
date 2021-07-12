package com.story.storyadmin.filter.xss;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *跨域：由于浏览器的安全性限制，不允许AJAX访问 协议不同、域名不同、端口号不同的 数据接口;
 * 前后端都需要设置允许跨域
 *
 * 这个过滤器主要在前端页面进行xss防范
 */
//@Component
//@ServletComponentScan
@WebFilter(urlPatterns = "/*")
public class CrosXssFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(CrosXssFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        //request.setCharacterEncoding("utf-8");
        //response.setContentType("text/html;charset=utf-8");
        ////跨域设置
        //if(response instanceof HttpServletResponse){
        //    HttpServletResponse httpServletResponse=(HttpServletResponse)response;
        //    //通过在响应 header 中设置 ‘*’ 来允许来自所有域的跨域请求访问。
        //    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        //    //通过对 Credentials 参数的设置，就可以保持跨域 Ajax 时的 Cookie
        //    //设置了Allow-Credentials，Allow-Origin就不能为*,需要指明具体的url域
        //    //httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        //    //请求方式
        //    httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
        //    //（预检请求）的返回结果（即 Access-Control-Allow-Methods 和Access-Control-Allow-Headers 提供的信息） 可以被缓存多久
        //    httpServletResponse.setHeader("Access-Control-Max-Age", "86400");
        //    //首部字段用于预检请求的响应。其指明了实际请求中允许携带的首部字段
        //    httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
        //}


        // 获取后台直接postman测试调用接口收到的数据
        //Map parametersMap = request.getParameterMap();
        //// 获取遍历form-data表单提交输入的内容
        //Iterator it = parametersMap.entrySet().iterator();
        //String data=null;
        //while (it.hasNext()) {
        //    Map.Entry entry = (Map.Entry) it.next();
        //    String[] value = (String[]) entry.getValue();
        //    //for (int i = 0; i < value.length; i++) {
        //    //    logger.info("输入的参数："+value[i]);
        //    //    data= cleanXSS(value[i]);
        //    //}
        //    data= cleanXSS(value.toString());
        //}
        //logger.info("1111过滤后的参数："+data);

        //sql,xss过滤
        HttpServletRequest httpServletRequest=(HttpServletRequest)request;
       // String result=httpServletRequest.
        System.out.println("记录请求日志");
        logger.info("CrosXssFilter.......orignal url:{},ParameterMap:{}",httpServletRequest.getRequestURI(), JSONObject.toJSONString(httpServletRequest.getParameterMap()));
        XssHttpServletRequestWrapper xssHttpServletRequestWrapper=new XssHttpServletRequestWrapper(httpServletRequest);
        chain.doFilter(xssHttpServletRequestWrapper, response);
        logger.info("CrosXssFilter..........doFilter url:{},ParameterMap:{}",xssHttpServletRequestWrapper.getRequestURI(), JSONObject.toJSONString(xssHttpServletRequestWrapper.getParameterMap()));
        System.out.println("记录响应日志");
    }
    @Override
    public void destroy() {

    }

}