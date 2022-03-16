package com.story.storyadmin.basic.stream;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: lipan
 * @date: 2019-06-08
 * @description:
 * Stream API：
 *  不同于java.io 的InputStream 和 OutputStream
 *
 *   java.io 与Java.util.stream 的区别；
 *   1。从存储来讲：前者顺序读写byte/char    后者 顺序输出任意Java对象
 *   2。从用途来讲；前者序列化至文件/网络     后者 内存计算/业务逻辑
 *
 *   不同于java.util.List
 *   java.util.List 与 Java.util.stream 的区别；
 *   元素：前者 已分配并存储在内存      后者为分配，实时计算
 *   用途：前者 操作一组已存在的Java对像 后者 惰性计算
 *
 *   Stream 的特点：
 *   可以"存储"有限个或者无限个元素
 *   可以转换为另一个Stream
 *   计算通常发生在最后结果的获取 （惰性计算）
 *
 *   Stream API的特点；
 *   提供了一套新的流式的抽象序列
 *   支持函数式编程 / 链式操作
 *   Stream可以表示无限序列 ，并且大多数情况下是惰性求值的
 *
 */
public class StreamBasic {

    public static void main(String[] args) throws Exception {
        //创建Stream的三种方式 :通过指定元素/现有数组/现有Collection创建
        //Stream.of(T...t)
        //Arrays.stream(array)
        //collection.stream()
        Stream<Integer> s1 = Stream.of(1, 2, 3, 4, 5);

        Integer[] theArray= new Integer[]{1,2,3,4,5};
        Stream<Integer> s2 = Arrays.stream(theArray);

        List aList = new ArrayList();
        Stream<Integer> s3 = aList.stream();

        //基本类型的Stream 有IntStream / LongStream / DoubleStream
    }

    //Arrays.stream(array)
    public static void createSream() {
        String[] array = "JDK Stream API supports functional-style ".split(" ");
        //array -> Stream;
        long n = Arrays.stream(array)
                //.filter((s) -> s.equals(s.toUpperCase()))  //过滤的方法，统计的就是纯大写的字符个数  //2
                .count();
        System.out.println("How many words ?" + n);  //5
    }




    public void test1() {
        //这里关于split()方法的使用
        // 1. 如果你想要把 一个逗号分隔的字符串 转成 以逗号分隔的数组
        String str = "feilong,fei";
        //  String[] split = str.split(" ");  // 结果输出 feilong,fei
        String[] split = str.split(","); //结果输出feilong fei
        for (String string : split){
            System.out.print(string + " ");
        }
    }



}
