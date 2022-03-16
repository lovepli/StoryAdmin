> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483948&idx=1&sn=ee9b3ad4dd1dadd174984d1cedb5760a&scene=21#wechat_redirect)

![](https://mmbiz.qpic.cn/mmbiz_jpg/IJUXwBNpKlgJOCN8LQa1s2wh2iaQd5hm0R2Sm1PdU813jMv2eZI9bcbauec83juvORZpF9Y5B9BibAjRCRJMQesg/640?wx_fmt=jpeg)

### 本文脑图

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgJOCN8LQa1s2wh2iaQd5hm0pGupMvIB3Pb0JTyJ40I3znJ43TQUE3qLfDUU2ibCGh26ickRbZdgCvOQ/640?wx_fmt=png)

### 运行时数据区模型

在 java 虚拟机中把内存分为若干个不同的数据区域。这些区域有各自的用途，有些区域随着虚拟机进程启动而存在，有些区域则依赖用户线程的启动和结束而建立和销毁。在 JVM 中主要分为以下几个区域：

1.  程序计数器

2.  方法区

3.  虚拟机栈

4.  本地方法栈

5.  java 堆


![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgJOCN8LQa1s2wh2iaQd5hm0mlFicVH1jCqXMrhAGDN62zqfDkCUvI4xDOm0e5DYYHtH0seW0xAVMAg/640?wx_fmt=png)

在这里插入图片描述

#### 程序计数器

程序计数器是内存中较小的一部分区域，是当前线程执行的字节码的行号指示器。在字节码解释器工作时通过计数器的值来选取下一条指令。

> **为什么需要计数器？**  
> 多线程情况下，一条线程中有多个指令，为了使线程切换可以恢复到正确执行位置，每个线程都具有各自独立的程序计数器，所以该区域是非线程共享的内存区域。

如果执行的是 Java 方法，计数器记录的是正在执行的字节码指令的地址；若执行的是 Native 方法，计数器存储为空。**这块内存区域是虚拟机规范中唯一没有 OutOfMemoryError 的区域。**

我们可以得出程序计数器主要有两个作用：

1.  字节码解释器通过改变程序计数器来依次读取指令，从而实现代码的流程控制，如：顺序执行、选择、循环、异常处理。

2.  在多线程的情况下，程序计数器用于记录当前线程执行的位置，从而当线程被切换回来的时候能够知道该线程上次运行到哪儿了。


#### 方法区

方法区也称 "`永久代`"，它用于存储虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据，是各个线程共享的内存区域。

在 JDK8 之前的 HotSpot JVM，存放这些” 永久的” 的区域叫做 “`永久代(permanent generation)`”。永久代是一片连续的堆空间，在 JVM 启动之前通过在命令行设置参数`-XX:MaxPermSize`来设定永久代最大可分配的内存空间，默认大小是 64M（64 位 JVM 默认是 85M）。

方法区或永生代相关设置

*   `-XX:PermSize=64MB` 最小尺寸，初始分配

*   `-XX:MaxPermSize=256MB` 最大允许分配尺寸，

*   按需分配`XX:+CMSClassUnloadingEnabled`

*   `-XX:+CMSPermGenSweepingEnabled` 设置垃圾不回收

*   `-server`选项下默认`MaxPermSize为64m`，`-client`选项下默认`MaxPermSize为32m`


java 虚拟机规范堆方法区限制非常的宽松，可以不选择垃圾回收，以及不需要连续的内存和可扩展的大小。这个区域主要是针对于**常量池的回收以及对类型的卸载**，当方法区无法分配到足够的内存的时候也会抛出 OOM。

#### 虚拟机栈

虚拟机栈是每个 java 方法的内存模型：每个方法被执行的时候都会创建一个 "栈帧", 用于存储**局部变量表 (包括参数)、操作栈、方法出口**等信息。每个方法被调用到执行完的过程，就对应着一个栈帧在虚拟机栈中从入栈到出栈的过程。

平时说的栈一般指局部变量表部分。栈帧对应的结构图，如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgJOCN8LQa1s2wh2iaQd5hm0TqGr3kpR4YexZ1Be7ILowIrwtbSaINIIGciaxqIYFMNo65H1V1rZYtQ/640?wx_fmt=png)  
局部变量表所需要的空间在编译期完成分配，当执行一个方法时，该方法需要在栈帧中分配多大的局部变量表的空间完全是可以确定的，因此在方法运行的期间不会改变局部变量表的大小。

初级程序员可能笼统的将 Java 内存分为**堆内存**和**栈内存**时，该区域就是常说的栈内存，该区域的局部变量表存放**基本类型、对象的引用类型**，在对象的引用类型中存储的是指向**对象的地址**。

该区域会出现两种异常

1.  当线程请求的栈深度大于虚拟机所允许的深度，就会抛出`StackOverflowError`异常。

2.  一般虚拟机的内存都是**动态扩展**的，但是有可能动态的扩展还是配不到足够的内存，就会抛出 OOM 异常。


#### 本地方法栈

本地方法栈（Native Method Stacks）与虚拟机栈所发挥的作用是非常相似的，其区别不过是虚拟机栈为虚拟机执行 Java 方法（也就是字节码）服务。

本地方法栈为虚拟机使用到的 native 方法服务，可能底层调用的 c 或者 c++, 我们打开 jdk 安装目录可以看到也有很多用 c 编写的文件，可能就是 native 方法所调用的 c 代码。

#### Java 堆

`Java 堆（Java Heap`）是 Java 虚拟机所管理的**内存中最大的一块**。Java 堆是被**所有线程共享**的一块内存区域，在虚拟机启动时创建。

此内存区域的唯一目的就是存放对象实例，几乎所有的对象实例都在这里分配内存。堆内存是**所有线程共有的**，可以分为两个部分：**年轻代**和**老年代**。

> 注意: 它是所有线程共享的，它的目的是存放对象实例。同时它也是 GC 所管理的主要区域，因此常被称为 **GC 堆**。根据虚拟机规范，Java 堆可以存在物理上不连续的内存空间，就像磁盘空间只要逻辑是连续的即可。它的内存大小可以设为固定大小，也可以扩展。当前主流的虚拟机如 HotPot 都能按扩展实现 (通过设置 `-Xmx`和`-Xms`)，如果堆中没有内存内存完成实例分配，而且堆无法扩展将报 OOM 错误 (OutOfMemoryError)

新生代又分为：`Eden`空间、`From Survivor`、`To Survivor`空间。进一步划分的目的是更好地回收内存，或者更快地分配内存。

**分代回收**的原因：**对象的生命周期不同**，所以针对不同生命周期的对象可以采取不同的回收方式，以便提高回收效率。从内存分配的角度来看，线程共享的 java 堆中可能会划分出**多个线程私有的分配缓冲区**。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgJOCN8LQa1s2wh2iaQd5hm0BibNZo4tPwJHe1UcQRBUicv7cT1lxs0VPez8GGdwAbHRVp8UQV6HmfOQ/640?wx_fmt=png)

### 垃圾回收算法

#### 标记 - 清除

**标记 - 清除**是最基本的回收算法，后面的算法的设计的思想都是基于此算法进行设计。标记 - 清除算法一共分为两个阶段**标记**和**清除**阶段，标记阶段是将不可达的对象（即为不存活的对象）进行标记，接着清除阶段将这些标记的对象进行清除。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgJOCN8LQa1s2wh2iaQd5hm0HJPmahhw70CNViaRqwqXZNX7PXX0UwfoRv8EV2KDwGAic60ickG7UlE5A/640?wx_fmt=png)  
标记 - 清除算法主要有**两个问题**：一个是**效率的问题**，标记和清除两个过程效率都不高；另一个问题就是该算法会**产生很多的内存碎片**，大量的不连续内存空间，当程序需要申请较大的内存空间存储大对象的时候，有可能无法申请到足够的内存空间而不得不再一次触发一次垃圾回收动作。

#### 复制算法

复制算法将**内存划分为两个区间**，在任意时间点，所有动态分配的对象都只能分配在其中一个区间（**称为活动区间**），而另外一个区间（**称为空闲区间**）则是空闲的。

当有效内存空间耗尽时，JVM 将暂停程序运行，开启复制算法 GC 线程。接下来 GC 线程会将活动区间内的存活对象，全部复制到空闲区间，且严格按照内存地址依次排列，与此同时，GC 线程将更新存活对象的内存引用地址指向新的内存地址。

此时，空闲区间已经与活动区间交换，而垃圾对象现在已经全部留在了原来的活动区间，也就是现在的空闲区间。事实上，在活动区间转换为空间区间的同时，垃圾对象已经被一次性全部回收。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgJOCN8LQa1s2wh2iaQd5hm0KibqK92ibSeL037vXD4BkY74dThTvho9Zpm1LvicVFG9MSlI0zic6MtYHg/640?wx_fmt=png)

新生代中因为对象都是 "朝生夕死的"，深入理解 JVM 虚拟机上说 98% 的对象存活率很低，适用于复制算法，复制算法比较适合用于存活率低的内存区域。它优化了标记 / 清除算法的效率和内存碎片问题。

JVM 不是平分内存，新生代中由于存活率低，不需要复制保留那么大的区域造成空间上的浪费，因此不需要按 1:1 划分内存区域，而是将内存分为一块 Eden 空间和 From Survivor、To Survivor【保留空间】。

（1）新生代：大多数对象在新生代中被创建，其中很多对象的生命周期很短。每次新生代的垃圾回收（又称 **Minor GC**）后只有少量对象存活，所以选用**复制算法**，只需要少量的复制成本就可以完成回收。

> 在新生代内存中每次只是用 Eden 和其中的一个 S 区。当 Eden 区满时，还存活的对象将被复制到两个`Survivor区`中的一个。当这个 Survivor 区满时，此区的存活且不满足 “晋升” 条件的对象将被复制到另外一个 Survivor 区。
>
> 对象每经历一次`Minor GC`，年龄加 1，达到 “晋升年龄阈值” 后，被放到老年代，这个过程也称为 “晋升”。显然，“晋升年龄阈值” 的大小直接影响着对象在新生代中的停留时间，在`Serial`和`ParNew GC`两种回收器中，“晋升年龄阈值” 通过参数`MaxTenuringThreshold`设定，默认值为 15。

（2）老年代：在新生代中经历了 N 次垃圾回收后仍然存活的对象，就会被放到年老代，该区域中对象存活率高。老年代的垃圾回收（又称 **Major GC**）通常使用**标记 - 清理**或**标记 - 整理**算法。整堆包括新生代和老年代的垃圾回收称为 **Full GC**。

（3）永久代：主要存放元数据，例如`Class`、`Method`的元信息，与垃圾回收要回收的 Java 对象关系不大。相对于新生代和年老代来说，该区域的划分对垃圾回收影响比较小。在 **JDK 1.8 中移除整个永久代**，取而代之的是一个叫**元空间**的区域。

> 默认的 Eden : from : to = 8 : 1 : 1 (可以通过参数 –XX:SurvivorRatio 来设定)，即：Eden = 8/10 的新生代空间大小，from = to = 1/10 的新生代空间大小。

在新生代中，并不是每次存活的对象都少于 10%，有时候若是存活的对象大于 10%，就会向老年代进行**空间分配担保**。

#### 标记 - 整理

标记 - 整理算法也分为两步，首先标记不可达的对象，然后存活的对象往一端移动，然后直接清理掉端边界以外的内存。

老年代中存活率比较高，要是使用复制算法，会大量浪费时间在复制对象上，因此复制算法不适合用在存活率比较高的场景。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKlgJOCN8LQa1s2wh2iaQd5hm0mn0Slp9icnxeYiaYDWSO4QbibO6RlGWjm4sATZncTNhQCUDDT17072qSg/640?wx_fmt=png)  
标记的存活对象将会被整理，按照**内存地址依次排列**，而未被标记的内存会被清理掉。如此一来，当我们需要给新对象分配内存时，JVM 只需要持有一个**内存的起始地址**即可，这比维护一个空闲列表显然少了许多开销。

不难看出，标记 / 整理算法不仅可以弥补标记 / 清除算法当中，内存区域分散的缺点，也消除了复制算法当中，内存减半的高额代价。

不过任何算法都会有其缺点，标记 / 整理算法唯一的缺点就是效率也不高，不仅要标记所有存活对象，还要整理所有存活对象的引用地址。从效率上来说，**标记 / 整理算法要低于复制算法**。

> 关于 JVM 深入研究以及 JVM 调优，后面的文章会继续深入，这篇文章先介绍 JVM 的内存模型，以及回收算法。

![](https://mmbiz.qpic.cn/mmbiz_jpg/IJUXwBNpKlgJOCN8LQa1s2wh2iaQd5hm0NU1ZZ2hxiatPkQZhZ0v4t4mgmViczqZNAbXnAF6Vt3oNA9rd5FjWppjQ/640?wx_fmt=jpeg)

_**[往期精彩回顾]**_

[[万字长文，一文搞懂 TCP/IP 和 HTTP、HTTPS]](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483880&idx=1&sn=b4b71f169dddc41925814ab4e5bc208b&chksm=fbf7e82acc80613c2f9d2e11aba576dd5d367140ab74c33f6b79e0e6a3cbb84e388a50fded7d&scene=21#wechat_redirect)

[[Mysql 优化提高笔记整理，来自于一位鹅厂大佬的笔记](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483938&idx=1&sn=3d97fd3aa5601834138b33e3dddfc544&chksm=fbf7ebe0cc8062f69320ea0b10d92bea8c123801cd15fa1870c91ad29e62c503185e46111716&scene=21#wechat_redirect)] 

[[B 树、B - 树、B + 树、B * 树图文详解]](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483924&idx=1&sn=54f8069ebf3e289c9d05c56c839d57ff&chksm=fbf7ebd6cc8062c0acdbaaadd09bca7a01e050cbbbd8f281b95d6c32db77203cea8266d5715f&scene=21#wechat_redirect)

[[万字长文，最硬核的 mysql 知识总结]](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483886&idx=2&sn=3b9a0dde6fa9b06f3fba6a0aeca83820&chksm=fbf7e82ccc80613ab4eeda8ced91d6b6b6c8143693e864711de395f112a7f1cdef257ec26ecb&scene=21#wechat_redirect)

[[为了把 mysql 的索引底层原理讲清楚，我把计算机翻了个底朝天]](http://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483934&idx=1&sn=e95a47a8255ac4a814a53afbc54e513a&chksm=fbf7ebdccc8062cab93bec95d884c8a11f6fb054caa3b1b8e4c6b6ee466944ff2a8b0affd695&scene=21#wechat_redirect)