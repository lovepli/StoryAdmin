package com.story.storyadmin.designPrinciples.compositeReusePrinciple;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 * 以数据库操作为例，首先创建DBConnection类。
 */
public class DBConnection{
    public String getConnection(){
        return "数据库连接";
    }
}