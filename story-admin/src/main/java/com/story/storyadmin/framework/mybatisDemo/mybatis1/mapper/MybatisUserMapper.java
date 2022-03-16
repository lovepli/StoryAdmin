package com.story.storyadmin.framework.mybatisDemo.mybatis1.mapper;


import com.story.storyadmin.domain.entity.sysmgr.User;

/**
 * @author: 59688
 * @date: 2021/9/27
 * @description:
 */
public interface MybatisUserMapper {
    // @Select("select * from mybatis_user_test where id = ?")
    public User getUserById(Long id);
}