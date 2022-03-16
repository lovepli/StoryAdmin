package com.story.storyadmin.interceptor;

/**
 * @author: lipan
 * @date: 2021年08月25日 12:10 下午
 * @description:
 */
public class Demo {
    /**
     * 过滤器-->spring框架来管理（如过滤中文乱码，防xss）
     * 拦截器-->springmvc框架来管理(拦截器只是拦截springmvc框架的controller)
     * 监听器-->spring框架来管理
     */
    /**
     * 项目中的springmvc框架(视图层)用到的地方有：配置类型转换器，配置全觉异常处理，配置拦截器
     * 项目中的spring框架(业务层)负责处理service层业务
     * 项目中的mybatis框架(持久层)负责操作sql数据库
     */
}
