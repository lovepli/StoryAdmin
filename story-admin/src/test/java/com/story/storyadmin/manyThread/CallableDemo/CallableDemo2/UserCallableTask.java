package com.story.storyadmin.manyThread.CallableDemo.CallableDemo2;

import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.mapper.sysmgr.UserMapper;

import java.util.concurrent.Callable;

/**
 * @author: 59688
 * @date: 2021/7/23
 * @description:
 */

public class UserCallableTask implements Callable<String> {

    private String param;

    private UserMapper userMapper;

    public UserCallableTask (String param,UserMapper userMapper){
        this.param = param;
        this.userMapper = userMapper;
    }

    public UserCallableTask (String param){
        this.param = param;
    }
    // 验证异常处理例子
    //@Override
    //public String call() throws Exception {
    //    param += "UserCallableTask";
    //    int a = 1/0;
    //    return param;
    //}


    //验证多线程事务回滚例子
    @Override
    public String call() throws Exception {
        param += "UserCallableTask";
        User user = new User();
        user.setName("我是子线程");
        userMapper.insert(user);
        int a = 1/0;
        return param;
    }


}
