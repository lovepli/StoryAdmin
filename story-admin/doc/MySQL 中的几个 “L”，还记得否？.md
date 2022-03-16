> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/raFHYDaPiBC9dpwfRv3OKw)

大家好，我是松哥，今天给大家分享：**MySQL 中的几个 “L”，还记得否？**

在我们刚刚开始学数据库知识的时候，首先会接触到各种 **L**，这里的 L 也就是语言 language 的首字母。

大致有如下几种：

*   DQL

*   DML

*   DCL

*   DDL

*   TCL


下面，松哥就带大家逐个攻破。

DQL
---

指`select`查询语句 (`Data query language`)，基本结构是由 `select`子句，`from`子句，`where`等。

### 单表查询

```
-- 格式
SELECT selection_list /*要查询的列名称*/
  FROM table_list /*要查询的表名称*/
  WHERE condition /*行条件*/
  GROUP BY grouping_columns /*对结果分组*/
  HAVING condition /*分组后的行条件*/
  ORDER BY sorting_columns /*对结果排序*/
  LIMIT offset_start, row_count /*结果限定*/
```

### 按条件查询指定列

```
SELECT t_man.Mname,t_man.Mage FROM t_man WHERE t_man.Mage > 30

-- --------------查询条件----------------------------

-- 1.逻辑运算符
-- NOT : 取反
WHERE NOT t_man.Mage > 30
-- AND : 逻辑与
WHERE t_man.Mage > 30 AND t_man.Mname LIKE '_'
-- OR : 逻辑或
WHERE t_man.Mage > 30 OR t_man.Mname LIKE '_'

-- 2.比较运算符
=、<>、!=、>、>=、!>、<、<=、!<

-- 3.LIKE，用于模糊查询
-- % : 后面可以跟零个或多个字符
-- _ : 匹配任意单个字符
-- [ ] : 查询一定范围内的单个字符，包括两端数据
WHERE t_man.Mname LIKE '[周李]%'
-- [^] [!]: 表示不在一定范围内的单个字符，包括两端数据

-- 4.BETWEEN
between xx and xx
WHERE t_man.Mage BETWEEN 30 AND 31 (等同于 t_man.Mage>=30 AND t_man.Mage<=31)
not between xx and xx

-- 5.is (not) null
-- 在 where 子句中，需要用 is (not) null 判断空值，不能使用 = 判断空值
WHERE t_man.Mage is not null

-- 6.in 多条件
WHERE t_man.Mage IN (30,31)

-- 7.ALL SOME ANY
-- Some 和 any 等效，all 是大于最大者，any 是小于最小者
WHERE t_man.Mage > ALL(SELECT t_man.Mage FROM t_man WHERE t_man.Mname LIKE '张%')

-- 8.exists 和 no exists
WHERE exists (select * from t_man where t_man.Mid = 8001)

-- 9.Group by 分组
SELECT AVG(t_man.Mage) FROM t_man GROUP BY t_man.Msex

-- 10.Having 分组后条件
SELECT AVG(t_man.Mage) AS mk,t_man.Msex FROM t_man GROUP BY t_man.Msex HAVING mk > 30

-- 11.ORDER BY 排序 ASC,DESC
SELECT * FROM t_man ORDER BY t_man.Mid ASC

-- 12.DISTINCT 去重
SELECT DISTINCT(t_man.Msex) FROM t_man

-- 13.LIMIT 分页（显示第一行数据）
SELECT * FROM t_man LIMIT 0,1
```

### 连接查询

```
-- 交叉连接（Cross Join），没有链接条件的表查询会出现笛卡儿积
SELECT * FROM t_man,t_dept
SELECT * FROM t_man JOIN t_dept

-- 内连接（inner Join 或 Join），两表中都有才显示，即两表的交集
SELECT * FROM t_man JOIN t_dept ON t_man.Mid = t_dept.Mid

-- 左外连接（Left outer Join），以左边表为主，左表全部显示，没有对应的就显示空，即左并集
SELECT * FROM t_man LEFT JOIN t_dept ON t_man.Mid = t_dept.Mid

-- 右外连接（Right outer Join），与左外连接相反
SELECT * FROM t_man RIGHT JOIN t_dept ON t_man.Mid = t_dept.Mid

-- 全连接（Full outer Join），默认不支持，但也其他方式可以实现。
SELECT * FROM t_man LEFT JOIN t_dept ON t_man.Mid = t_dept.Mid
UNION
SELECT * FROM t_man RIGHT JOIN t_dept ON t_man.Mid = t_dept.Mid

-- UNION ALL 与 UNION 区别是允许重复
```

用图来表示更加形象化：

![](https://mmbiz.qpic.cn/mmbiz_png/f0OuP64tHwYkcFj8F3DJatGraahnRwRc3amCb53wcia53CMHJttmyNibPh8icFY7L6REiaX5X8lsXj1p3QOoBQUquw/640?wx_fmt=png)



DML
---

DML 是指数据操作语言，英文全称是`Data Manipulation Language`，用来对数据库中表的记录进行更新。关键字：插入 insert，删除 delete，更新 update 等，是对数据进行操作。

### insert

insert 插入

```
insert into 表 (列名1,列名2,列名3...) values (值1,值2,值3...)； //向表中插入某些列
insert into 表 values (值1,值2,值3...);  //向表中插入所有列
```

注意：

> 1.  列名数与 values 后面的值的个数相等
>
> 2.  列的顺序与插入的值的顺序一致
>
> 3.  列名的类型与插入的值要一致
>
> 4.  插入值的时候不能超过最大长度
>
> 5.  值如果是字符串或者日期需要加引号’’
>

### update

update 更新数据

```
update 表名 set 字段名=值,字段名=值...;
update 表名 set 字段名=值,字段名=值... where 条件;
```

注意：

> 1.  列名的类型与修改的值要一致
>
> 2.  修改值的时候不能超过最大长度
>
> 3.  值如果是字符串或者日期要加’’
>

### delete

delete 删除数据

```
delete from 表名 [where 条件];
```

注意：

> 删除表中所有记录使用 delete from 表名；还是 truncate table 表名；？删除方式：delete 一条一条的删除，不清空 auto_increment（自增）记录数；truncate 直接删除表，重新建表，auto_increment 讲置为 0，重新开始。事务方面：delete 删除的数据，如果在一个事务中可以找回；truncate 删除的数据找不回来。

DCL
---

DCL 是**数据控制语言**（`Data Control Language`）的简称，它包含诸如`GRANT`之类的命令，并且主要涉及数据库系统的权限，权限和其他控件。

*   `GRANT` ：允许用户访问数据库的权限

*   `REVOKE`：撤消用户使用 GRANT 命令赋予的访问权限


DCL 语句主要是 DBA 用来管理系统中的对象权限时所使用，一般的开发人员很少使用。

### 案例演示

下面 通过一个例子来简单说明一下。创建一个数据库用户 plf，具有对 plf 数据库中所有表的 SELECT/INSERT 权限：

```
mysql> grant select,insert on plf.* to 'plf'@'%' identified by '123456';
Query OK, 0 rows affected (0.00 sec)
mysql> flush privileges;
Query OK, 0 rows affected (0.00 sec)

mysql> quit
Bye



[root@mysql ~]# mysql -uplf -p123456 -h 192.168.3.100
Warning: Using a password on the command line interface can be insecure.
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 7
Server version: 5.6.37 Source distribution

Copyright (c) 2000, 2017, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> use mysql;
ERROR 1044 (42000): Access denied for user 'plf'@'%' to database 'mysql'
mysql> use plf
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
```

由于权限变更，需要将 plf 的权限变更，收回 INSERT，只能对数据进行 SELECT 操作, 这时我们需要使用 root 账户进行上述操作：

```
mysql> revoke insert on plf.* from 'plf'@'%';
Query OK, 0 rows affected (0.00 sec)

mysql> quit
Bye





[root@mysql ~]# mysql -uplf -p123456 -h 192.168.3.100
Warning: Using a password on the command line interface can be insecure.
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 9
Server version: 5.6.37 Source distribution

Copyright (c) 2000, 2017, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> use plf
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
mysql> show tables;
+---------------+
| Tables_in_plf |
+---------------+
| dept          |
| emp           |
| hk_info       |
| log_info      |
| user_info     |
+---------------+
5 rows in set (0.00 sec)

mysql> insert into dept values(7,'plf');
ERROR 1142 (42000): INSERT command denied to user 'plf'@'192.168.3.100' for table 'dept'
mysql> select*from dept;
+--------+----------+
| deptno | deptname |
+--------+----------+
|      1 | tech     |
|      2 | sale     |
|      3 | hr       |
|      5 | fin      |
+--------+----------+
4 rows in set (0.00 sec)
```

以上例子中的 grant 和 revoke 分别授出和收回了用户 plf 的部分权限，达到了我们的目的。

DDL
---

DDL 是数据定义语言（`Data Definition Language`）的简称，它处理数据库`schemas`和描述数据应如何驻留在数据库中。

*   CREATE：创建数据库及其对象（如表，索引，视图，存储过程，函数和触发器）

*   ALTER：改变现有数据库的结构

*   DROP：从数据库中删除对象

*   TRUNCATE：从表中删除所有记录，包括为记录分配的所有空间都将被删除

*   COMMENT：添加注释

*   RENAME：重命名对象


常用命令如下：

```
# 建表
CREATE TABLE sicimike  (
  id int(4) primary key auto_increment COMMENT '主键ID',
  name varchar(10) unique,
  age int(3) default 0,
  identity_card varchar(18)
  # PRIMARY KEY (id) // 也可以通过这种方式设置主键
  # UNIQUE KEY (name) // 也可以通过这种方式设置唯一键
  # key/index (identity_card, col1...) // 也可以通过这种方式创建索引
) ENGINE = InnoDB;

# 设置主键
alter table sicimike add primary key(id);

# 删除主键
alter table sicimike drop primary key;

# 设置唯一键
alter table sicimike add unique key(column_name);

# 删除唯一键
alter table sicimike drop index column_name;

# 创建索引
alter table sicimike add [unique/fulltext/spatial] index/key index_name (identity_card[(len)] [asc/desc])[using btree/hash]
create [unique/fulltext/spatial] index index_name on sicimike(identity_card[(len)] [asc/desc])[using btree/hash]
example： alter table sicimike add index idx_na(name, age);

# 删除索引
alter table sicimike drop key/index identity_card;
drop index index_name on sicimike;

# 查看索引
show index from sicimike;

# 查看列
desc sicimike;

# 新增列
alter table sicimike add column column_name varchar(30);

# 删除列
alter table sicimike drop column column_name;

# 修改列名
alter table sicimike change column_name new_name varchar(30);

# 修改列属性
alter table sicimike modify column_name varchar(22);

# 查看建表信息
show create table sicimike;

# 添加表注释
alter table sicimike comment '表注释';

# 添加字段注释
alter table sicimike modify column column_name varchar(10) comment '姓名';
```

### TCL

TCL 是**事务控制语言**（`Transaction Control Language`）的简称，用于处理数据库中的事务

*   `COMMIT`：提交事务

*   `ROLLBACK`：在发生任何错误的情况下回滚事务


### 事务的概念

事务是由一条或多条 SQL 语句组成的一个执行单元，这个单元作为一个不可分割的执行整体，要么全部执行成功，要么全部执行失败。若其中有一条执行失败则事务会回滚到事务开始之前的状态。事务有四个属性 (ACID)：原子性、一致性、隔离性、持久性

*   原子性 (Atomicity)：指事务是一个不可分割的整体，事务中的操作要么都执行，要么都不执行。

*   一致性 (Consistency)：事务必须是数据库从一个一致性状态变为另一个一致性状态。

*   隔离性 (Isolation)：指一个事务的执行不能被其他事务干扰，即一个事务内部的操作及其使用的数据对并发的其他事务是隔离的，并发执行的各个事务之前不能相互干扰。这个在高并发环境下尤为重要，事务隔离的程度跟选择的隔离级别有很大关系。

*   持久性：一个事务一旦提交，对数据库中数据的改变就是永久性的。


注意：

> 事务仅对数据的增删改操作有效，对于表的定义、表结构的变化等是没有事务的概念的。

### 存储引擎和事务

存储引擎是指数据存储时使用的不同的技术，并不是所有的存储引擎都支持事务，Mysql 中常用的存储引擎中只有 InnoDB 是支持事务的，MyIsam 和 Memory 则不支持事务。

Mysql 中查看存储引擎的方式：`show engines;`

![](https://mmbiz.qpic.cn/mmbiz_png/f0OuP64tHwYkcFj8F3DJatGraahnRwRcsMMcicYmEYgLziceteYlr7oicO6Biag1Yb958rC9peG9HlAqg8k9xJQicvw/640?wx_fmt=png)

在这里插入图片描述

### 事务的分类

事务有隐式事务和显示事务之分，区别在于有没有显式的事务开启和结束标记。

默认的 INSERT、UPDATE、DELETE 语句都是会自动提交的，事务提交后是不能够回滚的。这跟数据库的 autocommit 属性的设置有关，默认情况下都是开启自动提交的。

可通过`SHOW VARIABLES;`查看数据库的属性设置。

![](https://mmbiz.qpic.cn/mmbiz_png/f0OuP64tHwYkcFj8F3DJatGraahnRwRcNMDqHUI7jsl4eBCia3g5ZtrAU75IvvPW1kcHTWORFiaat3czM6kVWy9g/640?wx_fmt=png)

img

隐式事务举例：默认情况下的增删改

```
DELETE FROM table_a WHERE id = 1;
```

显示事务：在开启显示事务之前一定要关闭事务的自动提交

```
#关闭事务的自动提交
SET autocommit = 0;#该语句仅对当前事务有效
```

显示事务举例：具有明显的事物开启和结束标记，事务结束要么提交要么回滚

```
#开启事务
SET autocommit = 0;#有了这句代码就会开启事务
START TRANSACTION;#这句代码可省略
#事务的执行语句
UPDATE account
SET balance = 500
WHERE
 username = '张无忌';
UPDATE account
SET balance = 1500
WHERE
 username = '赵敏';
#事务提交
COMMIT;
#ROLLBACK;#事务回滚
```

### 事务的隔离级别

对于并发运行的多个事务，当这些事务访问相同的数据时，如果没有采取必要的隔离机制，就会导致各种并发问题：

*   脏读：对于两个事务 T1、T2，T1 读取了已经被 T2 更新但还没有提交的数据之后，T2 回滚了，则 T1 读取的数据就是无效的，这就是脏读。

*   不可重复读：对于两个事务 T1、T2，T1 读取数据后，T2 更新了 T1 读取的数据，此时 T1 再次读取该数据时会和前一次不一样，这称为不可重复读。

*   幻读：对于两个事物 T1、T2，T1 读取一个表中的数据后，T2 向该表中插入了新的数据，T1 再次读取这个表时发现多出了新的数据，这称为幻读。


事务的隔离级别：一个事务与其他事务隔离的程度称为隔离级别，数据库中有多种隔离级别，不同的隔离级别对应不同的干扰程度，隔离级别越高，数据一致性就越好，但并发性越弱。

数据库的 4 种事务隔离级别：

![](https://mmbiz.qpic.cn/mmbiz_png/f0OuP64tHwYkcFj8F3DJatGraahnRwRcpxSiaTZmapweobKo8c9kyM4eXpaRGibEmZjnPMRcqduMAvaiat96s0mrg/640?wx_fmt=png)



注意：

> 可重复读是行锁，行锁只能保证事务中被操作的数据行不能被其他事务修改，但是无法保证其他事务向该表中插入新数据，因此无法解决幻读的问题；串行化则是表锁，表锁是说在一个事务中一旦操作了某个表，则在该事务提交或回滚之前所有对该表的增删改查操作都将等待，因此可以解决幻读的问题，但是效率低下。
>
> Oracle 支持 READ COMMITTED 和 SERIALIZABLE 两种隔离级别，默认为 READ COMMITTED；Mysql 支持上述 4 中事务隔离级别，默认为 REPEATABLE READ(可重复读)

查看隔离级别：`SELECT @@tx_isolation;`

设置隔离级别：

```
#设置当前连接的隔离级别:SESSION可以省略
SET [SESSION] TRANSACTION ISOLATION LEVEL 隔离级别;
#设置全局的隔离级别
SET GLOBAL TRANSACTION ISOLATION LEVEL 隔离级别;
```

### 事务的保存点

事务的保存点 (SAVEPIONT) 其实就是指定事务可回滚到的一个节点，有时我们并不一定要将整个事务的所有操作都进行回滚，而是只回滚该事务中的某一部分操作，这个时候就需要用到事务的保存点，一个事务中可以有多个保存点，多个保存点间根据名称区分。

```
SET autocommit = 0;
START TRANSACTION;
DELETE FROM account WHERE id = 2;
SAVEPOINT a; #设置一个保存点
DELETE FROM account WHERE id = 3;
ROLLBACK TO a; #回滚到a保存点
```

好了，以上便是今天分享的 MySQL 中的各种 L（Language）。

### 参考

> https://blog.csdn.net/rubulai/article/details/94663325、https://www.cnblogs.com/plf-Jack/p/11117712.htmlhttps://blog.csdn.net/DR_eamMer/article/details/85540578

好了，今天的分享就到这里了，记得关注松哥哟！

公众号

END
---

最近面试 BAT，整理一份面试资料《Java 面试 BAT 通关手册》，覆盖了 Java 核心技术、JVM、Java 并发、SSM、微服务、数据库、数据结构等等。在这里，我为大家准备了一份 2021 年最新最全 BAT 等大厂 Java 面试经验总结。
------------------------------------------------------------------------------------------------------------------------------

#### 

```
别找了，想获取史上最全的Java大厂面试题学习资料

扫下方二维码回复「手册」就好了

其他福利

Spring Cloud Alibaba视频教程


如何搞定并发编程，源码+课件
```