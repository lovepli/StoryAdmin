package com.story.storyadmin.framework.springTransaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: 59688
 * @date: 2021/9/27
 * @description: 这里通过这2个DAO，想模拟一个事务中账户购买、下单2个操作。
 */
public class UserAccountDao {

    private DataSource dataSource;

    public UserAccountDao(DataSource dataSource){
        this.dataSource =dataSource;
    }

    public void buy() throws SQLException {
        Connection connection =SingleThreadConnectionHolder.getConnection(dataSource);

        //进行业务操作
        //...
        System.out.println("当前用户购买线程："+Thread.currentThread().getName()+",使用管道（hashcode）："+connection.hashCode());
    }
}
