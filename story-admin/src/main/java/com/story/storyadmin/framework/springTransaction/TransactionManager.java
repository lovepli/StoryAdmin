package com.story.storyadmin.framework.springTransaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author: 59688
 * @date: 2021/9/27
 * @description: TransactionManager，这个我们经常在Spring里面进行配置吧，事务大管家
 */
public class TransactionManager {
    private DataSource dataSource;

    public TransactionManager(DataSource dataSource){
        this.dataSource =dataSource;
    }

    private Connection getConnection() throws SQLException {
        return  SingleThreadConnectionHolder.getConnection(dataSource);
    }

    /**
     * 开启事务
     * @throws SQLException
     */
    public void start() throws SQLException {
        Connection connection =getConnection();
        connection.setAutoCommit(false);
    }

    /**
     * 回滚事务
     */
    public void rollback()  {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.rollback();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void close() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        connection.close();
    }


}
