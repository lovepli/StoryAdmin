package com.story.storyadmin.utils.stringMethordUtil;

import java.util.Arrays;

/**
 * @author: 59688
 * @date: 2021/7/20
 * @description: String类有20多个方法，常用方法演示
 *
 */
public class StringDemo {

    public static  void fun(){
        String str1 = "Hello World";
        String str2 = "Hello World";
        String str3 = "hello world";
        String str4 = " hello world ";
        //返回字符串的长度
        System.out.println("r1: " + str1.length());
        //比较两个字符串的大小compareTo(返回的是int),0相等，负数小于，正数大于
        System.out.println("r2 : " + str1.compareTo(str2));
        //比较两个字符串的大小compareTo(返回的是int),0相等，负数小于，正数大于
        System.out.println("r3 : " + str1.compareTo(str3));
        //字符串比较compareToIgnoreCase，忽略大小写。0相等，负数小于，正数大于
        System.out.println("r4 : " + str1.compareToIgnoreCase(str3));
        //字符串查找indexOf，返回的是找到的第一个的位置，没找到返回-1。从0开始
        System.out.println("r5 : " + str1.indexOf("o"));
        //查找字符串最后一次出现的位置lastIndexOf
        System.out.println("r6 : " + str1.lastIndexOf("o"));
        //删除字符串中的一个字符,字符串从0开始的 substring(a, b)
        //返回指定起始位置（含）到结束位置（不含）之间的字符串
        System.out.println("r7 : " + str1.substring(0, 5) + str1.substring(6));

        //字符串替换,替换所有
        System.out.println("r8 : " + str1.replace("o", "h"));
        //字符串替换,替换所有
        System.out.println("r9 : " + str1.replaceAll("o", "h"));
        //字符串替换,替换第一个
        System.out.println("r10 : " + str1.replaceFirst("o", "h"));
        //字符串反转
        System.out.println("r11 : " + new StringBuffer(str1).reverse());
        //字符串反转
        System.out.println("r11’: " + new StringBuilder(str1).reverse());
        //字符串分割
        String[] temp = str1.split("\\ ");
        for (String str : temp) {
            System.out.println("r12 : " + str);
        }
        //字符串转大写
        System.out.println("r13 : " + str1.toUpperCase());
        //字符串转小写
        System.out.println("r14 : " + str1.toLowerCase());
        //去掉首尾空格
        System.out.println("r15 : " + str4.trim());
        //是否包含,大小写区分
        System.out.println("r16 : " + str1.contains("World"));
        //返回指定位置字符
        System.out.println("r17 : " + str1.charAt(4));
        //测试此字符串是否以指定的后缀结束
        System.out.println("r18 : " + str1.endsWith("d"));
        //测试此字符串是否以指定的前缀开始
        System.out.println("r19 : " + str1.startsWith("H"));
        //测试此字符串从指定索引开始的子字符串是否以指定前缀开始
        System.out.println("r20 : " + str1.startsWith("ll", 2));
        //将指定字符串连接到此字符串的结尾。等价于用“+”
        System.out.println("r21 : " + str1.concat("haha"));
        //比较字符串的内容是否相同
        System.out.println("r22 : " + str1.equals(str2));
        //与equals方法类似，忽略大小写
        System.out.println("r23 : " + str1.equalsIgnoreCase(str2));
        //判断是否是空字符串
        System.out.println("r24:  " + str1.isEmpty());
    }

    public static void fun2(){
        //TODO 注意事项
        //String.split(String regex)部分关键字需要转译
        //
        //使用字符串String 的plit 方法时，传入的分隔字符串是正则表达式，则部分关键字（比如 .[]()\| 等）需要转义。
        //
        //反例：
        //String.split(String regex) 反例
        //String[] split = "a.ab.abc".split(".");
        //System.out.println(Arrays.toString(split));   // 结果为[]
        //String[] split1 = "a|ab|abc".split("|");
        //System.out.println(Arrays.toString(split1));  // 结果为["a", "|", "a", "b", "|", "a", "b", "c"]

        //
        //正例：
        // String.split(String regex) 正例
        // . 需要转译
        //String[] split2 = "a.ab.abc".split("\\.");
        //System.out.println(Arrays.toString(split2));  // 结果为["a", "ab", "abc"]
        //
        //// | 需要转译
        //String[] split3 = "a|ab|abc".split("\\|");
        //System.out.println(Arrays.toString(split3));  // 结果为["a", "ab", "abc"]

        // 如果不进行转译的话，可以先进行replace()替换掉字符串中的“.”，为“,”，然后再对“,”进行split()分割，也能得到同样的结果
        String [] split2 = "a.ab.abc".replace(".",",").split(",");
        System.out.println(Arrays.toString(split2));

    }

    public static void main(String[] args) throws Exception {

        fun2();


    }
}