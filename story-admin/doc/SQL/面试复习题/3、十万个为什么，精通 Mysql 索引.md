> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247489108&idx=1&sn=81eca109468ede7caab7467c3c1e2018&scene=21#wechat_redirect)

大家好，我是黎杜，这一期又带来 Mysql 的索引篇，上一期我们从大的方向聊了学习 Mysql 的个人经验，有兴趣的可以看一看：[学习 Mysql 的亿点点意见](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247489047&idx=1&sn=fc2a8e4a051c4ca103520b127d766e9e&scene=21#wechat_redirect)

以后可以大胆的在工作中写技术文章了，嘻嘻嘻，当然是在工作中接口开发完的前提下，不过以我的开发速度，开发接口不就是轻而易举的事。

为啥我能在工作的时间写技术文呢？请看下图：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9VyLHJT3hlDIlZ2DQug6iarp7G2ia2d6YR7z7u6Oiciav7YBhsHyzibdfLiaw/640?wx_fmt=png)

因为写博客也是 kpi 之一，小伙伴们会问真有那么爽，还真有，因为内部为了共同学习和分享，都会把要求 RD 写博客作为 kpi 之一，然后好的文章就会分享到内部团队，共同学习，所以即使在上班写博客被领导看见，也可光明正大的。

好了，话不多说，我们直接进入今天的正题，首先来聊一聊什么是索引。

索引概念
----

概念：**索引是提高 mysql 查询效率的数据结构**。总的一句话概括就是索引是一种提高查询效率的数据结构。

数据库查询是数据库的最主要功能之一。设计者们都希望查询数据的速度能尽可能的快，因此数据库系统的设计者会从查询算法的角度进行优化。

最基本的查询算法当然是顺序查找（linear search），这种复杂度为 O(n) 的算法在数据量很大时显然是糟糕的，好在计算机科学的发展提供了很多更优秀的查找算法，例如：**有顺序查找、折半查找、快速查找**等。

但是，每种查找算法都只能应用于特定的数据结构之上，例如顺序查找依赖于顺序结构，折半查找通过二叉查找树或红黑树实现二分搜索。

因此，在数据之外，数据库系统还维护着满足特定查找算法的数据结构。这种数据结构，就是索引。

索引性能分析
------

目前，大多数数据库系统及文件系统都采用 B-Tree 或其变种 B+Tree 作为索引结构。B+ 树索引是 B+ 树在数据库中的一种实现，是最常见也是数据库中使用最为频繁的一种索引。

从最早的平衡二叉树演化而来的。B+ 树是由二叉查找树、平衡二叉树（AVLTree）和平衡多路查找树（B-Tree）逐步优化而来。

**那么为什么 mysql 的索引选择 B + 数呢？**

**有序数组、Hash 索引、红黑树、二叉查找树、AVL 树**也可以作为数据结构也可以用来实现索引，但是文件系统以及数据库系统普遍采用 B 树或者 B + 树，这里结合各个索引的特点以及计算的组成原理来深入的分析。

但是，对于 Mysql 来说适合它的才是最好的查询，一方面要实现高效的查询，除了简单的条件查询，还要支持有序的高效索引的范围查询、分组。

有序数组在等值查询和范围查询性能都是非常好的，那为什么又不用有序数组作为索引呢？因为对于数组而言作为索引更新的成本太高，新增数据要把后面的数据都往后移一位，所以也不采用有序数组作为索引的底层实现。

hash 是以 key-value 的形式进行存储，适合于等值查询的场景，查询的时间复杂度为 O(1)，因为 hash 储存并不是有序的，所以对于范围查询就可能要遍历所有数据进行查询，而且不同值的计算还会出现 hash 冲突，所以 hash 并不适合于做 Mysql 的索引。

另一方面就是除了查询的效率要高，还要有高效的读取数据效率（io），我们都知道计算机的随机磁盘 io 效率是非常低下的。

**那么为什么硬盘的存取会如此的慢呢？**

这个就要讲硬盘的读写原理，硬盘有很多种，但是都是由**盘片、磁头、盘片主轴、控制电机、磁头控制器、数据转换器、接口、缓存**等几个部分组成。

所有的盘片都固定在一条轴上，那条轴叫做盘片主轴，所有的盘片都是绝对平行的，也形成一个柱体，每个盘片上都有一个磁头，每个磁头都在同一轴线上，就是从上方往下看，磁头是绝对重叠的。

所有的磁头连在一个磁头控制器上，由磁头控制器负责各个磁头的运动，磁头可沿盘片的半径方向移动，实际上是斜切运动，每个磁头同一时刻必须是同轴的盘片以每分钟数千转到上万转的速度在高速运转，这样磁头就能对盘片上的指定位置进行数据的读写操作：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9h1Rp0jv8qSyRuPk6lM3pJbRusqlGhJnSXYLReYQb47FIwPqsa2G9cQ/640?wx_fmt=png)

**磁盘数据的读写原理**

盘片被划分成一系列同心环，圆心是盘片中心，每个同心环叫做一个磁道，所有半径相同的磁道组成一个柱面。

**磁道被沿半径线划分成一个个小的段，每个段叫做一个扇区，每个扇区是磁盘的最小存储单元**。为了简单起见，我们下面假设磁盘只有一个盘片和一个磁头。

当磁盘读取数据时，系统会将数据逻辑地址传给磁盘，磁盘的控制电路按照寻址逻辑将逻辑地址翻译成物理地址，即确定要读的数据在哪个磁道，哪个扇区。

为了读取这个扇区的数据，**需要将磁头放到这个扇区上方，为了实现这一点，磁头需要移动对准相应磁道，这个过程叫做寻道，所耗费时间叫做寻道时间，然后磁盘旋转将目标扇区旋转到磁头下，这个过程耗费的时间叫做旋转时间**。

即一次磁盘的读写操作完成过程由三个动作组成：

*   **寻道（时间）**：磁头移动定位到指定磁道。

*   **旋转延迟（时间）**：等待指定扇区从磁头下旋转经过。

*   **数据传输（时间）**：数据在磁盘与内存之间的实际传输


> 额外知识：
>
> *   盘面：硬盘的每一个盘片都有上下两个盘面，一般每个盘面都会得到利用，都可以存储数据，盘面号又叫磁头号，因为每一个有效盘面都有一个对应的读写磁头。
>
> *   磁道：磁盘在格式化时被划分成许多同心圆，这些同心圆轨迹叫做磁道，磁道从外向内从 0 开始顺序编号，信息以脉冲串的形式记录在这些轨迹中，这些同心圆不是连续记录数据，而是被划分成一段段的圆弧。
>
> *   所有盘面上的同一磁道构成一个圆柱，通常称作柱面。所有盘面上的同一磁道构成一个圆柱，通常称作柱面。数据的读 / 写按柱面进行，而不按盘面进行，当一个磁道写满数据后，就在同一柱面的下一个盘面来写，一个柱面写满后，才移到下一个扇区开始写数据，读数据也按照这种方式进行，这样就提高了硬盘的读 / 写效率。
>

**提高磁盘数据读写原理**

局部性原理与磁盘预读。由于存储介质的特性，**磁盘本身存取就比主存慢很多，再加上机械运动耗费，磁盘的存取速度往往是主存的几百分分之一，因此为了提高效率，要尽量减少磁盘 I/O。**

为了达到这个目的，**磁盘往往不是严格按需读取，而是每次都会预读，即使只需要一个字节，磁盘也会从这个位置开始，顺序向后读取一定长度的数据放入内存**。

这样做的理论依据是计算机科学中著名的局部性原理：**当一个数据被用到时，其附近的数据也通常会马上被使用**。

所以，程序运行期间所需要的数据通常应当比较集中。由于磁盘顺序读取的效率很高（不需要寻道时间，只需很少的旋转时间），因此对于具有局部性的程序来说，预读可以提高 I/O 效率。

预读的长度一般为页（page）4k 的整倍数。页是计算机管理存储器的逻辑块，硬件及操作系统往往将主存和磁盘存储区分割为连续的大小相等的块，每个存储块称为一页（在许多操作系统中，页的大小通常为 4k），主存和磁盘以页为单位交换数据。

当程序要读取的数据不在主存中时，会触发一个缺页异常，此时系统会向磁盘发出读盘信号，**磁盘会找到数据的起始位置并向后连续读取一页或几页载入内存中，然后异常返回，程序继续运行**。

> **所以，硬盘中由于涉及到机械运动，所以一次的磁盘 IO 消耗的时间是非常大的，于内存的读取速度相比，就好比光速与声速的比较。**

因此，假如内存条件允许的话，Mysql 巴不得把所有的数据一次性加载到内存中进行读写。一般来说，索引本身也很大，不可能全部存储在内存中，因此索引往往以索引文件的形式存储在磁盘上。

服务器的内存的大小也是限制的，一个服务器中可能不止跑着 Mysql 一个进行，多多少少都有可能二三十个进行，每个进行都需要操作系统分配内存。

这样的话，索引查找过程中就要产生磁盘 I/O 消耗，相对于内存存取，硬盘 I/O 存取的消耗要高几个数量级，查找过程中磁盘 I/O 的存取次数。

Mysql 中的一些大的数据表，一个表就有可能几个 G，索引结构也很大，那服务器内存不得撑爆了。

**所以，必须做一个取舍，在内存与磁盘中进行衡量，数据尽量放在内存中，而在少量的数据在磁盘中，读取磁盘的次数控制到最少，也就是对于 Mysql 的性能影响到最小，加上磁盘数据读写原理来提高数据的读取效率**。

那么在众多树的条件下，B + 树又是以怎么样的又是脱颖而出呢？下面我们来聊一聊 B 树、B - 树、B + 树、红黑树性能。

**二叉树、红黑树、AVL 树、B 树、B + 树性能分析**

B 树性能分析：B 树是二叉查找平衡树，但是 B 树一个节点只存一个关键字，在大量数据的时候，B 树树高非常大，性能低下：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9AdH8dhTECKzPJBFic8ycHWB5mu7feWx2lSKgp1HE8A3zHtSschycJLw/640?wx_fmt=png)

甚至在极端的情况下，因为二叉搜索树不存在平衡算法，所以在某些特殊的情况下，二叉搜索树等同于线性，出现蹩脚的情况，设计者们发现降低树的高度自然就可以提高查找效率：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9ialZ6Axc3adYH9DMWY5pJiadopfU3wnXg8SicqoicTQKNhS2vMA7nMkU7g/640?wx_fmt=png)

红黑树和 AVL 树是在二叉树的基础上机上加上平衡算法，红黑树确保没有一条路径会比其它路径长出两倍，它是弱平衡树而 AVL 是严格的平衡，所以相对于二叉树的蹩脚情况做了很大的改进，加入了平衡算法：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe95UibS1pu0r8icpu8VSdAiabvYppuUzPjweYibLGOnVicibxMnYqzGZS8fYCw/640?wx_fmt=png)

但是，同样还是存在数据量大导致树非常高的问题，所以现在的目标就是压缩树的高度。

B 树基于减少树的高度上，B 树是一种多路搜索树，每个节点都可以有多于两个子节点，并不是二叉的：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9J692h5N0vH9Gib7GIichH2xJP1J5W9RGfMic0aW6XF3DG1Ng7ZWgfyvkw/640?wx_fmt=png)

B 树与 B + 树最大的区别就是 B 的非叶子节点可以存储数据，而 B + 树只有叶子结点才可以存储数据，B 树是多路搜索树，一个节点可以存储很多数据，所以 B 树的高度大大减小。

但是 B 树相对于 B + 树来说，在查找数据的时候，由于每一个节点都有可能包含目标数据，所以查找总是从根节点进行向下搜索，这个特点会带来大量的随机 io。

而在 B + 树种，因为叶子结点才会存储数据（InnoDB），这样子相比 B 树一个页大小存储的索引数据就更多了（16K），并且叶子结点通过双向指针指向相邻的节点，依次连接。

并且相邻结点是有序的，所以对于范围查找是非常方便的，获取到第一个符合条件的，然后通过指针，往后获取数据，直到最后一个不满足条件为止。

所以总结来说：B + 树是多叉树，一个数据页的大小是 16kb，在 1-3 的树高就能存储 10 亿级以上的数据，也就是只要访问磁盘 1-3 次就足够了，并且 B + 树的叶子结点上一个叶子结点有指针指向下一个叶子结点，便于范围查询：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe98k4Xxpl8kXqU10X7AYxLicYlvh9ekgLm205icicogIkFx5xHibxcLU5QHQ/640?wx_fmt=png)

下面我们来详细的聊一聊 Mysql 索引 B + 树查询原理。

B + 树索引原理
---------

上面也大概说了一下 B + 树的介绍，在 B+Tree 中，所有数据记录节点都是按照键值大小顺序存放在同一层的叶子节点上，而非叶子节点上只存储 key 值信息，这样可以大大加大每个节点存储的 key 值数量，降低 B+Tree 的高度。

在 B + 树的结构中，只在叶子节点存储数据，在非叶子节点中只存储的索引，在非叶子节点中可以有更大的空间储存更多的索引，这样 B + 树的出度 d 就可以大大的增加，从而降低的 B + 树的高度 h，B 树中一个节点的大小为一个 page 的大小，也就是一次 IO 的读取，h 越小 IO 的次数就可以减少：

**dmax=floor(pagesize/(keysize+datasize+pointsize))**

floor 表示向下取整。由于 B+Tree 内节点去掉了 data 域，因此可以拥有更大的出度，拥有更好的性能。

我们来看看 B + 树的搜索过程，Mysql 的 InnoDB 的索引的结构如下图所示，**假设我们要搜索 id 为 15 的数据**：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9hlyOVwYt33lQxagOweQibiaTFJjBMy0OgwvvmRagkoH6fanNia0Y05Lhw/640?wx_fmt=png)

1.  根据根节点找到磁盘块 1，读入内存，一般根节点也会常驻内存，甚至可以省略一次磁盘 IO 操作。【磁盘 I/O 操作第 1 次】

2.  比较 id 15 在区间 28 的左边，于是根据 p1 找到磁盘 2。

3.  将磁盘 2 读入内存，查找结果 15 在（10,17）之间。【磁盘 I/O 操作第 2 次】

4.  然后根据磁盘 2 的指针 p2 找到磁盘块 5，读入内存。【磁盘 I/O 操作第 3 次】

5.  最后根据 id=15 找到对应的数据，返回结果。


所以根据上面的查找只需要至多三次的磁盘 IO 就可以找到对应的数据。从上面的 B + 树的原理图中非叶子节点构成了类似于一个一个目录一样，也可以叫做索引页，最后找到叶子结点的数据。

在 MySQL 中，不同存储引擎对索引的实现方式是不同的，Mysql 有 MyISAM 和 InnoDB 两个存储引擎的索引实现方式，下面就来分别介绍这两种存储引擎。

### MyISAM

在 MyISAM 储存引擎中，数据和索引文件是分开储存的，Myisam 的存储文件有三个，后缀名分别是 .frm、.MYD、MYI，其中 .frm 是表的定义文件，.MYD 是数据文件，.MYI 是索引文件。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9GucCFHgvCXJPq47quYduTvE6kAZxTZE5o3eYkyMcYSY3EGr5ZIXQibg/640?wx_fmt=png)

Myisam 只支持表锁，且不支持事务。Myisam 由于有单独的索引文件，在读取数据方面的性能很高 。

Myisam 也是 B + 树结构，但是 MyISAM 索引的叶子节点的数据保存的是行数据的地址。因此，MyISAM 中索引检索的算法首先在索引树中找到行数据的地址，然后根据地址找到对应的行数据。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9rywjAyibBG4BlFiaP4VVoGZlBpcDc7j7MCdXGm8k1aYYiaWgl5aWapMgg/640?wx_fmt=png)

可以看出 MyISAM 的索引文件仅仅保存数据记录的地址。主键索引和辅助索引，只是主索引要求 key 是唯一的，而辅助索引的 key 可以重复。如果我们在 Col2 上建立一个辅助索引，则此索引的如下图：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9iaibVo0iaRIdw0YUOwcV1HHOV9aeG3fss27YgK6ibbfvEn0HUicdUHyUODw/640?wx_fmt=png)

MyISAM 的索引方式也叫做 “非聚集” 的，之所以这么称呼是为了与 InnoDB 的聚集索引区分。

### InnoDB

在 InnoDB 中，数据和索引文件是合起来储存的，如图所示，InnoDB 的存储文件有两个，后缀名分别是 .frm 和 .idb，其中 .frm 是表的定义文件，而 idb 是数据文件。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe90gNtdro9eBhSOmQF2R49LgGT3jaVLntnNfcWtxpXCTbjK5veLcgIIQ/640?wx_fmt=png)

在 InnoDB 虽然底层也是 B + 树实现的方式，当时与 MyISAM 却有明显的区别，在 InnoDB 实现的索引结构中，索引文件和数据文件是一起的，InnoDB 中索引文件中的 key 就是数据表中的主键索引，因此 InnoDB 的索引文件也是主索引文件。如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe93tFfOqRmwjrIcpDicm7l3H8tIPNWTtegn7iaaiaTXp2D2prmUsj8j6w3g/640?wx_fmt=png)

如果给另一个字段指定为普通索引，则普通索引树的结构如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9WLKmBW32e75vf4ibicCiaD0dIFyEWmALFuKMhicLRddWrr46SP7fiagia4lg/640?wx_fmt=png)

所以，当查询不是按照主键查询时候就会先在辅助索引树上先找到主键的值，然后再到主索引树找到对应的行数据的值，这叫做回表，回表降低了表的查询效率。

Mysql 索引种类
----------

Mysql 中索引的种类也不是很多，不同类型的索引有不同的作用，索引的作用相互之间也存在交叉关系，Mysql 中索引主要分为以下几类：

1.  **「主键索引」（PRIMARY KEY）**：主键索引一般都是在创建表的时候指定，「一个表只有一个主键索引」，特点是「唯一、非空」。

2.  **「唯一索引」（UNIQUE）**：唯一索引具有的特点就是唯一性，可以在创建表的时候指定，也可以在创建表后创建。

3.  **「普通索引」（INDEX）**：普通索引唯一的作用就是加快查询。

4.  **「组合索引」（ INDEX）**：组合索引是创建一个「多个字段的索引」，这个概念是相对于上上面的单列索引而言，组合索引查询遵循「最左前缀原则」。

5.  **「全文索引」（FULLTEXT）**：全文索引是针对一些大的「文本字段」创建的索引，也称为「全文检索」。

6.  **「聚簇索引」和「非聚簇索引」**：聚簇索引和非聚簇索引的概念比上面的概念要大，属于包含和被包含的关系。例如：InnoDB 中主键索引使用的就是聚簇索引。


若是你想查看一个表的所有索引，可以执行下面的 sql 来查看：

```
show index from 表名
```

例如，查看我自己的测试表里面的索引，如下图所示，Key_name 表示索引的名字，Column_name 表示索引的字段：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe97FJeB3CZhlqZ3VQZ7w9LWCSj9ibl8SbibR5ic8OBHjvEdVF4QEoicq2XZQ/640?wx_fmt=png)

### 主键索引

主键索引在 InnoDB 存储引擎中是最常见的索引类型，一个表都会有一个主键索引，它索引的字段不允许为空值，并且唯一。

一般是在创建表的时候，可以通过 **RIMARY KEY** 指定主键索引，在 InnoDB 存储引擎中，**若是创建表的时候没有主观创建主键索引，Mysql 就会看表中是否有唯一索引，有，就会指定「非空的唯一索引」为主键索引**

**若是没有唯一索引，就会默认生成一个 6byte 空间的自动增长主键作为主键索引，可以通过 select _rowid from 表名查询的是对应的主键值**.。

MyISAM 储存引擎是可以不存在主键索引，MyISAM 和 InnoDB 储存数据的结构方式还是有明显的区别，这个后面篇章会详细讲解。

### 唯一索引

唯一索引与主键索引的区别就是，唯一索引允许为空，若是在组合索引中，只要创建的列值是唯一的

唯一索引在实际中更多的是用来保证数据的唯一性，假如你仅仅要数据能够快速查询，你也可以使用普通索引，所以唯一索引重在体现它的唯一性。

实际的业务场景，有些库表字段要求唯一，就可以使用唯一索引，创建唯一索引的方式有三种。

（1）一个是在创建表的时候指定，如下 sql：

```
CREATE TABLE user( 
 id INT PRIMARY KEY NOT NULL, 
 name VARCHAR(16) NOT NULL, 
 UNIQUE unique_name (name(10)) 
);
```

（2）也可以在表创建后创建，如下 sql：

```
CREATE UNIQUE INDEX unique_name ON user(name(10))；
```

（3）通过修改表结构创建，如下 sql：

```
ALTER user ADD UNIQUE unique_name ON (name(10))
```

这里有一个细节要注意的是创建的 name 字段，指定的长度是 16 字符，而创建的索引的长度制定的是 10 字符，因为也没有人的名字长度会超过 10 个字符，所以减少索引长度，能够减少索引所占的空间的大小。

### 普通索引

普通索引的唯一作用就是加快数据的查询，一般对查询语句 WHERE 和 ORDER BY 后面的字段创建普通索引。

创建普通索引的方式也有三种，基本和创建唯一索引的方式一样，只是把关键字 UNIQUE 换成 INDEX，如下所示：

```
// 创建表的时候创建
CREATE TABLE user( 
 id INT PRIMARY KEY NOT NULL, 
 name VARCHAR(16) NOT NULL, 
 INDEX index_name (name(10)) 
);
// 创建表后创建
CREATE INDEX INDEX index_name ON user(name(10))；
// 修改表结构创建
ALTER user ADD INDEX index_name ON (name(10))
```

若是想删除索引，可以通过执行下面的 sql 进行删除索引：

```
DROP INDEX index_name ON user;
```

### 组合索引

组合索引即用多个字段创建一个索引，组合索引能够避免「回表查询」，相对于多字段的单列索引，组合索引的查询效率更高。

创建组合索引 (联合索引) 的方式和上面创建普通索引的方式一样，只不过字段的数目多了，如下 sql 创建：

```
// 其它方式和上面的一样，这里就只列举修改表结构的方式创建
ALTER TABLE employee ADD INDEX name_age_sex (name(10),age,sex);
```

#### 回表查询

什么是回表查询呢？回表查询简单来说「通过二级索引查询数据，得不到完整的数据行，需要再次查询主键索引来获得数据行」。

InnoDB 存储引擎中，索引分为 「聚簇索引」和「二级索引」，主键索引就是聚簇索引，其它的索引为二级索引。

聚簇索引中的叶子节点保存着完整的数据行，而二级索引的叶子节点并不是保存完整的数据行。

上面提到 InnoDB 表是一定要有主键索引的，虽然索引占据空间，但是索引符合二分查找的算法，查找数据非常的快。

假设还是上面的 employee 表，里面有主键索引 id，和普通的索引 name，那么在 InnoDB 中就会存在两棵 B+Tree，一棵是主键索引树：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9Ln71cNibqZY0FG6179ff4JjXBBI547eC9FOZbNZwicN1hlq3JVHh3J6Q/640?wx_fmt=png)

在主键索引树中的叶子节点存储的是完整的数据行，另外一棵是 name 字段的二级索引树，如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9zP3gtYsOoQSQcjWMKpMbqHVRl4meZcQwo7bUUoxch14nlEsjjiaSXNg/640?wx_fmt=png)

倘若你执行这条 sql：**select name, age, sex from employee where name ='as'**; 就会先执行二级索引的查询，当查询 name='as'时，得到主键为 50，再根据主键查询主键索引树，得到完整的数据行，具体的执行流程如下：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9kKDzAdmzJQG9yCts5z4XKjsib00IhibYkY8MaTN6ibMLv7C1wM87c6Y7A/640?wx_fmt=png)

这个就是回表查询，回表查询会查询两次，这样就会降低查询的效率，为了避免回表查询，只查询一次就能得到完整的数据呢？

#### 索引覆盖

**常见的方式就是「建立组合索引（联合索引）「进行」索引覆盖」**，什么是索引覆盖呢？**索引覆盖就是「索引的叶子节点已经包含了查询的数据，没必要再回表进行查询。」**

假如我还是执行如下 sql：**select name, age, sex from employee where name ='as'**; 因为普通索引只有 name 字段才建立了索引，这必然会导致回表查询。

为了提高查询效率，就 (name)「单列索引升级为联合索引」(name, age, sex) 就不同了。

因为建立的联合索引，在二级节点的叶子阶段就会同时存在 name, age, sex 三个的值，一次性就会获得所需要的数据，这样就避免了回表，但是所有的方案都不是完美的。

若是这个联合索引哪一天某一个数据行的 name 值改变了或者 age 改变了，我就需要同时维护主键索引和联合索引两棵树，这样的维护成本就高了，性能开销也大了。

相比之前数据的改变，我只需要维护主键索引即可，联合索引的创建就导致了需要同时维护两棵树，这样就会影响插入、更新数据的操作，所以并没有哪种方案是完美的。

#### 最左前缀原则

我们知道单列索引是按照索引列有序性的进行组织 B+Tree 结构的，联合索引又是怎么组织 B+Tree 呢？

联合索引其实也是按照创建索引的时候，最左边的进行最开始的排序，也就是「最左前缀原则」，比如一个表中有如下数据：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9kzPlmWTUYIOLa1eK2Pcvm17aic6tltD2Fhk1wM5ftbibvic8DHzg9KNfw/640?wx_fmt=png)

如上图所示，对于联合索引中 name 字段是放在最前面的，所以 name 是完全有序的，但是 age 字段就不是有序的，只有当 name 相同，例如：name='bc'此时 age 字段的索引排序才是完全有序的。

所以你会发现，在联合索引中你只有使用以下的规则的方式查询才会使用到索引：

*   name,age,sex

*   name,age

*   name


**因为 Mysql 的底层有查询优化器，会判断 sql 执行的时候若是使用全表扫描的效率比使用索引的效率更高，就会使用全表扫描。**

假如，我查询的时候使用 **age>=23,sex='男'**; 两个字段作为查询条件，但是没有使用 name 字段，因为在 name 不知情的条件下，对于 age 是无序的。

对于 age>=23 条件可能在很多的 name 不同中都有符合条件的出现，所以就没有办法使用索引，这也是索引实现的原因，一定要遵循「查找有序，充分的利用索引的有序性」。

假如你是分别在 name，age，sex 三个字段中分别建立三个单列索引，就相当于建立三颗索引树，那么它的查询效率，比我们使用一棵索引树查询效率就可想而知了。

有一种情况即使使用到了最左边的 name 字段也不会使用索引，例如：WHERE name like '%d%'；这种 like 条件的模糊查询是会使索引失效。

我们可以这样理解，「查询字符串也是遵循最左前缀原则的」，字符串的查询是对字符串里面的字符一个一个的匹配，「若是字符串最左边为 % 表示一个不确定的字符串，那么是没办法利用到索引的有序性」。

但是若是修改为 ：WHERE name like 'd%'；就可以使用索引，因为最左边的字符串是确定的，这种称为「匹配列前缀」。

实际业务场景中联合索引的创建，「我们应该把识别度比较高的字段放在前面，提高索引的命中率，充分的利用索引」。

#### 索引下推

Mysql5.6 版本提出了索引下推的原则，「用于查询优化，主要是用于 like 关键字的查询的优化」，什么是索引下推呢？

下面通过演示来说明一下他的概念，还是利用原来的 employee 测试表，假如我要执行下面的 sql 进行查询：SELECT * from user where name like '张 %' and age=40；

假如没有索引下推，执行的过程如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9wewnmZTmBxczpZy1w39xNR870HACqPC5ynZicyzNW7xcsnqicHfDJU2w/640?wx_fmt=png)

查询会直接忽略 age 字段，将 name 查询的张开头的 id=5、id=7 的结果返回给 Mysql 服务器，再执行两次的回表查询。

若是上面的查询操作使用了索引下推，执行的过程如下：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9eiaibHXU28SHa0talQdyx68D7TrBd2hibpicxxc0Yg5J45PcvF9BocPlIQ/640?wx_fmt=png)

Mysql 会将查询条件 age=40 的查询条件传递给存储引擎，再次过滤掉 age=50 的数据行，这样回表的次数就变为了一次，提高了查询效率。

总结起来索引下推就是在执行 sql 查询的时候，会将一部分的索引列的判断条件传递给存储引擎，由存储引擎通过判断是否符合条件，只有符合条件的数据才会返回给 Mysql 服务器。

### 全文索引

全文索引也称为全文检索，可以通过以下 sql 建立全文索引：ALTER TABLE employee ADD FULLTEXT fulltext_name(name); 或者 CREATE INDEX 的方式创建。

全文索引主要是针对 CHAR、VARCHAR 或 TEXT 这种文本类的字段有效，有人说不也可以使用 like 关键字来查询文本吗。

普通索引（单列索引）的查询只能加快字段内容中最前面的字符串的检索，若是对于多个单词组成文本的查询普通索引就无能为力了。

索引一经创建就没有办法修改，若是想要修改索引，必须重建，可以使用以下 sql 来删除索引：DROP INDEX fulltext_name ON employee;

### 聚簇索引和非聚簇索引

聚簇索引和非聚簇索引是相对于存储引擎的概念，范围比较大，包含上面所提到的索引类型。

「聚簇索引就是叶子节点中存储的就是完整的行数据，索引和数据存储在一起；而非聚簇索引的索引文件和数据文件是分开的，所以查询数据会多一次查询」。

因此聚簇索引的查询速度会快于非聚簇索引的查询速度，在 Mysql 的存储引擎中，「InnoDB 支持聚簇索引，MyISAM 不支持聚簇索引，MyISAM 支持非聚簇索引」。

#### 聚簇索引

下面我们来看看 InnoDB 中的聚簇索引，前面说到 InnoDB 都会有一个主键，该主键就是用于支持聚簇索引，聚簇索引结构图，大致如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9UvmiceZ5ausspKJCrRkgD3zcFCzPJRYEgEOXuLXmvFAwCGkic1yPTByQ/640?wx_fmt=png)

InnoDB 中适用于最好的主键选择就是给出一个 AUTO_INCREMENT 的列作为自增的主键，有的人可能会使用 UUID 作为随机主键。

因为索引要维持有序性，若是使用随机的主键，主键的插入需要寻找合适的位置进行放置，这样维护主键索引树的成本就会变得更高。

相反的，自增主键，主键都是自增变大，在维护主键索引树的成本就会变得更小，随意应该尽量避免随机主键。

#### 非聚簇索引

MyISAM 使用的是非聚簇索引，新插入数据的时候，会按顺序的写入的磁盘中，并且给每一行数据标记一个行号，从小逐渐增大。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9ia1BH19Eg1qGHvMdyfp6U1TnedzWB8pY859eLXVYCCgGSGwYzHk98kA/640?wx_fmt=png)

当 MyISAM 创建主键索引的时候，形成的主键索引树的结构图如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9aYicPuySkiahVfrfUrx6vZQph2AMia8jbhz12ujgl6jzyjOicmYOFcEibuw/640?wx_fmt=png)

在主键索引中，数据也是非空且唯一，主键索引树中存储的是数据行的行号，当查询数据的时候使用主键索引查询需要查询到行号，然后通过行号获取数据。

非主键索引和主键索引一样叶子节点也是存储着行号，唯一的区别就是非主键索引不要求非空、唯一。

我们可以通对比图来对比一下「InnoDB(聚簇索引)」 和 「MyISAM(非聚簇索引)」 的索引数据布局，如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9TUNOVibC066KOoHj5u2upMOjChSwUyhbDiclJCjW3cKHw0b9N8DDe7Ew/640?wx_fmt=png)

说到这里相信应该大家对于 **「InnoDB(聚簇索引)」** 和 **「MyISAM(非聚簇索引)」** 有了非常清晰的认识和理解，下面是来说一说索引的优化，这个也是和我们日常开发最密切相关的。

索引优化
----

以个人多年（实际就两年）优化经验来看，Mysql 索引优化无非就是以下三点：

1.  最好的利用索引来进行查询

2.  避免全表扫描

3.  复杂查询，避免扫描无效的数据


### 优化前提

Explain 关键字是 Mysql 中 sql 优化的常用「关键字」，通常都会使用 Explain 来「查看 sql 的执行计划，而不用执行 sql」，从而快速的找出 sql 的问题所在。

在讲解 Explain 之前首先创建需要的「用户表 user、角色表 role、以及用户角色关系表 role_user」作为测试用的表：

```
// 用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `age` int(11)  NOT NULL DEFAULT 0,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` (`id`, `name`, `age`,`update_time`) VALUES (1,'张三',23,'2020-12-22 15:27:18'), (2,'李四',24,'2020-06-21 15:27:18'), (3,'王五',25,'2020-07-20 15:27:18');

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `role` (`id`, `name`) VALUES (1,'产品经理'),(2,'技术经理'),(3,'项目总监');

DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_role_user_id` (`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `role_user` (`id`, `role_id`, `user_id`) VALUES (1,2,1),(2,1,2),(3,3,3); 
```

我们首先执行一条 sql：explain select * from user where id =2;，执行后可以看到执行的结果如下：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9YtHWxk4ZrZQkrSiaVvReibD6IADokweVgupAIKDxaXWQZExAokfM392w/640?wx_fmt=png)可以看到这里有 12 个字段那个且都有对应的值，这就是 explain 的执行计划，能看懂这个执行计划，你离精通 sql 优化就不远了，下面就来详细的介绍这 12 个字段分别表示什么意思。

#### id 字段

id 表示执行 select 查询语句的序号，它是 sql 执行的顺序的标识，sql 按照 id 从大到小执行，id 相同的为一组，从上到下执行。

什么意思呢？例如执行这条 sql：explain select * from user where id in (select user_id from role_user);

```
+----+-------------+-----------+------------+-------+---------------+--------------------+---------+------+------+----------+-----------------------------------------------------------------------------------+
| id | select_type | table     | partitions | type  | possible_keys | key                | key_len | ref  | rows | filtered | Extra                                                                             |
+----+-------------+-----------+------------+-------+---------------+--------------------+---------+------+------+----------+-----------------------------------------------------------------------------------+
|  1 | SIMPLE      | user      | NULL       | ALL   | PRIMARY       | NULL               | NULL    | NULL |    3 |   100.00 | NULL                                                                              |
|  1 | SIMPLE      | role_user | NULL       | index | NULL          | index_role_user_id | 8       | NULL |    3 |    33.33 | Using where; Using index; FirstMatch(user); Using join buffer (Block Nested Loop) |
+----+-------------+-----------+------------+-------+---------------+--------------------+---------+------+------+----------+-----------------------------------------------------------------------------------+
```

显示出的两者的 id 都相同，便表示 sql 的执行从上往下执行，第一条记录对应的是 user 表，然后第二条记录对应的是 role_user 表，这种是 id 相同的情况。

若是 id 不同，例如执行下面的 sql：explain select (select 1 from user limit 1) from role;：

```
+----+-------------+-------+------------+-------+---------------+------------+---------+------+------+----------+-------------+
| id | select_type | table | partitions | type  | possible_keys | key        | key_len | ref  | rows | filtered | Extra       |
+----+-------------+-------+------------+-------+---------------+------------+---------+------+------+----------+-------------+
|  1 | PRIMARY     | role  | NULL       | index | NULL          | index_name | 33      | NULL |    3 |   100.00 | Using index |
|  2 | SUBQUERY    | user  | NULL       | index | NULL          | PRIMARY    | 4       | NULL |    3 |   100.00 | Using index |
+----+-------------+-------+------------+-------+---------------+------------+---------+------+------+----------+-------------+
```

就会看到有两条记录，并且两条记录的 id 会不一样，id 越大的就越先执行，可以看到 id=2 的执行的是 user 表，也就是子查询部分，最后执行最外层的部分。

「结论：」 这个就是 id 标识 sql 的执行顺序，一般在复杂查询中会有多条记录，简单查询只有一条记录，复杂查询中 id 相同的为一组，执行的顺序是从上往下，而 id 越大的越先执行；Mysql 8 中会存在对子查询进行优化，所以有时候即使是复杂查询，也只有一条记录。

#### select_type 字段

select_type 表示查询的类型，也就是对应的是简单查询还是复杂查询，若是复杂查询又包含：「简单的子查询、from 子句的子查询、union 查询」。下面就分别来看看 select_type 中的所有查询类型。

*   **simple**simple 表示简单查询，不含有任何的复杂查询。![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9wthjhDBnac3P242ZiadfzsJamUYeMnW1f4djDGyZgicO3VmZsn3PNJBA/640?wx_fmt=png)

*   **PRIMARY** 复杂查询中「最外层的 select 语句的查询类型就是 PRIMARY」，例如执行下面的 sql：explain select * from role where id = (select id from role_user where role_id = (select id from user where id = 2));![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9RMBpdDjibXYd6QiarYfGcIHQQYocTJXxxOsDeWgRxdk8Pyr2Pj0b71mQ/640?wx_fmt=png) 最外层的 select，也就是 select * from role where id =？会被标记为 PRIMARY 类型。

*   **SUBQUERY** 在「select 或者 where 中包含的子查询」会被表示为 SUBQUERY 类型，例如上一句执行的 sql 中就有两次的子查询为 SUBQUERY。![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9ZGiaf8qeVWqIW2dW7GItwzt9nPIJmhT1Ey6AuJK6QeJT4RoFem3YOPQ/640?wx_fmt=png)

*   **DERIVED**「DERIVED 表示的是派生表或者衍生表的意思，在 from 包含的子查询中会被表示为 DERIVED 类型」，Mysql 会递归执行这些子查询，并且把结果放在临时表中。执行 sql：explain select * from (select name from user union select name from role) a where a.name = '张三';![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9CydJ4wbTcAvlhwKzCPUEJqRnSd88Ohm79pDWh0lNQSM98PC7AXqibEA/640?wx_fmt=png) 在 Mysql 5.7 以上的版本中对其做了优化，新增了 derived_merge(派生合并)，可以加快查询效率。

*   **UNION** 在出现「UNION 查询语句中，第二个 select 的查询语句就会被表示为 UNION」：![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9jddMia5hjRZrX9sXfS4JIDcjrib5gWzAZJFoJ9aGHYKLicmyvzVDxATkg/640?wx_fmt=png)

*   **UNION RESULT**「UNION 查询语句的结果被标记为 UNION RESULT」，如上面执行的 sql：explain select * from (select name from user union select name from role) a where a.name = '张三';![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9IUJjcfKVsSURupSEROvY1ZSOL1Vs9YZAuib2B1WRiclOhPNbBF9uPAcg/640?wx_fmt=png) 第四行记录中从 table 字段中可以看出，第四行的记录来源于第二行和第三行 <union2,3>，因此一个 UNION 查询语句的结果就会被标记为 UNION RESULT

*   **DEPENDENT UNION**：也表示 UNION 查询语句中第二个或者后面的语句，但是取决于外面的查询。

*   **DEPENDENT SUBQUERY**：子查询中的第一个 select 语句，也是依赖于外部的查询。

*   **UNCACHEABLE SUBQUERY**：子查询的结果不能被缓存，必须重新评估外连接的第一行。


#### table 字段

这个很容易看出「table 字段表示的是查询的是哪个表」，一个是已经存在的表，比如上面的 user、role 都是我们自己创建的表，也可以表示衍生表。

比如：UNION RESULT 的 table 字段表示为 <union2,3>，也就是查询的是第二行和第三行的结果记录。

#### type 字段

「type 字段表示的 sql 关联的类型或者说是访问的类型」。从这个字段中我们可以确定这条 sql 查找数据库表的时候，查找记录的大概范围是怎么样的，直接就能体现 sql 的效率问题。

type 字段的类型也是有比较多，主要常见掌握的有以下几个：system、const 、eq_ref 、ref 、range 、index 、ALL。它的性能体现是从高到低，即 system > const > eq_ref > ref > range > index > ALL，下面就来详细的说一说这属性。

##### system

system 是 const 的特例，「表示表中只有一行记录」，这个几乎不会出现，也作为了解。

##### const

const 表示通过索引一次就查找到了数据，一般 const 出现在「唯一索引或者主键索引中使用等值查询」，因为表中只有一条数据匹配，所以查找的速度很快。例子：explain select * from user where id =2;

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe91ZKnzcianXjaCM5ibXGZXyYrSAwhr9LQib6wWQ028Jxc6wzfjkKNEOOQQ/640?wx_fmt=png)

##### eq_ref

eq_ref 表示使用唯一索引或者主键索引扫描作为表链接匹配条件，对于每一个索引键，表中只有一条记录与之匹配。例如：explain select * from user left join role_user on user.id = role_user.user_id left join role on role_user.role_id=role.id;

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9GT6OxbKSIYJDE2eZDbVh91Xjk0C4Dsnc37ia6LhPtDBeFLqz2bC9eww/640?wx_fmt=png)

##### ref

ref 性能比 eq_ref 差，也表示表的链接匹配条件，也就是使用哪些表字段作为查询索引列上的值，ref 与 eq_ref 的区别就是 eq_ref 使用的是唯一索引或者主键索引。

ref 扫描后的结果可能会找到多条符合条件的行数据，本质上是一种索引访问，返回匹配的行。例如：explain select * from user where name = '张三';

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9ibLTl8nrqRzXd7lQoDibEtgze1iaC6kkUy7IUhlmbQuW2YOa6mOfOqDww/640?wx_fmt=png)

##### range

「range 使用索引来检索给定范围的行数据，一般是在 where 后面使用 between、<>、in 等查询语句就会出现 range」：explain select * from user where id> 2;

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9ibvHhYFz7CW632oKtR5sApQ3quFfTtSCJrbLGB0147JUzYcbLTCwNyA/640?wx_fmt=png)

##### index

index 表示会遍历索引树，index 回避 ALL 速度快一些，但是出现 index 说明需要检查自己的索引是否使用正确：explain select id from user;

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9MHTjTGFMImbiajsrCUWUjXktpUJpqibvQrW9oDMxpfNkvATkN9ZwFiaFA/640?wx_fmt=png)

##### ALL

「ALL 与 index 的区别就是 ALL 是从硬盘中读取，而 index 是从索引文件中读取」，ALL 全表扫描意味着 Mysql 会从表的头到尾进行扫描，这时候表示通常需要增加索引来进行优化了，或者说是查询中并没有使用索引作为条件进行查询：explain select * from user;

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9Ptzrq7iber75521wDiaZUAZvjia6zKqmH2wGJolSoVWERLkgUicR7R3ouQ/640?wx_fmt=png)

#### possible_keys 字段

possible_keys 表示这一列查询语句可能使用到的索引，仅仅只是可能，列出来的索引并不一定真正的使用到。

当没有使用索引为 NULL 时，说明需要增加索引来优化查询了，若是表的数据比较少的话，数据库觉得全表扫描更快，也可能为 NULL。

#### key 字段

key 字段与 possible_keys 的区别就是，表示的真正使用到的索引，即 possible_keys 中包含 key 的值。

若是想 Mysql 使用或者忽视 possible_keys 中的索引，可以使用 FORCE INDEX、USE INDEX 或者 IGNORE INDEX。

#### key_len 字段

表示 sql 查询语句中索引使用到的字节数，这个字节数并不是实际的长度，而是通过计算查询中使用到的索引中的长度得出来的，显示的是索引字段最大的可能长度。

一般来说在不损失精度的前提下，key_len 是越小越好，比如上面的测试表的 id 为 int 类型，int 类型由 4 个字节组成：explain select * from user where id =2;

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe96JO77hVQCj7ToRf67oDSon1yKsNCEOmesm8ClltbdgUqRCGnc9GEpw/640?wx_fmt=png)

key_len 对于不同的类型有自己的计算规则，具体的计算规则如下所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9tPnGkp3HgMRrRda1BjlyJ7VaH9UrYuOZOSGLBe6oficpiay3m87bZZSQ/640?wx_fmt=png)若是索引为字符串类型的时候，实际存储的字符串非常长，已经超出了字符串类型的存储最大长度（768 字节），mysql，就会使用类似左前缀索引来处理。

#### ref 字段

ref 表示列与索引的比较，表连接的匹配条件，表示哪些列或者常量被用于查询索引列上的值。

#### rows 字段

rows 表示估算的要扫描的行数，一般 Mysql 会根据统计表信息和索引的选用情况，估算出 查找记录所要扫描的行数，注意这个并不是实际结果集的行数。

#### partitions、filtered 字段

partitions 表示所匹配的分区；filtered 表示的是查询表行所占表的百分比。

#### Extra 字段

该字段显示的是 sql 查询的额外信息，主要有以下几种情况：

##### Using index

表示查询的列被索引覆盖，这个是查询性能比较高的体现，即所要查询的信息搜在索引里面可以得到，不用回表，索引被正确的使用：explain select id from user where id =2;

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9eZhQnBvk6tUC3zmxoSS6nKC9zPZ8zwhgbmY3fib44toKUYAmnauWEnw/640?wx_fmt=png)

假如同时出现了 using where，表示索引用于执行索引键值的查找；若是没有出现 using where，则表示索引用于读取数据，而非执行查询的动作。

##### Using where

该属性与 Using index 相反，查询的列并没有被索引覆盖，where 条件后面使用的是非索引的前导列，它仅仅是使用了 where 条件而已：explain select user.* from user,role,role_user where user.id = role_user.user_id and role.id=role_user.role_id;

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9BlCVkBvT5gOopialTuGh6Fa1PR5TIaWKxriaibic2zgL3dBka1kic4FPwDg/640?wx_fmt=png)

##### Using temporary

「Using temporary 表示使用了临时表存储中间的结果，一般在对结果排序的时候会使用临时表」，例如：排序 order by 和分组查询 group by。例子：explain select * from (select name from user union select name from role) a where a.name = '张三';

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9IUJjcfKVsSURupSEROvY1ZSOL1Vs9YZAuib2B1WRiclOhPNbBF9uPAcg/640?wx_fmt=png)

##### Using filesort

Using filesort 表示文件排序，说明 Mysql 对数据使用了外部的索引进行排序，并没有使用表中的索引进行排序：explain select * from user order by name;

##### Using join buffer

Using join buffer 表示使用连接缓存：explain select user.* from user,role,role_user where user.id = role_user.user_id and role.id=role_user.role_id;

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9pb85pa1Pjetjicc9iaFsciaIRncOvtWLwRHxQAbbic0B4CYsia4huA3eQrw/640?wx_fmt=png)

它强调在获取连接条件时，并没有使用索引，而是使用连接缓冲区来存储中间结果，若是出现该值，一般说明需要添加索引来进行优化了。

##### Impossible where

Impossible where 会出现在 where 后的条件一直为 false 的情况下，这种可以忽视，比较少出现：explain select * from user where name = 'hah' and name = 'sfsd';

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9JicWmbXYySLZzD8pnK695pFWMoKNllfWeHSdsWiaaUPeInlibZCNPkzIQ/640?wx_fmt=png)

##### Select tables optimized away

表示 select 语句没有遍历表或者索引就返回数据了，比如：explain select min(id) from user;

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliamr9smtlfLWYHerQvqFfe9TS9qLs3NkBztk8ljW9y5L6kXa5jfXLOohJo5qiawqRkJgDNwhyFDqTQ/640?wx_fmt=png)

在 Extra 字段中还有其它的属性，但是几乎都没见过的，不出现，所以哪些就讲解，有兴趣的可以自己去了解，这里只列出这些常见的。

### 总结

下面我来总结一下要正确的使用索引的一些规则。要正确的创建索引，用索引正确的查询，不要使索引失效，因此索引的设计和优化的原则应该遵循下面的几个原则：

1.  索引列不要在表达式中出现，这样会导致索引失效。如：「SELECT  ...... WHERE id+1=5」;

2.  索引列不要作为函数的参数使用。

3.  索引列尽量不要使用 like 关键字。如：「SELECT  ...... WHERE name like '%d%'」。可以使用 Mysql 内置的函数 INSTR(str,substr) 来匹配，查询字符串出现的下表的位置；也可以是使用 FullText 全文索引，用 match against 检索; 假如数据量非常大的话建议使用 es 或者 solr 来替代。

4.  数字型的索引列不要当作字符串类型进行条件查询。如：「SELECT  ...... WHERE id = '35'」;

5.  尽量不要在条件 not in、<>、!= 、or 中使用索引，其中 in 关键字也要慎重使用，在控制 in 条件的数量在 1000 以内，如果 in 后面的条件太多，会导致全表扫描，这个在阿里巴巴的开发手册也有说明；建议使用 between 来替代 in 或者使用子查询 exists 代替；or 关键字建议使用 union 来替代优化。

6.  建表规范不建议在表中默认值是 null 的情况，一般建表都会一个默认值，比如 0 或者空字符串，防止出现一些空指针的现象。

7.  在索引列的字段中不要出现 NULL 值，NULL 值会使索引失效，可以用特殊的字符比如空字符串' '或者 0 来代替 NULL 值。

8.  联合索引的查询应该遵循最左前缀原则。

9.  一般对于区别性比较大的字段建立索引，在联合索引中区别性比较大（识别度比较高）放在最前面，提高索引的命中率，在 mybatis 的 xml 文件中多条件 where 查询时，命中索引的条件放在前面，能使用联合索引就不要使用单列索引。

10.  where、order by 、 group by 后面的条件尽量使用索引进行优化 ，可以使用 exlpain 对查询进行优化。

11.  复杂查询中建议使用 inner on 来做关联，因为在 mysql 优化器中 inner on 会有限使用小表关联大表，on 后面的条件要建立索引，左关联和右关联应该遵循小表带大表的规则。

12.  微服务分布式系统下的库表设计原则不要进行跨库的 join 操作，一个库的数据量若是比较大，应该减少或者尽量不要 join 操作，要进行 join 操作应该避免 join 的无效的数据，保持接口的单一原则，减少 join 操作的方法可以字段冗余，对于一些很久都不会改变的字段，可以考虑冗余，从而减少 join 操作，阿里巴巴的手册中也有提到，不允许超过三个 join，宁愿分多次查询。

13.  业务优化考虑原则：读多写少场景或者读少写多场景，一般互联网的都是读多写少场景，对于一些需要进行统计的字段，比如一些首页报表、数据面板，需要大量统计的接口，尽量减少 Mysql 来进行统计，避免慢 sql，优化的方法，可以牺牲部分写的性能来提升读的新能，提前在写入的时候通过异步的方式进行统计（比如统计当前月的积分，一年的绩效等数据），有些数据量涉及的数据本身比较大，如果用 Mysql 的进行查询的时候统计，那必然导致慢 sql。

14.  查询优化对于多次同步调用服务的接口可以通过异步的的方式进行调用，减少接口的响应时间。

15.  索引的大小要适度，不易过大，避免索引的冗余。


兄弟，我看你骨骼惊奇，我将我毕生功力传授给你，要将这绝世武功发扬光大，就靠大家了，开个玩笑。

好了这一期就到这里，我是黎杜，我们下一期继续来深入 Mysql，下一期见。

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