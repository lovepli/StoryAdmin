package com.story.storyadmin.framework.mybatisDemo.mybatis1.config;

import lombok.Data;

/**
 * Function对象包括sql的类型、方法名、sql语句、返回类型和参数类型。
 */
@Data
public class Function {
    private String sqltype;
    private String funcName;
    private String sql;
    private Object resultType;
    private String parameterType;
}