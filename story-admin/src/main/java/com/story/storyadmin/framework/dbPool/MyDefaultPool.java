package com.story.storyadmin.framework.dbPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

/**
 * @author: lipan
 * @date: 2021年10月04日 9:34 下午
 * @description: 连接池：
 * 得加载外部配置进行初始化
 * 还得加载数据库驱动
 * 要考虑采用什么集合对连接通道进行储存，vector or list ?
 *
 * 需要注意到是，MyDefaultPool持有一个管道集合，基于多线程的考虑，这里使用了Vector。
 */
public class MyDefaultPool implements IMyPool{
    private Vector<MyPooledConnection> myPooledConnectionVector = new Vector<>();
    private static String jdbcUrl;
    private static String jdbcUserName;
    private static String jdbcPassword;
    private static int initCount;
    private static int step;
    private static int maxCount;


    public MyDefaultPool() {
        // 初始化数据库连接池配置
      init();
      try {
          //加载驱动
          Class.forName(DBConfigXML.jdbcDriver);
      }catch (ClassNotFoundException e) {
          e.printStackTrace();
      }
      //初始化数据库连接池管道
      createMyPooledConnection(initCount);

    }

    /**
     * 数据库连接池初始化
     * 数据库连接池需要根据外部配置文件完成数据库驱动加载以及初始化管道的建立。
     */
    private  void init() {
        jdbcUrl  = DBConfigXML.jdbcUrl;
        jdbcUserName = DBConfigXML.jdbcUserName;
        jdbcPassword = DBConfigXML.jdbcPassword;
        initCount = DBConfigXML.initCount;
        step = DBConfigXML.step;
        maxCount = DBConfigXML.maxCount;
    }




    /**
     * 创建数据库连接管道
     * 数据库连接池在创建管道时，应该去看一下是否达到上限，如果没有，则可以创建。
     *
     * 不仅仅要创建出来，还要标示每一个管道的isBusy标志。
     * @param count
     */
    @Override
    public void createMyPooledConnection(int count) {
      if (myPooledConnectionVector.size() > maxCount ||
          myPooledConnectionVector.size() + count > maxCount){
          throw new RuntimeException("连接池已满！");
      }
      for (int i = 0;i < count;i++) {
          try {
              Connection connection = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword);
          }catch (SQLException e) {
              e.printStackTrace();
          }
      }
    }

    /**
     * 得到数据库连接管道
     * 这里需要注意的是：如果得不到操作管道，需要去创建管道！
     * @return
     */
    @Override
    public MyPooledConnection getPooledConnection() {
        if (myPooledConnectionVector.size() <1){
            throw new RuntimeException("连接池初始化错误！");
        }

        MyPooledConnection myPooledConnection = null;

        try {
            myPooledConnection = getRealConnectionFormPool();

             while (myPooledConnection == null) {

                 createMyPooledConnection(step);

                 myPooledConnection  = getRealConnectionFormPool();

                 return myPooledConnection;
             }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return myPooledConnection;
    }

    /**
     * 第一，这里使用了synchronized，就是为了避免多线程下产生问题。
     * 第二，要知道Connection是有超时机制的，如果我们得到的管道的Connection已经超时了怎么办呢？
     * 第三，得到管道后，一定注意isBusy的设置。
     * @return
     * @throws SQLException
     */
    private synchronized  MyPooledConnection getRealConnectionFormPool() throws SQLException {
        for (MyPooledConnection myPooledConnection : myPooledConnectionVector) {

            if (!myPooledConnection.isBusy()){
               if (myPooledConnection.getConnection().isValid(3000)){
                   myPooledConnection.setBusy(true);
                   return myPooledConnection;
               } else {
                   Collection connection = (Collection) DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword);
                   myPooledConnection.setConnection((Connection) connection);
                   myPooledConnection.setBusy(true);
                   return myPooledConnection;
               }
            }
        }
        return null;
    }
}
