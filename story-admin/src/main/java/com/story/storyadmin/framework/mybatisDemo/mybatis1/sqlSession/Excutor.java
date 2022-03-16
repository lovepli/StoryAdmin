package com.story.storyadmin.framework.mybatisDemo.mybatis1.sqlSession;

/**
 * @author: 59688
 * @date: 2021/9/27
 * @description:
 */
public interface Excutor {
    public <T> T query(String statement,Object parameter);
}
