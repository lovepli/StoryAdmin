package com.story.storyadmin.manyThread.CallableDemo.CallableDemo2;

import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.mapper.sysmgr.UserMapper;

/**
 * @author: 59688
 * @date: 2021/7/23
 * @description:
 */

public class UserRunnableTask implements Runnable {

    private String param;

    private UserMapper userMapper;

    public UserRunnableTask (String param,UserMapper userMapper){
        this.param = param;
        this.userMapper = userMapper;
    }

    @Override
    public void run() {
        param += "UserRunnableTask";
        User user = new User();
        user.setName(param);
        int a = 1/0;
        userMapper.insert(user);
    }


}
