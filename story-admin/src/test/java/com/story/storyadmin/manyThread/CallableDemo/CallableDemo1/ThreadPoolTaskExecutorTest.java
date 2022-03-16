package com.story.storyadmin.manyThread.CallableDemo.CallableDemo1;

import com.alibaba.fastjson.JSON;
import com.story.storyadmin.config.threadpool.VisiableThreadPoolTaskExecutor;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.service.sysmgr.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * @author: 59688
 * @date: 2021/7/23
 * @description: 线程池----ThreadPoolTaskExecutor的提交方法execute和submit https://blog.csdn.net/w_t_y_y/article/details/102817576
 */
@Slf4j
//@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
//@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("线程池ThreadPoolTaskExecutor的提交方法execute和submit")
public class ThreadPoolTaskExecutorTest {


    @Autowired
    private VisiableThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private UserService userService;


    /**
     * 使用单元测试，创建线程池，然后启动一个线程，在线程中sleep(5*1000),这是会报java.lang.InterruptedException: sleep interrupted异常，
     * 原因是因为单元测试启动的主线程很快就结束了，而子线程确sleep5秒，使得主线程强行打断子线程的sleep,因此抛出异常，
     * 解决办法是可以在单元测试的最后加上sleep(10*1000),目的是不让主线程在子线程前结束。
     */
    @Test
    @DisplayName("测试线程池无返回值的任务使用public void execute(Runnable command) 方法提交；")
    public void execute() throws InterruptedException {
        System.out.println("-----线程1-----" + Thread.currentThread().getName());
        System.out.println("进入方法");

        threadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("-----线程2-----" + Thread.currentThread().getName());
                    Thread.sleep(5 * 1000);
                    System.out.println("sleep后，" + Thread.currentThread().getName() + "执行完毕！");
                } catch (InterruptedException e) {
                    log.error("线程异常,{}" + e.getMessage());
                    e.printStackTrace();
                }

            }
        });
        System.out.println("执行提交后");
        /**
         * 可见，由于子线程比较耗时，主线程结束后子线程还没有执行完。
         */
        //可以看到子线程比较耗时，主线程结束之后，子线程还没有执行完；所以我们为了看到所有执行的结果输出，给主线程睡眠时间大于子线程执行总耗时，如下
        Thread.sleep(10 * 1000);
    }


    /**
     * 一、submit提交任务之后没有取数据：
     * @throws InterruptedException
     */
    @Test
    @DisplayName("测试线程池有返回值的任务使用public <T> Future<T> submit(Callable) 方法提交；")
    //public List<User> submit() { // 方法返回查询的userList
    public void submit1() throws InterruptedException {
        System.out.println("-----线程-----" + Thread.currentThread().getName());
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i <= 500; i++) {
            ids.add(i);
        }
        //异步获取所有用户
        List<User> users = new ArrayList<>();
        List<Future> futures = new ArrayList<>();

        for (int i = 0; i < ids.size(); i += 100) {
            int startIndex = i;
            int endIndex = startIndex + 100 > ids.size() ? ids.size() : startIndex + 100;
            UserTaskTest task = new UserTaskTest(ids.subList(startIndex, endIndex), userService);
            Future<List<User>> future = threadPoolTaskExecutor.submit(task);
            System.out.println("加入future");
            futures.add(future);
        }
        // System.out.println("返回结果" + users.size());
        System.out.println("返回值users：" + JSON.toJSONString(users));
        //可以看到子线程比较耗时，主线程结束之后，子线程还没有执行完；所以我们为了看到所有执行的结果输出，给主线程睡眠时间大于子线程执行总耗时，如下
        Thread.sleep(7 * 4000);
        /**
         * 总结：
         *  可以看到子线程比较耗时，主线程结束之后，子线程还没有执行完；
         */

        //return users;

    }

    /**
     * 二、submit提交任务之后取数据：
     * @throws InterruptedException
     */
    @Test
    @DisplayName("测试线程池有返回值的任务使用public <T> Future<T> submit(Callable) 方法提交；")
    //public List<User> submit() { // 方法返回查询的userList
    public void submit2() throws InterruptedException {
        System.out.println("-----线程-----" + Thread.currentThread().getName());
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i <= 500; i++) {
            ids.add(i);
        }
        //异步获取所有用户
        List<User> users = new ArrayList<>();
        List<Future> futures = new ArrayList<>();

        for (int i = 0; i < ids.size(); i += 100) {
            int startIndex = i;
            int endIndex = startIndex + 100 > ids.size() ? ids.size() : startIndex + 100;
            UserTaskTest task = new UserTaskTest(ids.subList(startIndex, endIndex), userService);
            Future<List<User>> future = threadPoolTaskExecutor.submit(task);
            System.out.println("加入future");
            futures.add(future);
        }
        //取数据
        try{
            System.out.println("获取数据");
            for(Future future : futures){
                System.out.println("获取数据内部");
                users.addAll((List<User>) future.get());
            }
        }catch (Exception e){

        }
       // System.out.println("返回结果" + users.size());
        System.out.println("返回值users：" + JSON.toJSONString(users));
        /**
         * 总结：
         * 可以看到，即使子线程比主线程耗时，主线程也等子线程结束后才结束。
         * 这两个例子证明了使用submit提交任务，提交后只要有从Future取数据的操作，就可以保证主线程在子线程结束后才结束。
         */
        //return users;
    }

    //************************************************************分割线****************************************************************************
    //2、下面再举个完整的例子，在子线程同样耗时以及主线程执行步骤一样的情况下比较execute和submit这两种方法：

    @Test
    @DisplayName("测试线程池无返回值的任务使用public void execute(Runnable command) 方法提交；")
    public void execute2() throws InterruptedException {
        System.out.println("-----线程-----" + Thread.currentThread().getName());
        String str = "execute方法";
        threadPoolTaskExecutor.execute(new MyOrderRunnableTask(str));
        System.out.println("主线程调用结束");
        //可以看到子线程比较耗时，主线程结束之后，子线程还没有执行完；所以我们为了看到所有执行的结果输出，给主线程睡眠时间大于子线程执行总耗时，如下
        Thread.sleep(3000);

    }

    @Test
    @DisplayName("测试线程池有返回值的任务使用public <T> Future<T> submit(Callable) 方法提交；")
    //public String submit3(){
    public void submit3(){
        System.out.println("-----线程-----" + Thread.currentThread().getName());
        String str = "submit方法";
        Future<String> future = threadPoolTaskExecutor.submit(new MyOrderCallableTask(str));
        try {
            str = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("主线程调用结束");
        System.out.println("返回值："+str);
        //return str;
    }

    /**
     * 总结：
     * 验证结束。这也和他们的功能是保持一致的。不需要返回结果，主线程就不需要等待子线程执行；需要返回结果，主线程肯定需要等所有的子线程结束后汇总结果。
     *
     * 一、所以在调用的时候也需要注意：
     * （1）如果主线程调用了ThreadPoolTaskExecutor的execute提交任务，且传递了参数给子线程，并且子线程在修改这个参数，
     * 调用后主线程就不应该再使用这个参数，因为这个参数的值已经无法确定了；
     * （2）如果主线程调用了ThreadPoolTaskExecutor的submit提交任务，记得要在调用的逻辑后面，从Future里面把返回值取出来（调用Future的get方法），
     * 否则就和execute的效果一样了。
     *
     * 二、处理异常的区别：Callable执行call时遇到异常会抛出，而Runnable执行run时遇到异常并不会抛出。
     */



}
