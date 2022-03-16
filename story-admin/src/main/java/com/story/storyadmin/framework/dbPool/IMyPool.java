package com.story.storyadmin.framework.dbPool;

/**
 * @author: lipan
 * @date: 2021年10月04日 9:30 下午
 * @description: 对连接池的一个基本管理API接口
 * 要可以得到数据库操作的管理/要可以创建数据库管道
 */
public interface IMyPool {

    public MyPooledConnection getPooledConnection();

    public void createMyPooledConnection(int count);
}
