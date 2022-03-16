package com.story.storyadmin.basic.exception.throwable;


import java.util.HashMap;

/**
 * @author: lipan
 * @date: 2019-06-04
 * @description:
 */
public class ExceptionHandleMechanism {

    private static int doWork() {
        try {
            int i = 10 / 0;//会抛出异常
            System.out.println("i=" + i);
        } catch (ArithmeticException e) {
            //捕获ArithmeticException
            System.out.println("ArithmeticException:" + e);  //应该将异常输出到日志文件存储起来便于查看
            return 0;
        } catch (Exception e) {  //应该捕获特定的异常，要清楚明确的异常
            //捕获Exception
            System.out.println("Exception:"+e);
            return 1;
        } finally {  //finally会在catch语句的return语句之前执行
            System.out.println("Finally");
            //return 2;
        }
        return 2;
    }

    public static void main(String[] args) {
        doWork();
        System.out.println("Mission Complete.");

    }
}
