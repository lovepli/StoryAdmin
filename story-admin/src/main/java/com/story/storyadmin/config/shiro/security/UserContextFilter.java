package com.story.storyadmin.config.shiro.security;

import com.story.storyadmin.config.shiro.LoginUser;
import com.story.storyadmin.constant.SecurityConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Springboot中实现Filter实现的途径
 * 1、实现javax.servlet.Filter接口，注解方式配置Filter 实现javax.servlet.Filter接口,
 * 2、通过FilterRegistrationBean类控制配置
 * 3、通过org.springframework.boot.context.embedded.ServletContextInitializer接口实现类配置
 *
 * filter的三种典型应用：
 * 1、可以在filter中根据条件决定是否调用chain.doFilter(request, response)方法， 即是否让目标资源执行
 * 2、在让目标资源执行之前，可以对request\response作预处理，再让目标资源执行
 * 3、在目标资源执行之后，可以捕获目标资源的执行结果，从而实现一些特殊的功能
 */

/** 应用上下文filter  拦截所有url*/
@WebFilter(filterName = "userContextFilter", urlPatterns = "/*")
public class UserContextFilter implements Filter {

    private  Logger logger = LoggerFactory.getLogger(UserContextFilter.class);
    /**
     * 初始化
     * Filter的创建和销毁由WEB服务器负责。 web 应用程序启动时，web 服务器将创建Filter 的实例对象，并调用其init方法，完成对象的初始化功能，
     * 从而为后续的用户请求作好拦截的准备工作，filter对象只会创建一次，init方法也只会执行一次。通过init方法的参数，可获得代表当前filter配置信息的FilterConfig对象。
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("####AnnoantationFilter init……"+filterConfig.getFilterName());
    }

    /**
     * 执行filter
     * 参考：https://my.oschina.net/wangnian/blog/647976
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 对request和response进行一些预处理
        // 这里所做的处理是在某次线程的某次请求url调用某个接口时，先进行判断用户是否登录过了(即http中存在Authorization请求头属性)，
        // 登录过了就将用户登录对象信息LoginUser先存到上下文对象中(其实就是将将用户信息设置为线程内部属性ThreadLocal)，
        // 然后该请求就可以从该请求的线程中取出来用户登录信息   TODO 这里的作用和从session中取出用户信息是一样的处理方式，感觉这样做复杂了很多，因为这里是jwt无状态形式的，服务端不存储用户信息，所以就不用session来存储用户信息了
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String authorization = httpServletRequest.getHeader(SecurityConsts.REQUEST_AUTH_HEADER);
        //判断用户是否登录，有登录就记录登录信息存储到userContext中，没有就放行
        if(authorization!=null){
            String account = JwtUtil.getClaim(authorization, SecurityConsts.ACCOUNT);
            //这个登录用户对象的构造器只传了account,所以只存了account值
            LoginUser loginUser = new LoginUser(account);
            //将登录用户对象存入到ThreadLocal线程内部属性中
            try (UserContext context = new UserContext(loginUser)) {
                logger.info("####AnnoantationFilter doFilter 执行前");
                // 让目标资源执行，放行
                filterChain.doFilter(servletRequest, servletResponse);
                logger.info("####AnnoantationFilter doFilter 执行后");
            }
        }else{
            // 让目标资源执行，放行
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * 销毁
     * Web容器调用destroy方法销毁Filter。destroy方法在Filter的生命周期中仅执行一次。在destroy方法中，可以释放过滤器使用的资源。
     */
    @Override
    public void destroy() {
        logger.info("####Annoantation destory-------");
    }
}
