> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484781&idx=1&sn=dc6ddb524825ec6b73f5ce0407ba6c5d&scene=21#wechat_redirect)

![](https://mmbiz.qpic.cn/mmbiz_jpg/IJUXwBNpKljjrOU752F5ibPrkKk9WrZ9t4jEbBjc8zgpPb9V7yOHspv1HU0EHrorF4TicNU3ic21lTfSicdmN9icCYA/640?wx_fmt=jpeg)前一篇的 Mysql 面试还是非常给力的，非常的干货，有兴趣的可以看一下 [[MySQL 四万字精华总结 + 面试 100 问，和面试官扯皮绰绰有余（收藏系列）](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484735&idx=1&sn=cebe2a175f5a3716aa04e91ed837d694&chksm=fbf7ecfdcc8065ebe15aaaf8d2d2fb092db3edb748113f6647278ca6539995e3ff67025d75bd&scene=21#wechat_redirect)]，虽然不是我写的，但是内容确实是非常的干货，4 万字解决你 Mysql 面试中存在的问题，好的文章还是要推荐给大家。

讲完了单机版的 Mysql 问题，来说一说集群，这不写了一篇 Mysql 主从的搭建，主要讲主从复制的原理，以及自己实践搭建主从，对于还没有接触主从的程序员，还是非常友好的，文章内容通俗易懂。

主从复制简介
------

在实际的生产中，为了解决 Mysql 的单点故障已经提高 MySQL 的整体服务性能，一般都会采用**「主从复制」**。

比如：在复杂的业务系统中，有一句 sql 执行后导致锁表，并且这条 sql 的的执行时间有比较长，那么此 sql 执行的期间导致服务不可用，这样就会严重影响用户的体验度。

主从复制中分为**「主服务器（master）**「和」**从服务器（slave）」**，**「主服务器负责写，而从服务器负责读」**，Mysql 的主从复制的过程是一个**「异步的过程」**。

这样读写分离的过程能够是整体的服务性能提高，即使写操作时间比较长，也不影响读操作的进行。

主从复制的原理
-------

首先放一张 Mysql 主从复制的原理图，总的来说 Mysql 的主从复制原理还是比较好理解的，原理非常的简单。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljjrOU752F5ibPrkKk9WrZ9tOCRmhCuk6I0vnDS8ydDW7J5rVYibrPf0iasgfia7L7tenkClcKoiczkjlw/640?wx_fmt=png)

Mysql 的主从复制中主要有三个线程：`master（binlog dump thread）、slave（I/O thread 、SQL thread）`，Master 一条线程和 Slave 中的两条线程。

`master（binlog dump thread）`主要负责 Master 库中有数据更新的时候，会按照`binlog`格式，将更新的事件类型写入到主库的`binlog`文件中。

并且，Master 会创建`log dump`线程通知 Slave 主库中存在数据更新，这就是为什么主库的 binlog 日志一定要开启的原因。

`I/O thread`线程在 Slave 中创建，该线程用于请求 Master，Master 会返回 binlog 的名称以及当前数据更新的位置、binlog 文件位置的副本。

然后，将`binlog`保存在 **「relay log（中继日志）」** 中，中继日志也是记录数据更新的信息。

SQL 线程也是在 Slave 中创建的，当 Slave 检测到中继日志有更新，就会将更新的内容同步到 Slave 数据库中，这样就保证了主从的数据的同步。

以上就是主从复制的过程，当然，主从复制的过程有不同的策略方式进行数据的同步，主要包含以下几种：

1.  **「同步策略」**：Master 会等待所有的 Slave 都回应后才会提交，这个主从的同步的性能会严重的影响。

2.  **「半同步策略」**：Master 至少会等待一个 Slave 回应后提交。

3.  **「异步策略」**：Master 不用等待 Slave 回应就可以提交。

4.  **「延迟策略」**：Slave 要落后于 Master 指定的时间。


对于不同的业务需求，有不同的策略方案，但是一般都会采用最终一致性，不会要求强一致性，毕竟强一致性会严重影响性能。

主从搭建
----

下面我们就来实操搭建主从，使用的是两台 centos7 并且安装的是 Mysql 8 来搭建主从，有一台 centos 7 然后直接克隆就行了。

（1）首先检查 centos 7 里面的 Mysql 安装包和依赖包：

```
rpm -qa |grep mysql
```

执行后，在我本机上的显示如下：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljjrOU752F5ibPrkKk9WrZ9tO5tGDvXDlgictSXRotI4Tn2lIwthm0wQFibibKklWMHdD40PTIpHTwhVA/640?wx_fmt=png)

（2）接着可以删除上面的安装包和依赖包：

```
sudo yum remove mysql*
```

（3）继续检查一下是否存在 Mariadb，若是存在直接删除 Mariadb

```
// 检查是否存在Mariadb
rpm -qa |grep mariadb
// 删除Mariadb
sudo rpm -e --nodeps mariadb-libs-5.5.56-2.el7.x86_64
```

（4）然后，就是删除 Mysql 的配置文件，可以使用下面的命令查找 Msqyl 配置文件的路径：

```
sudo find / -name mysql
```

在我本机上的显示的 Mysql 配置文件的路径如下：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljjrOU752F5ibPrkKk9WrZ9t8OUGjclHwp7H8amiaNvaHeS4tll0RhvGUM1eEIz5xpTrorHdDBXD9dA/640?wx_fmt=png)

（5）然后，通过下面的命令，将他们逐一删除：

```
sudo rm -rf /usr/lib64/mysql
......
```

（6）接着就开始安装 Mysql 8 了，使用 wget 命令下载 Mysql 8 的 repo 源，并且执行安装：

```
wget https://repo.mysql.com//mysql80-community-release-el7-3.noarch.rpm
sudo yum -y install mysql80-community-release-el7-3.noarch.rpm
```

安装完后会在 / etc/yum.repos.d / 目录下生成下面的两个文件，说明安装成功了：

```
mysql-community.repo
mysql-community-source.repo
```

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljjrOU752F5ibPrkKk9WrZ9t7DNWic7Zgoq071IUqT4LcX4gKY1g5EXdia98w3AadnlCm8NJPujM4T4w/640?wx_fmt=png)

（7）安装完 Mysql8 后，接着来更新一下 yum 源，并且查看 yum 仓库中的 Mysql：

```
// 更新yum源
yum clean all
yum makecache
// 查看yum仓库中的Mysql
yum list | grep mysql
```

（8）可以查看到仓库中存在 mysql-community-server.x86_64，直接安装就行了：

```
sudo yum -y install mysql-community-server
```

（9）接着启动 Mysql，并检查 Mysql 的状态：

```
// 启动Mysql
systemctl start  mysqld.service
// 检查Mysql的状态
systemctl status mysqld
```

确保查看 Mysql 的状态是`active(running)`，表示正在运行，并且配置一下 Mysql 开机启动：

```
systemctl enable mysqld
```

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljjrOU752F5ibPrkKk9WrZ9tDYPQJ230E7LfArHHq8iae2BB0GWvhgRHyicVAa0XNpIoiaOStssM4yicgg/640?wx_fmt=png)

（10）因为 Mysql 是新安装的，所以要修改一下初始密码，先查看一下初始密码：

```
grep "password" /var/log/mysqld.log
```

你可能找出来有多个，可能是你之前安装卸载后的文件没有删干净，这里你就直接看时间，时间对应你现在的时间，就是你的初始密码：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljjrOU752F5ibPrkKk9WrZ9tN7njkQ4erAPJkPiaepicULNVoAOobUWZ8VBDRzY4y0AuhGdTDgajKhicQ/640?wx_fmt=png)

（11）然后使用初始密码，登陆数据库，并且修改密码：

```
mysql -uroot -p
ALTER USER 'root'@'localhost' IDENTIFIED BY 'LDCldc@123095;
```

（12）此时在创建一个可以用于给两一台 centos 连接的用户，默认的 root 用户只有本机才能连接：

```
// 创建连接用户
create user 'test'@'%' identified by 'LDCldc-2020';
// 并且把防火墙给关了，或者配置一下3306端口
systemctl stop firewalld.service；
// 设置防火墙开机自动关闭
systemctl disable firewalld.service；
```

（13）测试：到这里就 Mysql 的安装教程就就讲完了，可以测试一下，两台 centos 是否可以 ping 通：

```
ping 192.168.163.156
```

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljjrOU752F5ibPrkKk9WrZ9t33ShPQ7mvRWvzoMmQB0CMNg5ic7OQCX9QesgGTy9WVRthUCLgTzmhtQ/640?wx_fmt=png)

我这里的两台机是可以互通的，`Master：192.168.163.156，Slave：192.168.163.155`，并且 Slave 使用下面的命令可以登陆到 Master 的 Mysql：

```
mysql -u[user] -p[密码] -h[远程主机ip]
```

确保了这两项测试成功后，就可以进行下面的主从搭建了。

（14）我这里使用的使用两台 centos 7 的 vmware 的 ip 分别是`192.168.163.155（Slave）`和`192.168.163.156（Master）`作为测试，首先在 192.168.163.156（Master）中创建一个测试库 test：

```
// 创建测试库
create database test default character set utf8mb4 collate utf8mb4_general_ci;
// 并且授权
grant all privileges on test.* to 'test'@'%';
```

（15）然后编辑 Master 中的 my.cnf 文件，此文件位于 / etc/my.cnf，执行下面的 sql，并添加下面的信息：

```
sudo vi /etc/my.cnf

==========以下是配置文件中的信息=============
# 配置编码为utf8
character_set_server=utf8mb4
init_connect='SET NAMES utf8mb4'

# 配置要给Slave同步的数据库
binlog-do-db=test
# 不用给Slave同步的数据库，一般是Mysql自带的数据库就不用给Slave同步了
binlog-ignore-db=mysql
binlog-ignore-db=information_schema
binlog-ignore-db=performance_schema
binlog-ignore-db=sys
# 自动清理30天前的log文件
expire_logs_days=30
# 启用二进制日志
log-bin=mysql-bin
# Master的id，这个要唯一，唯一是值，在主从中唯一
server-id=3
```

（16）配置完后重启 Mysql 服务，并查看 Mysql 的 log_bin 日志是否启动成功：

```
systemctl restart mysqld
# 查看log_bin日志是否启动成功
show variables like '%log_bin%';
```

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljjrOU752F5ibPrkKk9WrZ9tDFy5b9w7R6nhwx6Z9ex9RibHiaArKCBv5iajoAmcD7dwdhg9W2q90kLicg/640?wx_fmt=png)

（17）接着查看 Master 的状态：

```
show master status;
```

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljjrOU752F5ibPrkKk9WrZ9thibiaSf6K1R8uokPFnSVyX7Oqa794cDClvPhXU9r7UD0zricpqKic13UIw/640?wx_fmt=png)

这两个数据`File`和`Position`要记住，后面配置 Slave 的时候要使用到这两个数据。

（18）最后登陆 Master 的数据库，并创建一个用户用于同步数据：

```
create user 'backup'@'%' IDENTIFIED BY 'LDCldc-2020';
grant file on *.* to 'backup'@'%';
GRANT REPLICATION SLAVE, REPLICATION CLIENT ON *.* to 'backup'@'%';
```

到这里 Master 的配置就配置完了，后面就进行 Slave 的配置。

（19）在 Slave 中同样要创建 test 数据库，并且授权给 test 用户：

```
# 创建同步数据的test数据库
create database test default character set utf8mb4 collate utf8mb4_general_ci;
# 授权
grant all privileges on test.* to 'test'@'%';
```

（20）接着编辑 Slave 中 my.cnf 文件，同样是在 / etc/my.cnf 路径下，加入如下配置：

```
# 配置从服务器的ID，唯一的
server-id=4
#加上以下参数可以避免更新不及时，SLAVE 重启后导致的主从复制出错。
read_only = 1
master_info_repository=TABLE
relay_log_info_repository=TABLE
```

（21）并且重启 Slave 中的 Mysql 服务：

```
systemctl restart mysqld
```

（22）在 Slave 中添加 Master 的信息：

```
# master_host是Master的ip，master_log_file和master_log_pos就是配置之前查看Master状态时显示的File和Position信息
change master to master_host='192.168.163.156',master_port=3306,master_user='backup',master_password='LDCldc-2020',master_log_file='mysql-bin.000001',master_log_pos=1513; 
```

（23）最后查看 Slave 的状态：

```
show slave status\G
```

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKljjrOU752F5ibPrkKk9WrZ9t8UKhMKl88eqpica7Ec1IZSPicSmDLYuBYoPmNK1wOsdUP1rUqSn50Yxw/640?wx_fmt=png)

当看到`Slave_IO_Running`和`Slave_SQL_Running`都是 yes 的时候，这表示主从配置成功。

**「Slave_IO_Running 也就是 Slave 中的 IO 线程用于请求 Master，Slave_SQL_Running 时 sql 线程将中继日志中更新日志同步到 Slave 数据库中。」**

但是，有时候 Slave_IO_Running 会为 no，而 Slave_SQL_Running 为 yes，这时候需要检查一下原因，因为我自己初次搭建的时候，也是出现这个问题。

首先看重启一下`Slave`的 MySQL 服务：`systemctl restart mysqld`，然后执行：

```
stop slave;
start slave;
```

这样就能够使`Slave_IO_Running`和`Slave_SQL_Running`显示都是 yes 了。

（24）最后就是测试了，测试使用的是之前创建的 test 库，Master 是用来写的，在 Master 的 test 库中随机创建一个表，你会发现 Slave 也会有这个表，插入数据也一样，都会被同步到 Slave 中。

主从面试
----

> ❝
>
> Mysql 主从有什么优点？为什么要选择主从？
>
> ❞

1.  高性能方面：主从复制通过水平扩展的方式，解决了原来单点故障的问题，并且原来的并发都集中到了一台 Mysql 服务器中，现在将单点负载分散到了多台机器上，实现读写分离，不会因为写操作过长锁表而导致读服务不能进行的问题，提高了服务器的整体性能。

2.  可靠性方面：主从在对外提供服务的时候，若是主库挂了，会有通过主从切换，选择其中的一台 Slave 作为 Master；若是 Slave 挂了，还有其它的 Slave 提供读服务，提高了系统的可靠性和稳定性。


> ❝
>
> 若是主从复制，达到了写性能的瓶颈，你是怎么解决的呢？
>
> ❞

主从模式对于写少读多的场景确实非常大的优势，但是总会写操作达到瓶颈的时候，导致性能提不上去。

这时候可以在设计上进行解决采用分库分表的形式，对于业务数据比较大的数据库可以采用分表，使得数据表的存储的数据量达到一个合理的状态。

也可以采用分库，按照业务进行划分，这样对于单点的写，就会分成多点的写，性能方面也就会大大提高。

> ❝
>
> 主从复制的过程有数据延迟怎么办？导致 Slave 被读取到的数据并不是最新数据。
>
> ❞

主从复制有不同的复制策略，对于不同的场景的适应性也不同，对于数据的实时性要求很高，要求强一致性，可以采用同步复制策略，但是这样就会性能就会大打折扣。

若是主从复制采用异步复制，要求数据最终一致性，性能方面也会好很多。只能说，对于数据延迟的解决方案没有最好的方案，就看你的业务场景中哪种方案使比较适合的。

![](https://mmbiz.qpic.cn/mmbiz_jpg/IJUXwBNpKljjrOU752F5ibPrkKk9WrZ9tONicsFDJBATfHd2S8uA3X06fspd1l87DJibVpN9NtpAhfTsS7t1fA9Yw/640?wx_fmt=jpeg)

【推荐阅读】

[1] [看完这篇 Redis 缓存三大问题，保你能和面试官互扯。](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483977&idx=1&sn=e42c9aedb8bb1c914afa5f2f74324d1f&chksm=fbf7eb8bcc80629dac2c4074c393ca672915bab26864a277251883f1f6c2a6d96338dae3e5b0&scene=21#wechat_redirect)

[2] [我以为我对 Mysql 事务很熟，直到我遇到了阿里面试官](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484313&idx=1&sn=6801e8822dfb9b4f2192960c8c3d5530&chksm=fbf7ea5bcc80634da0cf388f9e4efe36b87f278ee712faf5f4e6221173fc47a41547d135056a&scene=21#wechat_redirect)[。](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484313&idx=1&sn=6801e8822dfb9b4f2192960c8c3d5530&chksm=fbf7ea5bcc80634da0cf388f9e4efe36b87f278ee712faf5f4e6221173fc47a41547d135056a&scene=21#wechat_redirect)

[3] [面试官：你知道 java 类是怎么跑起来的吗？问的我一脸懵](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483972&idx=1&sn=5e6b01caa6aa8fe7111924dc850e865e&chksm=fbf7eb86cc806290160187cdc195aad6939ba81e6988602db59f897fe1ee4f4d08a573424bea&scene=21#wechat_redirect)

[4] [面试造飞机系列：面对 Redis 持久化连环 Call，你还顶得住吗？](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484021&idx=1&sn=4c228c705cfd524db3cf27425fde1ba0&chksm=fbf7ebb7cc8062a138b9baad194548983095806f8f15a82e1a95f18f19999c76d750aa91b93b&scene=21#wechat_redirect)

[5] [面试造飞机系列：用心整理的 HashMap 面试题，以后都不用担心了](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483998&idx=1&sn=b155ac4d5263d54ea3008ff33196c3b2&chksm=fbf7eb9ccc80628a559180abac786b108859180787716e093da6a315c0226071060b73836ce5&scene=21#wechat_redirect)

[6] [大厂面试官必问的 Mysql 锁机制](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247484473&idx=1&sn=e1093c8ebc4e6b105dbd41e1065ad0e8&chksm=fbf7edfbcc8064edf01f40dced8d72b1fcc499bd2aa1eeecda15b863eefa7c2a08e075417779&scene=21#wechat_redirect)