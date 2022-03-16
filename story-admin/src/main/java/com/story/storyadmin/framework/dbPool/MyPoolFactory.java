package com.story.storyadmin.framework.dbPool;

/**
 * @author: lipan
 * @date: 2021年10月04日 10:11 下午
 * @description: 单例模式 单例工厂
 * 数据库连接池工厂
 */
public class MyPoolFactory {

    public static class CreatePool{
        public static IMyPool myPool = new MyDefaultPool();
    }

    public static IMyPool getInstance(){
        return  CreatePool.myPool;
    }

}
