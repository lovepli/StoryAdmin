package com.story.storyadmin.framework.springMVCDemo.springmvc2.dao.impl;


import com.story.storyadmin.framework.springMVCDemo.springmvc2.annotation.Repository;
import com.story.storyadmin.framework.springMVCDemo.springmvc2.dao.UserDao;

/**
 * @author: 59688
 * @date: 2021/9/28
 * @description:
 */
@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {
    @Override
    public void insert() {
        System.out.println("execute UserDaoImpl.insert()");
    }
}
