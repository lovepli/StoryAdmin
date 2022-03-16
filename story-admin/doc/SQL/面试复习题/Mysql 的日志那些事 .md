> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247489139&idx=1&sn=310c85d09efa0771285d415725e6877f&scene=21#wechat_redirect)

hello 大家好，我是黎杜，上一篇给大家带来了 [Mysql 的 join 原理篇](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247489126&idx=1&sn=0be55deb921c49bd3b592dd155407b9d&scene=21#wechat_redirect)这一篇来聊一聊 Mysql 的日志篇。

Mysql 中日志还是挺多的，主要包含以下几个常用的日志：

1.  **binlog**：归档日志， Server 层的日志。

2.  **redo log**：重做日志，InnoDB 存储引擎层的日志。

3.  **undo log**：回滚日志，提供回滚操作，InnoDB 存储引擎层的日志。

4.  **relay log**：中继日志，主从复制原理日志。

5.  **slow_query_log**：慢查询日志，用于慢 sql。


下面我们就来聊一聊这几个日志的原理和作用。

binlog 和 redo log
-----------------

redo log 日志也叫做 WAL 技术（Write- Ahead Logging），他是一种先写日志，并更新内存，最后再更新磁盘的技术，为了就是减少 sql 执行期间的数据库 io 操作，并且更新磁盘往往是在 Mysql 比较闲的时候，这样就大大减轻了 Mysql 的压力。

redo log 是固定大小，是物理日志，属于 InnoDB 引擎的，并且写 redo log 是环状写日志的形式：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljxbzlsHykBluA1aQnxopm8Q3zmdA0icw6BiaWs8iaYYFkNDyvdftBflK7Fmrypd3Ip291weD0qL3XbQ/640?wx_fmt=png)

如上图所示：若是四组的 redo log 文件，一组为 1G 的大小，那么四组就是 4G 的大小，其中 write pos 是记录当前的位置，有数据写入当前位置，那么 write pos 就会边写入边往后移。

check point 记录擦除的位置，因为 redo log 是固定大小，所以当 redo log 满的时候，也就是 write pos 追上 check point 的时候，需要清除 redo log 的部分数据，清除的数据会被持久化到磁盘中，然后将 check point 向前移动。

redo log 日志实现了即使在数据库出现异常宕机的时候，重启后之前的记录也不会丢失，这就是 crash-safe 能力。

binlog 称为归档日志，是逻辑上的日志，它属于 Mysql 的 Server 层面的日志，记录着 sql 的原始逻辑，主要有两种模式：一个是 statement 格式记录的是原始的 sql，而 row 格式则是记录行内容。

redo log 和 binlog 记录的形式、内容不同，这两者日志都能通过自己记录的内容恢复数据。

之所以这两个日志同时存在，是因为刚开始 Mysql 自带的引擎 MyISAM 就没有 crash-safe 功能的，并且在此之前 Mysql 还没有 InnoDB 引擎，Mysql 自带的 binlog 日志只是用来归档日志的，所以 InnoDB 引擎也就通过自己 redo log 日志来实现 crash-safe 功能。

undo log
--------

undo log 是回滚日志，是实现 MVCC 的一部分，要详细了解 undo log，我们先来详细了解 MVCC 的原理。

MVCC 叫做多版本控制，实现 MVCC 时用到了一致性视图，用于支持读提交和可重复读的实现。

对于一行数据若是想实现可重复读取或者能够读取数据的另一个事务未提交前的原始值，那么必须对原始数据进行保存或者对更新操作进行保存，这样才能够查询到原始值。

在 Mysql 的 MVCC 中规定每一行数据都有多个不同的版本，一个事务更新操作完后就生成一个新的版本，并不是对全部数据的全量备份，因为全量备份的代价太大了：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljxbzlsHykBluA1aQnxopm8CURC2bLgrWp3d7iaTScVMWYfjua6cCu0Y3FIZBbksAGWl3Uu7osHoag/640?wx_fmt=png)

如图中所示，假如三个事务更新了同一行数据，那么就会有对应的 v1、v2、v3 三个数据版本，每一个事务在开始的时候都获得一个唯一的事务 id（transaction id），并且是顺序递增的，并且这个事务 id 最后会赋值给 row trx_id，这样就形成了一个唯一的一行数据版本。

实际上版本 1、版本 2 并非实际物理存在的，而图中的 U1 和 U2 实际就是 undo log 日志（回滚日志），这 v1 和 v2 版本是根据当前 v3 和 undo log 计算出来的。

InnoDB 引擎就是利用每行数据有多个版本的特性，实现了秒级创建 “快照”，并不需要花费大量的是时间。

relay log
---------

relay log 中继日志用于实现主从复制，我们先来了解一下主从复制原理。

在实际的生产中，为了解决 Mysql 的单点故障已经提高 MySQL 的整体服务性能，一般都会采用「主从复制」。

比如：在复杂的业务系统中，有一句 sql 执行后导致锁表，并且这条 sql 的的执行时间有比较长，那么此 sql 执行的期间导致服务不可用，这样就会严重影响用户的体验度。

主从复制中分为「主服务器（master）「和」从服务器（slave）」，「主服务器负责写，而从服务器负责读」，Mysql 的主从复制的过程是一个「异步的过程」。

这样读写分离的过程能够是整体的服务性能提高，即使写操作时间比较长，也不影响读操作的进行。

首先放一张 Mysql 主从复制的原理图，总的来说 Mysql 的主从复制原理还是比较好理解的，原理非常的简单。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljxbzlsHykBluA1aQnxopm8vE42ZzI67k5YK8dvVFQtbHEaiadRiaRGv0DNHjOKaiaILWzib2FbMfKVjw/640?wx_fmt=png)

Mysql 的主从复制中主要有三个线程：master（binlog dump thread）、slave（I/O thread 、SQL thread），Master 一条线程和 Slave 中的两条线程。

master（binlog dump thread）主要负责 Master 库中有数据更新的时候，会按照 binlog 格式，将更新的事件类型写入到主库的 binlog 文件中。

并且，Master 会创建 log dump 线程通知 Slave 主库中存在数据更新，这就是为什么主库的 binlog 日志一定要开启的原因。

I/O thread 线程在 Slave 中创建，该线程用于请求 Master，Master 会返回 binlog 的名称以及当前数据更新的位置、binlog 文件位置的副本。

然后，将 binlog 保存在 「relay log（中继日志）」 中，中继日志也是记录数据更新的信息。

SQL 线程也是在 Slave 中创建的，当 Slave 检测到中继日志有更新，就会将更新的内容同步到 Slave 数据库中，这样就保证了主从的数据的同步。

以上就是主从复制的过程，当然，主从复制的过程有不同的策略方式进行数据的同步，主要包含以下几种：

1.  「同步策略」：Master 会等待所有的 Slave 都回应后才会提交，这个主从的同步的性能会严重的影响。

2.  「半同步策略」：Master 至少会等待一个 Slave 回应后提交。

3.  「异步策略」：Master 不用等待 Slave 回应就可以提交。

4.  「延迟策略」：Slave 要落后于 Master 指定的时间。


对于不同的业务需求，有不同的策略方案，但是一般都会采用最终一致性，不会要求强一致性，毕竟强一致性会严重影响性能。

slow_query_log
--------------

在 mysql 中有一个动态参数 long_query_time 表示慢查询的阈值，表示当执行的 sql 超过这个这个参数的时间，就数据慢查询的范围就会将此纪录存入该日志中，该值默认为 10，通以下命令进行查看。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljxbzlsHykBluA1aQnxopm8xjT5k8ECGe9FYVTkXm6nTrP5bqb4PKW4XXicH0iaFJHiaOOJeZIPmIz9g/640?wx_fmt=png)

在实际的开发中，一般不会有超过 10 秒的的 sql，所以我们将该值可以设置小一点，设置为 1 秒，通过命令 SET long_query_time=1 将该值设置为 1 秒，但是默认慢查询的日志记录功能是关闭的。如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljxbzlsHykBluA1aQnxopm8h4mGxbZmmrHh0uR5oHMnlOsSYXicSZAC2js74Y1S3ftibjyjZgBqQ8iag/640?wx_fmt=png)

通过命令 SET GLOBAL slow_query_log=ON 将该功能开启，最后做一个测试执行 sql：select sleep(10)；然后来到 mysql 的 datadir 目录下就会有该慢查询文件：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljxbzlsHykBluA1aQnxopm8sXib659TDEGOGA9uu5ZanDt1OvkNyYWhXFap4ib9oe9FfLVz080A7zcQ/640?wx_fmt=png)

执行 cat + 改文件名，就可以看到该 sql 执行的时间，以及执行慢的 sql 语句：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljxbzlsHykBluA1aQnxopm8DsDf7CBllWWxl9A69Q9YWPX7ia7PFXnMY2DICehh0eZVfyRLiaibtZMGg/640?wx_fmt=png)另外通过设置 log_queries_not_using_indexes 参数，也可以将没有使用索引的 sql 记录收到该文件中。

好了，这一期，我们就到这里，我们下一期再见。

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