package com.story.storyadmin.async;

import com.story.storyadmin.web.async.EmailController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.Map;



/**
 * @author: lipan
 * @date: 12:35 上午
 * @description:
 */
@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("异步单元测试")
@Slf4j
public class AsyncTest2 {


    @Autowired
    private ApplicationContext applicationContext;


    @Test
    @DisplayName("测试异步方法")
    public void asyncCall() {
        try {
            System.out.println("1、开始执行异步任务！！");
            // 这样调用同类下的异步方法是不起作用的
             //this.testAsyncTask();
            // 通过上下文获取自己的代理对象调用异步方法
            EmailController emailController = (EmailController) applicationContext.getBean(EmailController.class);
            // 或者使用的封装方法获取
            // EmailController emailController = ApplicationContextUtil.getBean(EmailController.class);
            emailController.testAsyncTask();
            System.out.println("2、异步任务调用成功！！");
        } catch (Exception e) {
            System.out.println("2、异步任务调用失败！！");
            log.error("error!", e);
        }
    }


    // 注意一定是public,且是非static方法
    @Async("taskExecutor") // 使用自定义线程池
    @Test
    @DisplayName("测试异步方法")
    public void testAsyncTask() throws InterruptedException {
        System.out.println("异步任务开始执行！");
        Thread.sleep(10000);
        System.out.println("异步任务执行完成！");
    }

    /**
     * 2、开启 cglib 代理，手动获取 Spring 代理类, 从而调用同类下的异步方法。
     * <p>首先，在启动类上加上 @EnableAspectJAutoProxy(exposeProxy = true) 注解。 TODO 本项目中使用的是第一种方式进行一步调用！！
     * <p>代码实现，如下：
     * 三、异步请求与异步调用的区别 两者的使用场景不同，异步请求用来解决并发请求对服务器造成的压力，从而提高对请求的吞吐量；
     * 而异步调用是用来做一些非主线流程且不需要实时计算和响应的任务，比如同步日志到kafka 中做日志分析等。
     * <p>异步请求是会一直等待 response 相应的，需要返回结果给客户端的；而异步调用我们往往会马上返回给客户端响应，
     * 完成这次整个的请求，至于异步调用的任务后台自己慢慢跑就行，客户端不会关心。
     */
    public Map<String, Object> asyncCall2() {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            boolean isAop = AopUtils.isAopProxy(EmailController.class);//是否是代理对象；
            boolean isCglib = AopUtils.isCglibProxy(EmailController.class);  //是否是CGLIB方式的代理对象；
            boolean isJdk = AopUtils.isJdkDynamicProxy(EmailController.class);  //是否是JDK动态代理方式的代理对象；
            //以下才是重点!!!
            EmailController emailService = (EmailController)applicationContext.getBean(EmailController.class);
            EmailController proxy = (EmailController) AopContext.currentProxy();
            System.out.println(emailService == proxy ? true : false);
            proxy.testAsyncTask();
            System.out.println("end!!!");
            resMap.put("code", 200);
        } catch (Exception e) {
            resMap.put("code", 400);
            log.error("error!", e);
        }
        return resMap;
    }


}
