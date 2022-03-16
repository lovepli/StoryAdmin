package com.story.storyadmin.framework.springTransaction.jdbc;

import com.story.storyadmin.utils.wind.StringUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * BasicDataSourceDemo
 * 创建人:yuchao
 * 时间：2015-11-6-上午09:56:03
 * @version 1.0.0
 *
 * BasicDataSource的池配置属性 https://www.cnblogs.com/yu0312chao/p/4942015.html
 *
 */
public class BasicDataSourceDemo {

    private static final Logger log  = LoggerFactory.getLogger(BasicDataSourceDemo.class);

    public static void main(String[] args) {

        Properties dbProps =new Properties();
        Connection connection =null;
        try {
            dbProps.load(BasicDataSourceDemo.class.getResourceAsStream("/jdbc.properties"));
            System.setProperty("javax.xml.parsers.DocumentBuilderFactory","com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
            String driverClassName = dbProps.getProperty("jdbc.driverClassName");
            String url = dbProps.getProperty("jdbc.url");
            String username = dbProps.getProperty("jdbc.username");
            String password = dbProps.getProperty("jdbc.password");

            String initialSize = dbProps.getProperty("dataSource.initialSize");
            String minIdle = dbProps.getProperty("dataSource.minIdle");
            String maxIdle = dbProps.getProperty("dataSource.maxIdle");
            String maxWait = dbProps.getProperty("dataSource.maxWait");
            String maxActive = dbProps.getProperty("dataSource.maxActive");
            String timeBetweenEvictionRunsMillis =dbProps.getProperty("dataSource.timeBetweenEvictionRunsMillis");
            String minEvictableIdleTimeMillis =dbProps.getProperty("dataSource.minEvictableIdleTimeMillis");

            //取得连接时是否进行有效性验证（即是否还和数据库连通的）
            boolean testOnBorrow = Boolean.valueOf(dbProps.getProperty("dataSource.testOnBorrow","true")).booleanValue();
            //返回连接时是否进行有效性验证（即是否还和数据库连通的）
            boolean testOnReturn = Boolean.valueOf(dbProps.getProperty("dataSource.testOnReturn","true")).booleanValue();
            //连接空闲时是否进行有效性验证（即是否还和数据库连通的）
            boolean testWhileIdle = Boolean.valueOf(dbProps.getProperty("dataSource.testWhileIdle","true")).booleanValue();
            //是否在自动回收超时连接的时候打印连接的超时错误
            boolean logAbandoned = Boolean.valueOf(dbProps.getProperty("dataSource.logAbandoned","false")).booleanValue();
            //是否自动回收超时连接
            boolean removeAbandoned = (Boolean.valueOf(dbProps.getProperty("dataSource.removeAbandoned","false"))).booleanValue();
            //超时时间(以秒数为单位)
            int removeAbandonedTimeout = Integer.parseInt(dbProps.getProperty("dataSource.removeAbandonedTimeout","300"));

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            //初始化连接数
            if (StringUtils.isNotBlank(initialSize)) {
                dataSource.setInitialSize(Integer.parseInt(initialSize));
            }
            //最小空闲连接数
            if (StringUtils.isNotBlank(minIdle)) {
                dataSource.setMinIdle(Integer.parseInt(minIdle));
            }
            //最大空闲连接数
            if (StringUtils.isNotBlank(maxIdle)) {
                dataSource.setMinIdle(Integer.parseInt(maxIdle));
            }
            //超时回收时间（以毫秒为单位）
            if (StringUtils.isNotBlank(maxWait)) {
                dataSource.setMinIdle(Integer.parseInt(minIdle));
            }
            //最大连接数
            if (StringUtils.isNotBlank(maxActive)) {
                dataSource.setMinIdle(Integer.parseInt(minIdle));
            }

            if (StringUtils.isNotBlank(minEvictableIdleTimeMillis)) {

            }
            //是否在自动回收超时连接的时候打印连接的超时错误
            dataSource.setLogAbandoned(logAbandoned);
            //是否自动回收超时连接
            dataSource.setRemoveAbandoned(removeAbandoned);
            //超时时间(以秒数为单位)
            dataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
            //取得连接时是否进行有效性验证（即是否还和数据库连通的）
            dataSource.setTestOnBorrow(testOnBorrow);
            //返回连接时是否进行有效性验证（即是否还和数据库连通的）
            dataSource.setTestOnReturn(testOnReturn);
            //连接空闲时是否进行有效性验证（即是否还和数据库连通的）
            dataSource.setTestWhileIdle(testWhileIdle);

            if(StringUtils.isNotBlank(timeBetweenEvictionRunsMillis)){
                dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(timeBetweenEvictionRunsMillis));
            }

            /**
             * 他们两个配合，可以持续更新连接池中的连接对象，当timeBetweenEvictionRunsMillis 大于0时，每过timeBetweenEvictionRunsMillis 时间，
             * 就会启动一个线程，校验连接池中闲置时间超过minEvictableIdleTimeMillis的连接对象。
             * */
            if(StringUtils.isNotBlank(timeBetweenEvictionRunsMillis)){
                dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(timeBetweenEvictionRunsMillis));
            }

            if(StringUtils.isNotBlank(minEvictableIdleTimeMillis)){

                dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(minEvictableIdleTimeMillis));
            }

            try {
                connection = dataSource.getConnection();
                System.out.println("从BasicDataSource池中获取连接："+connection);
            } catch (SQLException e) {
                e.printStackTrace();
                log.info("对不起连接数据库失败，请查看您的配置.......");
            }

        } catch (IOException e) {

            e.printStackTrace();

        }finally{

            try {
                if (null!=connection) {
                    //将连接释放到 BasicDataSource池中
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}