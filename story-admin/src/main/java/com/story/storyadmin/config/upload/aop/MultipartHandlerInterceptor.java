/*
 *  Copyright HNA©2017. All rights reserved.
 */
package com.story.storyadmin.config.upload.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.story.storyadmin.config.upload.annotation.FileSlotDisabled;
import com.story.storyadmin.config.upload.support.MultipartContextHolder;
import com.story.storyadmin.config.upload.support.MultipartRequestWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Multipart请求拦截器. 把request对象放到当前Thread中， 供后面的${@link MultipartAspect}使用。
 *
 * 知识点：spring mvc 拦截器的使用
 * spring mvc 拦截器根 spring 拦截器相比，它里面能够获取HttpServletRequest和HttpServletResponse?等 web 对象实例。
 * spring mvc 拦截器的顶层接口是：HandlerInterceptor，包含三个方法：
 * preHandle?目标方法执行前执行
 * postHandle?目标方法执行后执行
 * afterCompletion?请求完成时执行
 *
 * 为了方便我们一般情况会用 HandlerInterceptor 接口的实现类HandlerInterceptorAdapter类。
 * 假如有权限认证、日志、统计的场景，可以使用该拦截器。
 * 第一步，继承HandlerInterceptorAdapter类定义拦截器：
 * 第二步，将该拦截器注册到 spring 容器：
 * 第三步，在请求接口时 spring mvc 通过该拦截器，能够自动拦截该接口，并且校验权限。
 *
 * 该拦截器其实相对来说，比较简单，可以在DispatcherServlet类的doDispatch方法中看到调用过程：
 *
 */
public class MultipartHandlerInterceptor extends HandlerInterceptorAdapter {

    /**
     * 是否开启自动保存
     */
	@Value("${file.multipart.autoSaveEnabled:true}")
    private Boolean autoSaveEnabled = true;

    /**
     * 目标方法执行前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        if (!this.autoSaveEnabled) {
            return super.preHandle(request, response, handler);
        }

        MultipartRequestWrapper requestWrapper = new MultipartRequestWrapper(request);

        if (!requestWrapper.isMultipartRequest()) {
            return super.preHandle(request, response, handler);
        }

        HandlerMethod method = (HandlerMethod) handler;

        // 如果有@FileSlotDisabled注解， 不继续处理。
        FileSlotDisabled fileSlotDisabledAnnotation = method
                .getMethodAnnotation(FileSlotDisabled.class);
        if (fileSlotDisabledAnnotation != null) {
            return super.preHandle(request, response, handler);
        }

        RequestMapping requestMappingAnnotation = method
                .getMethodAnnotation(RequestMapping.class);
        if (requestMappingAnnotation == null) {
            return super.preHandle(request, response, handler);
        }

        MultipartContextHolder.setRequestWrapper(requestWrapper);

        return super.preHandle(request, response, handler);
    }

    /**
     * 请求完成时执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        // 清理
        MultipartContextHolder.resetRequestWrapper();
    }

}
