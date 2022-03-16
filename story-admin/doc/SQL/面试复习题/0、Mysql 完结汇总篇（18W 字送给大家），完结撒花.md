> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/i8SHizPrBMQ2GIhQP7ozRw)

hello 大家好，我是黎杜，上一篇写了关于 Mysql 的日志篇，有兴趣的可以看一看，距离上一次的写完了 JVM 的调优所有部分：[如何啃下 JVM 这座大山，完结撒花（完结篇）](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247489013&idx=1&sn=c605a4295d989243b7ed0abe18d11c6a&scene=21#wechat_redirect)，这些词也写完了关于 Mysql 的所有内容。

我根据自己的思维导图汇总了一下所有的 Mysql 的篇，基本都已经覆盖了，并且基本的内容都有相关的文章覆盖：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljILPSprWudyVaaJibAgyXUJjcqdFsAbK3hobEPDQPIU2jTbac6YLYMRhkjAzaN1piasAicfPicopnW2g/640?wx_fmt=png)

所以这一篇做一下汇总，便于大家学习，基本都是上面按照上面的思维导图来学习，需要上面的思维导图的可以添加我微信：**abc730500468** 来获取。

首先，个人推荐学习的 Mysql 的书籍如下：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljILPSprWudyVaaJibAgyXUJguq4a2egZ7HMibRICqpQoHRbPvDXvSRWCllGuliaiaialX9OTQ97vQXicWw/640?wx_fmt=png)

我们比较熟悉的就是《Mysql 45 讲》、《MySQL 技术内幕  InnoDB 存储引擎  第 2 版》、《MySQL 性能调优与架构设计》、《高性能 mysql 第三版》这四个都是市面上推荐比较多的。

书挺多的，章节的内容很多，我们学习只需要学自己需要的部分即可，具体学习的内容可以参考这一篇：[怎么学好 Mysql？学什么？怎么学？聊一聊个人的亿点意见](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247489047&idx=1&sn=fc2a8e4a051c4ca103520b127d766e9e&scene=21#wechat_redirect)

Mysql 的第一篇基础：[Mysql 前奏！！！先来个 2 万字的基础总结](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247489020&idx=1&sn=efe65050943baf5f6b4fb9642973b760&scene=21#wechat_redirect) 还有我自己之前写的一篇原创：[万字长文，最硬核的 mysql 知识总结](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483886&idx=2&sn=3b9a0dde6fa9b06f3fba6a0aeca83820&scene=21#wechat_redirect)。

上面两篇主要是基础，Mysql 的语法，基本的 crud 操作，有基础的可以直接绕过。

接下来就是深入 Mysql 的索引，两万字的 Mysql 索引文章献给你：[十万个为什么，精通 Mysql 索引](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247489108&idx=1&sn=81eca109468ede7caab7467c3c1e2018&scene=21#wechat_redirect)和[为了把 mysql 的索引底层原理讲清楚，我把计算机翻了个底朝天](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483934&idx=1&sn=e95a47a8255ac4a814a53afbc54e513a&scene=21#wechat_redirect)、[助你进大厂，这些 Mysql 索引底层知识你是必须知道的](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484514&idx=1&sn=69172d771096747dce7f4c2c90c69cf0&scene=21#wechat_redirect)、[阿里一面，给了几条 SQL，问需要执行几次树搜索操作？](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247488760&idx=1&sn=b37164d4fb6e0742a6c80cc4d85144c6&scene=21#wechat_redirect)。

四篇索引文章以及足够详细的讲解索引部分了，写索引我也写到吐了，这四篇文章里面已经非常的全面讲解了 Mysql 的索引，包括索引数据结构、最左前缀原则、以及索引优化原理和一些个人的经验，读者的评价也是非常不错的。

第五篇是关于 Mysql 的 join 原理：[Mysql 中 join 的那些事](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247489126&idx=1&sn=0be55deb921c49bd3b592dd155407b9d&scene=21#wechat_redirect)，详细的讲解了 join 的三种算法，以优化 join 的手段。

第六篇是 order by 的原理篇和优化篇：[看一遍就理解：order by 详解](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247488756&idx=1&sn=4d99022ad1fdf00288ae3d781fdaccdc&scene=21#wechat_redirect)，这一篇不是个人写的原创，但是也是高质量的文章，所以推荐给大家学习，因为我个人也看了，也是参考《Mysql 45 讲》输出的文章。

第七篇关于 Mysql 事务的：[我以为我对 Mysql 事务很熟，直到我遇到了阿里面试官](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484313&idx=1&sn=6801e8822dfb9b4f2192960c8c3d5530&scene=21#wechat_redirect)，详细的讲解了事务的原理、特性。

这篇文章，在 csdn 也被推荐到了首页，并且有着七万多的阅读，不过也挺多人喷的，也习惯了：https://blog.csdn.net/qq_43255017/article/details/106442887

除了上面写的本地事务，本号之前有转载一篇关于微服务、分布式事务的：[不懂分布式事务，别说你懂微服务！](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247487319&idx=1&sn=4e1f83614da2d05e5f6951e53ccf0a48&scene=21#wechat_redirect)，可以相互之间进行比较学习。

第八篇是 Mysql 的锁机制，锁机制是比较复杂的部分，并且也关联到事务，虽然事务的部分原理原理是基于锁机制的，但是对于事务的原理还是比较容易理解的，所以有了事务的原理基础，再去理解锁机制是比较好理解：[大厂面试官必问的 Mysql 锁机制](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484473&idx=1&sn=e1093c8ebc4e6b105dbd41e1065ad0e8&scene=21#wechat_redirect)

其中，锁机制一个比较重要的一个知识点和面试中可能会问到的就是 Mysql 的死锁，关于怎么排查死锁，以及解决死锁，可以参考这一篇文章：[阿里二面：怎么解决 MySQL 死锁问题的？](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247488725&idx=1&sn=5342c4d4b84f533beb9809e7eb759b06&scene=21#wechat_redirect)

还有一篇就是关于数据库悲观锁了乐观锁的文章：[浅谈数据库乐观锁、悲观锁](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483930&idx=1&sn=f2224e08fae5abdfe0729f80c0b69496&scene=21#wechat_redirect)

事务、隔离级别、阻塞、死锁的综合篇，也可以看这一篇：[8000 字 | 32 张图 | 一文搞懂事务 + 隔离级别 + 阻塞 + 死锁](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247488731&idx=1&sn=22b6cafc3f5f8cfadc347ba7333abde7&scene=21#wechat_redirect)

第九篇算是比较冷门的，之前是看了《MySQL 技术内幕  InnoDB 存储引擎  第 2 版》写的，关于 InnoDB 的文件结构的：[深入 mysql 的 innodb 存储引擎之文件结构](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483943&idx=1&sn=e1564312d22eb52e537fa77ab8807dbf&scene=21#wechat_redirect)

第十篇是 Mysql 的日志篇，包括 binlog、redo log、undo relog、relay log、slow_query_log：[Mysql 的日志那些事](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247489139&idx=1&sn=310c85d09efa0771285d415725e6877f&scene=21#wechat_redirect)。

有兴趣的可以看，里面的有一部分是关于慢 sql 的部分，大家可以学习一下。

第十一篇就是高可用、高性能的基础篇主从复制原理篇：[小白都能懂的 Mysql 主从复制原理（原理 + 实操）](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484781&idx=1&sn=dc6ddb524825ec6b73f5ce0407ba6c5d&scene=21#wechat_redirect)

还有一篇是转载其他博主的主从延迟的原因，以及解决方案：[面试官：Mysql 中主库跑太快，从库追不上怎么整？](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247488741&idx=1&sn=a3183f3a3070707c135a0692c8474f4e&scene=21#wechat_redirect)，可以对比进行学习。

后面的部分就是关于 Mysql 的面试部分，以及一些关于实际的业务实战、优化的文章。

其中第十二篇是关于 select 语句的执行的原理：[面试官：听说你 sql 写的挺溜的，你说一说查询 sql 的执行过程](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483958&idx=1&sn=9ec13738865607910143ec9db8e9bb38&scene=21#wechat_redirect)

第十三篇是 select 与 update 的执行区别：[面试官：你知道 select 语句和 update 语句分别是怎么执行的吗？](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247487079&idx=1&sn=65d31e70066690d10917a0e241a9b37a&scene=21#wechat_redirect)

第十四篇 20 道 Mysql 面试题：[精心为你准备的最全的 20 道 Mysql 面试题。](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247487126&idx=1&sn=dd36423f6726852bc18eeef0d7c4e4da&scene=21#wechat_redirect)，全面的总结了所有会碰见的 Mysql 的面试题，又可以和面试官互扯一波了。

下面就全部作为汇总一起，就不去一篇一篇的介绍了。

**调优篇：**

*   [MySQL 海量数据优化（理论 + 实战） 吊打面试官](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247488248&idx=1&sn=b3b14f306ba34804e7108acc83b36dfc&scene=21#wechat_redirect)

*   [SQL 优化最干货总结 - MySQL(2020 最新版）](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247488002&idx=1&sn=130cef298442bf10404b9585f0d7768e&scene=21#wechat_redirect)

*   [如何完成一次快速的查询？](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247488172&idx=1&sn=d5ea1d487ca4fe4429cca67c86708874&scene=21#wechat_redirect)

*   [常见的 SQL 错误用法，避免采坑！！！](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247487807&idx=1&sn=60ff439440fd0bfb793f605510cded5a&scene=21#wechat_redirect)

*   [explain 都不会用，你还好意思说精通 Mysql 查询优化？](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484961&idx=1&sn=6fd5eb8df090f7ec9713d3c51f4ed418&scene=21#wechat_redirect)


**面试篇：**

*   [数据库跟缓存的双写一致性](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247488600&idx=1&sn=05fa1cf9c57df30088c52e1750790ded&scene=21#wechat_redirect)

*   [史上最全的数据库面试题，不看绝对后悔！](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247488241&idx=1&sn=13e0c444af4961d895e9ed53ffc49a0c&scene=21#wechat_redirect)

*   [去阿里面试被问：如果是 MySQL 引起的 CPU 消耗过大，你会如何优化？](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247485080&idx=1&sn=738e14940cfac5916baff189c093dee3&scene=21#wechat_redirect)

*   [精心为你准备的最全的 20 道 Mysql 面试题。](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247487126&idx=1&sn=dd36423f6726852bc18eeef0d7c4e4da&scene=21#wechat_redirect)

*   [分布式锁（数据库、Redis、ZK）拍了拍你](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484635&idx=1&sn=63f57881e62d8244080f4df9b3fcc96d&scene=21#wechat_redirect)


**原理篇：**

*   [宝贝睡了吗？这 1W 字详解的 InnoDB 原理送给你！](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247487950&idx=1&sn=1d48cf16b0b09f18f43ff40a1eddbef1&scene=21#wechat_redirect)

*   [删库一定要跑路吗？手把手教你 MySQL 数据恢复](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247485123&idx=1&sn=676cfe69378c5eb606a8a22339c8da9c&scene=21#wechat_redirect)


最后来一篇硬菜，4 万字的 Mysql 总结：[MySQL 四万字精华总结 + 面试 100 问，和面试官扯皮绰绰有余（收藏系列）](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484735&idx=1&sn=cebe2a175f5a3716aa04e91ed837d694&scene=21#wechat_redirect)

上面的所有 Mysql 教程送给大家，按照上面的顺序去看，下一个 Mysql 大神就是你了。

好了，Mysql 的所有文章都输出完了，基本每一个技术点都有涉及到，并且深入的进行原理讲解，有些还有运维方面的实操，比如主从的搭建。

有人会问学那么多你记得住吗？说的真没有人能详细的记住。但是，知识就是这样的，不可能一下子吃成一个胖子，都是反复的回顾，不断的温馨，你每经过一个阶段的提升，对于以前的回顾的知识都会有重新的认识和领悟。

当你能够把所有的知识点在实际的业务中串起来的时候，说明你对于知识又更进一步的了解和深入，可能刚开始我们是学敲代码，越到后面，我们就变成了学思维、学设计，不在于纠结每一行代码。

我感觉现在个人的提升还是比较大的，之前我敲代码是基于实现，现在都会多方面考虑，怎么去优化接口（异步编程），减少接口的响应时间，优化 sql（字段怎么冗余，索引怎么创建合适），JVM 的调优参数的设置，对基本的 crud 代码不太感兴趣了。

现在，接到需求都是会认真的去思考每一个场景，每一个优化点，并不会立马去实现，因为我感觉实现是最简单的事了，大部分的实现都花在了思考上，几乎一天下来就两三个小时在敲代码，其余的时间都是在思考或者看文章，有时间还会做做思维导图总结总结。

所以，个人感觉提升还是有所帮助。

吹得有点过了，哈哈哈。所以，大家一起加油吧！！！

这就是我个人学习的感悟吧，上面的每一篇基本都是高质量的，即使是有一些是转载的，我也会审核，没有质量的我都不会发到本号中，所以，都值得大家一看。

下面，我是打算连载并发编程的文章，因为最近，在项目中，为了保证数据的一致性，防止脏数据的出现，也用到了一些并发的工具类，还有异步编程，边学边用。

好了，这一期就到这里了，我是黎杜，我们下一期见，如果感觉我的文章对你有帮助的，谢谢大家点个赞。

```
1.精心为你准备的最全的20道Mysql面试题。


2.万字好文，电商秒杀系统架构分析与实战！


3.面试官：你知道select语句和update语句分别是怎么执行的吗？

4.不懂分布式事务，别说你懂微服务！


5.【面经】深入Spring Cloud架构组成

6.深入理解：一文讲透RabbitMQ

7.SQL优化最干货总结-MySQL(2020最新版）

8.面试官：如何保障消息100%投递成功、消息幂等性？

9.通俗讲解分布式锁，看完不懂算作者输

黎杜编程
专注Java技术文章输出100年
73篇原创内容
公众号
```