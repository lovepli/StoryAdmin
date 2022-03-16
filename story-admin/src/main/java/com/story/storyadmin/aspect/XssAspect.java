package com.story.storyadmin.aspect;

import com.story.storyadmin.utils.IllegalStrFilterUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author: 59688
 * @date: 2021/3/24
 * @description: 防止xss攻击切面类 aop判断是否存在sql注入
 */
//@Aspect
//@Component
public class XssAspect {

    /**
     * 切面类
     */
    private static final Logger logger = LoggerFactory.getLogger(XssAspect.class);

    // 存在SQL注入风险
    private static final String IS_SQL_INJECTION = "输入参数存在SQL注入风险";
    private static final String UNVALIDATED_INPUT = "输入参数含有非法字符";
    private static final String ERORR_INPUT = "输入的参数非法";

    //切入点
    @Pointcut("execution(* com.story.storyadmin.web.*.*(..))")
    public void webLog() {
    }

    //前置通知
    @Before("webLog()")
    public void before(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //logger.info("ARGS2数组第一个对象 : " + joinPoint.getArgs()[0].toString());
        //for(Object o:joinPoint.getArgs()){
        //    // 对数组参数一个个过滤
        //    logger.info("参数-----"+o.toString());
        //
        //}
    }

    //后置通知
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void afterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("方法的返回值 : " + ret);
    }

    //后置异常通知
    @AfterThrowing("webLog()")
    public void returnThrowing(JoinPoint joinPoint) {
        logger.info("方法异常时执行");
    }

    //最终通知
    @After("webLog()")
    public void after(JoinPoint joinPoint) {
        logger.info("方法最终执行");
    }

    //环绕通知
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) {
        logger.info("方法环绕start.....");
        try {
            Object[] args = pjp.getArgs();// 参数
            String str = String.valueOf(args); // TODO 这个得到的结果是一个Stirng对象
            //for(Object o:args){
            //    // 对数组参数一个个过滤
            //    logger.info("--------方法的参数为:"+o.toString());
            //}
            // 参数数组转字符串
             String strInput= Arrays.toString(args);
             // 判断是否存在sql注入
            if (!IllegalStrFilterUtil.sqlStrFilter(strInput)) {
                logger.info(IS_SQL_INJECTION);
                new RuntimeException(ERORR_INPUT);
            }
            // 判断是否存在特殊字符串
            if (!IllegalStrFilterUtil.isIllegalStr(strInput)) {
                logger.info(UNVALIDATED_INPUT);
                new RuntimeException(ERORR_INPUT);
            }

            // 后台参数进行过滤
            // String ss=IllegalStrFilterUtil.cleanXSS(strInput);
            // 过略后的参数为ss
            // System.out.println("----参数过滤后的结果：----:"+ss);
            Object o = pjp.proceed();
            logger.info("方法环绕proceed，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}