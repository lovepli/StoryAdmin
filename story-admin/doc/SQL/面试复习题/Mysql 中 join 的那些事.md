> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247489126&idx=1&sn=0be55deb921c49bd3b592dd155407b9d&scene=21#wechat_redirect)

大家好，我是黎杜，上一期我们聊了 Mysql 的索引篇，这一期，我们来聊一聊 Mysql 中的 join 原理，join 用法基本工作过的都会用，不管是 **left join、right join、inner join** 语法都是比较简单的。

但是，join 的原理确实博大精深，对于一些传统 it 企业，几乎是一句 sql 走天下，join 了五六个表，当数据量上来的时候，就会变得非常慢，索引对于掌握 join 的优化还是非常有必要的。

**阿里的开发手册中规定 join 不能查过三个，有些互联网是明确规定不能使用 join 的的明文规定**，那么在实际的场景中，我们真的不能使用 join 吗？我们就来详细的聊一聊。

Mysql 的 join 主要涉及到三种算法，分别是 **Simple Nested-Loop Join、Block Nested-Loop Join、Index Nested-Loop Join**，下面我们就来深入的了解这三种算法的原理、区别、效率。

首先，为了测试先准备两个表作为测试表，并且使用存储过程初始化一些测试数据，初始化的表结构 sql 如下所示：

```
CREATE TABLE `testa` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '活动主键',
  `col1` int(20) NOT NULL DEFAULT '0' COMMENT '测试字段1',
  `col2` int(20) NOT NULL DEFAULT '0' COMMENT '测试字段2',
  PRIMARY KEY (`id`),
  KEY `col1` (`idx_col1`)
)ENGINE=InnoDB AUTO_INCREMENT=782 DEFAULT CHARSET=utf8mb4 COMMENT='测试表1';


CREATE TABLE `testb` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '活动主键',
  `col1` int(20) NOT NULL DEFAULT '0' COMMENT '测试字段1',
  `col2` int(20) NOT NULL DEFAULT '0' COMMENT '测试字段2',
  PRIMARY KEY (`id`),
  KEY `col1` (`idx_col1`)
) ENGINE=InnoDB AUTO_INCREMENT=782 DEFAULT CHARSET=utf8mb4 COMMENT='测试表2';
```

初始化数据：

```
CREATE DEFINER = `root` @`localhost` PROCEDURE `init_data` () 

BEGIN
 DECLARE i INT;
 
 SET i = 1;
 WHILE ( i <= 100 ) DO
   INSERT INTO testa VALUES ( i, i, i );
  SET i = i + 1;
 END WHILE;
 
 SET i = 1;
 WHILE ( i <= 2000) DO
   INSERT INTO test2 VALUES ( i, i, i );
  SET i = i + 1;
 END WHILE;

END
```

分别初始化 testa 表为 100 条数据，testb 为 2000 条数据

Simple Nested-Loop Join
-----------------------

首先，我们执行如下 sql：

```
select * from testa ta left join testb tb on (ta.col1=tb.col2);
```

**Simple Nested-Loop Join** 是最简单也是最粗暴的 join 方法，上面的 sql 在 testb 的 col2 字段是没有加索引的，所以当 testa 为驱动表，testb 为被驱动表时，就会拿着 testa 的每一行，然后去 testb 的全表扫描，执行流程如下：

1.  从表 testa 中取出一行数据，记为 ta。

2.  从 ta 中取出 col1 字段去 testb 中全表扫描查询。

3.  找到 testb 中满足情况的数据与 ta 组成结果集返回。

4.  重复执行 1-3 步骤，直到把 testa 表的所有数据都取完。


因此扫描的时间复杂度就是 100*2000=20W 的行数，所以在被驱动表关联字段没有添加索引的时候效率就非常的低下。

假如 testb 是百万数据以上，那么扫描的时间复杂度就更恐怖了，但是在 Mysql 中没有使用这个算法，而是使用了另一种算法 **Block Nested-Loop Join**，目的就是为了优化驱动表没有索引时的查询。

Block Nested-Loop Join
----------------------

还是上面的 sql，不过通过加 explain 关键字来查看这条 sql 的执行计划：

```
explain select * from testa ta left join testb tb on (ta.col1=tb.col2);
```

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhfm6cpegI3vu7lg3P8CvcNPAaONIM7S7N82IUeAAYOvGAyYB4q89xMBYnOnQQzfqFcibQbK2CdKng/640?wx_fmt=png)

可以看到 testb 依旧是全表扫描，并且在 Extra 字段中可以看到 testb 的 **Using join buffer（hash join）**的字样，在 rows 中可以看到总扫描的行数是**驱动表行数 + 被驱动表行数**，那么这个算法与 **Simple Nested-Loop Join** 有什么区别呢？

**Block Nested-Loop Join** 算法中引入了 **join buffer** 区域，而 join buffer 是一块内存区域，它的大小由 **join_buffer_size** 参数大小控制，默认大小是 **256k**：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhfm6cpegI3vu7lg3P8CvcN7KhRicwC1quibkSMKKxw8DZ4raWUD8zWw3MwkgJd33kibMLgw1I7BTIbQ/640?wx_fmt=png)

在执行上面的 sql 的时候，它会把 testa 表的数据全部加载到 join buffer 区域，因为 join buffer 是内存操作，因此相对于比上面的 simple 算法要高效，具体的执行流程如下：

1.  首先把 testa 表的所有数据都加在到 join buffer 里面，这里的所有数据是 select 后面的 testa 的字段，因为这里是 select *，所以就是加载所有的 testa 字段。

2.  然后遍历的取 testb 表中的每一行数据，并且与 join buffer 里面的数据济宁对比，符合条件的，就作为结果集返回。


具体的流程图如下所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhfm6cpegI3vu7lg3P8CvcN9p0Wc23uxjtk0GCcKnzmgEu7P4ufPCOenN029uqf4neFlwbiatFMpJQ/640?wx_fmt=png)

所以，从上面的执行的步骤来看（假设驱动表的行数为 N，被驱动表的行数据为 M），Block Nested-Loop Join 的扫描的行数还是**驱动表 + 被驱动表行数（N+M）**，在内存中总的比较次数还是**驱动表 * 被驱动表行数（N*M）**

上面我们提到 join buffer 是一块内存区域，并且有自己的大小，要是 join buffer 的大小不足够容纳驱动表的数量级怎么办呢？

答案就是**分段**，你要是 join buffer 没办法容纳驱动表的所有数据，那么就不把所有的数据加载到 join buffer 里面，先加载一部分，后面再加载另一部分，比如：先加载 testa 中的 80 条数据，与 testb 比较完数据后，清空再加载 testa 后 20 条数据，再与 testb 进行比较。具体执行流程如下：

1.  先加载 testa 中的 80 条数据到 join buffer

2.  然后一次遍历 testb 的所有数据，与 join buffer 里面的数据进行比较，符合条件的组成结果集。

3.  清空 join buffer，再加载 testa 后面的 20 条数据。

4.  然后一次遍历 testb 的所有数据，与 join buffer 里面的数据进行比较，符合条件的组成结果集并返回。


执行流程图如下所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhfm6cpegI3vu7lg3P8CvcNLzicKonAK7OpqAan22d45D2toLOTNQfRFervlvwuIicsz1T7OFIVE9oQ/640?wx_fmt=png)

从上面的结果来看相对于比内存足够的 join buffer 来说，分段的 join buffer 多了一遍全表全表遍历 testb，并且分的段数越多，多扫描驱动表的次数就越多。，性能就越差，所以在某一些场景下，适当的增大 join buffer 的值，是能够提高 join 的效率。

假如驱动表的行数是 N，分段参数为 K，被驱动表的行数是 M，那么总的扫描行数还是 **N+K*M**，而内存比较的次数还是 **N*M**，没有变。

其中 K 段数与 N 的数据量有关，若是 N 的数据量越大，那么可能 K 被分成段数就越多，这样多次重复扫描的被驱动表的次数就越多。

所以在 join buffer 不够的情况小，驱动表是越小越好，能够减少 K 值，减少重复扫描被驱动表的次数。这也就是为什么提倡小表要作为驱动表的原因。

那么这里提到小表的概念，是不是就是数据量少的就是认为是小表呢？其实不然，小表的真正的还是是实际参与 join 的数据量，比如以下的两条 sql：

```
select * from testa ta left join testb tb on (ta.col1=tb.col2) where tb.id<=20;
select * from testb tb left join testa ta on (ta.col1=tb.col2) where tb.id<=20;
```

在第二条 sql 中，虽然 testb 驱动表数据量比较大，但是在 where 条件中实际参与 join 的行数也就是 id 小于等于 20 的数据，完全小于 testa 的数据量，所以这里选择以 testb 作为驱动表是更加的合适。

在实际的开发中 Block Nested-Loop Join 也是严禁被禁止出现的，严格要求关联条件建索引，所以性能最好的就是 Index Nested-Loop Join 算法。

Index Nested-Loop Join
----------------------

当我们执行如下 sql 时：

```
select * from testa ta left join testb tb on (ta.col1=tb.col1);
```

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhfm6cpegI3vu7lg3P8CvcN4PJXGgVcaiccmwfMoREU9nkHbnUWEoUVzI7LgosH6CYUxfAeUl2J4RA/640?wx_fmt=png)

它的执行流程如下：

1.  首先取 testa 表的一行数据。

2.  使用上面的行数据的 col1 字段去 testb 表进行查询。

3.  在 testb 找到符合条件的数据行，并与 testa 的数据行组合作为结果集。

4.  重复执行 1-3 步骤，直到取完 testa 表的所有数据。


因为 testb 的 col1 字段是建立了索引，所以，当使用 testa 表的字段 col1 去 testb 查找的时候，testb 走的是 col1 索引的 b + 树的搜索，时间复杂度近似 log2M，并且因为是 select*，也就是要查找 testb 的所有字段，所以这里也涉及到回表查询，因此就变成了 2*log2M，若是不懂回表的，可以参考这一篇文章：[十万个为什么，精通 Mysql 索引](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247489108&idx=1&sn=81eca109468ede7caab7467c3c1e2018&scene=21#wechat_redirect)

在这个过程中，testa 表的扫描行数是全部，所以需要扫描 100 行，然后 testa 的每一行都与 testb 也是一一对应的，所以 col1 索引查询扫描的行数也是 100 行，所以总的扫描行数就是 200 行。

我们假设驱动表的数据行位 N，被驱动表的数据行为 M，那么近似的复杂度为：**N+N*2*log M**，因为驱动表的扫描行数就是 N，然后被驱动表因为每一次都对应驱动表的一次，并且一次的时间复杂度就是近似 2*log M，所以被驱动表就是 N*2*log M。

明显 N 的值对于 **N+N*2*log M** 的结果值影响更大，所以 N 越小越好，所以选择小表作为驱动表是最优选择。

**在一些情况下的优化，假如 join 的驱动表所需要的字段很少（两个），可以建立联合索引来优化 join 查询，并且如果业务允许的话，可以通过冗余字段，减少 join 的个数提高查询的效率**。

好了，这一期就分享 join 的原理，以及 join 一些优化的手段和注意的事项，我们下一期见。

参考
--

《Mysql 45 讲》

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