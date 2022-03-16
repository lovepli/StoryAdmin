> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247487126&idx=1&sn=dd36423f6726852bc18eeef0d7c4e4da&scene=21#wechat_redirect)

![](https://mmbiz.qpic.cn/mmbiz_jpg/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CtYvRusRIjcZHlIqfSn8t2QlmjNw3vW0Uhyd7wD3yZJBPUeYhw5BXNg/640?wx_fmt=jpeg)

### 1. 什么 Mysql 的事务？事务的四大特性？事务带来的什么问题？

Mysql 中事务的隔离级别分为四大等级：**读未提交（READ UNCOMMITTED）、读提交 （READ COMMITTED）、可重复读 （REPEATABLE READ）、串行化 （SERIALIZABLE）**。

在 Mysql 中事务的四大特性主要包含：**原子性（Atomicity）、一致性（Consistent）、隔离性（Isalotion）、持久性 (Durable)**，简称为`ACID`。

1.  **原子性**：是指事务的原子性操作，**对数据的修改要么全部执行成功，要么全部失败，实现事务的原子性**，是基于日志的 Redo/Undo 机制。

2.  **一致性**：是指**执行事务前后的状态要一致**，可以理解为数据一致性。

3.  **隔离性**：侧重指**事务之间相互隔离，不受影响**，这个与事务设置的隔离级别有密切的关系。

4.  **持久性**：则是指在**一个事务提交后，这个事务的状态会被持久化到数据库中，也就是事务提交，对数据的新增、更新将会持久化到数据库中**。


在我的理解中：**原子性、隔离性、持久性都是为了保障一致性而存在的，一致性也是最终的目的**。

没有那种隔离级别是完美的，只能根据自己的项目业务场景去评估选择最适合的隔离级别，大部分的公司一般选择 Mysql 默认的隔离级别：**可重复读**。

隔离级别从：**读未提交 - 读提交 - 可重复读 - 串行化**，**级别越来越高，隔离也就越来越严实，到最后的串行化，当出现读写锁冲突的时候，后面的事务只能等前面的事务完成后才能继续访问**。

1.  **读未提交：读取到别的事务还没有提交的数据，从而产生了脏读**。

2.  **读提交：读取别的事务已经提交的数据，从而产生不可重复读。**

3.  **可重复读：事务开启过程中看到的数据和事务刚开始看到的数据是一样的，从而产生幻读，在 Mysql 的中通过 MVCC 多版本控制的一致性视图解决了不可重复读问题以及通过间隙锁解决了幻读问题。**

4.  **串行化：对于同一行记录，若是读写锁发生冲突，后面访问的事务只能等前面的事务执行完才能继续访问**。


举个例子，假如有一个 user 表，里面有两个字段 id 和 age，里面有一条测试数据：（1,24），现在要执行 age+1，同时有两个事务执行：

<table data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)"><thead data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)"><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><th data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)|rgb(240, 240, 240)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); min-width: 85px;">事务 1</th><th data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)|rgb(240, 240, 240)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); min-width: 85px;">事务 2</th></tr></thead><tbody data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)"><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">启动事务，接着查询 age（a1）</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;"><br data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)"></td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;"><br data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)"></td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">启动事务</td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;"><br data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)"></td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">查询 age（a2）</td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;"><br data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)"></td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">执行 age=age+1</td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">查询 age（a3）</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;"><br data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)"></td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;"><br data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)"></td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">提交事务</td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">查询 age（a4）</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;"><br data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)"></td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">提交事务</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;"><br data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)"></td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">查询 age（a5）</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;"><br data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)"></td></tr></tbody></table>

经过上面的执行，在四种隔离级别下 a1,a2,a3,a4,a5 的值分别是多少？我们来认真的分析一波：

1.  **读未提交**：a1 和 a2 因为读的是初始值所以为 24，隔离级别为读未提交，事务 2 执行了 age=age+1，不管事务 2 是否提交，那么 a3、a4 和 a5 的值都是 25。

2.  **读提交**：a1 和 a2 因为读的是初始值所以为 24，隔离级别为读提交所以 a3 还是 24，a4 和 a5 因为事务 2 已经提交所以得到的值是 25。

3.  **可重复读**：a1 和 a2 因为读的是初始值所以为 24，可重复读的隔离级别下，a3 和 a4 读取的值和事务开始的结果一样，所以还是 24，a5 前一步因为已经提交事务，所以 a5 的值是 25。

4.  **串行化**：a1 和 a2 因为读的是初始值所以为 24，串行化隔离级别下，当事务 2 修改数据的时候，获取了写锁，事务 1 读取 age 的值会被锁住，所以在事务 1 的角度下 a3 和 a4 读取的值为 24，a5 的值为 25。


当你能够分析得出这个例子下，在不同隔离级别下分析的出 a1-a5 的值，说明你对事务的隔离级别已经有比较深入的理解了。

### 2. 你详细了解过 MVCC 吗？它是怎么工作的？

`MVCC`叫做**多版本控制**，实现 MVCC 时用到了**一致性视图**，用于支持**读提交**和**可重复读**的实现。

对于一行数据若是想实现可重复读取或者能够读取数据的另一个事务未提交前的原始值，那么必须对原始数据进行保存或者对更新操作进行保存，这样才能够查询到原始值。

在 Mysql 的 MVCC 中规定**每一行数据都有多个不同的版本，一个事务更新操作完后就生成一个新的版本**，并不是对全部数据的全量备份，因为全量备份的代价太大了：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CJJGicssrBlEao3ULmOiaAhkN8xc6SXibhx4sqpCrOv0ehtZkcwBYEibwUw/640?wx_fmt=png)

如图中所示，假如三个事务更新了同一行数据，那么就会有对应的 v1、v2、v3 三个数据版本，每一个事务在开始的时候都获得一个唯一的事务 id（`transaction id`），并且是顺序递增的，并且这个事务 id 最后会赋值给`row trx_id`，这样就形成了一个唯一的一行数据版本。

实际上版本 1、版本 2 并非实际物理存在的，而图中的 U1 和 U2 实际就是`undo log`日志（**回滚日志**），这 v1 和 v2 版本是根据当前 v3 和`undo log`计算出来的。

InnoDB 引擎就是利用每行数据有多个版本的特性，实现了秒级创建 “快照”，并不需要花费大量的是时间。

### 3.Mysql 的 InnoDB 和 MyISAM 有什么区别？

（1）InnoDB 和 MyISAM 都是 Mysql 的存储引擎，现在 MyISAM 也逐渐被 InnoDB 给替代，主要因为 InnoDB 支持事务和行级锁，MyISAM 不支持事务和行级锁，MyISAM 最小锁单位是表级。因为 MyISAM 不支持行级锁，所以在并发处理能力上 InnoDB 会比 MyISAM 好。

（2） 数据的存储上：MyISAM 的索引也是由 B + 树构成，但是树的叶子结点存的是行数据的地址，查找时需要找到叶子结点的地址，再根据叶子结点地址查找数据。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CKxb1UVOE3ESo3VFXSKUBYZGeSVR7ss6EPVb0JV2iaxErACQuiaKz8icAw/640?wx_fmt=png)

InnoDB 的主键索引的叶子结点直接就是存储行数据，查找主键索引树就能获得数据：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CFu4aSORQ7RHCfPcVdZB7zaIPIP6iaCdIDoUdFzgDLhQzMIQekSiauovA/640?wx_fmt=png)

若是根据非主键索引查找，非主键索引的叶子结点存储的就是，当前索引值以及对应的主键的值，若是联合索引存储的就是联合索引值和对应的主键值。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CYy8iafHDudNjV4iclf8TIBd4WZ3zHZR5P1pajgMAicvDUOxy568BFD0rA/640?wx_fmt=png)

（3）数据文件构成：MyISAM 有三种存储文件分别是扩展名为：`.frm`（文件存储表定义）、`.MYD` (MYData 数据文件)、`.MYI` (MYIndex 索引文件)。而 InnoDB 的表只受限于操作系统文件的大小，一般是 2GB

（4）查询区别：对于读多写少的业务场景，MyISAM 会更加适合，而对于 update 和 insert 比较多的场景 InnoDB 会比较适合。

（5）coun(*) 区别：select count(*) from table，MyISAM 引擎会查询已经保存好的行数，这是不加 where 的条件下，而 InnoDB 需要全表扫描一遍，InnoDB 并没有保存表的具体行数。

（6）其它的区别：InnoDB 支持外键，但是不支持全文索引，而 MyISAM 不支持外键，支持全文索引，InnoDB 的主键的范围比 MyISAM 的大。

### 4. 你知道执行一条查询语句的流程吗？

当 Mysql 执行一条查询的 SQl 的时候大概发生了以下的步骤：

1.  客户端发送查询语句给服务器。

2.  服务器首先进行用户名和密码的验证以及权限的校验。

3.  然后会检查缓存中是否存在该查询，若存在，返回缓存中存在的结果。若是不存在就进行下一步。注意：Mysql 8 就把缓存这块给砍掉了。

4.  接着进行语法和词法的分析，对 SQl 的解析、语法检测和预处理，再由优化器生成对应的执行计划。

5.  Mysql 的执行器根据优化器生成的执行计划执行，调用存储引擎的接口进行查询。服务器将查询的结果返回客户端。


Mysql 中语句的执行都是都是分层执行，每一层执行的任务都不同，直到最后拿到结果返回，主要分为 **Service 层**和**引擎层**。

**在 Service 层中包含：连接器、分析器、优化器、执行器。引擎层以插件的形式可以兼容各种不同的存储引擎，主要包含的有 InnoDB 和 MyISAM 两种存储引擎**。具体的执行流程图如下所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2C0I2TcMia5tOpFZxnuNz8pibEV9tFRJkDYw7BSaVj6iaQA70R2xHo8qZUQ/640?wx_fmt=png)

### 5.redo log 和 binlog 了解过吗？

`redo log`日志也叫做`WAL`技术（`Write- Ahead Logging`），他是一种**先写日志，并更新内存，最后再更新磁盘的技术**，为了就是减少 sql 执行期间的数据库 io 操作，并且更新磁盘往往是在 Mysql 比较闲的时候，这样就大大减轻了 Mysql 的压力。

`redo log`是固定大小，是物理日志，属于 InnoDB 引擎的，并且写 redo log 是环状写日志的形式：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2Cy1aibFicjwWtt3JibPBtycwiccBaws2e43cvAMNibfCruVfiaBkjW8tEb86w/640?wx_fmt=png)

如上图所示：若是四组的 redo log 文件，一组为 1G 的大小，那么四组就是 4G 的大小，其中`write pos`是**记录当前的位置**，有数据写入当前位置，那么 write pos 就会边写入边往后移。

`check point`记录**擦除的位置**，因为 redo log 是固定大小，所以当 redo log 满的时候，也就是`write pos`追上`check point`的时候，需要清除`redo log`的部分数据，清除的数据会被持久化到磁盘中，然后将`check point`向前移动。

`redo log`日志实现了即使在数据库出现异常宕机的时候，重启后之前的记录也不会丢失，这就是`crash-safe`能力。

`binlog`称为**归档日志**，是逻辑上的日志，它属于 Mysql 的 Server 层面的日志，记录着 sql 的原始逻辑，主要有两种模式：**一个是 statement 格式记录的是原始的 sql，而 row 格式则是记录行内容**。

redo log 和 binlog 记录的形式、内容不同，这两者日志都能通过自己记录的内容恢复数据。

之所以这两个日志同时存在，是因为刚开始 Mysql 自带的引擎 MyISAM 就没有 crash-safe 功能的，并且在此之前 Mysql 还没有 InnoDB 引擎，Mysql 自带的 binlog 日志只是用来归档日志的，所以 InnoDB 引擎也就通过自己 redo log 日志来实现 crash-safe 功能。

### 6. 线上要给热点数据表添加字段该怎么操作？

首先给表加一个字段，会导致扫描全表数据，并且会加 MDL 写锁，所以在线上操作一定要谨慎再谨慎，有可能还没操作完就导致数据库给搞崩了。

对于这种情况有限考虑线上的稳定的运行，加字段是其次，可以通过在 alter table 后设定等待的时间，若是获取不到锁后面在进行尝试，并且可以选择访问量比较上的时间段进行获取。

若是能获取到锁那是最好了，当然即使获取到锁也不要阻塞后面的业务语句，一切都是以业务优先为原则。

### 7.Msyql 的索引的底层实现吗？为什么不用有序数组、hash 或者二叉树实现索引？

Mysql 的索引是一种加快查询速度的数据结构，索引就好比书的目录一样能够快速的定位你要查找的位置。

Mysql 的索引底层是使用 B + 树的数据结构进行实现，结构如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CFu4aSORQ7RHCfPcVdZB7zaIPIP6iaCdIDoUdFzgDLhQzMIQekSiauovA/640?wx_fmt=png)

索引的一个数据页的大小是 16kb，从磁盘加载到内存中是以数据页的大小为单位进行加载，然后供查询操作进行查询，若是查询的数据不在内存中，才会从磁盘中再次加载到内存中。

索引的实现有很多，比如 hash。hash 是以`key-value`的形式进行存储，**适合于等值查询的场景**，查询的时间复杂度为 O(1)，因为 **hash 储存并不是有序的，所以对于范围查询就可能要遍历所有数据进行查询**，而且**不同值的计算还会出现 hash 冲突**，所以 hash 并不适合于做 Mysql 的索引。

有序数组在等值查询和范围查询性能都是非常好的，那为什么又不用有序数组作为索引呢？因为**对于数组而言作为索引更新的成本太高，新增数据要把后面的数据都往后移一位**，所以也不采用有序数组作为索引的底层实现。

最后二叉树，主要是因为**二叉树只有二叉，一个节点存储的数据量非常有限，需要频繁的随机 IO 读写磁盘，若是数据量大的情况下二叉的树高太高**，严重影响性能，所以也不采用二叉树进行实现。

而 B + 树是多叉树，一个数据页的大小是 16kb，在 1-3 的树高就能存储 10 亿级以上的数据，也就是只要访问磁盘 1-3 次就足够了，并且 **B + 树的叶子结点上一个叶子结点有指针指向下一个叶子结点，便于范围查询**：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CruKuPjrkhR1qeMr2XVQq5KmdLgswH9VZzbHtMe8f3dkuGnvgniaeySw/640?wx_fmt=png)

### 8. 怎么查看索引是否生效？什么情况下索引会失效呢？

查看索引是否起作用可以使用 explain 关键字，查询后的语句中的 key 字段，若是使用了索引，该字段会展示索引的名字。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2ChEFhmm0OBjVB4KpBicySAaVx6magrOQM1aMnQG2DicP540o0dDmwwxog/640?wx_fmt=png)

（1）**where 条件查询中使用了 or 关键字**，有可能使用了索引进行查询也会导致索引失效，若是想使用 or 关键字，又不想索引失效，只能在 or 的所有列上都建立索引。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2C3oWCzR36sHQ0WqTB3PDC21HIuZ2fax5F3S3uEDfafNCgicbWzqqt0Pg/640?wx_fmt=png)

（2）**条件查询中使用 like 关键字，并且不符合最左前缀原则**，会导致索引失效。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CU9ibcoYCAicOu93vICQFXVY1xILrpqNh9gtCf0gC4BXbrziciaG1rzDajg/640?wx_fmt=png)

（3）**条件查询的字段是字符串，而错误的使用 where column = 123 数字类型**也会导致索引失效。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CD3tic91ZcRUy2FrZuwQeRJb9OibVPSeQ0iaoDf6dRbhB3dIQpFIw1jxNw/640?wx_fmt=png)

（4）对于**联合索引查询不符合最左前缀原则**，也会导致索引失效，如下所示：

```
alter table user add index union_index(name, age)   // name左边的列， age 右边的列                                                              
select * from user where name = 'lidu'     // 会用到索引
select * from user where age = 18          //  不会使用索引
```

（5）在 **where 条件查询的后面对字段进行 null 值判断**，会导致索引失效，解决的办法就是可以把 null 改为 0 或者 - 1 这些特殊的值代替：

```
SELECT id FROM table WHERE num is null
```

（6）在 **where 子句中使用!= ,< > 这样的符号**，也会导致索引失效。

```
SELECT id FROM table WHERE num != 0
```

（7）**where 条件子句中 = 的左边使用表达式操作或者函数操作**，也会导致索引失效。

```
SELECT id FROM user WHERE age / 2 = 1
SELECT id FROM user WHERE SUBSTRING(name,1,2) = 'lidu'
```

### 9. 你知道有哪些种类的索引？

索引从**数据结构**进行划分的分为：**B + 树索引、hash 索引、R-Tree 索引、FULLTEXT 索引**。

索引从**物理存储**的角度划分为：**聚族索引**和**非聚族索引**。

从**逻辑的角度**分为：**主键索引**、**普通索引、唯一索引、联合索引**以及**空间索引**。

### 10. 你平时是怎么进行 SQL 优化的？

SQL 的优化主要是对字段添加索引，主要包含有这四种索引 (**主键索引 / 唯一索引 / 全文索引 / 普通索引**)，以及结合具体的业务场景分析具体是使用什么索引最合理。

explain 可以帮助我们在不真正执行某个 sql 语句时，就执行 mysql 怎样执行，这样利用我们去分析 sql 指令：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CCciaFh2w7BYq8dv1r9gGMTVXofUrYARCF69gcIuStlt1LOUpomeUq2A/640?wx_fmt=png)

1.  `id`：查询的序列号。

2.  `select_type`：查询类型。

3.  `table`：查询表名。

4.  `type`：扫描方式，all 表示全表扫描。

5.  `possible_keys`：可是使用到的索引。

6.  `key`：实际使用到的索引。

7.  `rows`：该 sql 扫面了多少行。

8.  `Extra`：sql 语句额外的信息，比如排序方式


#### SQL 优化方法

（1）对于条件查询，首先考虑在条件 where 和 order by 后的字段建立索引。（2）避免索引失效，避免 where 条件后进行 null 值的判断。（3）避免 where 后使用!= 或 <> 操作符。（4）避免在 where 后面进行使用函数。（5）避免 where 条件后使用 or 关键字来连接。

上面的这一些都是要注意的，当然还有很多的小技巧，都有可能会导致索引的实效。

#### 索引的种类

另一方面就是考虑到底是建立哪种索引比较合适，这里以普通索引和唯一索引进行举例说明。

假如我们的业务场景是读多写少的场景，那么 SQL 查询请求过来，假如数据已经在内存中，获取到数据后就直接返回，假如数据不在内存的数据页中，就会加载磁盘到内存中再返回，对于这种场景可能对于普通索引和唯一索引的选择性能上并没有明显的区别。

但是，一般建议选择普通索引，在写多读少的场景下，这两者索引的选择对性能的影响就比较大了，对于普通索引的的写，不管数据是否存在于内存中，都会先写入内存中的一小块叫做`chang buffer`内存中，然后在通过后台刷盘，一般会选择 Mysql 比较闲的时候进行刷盘。

而唯一索引就不同了，因为他要确保索引的唯一性，索引写数据的时候，假如数据不在内存中，要先从磁盘中加载数据到内存中，然后比较是否唯一，所以唯一索引就不能使用 chang buffer 的优化机制，会频繁的进行随机的磁盘 IO。

### 11. 什么是聚簇索引和非聚簇索引？

聚族索引和非聚族索引的主要区别是：聚族索引的叶子结点就是数据节点，而非聚族索引的叶子结点存储仍然是索引节点，只不过有指向对应数据块的指针。

区别这两者的区别就是来对比 InnoDB 和 MYISAM 的数据结构了。假如我们有一个表原始数据如下所示：

<table data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)"><thead data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)"><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><th data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)|rgb(240, 240, 240)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); min-width: 85px;">row number</th><th data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)|rgb(240, 240, 240)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); min-width: 85px;">col1</th><th data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)|rgb(240, 240, 240)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); min-width: 85px;">col2</th></tr></thead><tbody data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)"><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">0</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">99</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">8</td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">1</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">12</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">56</td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">2</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">3000</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">62</td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">...</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">...</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">...</td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">9997</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">18</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">8</td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">9998</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">4700</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">13</td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">9999</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">3</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">93</td></tr></tbody></table>

那么在 MyISAM 的索引中数据的储存结构如下所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2Cet1bHZczwNWLg9z7dGXtuSmq0AcnH2aRV7Vdk6s4BxDfujT7wkjdkw/640?wx_fmt=png)MyISAM 以叶子结点存储的 Row number 来找到对应的行数据，也就是叶子结点存储的是行指针，这也可以发现 MyISAM 引擎中数据文件（.MYI）和索引文件（.MYD）是分开的，索引 MyISAM 的查找索引树后，需要根据行指针二次的进行定位。

而在 InnoDB 的主键索引存储的结构形式如下所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CicGerStkHCL7jUvTJE5WkQTJ8cnF85dwIuh58N9HT1rAy3B5FmmP7IA/640?wx_fmt=png)InnoDB 的主键索引中叶子结点并不是存储行指针，而是存储行数据，二级索引中 MyISAM 也是一样的存储方式，InnoDB 的二级索引的叶子结点则是存储当前索引值以及对应的主键索引值。

InnoDB 的二级索引带来的好处就是**减少了由于数据移动或者数据页分列导致行数据的地址变了而带来的维护二级索引的性能开销，因为 InnoDB 的二级索引不需要更新行指针**：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CLdPRDUzKx0y5RIfvkN4ibrkHXJT3NkfX2gzeIUQUZftia3CjIzBIeiaaw/640?wx_fmt=png)

### 12. 什么是回表？回表是怎么产生的呢？

上面说过 InnoDB 引擎的**主键索引存储的是行数据，二级索引的叶子结点存储的是索引数据以及对应的主键**，所以回表**就是根据索引进行条件查询，回到主键索引树进行搜索的过程**：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CNukt0iaUsvqxLOwtAVJOw9icfwvaiaXvNatPlCGUCTbRyAuA5MDgsnDIw/640?wx_fmt=png)

因为查询还要回表一次，再次查询主键索引树，所以实际中应该尽量避免回表的产生。

### 13. 怎么解决回表的问题？

解决回表问题可以建立联合索引进行索引覆盖，如图所示根据 name 字段查询用户的 name 和 sex 属性出现了回表问题：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CNukt0iaUsvqxLOwtAVJOw9icfwvaiaXvNatPlCGUCTbRyAuA5MDgsnDIw/640?wx_fmt=png)

那么我们可以建立下面这个联合索引来解决：

```
create table user (
 id int primary key,
 name varchar(20),
 sex varchar(5),
 index(name, sex)
) engine = innodb;
```

建立了如上所示的`index(name,sex)`联合索引，在二级索引的叶子结点的位置就会同时也出现 sex 字段的值，因为能够获取到要查询的所有字段，因为就不用再回表查询一次。

### 14. 什么是最左前缀原则？

**最左前缀原则可以是联合索引的的最左 N 个字段，也可以是字符串索引的最左的 M 个字符**。举个例子，假如现在有一个表的原始数据如下所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CfFfAwCpMTHT2Yvvz58Pn6rQjCBqHYrggh2L7Accxdjoe5Q8QzdMkqw/640?wx_fmt=png)

并根据`col3 ，col2`的顺序建立**联合索引**，此时联合索引树结构如图下所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CoKpKWLIA1YcY5rRN5cDb1u7zDDP6ic5OezToDdYc6dgN1c6Rd3Vib5Ig/640?wx_fmt=png)

叶子结点中首先会根据 col3 的字符进行排序，若是 col3 相等，在 col3 相等的值里面再对 col2 进行排序，假如我们要查询`where col3 like 'Eri%'`，就可以快速的定位查询到 Eric。

若是查询条件为 where col3 like '%se'，前面的字符不确定，表示任意字符都可以，这样就可以导致全表扫描进行字符的比较，就会使索引失效。

### 15. 什么是索引下推？

Mysql5.6 之前是没有索引下推这个功能，后面为了提高性能，避免不必要的回表 5.6 之后就有了索引下推优化的功能。

假如我们有一个用户表，并且使用用户的 name，age 两个字段建立联合索引，name 在没有索引下推的功能，执行下面的 sql，执行的流程如下图所示：

```
select * from tuser where name like '张%' and age=10 and ismale=1;
```

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CcfWmTy2pCmGygWZWFx1YoukLxyIwadiaoPrOukTGyhJqC2SyqGzMp8A/640?wx_fmt=png)

当比较第一个索引字段 name like '张 %' 就会筛选出四行数据，后面它不会再比较 age 值是否符合要求，直接获取到主键值，然后在回表查询，回表后再对比 age、ismale 是否符合条件。

从上面的数据看来其实 name，age 两个字段建立的联合索引，两个字段的值会存储在联合索引树中，可以直接对比 age 字段是否符合查询的条件 age=10，那么索引下推就是做了这些事：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2C7kJ1PpJLjia2UXNqLSnR8R2PbJSzqRQHvywJ7cHbyhPZU4sdMITibvjQ/640?wx_fmt=png)

索引下推会再次根据你的 age 进行比较，发现有两条记录不符合条件直接过滤掉，符合条件的才会进行回表查询，这样就减少了不必要的回表查询。

### 16. 主键使用自增 ID 还是 UUID? 能说说原因吗？

自增 ID 和 UUID 作为主键的考虑主要有两方面，一个是**性能**另一个就是**存储的空间**大小，**一般没有特定的业务要求都不推荐使用 UUID 作为主键**。

因为使用 **UUID 作为主键插入并不能保证插入是有序的，有可能会涉及数据的挪动，也有可能触发数据页的分裂**，因为一个数据页的大小就是 16KB，这样插入数据的成本就会比较高。

而**自增 ID 作为主键的话插入数据都是追加操作，不会有数据的移动以及数据页的分裂**，性能会比较好。

另一方面就是存储空间，**自增主键一般整形只要 4 个字节，长整形才占 8 字节的大小空间**，而使用 **UUID 作为主键存储空间需要 16 字节的大小**，会占用更多的磁盘，在二级索引中也会存出一份主键索引，这样多占用消耗的空间就是两倍，性能低，所以不推荐使用。

### 17.Mysql 是怎么控制并发的访问资源？

Mysql 内部通过锁机制实现对资源的并发访问控制，保证数据的一致性，锁机制的类型和引擎的种类有关，MyISAM 中默认支持的表级锁有两种：共享读锁和独占写锁。表级锁在 MyISAM 和 InnoDB 的存储引擎中都支持，但是 InnoDB 默认支持的是行锁。

#### MyISAM 锁机制

Mysql 中可以通过以下 sql 来显示的在事务中显式的进行加锁和解锁操作：

```
// 显式的添加表级读锁
LOCK TABLE 表名 READ
// 显示的添加表级写锁
LOCK TABLE 表名 WRITE
// 显式的解锁（当一个事务commit的时候也会自动解锁）
unlock tables;
```

（1）MyISAM 表级写锁：当一个线程获取到表级写锁后，只能由该线程对表进行读写操作，别的线程必须等待该线程释放锁以后才能操作。

（2）MyISAM 表级共享读锁：当一个线程获取到表级读锁后，该线程只能读取数据不能修改数据，其它线程也只能加读锁，不能加写锁。

#### InnoDB 锁机制

InnoDB 和 MyISAM 不同的是，InnoDB 支持行锁和事务，InnoDB 中除了有表锁和行级锁的概念，还有 Gap Lock（间隙锁）、Next-key Lock 锁，间隙锁主要用于范围查询的时候，锁住查询的范围，并且间隙锁也是解决幻读的方案。

InnoDB 中的行级锁是对索引加的锁，在不通过索引查询数据的时候，InnoDB 就会使用表锁。

但是通过索引查询的时候是否使用索引，还要看 Mysql 的执行计划，Mysql 的优化器会判断是一条 sql 执行的最佳策略。

若是 Mysql 觉得执行索引查询还不如全表扫描速度快，那么 Mysql 就会使用全表扫描来查询，这是即使 sql 语句中使用了索引，最后还是执行为全表扫描，加的是表锁。

### 18.Mysql 的死锁是怎么发生的？怎么解决死锁问题？

死锁在 InnoDB 中才会出现死锁，MyISAM 是不会出现死锁，因为 MyISAM 支持的是表锁，一次性获取了所有的锁，其它的线程只能排队等候。

而 InnoDB 默认支持行锁，获取锁是分步的，并不是一次性获取所有的锁，因此在锁竞争的时候就会出现死锁的情况。

虽然 InnoDB 会出现死锁，但是并不影响 InnoDB 成为最受欢迎的存储引擎，MyISAM 可以理解为串行化操作，读写有序，因此支持的并发性能低下。

#### （1）死锁案例一：

举一个例子，现在数据库表 employee 中六条数据，如下所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2Cu5jJicJibTXKCx7ia08Uf7dexyibmIVs8LxNYIp8yUribXJa5gMuEtKXmWw/640?wx_fmt=png)

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

#### （2）死锁案例二

第二种死锁情况就是当一个事务开始并且 update 一条 id=1 的数据行时，成功获取到写锁，此时另一个事务执行也 update 另一条 id=2 的数据行时，也成功获取到写锁（id 为主键）。

此时 cpu 将时间分配给了事务一，事务一接着也是 update id=2 的数据行，因为事务二已经获取到 id=2 数据行的锁，所以事务已处于等待状态。

事务二有获取到了时间，像执行 update id=1 的数据行，但是此时 id=1 的锁被事务一获取到了，事务二也处于等待的状态，因此形成了死锁。

<table data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)"><thead data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)"><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><th data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)|rgb(240, 240, 240)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); min-width: 85px;">session1</th><th data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)|rgb(240, 240, 240)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); min-width: 85px;">session2</th></tr></thead><tbody data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)"><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">begin;update t set name=‘测试’ where id=1;</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">begin</td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;"><br data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)"></td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">update t set name=‘测试’ where id=2;</td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">update t set name=‘测试’ where id=2;</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;"><br data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)"></td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">等待…</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">update t set name=‘测试’ where id=1;</td></tr><tr data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">等待…</td><td data-darkmode-color-16334236510326="rgb(163, 163, 163)" data-darkmode-original-color-16334236510326="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16334236510326="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16334236510326="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">等待…</td></tr></tbody></table>

#### 死锁的解决方案

首先要解决死锁问题，在程序的设计上，当发现程序有高并发的访问某一个表时，尽量对该表的执行操作串行化，或者锁升级，一次性获取所有的锁资源。

然后也可以设置参数`innodb_lock_wait_timeout`，超时时间，并且将参数`innodb_deadlock_detect` 打开，当发现死锁的时候，自动回滚其中的某一个事务。

### 19. 能说一说 Mysql 的主从复制吗？

#### 读写分离

实现 MySQL 读写分离的前提是我们已经将 MySQL 主从复制配置完毕，读写分离实现方式：（1）配置多数据源。（2）使用 mysql 的 proxy 中间件代理工具。

#### 主从复制的原理

MySQL 的主从复制和读写分离两者有着紧密的联系，首先要部署主从复制，只有主从复制完成了才能在此基础上进行数据的读写分离。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlge95iaIUbQ0PAiaSbTyCmZ2CknAgCkPibkhPuh5eco10ABD2kqPROSrceCAkR5ibpgwTicIaknxKSnsfQ/640?wx_fmt=png)

#### 读写分离的原理

读写分离就是只在主服务器上写，只在从服务器上读。基本原理是让主数据库处理事务性查询，而从服务器处理 select 查询。数据库复制被用来把事务性查询导致的变更同步到从数据库中。

### 20. 能说一说分库分表吗？怎么分？

> 首先为什么要分表？（1） 如果一个表的每条记录的内容很大，那么就需要更多的 IO 操作，如果字段值比较大，而使用频率相对比较低，可以将大字段移到另一张表中，当查询不查大字段的时候，这样就减少了 I/O 操作 （2）如果表的数据量非常非常大，那么查询就变的比较慢；也就是表的数据量影响查询的性能。（3）表中的数据本来就有独立性，例如分别记录各个地区的数据或者不同时期的数据，特别是有些数据常用，而另外一些数据不常用。（4） 分表技术有 (水平分割和垂直分割)

#### 垂直分割

垂直分割是指数据表列的拆分，把一张列比较多的表拆分为多张表。垂直分割一般用于拆分大字段和访问频率低的字段，分离冷热数据。

垂直分割比较常见：例如博客系统中的文章表，比如文章 tbl_articles (id, titile, summary, content, user_id, create_time)，因为文章中的内容 content 会比较长，放在 tbl_articles 中会严重影响表的查询速度，所以将内容放到 tbl_articles_detail(article_id, content)，像文章列表只需要查询 tbl_articles 中的字段即可。

垂直拆分的优点：可以使得行数据变小，在查询时减少读取的 Block 数，减少 I/O 次数。此外，垂直分区可以简化表的结构，易于维护。

垂直拆分的缺点：主键会出现冗余，需要管理冗余列，并会引起 Join 操作，可以通过在应用层进行 Join 来解决。此外，垂直分区会让事务变得更加复杂。

#### 水平分割

水平拆分是指数据表行数据的拆分，表的行数超过 500 万行或者单表容量超过 10GB 时，查询就会变慢，这时可以把一张的表的数据拆成多张表来存放。水平分表尽可能使每张表的数据量相当，比较均匀。

水平拆分会给应用增加复杂度，它通常在查询时需要多个表名，查询所有数据需要 union 操作。在许多数据库应用中，这种复杂性会超过它带来的优点。

因为只要索引关键字不大，则在索引用于查询时，表中增加 2-3 倍数据量，查询时也就增加读一个索引层的磁盘次数，所以水平拆分要考虑数据量的增长速度，根据实际情况决定是否需要对表进行水平拆分。

水平分割最重要的是找到分割的标准，不同的表应根据业务找出不同的标准

1.  用户表可以根据用户的手机号段进行分割如 user183、user150、user153、user189 等，每个号段就是一张表。

2.  用户表也可以根据用户的 id 进行分割，加入分 3 张表 user0,user1,user2，如果用户的 id%3=0 就查询 user0 表，如果用户的 id%3=1 就查询 user1 表。

3.  对于订单表可以按照订单的时间进行分表。


#### 分库分表技术

现在市面上主要的分库分表技术有 mycat 和 sharding-jdbc，具体的分库分表的技术讲解我们留在下一次进行详细的讲解。

### 参考

《Mysql45 讲》

《数据库原理》

end



![](https://mmbiz.qpic.cn/mmbiz/cZV2hRpuAPiaJQXWGyC9wrUzIicibgXayrgibTYarT3A1yzttbtaO0JlV21wMqroGYT3QtPq2C7HMYsvicSB2p7dTBg/640?wx_fmt=gif)

****有热门推荐👇****

[万字带你入门 Netty ！高手勿入！！！](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247486926&idx=1&sn=ee08add31e803e646346eb0b08c8a492&chksm=fbf7e40ccc806d1aba39ae4798dfb564b8da45abbfa74bc24d1a138b58fbee9db9674df79333&scene=21#wechat_redirect)

[SpringMVC+Spring+Mybatis 支付宝支付功能实战（图文详解）](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247486746&idx=1&sn=1293af0eda09c322f0b1b66e10244414&chksm=fbf7e4d8cc806dcebf29cdf47a7a2eabedf6ae6875cefa24b01b0885e016e7761141b1100e31&scene=21#wechat_redirect)

[【简历加分】hexo 框架搭建个人博客站点，手把手教学。](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247486709&idx=1&sn=adfaf412e85abc28e8e8923567306552&chksm=fbf7e537cc806c21315e91c002a9eb8db971d52c6109126bc1017c62b8a811689dabbb7181d3&scene=21#wechat_redirect)

[图解 MyBatis 的 SQL 执行流程（干货）](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247486630&idx=1&sn=1a52f0bf5e074451f39287ea80bfa89f&chksm=fbf7e564cc806c729d1a59372d921a42a361ca7c41dddcf39ad1d0cf876c0f356059ad720c81&scene=21#wechat_redirect)

[牛逼，手撸一个 SpringBoot 缓存系统！](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247486624&idx=1&sn=1e0a5732ce76f01bd02bdb1183820cd5&chksm=fbf7e562cc806c74fbb3e666dba91725c3ffc1cb097c123e16d4eed3627df8a1988385fde14a&scene=21#wechat_redirect)

[3W 字的 Spring Boot 超详细总结（硬核干货）](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247486620&idx=1&sn=25c45b3eeed37e0815acee5317ffa346&chksm=fbf7e55ecc806c48911af579867660f8e4ad9d4d00dfebd7b31169682a1fbd7b2fd3a05b3bc2&scene=21#wechat_redirect)

![图片](https://mmbiz.qpic.cn/mmbiz_jpg/IJUXwBNpKlia9mJEwjmFB4mfS6cx0HcwpYUH1PPJUQg5Ru8WGqmGkgzeT0phcfUnJqMYCnx868qDATAEqX5apsg/640?wx_fmt=jpeg)

如有收获，点个在看，诚挚感谢![](https://mmbiz.qpic.cn/mmbiz_png/fEsWkVrSk54JM0xl7WLNGnqqwbcXQBWIOCMuOgPHRoOjMamXzrCqMS2xgOo5ibic9icPk7u3zsVgic3s1ZUYJ4xHXg/640?wx_fmt=png)