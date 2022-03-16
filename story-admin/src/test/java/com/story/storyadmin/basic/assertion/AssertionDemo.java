package com.story.storyadmin.basic.assertion;

import static java.lang.Math.abs;

/**
 * @author: lipan
 * @date: 2019-06-08
 * @description:
 * 断言（Assertion）是一种程序调试方式
 * 1。使用assert关键字
 * 2。断言条件预期为true
 * 3。如果断言失败，抛出AssertionError
 * 4.可选的断言消息
 *
 * 断言的特点；
 * 1。断言失败会抛出AssertionError，导致程序结束退出
 * 2。不能用于可恢复的程序错误
 * 3。只能用于开发的测试阶段
 *
 */
public class AssertionDemo {

    static double abs(double d) {
       // return d >= 0 ? d : -d;  //正确的语句
        return d < 0 ? d : -d;  //故意使断言出错的语句
    }

    public static void main(String[] args) {

//        double x = abs(-123.45);
//        assert x >= 0 : "x must >= 0 but x = " + x;
//        System.out.println(x);

        //这里我们只能在单元测试里面AssertionDemoTest类来验证断言的使用
    }
}
