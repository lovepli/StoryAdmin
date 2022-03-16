package com.story.storyadmin.manyThread.CallableDemo.CallableDemo1;

import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.service.sysmgr.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author: 59688
 * @date: 2021/7/23
 * @description: https://blog.csdn.net/w_t_y_y/article/details/102817576
 */

public class UserTaskTest implements Callable<List<User>> {

    private List<Integer> ids;

    private UserService userService;

    public UserTaskTest(List<Integer> childIds, UserService userService) {
        System.out.println("构造");
        this.ids = childIds;
        this.userService = userService;
    }

    @Override
    public List<User> call() throws Exception {
        System.out.println("-----线程-----" + Thread.currentThread().getName());
        Thread.sleep(4000);
        System.out.println("执行");
       // return userService.getByIds(ids);

        User user= new User();
        user.setId(99l);
        user.setName("张三三");
        user.setEmail("test@gamil.com");
        user.setAccount("admin888");
        List<User> userList =new ArrayList<>();
        userList.add(user);
        return userList;
    }
}
