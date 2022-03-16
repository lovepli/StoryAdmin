package com.story.storyadmin.framework.springTransaction;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.SQLException;

/**
 * @author: 59688
 * @date: 2021/9/27
 * @description: 透彻理解Spring事务设计思想之手写实现 https://www.jianshu.com/p/1becdc376f5d
 * 这里，主要是模拟Spring的注入以及多用户并发请求。
 * 你可以发现，一个线程中的一个事务的多个操作，使用的是同一个Connection！
 *
 */
public class Test {

    public static final String jdbcDriver = "com.mysql.jdbc.Driver";
    public static final String jdbcURL = "jdbc:mysql://localhost:3306/story_admin2";
    public static final String jdbcUserName = "root";
    public static final  String jdbcPassword = "123456";

    public static void main(String[] args) throws SQLException {
        //BasicDataSource是DBCP连接池中的一个类，是javax.sql.DataSource的一个实现
        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setDriverClassName(jdbcDriver);
        basicDataSource.setUrl(jdbcURL);
        basicDataSource.setUsername(jdbcUserName);
        basicDataSource.setPassword(jdbcPassword);

        final UserService userService = new UserService(basicDataSource);

        for (int i=0;i<10;i++){
            new Thread((Runnable) () ->{userService.action();}).start();
        }

        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }




}
