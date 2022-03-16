package com.story.storyadmin.basic.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author: lipan
 * @date: 2019-06-09
 * @description:
 *
 * Stream.reduce()是一个Stream的聚合方法 把一个Stream的所有元素集合成一个结果
 *
 * reduce()方法：
 * 将一个Stream的每一个元素一次作用于BiBuction,并将结果合并
 * reduce是聚合方法
 * 聚合方法会立刻对Stream进行运算
 */
public class StreamReduceSample {

    public static void main(String[] args) {

        Long n1= Stream.of(1, 2, 6, 8, 9).count(); //5

        //求和
        int s1=  Stream.of(1, 2, 6, 8, 9).reduce((acc, n) -> acc + n).get(); //26
        //等同于
        int s2= IntStream.of(1, 2, 6, 8, 9).sum();  //26

        //求积
        int s3 = Stream.of(1, 2, 6, 8, 9).reduce((acc, n) -> acc * n).get();  //864
        int s4= Stream.of(1, 2, 6, 8, 9).reduce(1000, (acc, n) -> acc * n);  //864000 提供了100作为初始值


       // test2();
        test3();
    }

    public static void test1() {
        int r = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .reduce((acc, x) -> acc * x)
                .get();
        System.out.println(r); //362880
    }

    public static void test2() {
        int r = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .reduce(100, (acc, x) -> acc * x);
        System.out.println(r); //36288000
    }

    public static void test3() {
        String[] array = "JDK Stream API supports functional-style operations".split(" ");
        String result= Arrays.stream(array)
                .map(String::toLowerCase)
                .reduce((acc, s) -> acc + " ~ " + s)
                .get();
        System.out.println(result);
        //jdk ~ stream ~ api ~ supports ~ functional-style ~ operations  类似于String的join方法
    }
}
