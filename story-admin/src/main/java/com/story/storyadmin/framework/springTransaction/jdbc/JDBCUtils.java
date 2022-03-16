package com.story.storyadmin.framework.springTransaction.jdbc;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

/**
 * @author: 59688
 * @date: 2021/9/27
 * @description:
 */
public class JDBCUtils {
    //创建BasicDataSource对象
    private static BasicDataSource datasource = new BasicDataSource();
    //静态代码块,实现必要参数设置
    static{
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost:3306/gjp");
        datasource.setUsername("root");
        datasource.setPassword("root");
        datasource.setMaxActive(10);
        datasource.setMaxIdle(5);
        datasource.setMinIdle(2);
        datasource.setInitialSize(10);
    }
    public static DataSource getDataSource(){
        return datasource;
    }
}