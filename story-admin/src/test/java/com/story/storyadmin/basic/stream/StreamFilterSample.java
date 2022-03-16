package com.story.storyadmin.basic.stream;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author: lipan
 * @date: 2019-06-09
 * @description:
 */

class NaturalSupplier1 implements Supplier<Long> {  //产生自然数的序列

    long x = 0;

    @Override
    public Long get() {
        x++;
        return x;
    }
}

public class StreamFilterSample {

    static void test1() {
        Stream<Long> natural = Stream.generate(new NaturalSupplier1());
        // Stream<Long> odd = natural.filter((n) -> n % 2 == 1);  //奇数
        Stream<Long> odd = natural.filter((n) -> n % 5 == 0); //被5整除的数
        odd.limit(20).forEach(System.out::println);
    }

    //过滤用户输入的不规范的字符串
    static void test2() {
        String[] array = {"Java", "Python ", " ", null, "\n\n", " Ruby"};
        Stream<String> normalized = Arrays.stream(array)
                .filter(s -> s != null && !s.trim().isEmpty())  //过滤掉null以及空字符串
                .map(s -> s.trim());  //去首尾掉空的

        normalized.forEach(System.out::println);
    }

    public static void main(String[] args) {
        test2();

    }




}
