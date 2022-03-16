package com.story.storyadmin.basic.exception;

import java.io.UnsupportedEncodingException;

/**
 * @author: lipan
 * @date: 2019-06-08
 * @description:
 */
public class Main {
    public static void main(String[] args) {
       // packagetest("UTF-8");
       // packagetest("ABC");

        //异常捕获的try catch finally处理
     //  process("abc");  //java.lang.NumberFormatException: For input string: "abc"
      //  process("0"); //java.lang.ArithmeticException: / by zero

        //抛出异常
        process1();
    }

    private static void test(String encoding) {  //可以使用debug来查看过程

        System.out.println("Test encoding " + encoding + "...");

        try {
            "packagetest".getBytes(encoding);
            System.out.println("ok");
        } catch (UnsupportedEncodingException e) {
         //  e.printStackTrace();  //打印异常的传播栈，对于调试非常有用
            System.out.println("Failed.");
            System.out.println(e);  //打印输出异常信息
        }
    }

    private static void process(String s) {
//
//        try {
//            int n = Integer.parseInt(s);
//            int m = 100 / n;
//        } catch (NumberFormatException e) {
//            System.out.println(e);
//            System.out.println("Bad input.");
//        } catch (ArithmeticException e) {
//            System.out.println(e);
//            System.out.println("Bad input.");
//        } finally {
//            System.out.println("end process.");
//        }

        //改进：将两个exception合并写在一起
        //1。catch子句的异常匹配顺序非常重要。子类必须放在前面
        try {
            int n = Integer.parseInt(s);
            int m = 100 / n;
        } catch (NumberFormatException | ArithmeticException e){  //2。catch可以匹配多个非继承关系的异常
            System.out.println(e);
            System.out.println("Bad input.");
        } finally {  //finally语句不管是否异常，一定执行
            System.out.println("end process.");
        }
    }

    private static void process1() {
        try {
            process2();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("END.");
        }
    }

    static void process2() {
        //process3();
      //  process4();
        process5();
    }

    static void process3() {
        Integer.parseInt(null);
    }

    //改变一：异常转变 当我们捕获到NumberFormatException的时候，我们可以抛出一种新的异常
    static void process4() {
        try {
            Integer.parseInt(null);
        } catch (NumberFormatException e) { //原始异常
            throw new IllegalArgumentException(e); //在抛出新的异常的时候必须将新的原始的异常 e 传入到新的异常中，便于跟踪异常信息
        }
    }

    static void process5() {
        try {
            Integer.parseInt(null);  //在程序可能出错的地方debug
        } catch (NumberFormatException e) { //原始异常
            throw new IllegalArgumentException(e); //在抛出新的异常的时候必须将新的原始的异常 e 传入到新的异常中，便于跟踪异常信息
        } finally {
            System.out.println("finally...");
            throw new NullPointerException();  //finally里面抛出的异常会覆盖掉后面进入catch里面将要执行抛出的异常 最后只会抛出java.lang.NullPointerException异常
                                               //尽量避免在finally里面抛出异常
        }
    }


    /**
     * 如何保存所有的异常信息？
     * 1。用origin变量保存原始异常
     * 2。如果存在异常，用addSuppressed()添加新异常
     * 3。如果存在原始异常，或者新异常，最后在finally抛出
     * @throws Exception
     */
    static  void test() throws Exception {

        Exception origin = null;
        try {
            process6("");
        } catch (Exception e) {
            origin = e;
            throw new RuntimeException(e);
        }finally {
            try {
                throw new NullPointerException();
            } catch (Exception e) {
                if (origin != null) {
                    origin.addSuppressed(e);
                } else {
                    origin = e;
                }
            }
            if (origin != null) {
                throw origin;
            }
        }
    }

    /**
     * 如何获取所有的异常信息？
     * 用getSuppressed()获取所有Suppressed Exception (屏蔽的异常)
     * @param s
     */
    static void process6(String s) {
        try {
            process7("");
        } catch (Exception e) {
            e.printStackTrace();
            for (Throwable t : e.getSuppressed()) {
                t.printStackTrace();
            }
        }
    }

    static void process7(String s) {

    }

}
