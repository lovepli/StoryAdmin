package com.story.storyadmin.basic.other;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.lang.Integer;

/**
 * @author: lipan
 * @date: 2019-06-06
 * @description:
 */
public class DemoTest {

    public static void main(String[] args) {

        //字符串和编码
        String s = "hello";
        System.out.println( s.equals("Hello")); //false
        System.out.println(s.equalsIgnoreCase("Hello"));//true 这个方法可以忽略大小写

        //是否包含子串
        System.out.println(s.contains("ll")); //true
        System.out.println(s.indexOf("ll"));  //2
        System.out.println(s.lastIndexOf("ll"));//
        System.out.println(s.startsWith("he"));  //true
        System.out.println(s.endsWith("lo"));  //true

        //去除空格
        String s1 = "  \t hello\r\n";
        System.out.println(s1.trim()); //hello  trim()方法：移除首尾空白字符  空格, \t ,\r,\n

        //提取子串
        String s2 = "hello, world";
        System.out.println( s2.substring(7));//"world"
        System.out.println(s2.substring(1, 5));//"ello"

        //大小写转换
        String s3 = "heLLO";
        System.out.println(s3.toUpperCase());  //"HELLO"
        System.out.println(s3.toLowerCase()); //"hello"

        //替换字符串
        String s4 = "hello";
        System.out.println(s4.replace('l','w')); //"hewwo"  替换一个字符
        System.out.println(s4.replace("l","w~")); //"hew~w~o" 替换字符串

        //使用正则表达式替换子串
        String s5 = "A,,B;C ,D";
        System.out.println(s5.replaceAll("[\\,\\;\\s]+", ", "));  //"A, B, C, D"

        //分割字符串
        String[] ss = s5.split("[\\,\\;\\s]+"); //{"A, B, C, D"}
        System.out.println(ss);

        //拼接字符串
        String[] arr = {"A", "B", "C"};
        String ss1 = String.join("~~", arr);
        System.out.println(ss1); //"A~~B~~C"

        //类型转换
        //把任意数据类型转换为String  这个方法是个重载的方法
        System.out.println(String.valueOf(123)); //"123"
        System.out.println(String.valueOf(true));  //"true"
        System.out.println(String.valueOf(new Object())); //"java.lang.Object@7562e299"


        //把String转换为其他类型
        //方法一：static int Integer.parselnt(String);
        //方法二：static Integer Integer.valueOf(String);
        int i = Integer.parseInt("123"); //123
        Integer I = Integer.valueOf("123");
        System.out.println(i);

        //Error
       // Integer I2 = Integer.getInteger("123");

        //String 转换为char[] 因为String类的内部实际上是维护了一个char[],所以可以互相转换
        char[] cs = s.toCharArray();
        System.out.println(cs); //{'h', 'e', 'l', 'l', 'o'}

        //char[] 转换为 String
        String s6 = new String(cs); //直接给构造函数传递一个char[] 作为参数
        System.out.println(s6); //"hello"

        try {
            //String转换为byte[]  需要注意编码
            byte[] bs1 = s.getBytes("UTF-8");
            byte[] bs2 = s.getBytes(StandardCharsets.UTF_8);

            //byte[]转换为String
            new String(bs1, "UTF-8");
            new String(bs2, StandardCharsets.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //StringBuilder要点知识 StringBuilder可以高效拼接字符串 是可变长度的对象，可以预分配缓冲区
        StringBuilder sb = new StringBuilder(1024);
        for (int k = 0; k < 1000; k++) {
            sb.append(String.valueOf(k));
        }
        String s7 = sb.toString();
        System.out.println(s7);

        //StringBuilder可以进行链式操作  查看append()方法内部实现，返回的是一个this对象。因此可以继续调用他的方法，就形成了链式
        StringBuilder sb1 = new StringBuilder(1024);
        String s8 = sb1.append("Mr  ")
                .append("Li")
                .append("!")
                .insert(0,"Hello, ")
                .toString();
        System.out.println(s8);

        //相比较String的+号操作，StringBuilder不需要特别改写字符串 + 号操作
        //因为编译器在内部自动把多个连续的 + 操作优化为Stringbuilder操作
        String s9 = "Hello, " + "World " + "!";

        //StringBuilder 与Stringbuilder 接口完全相同
        //StringBuffer是StringBuilder的线程安全版本
        //没有必要使用Stringbuffer








    }
}
