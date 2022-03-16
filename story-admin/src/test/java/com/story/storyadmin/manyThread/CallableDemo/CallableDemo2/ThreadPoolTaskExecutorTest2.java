package com.story.storyadmin.manyThread.CallableDemo.CallableDemo2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author: 59688
 * @date: 2021/7/23
 * @description: 异常处理测试 https://blog.csdn.net/w_t_y_y/article/details/102817576
 */
@Slf4j
//@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
//@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("线程池ThreadPoolTaskExecutor的提交方法execute和submit的异常处理测试")
public class ThreadPoolTaskExecutorTest2 {

    @Autowired(required=true)
    private ThreadPoolTaskExecutorService threadPoolTaskExecutorService;

    @Test
    @DisplayName("测试异常处理submit")
    //public String submit(String param){
    public void submit(){
        String param="123";
        param = threadPoolTaskExecutorService.submit(param);
        //return param;
        System.out.println("方法返回值："+param);
    }

    @Test
    @DisplayName("测试异常处理execute")
    //public String execute(String param){
    public void execute(){
        String param="123";
        String res = threadPoolTaskExecutorService.execute(param);
        //return res;
        System.out.println("方法返回值："+param);
    }

    /**
     * 二、处理异常的区别：Callable执行call时遇到异常会抛出，而Runnable执行run时遇到异常并不会抛出。
     * 因为在两个task里面都加了异常1/0，所以请求这两个方法都不会往数据库插入数据。call方法抛出异常，service层捕获到后return就不再插入数据了，run方法自己遇到异常就终止了也不再往下执行。区别就再于二者对于异常的处理，调用sumbit方法执行时可以捕获异常，这样就可以自定义处理如把异常抛出给调用处（controller层），而execute的run方法遇到异常就自己终止了，主线程无法感知其运行成功与否。
     *
     * 有的人可能会想在调用execute时加上try...catch....，这个肯定是不可以的，这个try...catch...捕获的只是
     * threadPoolTaskExecutor.execute(new UserRunnableTask(param,userMapper));这个任务提交有没有异常，而这个任务和主线程是异步的，它实际执行的run方法主线程是捕获不到的。可以验证一下：
     *
     */

    @Test
    @DisplayName("测试异常处理submit2")
    //public String submit(String param){
    public void submit2(){
        String param="123";
        param = threadPoolTaskExecutorService.submit2(param);
        //return param;
        System.out.println("方法返回值："+param);
    }

    @Test
    @DisplayName("测试异常处理execute2")
    //public String execute(String param){
    public void execute2(){
        String param="123";
        String res = threadPoolTaskExecutorService.execute2(param);
        //return res;
        System.out.println("方法返回值："+param);
    }


}
