package com.story.storyadmin.framework.mybatisDemo.mybatis1;

import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.framework.mybatisDemo.mybatis1.mapper.MybatisUserMapper;
import com.story.storyadmin.framework.mybatisDemo.mybatis1.sqlSession.MySqlsession;


/**
 * 手写一个 Mybatis 框架 https://my.oschina.net/liughDevelop/blog/1631006
 * https://github.com/qq53182347/liugh-mybatis
 */
public class TestMybatis {

    public static void main(String[] args) {
        MySqlsession sqlsession=new MySqlsession();
        MybatisUserMapper mapper = sqlsession.getMapper(MybatisUserMapper.class);
        User user = mapper.getUserById(1l);
        System.out.println(user);
    }
}