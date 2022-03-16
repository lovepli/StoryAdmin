> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [www.toutiao.com](https://www.toutiao.com/i6933022277248336388/)

Java8 stream 复杂使用场景，你遇到过吗？
==========================

原创 2021-02-26 09:00· [一灯架构](/c/user/token/MS4wLjABAAAAPeHh4VHxdSVA9adkZXQymOMDUEIdFFXtSLa_PQdunHs/?source=tuwen_detail)

Java8 stream 流在工作中很常用，能够大大提升我们的开发效率，但是有些更复杂的场景，初级程序员可能很少遇到，资深程序员在日常工作会经常遇到，这些场景你知道解决办法吗？

1. 按照属性 ID 去重对象
   ===============

虽然 distinct() 方法也可以进行去重，但是只能比较整个对象，不能比较对象里属性。

![](https://p3-tt.byteimg.com/origin/pgc-image/e7a86edb014e409dbdb26f7b66a550c0?from=pc)

我们可以这样去重，新建一个方法，利用 HashMap 的 key 不能重复的特性，进行对象去重。

![](https://p6-tt.byteimg.com/origin/pgc-image/cb1f8d53160e484f9f1970aa3507656b?from=pc)

2. List 转换成 Map 时遇到重复主键
   =======================

![](https://p1-tt.byteimg.com/origin/pgc-image/ef972292c8cf4b3a80d9f73515f98ced?from=pc)

这样转换会报错，因为 ID 重复。

![](https://p6-tt.byteimg.com/origin/pgc-image/ecc0e369c7264e13ae1a1ce0b91766a0?from=pc)

可以这样做

![](https://p1-tt.byteimg.com/origin/pgc-image/f8d8b261aeb449d8b86afd80d53e2173?from=pc)

3. List 集合转换
   ============

**怎么把 List<List<User>> 类型的集合转换 List<User > 类型**

![](https://p1-tt.byteimg.com/origin/pgc-image/eb4bacfa2ca042a992dd33b6ddaab807?from=pc)

4. BigDecimal 求和
   ================

![](https://p6-tt.byteimg.com/origin/pgc-image/04f41b2322d243d4b12f1b1423b22a3d?from=pc)

你还遇到过 Java8 stream 流的哪些复杂场景？