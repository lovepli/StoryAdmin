> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247488995&idx=1&sn=c0343aad85c27439562b335c9b5d7872&scene=21#wechat_redirect)

小伙伴们大家好，这是 JVM 调优实战的第二篇系列文章，上一篇的文章讲解了 JVM 调优的工具类，没有看的小伙伴可以看一下。



这一篇主要是案例讲解，主要讲解的案例：**内存不足的排查、CPU 飙高的排查、OOM 异常的排查、栈溢出排查、死锁的排查**。

其实上面的主要是问题的排查，只能算是问题解决的一方面，**另外还会基于以前讲解 GC 篇的文章，对于各个使用场景的 GC 的选择，以及使用的 JVM 参数，常用的 JVM 参数调优的讲解等**。

所以还是来一个思维导图，便于大家对于这篇文章有一个总体的认识，我想着一篇也至少有一万以上的字才能解决：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj13sSLiabqcBdubt3a4iboMAx40R7Qk9X0ja2uRNNqnkr1nPAgkzfQYKoQ/640?wx_fmt=png)

思维导图右边是上一篇已经讲过的了，左边是这一篇要讲解的，好了废话不多说，直接开始我们的正题。

磁盘不足排查
------

其实，磁盘不足排查算是系统、程序层面的问题排查，并不算是 JVM，但是另一方面考虑过来就是，系统磁盘的不足，也会导致 JVM 的运行异常，所以也把内存不足算进来了。

并且排查磁盘不足，是比较简单，就是几个命令，然后就是逐层的排查，首先第一个命令就是 **df -h**，查询磁盘的状态：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj10ibFUGmtryk6aMqSFicWxxTHiatHR9pKbgYar98BvgJJ6fh5Zia1s06Cdg/640?wx_fmt=png)

从上面的显示中其中第一行使用的 2.8G 最大，然后是挂载在 **/** 目录下，我们直接 **cd /**。

然后通过执行：

```
du -sh *
```

查看各个文件的大小，也是找到其中最大的，或者说存储量级差不多的并且都非常大的（非常大，你忍一下就好），马上就能让他变得非常小。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj17icRdtEJRafaiasficluw1KzCFoudlcqzMErIIufVtHY1MqE8Oj8RLVEQ/640?wx_fmt=png)

然后，就是直接 cd 到对应的目录也是执行：du -sh *，就这样一层一层的执行，找到对应的没用的，然后文件又比较大的，可以直接删除。

CPU 过高排查
--------

然后就是排查 CPU 的飙高的原因，**CPU 飙高的排查都是直接找到对应 CPU 占比最高的进程，然后找到 CPU 最高的线程**。

总结一下可能导致 CPU 标高的原因，可能是**一个 GC 线程频繁或者锁资源竞争频繁，线程数过多**等原因。

*   **GC 线程频繁**

*   **锁竞争频繁（自旋）**


其中 GC 线程频繁，有可能是**大对象（对象过多），内存泄漏**等原因导致内存紧张一直在执行 GC，但是每次执行的 GC 回收的垃圾都非常少。

一般 CPU 紧张，都是线上实施排查，并且一般大厂都会有自己自研的监控平台，我们自己的监控平台，对于我们每台服务器的健康状况（健康分）、服务期内的应用（Mysql、Redis、Mq、Kafka、服务）都会进行实施的监控报警，所以一般都能都在出现问题前将问题解决掉。

在线上之间也提到过可以使用 **top**、**jstack** 命令排查 CPU 飙高的问题。这里有一段案例代码如下：

```
public class CPUSoaring {
        public static void main(String[] args) {

                Thread thread1 = new Thread(new Runnable(){
                        @Override
                        public void run() {
                                for (;;){
                                      System.out.println("I am children-thread1");
                                }
                        }
                },"children-thread1");
                
                 Thread thread2 = new Thread(new Runnable(){
                        @Override
                        public void run() {
                                for (;;){
                                      System.out.println("I am children-thread2");
                                }
                        }
                },"children-thread2");
                
                thread1.start();
                thread2.start();
                System.err.println("I am is main thread!!!!!!!!");
        }
}
```

（1）首先通过 **top** 命令可以查看到 id 为 **3806** 的进程所占的 CPU 最高，

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1GAgaIORn15jYbR50yibQ4oEQRLibmWubiaf0jelSUOj0MDV6pC4IzkFicA/640?wx_fmt=png)

（2）然后通过 **top -Hp pid** 命令，找到占用 CPU 最高的线程：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1cpGR3ksBzYibolbbSE4JTEiaWA4Oia6ocWkb2ibfy7P1D0XO729Xiaia4EBA/640?wx_fmt=png)

（3）接着通过：**printf '%x\n' tid** 命令将线程的 tid 转换为十六进制：xid：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1QpjkRAE3Oxq5QlFpt4IPzPWSamzX0BndLkyGXIAGo0TdjhIQr00tbg/640?wx_fmt=png)

（4）最后通过：**jstack pid|grep xid -A 30** 命令就是输出线程的堆栈信息，线程所在的位置：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1xKvX1HBnbiaLXnXGyXdrDoFtLx3o9rXKdZwOTLl6seFhSPur77iadRZQ/640?wx_fmt=png)

（5）还可以通过 **jstack -l pid > 文件名称. txt** 命令将线程堆栈信息输出到文件，线下查看。

这就是一个 CPU 飙高的排查过程，目的就是要**找到占用 CPU 最高的线程所在的位置**，然后就是 **review** 你的代码，定位到问题的所在。

使用 Arthas 的工具排查也是一样的，首先要使用 top 命令找到占用 CPU 最高的 Java 进程，然后使用 Arthas 进入该进程内，**使用 dashboard 命令排查占用 CPU 最高的线程。**，最后通过 **thread** 命令线程的信息。

OOM 异常排查
--------

OOM 的异常排查也比较简单，首先服务上线的时候，要先设置这两个参数：

```
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${目录}
```

指定项目出现 OOM 异常的时候自动导出堆转储文件，然后通过内存分析工具（**Visual VM**）来进行线下的分析。

首先我们来聊一聊，哪些原因会导致 OOM 异常，站在 JVM 的分区的角度：

*   **Java 堆**

*   **方法区**

*   **虚拟机栈**

*   **本地方法栈**

*   **程序计数器**

*   **直接内存**


只有**程序计数器**区域不会出现 OOM，在 Java 8 及以上的**元空间**（本地内存）都会出现 OOM。

而站在程序代码的角度来看，总结了大概有以下几点原因会导致 OOM 异常：

*   **内存泄露**

*   **对象过大、过多**

*   **方法过长**

*   **过度使用代理框架，生成大量的类信息**


接下来我们屋来看看 OOM 的排查，出现 OOM 异常后 dump 出了堆转储文件，然后打开 jdk 自带的 Visual VM 工具，导入堆转储文件，首先我使用的 OOM 异常代码如下：

```
import java.util.ArrayList;
import java.util.List;

class OOM {

        static class User{
                private String name;
                private int age;

                public User(String name, int age){
                        this.name = name;
                        this.age = age;
                }
        }

        public static void main(String[] args) throws InterruptedException {
                List<User> list = new ArrayList<>();
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        Thread.sleep(1000);
                        User user = new User("zhangsan"+i,i);
                        list.add(user);
                }
        }

}
```

代码很简单，就是往集合里面不断地 add 对象，带入堆转储文件后，在类和实例那栏就可以看到实例最多的类：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1bJdLtRhPytn2ic1Pw76aaUKI9Iwm0cxXs2CLOpdViakIUa4s0kmVSB5A/640?wx_fmt=png)

这样就找到导致 OOM 异常的类，还可以通过下面的方法查看导致 OOM 异常的线程堆栈信息，找到对应异常的代码段。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj11RtOJPs4VMbRf3L2pYRiayfkrgwvxNtK7PUOtD0oD5unyRCt6EMISLw/640?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1kC2icuHoia3ztQLKs8GZUZQibPPRsZTAZtnWfoRibuPNyR418xGV7rKz7A/640?wx_fmt=png)

上面的方法是排查已经出现了 OOM 异常的方法，肯定是防线的最后一步，那么在此之前怎么防止出现 OOM 异常呢？

一般大厂都会有自己的监控平台，能够实施的**监控测试环境、预览环境、线上实施的服务健康状况（CPU、内存）** 等信息，对于频繁 GC，并且 GC 后内存的回收率很差的，就要引起我们的注意了。

因为一般方法的长度合理，95% 以上的对象都是朝生夕死，在 **Minor GC** 后只剩少量的存活对象，所以在代码层面上应该避免**方法过长、大对象**的现象。

每次自己写完代码，自己检查后，都可以提交给比自己高级别的工程师 **review** 自己的代码，就能及时的发现代码的问题，基本上代码没问题，百分之九十以上的问题都能避免，这也是大厂注重代码质量，并且时刻 **review** 代码的习惯。

栈溢出
---

栈溢出异常的排查（包括**虚拟机栈、本地方法栈**）基本和 OOM 的一场排查是一样的，导出异常的堆栈信息，然后使用 mat 或者 Visual VM 工具进行线下分析，找到出现异常的代码或者方法。

当线程请求的栈深度大于虚拟机栈所允许的大小时，就会出现 **StackOverflowError** 异常，二从代码的角度来看，导致线程请求的深度过大的原因可能有：**方法栈中对象过大，或者过多，方法过长从而导致局部变量表过大，超过了 - Xss 参数的设置**。

死锁排查
----

死锁的案例演示的代码如下：

```
public class DeadLock {

 public static Object lock1 = new Object();
 public static Object lock2 = new Object();

 public static void main(String[] args){
  Thread a = new Thread(new Lock1(),"DeadLock1");
  Thread b = new Thread(new Lock2(),"DeadLock2");
  a.start();
  b.start();
 }
}
class Lock1 implements Runnable{
 @Override
 public void run(){
  try{
   while(true){
    synchronized(DeadLock.lock1){
     System.out.println("Waiting for lock2");
     Thread.sleep(3000);
     synchronized(DeadLock.lock2){
      System.out.println("Lock1 acquired lock1 and lock2 ");
     }
    }
   }
  }catch(Exception e){
   e.printStackTrace();
  }
 }
}
class Lock2 implements Runnable{
 @Override
 public void run(){
  try{
   while(true){
    synchronized(DeadLock.lock2){
     System.out.println("Waiting for lock1");
     Thread.sleep(3000);
     synchronized(DeadLock.lock1){
      System.out.println("Lock2 acquired lock1 and lock2");
     }
    }
   }
  }catch(Exception e){
   e.printStackTrace();
  }
 }
}
```

上面的代码非常的简单，就是两个类的实例作为锁资源，然后分别开启两个线程，不同顺序的对锁资源资源进行加锁，并且获取一个锁资源后，等待三秒，是为了让另一个线程有足够的时间获取另一个锁对象。

运行上面的代码后，就会陷入死锁的僵局：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1vEPibQZuJMfpgJpbThz6YyD7aZUtCkHS2ENWRKmtzBbo0lv4Q0ovzSw/640?wx_fmt=png)

对于死锁的排查，若是在测试环境或者本地，直接就可以使用 Visual VM 连接到该进程，如下界面就会自动检测到死锁的存在

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1rfpZqTLcbmj1gjhogLYAGVkTEicSObKnSMXTea2RUIaFkjmuq4jPMYQ/640?wx_fmt=png)

在这里插入图片描述

并且查看线程的堆栈信息。就能看到具体的死锁的线程：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1LuP7fBlypDb0hjCF6RbFw9FhqJiaib4uEhKA2oTZX1KDnMJVkItAWUfQ/640?wx_fmt=png)线上的话可以上用 Arthas 也可以使用原始的命令进行排查，原始命令可以先使用 **jps** 查看具体的 Java 进程的 ID，然后再通过 **jstack ID** 查看进程的线程堆栈信息，他也会自动给你提示有死锁的存在：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1RShg0FxibM2ibQia0ic6LiadKE19HdC5KFu4OkNjaCaGCxL4e4wWf5nn8oQ/640?wx_fmt=png)

在这里插入图片描述

Arthas 工具可以使用 **thread** 命令排查死锁，要关注的是 **BLOCKED** 状态的线程，如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1AFRPRMoMKx2KwkySCgDhzOOIBayJNleBHIelLl1JKk3ibz1YWYRkQfg/640?wx_fmt=png)

具体 thread 的详细参数可以参考如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj15t9z11mOicLWylOX4YA6YYrq0OSu4r0ZlOlHA8u2hicLCNmu5hRhPWmw/640?wx_fmt=png)

描述

### 如何避免死锁

上面我们聊了如何排查死锁，下面我们来聊一聊如何避免死锁的发生，从上面的案例中可以发现，死锁的发生两个线程同时都持有对方不释放的资源进入僵局。

所以，在代码层面，要避免死锁的发生，主要可以从下面的四个方面进行入手：

1.  **首先避免线程的对于资源的加锁顺序要保持一致**

2.  **并且要避免同一个线程对多个资源进行资源的争抢**。

3.  **另外的话，对于已经获取到的锁资源，尽量设置失效时间，避免异常，没有释放锁资源，可以使用 acquire() 方法加锁时可指定 timeout 参数。**

4.  **最后，就是使用第三方的工具检测死锁，预防线上死锁的发生。**


死锁的排查已经说完了，上面的基本就是问题的排查，也可以算是调优的一部分吧，但是对于 JVM 调优来说，重头戏应该是在 **Java 堆**，这部分的调优才是重中之重。

下面我们来聊一聊 Java 堆的调优。

调优目的
----

再说 GC 堆的调优之前，我们先来聊一聊调优的目的，可能人云亦云，每个人的看法都不一样，我就单纯的说说我对调优的看法。

我认为调优的目的主要有两个，而且调优还必须要有前提，前提是你的系统必须要调优了，什么意思呢？比如你的服务运行速度慢，响应慢，吞吐量小，甚至出现 **OOM** 异常了，那么这就需要调优了。

假如，你的服务运行状态佳，响应快，吞吐量高，那就没必要了，此时你再去调优，就有可能适得其反。

然后，调优的目的就是第一个是是你的服务运行状态佳，响应速度快（响应时间能够在接受范围），或者说吞吐量高，要达到这种目的就是使 GC 时间（STW）合理，次数合理，Minor GC 次数合理（几小时一次），Full GC 合理（一天一次或者几天一次）。

第二个目的就是为了解决问题，比如出现了 OOM，那么调优的目的就是为了防止再次出现 OOM 异常。

调优理论指标
------

那么调优的指标又是什么，有什么指标可以衡量你调优后，服务是比以前的运行状态更佳了。

其实上面也说了，一个是**平均响应时间、吞吐量（吞吐量 = CPU 在用户应用程序运行的时间 / （CPU 在用户应用程序运行的时间 + CPU 垃圾回收的时间），一般而言 GC 的吞吐量不能低于 95%）**

这两个指标映射到 JVM 的底层来说就是 **GC 的停顿时间 STW（停顿时间越长就意味着用户线程等待的时间越长，停顿时间会直接影响用户使用系统的体验）、以及垃圾的回收频率（通常来说垃圾回收频率是越低越好，垃圾收集的过程是非常占用 CPU 资源的）**。

当然，也不能一味的追求 GC 次数减少，GC 次数减少了有可能就会使得单次 GC 的时间变长，那么就可能会增加单次 GC 的 “停顿时长”，所以需要在这两者之间做一些平衡。

调优实战
----

上面说完了调优的目的和调优的指标，那么我们就来实战调优，首先准备我的案例代码，如下：

```
import java.util.ArrayList;
import java.util.List;

class OOM {

 static class User{
  private String name;
  private int age;

  public User(String name, int age){
   this.name = name;
   this.age = age;
  }

 }

 public static void main(String[] args) throws InterruptedException {
  List<User> list = new ArrayList<>();
  for (int i = 0; i < Integer.MAX_VALUE; i++) {
       Tread.sleep(1000);
   System.err.println(Thread.currentThread().getName());
   User user = new User("zhangsan"+i,i);
   list.add(user);
  }
 }
}
```

案例代码很简单，就是不断的往一个集合里里面添加对象，首先初次我们启动的命令为：

```
java   -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseGCLogFileRotation -XX:+PrintHeapAtGC -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=50M -Xloggc:./logs/emps-gc-%t.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./logs/emps-heap.dump OOM
```

就是纯粹的设置了一些 GC 的打印日志，然后通过 Visual VM 来看 GC 的显示如下：![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1rlDBUGt1nP0PMwH8DD3mucBciaQvGXa6BoCZj3UgBPq84skJ6VgJ9rg/640?wx_fmt=png)可以看到一段时间后出现 4 次 Minor GC，使用的时间是 29.648ms，发生一次 Full GC 使用的时间是 41.944ms。

Minor GC 非常频繁，Full GC 也是，在短时间内就发生了几次，观察输出的日志发现以及 Visual VM 的显示来看，都是因为内存没有设置，太小，导致 Minor GC 频繁。

因此，我们第二次适当的增大 Java 堆的大小，调优设置的参数为：

```
java -Xmx2048m -Xms2048m -Xmn1024m -Xss256k  -XX:+UseConcMarkSweepGC  -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseGCLogFileRotation -XX:+PrintHeapAtGC -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=50M -Xloggc:./logs/emps-gc-%t.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./logs/emps-heap.dump OOM
```

观察一段时间后，结果如下图所示：![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1WPhxap3pI1iag9mDkO2ibe5nOicObmIHzGMBIJms9Ap3f7nia12wRSP5Og/640?wx_fmt=png)

可以发现 Minor GC 次数明显下降，但是还是发生了 Full GC，根据打印的日志来看，是因为元空间的内存不足，看了上面的 Visual VM 元空间的内存图，也是一样，基本都到顶了：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1Y7Sdic7PVe3YibXgNUzmKu8QtYwXVh7AuicojznRN4icjLhEbfgqRmDTMg/640?wx_fmt=png)

在这里插入图片描述

因此第三次对于元空间的区域设置大一些，并且将 GC 回收器换成是 CMS 的，设置的参数如下：

```
java -Xmx2048m -Xms2048m -Xmn1024m -Xss256k -XX:MetaspaceSize=100m -XX:MaxMetaspaceSize=100m -XX:+UseConcMarkSweepGC  -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseGCLogFileRotation -XX:+PrintHeapAtGC -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=50M -Xloggc:./logs/emps-gc-%t.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./logs/emps-heap.dump OOM
```

观察相同的时间后，Visual VM 的显示图如下：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1iaoy4Bnia5tqXLoBWF4wFIRyrLjnDfZU77d987Hia8CSIOeydBpzrrKCQ/640?wx_fmt=png)

同样的时间，一次 Minor GC 和 Full GC 都没有发生，所以这样我觉得也算是已经调优了。

但是调优并不是一味的调大内存，是要在各个区域之间取得平衡，可以适当的调大内存，以及更换 GC 种类，举个例子，当把上面的案例代码的 Thread.sleep(1000) 给去掉。

然后再来看 Visual VM 的图，如下：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1oAzrfERKmUGeibicQO8d0yQ9tp0vicCTqjYz3ygCdicL39Ncf6JhCaOvsQ/640?wx_fmt=png)

可以看到 Minor GC 也是非常频繁的，因为这段代码本身就是不断的增大内存，直到 OOM 异常，真正的实际并不会这样，可能当内存增大到一定两级后，就会在一段范围平衡。

当我们将上面的情况，再适当的增大内存，JVM 参数如下：

```
java -Xmx4048m -Xms4048m -Xmn2024m -XX:SurvivorRatio=7  -Xss256k -XX:MetaspaceSize=300m -XX:MaxMetaspaceSize=100m -XX:+UseConcMarkSweepGC  -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseGCLogFileRotation -XX:+PrintHeapAtGC -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=50M -Xloggc:./logs/emps-gc-%t.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./logs/emps-heap.dump OOM
```

可以看到相同时间内，确实 Minor GC 减少了，但是时间增大了，因为复制算法，基本都是存活的，复制需要耗费大量的性能和时间：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlhsjFwfhJ1KgricalrUGaFj1v6HYHmAPj3SPT8a8vrIU3OgMk5kXPAbWWia7Ik7ImGXj7ym95fHXyOQ/640?wx_fmt=png)

在这里插入图片描述

所以，调优要有取舍，取得一个平衡点，性能、状态达到佳就 OK 了，并没最佳的状态，这就是调优的基本法则，而且调优也是一个细活，所谓慢工出细活，需要耗费大量的时间，慢慢调，不断的做对比。

调优参数
----

好了对于 JVM 的调优，我已经讲完了，下面给大家整理了 JVM 常用的调优参数，方便大家用的时候，直接粘贴复制

### 堆

*   -Xms1024m 设置堆的初始大小

*   -Xmx1024m 设置堆的最大大小

*   -XX:NewSize=1024m 设置年轻代的初始大小

*   -XX:MaxNewSize=1024m 设置年轻代的最大值

*   -XX:SurvivorRatio=8 Eden 和 S 区的比例

*   -XX:NewRatio=4 设置老年代和新生代的比例

*   -XX:MaxTenuringThreshold=10 设置晋升老年代的年龄条件


### 栈

*   -Xss128k


### 元空间

*   -XX:MetasapceSize=200m 设置初始元空间大小

*   -XX:MaxMatespaceSize=200m 设置最大元空间大小 默认无限制


### 直接内存

*   -XX:MaxDirectMemorySize 设置直接内存的容量，默认与堆最大值一样。


### 日志

*   -Xloggc:/opt/app/ard-user/ard-user-gc-%t.log   设置日志目录和日志名称

*   -XX:+UseGCLogFileRotation           开启滚动生成日志

*   -XX:NumberOfGCLogFiles=5            滚动 GC 日志文件数，默认 0，不滚动

*   -XX:GCLogFileSize=20M               GC 文件滚动大小，需开 UseGCLogFileRotation

*   -XX:+PrintGCDetails      开启记录 GC 日志详细信息（包括 GC 类型、各个操作使用的时间）, 并且在程序运行结束打印出 JVM 的内存占用情况

*   -XX:+ PrintGCDateStamps             记录系统的 GC 时间

*   -XX:+PrintGCCause                   产生 GC 的原因 (默认开启)


### GC

#### Serial 垃圾收集器（新生代） 开启：

*   -XX:+UseSerialGC 关闭：

*   -XX:-UseSerialGC // 新生代使用 Serial  老年代则使用 SerialOld


#### Parallel Scavenge 收集器（新生代） 开启

*   -XX:+UseParallelOldGC 关闭

*   -XX:-UseParallelOldGC  新生代使用功能 Parallel Scavenge 老年代将会使用 Parallel Old 收集器


#### ParallelOl 垃圾收集器（老年代） 开启

*   -XX:+UseParallelGC 关闭

*   -XX:-UseParallelGC 新生代使用功能 Parallel Scavenge 老年代将会使用 Parallel Old 收集器


#### ParNew 垃圾收集器（新生代） 开启

*   -XX:+UseParNewGC 关闭

*   -XX:-UseParNewGC // 新生代使用功能 ParNew 老年代则使用功能 CMS


#### CMS 垃圾收集器（老年代） 开启

*   -XX:+UseConcMarkSweepGC 关闭

*   -XX:-UseConcMarkSweepGC

*   -XX:MaxGCPauseMillis  GC 停顿时间，垃圾收集器会尝试用各种手段达到这个时间，比如减小年轻代

*   -XX:+UseCMSCompactAtFullCollection 用于在 CMS 收集器不得不进行 FullGC 时开启内存碎片的合并整理过程，由于这个内存整理必须移动存活对象，（在 Shenandoah 和 ZGC 出现前）是无法并发的。

*   -XX：CMSFullGCsBefore-Compaction 多少次 FullGC 之后压缩一次，默认值为 0，表示每次进入 FullGC 时都进行碎片整理）

*   -XX:CMSInitiatingOccupancyFraction 当老年代使用达到该比例时会触发 FullGC，默认是 92。

*   -XX:+UseCMSInitiatingOccupancyOnly 这个参数搭配上面那个用，表示是不是要一直使用上面的比例触发 FullGC，如果设置则只会在第一次 FullGC 的时候使用 - XX:CMSInitiatingOccupancyFraction 的值，之后会进行自动调整。

*   -XX:+CMSScavengeBeforeRemark 在 FullGC 前启动一次 MinorGC，目的在于减少老年代对年轻代的引用，降低 CMSGC 的标记阶段时的开销，一般 CMS 的 GC 耗时 80% 都在标记阶段。

*   -XX:+CMSParallellnitialMarkEnabled 默认情况下初始标记是单线程的，这个参数可以让他多线程执行，可以减少 STW。

*   -XX:+CMSParallelRemarkEnabled 使用多线程进行重新标记，目的也是为了减少 STW。


#### G1 垃圾收集器 开启

*   -XX:+UseG1GC 关闭

*   -XX:-UseG1GC

*   -XX：G1HeapRegionSize 设置每个 Region 的大小，取值范围为 1MB～32MB

*   -XX：MaxGCPauseMillis 设置垃圾收集器的停顿时间，默认值是 200 毫秒，通常把期望停顿时间设置为一两百毫秒或者两三百毫秒会是比较合理的


好了，JVM 的调优基本讲完了，这一期就到这里，我是黎杜，我们下一期见。

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




你点的每个赞，我当做都认真
```