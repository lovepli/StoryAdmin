package com.story.storyadmin.framework.mybatisDemo.mybatis2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: 59688
 * @date: 2021/9/28
 * @description:
 * 当invoke方法被调用时，我们根据调用的方法，进行反射，得到namespace以及对应的SQL，
 * 然后，我们把SQL交给sqlSession进行执行即可。
 */
public class MyMapperProxy implements InvocationHandler {

    private MySqlSession sqlSession;

    public MyMapperProxy(){

    }
    public MyMapperProxy(MySqlSession sqlSession){
        this.sqlSession = sqlSession;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      String mapperClass = method.getDeclaringClass().getName();
      if (StudentMapperXML.namespace.equals(mapperClass)){
          String methodName = method.getName();
          String originSql = StudentMapperXML.getMethodSQL(methodName);

          // 我们这里简单点，直接把%d替换
          String formattedSql = String.format(originSql,String.valueOf(args[0]));

          return sqlSession.selectOne(formattedSql);
      }
      return null;
    }
}
