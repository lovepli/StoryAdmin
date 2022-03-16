package com.story.storyadmin.basic.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author: lipan
 * @date: 2019-06-08
 * @description:  使用apache的  Commons Logging 定义了6个日志级别
 *
 * Commons Logging 是使用最广泛的日志模块
 * Commons Logging 的API非常简单
 * Commons Logging 可以自动使用其他日志模块
 *
 */
public class LoggingDemo2 {

    //在静态方法中引用Log
    static final Log log=LogFactory.getLog(LoggingDemo2.class);



    public static void main(String[] args) {

        log.info("start...");
        log.info("Create new People");
        People p = new People("xiao Ming");
       log.info(p.hello());
        try {
            p.test();
        } catch (Exception e) { //程序报错会打印
            log.error("Exception",e);
        }
        log.info("Program end.");
        log.warn("end...");
    }
}
