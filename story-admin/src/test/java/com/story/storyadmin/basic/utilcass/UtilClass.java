package com.story.storyadmin.basic.utilcass;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author: lipan
 * @date: 2019-06-07
 * @description: 常用的工具类
 */
public class UtilClass {



    public static void main(String[] args) {

        //Math提供了数学计算的静态方法  常量：PI=3.14159...   E=2.71828
        //abs/min/max
        //pow/sqrt/exp/log/log10...
        //sin/cos/tan/asin/acos
        Math.pow(2,10);  //2 ^10 =1024
        Math.sqrt(2); //根号2
        Math.exp(2); //e 的平方
        Math.log(4); //log 以e为底数，4为指数
        Math.log10(100); //log 以10为底数，100为指数
        Math.sin(Math.PI / 6); //sin(n/6) = 0.5
        Math.cos(Math.PI / 6); //cos(n/3) = 0.5


        //Math.random() 生成一个随机数
        // 0 <= 随机数 <1
        double x1 = Math.random();
        //可用于某个区间的随机数
        long MIN = 1000;
        long MAX = 9000;
        double x2 = Math.random() * (MAX - MIN) + MIN;
        double d = (long) x2;

        //Random 用来创建伪随机数
        //nextInt nextLong nextFlooat..
        Random r = new Random();
        r.nextInt(); //生成下一个随机int
        r.nextLong();
        r.nextFloat(); //生成下一个随机float 介于 0～1
        r.nextDouble();//生成下一个随机double 介于 0～1
        //nextInt(N)生成不大于N的随机数
        r.nextInt(10); //生成0～10之间的随机数，不包括10

        /**
         * 什么是伪随机数？
         * 1。给定种子后伪随机数算法会生成完全相同的序列
         * 2。不给定种子是Random使用系统当前时间戳作为种子
         */
        Random r2 = new Random(12345);
        for (int i = 0; i < 10; i++) {
            System.out.println(r2.nextInt(100));
        }
        //53,23,80,41,...

        //SecureRandom用来创建安全的随机数  缺点：比较慢
        SecureRandom sr = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            System.out.println(sr.nextInt(100));
        }

        // BigInteger在内部用任意多个int[] 来表示非常大的整数  因为基本数据类型 int和long只能表示有限范围的整数 多用于财务计算 同样也继承Number
        BigInteger bi = new BigInteger("1234567890");
        System.out.println(bi.pow(5));  //五次方

        //BigDecimal 表示任意精度的浮点数，多用于财务计算 同样也继承Number
        BigDecimal bd = new BigDecimal("123.10");
        System.out.println(bd.multiply(bd));





    }


}
