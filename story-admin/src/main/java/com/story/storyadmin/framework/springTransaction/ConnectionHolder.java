package com.story.storyadmin.framework.springTransaction;



import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 59688
 * @date: 2021/9/27
 * @description: 透彻理解Spring事务设计思想之手写实现 https://www.jianshu.com/p/1becdc376f5d
 * 在Spring中，有时候我们是不是要配置多个数据源DataSource？很显然，Spring需要通过DataSource来得到操作数据库的管道Connection，这有点类似于JNDI查找。
 *
 * 这里通过ConnectionHolder类来完成这个过程，需要思考的是在多线程下，这显然是存在问题的。为避免多线程问题，难道我们采用线程安全的Map，比如ConcurrentHashMap，其实我们真正的目的是什么？是保证一个线程下，一个事务的多个操作拿到的是一个Connection，显然使用ConcurrentHashMap根本无法保证！
 *
 * Spring很聪明，她提供了一种思路，来解决，看SingleThreadConnectionHolder的代码！

 *
 */
public class ConnectionHolder {

    //实现DataSource到数据库管道的路由
    private Map<DataSource, Connection> map =new HashMap<>();

    public Connection getConnectionByDataSource(DataSource dataSource) throws SQLException {
         Connection connection =map.get(dataSource);
         if(connection == null || connection.isClosed()){
             connection  = dataSource.getConnection();
             map.put(dataSource,connection);
         }
         return connection;
    }

}
