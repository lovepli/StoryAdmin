package com.story.storyadmin.designPrinciples.compositeReusePrinciple.extendChange;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 * 再创建Oracle支持的逻辑
 */
public class OracleConnection extends DBConnection2 {
    @Override
    public String getConnection(){
        return "Oracle 数据库连接";
    }
}
