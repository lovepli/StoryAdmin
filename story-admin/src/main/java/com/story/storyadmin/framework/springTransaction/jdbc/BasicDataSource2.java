package com.story.storyadmin.framework.springTransaction.jdbc;


import lombok.Data;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: 59688
 * @date: 2021/9/27
 * @description:
 */
@Data
public class BasicDataSource2{

    // jdk提供的数据库连接工具，不过不支持池化
    public BasicDataSource2() throws SQLException {
       DriverManagerDataSource ds = new DriverManagerDataSource();
       ds.setDriverClassName("com.mysql.jdbc.Driver");
       ds.setUrl("jdbc:mysql://localhost:3309/sampledb");
       ds.setUsername("root");
       ds.setPassword("1234");
       Connection actualCon = ds.getConnection();
    }
}
