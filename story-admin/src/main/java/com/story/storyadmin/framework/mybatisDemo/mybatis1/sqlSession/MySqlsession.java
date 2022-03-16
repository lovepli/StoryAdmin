package com.story.storyadmin.framework.mybatisDemo.mybatis1.sqlSession;
import java.lang.reflect.Proxy;

/**
 * 接下来实现我们的MySqlSession,首先的成员变量里得有Excutor和MyConfiguration，代码的精髓就在getMapper的方法里。
 */
public class MySqlsession {

    private Excutor excutor= new MyExcutor();

    private MyConfiguration myConfiguration = new MyConfiguration();

    public <T> T selectOne(String statement,Object parameter){
        return excutor.query(statement, parameter);
    }

    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> clas){
        //动态代理调用
        return (T)Proxy.newProxyInstance(clas.getClassLoader(),new Class[]{clas},
                new MyMapperProxy(myConfiguration,this));
    }

}