package com.story.storyadmin.basic.stream;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author: lipan
 * @date: 2019-06-08
 * @description:  通过Supplier创建无限序列
 *
 */
 public class NaturalSupplier implements Supplier<BigInteger> {

    BigInteger next = BigInteger.ZERO;

    @Override
    public BigInteger get() {
        next = next.add(BigInteger.ONE);
        return next;
    }

    public static void main(String[] args) {
         //表示全体自然数的Stream
        Stream<BigInteger> s = Stream.generate(new NaturalSupplier());

        //死循环操作
        s.forEach(System.out::println);

        //无限序列变成有限序列  截取前100个元素
        s.limit(100).forEach(System.out::println);  //OK！
    }
}
