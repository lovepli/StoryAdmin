package com.story.storyadmin.basic.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author: lipan
 * @date: 2019-06-08
 * @description:
 *
 * 使用JDK Logging类的异常处理 控制台可以打印输出显示日志的时间   Logging定义了7个日志级别
 * 1.日志记录可以按照级别分类，便于打开或者关闭某些级别
 * 2.可以根据配置文件调整日志，无需修改代码
 * 3.JDK提供了Longging:java.util.logging
 */
public class LoggingDemo {

    public static void main(String[] args) {
        Logger logger = Logger.getGlobal();
       // logger.setLevel(Level.WARNING);  //设置打印的日志级别为WARNING  则所有info的输出都不见了，只有WARNING得输出，根据日志输出级别，高于WARNING级别的将被打印输出
        logger.info("Create new People");
        People p = new People("xiao Ming");
        System.out.println(p.hello());
        try {
            p.test();
        } catch (Exception e) { //程序报错会打印
            logger.log(Level.WARNING,"packagetest() is wrong.",e);
        }
        logger.info("Program end.");
    }
}
