package com.story.storyadmin.framework.iocAndAop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 59688
 * @date: 2021/9/26
 * @description: 1.定义配置标记
 * https://www.cnblogs.com/tc971121/p/13490708.html
 * https://github.com/313989006/MyFrame
 *
 *
 * @Aspect：配置标记横切对象（方法）的地址
 *
 * @Order：配置标记横切的顺序
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    String pointcut();

    /**
     * 总结实现aop的主要流程：
     *
     * 1.定义配置标记
     *
     * 2.定义默认切面类
     *
     * 3.定义切面织入器
     *
     * 核心流程：
     *
     * 1.获取所有的切面类
     * 2.遍历容器管理的类
     * 3.筛选出匹配容器管理类的切面aspectlist
     * 4.尝试进行Aspect的织入 生成动态代理对象 并替换beancontainer中原先class对应所对应的实例对象
     *
     * 4.其他类：切面信息包装类/InvocationHandler实现类/创建代理实例类/解析Aspect表达式工具类等。
     */
}
