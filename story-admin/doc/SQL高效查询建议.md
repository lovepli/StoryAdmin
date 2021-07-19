> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/pUWgsEEk7j8ZddveMzZ4lg)

**点击关注上方 “数据前线”，**

**设为 “置顶或星标****”，第一时间送达干货**

为什么别人的查询只要几秒，而你的查询语句少则十多秒，多则十几分钟甚至几个小时？与你的查询语句是否高效有很大关系。

今天我们来看看如何写出比较高效的查询语句。

1.  尽量不要使用 NULL 当默认值

    在有索引的列上如果存在 NULL 值会使得索引失效，降低查询速度，该如何优化呢？例如：

    SELECT *  FROM [Sales].[Temp_SalesOrder] WHERE UnitPrice IS NULL

    我们可以将 NULL 的值设置成 0 或其他固定数值，这样保证索引能够继续有效。

    SELECT *  FROM [Sales].[Temp_SalesOrder] WHERE UnitPrice =0

    这是改写后的查询语句，效率会比上面的快很多。

2.  尽量不要在 WHERE 条件语句中使用!= 或 <>

    在 WHERE 语句中使用!= 或 <> 也会使得索引失效，进而进行全表扫描，这样就会花费较长时间了。

3.  应尽量避免在 WHERE 子句中使用 OR

    遇到有 OR 的情况，我们可以将 OR 使用 UNION ALL 来进行改写

    例如：

    SELECT * FROM T1 WHERE NUM=10 OR NUM=20

    可以改写成

    SELECT * FROM T1 WHERE NUM=10

    UNION ALL

    SELECT * FROM T1 WHERE NUM=20

4.  IN 和 NOT IN 也要慎用

    遇到连续确切值的时候 ，我们可以使用 BETWEEN AND 来进行优化

    例如：

    SELECT * FROM T1 WHERE NUM IN (5,6,7,8)

    可以改写成：

    SELECT * FROM T1 WHERE NUM BETWEEN 5 AND 8.

5.  子查询中的 IN 可以使用 EXISTS 来代替

    子查询中经常会使用到 IN，如果换成 EXISTS 做关联查询会更快

    例如：

    SELECT * FROM T1 WHERE ORDER_ID IN (SELECT ORDER_ID FROM ORDER WHERE PRICE>20);

    可以改写成：

    SELECT * FROM T1 AS A WHERE EXISTS (SELECT 1 FROM ORDER  AS B WHERE A.ORDER_ID=B.ORDER_ID AND B.PRICE>20)

    虽然代码量可能比上面的多一点，但是在使用效果上会优于上面的查询语句。

6.  模糊匹配尽量使用前缀匹配

    在进行模糊查询，使用 LIKE 时尽量使用前缀匹配，这样会走索引，减少查询时间。

    例如：

    SELECT * FROM T1 WHERE NAME LIKE '% 李四 %'

    或者

    SELECT * FROM T1 WHERE NAME LIKE '% 李四'

    均不会走索引，只有当如下情况

    SELECT * FROM T1 WHERE NAME LIKE '李四 %'才会走索引。


上述这些都是平常经常会遇到的，就直接告诉大家怎么操作了，具体可以下去做试验尝试一下，今天就讲到这里，如有什么疑问，请在下方留言。

```
更多精彩内容，请关注「数据前线」

记得点「赞」和「在看」

爱你们
```