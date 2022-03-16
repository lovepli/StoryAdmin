> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/wYM6yL-hWza7bxQgm30qVA)

本篇是 MySQL 知识体系总结系列的第二篇，该篇的主要内容是通过 explain 逐步分析 sql，并通过修改 sql 语句与建立索引的方式对 sql 语句进行调优，也可以通过查看日志的方式，了解 sql 的执行情况，还介绍了 MySQL 数据库的行锁和表锁。

一、explain 返回列简介
---------------

### 1、type 常用关键字

system > const > eq_ref > ref > range > index > all。

1.  system：表仅有一行，基本用不到；

2.  const：表最多一行数据配合，主键查询时触发较多；

3.  eq_ref：对于每个来自于前面的表的行组合，从该表中读取一行。这可能是最好的联接类型，除了 const 类型；

4.  ref：对于每个来自于前面的表的行组合，所有有匹配索引值的行将从这张表中读取；

5.  range：只检索给定范围的行，使用一个索引来选择行。当使用 =、<>、>、>=、<、<=、IS NULL、<=>、BETWEEN 或者 IN 操作符，用常量比较关键字列时，可以使用 range；

6.  index：该联接类型与 ALL 相同，除了只有索引树被扫描。这通常比 ALL 快，因为索引文件通常比数据文件小；

7.  all：全表扫描；


实际 sql 优化中，最后达到 ref 或 range 级别。

### 2、Extra 常用关键字

Using index：只从索引树中获取信息，而不需要回表查询；

Using where：WHERE 子句用于限制哪一个行匹配下一个表或发送到客户。除非你专门从表中索取或检查所有行，如果 Extra 值不为 Using where 并且表联接类型为 ALL 或 index，查询可能会有一些错误。需要回表查询。

Using temporary：mysql 常建一个临时表来容纳结果，典型情况如查询包含可以按不同情况列出列的 GROUP BY 和 ORDER BY 子句时；

二、触发索引代码实例
----------

### 1、建表语句 + 联合索引

```
CREATE TABLE `student` (
  `id` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `age` int(10) NOT NULL,
  `sex` int(11) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_union_index` (`name`,`age`,`sex`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

### 2、使用主键查询

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQkHl3dDZnX75m0vq9RhDawibzyTqjX3oncPWpGOe5cpotibook7voibqFQ/640?wx_fmt=png)

### 3、使用联合索引查询

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQwXBJkrZAtEGnRbTtBG4PNXYCm609lohibVzs61emGaQ3HuZGZ5wbFiaQ/640?wx_fmt=png)

### 4、联合索引，但与索引顺序不一致

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQCAVwQuF0Gt2XDUEicicia3ibyYick9VZKebibssV3lzVHBsicJgL9cB7MWU5g/640?wx_fmt=png)

备注：因为 mysql 优化器的缘故，与索引顺序不一致，也会触发索引，但实际项目中尽量顺序一致。

### 5、联合索引，但其中一个条件是 >

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQwXBJkrZAtEGnRbTtBG4PNXYCm609lohibVzs61emGaQ3HuZGZ5wbFiaQ/640?wx_fmt=png)

### 6、联合索引，order by

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQfs3AIKEOJPialqJXsBbxjXHtqqjKNZwBysr3dtP6eKO9Klb3icQzh5rg/640?wx_fmt=png)

where 和 order by 一起使用时，不要跨索引列使用。

三、单表 sql 优化
-----------

### 1、删除 student 表中的联合索引。

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQNvwTIXAHX2CqyX4w6ZvNFBXyWfdrHZN9LxcrfIAmOJGb2kWo1oL4CQ/640?wx_fmt=png)

2、添加索引

```
alter table student add index student_union_index(name,age,sex);
```

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQgBVYJOL0cfiameNzslcuDeibpicP1ER9Z4Mk0PTibAm3kawJKnmNuDD2AQ/640?wx_fmt=png)

优化一点，但效果不是很好，因为 type 是 index 类型，extra 中依然存在 using where。

### 3、更改索引顺序

因为 sql 的编写过程

```
select distinct ... from ... join ... on ... where ... group by ... having ... order by ... limit ...
```

解析过程

```
from ... on ... join ... where ... group by ... having ... select distinct ... order by ... limit ...
```

因此我怀疑是联合索引建的顺序问题，导致触发索引的效果不好。are you sure？试一下就知道了。

```
alter table student add index student_union_index2(age,sex,name);
```

删除旧的不用的索引：

```
drop index student_union_index on student
```

索引改名

```
ALTER TABLE student RENAME INDEX student_union_index2 TO student_union_index
```

更改索引顺序之后，发现 type 级别发生了变化，由 index 变为了 range。

range：只检索给定范围的行，使用一个索引来选择行。

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQpf5XFozFKwSa1CvLxqzt4e8uE5lB3flgd4IofX7rYvIibKXFTzEMNmQ/640?wx_fmt=png)

备注：in 会导致索引失效，所以触发 using where，进而导致回表查询。

### 4、去掉 in

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQmuT8rbuS0eQz3jZ3qEsIhMaxic0PU5WtXJwbKNEjEk9lqvbpvYN01cA/640?wx_fmt=png)

ref：对于每个来自于前面的表的行组合，所有有匹配索引值的行将从这张表中读取；

index 提升为 ref 了，优化到此结束。

### 5、小结

1.  保持索引的定义和使用顺序一致性；

2.  索引需要逐步优化，不要总想着一口吃成胖子；

3.  将含 in 的范围查询，放到 where 条件的最后，防止索引失效；


四、双表 sql 优化
-----------

### 1、建表语句

```
CREATE TABLE `student` (
  `id` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `age` int(10) NOT NULL,
  `sex` int(11) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

```
CREATE TABLE `teacher` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `course` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

### 2、左连接查询 

```
explain select s.name,t.name from student s left join teacher t on s.teacher_id = t.id where t.course = '数学'
```

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQhlDqSof18BZaDI48Rviaib6C7Z5jIia1iciclsQQEwuqmH0LRueHhj3NEeg/640?wx_fmt=png)

联合查询时，小表驱动大表。小表也称为驱动表。其实就相当于双重 for 循环，小表就是外循环，第二张表（大表）就是内循环。

虽然最终的循环结果都是一样的，都是循环一样的次数，但是对于双重循环来说，一般建议将数据量小的循环放外层，数据量大的放内层，这是编程语言的优化原则。

再次代码测试：

student 数据：四条

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQAjW9SNjHENo6fOUcFYOICjo73tYthkwTveVghqOnmVrjOcgQTHkOlg/640?wx_fmt=png)

teacher 数据：三条

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQPUtd9Joz9zHEM9hdJVGJ5SPWicgcfKOVkM4wNT3Iic2Acqc6hWbcgUOg/640?wx_fmt=png)

按照理论分析，teacher 应该为驱动表。

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQ3oiaibh3yqj9P3BAx5JLKphlHvfia2vxCRkj1CyqV3JMzIJNLYbZWWf4Q/640?wx_fmt=png)

sql 语句应该改为：

```
explain select teacher.name,student.name from teacher left join student on teacher.id = student.id  where teacher.course = '数学'
```

优化一般是需要索引的，那么此时，索引应该怎么加呢？往哪个表上加索引？

索引的基本理念是：索引要建在经常使用的字段上。

由 on teacher.id = student.id 可知，teacher 表的 id 字段使用较为频繁。

left join on，一般给左表加索引；因为是驱动表嘛。

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQN3jjy8fxsOV14rTIpwn6iaOTubQb4oa1TvyVzxbSvPDRbqDeWLh4tEw/640?wx_fmt=png)

```
alter table teacher add index teacher_index(id);
alter table teacher add index teacher_course(course);
```

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQxNic3icd3ictY9ic5zN7ic9ibNliaPib3flEll6GzmTE1iadwN2lJjd2XfUUL2w/640?wx_fmt=png)

备注：如果 extra 中出现 using join buffer，表明 mysql 底层觉得 sql 写的太差了，mysql 加了个缓存，进行优化了。

### 3、小结

1.  小表驱动大表

2.  索引建立在经常查询的字段上

3.  sql 优化，是一种概率层面的优化，是否实际使用了我们的优化，需要通过 explain 推测。


五、避免索引失效的一些原则
-------------

1、复合索引，不要跨列或无序使用（最佳左前缀）；

2、符合索引，尽量使用全索引匹配；

3、不要在索引上进行任何操作，例如对索引进行（计算、函数、类型转换），索引失效；

4、复合索引不能使用不等于（!= 或 <>）或 is null（is not null），否则索引失效；

5、尽量使用覆盖索引（using index）；

6、like 尽量以常量开头，不要以 % 开头，否则索引失效；如果必须使用 %name% 进行查询，可以使用覆盖索引挽救，不用回表查询时可以触发索引；

7、尽量不要使用类型转换，否则索引失效；

8、尽量不要使用 or，否则索引失效；

六、一些其他的优化方法
-----------

1、exist 和 in

```
select name,age from student exist/in (子查询);
```

如果主查询的数据集大，则使用 in；

如果子查询的数据集大，则使用 exist；

### 2、order by 优化

sing filesort 有两种算法：双路排序、双路排序（根据 IO 的次数）

MySQL4.1 之前，默认使用双路排序；双路：扫描两次磁盘（①从磁盘读取排序字段，对排序字段进行排序；②获取其它字段）。

MySQL4.1 之后，默认使用单路排序；单路：只读取一次（全部字段），在 buffer 中进行排序。但单路排序会有一定的隐患（不一定真的是只有一次 IO，有可能多次 IO）。

注意：单路排序会比双路排序占用更多的 buffer。

单路排序时，如果数据量较大，可以调大 buffer 的容量大小。

```
set max_length_for_sort_data = 1024;单位是字节byte。
```

如果 max_length_for_sort_data 值太低，MySQL 底层会自动将单路切换到双路。

太低指的是列的总大小超过了 max_length_for_sort_data 定义的字节数。

**提高 order by 查询的策略：**

1.  选择使用单路或双路，调整 buffer 的容量大小；

2.  避免 select * from student;（① MySQL 底层需要对 * 进行翻译，消耗性能；② * 永远不会触发索引覆盖 using index）；

3.  符合索引不要跨列使用，避免 using filesort；

4.  保证全部的排序字段，排序的一致性（都是升序或降序）；


七、sql 顺序 -> 慢日志查询
-----------------

慢查询日志就是 MySQL 提供的一种日志记录，用于记录 MySQL 响应时间超过阈值的 SQL 语句（long_query_time，默认 10 秒） ；

慢日志默认是关闭的，开发调优时打开，最终部署时关闭。

### 1、慢查询日志

（1）检查是否开启了慢查询日志：

```
show variables like '%slow_query_log%'
```

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQ3rtq63yUOXcZK7Kouu8CicUAGIxlRIvJ9hI4PRpyM3FMX67BMpsghibg/640?wx_fmt=png)

（2）临时开启：

```
set global slow_query_log = 1;
```

（3）重启 MySQL：

```
service mysql restart;
```

（4）永久开启：

/etc/my.cnf 中追加配置：

放到 [mysqld] 下：

```
slow_query_log=1
slow_query_log_file=/var/lib/mysql/localhost-slow.log
```

### 2、阈值

（1）查看默认阈值：

```
show variables like '%long_query_time%'
```

（2）临时修改默认阈值：

```
set global long_query_time = 5;
```

（3）永久修改默认阈值：

/etc/my.cnf 中追加配置：

放到 [mysqld] 下：

long_query_time = 5;

（4）MySQL 中的 sleep：

```
select sleep(5);
```

（5）查看执行时间超过阈值的 sql：

```
show global status like '%slow_queries%';
```

八、慢查询日志 --> mysqldumpslow 工具
----------------------------

### 1、mysqldumpslow 工具

慢查询的 sql 被记录在日志中，可以通过日志查看具体的慢 sql。

```
cat /var/lib/mysql/localhost-slow.log
```

通过 mysqldumpslow 工具查看慢 sql，可以通过一些过滤条件，快速查出需要定位的慢 sql。

```
mysqldumpslow --help
```

参数简要介绍：

s：排序方式

r：逆序

l：锁定时间

g：正则匹配模式

### 2、查询不同条件下的慢 sql

（1）返回记录最多的 3 个 SQL

```
mysqldumpslow -s r -t 3 /var/lib/mysql/localhost-slow.log
```

（2）获取访问次数最多的 3 个 SQL

```
mysqldumpslow -s c -t 3 /var/lib/mysql/localhost-slow.log
```

（3）按时间排序，前 10 条包含 left join 查询语句的 SQL

```
mysqldumpslow -s t -t 10 -g "left join" /var/lib/mysql/localhost-slow.log
```

九、分析海量数据
--------

### 1、show profiles

打开此功能：set profiling = on;

show profiles 会记录所有 profileing 打来之后，全部 SQL 查询语句所花费的时间。

缺点是不够精确，确定不了是执行哪部分所消耗的时间，比如 CPU、IO。

### 2、精确分析，sql 诊断

show profile all for query  上一步查询到的 query_id。

### 3、全局查询日志

show variables like '%general_log%'

开启全局日志：

set global general_log = 1;

set global log_output = table;

十、锁机制详解
-------

### 1、操作分类

读写：对同一个数据，多个读操作可以同时进行，互不干扰。

写锁：如果当前写操作没有完毕，则无法进行其它的读写操作。

### 2、操作范围

表锁：一次性对一张表整体加锁。

如 MyISAM 存储引擎使用表锁，开销小、加锁快、无死锁；但锁的范围大，容易发生冲突、并发度低。

行锁：一次性对一条数据加锁。

如 InnoDB 存储引擎使用的就是行锁，开销大、加锁慢、容易出现死锁；锁的范围较小，不易发生锁冲突，并发度高（很小概率发生高并发问题：脏读、幻读、不可重复读）

lock table 表 1 read/write，表 2 read/write，...

查看加锁的表：

show open tables;

### 3、加读锁，代码实例

```
会话0：
lock table student read;
select * from student; --查，可以
delete from student where id = 1;--增删改，不可以
select * from user; --查，不可以
delete from user where id = 1;--增删改，不可以
```

如果某一个会话对 A 表加了 read 锁，则该会话可以对 A 表进行读操作、不能进行写操作。即如果给 A 表加了读锁，则当前会话只能对 A 表进行读操作，其它表都不能操作

```
会话1：
select * from student; --查，可以
delete from student where id = 1;--增删改，会“等待”会话0将锁释放
会话1：
select * from user; --查，可以
delete from user where id = 1;--增删改，可以
```

会话 0 给 A 表加了锁，其它会话的操作①可以对其它表进行读写操作②对 A 表：读可以，写需要等待释放锁。

### 4、加写锁

```
会话0：
lock table student write;
```

当前会话可以对加了写锁的表，可以进行任何增删改查操作；但是不能操作其它表；

其它会话：

对会话 0 中对加写锁的表，可以进行增删改查的前提是：等待会话 0 释放写锁。

### 5、MyISAM 表级锁的锁模式

MyISAM 在执行查询语句前，会自动给涉及的所有表加读锁，在执行增删改前，会自动给涉及的表加写锁。

所以对 MyISAM 表进行操作，会有如下情况发生：

（1）对 MyISAM 表的读操作（加读锁），不会阻塞其它会话（进程）对同一表的读请求。但会阻塞对同一表的写操作。只有当读锁释放后，才会执行其它进程的写操作。

（2）对 MyISAM 表的写操作（加写锁），会阻塞其它会话（进程）对同一表的读和写操作，只有当写锁释放后，才会执行其它进程的读写操作。

### 6、MyISAM 分析表锁定

查看哪些表加了锁：

show open tables;1 代表被加了锁

分析表锁定的严重程度：

show status like 'table%';

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQhO6oZDRYoawQLhNADWZv4j4wveTgs4iaBFjgAwfWiaZ29GgfSEhXSDVg/640?wx_fmt=png)

able_locks_immediate：可能获取到的锁数

Table_locks_waited：需要等待的表锁数（该值越大，说明存在越大的锁竞争）

一般建议：Table_locks_immediate/Table_locks_waited > 5000，建议采用 InnoDB 引擎，否则采用 MyISAM 引擎。

### 7、InnoDB 分析表锁定

为了研究行锁，暂时将自动 commit 关闭，set autocommit = 0;

show status like '%innodb_row_lock%';

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQU8ickQKDI6AXzRfI0K1YUGKTPpP3mr3GQlWZH9QM0tnicicGMZfQI9wibg/640?wx_fmt=png)

Innodb_row_lock_current_waits：当前正在等待锁的数量

Innodb_row_lock_time：等待总时长。从系统启动到现在一共等待的时间

Innodb_row_lock_time_avg：平均等待时长。从系统启动到现在一共等待的时间

Innodb_row_lock_time_max：最大等待时长。从系统启动到现在一共等待的时间

Innodb_row_lock_waits：等待次数。从系统启动到现在一共等待的时间

### 8、加行锁代码实例

（1）查询 student

```
select id,name,age from student
```

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQtRxPIQqW2vicWqm9TwjlfLMAoP77sO5Iq4zZH1pO7Pwulyz2loNX6kQ/640?wx_fmt=png)

（2）更新 student

```
update student set age = 18 where id = 1
```

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQTl386krQsr3V2UWicU3ictY6OuSK04xVxEia3SAl8GBR9s9Zweibhibpia9g/640?wx_fmt=png)

（3）加行锁

通过 select id,name,age from student for update; 给查询加行锁。

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQaOQia1sLymGCXDuVpRnJWglbkhxLlqXW7Bw38W6McibXibZBYht1wQbjQ/640?wx_fmt=png)

依旧修改成功，原因是 MySQL 默认是自动提交的，因此需要暂时将自动 commit 关闭

set autocommit = 0;

![](https://mmbiz.qpic.cn/mmbiz_png/YUYc62VIvE2ZCJoiaaH7ePicpZMPVJ5yiaQ2QrswWx0uf3q3sAPwX0rPhIPolVhBZ59MCoR59EUPriaEgPo8dt4UKg/640?wx_fmt=png)

### 9、行锁的注意事项

（1）如果没有索引，行锁自动转为表锁。

（2）行锁只能通过事务解锁。

（3）InnoDB 默认采用行锁

优点：并发能力强，性能高，效率高

缺点：比表锁性能损耗大

高并发用 InnoDb，否则用 MyISAM

公众号