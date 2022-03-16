> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/B_slzAZLp-y8G0haZwIbTg)

大家好，我是狼王，一个爱打球的程序员

> 咱们使用 MySQL 大概率上都会遇到死锁问题，这实在是个令人非常头痛的问题。本文将会对死锁进行相应介绍，对常见的死锁案例进行相关分析与探讨，以及如何去尽可能避免死锁给出一些建议。

`话不多说，开整！`

### 什么是死锁

死锁是并发系统中常见的问题，同样也会出现在数据库 MySQL 的并发读写请求场景中。当两个及以上的事务，双方都在等待对方释放已经持有的锁或因为加锁顺序不一致造成循环等待锁资源，就会出现 “死锁”。常见的报错信息为 `Deadlock found when trying to get lock...`。

举例来说 A 事务持有 X1 锁 ，申请 X2 锁，B 事务持有 X2 锁，申请 X1 锁。A 和 B 事务持有锁并且申请对方持有的锁进入循环等待，就造成了死锁。

![](https://mmbiz.qpic.cn/mmbiz_png/7VkkuTzAZPp08uy3ZghjeKeEiae1ooyibicvj4hqx7uiaQTbEMKSGNDVx0pWFwljeSNO6gFvGRvRGMvcpGDTd0WiaOw/640?wx_fmt=png)

如上图，是右侧的四辆汽车资源请求产生了回路现象，即死循环，导致了死锁。

从死锁的定义来看，MySQL 出现死锁的几个要素为：

1.  两个或者两个以上事务

2.  每个事务都已经持有锁并且申请新的锁

3.  锁资源同时只能被同一个事务持有或者不兼容

4.  事务之间因为持有锁和申请锁导致彼此循环等待


### InnoDB 锁类型

为了分析死锁，我们有必要对 InnoDB 的锁类型有一个了解。![](https://mmbiz.qpic.cn/mmbiz_png/7VkkuTzAZPp08uy3ZghjeKeEiae1ooyibicysIEia6kunTYUf3KQ6bxP2QCPByG7ctyoXl7hhAzicIJcqjxE6iaXiaxiaQ/640?wx_fmt=png)

MySQL InnoDB 引擎实现了标准的`行级别锁：共享锁( S lock ) 和排他锁 ( X lock )`

> 1.  不同事务可以同时对同一行记录加 S 锁。
>
> 2.  如果一个事务对某一行记录加 X 锁，其他事务就不能加 S 锁或者 X 锁，从而导致锁等待。
>

如果事务 T1 持有行 r 的 S 锁，那么另一个事务 T2 请求 r 的锁时，会做如下处理:

> 1.  T2 请求 S 锁立即被允许，结果 T1 T2 都持有 r 行的 S 锁
>
> 2.  T2 请求 X 锁不能被立即允许
>

如果 T1 持有 r 的 X 锁，那么 T2 请求 r 的 X、S 锁都不能被立即允许，T2 必须等待 T1 释放 X 锁才可以，因为 X 锁与任何的锁都不兼容。共享锁和排他锁的兼容性如下所示：![](https://mmbiz.qpic.cn/mmbiz_png/7VkkuTzAZPp08uy3ZghjeKeEiae1ooyibicv7A6f0pVWr7gUxckbJVnoliapMCZkudn6s5eHI5t8tY1t5Z2OYH8jEQ/640?wx_fmt=png)

#### 间隙锁 (gap lock)

间隙锁锁住一个间隙以防止插入。假设索引列有 2, 4, 8 三个值，如果对 4 加锁，那么也会同时对 (2,4) 和(4,8)这两个间隙加锁。其他事务无法插入索引值在这两个间隙之间的记录。但是，间隙锁有个例外:

> 1.  如果索引列是唯一索引，那么只会锁住这条记录 (只加行锁)，而不会锁住间隙。
>
> 2.  对于联合索引且是唯一索引，如果 where 条件只包括联合索引的一部分，那么依然会加间隙锁。
>

#### next-key lock

next-key lock 实际上就是 行锁 + 这条记录前面的 gap lock 的组合。假设有索引值 10,11,13 和 20, 那么可能的 next-key lock 包括:

> (负无穷, 10],(10,11],(11,13],(13,20],(20, 正无穷)

在 RR 隔离级别下，InnoDB 使用 next-key lock 主要是防止`幻读`问题产生。

#### 意向锁 (Intention lock)

InnoDB 为了支持多粒度的加锁，允许行锁和表锁同时存在。为了支持在不同粒度上的加锁操作，InnoDB 支持了额外的一种锁方式，称之为意向锁 (Intention Lock)。意向锁是将锁定的对象分为多个层次，意向锁意味着事务希望在更细粒度上进行加锁。意向锁分为两种:

> 1.  意向共享锁 (IS)：事务有意向对表中的某些行加共享锁
>
> 2.  意向排他锁 (IX)：事务有意向对表中的某些行加排他锁
>

由于 InnoDB 存储引擎支持的是行级别的锁，因此意向锁其实不会阻塞除全表扫描以外的任何请求。表级意向锁与行级锁的兼容性如下所示:![](https://mmbiz.qpic.cn/mmbiz_png/7VkkuTzAZPp08uy3ZghjeKeEiae1ooyibicrCcP1FwRFjey2TCeicMuZp2qLF90bibGD9el0lhIW6yPhjicMibibDlKk7A/640?wx_fmt=png)

#### 插入意向锁 (Insert Intention lock)

插入意向锁是在插入一行记录操作之前设置的一种间隙锁，这个锁释放了一种插入方式的信号，即多个事务在相同的索引间隙插入时如果不是插入间隙中相同的位置就不需要互相等待。假设某列有索引值 2，6，只要两个事务插入位置不同 (如事务 A 插入 3，事务 B 插入 4)，那么就可以同时插入。

#### 锁模式兼容矩阵

横向是已持有锁，纵向是正在请求的锁：![](https://mmbiz.qpic.cn/mmbiz_png/7VkkuTzAZPp08uy3ZghjeKeEiae1ooyibicpCcfaPq9s5d7uF4srTfQq1eWaka2RWVXDEblK2vicObfdCiagvaOicgKA/640?wx_fmt=png)

### 阅读死锁日志

在进行具体案例分析之前，咱们先了解下如何去读懂死锁日志，尽可能地使用死锁日志里面的信息来帮助我们来解决死锁问题。

后面测试用例的数据库场景如下:`MySQL 5.7 事务隔离级别为 RR`

表结构和数据如下:![](https://mmbiz.qpic.cn/mmbiz_png/7VkkuTzAZPp08uy3ZghjeKeEiae1ooyibicrYtObLuGncGtTOMW6G1TF0twtRc0PdtImtwomLSfKtAuI96icQX2cHA/640?wx_fmt=png)

测试用例如下:![](https://mmbiz.qpic.cn/mmbiz_png/7VkkuTzAZPp08uy3ZghjeKeEiae1ooyibicER5sibmYUK4blLnmyWCLFEvJgtaVOPMtTs4tjOXLRz5qOfBOoY6xlhg/640?wx_fmt=png)

通过执行 show engine innodb status 可以查看到最近一次死锁的日志。

#### 日志分析如下:

1.  ***** (1) TRANSACTION: TRANSACTION 2322, ACTIVE 6 sec starting index read


事务号为 2322，活跃 6 秒，starting index read 表示事务状态为根据索引读取数据。常见的其他状态有:![](https://mmbiz.qpic.cn/mmbiz_png/7VkkuTzAZPp08uy3ZghjeKeEiae1ooyibicibZ6TX3se2a5KWa78YzAICxKeocwdFyAkGEGRY5gIbCsk4pHxTqrRicQ/640?wx_fmt=png)

`mysql tables in use 1` 说明当前的事务使用一个表。

`locked 1` 表示表上有一个表锁，对于 DML 语句为 LOCK_IX

`LOCK WAIT 2 lock struct(s), heap size 1136, 1 row lock(s)`

`LOCK WAIT` 表示正在等待锁，`2 lock struct(s)` 表示 trx->trx_locks 锁链表的长度为 2，每个链表节点代表该事务持有的一个锁结构，包括表锁，记录锁以及自增锁等。本用例中 2locks 表示 IX 锁和 lock_mode X (Next-key lock)

`1 row lock(s)` 表示当前事务持有的行记录锁 / gap 锁的个数。

`MySQL thread id 37, OS thread handle 140445500716800, query id 1234 127.0.0.1 root updating`

`MySQL thread id 37` 表示执行该事务的线程 ID 为 37 (即 show processlist; 展示的 ID)

`delete from student where stuno=5` 表示事务 1 正在执行的 sql，比较难受的事情是 `show engine innodb status` 是查看不到完整的 sql 的，通常显示当前正在等待锁的 sql。

***** (1) WAITING FOR THIS LOCK TO BE GRANTED:

RECORD LOCKS space id 11 page no 5 n bits 72 index idx_stuno of table cw****.****student trx id 2322 lock_mode X waiting

RECORD LOCKS 表示记录锁， 此条内容表示事务 1 正在等待表 student 上的 idx_stuno 的 X 锁，本案例中其实是 Next-Key Lock 。

事务 2 的 log 和上面分析类似:

2.  ***** (2) HOLDS THE LOCK(S):


`RECORD LOCKS space id 11 page no 5 n bits 72 index idx_stuno of table cw****.****student trx id 2321 lock_mode X`

显示事务 2 的 insert into student(stuno,score) values(2,10) 持有了 a=5 的 Lock mode X

| LOCK_gap，不过我们从日志里面看不到事务 2 执行的 delete from student where stuno=5;

这点也是造成 DBA 仅仅根据日志难以分析死锁的问题的根本原因。

2.  ***** (2) WAITING FOR THIS LOCK TO BE GRANTED:


`RECORD LOCKS space id 11 page no 5 n bits 72 index idx_stuno of table cw****.****student trx id 2321 lock_mode X locks gap before rec insert intention waiting`

表示事务 2 的 insert 语句正在等待插入意向锁 lock_mode X locks gap before rec insert intention waiting (LOCK_X + LOCK_REC_gap)

### 经典案例分析

#### 案例一: 事务并发 insert 唯一键冲突

表结构和数据如下所示:![](https://mmbiz.qpic.cn/mmbiz_png/7VkkuTzAZPp08uy3ZghjeKeEiae1ooyibicw8EVmJ8BG8Khy2jYibfM5MwZMYpE5OXFlsQ9JD0rJveYJLTB8hT9Taw/640?wx_fmt=png)![](https://mmbiz.qpic.cn/mmbiz_png/7VkkuTzAZPp08uy3ZghjeKeEiae1ooyibicKAW3OPoFVWYoqnlzY7Kkwx6Tuib3P5jh88V1IZWMJ3nwjoU3kZx1bCA/640?wx_fmt=png) 测试用例如下:![](https://mmbiz.qpic.cn/mmbiz_png/7VkkuTzAZPp08uy3ZghjeKeEiae1ooyibich9nJAgXMicgAwhLiactz1dhbM8knHhptCQdAqNFia0LK19c6mGGpw7ic4w/640?wx_fmt=png) 日志分析如下:

1.  事务 T2 insert into t7(id,a) values (26,10) 语句 insert 成功，持有 a=10 的 `排他行锁( Xlocks rec but no gap )`

2.  事务 T1 insert into t7(id,a) values (30,10), 因为 T2 的第一条 insert 已经插入 a=10 的记录, 事务 T1 insert a=10 则发生唯一键冲突, 需要申请对冲突的唯一索引加上 S Next-key Lock( 即 lock mode S waiting ) 这是一个`间隙锁`会申请锁住 (,10],(10,20] 之间的 gap 区域。

3.  事务 T2 insert into t7(id,a) values (40，9) 该语句插入的 a=9 的值在事务 T1 申请的 `gap 锁4-10之间`， 故需事务 T2 的第二条 insert 语句要等待事务 T1 的 `S-Next-key Lock 锁`释放, 在日志中显示 lock_mode X locks gap before rec insert intention waiting 。


#### 案例一: 先 update 再 insert 的并发死锁问题

表结构如下，无数据:![](https://mmbiz.qpic.cn/mmbiz_png/7VkkuTzAZPp08uy3ZghjeKeEiae1ooyibicmQsgsOgYeUx4AC9hlfcXzRjFITIQ07yd3c1mbnO6f8pPHUSaDKFrKQ/640?wx_fmt=png) 测试用例如下:![](https://mmbiz.qpic.cn/mmbiz_png/7VkkuTzAZPp08uy3ZghjeKeEiae1ooyibicVWZujhIYpkiaFzorMic6jWsVYm2lENaIwAvQKGEIicGibELibRpuTRRcJoA/640?wx_fmt=png) 死锁分析:  
可以看到两个事务 update 不存在的记录，先后获得`间隙锁( gap 锁)`，gap 锁之间是兼容的所以在 update 环节不会阻塞。两者都持有 gap 锁，然后去竞争插入`意向锁`。当存在其他会话持有 gap 锁的时候，当前会话申请不了插入意向锁，导致死锁。

### 如何尽可能避免死锁

1.  合理的设计索引，区分度高的列放到组合索引前面，使业务 SQL 尽可能通过索引`定位更少的行，减少锁竞争`。

2.  调整业务逻辑 SQL 执行顺序， 避免 update/delete 长时间持有锁的 SQL 在事务前面。

3.  避免`大事务`，尽量将大事务拆成多个小事务来处理，小事务发生锁冲突的几率也更小。

4.  以`固定的顺序`访问表和行。比如两个更新数据的事务，事务 A 更新数据的顺序为 1，2; 事务 B 更新数据的顺序为 2，1。这样更可能会造成死锁。

5.  在并发比较高的系统中，不要显式加锁，特别是是在事务里显式加锁。如 select … for update 语句，如果是在事务里`（运行了 start transaction 或设置了autocommit 等于0）`, 那么就会锁定所查找到的记录。

6.  尽量按`主键/索引`去查找记录，范围查找增加了锁冲突的可能性，也不要利用数据库做一些额外额度计算工作。比如有的程序会用到 “select … where … order by rand();” 这样的语句，由于类似这样的语句用不到索引，因此将导致整个表的数据都被锁住。

7.  优化 SQL 和表设计，减少同时占用太多资源的情况。比如说，`减少连接的表`，将复杂 SQL `分解`为多个简单的 SQL。


* * *

好了。今天就说到这了，我还会不断分享自己的所学所想，希望我们一起走在成功的道路上！

> 乐于输出干货的 Java 技术公众号：**狼王编程**。公众号内有大量的技术文章、海量视频资源、精美脑图，不妨来关注一下！回复**资料**领取大量学习资源和免费书籍！

公众号

转发朋友圈是对我最大的支持！

![](https://mmbiz.qpic.cn/mmbiz_png/Pn4Sm0RsAujO0pvtNCLzZCiaWxGBfq2xaPwze1NRLTSQZYbzWNnTJwDwsReHiam91Wojzvw3RLibjicWkLWJjicgsvw/640?wx_fmt=png)

觉得有点东西就点一下 “赞和在看” 吧！感谢大家的支持了！