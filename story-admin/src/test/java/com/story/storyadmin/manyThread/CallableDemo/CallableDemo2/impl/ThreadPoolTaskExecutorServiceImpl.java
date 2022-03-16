package com.story.storyadmin.manyThread.CallableDemo.CallableDemo2.impl;

import com.story.storyadmin.config.threadpool.VisiableThreadPoolTaskExecutor;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.manyThread.CallableDemo.CallableDemo2.ThreadPoolTaskExecutorService;
import com.story.storyadmin.manyThread.CallableDemo.CallableDemo2.UserCallableTask;
import com.story.storyadmin.manyThread.CallableDemo.CallableDemo2.UserRunnableTask;
import com.story.storyadmin.mapper.sysmgr.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author: 59688
 * @date: 2021/7/23
 * @description:
 */

//@Service("threadPoolTaskExecutorService")
//@Service
public class ThreadPoolTaskExecutorServiceImpl implements ThreadPoolTaskExecutorService {


    @Autowired(required = true)
    private VisiableThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private UserMapper userMapper;

    /***********************************处理异常的区别： *******************************/
    @Override
    public String submit(String param) {
        Future<String> future = threadPoolTaskExecutor.submit(new UserCallableTask(param));
        try {
            param =  future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "error";
        } catch (ExecutionException e) {
            e.printStackTrace();
            return "error";
        }
        User user = new User();
        user.setName(param);
        userMapper.insert(user);
        return param;
    }

    @Override
    public String execute(String param) {
        threadPoolTaskExecutor.execute(new UserRunnableTask(param,userMapper));//这个任务提交有没有异常，而这个任务和主线程是异步的，它实际执行的run方法主线程是捕获不到的。可以验证一下：
        return "success";
    }


    //TODO 进行验证，验证确实没有捕获到。
    //@Override
    //@Transactional
    //public String execute(String param) {
    //    try {
    //        threadPoolTaskExecutor.execute(new UserRunnableTask(param, userMapper));
    //    } catch (Exception e) {
    //        System.out.println("有异常");
    //        return "error";
    //    }
    //    return "success";
    //}

/************************************三、多线程与事务回滚：***************************************************/

    @Override
    @Transactional
    public String submit2(String param) {


        Future<String> future = threadPoolTaskExecutor.submit(new UserCallableTask(param,userMapper));

        User user = new User();
        user.setName("我是主线程");
        userMapper.insert(user); //验证结果：数据库没有主线程的数据插入：说明主线程回滚成功。
        try {
            param =  future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "error";
        } catch (ExecutionException e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "error";
        }

        return param;
    }

    @Override
    public String execute2(String param) {
        // threadPoolTaskExecutor.execute(new UserRunnableTask(param,userMapper));
        return "success";
    }

    /**
     * 三、多线程与事务回滚：
     *
     * 上述，如果在事务中调用了多线程，submit遇到异常会抛出且必须被捕获，不会触发回滚，execute遇到异常主线程无法感知，也不会触发回滚。那如果需要在多线程调用时实现事务回滚该怎么做呢？这就需要加入其它的操作了：
     *
     * 1、submit方法与事务回滚：我们知道sumbit方法提交线程在获取返回结果时是需要捕获异常的，那么我们就可以在捕获到异常时手动回滚当前事务。
     *
     * （1）主线程正常，子线程发生异常，只回滚主线程：这种情况比较简单，主线程捕获异常后直接TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();回滚主线程就可以了：
     * （2）、主线程或子线程异常，主线程、子线程全部回滚：同时回滚主线程和子线程，就需要把主线程和子线程放到同一个事务中。
     *   说明主线程、子线程全部回滚成功。
     */

}
