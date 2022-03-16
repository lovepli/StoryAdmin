package com.story.storyadmin.framework.springTransaction.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 *  BasicDataSource配置 https://blog.csdn.net/oryjk/article/details/84013152
 *
 * 连接控制
 */
public class BasicDataSourceDemo2 {
    private static BasicDataSource dataSource = null;

    public BasicDataSourceDemo2() {
    }

    public static void init() {
        if (dataSource != null) {
            try {
                dataSource.close();
            } catch (Exception e) {
                //
            }
            dataSource = null;
        }

        try {
            Properties p = new Properties();
            p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
            p.setProperty("url", "jdbc:mysql://localhost:3309/sampledb");
            p.setProperty("password", "scott");
            p.setProperty("username", "tiger");
            p.setProperty("maxActive", "30");
            p.setProperty("maxIdle", "10");
            p.setProperty("maxWait", "1000");
            p.setProperty("removeAbandoned", "false");
            p.setProperty("removeAbandonedTimeout", "120");
            p.setProperty("testOnBorrow", "true");
            p.setProperty("logAbandoned", "true");

            dataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);

        } catch (Exception e) {
            //
        }
    }


    public static synchronized Connection getConnection() throws  SQLException {
        if (dataSource == null) {
            init();
        }
        Connection conn = null;
        if (dataSource != null) {
            conn = dataSource.getConnection();
        }
        return conn;
    }

    /**
     * 用apache的dbcp来建立独立的数据库连接池(db connection pool)
     * 数据库连接池的好处是不言而喻的，现在大部分的application server都提供自己的数据库连接池方案，此时，只要按照application server的文档说明，正确配置，即可在应用中享受到数据库连接池的好处。
     * 接下来，在我们的应用中，只要简单地使用ConnectionSource.getConnection()就可以取得连接池中的数据库连接，享受数据库连接带给我们的好处了。当我们使用完取得的数据库连接后，只要简单地使用connection.close()就可把此连接返回到连接池中，至于为什么不是直接关闭此连接，而是返回给连接池，这是因为dbcp使用委派模型来实现Connection接口了。
     *
     * 在使用Properties来创建BasicDataSource时，有很多参数可以设置，比较重要的还有：
     *
     * testOnBorrow、testOnReturn、testWhileIdle，他们的意思是当是取得连接、返回连接或连接空闲时是否进行有效性验证（即是否还和数据库连通的），默认都为false。所以当数据库连接因为某种原因断掉后，再从连接池中取得的连接，实际上可能是无效的连接了，所以，为了确保取得的连接是有效的， 可以把把这些属性设为true。当进行校验时，需要另一个参数：validationQuery，对oracle来说，可以是：SELECT COUNT(*) FROM DUAL，实际上就是个简单的SQL语句，验证时，就是把这个SQL语句在数据库上跑一下而已，如果连接正常的，当然就有结果返回了。
     *
     * 还有2个参数：timeBetweenEvictionRunsMillis 和 minEvictableIdleTimeMillis， 他们两个配合，可以持续更新连接池中的连接对象，当timeBetweenEvictionRunsMillis 大于0时，每过timeBetweenEvictionRunsMillis 时间，就会启动一个线程，校验连接池中闲置时间超过minEvictableIdleTimeMillis的连接对象。
     *
     * 还有其他的一些参数，可以参考源代码。
     * ————————————————
     * 版权声明：本文为CSDN博主「你们王哥」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/oryjk/article/details/84013152
     */
}
