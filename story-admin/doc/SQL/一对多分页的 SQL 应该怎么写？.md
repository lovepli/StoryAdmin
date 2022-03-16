> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/_gFSfuMzyoCx26Wk_lVzwg)

![](https://mmbiz.qpic.cn/sz_mmbiz_gif/zuF5sJGRDCsXRJOCqjgibGA0fWBtMXojos0wX2r9lhCds24j1cWIFd3Wuwncg3icalAjmweKngyHmibHfWc4CuErQ/640?wx_fmt=gif)

1. 前言

--------

**MySQL** 一对多的数据分页是非常常见的需求，比如我们要查询商品和商品的图片信息。但是很多人会在这里遇到分页的误区，得到不正确的结果。今天就来分析并解决这个问题。

2. 问题分析
-------

我们先创建一个简单商品表和对应的商品图片关系表，它们之间是一对多的关系：

![](https://mmbiz.qpic.cn/sz_mmbiz_png/zuF5sJGRDCt0KNG8S7vvO4lPPKNVPNqrDxArsFkL1icvDsBYu5MV4t16Df34z22TyCWvGFqpSjyqqcnONrThHiaQ/640?wx_fmt=png)

一对多关系

然后我分别写入了一些商品和这些商品对应的图片，通过下面的左连接查询可以看出它们之间具有明显的一对多关系：

```
SELECT P.PRODUCT_ID, P.PROD_NAME, PI.IMAGE_URL
FROM PRODUCT_INFO P
         LEFT JOIN PRODUCT_IMAGE PI
                   ON P.PRODUCT_ID = PI.PRODUCT_ID
```

![](https://mmbiz.qpic.cn/sz_mmbiz_png/zuF5sJGRDCt0KNG8S7vvO4lPPKNVPNqro4icNribTAvyZmWLsFM49DO4qFmpo3UibBruOK7wIwCLQBOIiadibZxq0hA/640?wx_fmt=png)

所有的一对多结果

按照传统的思维我们的分页语句会这么写：

```
    <resultMap id="ProductDTO" type="cn.felord.mybatis.entity.ProductDTO">
        <id property="productId" column="product_id"/>
        <result property="prodName" column="prod_name"/>
        <collection property="imageUrls"  ofType="string">
            <result column="image_url"/>
        </collection>
    </resultMap>

    <select id="page" resultMap="ProductDTO">
        SELECT P.PRODUCT_ID, P.PROD_NAME,PI.IMAGE_URL
        FROM PRODUCT_INFO P
                 LEFT JOIN PRODUCT_IMAGE PI
                           ON P.PRODUCT_ID = PI.PRODUCT_ID
        LIMIT #{current},#{size}
    </select>
```

当我按照预想传入了`(0，2)`想拿到前两个产品的数据，结果并不是我期望的：

```
2020-06-21 23:35:54.515 DEBUG 10980 --- [main] c.f.m.mappers.ProductInfoMapper.page     : ==>  Preparing: SELECT P.PRODUCT_ID, P.PROD_NAME,PI.IMAGE_URL FROM PRODUCT_INFO P LEFT JOIN PRODUCT_IMAGE PI ON P.PRODUCT_ID = PI.PRODUCT_ID limit ?,?
2020-06-21 23:35:54.541 DEBUG 10980 --- [main] c.f.m.mappers.ProductInfoMapper.page     : ==> Parameters: 0(Long), 2(Long)
2020-06-21 23:35:54.565 DEBUG 10980 --- [main] c.f.m.mappers.ProductInfoMapper.page     : <==      Total: 2
page = [ProductDTO{productId=1, prodName='杯子', imageUrls=[http://asset.felord.cn/cup1.png, http://asset.felord.cn/cup2.png]}]
```

我期望的两条数据是杯子和笔记本，但是结果却只有一条。原来当一对多映射时结果集会按照多的一侧进行输出（期望 4 条数据，实际上会有 7 条），而前两条展示的只会是杯子的数据（如上图），合并后就只有一条结果了，这样分页就对不上了。那么如何才能达到我们期望的分页效果呢？

3. 正确的方式
--------

> **正确的思路是应该先对主表进行分页, 再关联从表进行查询。**

抛开框架，我们的 **SQL** 应该先对产品表进行分页查询然后再左关联图片表进行查询：

```
SELECT P.PRODUCT_ID, P.PROD_NAME, PI.IMAGE_URL
FROM (SELECT PRODUCT_ID, PROD_NAME
      FROM PRODUCT_INFO
      LIMIT #{current},#{size}) P
         LEFT JOIN PRODUCT_IMAGE PI
                   ON P.PRODUCT_ID = PI.PRODUCT_ID
```

这种写法的好处就是通用性强一些。但是 **MyBatis** 提供了一个相对优雅的路子，思路依然是开头所说的思路。只不过我们需要改造上面的 **Mybatis XML** 配置：

```
<resultMap id="ProductDTO" type="cn.felord.mybatis.entity.ProductDTO">
    <id property="productId" column="product_id"/>
    <result property="prodName" column="prod_name"/>
     <!-- 利用 collection 标签提供的 select 特性 和 column   -->
    <collection property="imageUrls" ofType="string" select="selectImagesByProductId" column="product_id"/>
</resultMap>
<!-- 先查询主表的分页数据    -->
<select id="page" resultMap="ProductDTO">
    SELECT PRODUCT_ID, PROD_NAME
    FROM PRODUCT_INFO
    LIMIT #{current},#{size}
</select>
<!--根据productId 查询对应的图片-->
<select id="selectImagesByProductId" resultType="string">
    SELECT IMAGE_URL
    FROM PRODUCT_IMAGE
    WHERE PRODUCT_ID = #{productId}
</select>
```

4. 总结
-----

大部分情况下分页是很容易的，但是一对多还是有一些小小的陷阱的。一旦我们了解了其中的机制，也并不难解决。多多关注：**码农小胖哥**，获取更多开发技巧。

**往期推荐：**

[

JSON 类库 Jackson 优雅序列化 Java 枚举类

2020-06-21

![](https://mmbiz.qpic.cn/sz_mmbiz_jpg/zuF5sJGRDCt0KNG8S7vvO4lPPKNVPNqrhNt0WpF8rdtRQNJzdGGInnlviaIm7UmKEfBUXkKwguakmCM8ib5hbGmg/640?wx_fmt=jpeg)

](http://mp.weixin.qq.com/s?__biz=MzUzMzQ2MDIyMA==&mid=2247485392&idx=1&sn=f532c246287cde2dd35e5e36fa2d1f69&chksm=faa2e243cdd56b55d6c3334cc229d15ff78d465b32f3dd47482a2e191231421a697eb7b01ac5&scene=21#wechat_redirect)[

刷题 2 个月，终于进了梦寐以求的大厂，数据结构和算法太 TM 重要了！

2020-06-19

![](https://mmbiz.qpic.cn/sz_mmbiz_jpg/zuF5sJGRDCsXRJOCqjgibGA0fWBtMXojosuwibAia5tgCgLZX6iaE5kGLJMceqGJzU0CFIHl5Cq0a4DTan1NoA8mAQ/640?wx_fmt=jpeg)

](http://mp.weixin.qq.com/s?__biz=MzUzMzQ2MDIyMA==&mid=2247485344&idx=1&sn=248fa514b156614539625e90f8f02b40&chksm=faa2e233cdd56b256c3293cdbfeacc196304fd43cfd070c67fcabdd49840fb5f029cea93aaf4&scene=21#wechat_redirect)[

利用 Redis 的 Geo 功能实现查找附近的位置

2020-06-18

![](https://mmbiz.qpic.cn/sz_mmbiz_jpg/zuF5sJGRDCsXRJOCqjgibGA0fWBtMXojoibeatvTf9nDud10qdnbRQDzAXnTYwcjRnfViaZPxo1O3vmVggwGxwDTw/640?wx_fmt=jpeg)

](http://mp.weixin.qq.com/s?__biz=MzUzMzQ2MDIyMA==&mid=2247485312&idx=1&sn=6ae82c827cf37b81cad67543c965f37d&chksm=faa2e213cdd56b059fb35e6435712aa68a66c3f443bd19fc5b618e6c4aa5ce448e8aef23c0c0&scene=21#wechat_redirect)

![](https://mmbiz.qpic.cn/sz_mmbiz_jpg/zuF5sJGRDCtVDib1nfDbIwN8foDoHW72TDvN7gPphBZhsvyq54SFlKcZVk2ybwiaqRD2BHSxtCzLbsv3kPPNEibKA/640?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/mmbiz_gif/XSkVV7bT8QwncsiaUgo9ibQzbCAmUXmPGBJMA3xcDajMCQWQWKrkgpBLNYkfqkANptVvdyBEQwKX1ribcN8p0vrFQ/640?wx_fmt=gif)