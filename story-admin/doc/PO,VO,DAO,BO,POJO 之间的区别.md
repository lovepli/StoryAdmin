> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/4EfuvEfkvXlJpzXCzXwo8Q)

![](https://mmbiz.qpic.cn/sz_mmbiz_jpg/knmrNHnmCLEMNImeB2Svm2sMNjIicgCObicQAj42AySRfib8MxhdPia9laCm9VVYGovSBQQ61a6ORCibiblqTmWVtvdQ/640?wx_fmt=jpeg)

来源：网络

今天，咱来各种 OOOOOOOOOOOO！

VO
--

value object：值对象。

通常用于业务层之间的数据传递，由 new 创建，由 GC 回收。

PO
--

persistant object：持久层对象。

对应数据库中表的字段。VO 和 PO 都是属性加上属性的 get 和 set 方法；表面看没什么不同，但代表的含义是完全不同的。

DTO
---

data transfer object：数据传输对象。

表里面有十几个字段：id,name,gender(M/F),age,conmpanyId(如 001)...

页面需要展示四个字段：name,gender(男 / 女),age,conmpanyName(如今日头条股份有限公司)。

DTO 由此产生，一是能提高数据传输的速度（减少了传输字段），二能隐藏后端表结构。

![](https://mmbiz.qpic.cn/mmbiz_jpg/6mychickmupVjIsQaIrO1VGhxQvZmkL51nS6HxoKibNec3VJen4ibylBdBqLlKKVzkt3ZRfDj45HiayzTj9ia4Af2Zw/640?wx_fmt=jpeg)

图片

BO
--

business object：业务对象。

BO 把业务逻辑封装为一个对象。我理解是 PO 的组合，比如投保人是一个 PO，被保险人是一个 PO，险种信息是一个 PO 等等，他们组合起来是第一张保单的 BO。

POJO
----

plain ordinary java object：简单无规则 java 对象。

纯的传统意义的 java 对象，最基本的 Java Bean 只有属性加上属性的 get 和 set 方法。可以转化为 PO、DTO、VO；比如 POJO 在传输过程中就是 DTO。

![](https://mmbiz.qpic.cn/mmbiz_jpg/6mychickmupVjIsQaIrO1VGhxQvZmkL5118lEqFtDDlkjm3BnALN7HaJqfZwcsgnJQXe5dt0k4QckDib3fVIhH1w/640?wx_fmt=jpeg)

图片

DAO
---

data access object：数据访问对象。

主要用来封装对数据的访问，注意，是对数据的访问，不是对数据库的访问。

![](https://mmbiz.qpic.cn/mmbiz_jpg/6mychickmupVjIsQaIrO1VGhxQvZmkL51gib4szUG2j8ThywxKnyXn9TLnGYGaMOS344IhFtjXcxlwebNPPPz42w/640?wx_fmt=jpeg)

图片

```
1. 超神了！因为一次接口超时，我一路排查到了内核代码

2. 电商金额计算的 4 个坑，千万注意了！

3. 一个多月的努力，FGC发生频率优化了400倍

4. 记一次性能优化，单台 4 核 8G 机器支撑 5 万 QPS

最近面试BAT，整理一份面试资料《Java面试BATJ通关手册》，覆盖了Java核心技术、JVM、Java并发、SSM、微服务、数据库、数据结构等等。

获取方式：点“在看”，关注公众号并回复 Java 领取，更多内容陆续奉上。

文章有帮助的话，在看，转发吧。

谢谢支持哟 (*^__^*）
```