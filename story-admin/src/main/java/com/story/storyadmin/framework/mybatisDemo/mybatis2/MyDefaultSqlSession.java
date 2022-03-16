package com.story.storyadmin.framework.mybatisDemo.mybatis2;

import java.lang.reflect.Proxy;
import java.sql.SQLException;

/**
 * @author: 59688
 * @date: 2021/9/28
 * @description:
 */
public class MyDefaultSqlSession implements MySqlSession{

    private MyExecutor executor= new MyBaseExecutor();

    @Override
    public <T> T selectOne(String sql) throws SQLException {
        return executor.query(sql);
    }

    @Override
    public <T> T getMapper(Class<T> interfaces) {
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(),new Class[]{interfaces},new MyMapperProxy(this));
    }
}
