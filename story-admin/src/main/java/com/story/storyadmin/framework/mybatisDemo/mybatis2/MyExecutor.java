package com.story.storyadmin.framework.mybatisDemo.mybatis2;

import java.sql.SQLException;

/**
 * @author: 59688
 * @date: 2021/9/28
 * @description: 执行器MyExecuto
 */
public interface MyExecutor {

    public <T> T query(String statement) throws SQLException;
}
