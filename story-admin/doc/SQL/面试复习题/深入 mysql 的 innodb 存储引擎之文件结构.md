> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483943&idx=1&sn=e1564312d22eb52e537fa77ab8807dbf&scene=21#wechat_redirect)

![](https://mmbiz.qpic.cn/mmbiz_jpg/IJUXwBNpKlhE2snm9mCupwoZmVwpJPfgr9Skqyj4RaLlI1hleDEje42PVF8XUm7AeYnADrQb78TvETnQM7FWEw/640?wx_fmt=jpeg)

#### 文件分类

在 mysql 底层中，有各种的文件来构成 innodb 存储引擎的一部分，主要包含这几类文件：

1.  参数文件：在 mysql 启动时，指定初始化的一些参数文件，例如初始化某些结构的大小。

2.  日志文件：记录 mysql 执行 sql 操作的日志存储的文件。如：错误日志、慢查询日志文件、查询日志文件、二进制日志文件等

3.  socket 文件：主要是 UNIX 环境是连接本地的 mysql 需要使用 socket 文件。

4.  pid 文件：mysql 实例进程 ID 存储的文件。

5.  mysql 表结构文件：定义 mysql 表结构的文件。

6.  存储引擎文件：主要用来存储 mysql 的表数据和索引的文件。


#### 参数文件

当 mysql 实例启动的时候就会去参数配置文件中，读取各种初始化参数，如用来寻找定义各种数据库配置参数所在位置的配置文件（如 my.cnf 文件用来储存数据文件所在的文件、错误日志文件所在的位置、socket 文件所在的位置等），还有就是定义某些结构大小的配置参数的配置文件。

mysql 默认会在启动的时候按照这些文件路径去寻找这些配置参数文件，进而初始化 mysql 实例，我们也可以通过 mysql --help|grep my.cnf 来查看 my.cnf 文件所在的路径，如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhE2snm9mCupwoZmVwpJPfgIdY2kseRYxH4kQwBH97U0GuuelY20KRhDicgu3tPgHkPH64ckoiaMzzA/640?wx_fmt=png)  
我的`my.cnf`的文件路径是`/etc/my.cnf`，然后 cat 一下：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhE2snm9mCupwoZmVwpJPfgCSuKv24dXBYAOxkq6dQRke9fvoSAsPrgPCsTWIN4N38bYdu9Sp5q8g/640?wx_fmt=png)  
这里便是记录了**数据文件**的路径、**socket 文件**所在的路径、**log 日志**所在的文件路径、以及 **mysql 实例进程** pid 存储的路径。

在 mysql 的配置我配置文件中参数的存储是以 key/value 的形式进行存储的。例如：在 innodb 中 innodb_buffer_pool_size 参数，此参数表示 mysql 缓冲池的大小，可以通过如下途径进行查看：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhE2snm9mCupwoZmVwpJPfgcnD56MEAQxRWjur6EzibH0qTWEkXmticbbaCNpgcl642iahAdoDlicAoRw/640?wx_fmt=png)

##### 参数类型

在 mysql 中参数主要分为这两类，一个是**动态参数**，另一个是**静态参数**。动态参数意味着在 mysql 实例运行中可以进行修改，而静态参数意味着**整个 mysql 实例运行的生命周周期**中都不能修改。

我们常用的是动态参数，例如我们熟悉的`autocommit`，事务等级参数，直接`set autocommit=0`。对于静态参数，例如我们上面看到的`my.cnf`文件中的`datadir`数据文件路径就是静态参数，修改静态参数会直接报错。

对于动态参数又分为基于全局`（global）`还是还是会话`（session）`两类，可以通过`set @@global|@@session.动态参数=某值`，来改变参数。例如：`set @@global.read_buffer_size=1048567`

#### 日志文件

日志文件记录着操作数据库执行 sql 的各种日志记录，以及出现错误的错误日志记录。日志文件主要包含以下几类：

1.  错误日志：对于数据库执行某些操作失败，记录的错误日志记录，以及，某些操作的警告也会记录在这里。

2.  二进制日志：记录对 mysql 数据库更改的所有操作，但是不包含 select、show 操作，因为这两类并没有对数据库进行修改操作。

3.  慢查询日志：对于达到 mysql 自定义慢查询范围的 sql 语句，就会记录在这个日志中，对于检测那些 sql 查询耗时是比较有用的。

4.  查询日志：对于 mysql 的所有的查询记录都会记录在这个日志文件中。


##### 错误日志文件

错误无日志文件在 mysql 实例**启动**、**运行**、**关闭**的时候都会进行记录，我们可以通过以下的方式查看错误日志记录的位置。一个是通过上面的`my.cnf`文件中可以找到错误日志日志文件的路径位置，另一个可以通通过以下命令进行查找：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhE2snm9mCupwoZmVwpJPfgvrFc0g0Mz1VznqsPcrNoTy5wESzEA0DRjYNCP9D5ty60ZNozWKYTeQ/640?wx_fmt=png)  
该文件记录着错误信息，我们可以借助该文件很好的发现问题。当数据库不能重启的时候，通过查找该错误日志文件可以帮助我们解决问题。

##### 慢查询日志

在 mysql 中有一个动态参数 long_query_time 表示慢查询的阈值，表示当执行的 sql 超过这个这个参数的时间，就数据慢查询的范围就会将此纪录存入该日志中，该值默认为 10，通以下命令进行查看。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhE2snm9mCupwoZmVwpJPfghicLcMPsiaKo1dicXNfL6cGIsdO7BLP8A15ic5DibgD0pU3j4zBSJAnzssg/640?wx_fmt=png)

在实际的开发中，一般不会有超过 10 秒的的 sql，所以我们将该值可以设置小一点，设置为 1 秒，通过命令 `SET long_query_time=1`将该值设置为 1 秒，但是默认慢查询的日志记录功能是关闭的。如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhE2snm9mCupwoZmVwpJPfg8zALUMGwGScsl50zVIRezHQGHmKFSQ8VDfzRkr2uziaQiaiaicnL0VW30g/640?wx_fmt=png)  
通过命令`SET GLOBAL slow_query_log=ON`将该功能开启，最后做一个测试执行 sql：`select sleep(10)；`然后来到 mysql 的 datadir 目录下就会有该慢查询文件

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhE2snm9mCupwoZmVwpJPfgzG1KPkBJzzuV1ice8417hNric2pRaua7HSU0Ezko80pIDnIbM2PhrEiag/640?wx_fmt=png)  
执行 cat + 改文件名，就可以看到该 sql 执行的时间，以及执行慢的 sql 语句：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhE2snm9mCupwoZmVwpJPfg9lMPo2gSeJPZh0w9eJUL2chJXxic1iaxKB0lVwtkFJTLwW9KSsMHmLsg/640?wx_fmt=png)  
另外通过设置`log_queries_not_using_indexes`参数，也可以将没有使用索引的 sql 记录收到该文件中。

##### 查询日志

查询日志文件记录了所有 mysql 数据库的操作信息，无论请求是否正确。可以通过如下命令查看的日志的位置：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhE2snm9mCupwoZmVwpJPfgoIMU62k7BEXh22ia3RibIicOYBRfciawWsia9Wxu4ej0Is6gaGKY2bTNXww/640?wx_fmt=png)

##### 二进制日志

记录了所有的对 mysql 数据库修改的操作，但是不包含`select`、`show`这类，因为这类没有对数据库进行修改，若是想记录 select 和 show 操作，只能使用**查询日志**。

二进制日志文件主要有以下几个作用：

1.  恢复：某些数据的恢复需要二进制文件。

2.  复制：同另一台远程的 mysql 进行数据的同步。

3.  审计：用于判断对数据库是否有进行注入攻击。


在 mysql 中二进制文件存在于`datadir`目录下，如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhE2snm9mCupwoZmVwpJPfgTCpXAqrf6x4bdNHZQqvRvyhuhGlx8Jwexhj2dZgTJUz4mjB4DhsibMw/640?wx_fmt=png)  
二进制文件默认是没有启动的，我们需要手动的设置参数来启动它。但是启动二进制文件对数据库的整体性能会下降。但是对于数据库某一时刻宕机，对于数据的恢复是非常有用的。

MYSQL 查看是否启用了日志`show variables like 'log_bin';`，若是没有开启也可以通过 set 命令将该值设置为`on`，查看当前的日志 `show master status;`, 该日志文件的存放位置也是在`datadir`目录下

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhE2snm9mCupwoZmVwpJPfgQUr1RSxsjKBkH7AuHAiaFdhpOAwKOB7BCzItk8L6T7n9LJ2Z3cOicZPA/640?wx_fmt=png)

#### socket 文件以及 pid 文件

该两类文件我们几乎很少涉及，该文件的存放为位置，直接可以查看`my,cnf`文件，这里就不进行深入了解。

#### 表结构定义文件和存储引擎文件

##### 表结构定义文件和表空间

`*.frm` 文件是所有 mysql 数据库都有的文件，记录了该表的表结构定义。InnoDB 中用于存储数据的文件总共有两个部分，一是系统表空间文件，包括 `ibdata1`、 `ibdata2` 等文件，其中存储了 InnoDB 系统信息和用户数据库表数据和索引，是所有表公用的，另一个是`.idb`文件，是每张表独有的。如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhE2snm9mCupwoZmVwpJPfgR11PRhs1A1THW8qMMxAxCFD9nkKR72tbn1icYWPqatfTqELxib0UCBDQ/640?wx_fmt=png)

当打开 `innodb_file_per_table` 选项时， `.ibd` 文件就是每一个表独有的表空间，文件存储了当前表的 数据，索引数据和插入缓冲等信息。查看和设置`innodb_file_per_table` 配置

```
show variables like '%per_table%';
set global innodb_file_per_table =ON;
```

##### 重做日志文件

默认情况下在 mysql 的`datadir`下会有`ib_logfile0`和`ib_logfile1`两个文件，这两个文件就是**重做日志文件**。重做日志文件的记录对于数据库的 ACID 事务中的 **C（一致性）** 提供了保障。每个 innodb 存储引擎中至少会有两个重做日志文件。

每个重做日志文件的大小是相同的，并且以**循环**的方式进行日志的写入，当`ib_logfile0`文件写满的时候，就会切换到`ib_logfile1`日志文件。

重做日志文件的功能和二进制日志文件功能同样是记录事务日志的文件，那么二进制文件和重做日志文件有什么区别呢？

1.  二进制文件是记录所有和 mysql 数据库有关的日志文件，记录的范围比重做日志文件要广。

2.  二进制文件仅在事务提交的时候刷入磁盘。而在事务进行中就会不断的将重做日志写入到磁盘中，


重做日志写入磁盘并不是直接写入，而是先写入到一个重做缓冲日志中，需要一定的条件触发才能进行写入。触发重做缓冲日志写入磁盘，一个是主线程`（master thread）`，每秒会将重做日志刷入磁盘中。

另一个就是通过传参数 innodb_flush_log_at_trx_commit 参数来控制，该参数有 0，1，2，其中 0 表示事务提交的时候不会将重做日志刷入磁盘中，需要主线程触发日志输出到磁盘中。

1 表示事务 commit 的时候机会将缓冲区的日志输出到磁盘中。所以要事务的持久性和一致性将该值设置为 1。在出现异常的时候，需要需要数据的恢复，便可以查看重做日志，并结合二进制日志文件进行数据的恢复。保证数据不会丢失。

![](https://mmbiz.qpic.cn/mmbiz_jpg/IJUXwBNpKlhE2snm9mCupwoZmVwpJPfg7Ric3tpKYY0fA9V5pqs68j2upHuw4jbicnBMZjQxbjiaYRV6Kg36ZaRHw/640?wx_fmt=jpeg)

_**[参考]**_

Mysql 技术内幕 innoDB 存储引擎

_**[往期精彩回顾]**_

[[万字长文，一文搞懂 TCP/IP 和 HTTP、HTTPS]](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483880&idx=1&sn=b4b71f169dddc41925814ab4e5bc208b&chksm=fbf7e82acc80613c2f9d2e11aba576dd5d367140ab74c33f6b79e0e6a3cbb84e388a50fded7d&scene=21#wechat_redirect)

[[Mysql 优化提高笔记整理，来自于一位鹅厂大佬的笔记](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483938&idx=1&sn=3d97fd3aa5601834138b33e3dddfc544&chksm=fbf7ebe0cc8062f69320ea0b10d92bea8c123801cd15fa1870c91ad29e62c503185e46111716&scene=21#wechat_redirect)] 

[[B 树、B - 树、B + 树、B * 树图文详解]](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483924&idx=1&sn=54f8069ebf3e289c9d05c56c839d57ff&chksm=fbf7ebd6cc8062c0acdbaaadd09bca7a01e050cbbbd8f281b95d6c32db77203cea8266d5715f&scene=21#wechat_redirect)

[[万字长文，最硬核的 mysql 知识总结]](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483886&idx=2&sn=3b9a0dde6fa9b06f3fba6a0aeca83820&chksm=fbf7e82ccc80613ab4eeda8ced91d6b6b6c8143693e864711de395f112a7f1cdef257ec26ecb&scene=21#wechat_redirect)

[[为了把 mysql 的索引底层原理讲清楚，我把计算机翻了个底朝天]](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483934&idx=1&sn=e95a47a8255ac4a814a53afbc54e513a&chksm=fbf7ebdccc8062cab93bec95d884c8a11f6fb054caa3b1b8e4c6b6ee466944ff2a8b0affd695&scene=21#wechat_redirect)