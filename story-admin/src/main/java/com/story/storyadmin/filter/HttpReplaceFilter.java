package com.story.storyadmin.filter;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 后台做xss,防sql注入校验
 * 参考： https://www.jianshu.com/p/7e55801fa5d1?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
 */
//@Component
//@ServletComponentScan
//@WebFilter(urlPatterns = "/*")//此处的url根据实际情况而定
public class HttpReplaceFilter implements Filter {
    // 接口白名单
    private static final Set<String> ALLOW_PATHS = new HashSet<>(Arrays.asList("/captchaImage"));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String path = ((HttpServletRequest)request).getRequestURI().substring(((HttpServletRequest) request).getContextPath().length());
        //如果是在ALLOW_PATHS的集合中，那么就允许这种请求直接通过
        if (ALLOW_PATHS.contains(path)){
            filterChain.doFilter(request, response);
        }
        System.out.println("自定义过滤器！！");
        //HttpServletRequest没有提供相关的set方法来修改body，所以需要用修饰类
        ServletRequest requestWrapper = new BodyRequestWrapper((HttpServletRequest) request);

        filterChain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {

    }
}