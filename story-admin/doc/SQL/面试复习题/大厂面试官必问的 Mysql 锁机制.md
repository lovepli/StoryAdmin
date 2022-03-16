> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484473&idx=1&sn=e1093c8ebc4e6b105dbd41e1065ad0e8&scene=21#wechat_redirect)

![](https://mmbiz.qpic.cn/mmbiz_jpg/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsSQoGRAhBZnDuxXE7QdmZsUNxae1EQhwZDsiakiaDrhF2kV3FwvHIicCicg/640?wx_fmt=jpeg)

前言
--

前几天有粉丝和我聊到他找工作面试大厂时被问的问题，因为现在疫情期间，找工作也特别难找。他说面试的题目也比较难，都偏向于一两年的工作经验的面试题。

他说在一面的时候被问到 Mysql 的面试题，索引那块自己都回答比较满意，但是问到 Mysql 的锁机制就比较懵了。

因为平时没有关注 Mysql 的锁机制，当被问到高并发场景下锁机制是怎么保证数据的一致性的和事务隔离性的。

他把他面试的过程分享给了我，Mysql 高并发锁机制的问题，几乎面大厂都有被问到，Mysql 怎么在高并发下控制并发访问的？

我细想了一下，Mysql 的锁机制确实非常重要，所以在这里做一个全面的总结整理，便于以后的查阅，也分享给各位读者大大们。

Mysql 的锁机制还是有点难理解的，所以这篇文章采用图文结合的方式讲解难点，帮助大家理解，讲解的主要内容如下图的脑图所示，基本涵盖了 Mysql 锁机制的所有知识点。

本文脑图
----

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHszFxjRMhJ5HjSGBU90E5SvDzBQ2ebFJmYZj7NuAuqwncehz0DGgqibow/640?wx_fmt=png)

锁种类
---

Mysql 中锁的分类按照不同类型的划分可以分成不同的锁，按照**「锁的粒度」**划分可以分成：**「表锁、页锁、行锁」**；按照**「使用的方式」**划分可以分为：**「共享锁」**和**「排它锁」**；按照思想的划分：**「乐观锁」**和**「悲观锁」**。

下面我们对着这几种划分的锁进行详细的解说和介绍，在了解设计者设计锁的概念的同时，也能深入的理解设计者的设计思想。

**「表锁」**是粒度最大的锁，开销小，加锁快，不会出现死锁，但是由于粒度太大，因此造成锁的冲突几率大，并发性能低。

Mysql 的**「MyISAM 储存引擎就支持表锁」**，MyISAM 的表锁模式有两种：**「表共享读锁」**和**「表独占写锁」**。

当一个线程获取到 MyISAM 表的读锁的时候，会阻塞其他用户对该表的写操作，但是不会阻塞其它用户对该用户的读操作。

相反的，当一个线程获取到 MyISAM 表的写锁的时候，就会阻塞其它用户的读写操作对其它的线程具有排它性。

**「页锁」**的粒度是介于行锁和表锁之间的一种锁，因为页锁是在 BDB 中支持的一种锁机制，也很少没人提及和使用，所以这里制作概述，不做详解。

**「行锁」**是粒度最小的锁机制，行锁的加锁开销性能大，加锁慢，并且会出现死锁，但是行锁的锁冲突的几率低，并发性能高。

行锁是 InnoDB 默认的支持的锁机制，MyISAM 不支持行锁，这个也是 InnoDB 和 MyISAM 的区别之一。

行锁在使用的方式上可以划分为：**「共享读锁（S 锁）**「和」**排它写锁（X 锁）」**。

当一个事务对 Mysql 中的一条数据行加上了 S 锁，当前事务不能修改该行数据只能执行读操作，其他事务只能对该行数据加 S 锁不能加 X 锁。

若是一个事务对一行数据加了 X 锁，该事务能够对该行数据执行读和写操作，其它事务不能对该行数据加任何的锁，既不能读也不能写。

**「悲观锁和乐观锁是在很多框架都存在的一种思想，不要狭义地认为它们是某一种框架的锁机制」**。

数据库管理系统中为了控制并发，保证在多个事务执行时的数据一致性以及事务的隔离性，使用悲观锁和乐观锁来解决并发场景下的问题。

Mysql 的**「悲观锁的实现是基于 Mysql 自身的锁机制实现，而乐观锁需要程序员自己去实现的锁机制」**，最常见的乐观锁实现就锁机制是**「使用版本号实现」**。

乐观锁设计思想的在`CAS`的运用也是比较经典，之前我写过一篇关于 CAS 的文章，大家感兴趣的可以参考这一篇 [[深入剖析 AQS 和 CAS，看了都说好](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484265&idx=1&sn=bf2ad737c983c77dc9acf126de8bcacf&chksm=fbf7eaabcc8063bd4e13c9cc1627415fd4bc048326bce33e452ec96d4b645ef5b5a2ec82b272&scene=21#wechat_redirect)]。

从上面的介绍中说了每一种锁的概念，但是很难说哪一种锁就是最好的，锁没有最好的，只有哪种业务场景最适合哪种锁，具体业务具体分析。

下面我们就具体基于 Mysql 的存储引擎详细的分析每一种锁在存储引擎中的运用和实现。

MyISAM
------

MyISAM 中默认支持的表级锁有两种：**「共享读锁」**和**「独占写锁」**。表级锁在 MyISAM 和 InnoDB 的存储引擎中都支持，但是 InnoDB 默认支持的是行锁。

Mysql 中平时读写操作都是隐式的进行加锁和解锁操作，Mysql 已经自动帮我们实现加锁和解锁操作了，若是想要测试锁机制，我们就要显示的自己控制锁机制。

Mysql 中可以通过以下 sql 来显示的在事务中显式的进行加锁和解锁操作：

```
// 显式的添加表级读锁
LOCK TABLE 表名 READ
// 显示的添加表级写锁
LOCK TABLE 表名 WRITE
// 显式的解锁（当一个事务commit的时候也会自动解锁）
unlock tables;
```

下面我们就来测试一下 MyISAM 中的表级锁机制，首先创建一个测试表`employee` ，这里要指定存储引擎为 MyISAM，并插入两条测试数据：

```
CREATE TABLE IF NOT EXISTS employee (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(40),
    money INT
)ENGINE MyISAM

INSERT INTO employee(name, money) VALUES('黎杜', 1000);
INSERT INTO employee(name, money) VALUES('非科班的科班', 2000);
```

查看一下，表结果如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsz89Ap6Ed33yzKdXCw4lB0iafbH8tDvqqfmXPJlj27iaNqbejMpxxTK1A/640?wx_fmt=png)

### MyISAM 表级写锁

（1）与此同时再开启一个 session 窗口，然后在第一个窗口执行下面的 sql，在 session1 中给表添加写锁：

```
LOCK TABLE employee WRITE
```

（2）可以在 session2 中进行查询或者插入、更新该表数据，可以发现都会处于等待状态，也就是 session1 锁住了整个表，导致 session2 只能等待：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsETPRLwXk9szZL1ZUE627YqggibeM1W9pZXUOuXyGib2kuNMD2lVbn0ug/640?wx_fmt=png)

（3）在 session1 中进行查询、插入、更新数据，都可以执行成功：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsQuuJw7LETdu3TyjiaYEW4oaRj1m22GibKuxtTibISCJfao5kHkd7Yoo4Q/640?wx_fmt=png)

**「总结：」** 从上面的测试结果显示**「当一个线程获取到表级写锁后，只能由该线程对表进行读写操作，别的线程必须等待该线程释放锁以后才能操作」**。

### MyISAM 表级共享读锁

（1）接下来测试一下表级共享读锁，同样还是利用上面的测试数据，第一步还是在 session1 给表加读锁。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsm2Gib1G66iaEQ9cMkolfGKm9juDvX5RedeBfSdTHJamsCzmPRLApnopg/640?wx_fmt=png)

（2）然后在 session1 中尝试进行插入、更新数据，发现都会报错，只能查询数据。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsarAErN4TmxCMPHUwTayYMs2wQQ9r2PkxriaTXZicMyNsnQZFFicutD2tQ/640?wx_fmt=png)

（3）最后在 session2 中尝试进行插入、更新数据，程序都会进入等待状态，只能查询数据，直到 session1 解锁表 session2 才能插入、更新数据。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHs8jxrtJXJL3QmFkCIB6G81Sk12ncZwafQpBVRwj67o9WkmwGgBSEARw/640?wx_fmt=png)

**「总结：」** 从上面的测试结果显示**「当一个线程获取到表级读锁后，该线程只能读取数据不能修改数据，其它线程也只能加读锁，不能加写锁」**。

### MyISAM 表级锁竞争情况

MyISAM 存储引擎中，可以通过查询变量来查看并发场景锁的争夺情况，具体执行下面的 sql 语句：

```
show status like 'table%';
```

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsYLI9tciaGUZJQib2gj5HmFiaiaqt1F08U9Wx6t5Yzyp32gtTkSbuPmH8NA/640?wx_fmt=png)

主要是查看`table_locks_waited`和`table_locks_immediate`的值的大小分析锁的竞争情况。

`Table_locks_immediate`：表示能够立即获得表级锁的锁请求次数；`Table_locks_waited`表示不能立即获取表级锁而需要等待的锁请求次数分析，**「值越大竞争就越严重」**。

### 并发插入

通过上面的操作演示，详细的说明了表级共享锁和表级写锁的特点。但是在平时的执行 sql 的时候，这些**「解锁和释放锁都是 Mysql 底层隐式的执行的」**。

上面的演示只是为了证明显式的执行事务的过程共享锁和表级写锁的加锁和解锁的特点，实际并不会这么做的。

在我们平时执行 select 语句的时候就会隐式的加读锁，执行增、删、改的操作时就会隐式的执行加写锁。

MyISAM 存储引擎中，虽然读写操作是串行化的，但是它也支持并发插入，这个需要设置内部变量`concurrent_insert`的值。

它的值有三个值`0、1、2`。可以通过以下的 sql 查看`concurrent_insert`的默认值为**「AUTO(或者 1)」**。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsR0ghvqUvwkq3F7kLuSYZMe6JCRicoajiaxmqLLou2vyTIicaLmSXMF9Yg/640?wx_fmt=png)

concurrent_insert 的值为`NEVER (or 0)`表示不支持比并发插入；值为`AUTO(或者1）`表示在 MyISAM 表中没有被删除的行，运行另一个线程从表尾插入数据；值为`ALWAYS (or 2)`表示不管是否有删除的行，都允许在表尾插入数据。

### 锁调度

MyISAM 存储引擎中，**「假如同时一个读请求，一个写请求过来的话，它会优先处理写请求」**，因为 MyISAM 存储引擎中认为写请求比读请求重要。

这样就会导致，**「假如大量的读写请求过来，就会导致读请求长时间的等待，或者 "线程饿死"，因此 MyISAM 不适合运用于大量读写操作的场景」**，这样会导致长时间读取不到用户数据，用户体验感极差。

当然可以通过设置`low-priority-updates`参数，设置请求链接的优先级，使得 Mysql 优先处理读请求。

InnoDB
------

InnoDB 和 MyISAM 不同的是，InnoDB 支持**「行锁」**和**「事务」**，行级锁的概念前面以及说了，这里就不再赘述，事务的四大特性的概述以及实现的原理可以参考这一篇 []。

InnoDB 中除了有**「表锁」**和**「行级锁」**的概念，还有 Gap Lock（间隙锁）、Next-key Lock 锁，**「间隙锁主要用于范围查询的时候，锁住查询的范围，并且间隙锁也是解决幻读的方案」**。

InnoDB 中的行级锁是**「对索引加的锁，在不通过索引查询数据的时候，InnoDB 就会使用表锁」**。

**「但是通过索引查询的时候是否使用索引，还要看 Mysql 的执行计划」**，Mysql 的优化器会判断是一条 sql 执行的最佳策略。

若是 Mysql 觉得执行索引查询还不如全表扫描速度快，那么 Mysql 就会使用全表扫描来查询，这是即使 sql 语句中使用了索引，最后还是执行为全表扫描，加的是表锁。

若是对于 Mysql 的 sql 执行原理不熟悉的可以参考这一篇文章 []。最后是否执行了索引查询可以通过`explain`来查看，我相信这个大家都是耳熟能详的命令了。

### InnoDB 行锁和表锁

InnoDB 的行锁也是分为行级**「共享读锁（S 锁）**「和」**排它写锁（X 锁）」**，原理特点和 MyISAM 的表级锁两种模式是一样的。

若想显式的给表加行级读锁和写锁，可以执行下面的 sql 语句：

```
// 给查询sql显示添加读锁
select ... lock in share mode;
// 给查询sql显示添加写锁
select ... for update；
```

（1）下面我们直接进入锁机制的测试阶段，还是创建一个测试表，并插入两条数据：

```
// 先把原来的MyISAM表给删除了
DROP TABLE IF EXISTS employee;
CREATE TABLE IF NOT EXISTS employee (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(40),
    money INT
)ENGINE INNODB;
// 插入测试数据
INSERT INTO employee(name, money) VALUES('黎杜', 1000);
INSERT INTO employee(name, money) VALUES('非科班的科班', 2000);
```

（2）创建的表中可以看出对表中的字段只有 id 添加了主键索引，接着就是在 session1 窗口执行`begin`开启事务，并执行下面的 sql 语句：

```
// 使用非索引字段查询，并显式的添加写锁
select * from employee where name='黎杜' for update;
```

（3）然后在 session2 中执行 update 语句，上面查询的是 id=1 的数据行，下面 update 的是 id=2 的数据行，会发现程序也会进入等待状态：

```
update employee set name='ldc' where id =2;
```

可见若是**「使用非索引查询，直接就是使用的表级锁」**，锁住了整个表。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsTVxj9ibBicCIno0FngM4A7ePxp8W2Kf13TKEmm6bYaNyEiawQsaUZsfww/640?wx_fmt=png)

（4）若是 session1 使用的是 id 来查询，如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsl5GYf8Aq6SlGfsC1vklxGCXibictL2NfU9KK6qSY1Idvic8LCZFCwc5Fg/640?wx_fmt=png)

（5）那么 session2 是可以成功 update 其它数据行的，但是这里我建议使用数据量大的表进行测试，因为前面我说过了**「是否执行索引还得看 Mysql 的执行计划，对于一些小表的操作，可能就直接使用全表扫描」**。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsggHd9afjKpRAWPKsC11XHib9tySfnmpP3HDH555c3I3ATynUb0boQcA/640?wx_fmt=png)

（6）还有一种情况就是：假如我们给 name 字段也加上了普通索引，那么通过普通索引来查询数据，并且查询到多行数据，那它是锁这多行数据还是锁整个表呢？

下面我们来测试一下，首先给**「name 字段添加普通索引」**，如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsTiaFvqxzicOcaHoxLNHBee6fR3U74WnMZWicQWgqlQJbKnniaF7m5a31OA/640?wx_fmt=png)

（6）并插入一条新的数据 name 值与 id=2 的值相同，并显式的加锁，如下若是：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHs9gDppSka4CMEicHPa4lbcYJCKs0GzF3FjFqQq5kjPPjxCI2m8sQDuFQ/640?wx_fmt=png)

（7）当 update 其它数据行 name 值不是 ldc 的也会进入等待状态，并且通过 explain 来查看是否 name='ldc'有执行索引，可以看到 sql 语句是有执行索引条件的。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsEV9EzMsu5qYHgkkluQdCDZWAc4ibQ4B3fM6kJcYfwyCvbrohg78icPKg/640?wx_fmt=png)![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsucCpV2rwPicd0hnncKtxibz3ibsAdiawMoicQw7x2eWq14Efbgb3RIjsc8A/640?wx_fmt=png)

结论：从上面的测试锁机制的演示可以得出以下几个结论：

1.  执行非索引条件查询执行的是表锁。

2.  执行索引查询是否是加行锁，还得看 Mysql 的执行计划，可以通过 explain 关键字来查看。

3.  用普通键索引的查询，遇到索引值相同的，也会对其他的操作数据行的产生影响。


### InnoDB 间隙锁

当我们使用范围条件查询而不是等值条件查询的时候，InnoDB 就会给符合条件的范围索引加锁，在条件范围内并不存的记录就叫做 "间隙（GAP）"

大家大概都知道在事务的四大隔离级别中，不可重复读会产生幻读的现象，只能通过提高隔离级别到串行化来解决幻读现象。

但是 Mysql 中的不可重复是已经解决了幻读问题，它通过引入间隙锁的实现来解决幻读，通过给符合条件的间隙加锁，防止再次查询的时候出现新数据产生幻读的问题。

例如我们执行下面的 sql 语句，就会对 id 大于 100 的记录加锁，在 id>100 的记录中肯定是有不存在的间隙：

```
Select * from  employee where id> 100 for update;
```

（1）接着来测试间隙锁，新增一个字段 num，并将 num 添加为普通索引、修改之前的数据使得 num 之间的值存在间隙，操作如下 sql 所示：

```
alter table employee add num int not null default 0;
update employee set num = 1 where id = 1;
update employee set num = 1 where id = 2;
update employee set num = 3 where id = 3;
insert into employee values(4,'kris',4000,5);
```

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHs3Wj0Vs2iaseHeFI9b25nmRPmgBgDos0DKrmVCHKPmzzLngXfL4ZIp7Q/640?wx_fmt=png)

（2）接着在 session1 的窗口开启事务，并执行下面操作：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsGfXqLtDzLqRjWibpn4rjbDEh9sLQlAwPOWaIVgBscvBf2qgMldQmSgg/640?wx_fmt=png)

（3）同时打开窗口 session2，并执行新增语句：

```
insert into employee values(5,'ceshi',5000,2);  // 程序出现等待
insert into employee values(5,'ceshi',5000,4);  // 程序出现等待
insert into employee values(5,'ceshi',5000,6);  // 新增成功
insert into employee values(6,'ceshi',5000,0);  // 新增成功
```

**「从上面的测试结果显示在区间（1,3]U[3,5) 之间加了锁，是不能够新增数据行，这就是新增 num=2 和 num=4 失败的原因，但是在这个区间以外的数据行是没有加锁的，可以新增数据行」**。

根据索引的有序性，而普通索引是可以出现重复值，那么当我们第一个 sesson 查询的时候只出现一条数据 num=3，为了解决第二次查询的时候出现幻读，也就是出现两条或者更多 num=3 这样查询条件的数据。

Mysql 在满足 where 条件的情况下，给`（1,3]U[3,5)`区间加上了锁不允许插入 num=3 的数据行，这样就解决了幻读。

这里抛出几种情况接着来测试间隙锁。主键索引（唯一索引）是否会加上间隙锁呢？范围查询是否会加上间隙锁？使用不存在的检索条件是否会加上间隙锁？

先来说说：**「主键索引（唯一索引）是否会加上间隙锁呢？」**

因为主键索引具有唯一性，不允许出现重复，那么当进行等值查询的时候 id=3，只能有且只有一条数据，是不可能再出现 id=3 的第二条数据。

因此它只要锁定这条数据（锁定索引），在下次查询当前读的时候不会被删除、或者更新 id=3 的数据行，也就保证了数据的一致性，所以主键索引由于他的唯一性的原因，是不需要加间隙锁的。

再来说说第二个问题：**「范围查询是否会加上间隙锁？」**

直接在 session1 中执行下面的 sql 语句，并在 session2 中在这个 num>=3 的查询条件内和外新增数据：

```
select * from employee where num>=3 for update;
insert into employee values(6,'ceshi',5000,2);  // 程序出现等待
insert into employee values(7,'ceshi',5000,4);  // 程序出现等待
insert into employee values(8,'ceshi',5000,1);  // 新增数据成功
```

我们来分析以下原理：单查询 num>=3 的时候，在现有的 employee 表中满足条件的数据行，如下所示：

<table data-tool="mdnice编辑器" data-darkmode-bgcolor-16334235947309="rgba(112, 0, 0, 0.039999999999999994)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)" data-darkmode-color-16334235947309="rgb(163, 163, 163)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)"><thead data-darkmode-bgcolor-16334235947309="rgba(112, 0, 0, 0.039999999999999994)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)" data-darkmode-color-16334235947309="rgb(163, 163, 163)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)"><tr data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(163, 163, 163)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><th width="245" data-darkmode-bgcolor-16334235947309="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)|rgb(240, 240, 240)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); font-size: 14px; color: rgb(89, 89, 89); word-break: break-all;">id</th><th width="266" data-darkmode-bgcolor-16334235947309="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)|rgb(240, 240, 240)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); font-size: 14px; color: rgb(89, 89, 89); word-break: break-all;">num</th></tr></thead><tbody data-darkmode-bgcolor-16334235947309="rgba(112, 0, 0, 0.039999999999999994)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)" data-darkmode-color-16334235947309="rgb(163, 163, 163)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)"><tr data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(163, 163, 163)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td width="229" data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89);">3</td><td width="266" data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89);">3</td></tr><tr data-darkmode-bgcolor-16334235947309="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16334235947309="rgb(163, 163, 163)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td width="229" data-darkmode-bgcolor-16334235947309="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89);">4</td><td width="266" data-darkmode-bgcolor-16334235947309="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89);">5</td></tr><tr data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(163, 163, 163)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td width="229" data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89);">5</td><td width="266" data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89);">6</td></tr></tbody></table>

那么在设计者的角度出发，我为了解决幻读的现象：在 num>=3 的条件下是必须加上间隙锁的。

而在小于 num=3 中，下一条数据行就是 num=1 了，为了防止在（1，3] 的范围中加入了 num=3 的数据行，所以也给这个间隙加上了锁，这就是添加 num=2 数据行出现等待的原因。

最后来说一说：**「使用不存在的检索条件是否会加上间隙锁？」**

假如是查询 num>=8 的数据行呢？因为 employee 表并不存在中 num=8 的数据行，num 最大 num=6，所以为了解决幻读（6，8] 与 num>=8 也会加上锁。

说到这里我相信很多人已经对间隙锁有了清晰和深入的认识，可以说是精通了，又可以和面试官互扯了。

假如你是第一次接触 Mysql 的锁机制，第一次肯定是懵的，建议多认真的看几遍，跟着案例敲一下自己深刻的去体会，慢慢的就懂了。

死锁
--

死锁在 InnoDB 中才会出现死锁，MyISAM 是不会出现死锁，因为 MyISAM 支持的是表锁，一次性获取了所有的锁，其它的线程只能排队等候。

而 InnoDB 默认支持行锁，获取锁是分步的，并不是一次性获取所有的锁，因此在锁竞争的时候就会出现死锁的情况。

虽然 InnoDB 会出现死锁，但是并不影响 InnoDB 成为最受欢迎的存储引擎，MyISAM 可以理解为串行化操作，读写有序，因此支持的并发性能低下。

### 死锁案例一

举一个例子，现在数据库表 employee 中六条数据，如下所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsHlmpjUp2xZEBRsJDfwL0WK0rjbBApRt13VohaPqKO28nfaN6zKs7Ng/640?wx_fmt=png)

其中 name=ldc 的有两条数据，并且 name 字段为普通索引，分别是 id=2 和 id=3 的数据行，现在假设有两个事务分别执行下面的两条 sql 语句：

```
// session1执行
update employee set num = 2 where name ='ldc';
// session2执行
select * from employee where id = 2 or id =3;
```

其中 session1 执行的 sql 获取的数据行是两条数据，假设先获取到第一个 id=2 的数据行，然后 cpu 的时间分配给了另一个事务，另一个事务执行查询操作获取了第二行数据也就是 id=3 的数据行。

当事务 2 继续执行的时候获取到 id=3 的数据行，锁定了 id=3 的数据行，此时 cpu 又将时间分配给了第一个事务，第一个事务执行准备获取第二行数据的锁，发现已经被其他事务获取了，它就处于等待的状态。

当 cpu 把时间有分配给了第二个事务，第二个事务准备获取第一行数据的锁发现已经被第一个事务获取了锁，这样就行了死锁，两个事务彼此之间相互等待。

### 死锁案例二

第二种死锁情况就是当一个事务开始并且 update 一条 id=1 的数据行时，成功获取到写锁，此时另一个事务执行也 update 另一条 id=2 的数据行时，也成功获取到写锁（id 为主键）。

此时 cpu 将时间分配给了事务一，事务一接着也是 update id=2 的数据行，因为事务二已经获取到 id=2 数据行的锁，所以事务已处于等待状态。

事务二有获取到了时间，像执行 update id=1 的数据行，但是此时 id=1 的锁被事务一获取到了，事务二也处于等待的状态，因此形成了死锁。

<table data-tool="mdnice编辑器" data-darkmode-bgcolor-16334235947309="rgba(112, 0, 0, 0.039999999999999994)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)" data-darkmode-color-16334235947309="rgb(163, 163, 163)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)"><thead data-darkmode-bgcolor-16334235947309="rgba(112, 0, 0, 0.039999999999999994)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)" data-darkmode-color-16334235947309="rgb(163, 163, 163)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)"><tr data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(163, 163, 163)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><th data-darkmode-bgcolor-16334235947309="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)|rgb(240, 240, 240)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); font-size: 14px; color: rgb(89, 89, 89);">session1</th><th data-darkmode-bgcolor-16334235947309="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)|rgb(240, 240, 240)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); font-size: 14px; color: rgb(89, 89, 89);">session2</th></tr></thead><tbody data-darkmode-bgcolor-16334235947309="rgba(112, 0, 0, 0.039999999999999994)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)" data-darkmode-color-16334235947309="rgb(163, 163, 163)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)"><tr data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(163, 163, 163)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89);">begin;update t set name='测试' where id=1;</td><td data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89);">begin</td></tr><tr data-darkmode-bgcolor-16334235947309="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16334235947309="rgb(163, 163, 163)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-bgcolor-16334235947309="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89);"><br data-darkmode-bgcolor-16334235947309="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)"></td><td data-darkmode-bgcolor-16334235947309="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89);">update t set name='测试' where id=2;</td></tr><tr data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(163, 163, 163)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89);">update t set name='测试' where id=2;</td><td data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89);"><br data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)"></td></tr><tr data-darkmode-bgcolor-16334235947309="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16334235947309="rgb(163, 163, 163)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-bgcolor-16334235947309="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89);">等待.....</td><td data-darkmode-bgcolor-16334235947309="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89);">update t set name='测试' where id=1;</td></tr><tr data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(163, 163, 163)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89);">等待.....</td><td data-darkmode-bgcolor-16334235947309="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334235947309="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16334235947309="rgb(141, 141, 141)" data-darkmode-original-color-16334235947309="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89);">等待......</td></tr></tbody></table>

### 死锁的解决方案

首先要解决死锁问题，在程序的设计上，当发现程序有高并发的访问某一个表时，尽量对该表的执行操作串行化，或者锁升级，一次性获取所有的锁资源。

然后也可以设置参数`innodb_lock_wait_timeout`，超时时间，并且将参数`innodb_deadlock_detect` 打开，当发现死锁的时候，自动回滚其中的某一个事务。

总结
--

上面详细的介绍了 MyISAM 和 InnoDB 两种存储引擎的锁机制的实现，并进行了测试。

MyISAM 的表锁分为两种模式：**「共享读锁」**和**「排它写锁」**。获取的读锁的线程对该数据行只能读，不能修改，其它线程也只能对该数据行加读锁。

获取到写锁的线程对该数据行既能读也能写，对其他线程对该数据行的读写具有排它性。

MyISAM 中默认写优先于去操作，因此 MyISAM 一般不适合运用于大量读写操作的程序中。

InnoDB 的行锁虽然会出现死锁的可能，但是 InnoDB 的支持的并发性能比 MyISAM 好，行锁的粒度最小，一定的方法和措施可以解决死锁的发生，极大的发挥 InnoDB 的性能。

InnoDB 中引入了间隙锁的概念来决解出现幻读的问题，也引入事务的特性，通过事务的四种隔离级别，来降低锁冲突，提高并发性能。

![](https://mmbiz.qpic.cn/mmbiz_jpg/IJUXwBNpKlgbIBdolPSlRpvyVguoBgHsE8YxmzrbDHjfRrqnRoVEcygF60roJqUiatSVTFgOe9uG5t7yvcF6xSg/640?wx_fmt=jpeg)

【文章参考】

[1] https://www.cnblogs.com/leedaily/p/8378779.html

[2] https://blog.csdn.net/qq_38238296/article/details/88362999

【推荐阅读】

[1] [看完这篇 Redis 缓存三大问题，保你能和面试官互扯。](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483977&idx=1&sn=e42c9aedb8bb1c914afa5f2f74324d1f&chksm=fbf7eb8bcc80629dac2c4074c393ca672915bab26864a277251883f1f6c2a6d96338dae3e5b0&scene=21#wechat_redirect)

[2] [我以为我对 Mysql 事务很熟，直到我遇到了阿里面试官](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484313&idx=1&sn=6801e8822dfb9b4f2192960c8c3d5530&chksm=fbf7ea5bcc80634da0cf388f9e4efe36b87f278ee712faf5f4e6221173fc47a41547d135056a&scene=21#wechat_redirect)[。](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484313&idx=1&sn=6801e8822dfb9b4f2192960c8c3d5530&chksm=fbf7ea5bcc80634da0cf388f9e4efe36b87f278ee712faf5f4e6221173fc47a41547d135056a&scene=21#wechat_redirect)

[3] [面试官：你知道 java 类是怎么跑起来的吗？问的我一脸懵](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483972&idx=1&sn=5e6b01caa6aa8fe7111924dc850e865e&chksm=fbf7eb86cc806290160187cdc195aad6939ba81e6988602db59f897fe1ee4f4d08a573424bea&scene=21#wechat_redirect)

[4] [面试造飞机系列：面对 Redis 持久化连环 Call，你还顶得住吗？](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484021&idx=1&sn=4c228c705cfd524db3cf27425fde1ba0&chksm=fbf7ebb7cc8062a138b9baad194548983095806f8f15a82e1a95f18f19999c76d750aa91b93b&scene=21#wechat_redirect)

[5] [面试造飞机系列：用心整理的 HashMap 面试题，以后都不用担心了](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483998&idx=1&sn=b155ac4d5263d54ea3008ff33196c3b2&chksm=fbf7eb9ccc80628a559180abac786b108859180787716e093da6a315c0226071060b73836ce5&scene=21#wechat_redirect)