> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/9bZR2FrfU7h9Son8EqMWCQ)

前言
--

大家好呀~ 我是捡田螺的小男孩，今天跟大家聊聊日常开发中，如何减少 bug？本文将从**数据库、代码层面、缓存使用篇** 3 个大方向，总结出一共 50 多个注意点，助大家成为开发质量之星。![](https://mmbiz.qpic.cn/mmbiz_png/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52SgQ2jaTaTlBiaHctnan8P7ibovdn9WRCt4ALsSzEqS9y7EjXZ9Z9XNF2g/640?wx_fmt=png)

*   欢迎关注公众号：**捡田螺的小男孩**


1. 数据库篇
-------

![](https://mmbiz.qpic.cn/mmbiz_png/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52SkCAcpBCdT8ClwML8gvmxPr6c1EjJficD6xlxiaUyv2BtH3ia0WibPPk03g/640?wx_fmt=png)

慢查询

数据库篇的话，哪些地方容易导致 bug 出现呢？我总结了 7 个方面：**慢查询、数据库字段注意点、事务失效的场景、死锁、主从延迟、新老数据兼容、一些 SQL 经典注意点**。

### 1.1 慢查询

![](https://mmbiz.qpic.cn/mmbiz_gif/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52Suzj8lH3Vib99XWziceXY9a9nulFfBD6odibfek43gPzUT1zHrBJZFstXw/640?wx_fmt=gif)

慢查询. gif

#### 1.1.1 是否命中索引

提起慢查询，我们马上就会想到加索引。如果一条 SQL 没加索引，或者没有命中索引的话，就会产生慢查询。

**索引哪些情况会失效？**

*   查询条件包含 or，可能导致索引失效

*   如何字段类型是字符串，where 时一定用引号括起来，否则索引失效

*   like 通配符可能导致索引失效。

*   联合索引，查询时的条件列不是联合索引中的第一个列，索引失效。

*   在索引列上使用 mysql 的内置函数，索引失效。

*   对索引列运算（如，+、-、*、/），索引失效。

*   索引字段上使用（！= 或者 < >，not in）时，可能会导致索引失效。

*   索引字段上使用 is null， is not null，可能导致索引失效。

*   左连接查询或者右连接查询查询关联的字段编码格式不一样，可能导致索引失效。

*   mysql 估计使用全表扫描要比使用索引快, 则不使用索引。


#### 1.1.2 数据量大，考虑分库分表

单表数据量太大，就会影响 SQL 执行性能。我们知道索引数据结构一般是 B + 树，一棵高度为 3 的 B + 树，大概可以存储两千万的数据。超过这个数的话，B + 树要变高，查询性能会下降。

因此，数据量大的时候，建议分库分表。分库分表的中间件有 **mycat、sharding-jdbc**

#### 1.1.3 不合理的 SQL

日常开发中，笔者见过很多不合理的 SQL：比如一个 SQL 居然用了 **6 个表连接**, 连表太多会影响查询性能；再比如一个表，居然加了 **10 个索引**等等。索引是会降低了插入和更新 SQL 性能，所以索引一般不建议太多，一般不能超过五个。

### 1.2 数据库字段注意点

数据库字段这块内容，很容易出 bug。比如，你测试环境修改了表结构，加了某个字段，忘记把脚本带到生产环境，那发版肯定有问题了。

#### 1.2.1 字段是否会超长

假设你的数据库字段是：

```
`name` varchar(255) DEFAULT NOT NULL
```

如果请求参数来了变量 name，字段长度是 300，那插入表的时候就**报错**了。所以需要校验参数，防止字段超长。

#### 1.2.2 字段为空，是否会导致空指针等

我们设计数据库表字段的时候, 尽量把字段设置为 **not null**。

*   如果是整形，我们一般使用 0 或者 - 1 作为默认值。

*   如果字符串，默认空字符串


如果数据库字段设置为`NULL`值，容易导致程序空指针；如果数据库字段设置为`NULL`值，需要注意 **count(具体列)** 的使用，会有坑。

#### 1.2.3 字段缺失

我们的日常开发任务，如果在测试环境，对表进行修改，比如添加了一个新字段，必须要把 SQL 脚本带到生产环境，否则字段缺失，发版就有问题啦。

#### 1.2.4 字段类型是否支持表情

如果一个表字段需要支持表情存储，使用 **utf8mb4**。

#### 1.2.5 谨慎使用 text、blob 字段

如果你要用一个字段存储文件，考虑**存储文件的路径**，而不是保存整个文件下去。使用 text 时，涉及查询条件时，注意创建**前缀索引**。

### 1.3 事务失效的场景

#### 1.3.1 @Transactional 在非 public 修饰的方法上失效

@Transactional 注解，加在非 public 修饰的方法上，事务是不会生效的。spring 事务是借鉴了 AOP 的思想，也是通过动态代理来实现的。spring 事务自己在调用动态代理之前，已经对非 public 方法过滤了，所以非 public 方法，事务不生效。

#### 1.3.2 本地方法直接调用

以下这个场景，@Transactional 事务也是无效的

```
public class TransactionTest{
  public void A(){
    //插入一条数据
    //调用方法B (本地的类调用，事务失效了)
    B();
  }
  
  @Transactional
  public void B(){
    //插入数据
  }
}
```

#### 1.3.3 异常被 try...catch 吃了，导致事务失效。

```
@Transactional
public void method(){
  try{
    //插入一条数据
    insertA();
    //更改一条数据
    updateB();
  }catch(Exception e){
    logger.error("异常被捕获了，那你的事务就失效咯",e);
  }
}
```

#### 1.3.4 rollbackFor 属性设置错误

Spring 默认抛出了未检查`unchecked`异常（继承自 RuntimeException 的异常）或者 Error 才回滚事务；其他异常不会触发回滚事务。如果在事务中抛出其他类型的异常，就需要指定`rollbackFor`属性。

#### 1.3.5 底层数据库引擎不支持事务

MyISAM 存储引擎不支持事务，InnoDb 就支持事务

#### 1.3.6 spring 事务和业务逻辑代码必须在一个线程中

业务代码要和 spring 事务的源码在同一个线程中，才会受 spring 事务的控制。比如下面代码，方法 mothed 的子线程，内部执行的事务操作，将不受 mothed 方法上 spring 事务的控制，这一点大家要注意。这是因为 spring 事务实现中使用了 ThreadLocal，实现同一个线程中数据共享。

```
@Transactional
public void mothed() {
    new Thread() {
      事务操作
    }.start();
}
```

### 1.4 死锁

死锁是指两个或多个事务在同一资源上相互占用，并请求锁定对方的资源，从而导致恶性循环的现象。

![](https://mmbiz.qpic.cn/mmbiz_png/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52SQGjHBlDML2IcgbtptrUXOcBLejAnh06IN68mru00icIcNNn2PK3WpGQ/640?wx_fmt=png)

MySQL 内部有一套死锁检测机制，一旦发生死锁会立即回滚一个事务，让另一个事务执行下去。但死锁有**资源的利用率降低、进程得不到正确结果**等危害。

#### 1.4.1 9 种情况的 SQL 加锁分析

要避免死锁，需要学会分析：一条 SQL 的加锁是如何进行的? 一条 SQL 加锁，可以分 9 种情况进行探讨：

*   组合一：id 列是主键，RC 隔离级别

*   组合二：id 列是二级唯一索引，RC 隔离级别

*   组合三：id 列是二级非唯一索引，RC 隔离级别

*   组合四：id 列上没有索引，RC 隔离级别

*   组合五：id 列是主键，RR 隔离级别

*   组合六：id 列是二级唯一索引，RR 隔离级别

*   组合七：id 列是二级非唯一索引，RR 隔离级别

*   组合八：id 列上没有索引，RR 隔离级别

*   组合九：Serializable 隔离级别


#### 1.4.2 如何分析解决死锁？

分析解决死锁的步骤如下：

*   模拟死锁场景

*   show engine innodb status; 查看死锁日志

*   找出死锁 SQL

*   SQL 加锁分析，这个可以去官网看哈

*   分析死锁日志（持有什么锁，等待什么锁）

*   熟悉锁模式兼容矩阵，InnoDB 存储引擎中锁的兼容性矩阵。


有兴趣的小伙伴，可以看下我之前写的这篇文章：[手把手教你分析 Mysql 死锁问题](https://mp.weixin.qq.com/s?__biz=Mzg3NzU5NTIwNg==&mid=2247487979&idx=1&sn=588c83d77a8851f3b3c18cd68ed9c454&chksm=cf21cec2f85647d4a77cc239ae9a4cfd31bb8832be3d98540a08ea8b4a1f46b38cf736210a02&token=1327808550&lang=zh_CN&scene=21#wechat_redirect)

### 1.5 主从延迟问题考虑

先插入，接着就去查询, 这类代码逻辑比较常见，这可能会有问题的。一般数据库都是有主库，从库的。写入的话是写主库，读一般是读从库。如果发生主从延迟，，很可能出现你插入成功了，但是查询不到的情况。

![](https://mmbiz.qpic.cn/mmbiz_gif/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52SgcAJlPTwbyPyOuA0bicTVowflZWcILlKH9w3pF7hnMo7RbKFSus7Uiag/640?wx_fmt=gif)

#### 1.5.1 要求强一致性，考虑读主库

如果是重要业务，要求强一致性，考虑直接读主库

#### 1.5.2 不要求强一致性，读从库

如果是一般业务，可以接受短暂的数据不一致的话，优先考虑读从库。因为从库可以分担主库的读写压力，提高系统吞吐。

### 1.6 新老数据兼容

#### 1.6.1 新加的字段，考虑存量数据的默认值

我们日常开发中，随着业务需求变更，经常需要给某个数据库表添加个字段。比如在某个 APP 配置表，需要添加个场景号字段，如`scene_type`, 它的枚举值是 `01、02、03`，那我们就要跟业务对齐，新添加的字段，老数据是什么默认值，是为空还是默认 01，如果是为`NULL`的话，程序代码就要做好空指针处理。

#### 1.6.2 如果新业务用老的字段，考虑老数据的值是否有坑

如果我们开发中，需要沿用数据库表的老字段，并且有存量数据，那就需要考虑老存量数据库的值是否有坑。比如我们表有个 user_role_code 的字段，老的数据中，它枚举值是 `01：超级管理员 02：管理员 03：一般用户`。假设业务需求是**一般用户**拆分为 **03 查询用户和 04 操作用户**，那我们在开发中，就要考虑老数据值的问题啦。

### 1.7 一些 SQL 的经典注意点

#### 1.7.1 limit 大分页问题

limit 大分页是一个非常经典的 SQL 问题，我们一般有这 3 种对应的解决方案

**方案一：** 如果 id 是连续的，可以这样，返回上次查询的最大记录 (偏移量)，再往下 limit

```
select id,name from employee where id>1000000 limit 10.
```

**方案二:** 在业务允许的情况下限制页数：

建议跟业务讨论，有没有必要查这么后的分页啦。因为绝大多数用户都不会往后翻太多页。谷歌搜索页也是限制了页数，因此不存在 limit 大分页问题。

**方案三：**  利用延迟关联或者子查询优化超多分页场景。（先快速定位需要获取的 id 段，然后再关联）

```
SELECT a.* FROM employee a, (select id from employee where 条件 LIMIT 1000000,10 ) b where a.id=b.id
```

#### 1.7.2 修改、查询数据量多时，考虑分批进行。

我们更新或者查询数据库数据时，尽量避免循环去操作数据库，可以考虑分批进行。比如你要插入 10 万数据的话，可以一次插入 500 条，执行 200 次。

**正例：**

```
remoteBatchQuery(param);
```

**反例：**

```
for(int i=0;i<100000;i++){
  remoteSingleQuery(param)
}
```

2. 代码层面篇
--------

![](https://mmbiz.qpic.cn/mmbiz_png/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52S8e3r0Vbe1JhrgiaW8GyYKBeCV8SARtCBnAv671WL1AORobC8eKJxFzg/640?wx_fmt=png)

代码层面

### 2.1 编码细节

![](https://mmbiz.qpic.cn/mmbiz_gif/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52SCp5TVj4Ime7O5m76YfsZBlmQg7jXEKQIdOvXDWjaCHbmCOWiaQpzyMQ/640?wx_fmt=gif)

编码细节. gif

#### 2.1.1 六大典型空指针问题

我们编码的时候，需要注意这六种类型的空指针问题

*   包装类型的空指针问题

*   级联调用的空指针问题

*   Equals 方法左边的空指针问题

*   ConcurrentHashMap 类似容器不支持 k-v 为 null。

*   集合，数组直接获取元素

*   对象直接获取属性


```
if(object!=null){
   String name = object.getName();
}
```

#### 2.1.2 线程池使用注意点

*   使用 Executors.newFixedThreadPool，可能会出现 OOM 问题，因为它使用的是无界阻塞队列

*   建议使用自定义的线程池，最好给线程池一个清晰的命名，方便排查问题

*   不同的业务，最好做线程池隔离，避免所有的业务公用一个线程池。

*   线程池异常处理要考虑好


#### 2.1.3 线性安全的集合、类

在高并发场景下，`HashMap`可能会出现死循环。因为它是非线性安全的，可以考虑使用`ConcurrentHashMap`。所以我们使用这些集合的时候，需要注意是不是线性安全的。

*   Hashmap、Arraylist、LinkedList、TreeMap 等都是线性不安全的；

*   Vector、Hashtable、ConcurrentHashMap 等都是线性安全的


#### 2.1.4  日期格式，金额处理精度等

日常开发，经常需要对日期格式化，但是呢，年份设置为 YYYY 大写的时候，是有坑的哦。

```
Calendar calendar = Calendar.getInstance();
calendar.set(2019, Calendar.DECEMBER, 31);

Date testDate = calendar.getTime();

SimpleDateFormat dtf = new SimpleDateFormat("YYYY-MM-dd");
System.out.println("2019-12-31 转 YYYY-MM-dd 格式后 " + dtf.format(testDate));
```

运行结果：

```
2019-12-31 转 YYYY-MM-dd 格式后 2020-12-31
```

还有金额计算也比较常见，我们要注意精度问题：

```
public class DoubleTest {
    public static void main(String[] args) {
        System.out.println(0.1+0.2);
        System.out.println(1.0-0.8);
        System.out.println(4.015*100);
        System.out.println(123.3/100);

        double amount1 = 3.15;
        double amount2 = 2.10;
        if (amount1 - amount2 == 1.05){
            System.out.println("OK");
        }
    }
}
```

运行结果：

```
0.30000000000000004
0.19999999999999996
401.49999999999994
1.2329999999999999
```

#### 2.1.5 大文件处理

读取大文件的时候，不要`Files.readAllBytes`直接读到内存，会 OOM 的，建议使用`BufferedReader`一行一行来，或者使用`NIO`

#### 2.1.6 使用完 IO 资源流，需要关闭

使用 try-with-resource，读写完文件，需要关闭流

```
/*
 * 关注公众号，捡田螺的小男孩
 */
try (FileInputStream inputStream = new FileInputStream(new File("jay.txt")) {
    // use resources   
} catch (FileNotFoundException e) {
    log.error(e);
} catch (IOException e) {
    log.error(e);
}
```

#### 2.1.7 try...catch 异常使用的一些坑

*   尽量不要使用 e.printStackTrace() 打印，可能导致字符串常量池内存空间占满

*   catch 了异常，使用 log 把它打印出来

*   不要用一个 Exception 捕捉所有可能的异常

*   不要把捕获异常当做业务逻辑来处理


#### 2.1.8 先查询，再更新 / 删除的并发一致性

日常开发中，这种代码实现经常可见：先查询是否有剩余可用的票，再去更新票余量。

```
if(selectIsAvailable(ticketId){ 
    1、deleteTicketById(ticketId) 
    2、给现金增加操作 
}else{ 
    return “没有可用现金券” 
}
```

如果是并发执行，很可能有问题的，应该利用数据库更新 / 删除的原子性，正解如下：

```
if(deleteAvailableTicketById(ticketId) == 1){ 
    1、给现金增加操作 
}else{ 
    return “没有可用现金券” 
}
```

### 2.2 提供对外接口

![](https://mmbiz.qpic.cn/mmbiz_png/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52SBltVXxjMfnUkiabeibyQ6bm1R8tut1WicmfSMHl6MARwdQF5PpGb3kN3g/640?wx_fmt=png)

#### 2.2.1 校验参数合法性

我们提供对外的接口，不管是提供给客户端、还是前端，又或是别的系统调用，都需要校验一下入参的合法性。

> ★
>
> 如果你的数据库字段设置为 varchar(16), 对方传了一个 32 位的字符串过来，你不校验参数长度，插入数据库直接异常了。
>
> ”

![](https://mmbiz.qpic.cn/mmbiz_gif/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52SET4wNicN9pqYmIc7mh3yrCicG5ujfWoMtWAT4JykX0Vftw1ZKJrHY6vQ/640?wx_fmt=gif)

#### 2.2.2 新老接口兼容

很多 bug 都是因为修改了对外老接口，但是却不做兼容导致的。关键这个问题多数是比较严重的，可能直接导致系统发版失败的。新手程序员很容易犯这个错误哦~

比如我们有个 dubbo 的分布式接口，本次你修改了入参，就需要考虑新老接口兼容。原本是只接收 A，B 参数，现在你加了一个参数 C，就可以考虑这样处理。

```
//老接口
void oldService(A,B){
  //兼容新接口，传个null代替C
  newService(A,B,null);
}

//新接口，暂时不能删掉老接口，需要做兼容。
void newService(A,B,C);
```

#### 2.2.3 限流，防止大流量压垮系统

如果瞬间的大流量请求过来，容易压垮系统。所以为了保护我们的系统，一般要做限流处理。可以使用 **guava ratelimiter** 组件做限流，也可以用阿里开源的 **Sentinel**

#### 2.2.4 接口安全性，加签验签，鉴权

我们转账等类型的接口，一定要注意安全性。一定要鉴权，**加签验签**，为用户交易保驾护航。

#### 2.2.5 考虑接口幂等性

接口是需要考虑幂等性的，尤其抢红包、转账这些重要接口。最直观的业务场景，就是**用户连着点击两次**，你的接口有没有 hold 住。

> ★
>
> 1.  幂等（idempotent、idempotence）是一个数学与计算机学概念，常见于抽象代数中。
>
> 2.  在编程中. 一个幂等操作的特点是其任意多次执行所产生的影响均与一次执行的影响相同。幂等函数，或幂等方法，是指可以使用相同参数重复执行，并能获得相同结果的函数。
>
>
> ”

一般**幂等技****术方案**有这几种:

1.  查询操作

2.  唯一索引

3.  token 机制，防止重复提交

4.  数据库的 delete 删除操作

5.  乐观锁

6.  悲观锁

7.  Redis、zookeeper 分布式锁（以前抢红包需求，用了 Redis 分布式锁）

8.  状态机幂等


![](https://mmbiz.qpic.cn/mmbiz_gif/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52Srh3yFlr4ZXF2LH5eicpCYtcLvZYpiaDfCMVxLfRYgicugJGDhS0UvCoqQ/640?wx_fmt=gif)

接口幂等性. gif

### 2.3 调用第三方接口

![](https://mmbiz.qpic.cn/mmbiz_png/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52SdGMBw1NHgFRgkVF3tkpS5s7BRPOmM1pogGE0FM7iaFTLsAILZEoF7nQ/640?wx_fmt=png)

#### 2.3.1 超时处理

我们调用别人的接口，如果超时了怎么办呢？

> ★
>
> 举个例子，我们调用一个远程转账接口，A 客户给 B 客户转 100 万，成功的时候就把本地转账流水置为成功，失败的时候就把本地流水置为失败。如果调用转账系统超时了呢，我们怎么处理呢？置为成功还是失败呢？这个**超时处理可要考虑好**，要不然就资金损失了。这种场景下，调接口超时，我们就可以先**不更新本地转账流水**状态，而是重新发起查询远程转账请求，查询到转账成功的记录，再更新本地状态状态
>
> ”

#### 2.3.2 考虑重试机制

如果我们调用一个远程 http 或者 dubbo 接口，调用失败了，我们可以考虑引入重试机制。有时候网路抖动一下，接口就调失败了，引入重试机制可以提高用户体验。但是这个重试机制需要评估次数，或者有些接口不支持幂等，就不适合重试的。

#### 2.3.3 考虑是否降级处理

假设我们系统是一个提供注册的服务：用户注册成功之后，调远程 A 接口发短信，调远程 B 接口发邮件，最后更新注册状态为成功。

![](https://mmbiz.qpic.cn/mmbiz_png/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52S5kA65IY9kxg1UqvVEhFW3FsymMNU2sbUbaDPWWicNCNJFSgu3LafTrA/640?wx_fmt=png)

如果调用接口 B 发邮件失败，那用户就注册失败，业务可能就不会同意了。这时候我们可以考虑给 B 接口**降级处理**，提供**有损服务**。也就是说，如果调用 B 接口失败，那先不发邮件，而是先让用户注册成功，后面搞个定时补发邮件就好啦。

#### 2.3.4 考虑是否异步处理

我还是使用上个小节的**用户注册**的例子。我们可以开个异步线程去调 A 接口发短信，异步调 B 接口发邮件，那即使 A 或者 B 接口调失败，我们还是可以保证用户先注册成功。

把发短信这些通知类接口，放到异步线程处理，可以降低接口耗时，提升用户体验哦。

![](https://mmbiz.qpic.cn/mmbiz_png/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52SOOU9EMkoDVlAWM7gickPnJUtq7McDRS0dHdJiaCZs3TYPPKicPDCkJNbQ/640?wx_fmt=png)

#### 2.3.5 调接口异常处理

如果我们调用一个远程接口，一般需要思考以下：如果别人接口异常，我们要怎么处理，怎么兜底，是重试还是当做失败？怎么保证数据的最终一致性等等。

3. 缓存篇
------

![](https://mmbiz.qpic.cn/mmbiz_png/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52SibKuSKoxtLoUB0mIGIZIeuK4BKM5NE0p1jVL9xyw26uhN9hg0yNFUtQ/640?wx_fmt=png)

### 3.1 数据库与缓存一致性

使用缓存，可以降低耗时，提供系统吞吐性能。但是，使用缓存，会存在数据一致性的问题。

#### 3.1.1 几种缓存使用模式

*   Cache-Aside Pattern，旁路缓存模式

*   Read-Through/Write-Through（读写穿透）

*   Write- behind （异步缓存写入）


一般我们使用缓存，都是**旁路缓存模式**，读请求流程如下:

![](https://mmbiz.qpic.cn/mmbiz_png/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52SdmqsWdxl3vfibc5xaldoRWdQVJgicVKo79Qgibiawo9TLIW5L9lMCH9xIw/640?wx_fmt=png)

*   读的时候，先读缓存，缓存命中的话，直接返回数据

*   缓存没有命中的话，就去读数据库，从数据库取出数据，放入缓存后，同时返回响应。


旁路缓存模式的写流程：![](https://mmbiz.qpic.cn/mmbiz_png/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52SVOYPG0lfoxicIYuhIvZSDHaZxn8uMH1WQQlRoBWXiam4g9kphnR4CsicQ/640?wx_fmt=png)

#### 3.1.2 删除缓存呢，还是更新缓存？

我们在操作缓存的时候，到底应该删除缓存还是更新缓存呢？我们先来看个例子：

![](https://mmbiz.qpic.cn/mmbiz_png/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52S5qW6yt7rtqtu4tCMyic2uGBvgskvZ8JvKTuqRNhwSgiaoFTJkVUUGa7A/640?wx_fmt=png)

1.  线程 A 先发起一个写操作，第一步先更新数据库

2.  线程 B 再发起一个写操作，第二步更新了数据库

3.  由于网络等原因，线程 B 先更新了缓存

4.  线程 A 更新缓存。


这时候，缓存保存的是 A 的数据（老数据），数据库保存的是 B 的数据（新数据），数据不一致了，脏数据出现啦。如果是删除缓存取代更新缓存则不会出现这个脏数据问题。

#### 3.1.3 先操作数据库还是先操作缓存

双写的情况下，先操作数据库还是先操作缓存？我们再来看一个例子：假设有 A、B 两个请求，请求 A 做更新操作，请求 B 做查询读取操作。

![](https://mmbiz.qpic.cn/mmbiz_png/PoF8jo1PmpwXlB8V0pbumIKzRKZsic52Shkt4gfOzTkFTHtrDUfc4UE7UZXVo13rQ7jUeNnzgD0TWc3yrFQqwKg/640?wx_fmt=png)

image.png

1.  线程 A 发起一个写操作，第一步 del cache

2.  此时线程 B 发起一个读操作，cache miss

3.  线程 B 继续读 DB，读出来一个老数据

4.  然后线程 B 把老数据设置入 cache

5.  线程 A 写入 DB 最新的数据


酱紫就有问题啦，缓存和数据库的数据不一致了。缓存保存的是老数据，数据库保存的是新数据。因此，Cache-Aside 缓存模式，选择了先操作数据库而不是先操作缓存。

#### 3.1.4 如何保证最终一致性

*   缓存延时双删

*   删除缓存重试机制

*   读取 biglog 异步删除缓存


### 3.2 缓存穿透

> ★
>
> 缓存穿透：指查询一个一定不存在的数据，由于缓存不命中时，需要从数据库查询，查不到数据则不写入缓存，这将导致这个不存在的数据每次请求都要到数据库去查询，进而给数据库带来压力。
>
> ”

缓存穿透一般都是这几种情况产生的：**业务不合理的设计、业务 / 运维 / 开发失误的操作、黑客非法请求攻击**。如何避免缓存穿透呢？一般有三种方法。

*   如果是非法请求，我们在 API 入口，**对参数进行校验**，过滤非法值。

*   如果查询数据库为空，我们可以**给缓存设置个空值，或者默认值**。但是如有有写请求进来的话，需要更新缓存哈，以保证缓存一致性，同时，最后给缓存设置适当的过期时间。（业务上比较常用，简单有效）

*   使用**布隆过滤器**快速判断数据是否存在。即一个查询请求过来时，先通过布隆过滤器判断值是否存在，存在才继续往下查。


### 3.3 缓存雪崩

> ★
>
> 缓存雪崩：指缓存中数据大批量到过期时间，而查询数据量巨大，引起数据库压力过大甚至 down 机。
>
> ”

*   缓存雪奔一般是由于大量数据同时过期造成的，对于这个原因，可通过**均匀设置过期时间解决，即让过期时间相对离散一点**。如采用一个较大固定值 + 一个较小的随机值，5 小时 + 0 到 1800 秒酱紫。

*   **Redis 故障宕机也可能引起缓存雪奔**。这就需要构造 Redis 高可用集群啦。


### 3.4  缓存机击穿

> ★
>
> 缓存击穿：指热点 key 在某个时间点过期的时候，而恰好在这个时间点对这个 Key 有大量的并发请求过来，从而大量的请求打到 db。
>
> ”

缓存击穿看着有点像缓存雪崩，其实它两区别是，缓存雪奔是指数据库压力过大甚至 down 机，缓存击穿只是大量并发请求到了 DB 数据库层面。可以认为击穿是缓存雪奔的一个子集吧。有些文章认为它俩区别，是在于击穿针对某一热点 key 缓存，雪奔则是很多 key。

解决方案就有两种：

1.  **使用互斥锁方案**。缓存失效时，不是立即去加载 db 数据，而是先使用某些带成功返回的原子操作命令，如 (Redis 的 setnx）去操作，成功的时候，再去加载 db 数据库数据和设置缓存。否则就去重试获取缓存。

2.  **“永不过期”**，是指没有设置过期时间，但是热点数据快要过期时，异步线程去更新和设置过期时间。


### 3.5 缓存热 Key

在 Redis 中，我们把访问频率高的 key，称为热点 key。如果某一热点 key 的请求到服务器主机时，由于请求量特别大，可能会导致主机资源不足，甚至宕机，从而影响正常的服务。

如何解决热 key 问题？

*   **Redis 集群扩容**：增加分片副本，均衡读流量；

*   **对热 key 进行 hash 散列**，比如将一个 key 备份为 key1,key2……keyN，同样的数据 N 个备份，N 个备份分布到不同分片，访问时可随机访问 N 个备份中的一个，进一步分担读流量；

*   **使用二级缓存**，即 JVM 本地缓存, 减少 Redis 的读请求。


### 3.6 缓存容量内存考虑

#### 3.6.1 评估容量，合理利用

如果我们使用的是 Redis，而 Redis 的内存是比较昂贵的，我们不要什么数据都往 Redis 里面塞，一般 Redis 只缓存查询比较频繁的数据。同时，我们要合理评估 Redis 的容量，也避免频繁 set 覆盖，导致设置了过期时间的 key 失效。

如果我们使用的是本地缓存，如 guava 的本地缓存，也要评估下容量。避免容量不够。

#### 3.6.2 Redis 的八种内存淘汰机制

为了避免 Redis 内存不够用，Redis 用 8 种内存淘汰策略保护自己~

> ★
>
> *   volatile-lru：当内存不足以容纳新写入数据时，从设置了过期时间的 key 中使用 LRU（最近最少使用）算法进行淘汰；
>
> *   allkeys-lru：当内存不足以容纳新写入数据时，从所有 key 中使用 LRU（最近最少使用）算法进行淘汰。
>
> *   volatile-lfu：4.0 版本新增，当内存不足以容纳新写入数据时，在过期的 key 中，使用 LFU 算法进行删除 key。
>
> *   allkeys-lfu：4.0 版本新增，当内存不足以容纳新写入数据时，从所有 key 中使用 LFU 算法进行淘汰；
>
> *   volatile-random：当内存不足以容纳新写入数据时，从设置了过期时间的 key 中，随机淘汰数据；。
>
> *   allkeys-random：当内存不足以容纳新写入数据时，从所有 key 中随机淘汰数据。
>
> *   volatile-ttl：当内存不足以容纳新写入数据时，在设置了过期时间的 key 中，根据过期时间进行淘汰，越早过期的优先被淘汰；
>
> *   noeviction：默认策略，当内存不足以容纳新写入数据时，新写入操作会报错。
>
>
> ”

#### 3.6.3 不同的业务场景，Redis 选择适合的数据结构

*   排行榜适合用 zset

*   缓存用户信息一般用 hash

*   消息队列，文章列表适用用 list

*   用户标签、社交需求一般用 set

*   计数器、分布式锁等一般用 String 类型


### 3.7 Redis 一些有坑的命令

1.  不能使用 keys 指令

2.  慎用 O(n) 复杂度命令，如 hgetall 等

3.  慎用 Redis 的 monitor 命令

4.  禁止使用 flushall、flushdb

5.  注意使用 del 命令


最后
--

本文总结了 50 多个减少 bug 的编码注意点，都是日常开发经典的范例，希望对大家有帮助哈。另外，关注公众号：**捡田螺的小男孩**，回复**思维导图**，可以**领取本文的高清思维导图**。

公众号

****跪求大家帮忙点个********赞、在看、转发，感谢！****