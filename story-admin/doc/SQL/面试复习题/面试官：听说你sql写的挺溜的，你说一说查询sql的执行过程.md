> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483958&idx=1&sn=9ec13738865607910143ec9db8e9bb38&scene=21#wechat_redirect)

![](https://mmbiz.qpic.cn/mmbiz_jpg/IJUXwBNpKlgxA0d2BJwVrnKAAGoN8y1ricPvgNlS8PXvcq1gyDmcvWe98866Q0oTUSsHrdoPiakEIXic9wVWOAP9g/640?wx_fmt=jpeg)  
当希望 Mysql 能够高效的执行的时候，最好的办法就是清楚的了解 Mysql 是如何执行查询的，只有更加全面的了解 SQL 执行的每一个过程，才能更好的进行 SQl 的优化。

当执行一条查询的 SQl 的时候大概发生了一下的步骤：

1.  客户端发送查询语句给服务器。

2.  服务器首先检查缓存中是否存在该查询，若存在，返回缓存中存在的结果。若是不存在就进行下一步。

3.  服务器进行 SQl 的解析、语法检测和预处理，再由优化器生成对应的执行计划。

4.  Mysql 的执行器根据优化器生成的执行计划执行，调用存储引擎的接口进行查询。

5.  服务器将查询的结果返回客户端。


Mysql 的执行的流程图如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgxA0d2BJwVrnKAAGoN8y1rG19dsdTPNdTu1nokybShH4oibicyoDw6DKhXFLLFJ55rvIpQlwLU3hcA/640?wx_fmt=png)  
这里以一个实例进行说明 Mysql 的的执行过程，新建一个 User 表，如下：



```
// 新建一个表
DROP TABLE IF EXISTS User;
CREATE TABLE `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `age` int DEFAULT 0,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `dept` int,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

// 并初始化数据，如下
INSERT INTO User(name,age,address,phone,dept)VALUES('张三',24,'北京','13265543552',2);
INSERT INTO User(name,age,address,phone,dept)VALUES('张三三',20,'北京','13265543557',2);
INSERT INTO User(name,age,address,phone,dept)VALUES('李四',23,'上海','13265543553',2);
INSERT INTO User(name,age,address,phone,dept)VALUES('李四四',21,'上海','13265543556',2);
INSERT INTO User(name,age,address,phone,dept)VALUES('王五',27,'广州','13265543558',3);
INSERT INTO User(name,age,address,phone,dept)VALUES('王五五',26,'广州','13265543559',3);
INSERT INTO User(name,age,address,phone,dept)VALUES('赵六',25,'深圳','13265543550',3);
INSERT INTO User(name,age,address,phone,dept)VALUES('赵六六',28,'广州','13265543561',3);
INSERT INTO User(name,age,address,phone,dept)VALUES('七七',29,'广州','13265543562',4);
INSERT INTO User(name,age,address,phone,dept)VALUES('八八',23,'广州','13265543563',4);
INSERT INTO User(name,age,address,phone,dept)VALUES('九九',24,'广州','13265543564',4);
```

现在针对这个表发出一条 SQl 查询：`查询每个部门中25岁以下的员工个数大于3的员工个数和部门编号，并按照人工个数降序排序和部门编号升序排序的前两个部门。`

```
SELECT dept,COUNT(phone) AS num FROM User WHERE age< 25 GROUP BY dept HAVING num >= 3 ORDER BY num DESC,dept ASC LIMIT 0,2;
```

**执行连接器**

开始执行这条 sql 时，会检查该语句是否有权限，若是没有权限就直接返回错误信息，有权限会进行下一步，校验权限的这一步是在图一的连接器进行的，对连接用户权限的校验。

**执行检索内存**

相连建立之后，履行查询语句的时候，会先行检索内存，Mysql 会先行冗余这个 sql 与否履行过，以此`Key-Value`的形式平缓适用内存中，Key 是`检索预定`，Value 是`结果集`。

假如内存 key 遭击中，便会间接回到给客户端，假如没命中，便会履行后续的操作，完工之后亦会将结果内存上去，当下一次进行查询的时候也是如此的循环操作。

**执行分析器**

分析器主要有两步：（1）`词法分析`（2）`语法分析`

词法分析主要执行`提炼关键性字`，比如 select，`提交检索的表`，`提交字段名`，`提交检索条件`。语法分析主要执行辨别你`输出的sql与否准确`，是否`合乎mysql的语法`。

当 Mysql 没有命中内存的时候，接着执行的是 FROM student 负责把数据库的表文件加载到内存中去，`WHERE age< 60`，会把所示表中的数据进行过滤，取出符合条件的记录行，生成一张临时表，如下图所示。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgxA0d2BJwVrnKAAGoN8y1rxQHJVEfIbvw2dfpH6M9cDAgNibSwM5SpictOHRuiabMtzib0gZxq9qKN5w/640?wx_fmt=png)  
`GROUP BY dept` 会把上图的临时表分成若干临时表，切分的过程如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgxA0d2BJwVrnKAAGoN8y1raICExbrT26iajc9HrTACIcKEIfHayXiaU5HcfVCpfa57lHuNo4N9icTxA/640?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgxA0d2BJwVrnKAAGoN8y1rpnppUKwwVxXUpsjKmLSEJlicNKcBDxnExgSTuf90iaOUp1icicCia3FHP0A/640?wx_fmt=png)  
查询的结果只有部门 2 和部门 3 才有符合条件的值，生成如上两图的临时表。接着执行`SELECT后面的字段`，SELECT 后面可以是`表字段`也可以是`聚合函数`。

这里 SELECT 的情况与是否存在`GROUP BY`有关，若是不存在 Mysql 直接按照上图内存中整列读取。若是存在分别 SELECT 临时表的数据。

最后生成的临时表如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgxA0d2BJwVrnKAAGoN8y1rDalYpEEOJX7lhjLe2vd2sVpD6FUIRL1icCEY1j4eibM6ZGibPVJHpegoA/640?wx_fmt=png)  
紧接着执行`HAVING num>2`过滤员工数小于等于 2 的部门，对于`WHERE`和`HAVING`都是进行过滤，那么这两者有什么不同呢？

第一点是 WHERE 后面只能对表字段进行过滤，不能使用聚合函数，而 HAVING 可以过滤表字段也可以使用聚合函数进行过滤。

第二点是 WHERE 是对执行 from USer 操作后，加载表数据到内存后，WHERE 是对`原生表的字段`进行过滤，而 HAVING 是对`SELECT后的字段进行过滤`，也就是 WHERE`不能使用别名进行过滤`。

因为执行 WHERE 的时候，还没有 SELECT，还没有给字段赋予别名。接着生成的临时表如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgxA0d2BJwVrnKAAGoN8y1rgnFJh9ARWTKSYS7PYhN4icrSMPC0ual4tnKrcE08NiadEHq80gAwsREg/640?wx_fmt=png)  
最后在执行`ORDER BY后面的排序`以及`limit0,2`取得前两个数据，因为这里数据比较少，没有体现出来。最后生成的结果也是如上图所示。接着判断这个 sql 语句`是否有语法错误`，`关键性词与否准确`等等。

**执行优化器**

查询优化器会将解析树转化成执行计划。一条查询可以有多种执行方法，最后都是返回相同结果。优化器的作用就是找到这其中`最好的执行计划`。

生成执行计划的过程会消耗较多的时间，特别是存在许多可选的执行计划时。如果在一条 SQL 语句执行的过程中将该语句对应的最终执行计划进行缓存。

当`相似的语句`再次被输入服务器时，就可以直接`使用已缓存的执行计划`，从而跳过 SQL 语句生成执行计划的整个过程，进而可以提高语句的执行速度。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgxA0d2BJwVrnKAAGoN8y1rlZibDIXzy4rwiaFDKGvY1hUNXN1ibAU3YLVIe5wsp1KDtc2AqRibicJgcOA/640?wx_fmt=png)  
MySQL 使用基于成本的查询优化器。它会尝试预测一个查询使用某种执行计划时的成本，并选择其中成本最少的一个。

**执行执行器**

由优化器生成得执行计划，交由执行器进行执行，执行器调用存储引擎得接口，存储引擎获取数据并返回，结束整个查询得过程。

这里之讲解了 select 的过程，对于 update 这些修改数据或者删除数据的操作，会涉及到事务，会使用两个日志模块，redo log 和 binlog 日志。具体对这两个日志的介绍请看着一篇文章 [[深入 mysql 的 innodb 存储引擎之文件结构](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483943&idx=1&sn=e1564312d22eb52e537fa77ab8807dbf&chksm=fbf7ebe5cc8062f38ec6cac2eb9f0d60a148919a93be5216cc6f9029fe47608fb71b45f06173&scene=21#wechat_redirect)]。

以前的 Mysql 的默认存储引擎 MyISAM 引擎是没 redo log 的，而现在的默认存储引擎 InnoDB 引擎便是透过 redo 复杂度来拥护事务的，保证事务能够准确的回滚或者提交，保证事务的 ACID。

![](https://mmbiz.qpic.cn/mmbiz_jpg/IJUXwBNpKlgxA0d2BJwVrnKAAGoN8y1rlJGOyhMY3Pf850wY6UITXBAlpGLEibvQD4v2roCZ7Fb3EtSrHL6W2kw/640?wx_fmt=jpeg)

_**[往期精彩回顾]**_

[[万字长文，一文搞懂 TCP/IP 和 HTTP、HTTPS]](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483880&idx=1&sn=b4b71f169dddc41925814ab4e5bc208b&chksm=fbf7e82acc80613c2f9d2e11aba576dd5d367140ab74c33f6b79e0e6a3cbb84e388a50fded7d&scene=21#wechat_redirect)

[[Mysql 优化提高笔记整理，来自于一位鹅厂大佬的笔记](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483938&idx=1&sn=3d97fd3aa5601834138b33e3dddfc544&chksm=fbf7ebe0cc8062f69320ea0b10d92bea8c123801cd15fa1870c91ad29e62c503185e46111716&scene=21#wechat_redirect)] 

[[B 树、B - 树、B + 树、B * 树图文详解]](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483924&idx=1&sn=54f8069ebf3e289c9d05c56c839d57ff&chksm=fbf7ebd6cc8062c0acdbaaadd09bca7a01e050cbbbd8f281b95d6c32db77203cea8266d5715f&scene=21#wechat_redirect)

[[万字长文，最硬核的 mysql 知识总结]](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483886&idx=2&sn=3b9a0dde6fa9b06f3fba6a0aeca83820&chksm=fbf7e82ccc80613ab4eeda8ced91d6b6b6c8143693e864711de395f112a7f1cdef257ec26ecb&scene=21#wechat_redirect)

[[为了把 mysql 的索引底层原理讲清楚，我把计算机翻了个底朝天]](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483934&idx=1&sn=e95a47a8255ac4a814a53afbc54e513a&chksm=fbf7ebdccc8062cab93bec95d884c8a11f6fb054caa3b1b8e4c6b6ee466944ff2a8b0affd695&scene=21#wechat_redirect)

[[还在学 JVM？我都帮你总结好了（附脑图）](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483948&idx=1&sn=ee9b3ad4dd1dadd174984d1cedb5760a&chksm=fbf7ebeecc8062f8dfe2df001d0f82c4b24f03c6523b0ac5cb15466866c01e29d570ce26c6fb&scene=21#wechat_redirect)]

[[又出事了？网站被攻击了？高中生？](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483953&idx=1&sn=ed571975237e362c65f7121b77e3ca3f&chksm=fbf7ebf3cc8062e5209f02562860de610ec9c8d074ff6d8a7b38d1a408812e490712ac22ac47&scene=21#wechat_redirect)]