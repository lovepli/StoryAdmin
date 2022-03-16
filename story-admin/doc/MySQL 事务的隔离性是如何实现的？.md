> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/evegVfhpfuM51X8W1Cx5cQ)

并发场景
----

最近做了一些分布式事务的项目，对事务的隔离性有了更深的认识，后续写文章聊分布式事务。今天就复盘一下单机事务的隔离性是如何实现的？

**「隔离的本质就是控制并发」**，如果 SQL 语句就是串行执行的。那么数据库的四大特性中就不会有隔离性这个概念了，也就不会有脏读，不可重复读，幻读等各种问题了

**「对数据库的各种并发操作，只有如下四种，写写，读读，读写和写读」**

### 写 - 写

事务 A 更新一条记录的时候，事务 B 能同时更新同一条记录吗？

答案肯定是不能的，不然就会造成**「脏写」**问题，那如何避免脏写呢？答案就是**「加锁」**

### 读 - 读

MySQL 读操作默认情况下不会加锁，所以可以并行的读

### 读 - 写 和 写 - 读

**「基于各种场景对并发操作容忍程度不同，MySQL 就搞了个隔离性的概念」**。你自己根据业务场景选择隔离级别。

√ 为会发生，× 为不会发生

<table data-darkmode-bgcolor-16336202564253="rgba(112, 0, 0, 0.039999999999999994)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)" data-darkmode-color-16336202564253="rgb(163, 163, 163)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)"><thead data-darkmode-bgcolor-16336202564253="rgba(112, 0, 0, 0.039999999999999994)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)" data-darkmode-color-16336202564253="rgb(163, 163, 163)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)"><tr data-darkmode-bgcolor-16336202564253="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16336202564253="rgb(163, 163, 163)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><th data-darkmode-bgcolor-16336202564253="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)|rgb(240, 240, 240)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">隔离级别</th><th data-darkmode-bgcolor-16336202564253="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)|rgb(240, 240, 240)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">脏读</th><th data-darkmode-bgcolor-16336202564253="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)|rgb(240, 240, 240)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">不可重复读</th><th data-darkmode-bgcolor-16336202564253="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)|rgb(240, 240, 240)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">幻读</th></tr></thead><tbody data-darkmode-bgcolor-16336202564253="rgba(112, 0, 0, 0.039999999999999994)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)" data-darkmode-color-16336202564253="rgb(163, 163, 163)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)"><tr data-darkmode-bgcolor-16336202564253="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16336202564253="rgb(163, 163, 163)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-bgcolor-16336202564253="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">read uncommitted（未提交读）</td><td data-darkmode-bgcolor-16336202564253="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">√</td><td data-darkmode-bgcolor-16336202564253="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">√</td><td data-darkmode-bgcolor-16336202564253="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">√</td></tr><tr data-darkmode-bgcolor-16336202564253="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16336202564253="rgb(163, 163, 163)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-bgcolor-16336202564253="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">read committed（提交读）</td><td data-darkmode-bgcolor-16336202564253="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">×</td><td data-darkmode-bgcolor-16336202564253="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">√</td><td data-darkmode-bgcolor-16336202564253="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">√</td></tr><tr data-darkmode-bgcolor-16336202564253="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16336202564253="rgb(163, 163, 163)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-bgcolor-16336202564253="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">repeatable read（可重复读）</td><td data-darkmode-bgcolor-16336202564253="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">×</td><td data-darkmode-bgcolor-16336202564253="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">×</td><td data-darkmode-bgcolor-16336202564253="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">√</td></tr><tr data-darkmode-bgcolor-16336202564253="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16336202564253="rgb(163, 163, 163)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-bgcolor-16336202564253="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">serializable （可串行化）</td><td data-darkmode-bgcolor-16336202564253="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">×</td><td data-darkmode-bgcolor-16336202564253="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">×</td><td data-darkmode-bgcolor-16336202564253="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">×</td></tr></tbody></table>

**「所以你看，MySQL 是通过锁和隔离级别对 MySQL 进行并发控制的」**

MySQL 中的锁
---------

### 行级锁

InnoDB 存储引擎中有如下两种类型的行级锁

1.  **「共享锁」**（Shared Lock，简称 S 锁），在事务需要读取一条记录时，需要先获取改记录的 S 锁

2.  **「排他锁」**（Exclusive Lock，简称 X 锁），在事务要改动一条记录时，需要先获取该记录的 X 锁


如果事务 T1 获取了一条记录的 S 锁之后，事务 T2 也要访问这条记录。如果事务 T2 想再获取这个记录的 S 锁，可以成功，这种情况称为锁兼容，如果事务 T2 想再获取这个记录的 X 锁，那么此操作会被阻塞，直到事务 T1 提交之后将 S 锁释放掉

如果事务 T1 获取了一条记录的 X 锁之后，那么不管事务 T2 接着想获取该记录的 S 锁还是 X 锁都会被阻塞，直到事务 1 提交，这种情况称为锁不兼容。

**「多个事务可以同时读取记录，即共享锁之间不互斥，但共享锁会阻塞排他锁。排他锁之间互斥」**

S 锁和 X 锁之间的兼容关系如下

<table data-darkmode-bgcolor-16336202564253="rgba(112, 0, 0, 0.039999999999999994)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)" data-darkmode-color-16336202564253="rgb(163, 163, 163)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)"><thead data-darkmode-bgcolor-16336202564253="rgba(112, 0, 0, 0.039999999999999994)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)" data-darkmode-color-16336202564253="rgb(163, 163, 163)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)"><tr data-darkmode-bgcolor-16336202564253="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16336202564253="rgb(163, 163, 163)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><th data-darkmode-bgcolor-16336202564253="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)|rgb(240, 240, 240)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">兼容性</th><th data-darkmode-bgcolor-16336202564253="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)|rgb(240, 240, 240)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">X 锁</th><th data-darkmode-bgcolor-16336202564253="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)|rgb(240, 240, 240)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">S 锁</th></tr></thead><tbody data-darkmode-bgcolor-16336202564253="rgba(112, 0, 0, 0.039999999999999994)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)" data-darkmode-color-16336202564253="rgb(163, 163, 163)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)"><tr data-darkmode-bgcolor-16336202564253="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16336202564253="rgb(163, 163, 163)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-bgcolor-16336202564253="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">X 锁</td><td data-darkmode-bgcolor-16336202564253="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">互斥</td><td data-darkmode-bgcolor-16336202564253="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(255,255,255)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">互斥</td></tr><tr data-darkmode-bgcolor-16336202564253="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16336202564253="rgb(163, 163, 163)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-bgcolor-16336202564253="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">S 锁</td><td data-darkmode-bgcolor-16336202564253="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">互斥</td><td data-darkmode-bgcolor-16336202564253="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336202564253="#fff|rgba(50, 0, 0, 0.039999999999999994)|rgb(248, 248, 248)" data-darkmode-color-16336202564253="rgb(141, 141, 141)" data-darkmode-original-color-16336202564253="#fff|rgb(43, 43, 43)|rgb(89, 89, 89)" data-style="border-color: rgb(204, 204, 204); font-size: 14px; color: rgb(89, 89, 89); min-width: 85px;">兼容</td></tr></tbody></table>

**「update，delete，insert 都会自动给涉及到的数据加上排他锁，select 语句默认不会加任何锁」**

那什么情况下会对读操作加锁呢？

1.  select .. lock in share mode，对读取的记录加 S 锁

2.  select ... for update ，对读取的记录加 X 锁

3.  在事务中读取记录，对读取的记录加 S 锁

4.  事务隔离级别在 SERIALIZABLE 下，对读取的记录加 S 锁


**「InnoDB 中有如下三种锁」**

1.  Record Lock：对单个记录加锁

2.  Gap Lock：间隙锁，锁住记录前面的间隙，不允许插入记录

3.  Next-key Lock：同时锁住数据和数据前面的间隙，即数据和数据前面的间隙都不允许插入记录


写个 Demo 演示一下

```
CREATE TABLE `girl` (
  `id` int(11) NOT NULL,
  `name` varchar(255),
  `age` int(11),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

```
insert into girl values
(1, '西施', 20),
(5, '王昭君', 23),
(8, '貂蝉', 25),
(10, '杨玉环', 26),
(12, '陈圆圆', 20);
```

#### Record Lock

**「对单个记录加锁」**

如把 id 值为 8 的数据加一个 Record Lock，示意图如下![](https://mmbiz.qpic.cn/mmbiz_png/OQmPiaEUnhd5SmMQicib7RjUFm9lH858HXtLkSIzNwsbRSoQiaZVNMXMjl5BENvNAkaan4ICZZxx1N1ZOwobSpKvGg/640?wx_fmt=png) Record Lock 也是有 S 锁和 X 锁之分的，兼容性和之前描述的一样。

SQL 执行加什么样的锁受很多条件的制约，比如事务的隔离级别，执行时使用的索引（如，聚集索引，非聚集索引等），因此就不详细分析了，举几个简单的例子。

```
-- READ UNCOMMITTED/READ COMMITTED/REPEATABLE READ 利用主键进行等值查询
-- 对id=8的记录加S型Record Lock
select * from girl where id = 8 lock in share mode;

-- READ UNCOMMITTED/READ COMMITTED/REPEATABLE READ 利用主键进行等值查询
-- 对id=8的记录加X型Record Lock
select * from girl where id = 8 for update;
```

### Gap Lock

**「锁住记录前面的间隙，不允许插入记录」**

**「MySQL 在可重复读隔离级别下可以通过 MVCC 和加锁来解决幻读问题」**

当前读：加锁

快照读：MVCC

但是该如何加锁呢？因为第一次执行读取操作的时候，这些幻影记录并不存在，我们没有办法加 Record Lock，此时可以通过加 Gap Lock 解决，即对间隙加锁。![](https://mmbiz.qpic.cn/mmbiz_png/OQmPiaEUnhd5SmMQicib7RjUFm9lH858HXtaQaukgodtEB6mV0Gzs9ZaaJEDRBwZTiaMDibY7BNiaEQlsCaaNB5ea6vw/640?wx_fmt=png)如一个事务对 id=8 的记录加间隙锁，则意味着不允许别的事务在 id=8 的记录前面的间隙插入新记录，即 id 值在 (5, 8) 这个区间内的记录是不允许立即插入的。直到加间隙锁的事务提交后，id 值在 (5, 8) 这个区间中的记录才可以被提交

我们来看如下一个 SQL 的加锁过程

```
-- REPEATABLE READ 利用主键进行等值查询
-- 但是主键值并不存在
-- 对id=8的聚集索引记录加Gap Lock
SELECT * FROM girl WHERE id = 7 LOCK IN SHARE MODE;
```

由于 id=7 的记录不存在，为了禁止幻读现象（避免在同一事务下执行相同的语句得到的结果集中有 id=7 的记录），所以在当前事务提交前我们要预防别的事务插入 id=7 的记录，此时在 id=8 的记录上加一个 Gap Lock 即可，即不允许别的事务插入 id 值在 (5, 8) 这个区间的新记录

![](https://mmbiz.qpic.cn/mmbiz_png/OQmPiaEUnhd5SmMQicib7RjUFm9lH858HXtRCQg2jJCjufrePpQ2g3ZicMCqiaicZ4R7kSQiaM4wmquTTRIH9Q0sibpflg/640?wx_fmt=png)**「给大家提一个问题，Gap Lock 只能锁定记录前面的间隙，那么最后一条记录后面的间隙该怎么锁定？」**

其实 mysql 数据是存在页中的，每个页有 2 个伪记录

1.  Infimum 记录，表示该页面中最小的记录

2.  upremum 记录，表示该页面中最大的记录


为了防止其它事务插入 id 值在 (12, +∞) 这个区间的记录，我们可以给 id=12 记录所在页面的 Supremum 记录加上一个 gap 锁，此时就可以阻止其他事务插入 id 值在 (12, +∞) 这个区间的新记录

### Next-key Lock

**「同时锁住数据和数据前面的间隙，即数据和数据前面的间隙都不允许插入记录」**所以你可以这样理解 Next-key Lock=Record Lock+Gap Lock![](https://mmbiz.qpic.cn/mmbiz_png/OQmPiaEUnhd5SmMQicib7RjUFm9lH858HXtHhibP905yuY9QwPdSUgHETzORVicvD6GbKkDDZS8dUZsWKrxVnwOmqnQ/640?wx_fmt=png)

```
-- REPEATABLE READ 利用主键进行范围查询
-- 对id=8的聚集索引记录加S型Record Lock
-- 对id>8的所有聚集索引记录加S型Next-key Lock（包括Supremum伪记录）
SELECT * FROM girl WHERE id >= 8 LOCK IN SHARE MODE;
```

因为要解决幻读的问题，所以需要禁别的事务插入 id>=8 的记录，所以

*   对 id=8 的聚集索引记录加 S 型 Record Lock

*   对 id>8 的所有聚集索引记录加 S 型 Next-key Lock（包括 Supremum 伪记录）


### 表级锁

**「表锁也有 S 锁和 X 锁之分」**

在对某个表执行 select，insert，update，delete 语句时，innodb 存储引擎是不会为这个表添加表级别的 S 锁或者 X 锁。

在对表执行一些诸如 ALTER TABLE，DROP TABLE 这类的 DDL 语句时，会对这个表加 X 锁，因此其他事务对这个表执行诸如 SELECT INSERT UPDATE DELETE 的语句会发生阻塞

在系统变量 autocommit=0，innodb_table_locks = 1 时，手动获取 InnoDB 存储引擎提供的表 t 的 S 锁或者 X 锁，可以这么写

对表 t 加表级别的 S 锁

```
lock tables t read
```

对表 t 加表级别的 X 锁

```
lock tables t write
```

**「如果一个事务给表加了 S 锁，那么」**

*   别的事务可以继续获得该表的 S 锁

*   别的事务可以继续获得表中某些记录的 S 锁

*   别的事务不可以继续获得该表的 X 锁

*   别的事务不可以继续获得表中某些记录的 X 锁


**「如果一个事务给表加了 X 锁，那么」**

*   别的事务不可以继续获得该表的 S 锁

*   别的事务不可以继续获得表中某些记录的 S 锁

*   别的事务不可以继续获得该表的 X 锁

*   别的事务不可以继续获得表中某些记录的 X 锁


**「所以修改线上的表时一定要小心，因为会使大量事务阻塞」**，目前有很多成熟的修改线上表的方法，不再赘述

隔离级别
----

读未提交：每次读取最新的记录，没有做特殊处理 串行化：事务串行执行，不会产生并发

所以我们重点关注**「读已提交」**和**「可重复读」**的隔离实现！

**「这两种隔离级别是通过 MVCC（多版本并发控制）来实现的，本质就是 MySQL 通过 undolog 存储了多个版本的历史数据，根据规则读取某一历史版本的数据，这样就可以在无锁的情况下实现读写并行，提高数据库性能」**

**「那么 undolog 是如何存储修改前的记录？」**

**「对于使用 InnoDB 存储引擎的表来说，聚集索引记录中都包含下面 2 个必要的隐藏列」**

**「trx_id」**：一个事务每次对某条聚集索引记录进行改动时，都会把该事务的事务 id 赋值给 trx_id 隐藏列

**「roll_pointer」**：每次对某条聚集索引记录进行改动时，都会把旧的版本写入 undo 日志中。这个隐藏列就相当于一个指针，通过他找到该记录修改前的信息

如果一个记录的 name 从貂蝉被依次改为王昭君，西施，会有如下的记录，多个记录构成了一个版本链

![](https://mmbiz.qpic.cn/mmbiz_png/OQmPiaEUnhd5SmMQicib7RjUFm9lH858HXtGTZrh3lqkO97qp9W8OJzogkK4rgyv7OSgX3qQNFYyL7oAZQJ8Fqd0Q/640?wx_fmt=png)

**「为了判断版本链中哪个版本对当前事务是可见的，MySQL 设计出了 ReadView 的概念」**。4 个重要的内容如下

**「m_ids」**：在生成 ReadView 时，当前系统中活跃的事务 id 列表**「min_trx_id」**：在生成 ReadView 时，当前系统中活跃的最小的事务 id，也就是 m_ids 中的最小值**「max_trx_id」**：在生成 ReadView 时，系统应该分配给下一个事务的事务 id 值**「creator_trx_id」**：生成该 ReadView 的事务的事务 id

当对表中的记录进行改动时，执行 insert，delete，update 这些语句时，才会为事务分配唯一的事务 id，否则一个事务的事务 id 值默认为 0。

max_trx_id 并不是 m_ids 中的最大值，事务 id 是递增分配的。比如现在有事务 id 为 1，2，3 这三个事务，之后事务 id 为 3 的事务提交了，当有一个新的事务生成 ReadView 时，m_ids 的值就包括 1 和 2，min_trx_id 的值就是 1，max_trx_id 的值就是 4

![](https://mmbiz.qpic.cn/mmbiz_png/OQmPiaEUnhd5SmMQicib7RjUFm9lH858HXt3BUyyGcRMPOxypHnUEkPgpFunJPylJqmbBPXvK511aic6rpA7ibndJicg/640?wx_fmt=png)执行过程如下：

1.  如果被访问版本的 trx_id=creator_id，意味着当前事务在访问它自己修改过的记录，所以该版本可以被当前事务访问

2.  如果被访问版本的 trx_id<min_trx_id，表明生成该版本的事务在当前事务生成 ReadView 前已经提交，所以该版本可以被当前事务访问

3.  被访问版本的 trx_id>=max_trx_id，表明生成该版本的事务在当前事务生成 ReadView 后才开启，该版本不可以被当前事务访问

4.  被访问版本的 trx_id 是否在 m_ids 列表中 4.1 是，创建 ReadView 时，该版本还是活跃的，该版本不可以被访问。顺着版本链找下一个版本的数据，继续执行上面的步骤判断可见性，如果最后一个版本还不可见，意味着记录对当前事务完全不可见 4.2 否，创建 ReadView 时，生成该版本的事务已经被提交，该版本可以被访问


**「好了，我们知道了版本可见性的获取规则，那么是怎么实现读已提交和可重复读的呢？」**

其实很简单，就是生成 ReadView 的时机不同

举个例子，先建立如下表

```
CREATE TABLE `girl` (
  `id` int(11) NOT NULL,
  `name` varchar(255),
  `age` int(11),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

### Read Committed

**「Read Committed（读已提交），每次读取数据前都生成一个 ReadView」**

![](https://mmbiz.qpic.cn/mmbiz_png/OQmPiaEUnhd5SmMQicib7RjUFm9lH858HXtGTZrh3lqkO97qp9W8OJzogkK4rgyv7OSgX3qQNFYyL7oAZQJ8Fqd0Q/640?wx_fmt=png)下面是 3 个事务执行的过程，一行代表一个时间点![](https://mmbiz.qpic.cn/mmbiz_png/OQmPiaEUnhd5SmMQicib7RjUFm9lH858HXtH6pkU547A0qicxcndwevqN561qhfl6iaGYeYHx6xPm8vnEBb8nLGtztA/640?wx_fmt=png)**「先分析一下 5 这个时间点 select 的执行过程」**

1.  系统中有两个事务 id 分别为 100，200 的事务正在执行

2.  执行 select 语句时生成一个 ReadView，mids=[100,200]，min_trx_id=100，max_trx_id=201，creator_trx_id=0（select 这个事务没有执行更改操作，事务 id 默认为 0）

3.  最新版本的 name 列为西施，该版本 trx_id 值为 100，在 mids 列表中，不符合可见性要求，根据 roll_pointer 跳到下一个版本

4.  下一个版本的 name 列王昭君，该版本的 trx_id 值为 100，也在 mids 列表内，因此也不符合要求，继续跳到下一个版本

5.  下一个版本的 name 列为貂蝉，该版本的 trx_id 值为 10，小于 min_trx_id，因此最后返回的 name 值为貂蝉


![](https://mmbiz.qpic.cn/mmbiz_png/OQmPiaEUnhd5SmMQicib7RjUFm9lH858HXtBaVWlWmpOic8m6oMhqkKyPo5kDu8qkCicib693Ntaic83OL6uCiaEdyZElg/640?wx_fmt=png)

**「再分析一下 8 这个时间点 select 的执行过程」**

1.  系统中有一个事务 id 为 200 的事务正在执行（事务 id 为 100 的事务已经提交）

2.  执行 select 语句时生成一个 ReadView，mids=[200]，min_trx_id=200，max_trx_id=201，creator_trx_id=0

3.  最新版本的 name 列为杨玉环，该版本 trx_id 值为 200，在 mids 列表中，不符合可见性要求，根据 roll_pointer 跳到下一个版本

4.  下一个版本的 name 列为西施，该版本的 trx_id 值为 100，小于 min_trx_id，因此最后返回的 name 值为西施


当事务 id 为 200 的事务提交时，查询得到的 name 列为杨玉环。

### Repeatable Read

**「Repeatable Read（可重复读），在第一次读取数据时生成一个 ReadView」**![](https://mmbiz.qpic.cn/mmbiz_png/OQmPiaEUnhd5SmMQicib7RjUFm9lH858HXtA5ibGVStrRJVG11H0obiaXOIOFCExJkwCTym9GE9XnLcqqU6mfjlQOibA/640?wx_fmt=png)可重复读因为只在第一次读取数据的时候生成 ReadView，所以每次读到的是相同的版本，即 name 值一直为貂蝉，具体的过程上面已经演示了两遍了，我这里就不重复演示了，相信你一定会自己分析了。

公众号