package com.story.storyadmin.framework.mybatisDemo.mybatis2;

import java.sql.SQLException;

/**
 * @author: 59688
 * @date: 2021/9/28
 * @description: 对外暴露的API接口（MySqlSession）：
 */
public interface MySqlSession {

    <T> T selectOne(String sql) throws SQLException;

    <T> T getMapper(Class<T> interfaces);
}
