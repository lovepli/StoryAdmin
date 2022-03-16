package com.story.storyadmin.framework.dbPool;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: lipan
 * @date: 2021年10月04日 10:15 下午
 * @description: 理解数据库连接池底层原理之手写实现 https://www.jianshu.com/p/a50f614571e3
 */
public class Test {

    public static IMyPool myPool = MyPoolFactory.getInstance();

    public static void main(String[] args) {

        for (int i = 0; i < 1000;i++) {
            MyPooledConnection myPooledConnection = myPool.getPooledConnection();
            ResultSet query = myPooledConnection.query("select * from user");

            try {
                while (query.next()) {
                    System.out.println(query.getString("userName")+","+query.getString("address")+",使用管道："+
                           myPooledConnection.getConnection());
                }
                myPooledConnection.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }

}
