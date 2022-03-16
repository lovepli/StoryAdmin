package com.story.storyadmin.framework.mybatisDemo.mybatis2;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.*;
import java.util.Properties;

/**
 * @author: 59688
 * @date: 2021/9/28
 * @description:
 */
public class MyBaseExecutor implements  MyExecutor{

    private static final String CONNECTIONURL  = "";
    private static final String USERNAME  = "";
    private static final String PASSWORD  = "";


    @Override
    public <T> T query(String statement) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

          //  connection = DriverManager.getConnection(CONNECTIONURL,USERNAME,PASSWORD);
            connection = getConnection(); // 获取数据库连接
            String sql = statement;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            Student student = null;
           if (resultSet.next()){
               student = new Student();
               student.setId(resultSet.getInt("id"));
               student.setSex(resultSet.getInt("sex"));
               student.setName(resultSet.getString("name"));
               student.setAge(resultSet.getInt("age"));
               student.setAddress(resultSet.getString("address"));
           }
            return (T) student;

    }

    // jdk提供的数据库连接工具，不过不支持池化
    private Connection getConnection() throws SQLException {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/story_admin2");
        ds.setUsername("root");
        ds.setPassword("12345");
        return ds.getConnection();
    }


    //#############################################################################################
    //Java DriverManager.getConnection() 方法用于获得试图建立到指定数据库 URL 的连接。DriverManager 试图从已注册的 JDBC 驱动程序集中选择一个适当的驱动程序。
    /**
     * 语法1
     * getConnection(String url)
     *
     * 参数说明：
     * url：访问数据库的 URL 路径。
     * @return
     */
    public Connection getConnection2(){
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");  //注册数据库驱动
            String url = "jdbc:mysql://localhost:3306/test?user=root＆password=root";  //定义连接数据库的url
            con = DriverManager.getConnection(url);  //获取数据库连接
            System.out.println("数据库连接成功！");
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;  //返回一个连接
    }

    /**
     * 语法2
     * getConnection(String url,Properties info)
     *
     * 参数说明：
     * url：访问数据库的 URL 路径。
     * info：是一个持久的属性集对象，包括 user 和 password 属性。
     * 示例
     * @return
     */
    public Connection getConnection3(){
        Connection con = null;  //定义数据库连接对象
        Properties info = new Properties();  //定义Properties对象
        info.setProperty("user","root");  //设置Properties对象属性
        info.setProperty("password","root");
        try{
            Class.forName("com.mysql.jdbc.Driver");  //注册数据库驱动
            String url = "jdbc:mysql://localhost:3306/test";  //test为数据库名称
            con = DriverManager.getConnection(url,info);  //获取连接数据库的Connection对象
            System.out.println("数据库连接成功！");
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;//返回一个连接
    }

    /**
     *语法3
     * Connection(String url,String user,String password)
     *
     * 参数说明：
     * url：访问数据库的 URL 路径。
     * user：是访问数据库的用户名。
     * password：连接数据库的密码。
     * @return
     */
    public Connection getConnection4(){

         Connection con;
         String user = "sa";  //定义连接数据库的用户名
         String password = "";  //定义连接数据库的密码
         String className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
         String url = "jdbc:sqlserver://localhost:1433;DatabaseName=db_database01";  /**创建数据库连接*/

        try{
            Class.forName(className);//加载数据库驱动
            System.out.println("数据库驱动加载成功！");
            con = DriverManager.getConnection(url,user,password);  //连接数据库
            System.out.println("成功地获取数据库连接！");
        }catch(Exception e){
            System.out.println("创建数据库连接失败！");
            con = null;
            e.printStackTrace();
        }
        return con;
    }
}
