package com.story.storyadmin.framework.dbPool;

/**
 * @author: lipan
 * @date: 2021年10月04日 9:13 下午
 * @description: 很多数据库链接池都需要xml or propertles进行配置
 * DBConfigXML代表外部配置文件
 * 我们在实际中使用数据库连接池，需要在Spring的配置文件中，进行一些参数配置。这里为了简化解析过程，直接这样提供  。
 */
public class DBConfigXML {

    public static final String jdbcDriver = "com.mysql.jdbc.Driver";
    public static final String jdbcUrl = "jdbc:mysql://127.0.1.1:3306/test";
    public static final String jdbcUserName = "root";
    public static final String jdbcPassword = "123456";

    // 数据库链接池初始化大小
    public static final int initCount = 10;
    // 链接池不足的时候增加的步进值
    public static final int step =2;
    //连接池的最大数量
    public static final int maxCount = 50;
}
