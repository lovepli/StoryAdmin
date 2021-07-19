package com.story.storyadmin.config.mongo;

import com.alibaba.fastjson.JSONObject;
import com.story.storyadmin.config.shiro.LoginUser;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.domain.entity.sysmgr.SysLog;
import com.story.storyadmin.service.sysmgr.SysLogService;
import com.story.storyadmin.utils.DateMethordUtil.DateUtils;
import com.story.storyadmin.utils.netMethordUtil.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 系统日志切面
 * @Aspect - 将类标记为包含通知方法的类。
 */
@Aspect
@Order(5)
@Component
public class StorySysLogAspect {

    @Autowired
    private SysLogService sysLogService;

    public static final String ACCOUNT = "匿名用户";
    private static final String DEFAULT_PKG = "com.story.storyadmin.web";
    private static final String SHORT_PKG = "s.c";

    /**
     * ThreadLocal线程内部属性, 在所有方法里都可以直接使用 startTime 和 logId 对象
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    ThreadLocal<String> logId = new ThreadLocal<>();

    /**
     * @Pointcut - 将函数标记为切入点，扩后里面的可以是方法的全路径名，也可以是自定义注解，将注解标记的函数记为切入点
     */
    @Pointcut("@annotation(com.story.storyadmin.config.mongo.SysLogAnnotation)")
    public void storySysLog() {
    }

  /**
   * @Before - 将函数标记为在切入点覆盖的方法之前执行的通知。 @Before 是一种通知类型，可以确保在方法执行之前运行通知。
   * @param joinPoint 连接点(JoinPoint)代表您的应用程序中可以插入AOP方面的一个点/位置。 您也可以说，这是应用程序中使用Spring AOP框架执行操作的实际位置
   */
  @Before("storySysLog()")
  public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //开始时间 设置为系统当前时间
        startTime.set(System.currentTimeMillis());
        //日志ID 为uuid随机字符串
        logId.set(UUID.randomUUID().toString());

        //获取系统当前登录用户
        LoginUser loginUser = UserContext.getCurrentUser();
        final String method = request.getMethod();
        final String url = request.getRequestURL().toString();
        final String uri = request.getRequestURI();
        final String ip = IPUtils.getIpAddr(request);
        //当前登录用户的账户名
        final String account = loginUser != null ? loginUser.getAccount() : ACCOUNT;

        //获取连接点 的类
        final String clazz = joinPoint.getSignature().getDeclaringTypeName().replaceAll(DEFAULT_PKG, SHORT_PKG);
        //获取方法
        final String methodName = joinPoint.getSignature().getName();
        //获取方法参数数组
        final Object[] args = joinPoint.getArgs();
        // TODO 记录返回方法返回值待开发
        //使用Alibaba的fastJson工具将参数数组转换为json字符串
        final String params = JSONObject.toJSONString(args);
        //记录 系统日志信息，recordLog方法是一个异步方法
        sysLogService.recordLog(new SysLog(logId.get(), account, ip, method, url, uri, clazz, methodName, DateUtils.currentDate(),
                0L, params), null, null);
    }

  /**
   * @AfterReturning 是一种通知类型，可确保方法执行成功后运行通知
   * @Pointcut - 将函数标记为切入点
   * @After - 将函数标记为在切入点覆盖的方法之后执行的通知。execution( expression ) - 涵盖应用通知的方法的表达式。
   * returning - 要返回的变量的名称。
   *
   * @param ret
   */
  @AfterReturning(returning = "ret", pointcut = "storySysLog()")
  public void doAfterReturning(Object ret) {

      // TODO 这里为什么要在切点执行完再执行这个方法，只记录日志ID和耗时
        sysLogService.recordLog(null, logId.get(), System.currentTimeMillis() - startTime.get());
    }
}
