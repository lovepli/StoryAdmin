> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247488916&idx=1&sn=17d07be2bb6c8cf6c8c5a162f37cba62&scene=21#wechat_redirect)

关于 JVM 的的第三篇文章来了，这一篇是 JVM 的调优实战篇，上一篇是调优实战的基础理论篇，主要是关于 GC 方面的，因为对于 JVM 的调优基本就是 Java 堆了，所以涉及到的区域几乎与 GC 是脱不了关系的。

这一篇主要的内容如下，包括常用的调优工具本地的和线上的，并且有案例结合使用来讲解，然后就是一些常见的实战场景，这个也是几乎大厂面试官会问的，大家面试的时候也可以直接拿来当案例：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFp7ehslibdSpnbLoLYRHIMdMwujV6BYjqbvgmbOicWeIsDWwdus4uqL2g/640?wx_fmt=png)

调优工具
----

好了，首先我们来了解我们常用的调优工具吧，我这里推荐的 GUI 工具是：**VisualVM**，我不知道大家用的是哪一款。

### VisualVM

**VisualVM** 是 jdk 自带的调优工具，在安装的路径下 **jdk1.8.0_40\bin** 就可以看到 **jvisualvm** 工具了：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFmTnIQp8b0liaGKBCNZjPDCZEuzk8x5fVbDgyQGUHlzJxEoYjvXFocnw/640?wx_fmt=png)

直接双击它就可以使用了，双击后打开的界面如下：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFYeyJvkQBDch7lqjdz31goibiajdy4dhUB91TlxfuYjqG0CqnIA7qM0FQ/640?wx_fmt=png)

在打开的界面中，左边分为本地和远程，只要你本地有 java 进程启动，他就会自动检测到，比如 **DeadLock（pid 8120）** 就是我本地启动的死锁的案例。

你也可以连远程服务器的 Java 进程，不过一般正式环境的服务器都不会让你连接的，我们的正式环境只有 QA 才能操作，我们开发人员只能才做 dev 环境，像 test 环境都只能提测给 QA 有他们才能操作，这里我们就直接使用笨的操作。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tF5CTB8FwOsd8I8sJCayic4SgQ6IibkAL6jLYVClf9435ibyOKoQ7LXd4CA/640?wx_fmt=png)

在右边的内容区就可以看到我们主要关注的板块了，比如**概述显示的就是我们启动 Java 进程时设置的 JVM 参数以及系统的属性（一般默认）**。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFWVzCiblSygEbrYY8RGjPFzqIO8MJAialNMZklJJiaJjyVrQMwxib5DWIUA/640?wx_fmt=png)

还有就是监视板块，它显示的 GUI 界面如上图所示，展示的是 **CPU 的使用情况、堆大小的使用情况、Metadata（元空间的使用情况）、以及该进程中装载的类和线程数情况**，所以可以通过这个界面至少可以观察到 CPU 的健康状况和内存堆大小的变化情况。

第三个板块线程肯定就是现实线程的运行时的信息，可以看到这里直接就显示检测到死锁的存在，可以说这个工具还是非常的智能的，在所有线程中可以看看到两个爆红的 **DeadLock1** 和 **DeadLock2** 线程。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFDWLLfib47od4SCknp12A5NV5GDJbPD178dBpfhWKTZ3DjSN5icibakkUw/640?wx_fmt=png)

然后点击**右边的线程 Dump**，就可以看到线程的堆栈信息：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFAI1yBDic1NiarIBTxQ8lLGOXjhbzD0aViakIOusHcmxsGd8rautE4Jozg/640?wx_fmt=png)

可以看到两个线程在相互等待，各自等待的锁，都同时被对方持有就就陷入的死锁的局面。

所以通过第三板块的线程就能排查死锁的，然后就是**抽样器和 Profiler 可以查看 cpu 的内存情况**：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFnwGzQqDtnqLGicK2jxSOOYx6XozIvoMSHvxBrwYA6TL2ALkBaYvNbnA/640?wx_fmt=png)

最后的就是 **Visual GC**，这个必须要安装插件才有，安装的方式就是在导航栏的工具下的插件就可以安装，具体安装的话可以百度一下，挺简单的：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tF9FxRiaF46av4rKbvPqXHTq3HpDDHXGnnSadn1MXia5JpKG6kUghP1iarA/640?wx_fmt=png)

在这块的内容就可以看到包括 **Compile Time（编译时间）、Class Loader Time（类加载器加载类的时间）、GCTime（总 GC 的时间）、Eden、S1、S0、Old Gen（老年代大小）、Matadata（元空间的大小）**。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFUttp6Eub582SFacSHheOjzG4gA6RIb0y9vNBkNtZbicPat0M09XJ4VQ/640?wx_fmt=png)

对于调优 Java 堆，主要关注的几块内容就是下面这几块：**Eden、S1、S0、Old Gen** 区域的大小变化，以及 **XXX collections、XXXms 属性的变化，他们分别表示：发生了多少次 GC，以及发生 GC 过程的时间是多少**。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFUh5QzIdwIap5pu7Mr9AFJyiac9CKiczmicKRFte9u1CqOFWjScIvmynOA/640?wx_fmt=png)

还有一块是堆存储文件的分析，这一块也是在如下图所示，能够看到堆中实例数的占比，可以分析 OOM 问题、以及内存泄露的问题，结合线程的堆栈信息就能够及时的排查出 OOM：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFJ7hzaia9KmyONqiapibSr8zCM7icwbLZ5IOYEBJoydSnHqCr0q04CdFMtg/640?wx_fmt=png)![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFI3BictUBfGcwfVR0ufN2tXKRMI27TI7vIwtaokOfyBpTdV3oaW4jG3A/640?wx_fmt=png)

关于 Visual VM 的详细使用就说到这里，基本开发中要用的，都提到了，详细的自己也可以找一找教程进行深入的学习。

### Arthas

**Arthas** 是阿里的开源项目之一，可以用于生产中分析 Java 进程的具体情况，分析的一些内容和 Visual VM 很多都是类似的，只不过人家是 Linux 环境的使用命令的方式。

#### 下载

首先是下载 **Arthas**，直接使用 **wget** 方式：

```
wget https://alibaba.github.io/arthas/arthas-boot.jar
```

下载后他就是一个 jar 项目，可以通过 java 命令直接启动。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFgugJSPYdUR8Libw6mzXQZB3wGob6QiaIGmwI0FDNPz2UhwibQQrV9iauLQ/640?wx_fmt=png)

对于后面的实例讲解，比如 **OOM 案例**，**死锁案例**，**CPU 飙高案例**，为了节约时间，我都是直接使用 **javac** 和 **java** 命令来编译和运行的，比较方便，就不再创建 SpringBoot 项目了，大家也可以提前先配置好自己的 jdk 的环境变量。

#### 启动

下载完 **Arthas** 后就可以启动它，直接通过下面命令进行启动：

```
java -jar arthas-boot.jar -h
```

我先启动自己的案例 OOM

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tF8hzO8rujGbRN0FRexYeG83ibkGtibaB8XNicY0WYIaShZxol83bnHn0fQ/640?wx_fmt=png)

然后，再启动 **Arthas** 后，他就会自动检测到 Java 进程，然后选择你要监测的 Java 进程，比如 OOM，所以直接输入 1：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFaSdv418ibfEwZfJOrhXI1Wr5aUicRo9Alq3lnwicvBvs8KYtEauvsMUKw/640?wx_fmt=png)

输入 1 后就成功进入到 **Arthas**：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFknp915yb3SJ45Lbia4wchvNpFkrU1dtO2NQTa5oqhESQoN4WuicY9uQg/640?wx_fmt=png)

下面来了解 Arthas 的常用的命令，非常详细的就不会讲解，太多命令了，只讲几个常用的，也是案例中用到的，详细的学习可以移步到这里：Arthas 官方文档。

#### dashboard

输入 **dashboard** 命令后就会出现监控面板：**类图向界面，用于观察每个线程及所占的 CPU**。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFLSibtCPHlJFQgy2uEajhu4jD66YBRckBwjtpwKCPa1SBXNXQmC6hXuQ/640?wx_fmt=png)

对于里面的每一个字段表示什么意思，应该英文不差的，也能知道，我这里直接查百度截个图，就不一个一个敲了，太多了，大家可以下面的这个图：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFzq0UHwHFSSupqazh5YX2BtdjCFwic1f2uLc0ds8icLeia5EdLZSG5uwhw/640?wx_fmt=png)

#### thread

**thread** 命令是查看线程的信息的，包括**线程堆栈信息，线程占用的 CPU 信息**诸如此类。

thread 的参数如下所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFmxKyA0ibfFzLv8dGBrLzrUUga1ScCFanA2N2uibmaGpuJeSWia3A4Jx4Q/640?wx_fmt=png)

直接输入 **thread** 后，就可以查看到其中的 **children-thread** 是占用 cpu 最高的，而 **children-thread** 这个名字是我创建这个线程时赋予这个现成的名字。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tF7LZgzy8PArysAnqE0y5ChDTEkN6fLtadcpGSNUh50rtHia0zNhaBoDw/640?wx_fmt=png)

在阿里的开发手册中也有提到，对于线程池的创建最好命名，这样就是为了出现问题的时候方便排查原因：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFmUv1b9Q222XNsEFm68ia6jsTlLsUGicM3fY0nq8GFN2EX9nRSK2q5UCg/640?wx_fmt=png)

阿里这个开发手册挺棒的，强推一下，它的开发手册除了一些规范之外，也包括一些调优：sql 调优，有兴趣的可以看一看，之前应该挺多技术博主都在公众号中发过这本 pdf。

#### jvm

jvm 这个命令就可以**用于查看当前 JVM 信息**

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFCB6N1tkIRTgdhJavTeH8HCqFegbxGYXb7I3oHc9bmRZlJtgYibN4HmQ/640?wx_fmt=png)

上图详细的字段信息可以参考以下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tF0GWyicI7c6XGRlFD25Hz7CuUb1aYZQsAk5YofRoR69kvAd2ibl55gic9Q/640?wx_fmt=png)

#### trace

**trace** 命令是用来**监测类内部方法的调用的，以及方法调用的耗时情况**：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFSfhmDicDeffO2bsFZP36mQNhIx90ZLuYUBuG24yXsz5WsicCENGINztQ/640?wx_fmt=png)

如果一个方法的耗时过长，那么这个方法很可能就有问题，需要 review 原来的代码。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFDtG8lrEOBGavT8HSDECs1WfsGia59mYlz12jBic6w637ia6uhjBHhkmsg/640?wx_fmt=png)

**sc -d 类名**还可以查看类的信息，如下所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFq217ca2OpbYeaFtLagdv7doegjnxialAOLE3h2ibzUtCgqiannmdIERTQ/640?wx_fmt=png)

#### jad

jad 命令是将 **JVM 中实际运行的 class 的 byte code 反编译成 java 代码**，是比较强大的命令，比如：我们可能要线上检查代码的一些问题，就可以使用这个命令进行反编译：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFfUor5PwDdbIB4d7ujCicVXlOiawBYdJiaAVJEoDTVSOzkfrSDvJEaUNMA/640?wx_fmt=png)

#### watch

watch 命令可以用来**查看制定方法的调用情况**，观察的范围包括：**返回值、抛出异常、入参**等。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tF0icKr3ho5ib167C1J36f4ephIgEdcRjCZxcAw44DBNRSjHh5wlPG47oA/640?wx_fmt=png)

具体的详细参数可以参考如下：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tF7s5F5VmBacjSByYa3Ymmf3QGnqEj8PUHvpCxBhzeOVpqOnqRAtibcfg/640?wx_fmt=png)

### 原生命令

除了上面介绍的第三方的工具，还有可以使用 linux 的原始命令来排查问题，主要有以下几种命令：

*   jps

*   top

*   jstack

*   jstat

*   jmap

*   jhat

*   jinfo


#### jps

**jps** 可以列出正在运行的虚拟机进程，并显示虚拟机执行主类（Main Class，main() 函数所在的类）名称以及这些进程的本地虚拟机唯一 ID（LVMID，Local Virtual Machine Identifier）。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFuQPPbkqTD7RYmIo4yzqNMBHACSMQFWriaia0o6X8tRY5eTHbic3yYsTyg/640?wx_fmt=png)

这里查到 Java 进程的 Id 后就可以进行后面的操作，比如查进程内的线程情况。

若是想详细了解 jps 命令的的一些参数，可以使用 man 命令进行查询，比如 **man jps**：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFXeCj2WBR5EtUFWjS3cYqJGt5eDicSTD6jWwLsNwHKhBRhIC1Kzibnz7g/640?wx_fmt=png)

<table data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)"><thead data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)"><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><th data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)|rgb(240, 240, 240)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); min-width: 85px;">选项</th><th data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)|rgb(240, 240, 240)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); min-width: 85px;">作用</th></tr></thead><tbody data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)"><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">-q</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">用于只输出进程 PID 信息，省略主类的名称信息</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">-m</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">输出虚拟机启动的时候传给主类 main() 函数的参数</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">-l</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">输出主类的全名，以及包名的全路径</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">-v</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px; word-break: break-all;">输出虚拟机进程启动时的 JVM 参数</td></tr></tbody></table>

#### top

top 命令是查询各个进程的 cpu 使用情况，可以结合 ps 来使用，如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFmicxoY103QkQh2yHw6OYcNHoa8gibpuTl5fqibXm69xTR8ibXIjJ4B3S9w/640?wx_fmt=png)![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tF8kID3JqaPw0qHUnmDY4gZGSmNKh9Az0ic2CBpu6xn0vomiaNqWE6vvyQ/640?wx_fmt=png)

#### jstack

**jstack** 主要是用于**查询线程的堆栈信息情况**，用它就可以轻而易举的**排查死锁**的信息，比如如下图所示就是排查死锁，使用 **jstack pid**，若是发现死锁，它会给你提示的：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFBgk32QHE6uLMCIhG58EgzwN7DfDcJ9SNkia5Cr1Aua4kf6PTibZwy1Qg/640?wx_fmt=png)![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFmicrohQyAibSjOcDPMnZjJ0jKqSmcpObI63OzLD2Ky9h86Vzty9Z8qEQ/640?wx_fmt=png)

#### jstat

**jstat 是虚拟机统计信息的监视工具，用于统计虚拟机运行时状态信息的命令，比如 GC 信息、内存信息、即时编译器运行时的信息等**。

jstat 有挺多参数的，常用的如下所示：

<table data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)"><thead data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)"><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><th data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)|rgb(240, 240, 240)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); min-width: 85px;">选项</th><th data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)|rgb(240, 240, 240)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); min-width: 85px;">用途</th></tr></thead><tbody data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)"><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">-class</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">输出类加载、卸载数量、总空间以及类卸载耗时</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">-gc</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">输出 Java 堆情况，包括 Eden、S1、S0、老年代、等空间的容量、已使用量、垃圾回收时间等信息</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">-gccapaccity</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">也是监视 Java 堆的情况、但是主要关注 Java 堆各个区域使用到最大、最小空间</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">-gcutil</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">输出 Java 堆中已使用空间占用总空间的百分比</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">-gccause</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">与 - gcutil 功能一样，会额外输出上一次垃圾收集产生的原因</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">-gcnew</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">输出新生代的情况</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">-gcnewcapacity</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">也只关注新生代，主要是关注使用的最大、最小空间</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">-gcold</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">输出老年代的情况</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">-gcoldcapacity</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">也只关注老年代，主要是关注使用的最大、最小空间</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">-gcpermcapacity</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">输出永久代使用的最大、最小空间</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">-compiler</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">输出即时编译过的方法、耗时等信息</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">-printcomilation</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">输出已经被编译过的方法</td></tr></tbody></table>

使用 **jstat -gc pid** 就可以查看如下图所示的 gc 的一些情况

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFRULRdReKPNbsp9A62fIlY37vibtN6DCH6C7XBoyKiapMllaicgaIm99icw/640?wx_fmt=png)

上面的字段分别表示为：

<table data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)"><thead data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)"><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><th data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)|rgb(240, 240, 240)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); min-width: 85px;">列名</th><th data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(40, 40, 40)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)|rgb(240, 240, 240)" data-style="border-top-width: 1px; border-color: rgb(204, 204, 204); text-align: left; background-color: rgb(240, 240, 240); min-width: 85px;">作用</th></tr></thead><tbody data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)"><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">S0C</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">年轻代中第一个 survivor（幸存区）的容量 (字节)</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">S1C</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">年轻代中第二个 survivor（幸存区）的容量 (字节)</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">S0U</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">年轻代中第一个 survivor（幸存区）目前已使用空间 (字节)</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">S1U</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">年轻代中第二个 survivor（幸存区）目前已使用空间 (字节)</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">EC</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">年轻代中 Eden（伊甸园）的容量 (字节)</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">EU</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">年轻代中 Eden（伊甸园）目前已使用空间 (字节)</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">OC</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">Old 代的容量 (字节)</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">OU</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">Old 代目前已使用空间 (字节)</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">MC</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">元空间的容量 (字节)</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">MU</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">元空间目前已使用空间 (字节)</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">YGC</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">从应用程序启动到采样时年轻代中 gc 次数</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">YGCT</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">从应用程序启动到采样时年轻代中 gc 所用时间 (s)</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">FGC</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">从应用程序启动到采样时 old 代 (全 gc)gc 次数</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: rgb(248, 248, 248);"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">FGCT</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(32, 32, 32)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(248, 248, 248)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">从应用程序启动到采样时 old 代 (全 gc)gc 所用时间 (s)</td></tr><tr data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-width: 1px 0px 0px; border-right-style: initial; border-bottom-style: initial; border-left-style: initial; border-right-color: initial; border-bottom-color: initial; border-left-color: initial; border-top-style: solid; border-top-color: rgb(204, 204, 204); background-color: white;"><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">GCT</td><td data-darkmode-color-16336206948347="rgb(163, 163, 163)" data-darkmode-original-color-16336206948347="#fff|rgb(0,0,0)" data-darkmode-bgcolor-16336206948347="rgb(25, 25, 25)" data-darkmode-original-bgcolor-16336206948347="#fff|rgb(255,255,255)" data-style="border-color: rgb(204, 204, 204); min-width: 85px;">从应用程序启动到采样时 gc 用的总时间 (s)</td></tr></tbody></table>

关于 **jstat** 的一些其他参数的详细解释，大家可以查查文档或者直接使用 **man jstat** 来查看。

#### jmap

jmap 应该是使用比较少的工具，因为**他的作用就是生成堆转储快照，但是它会消耗系统资源，所以一般不用**，取而代之的就是使用 JVM 参数：**-XX:+HeapDumpOnOutOfMemoryError** 和 **-XX:HeapDumpPath= ${目录}** 。

然后，就是使用内存分析工具堆堆转储文件进行分析一波，所以这个命令，大家了解一下就好，不过一般项目比较小的话，一般的传统项目，你还是可以用的，对于互联网项目那就不行了。

#### jhat

jhat 也是大家要了解就行的命令，它主要是**虚拟机堆转储快照分析工具**，因为一般都会生成堆转储文件，然后使用 GUI 界面来分析，很少在线上分析堆转储文件。

因为**他也是要消耗服务器性能的，而且一般分析的过程都会比较长，所以不会采用在线上分析的方法**。

#### jinfo

jinfo 的作用是实施的**查看和调整虚拟机的各项参数**，不过我们可能一般更加关注的是在虚拟机启动的时候，我们设置的参数，可以通过 **jps -v** 来查询。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFbCzHa7YAzfwuibl28C7HVIhIHgqVOdmoNbaUsgibSPgd8V4OT87nsibaQ/640?wx_fmt=png)![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaO0l8SCB3rL4noAfNsM4tFmicINfo5bxvuErvTwY1tVkFSQfgABNag8DZKg7icr9iclvqicygFEcuTCg/640?wx_fmt=png)

好了，这一篇限于篇幅的原因，先写到这里，下一篇再继续了解 JVM 的调优实战案例分析，原来想一篇写完的，但是篇幅估计得达到两万多字，太长了，所以分两篇吧，有需要**阿里巴巴开发手册**的，和文章中使用到的**案例的源代码**的，可以加我微信：**abc730500468** 获取，这一期就到这里，我是黎杜，我们下一期见。

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