package com.story.storyadmin.framework.springMVCDemo.springmvc2.service.impl;

import com.story.storyadmin.framework.springMVCDemo.springmvc2.annotation.Qualifier;
import com.story.storyadmin.framework.springMVCDemo.springmvc2.annotation.Service;
import com.story.storyadmin.framework.springMVCDemo.springmvc2.dao.UserDao;
import com.story.storyadmin.framework.springMVCDemo.springmvc2.service.UserService;

/**
 * @author: 59688
 * @date: 2021/9/28
 * @description:
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Qualifier("userDaoImpl")
    private UserDao userDao;

    @Override
    public void insert() {
        System.out.println("UserServiceImpl.insert() start");
        userDao.insert();
        System.out.println("UserServiceImpl.insert() end");
    }
}
