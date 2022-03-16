package com.story.storyadmin.designPrinciples.compositeReusePrinciple.extendChange;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 * 将MySQL的逻辑抽离
 */
public class MySQLConnection extends DBConnection2 {
    @Override
    public String getConnection(){
        return "MySQL 数据库连接";
    }
}
