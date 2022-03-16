package com.story.storyadmin.Interceptor.demo2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义拦截器
 * @author shengwu ni
 * @date 2018/08/03
 * 定义拦截器，只需要实现 HandlerInterceptor 接口，HandlerInterceptor 接口是所有自定义拦截器或者 Spring Boot 提供的拦截器的鼻祖
 */
public class MyInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    /**
     * 定义拦截器，只需要实现 HandlerInterceptor 接口，HandlerInterceptor 接口是所有自定义拦截器或者 Spring Boot 提供的拦截器的鼻祖
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String methodName = method.getName();
        logger.info("====拦截到了方法：{}，在该方法执行之前执行====", methodName);

        //TODO 应用1  判断用户有没有登录
        // 判断用户有没有登陆，一般登陆之后的用户都有一个对应的token
        //String token = request.getParameter("token");
        //if (null == token || "".equals(token)) {
        //    logger.info("用户未登录，没有权限执行……请登录");
        //    return false;
        //}
        //重启项目，在浏览器中输入 localhost:8080/interceptor/test 后查看控制台日志，发现被拦截，如果在浏览器中输入 localhost:8080/interceptor/test?token=123 即可正常往下走。

        //TODO 应用2 取消拦截操作
        // 通过方法，可以获取该方法上的自定义注解，然后通过注解来判断该方法是否要被拦截
        // @UnInterception 是我们自定义的注解
        UnInterception unInterception = method.getAnnotation(UnInterception.class);
        if (null != unInterception) {
            return true;
        }

        // 返回true才会继续执行，返回false则取消当前请求
        return true;
    }

    /**
     * postHandle(……) 方法：该方法的执行时机是，当某个 url 已经匹配到对应的 Controller 中的某个方法，且在执行完了该方法，但是在 DispatcherServlet 视图渲染之前。所以在这个方法中有个 ModelAndView 参数，可以在此做一些修改动作。
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("执行完方法之后进执行(Controller方法调用之后)，但是此时还没进行视图渲染");
    }

    /**
     * afterCompletion(……) 方法：顾名思义，该方法是在整个请求处理完成后（包括视图渲染）执行，这时做一些资源的清理工作，这个方法只有在 preHandle(……) 被成功执行后并且返回 true 才会被执行。
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("整个请求都处理完咯，DispatcherServlet也渲染了对应的视图咯，此时我可以做一些清理的工作了");
    }
}