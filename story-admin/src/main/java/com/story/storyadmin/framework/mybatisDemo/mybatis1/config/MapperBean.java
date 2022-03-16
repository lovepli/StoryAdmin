package com.story.storyadmin.framework.mybatisDemo.mybatis1.config;

import lombok.Data;

import java.util.List;

/**
 * 用面向对象的思想设计读取xml配置后：
 */
@Data
public class MapperBean {
    private String interfaceName; //接口名
    private List<Function> list; //接口下所有方法
}

