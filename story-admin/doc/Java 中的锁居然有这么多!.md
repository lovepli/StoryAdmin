> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/BwkOoHC02z3yDHE9rfgynA)

小伙伴们晚上好呀~

干货可能会迟到，但是不能缺席呀！😄

嘿嘿  这篇来讲讲锁 🔒 啦~

看完上文的 `ConcurrentHashMap` 是不是发现有很多个 锁呀，这篇就带大家缕一缕~ 😝

![](https://mmbiz.qpic.cn/mmbiz_gif/zIjiaGEEKdYEMtm8j5FMx1CYXNE6BRlUVUeuxllVguBFyBeoxdSC9yI5Sf1KA1VTGT7TUXOfE2jqBoXYC2IRiaPA/640?wx_fmt=gif)img

* * *

### Java 中的锁

为啥是 Java 中的锁呢， 因为 锁的种类 也有很多的，像我们平时使用的 **「MySQL」**，它也有自己的 **「表锁，行锁，间隙锁」** ... ... 还有 **「基于 redis 的分布式锁」** （**「RedLock——红锁」**）呀，**「zookeeper 的分布式锁」** 等各种各样的🔒~

埋个坑🕳 ~ 以后写数据库专题的时候写写 😝

4ye 总结了一份思维导图，小伙伴们可以看看~

![](https://mmbiz.qpic.cn/mmbiz_png/zIjiaGEEKdYEMtm8j5FMx1CYXNE6BRlUV2icsogkw9gztNtnSmm4m5icmpUMCFtbVE5CuRHNObUpyfWqYDcpmB5bg/640?wx_fmt=png)image

### 乐观锁

说到这个就不得不提下 `JAVA` 中的 `CAS` 了，它是这种思想的具体实现~，还记得上文 频繁出现的 `Unsafe` 类吗，`ConcurrentHashMap` 就是通过它去调用这个 `CAS` （ Compare And Swap / Set ），去设置值的 😋

#### 概念：

读不加锁，更新数据期间会加锁（保证原子性）

#### 详解：

**「读数据时」** 会很乐观的认为别的线程没有在修改数据，所以不会上锁。

**「写数据时」** 会判断当前值和期望值一不一样，一样的话会进行修改，此时修改会加锁。

（这里还有些很硬核的点，涉及到硬件层面的锁~  **「基于 MESI 协议滴」**  后面具体专题再扩展下！😝 ）

#### 实现方式：

CAS 机制、版本号机制，时间戳机制

为什么会多后面两种机制呢，其实这里是为了解决这个 **「ABA 问题」**

##### ABA 问题

场景模拟，现在有三条线程

线程 1 读取变量 a，此时 a=1

线程 2 读取变量 a，此时 a=1, 比较后将它改为 a=2

线程 3 读取变量 a，此时 a=2, 比较后将它改为 a=1

这时线程 1 发现变量 a 还是 1 ，和原来一样，就将它改成其他值了

可以发现这个过程中 线程 1 在修改值的时候，线程 2,3 已经修改过变量 a 的值了，但是它毫不知情~

所以呢，为了解决这个问题，就引入了这个**「版本号机制 或者 时间戳机制~」**

其实就是多比较一个值，比如 **「每次更改时再比较下这个版本号或者时间戳对不对得上~」**

额 这里既然只讲 Java ，那也不扯远啦~  嘿嘿，不过道理还是通用的！

小伙伴们可以参考下 这个 **「原子类中的」** `AtomicStampedReference`  ，它就解决了这个 **「ABA 问题」**

### 悲观锁

这个就和乐观完全相反啦~  不管读操作还是写操作，都悲观的认为会被别的线程改变，所以 **「不管是读还是写都会 加锁」**

#### 概念：

悲观的认为，读写都要加锁，不然值会被其他线程改变~

#### 实现方式：

**「synchronized」** ，**「ReentrantLock」**

### 公平锁

公平嘛，要讲究先来后到  😄

#### 概念：

> ❝
>
> 多个线程按照申请锁的顺序来获取锁
>
> ❞

**「原理」**：主要依赖于维护这个锁的  **「等待队列」**，当队列为空时就直接占有锁, 不为空就加入到 **「等待队列」** 的末尾，然后按照 **「FIFO」** 的原则去获取锁。

#### 实现方式：

创建 **「ReentrantLock」** 时，显示指定 `new ReentrantLock(true)`

其实是靠这个 **「AQS」** 来实现公平和非公平的，这里也埋个坑🕳 后面会详解这个专题的😋

![](https://mmbiz.qpic.cn/mmbiz_png/zIjiaGEEKdYEMtm8j5FMx1CYXNE6BRlUVnoMBJTZ1Z81ibarBvtuKwMavbYVLYsGph4ATryK1MObuSficCdSdIZ7A/640?wx_fmt=png)image

### 非公平锁

这个就不和你讲先来后到了  😄

#### 概念：

> ❝
>
> 多个线程 不按照先到先得的方式去获取锁， 有可能后申请的线程会先得到锁~
>
> ❞

**「原理」**：非公平锁会尝试获取锁，失败的话会加入到 **「等待队列」** 的末尾，然后按照 **「FIFO」** 的原则去获取锁 ，变成公平锁的方式~

#### 实现方式：

创建 `ReentrantLock` 时，显示指定 `new ReentrantLock(false)`  或者使用默认的方式 `new ReentrantLock();`

![](https://mmbiz.qpic.cn/mmbiz_png/zIjiaGEEKdYEMtm8j5FMx1CYXNE6BRlUVtKNS8hczLyXoPwl7AGve0LpdlkIPstEhciarrdJCajEdRtIElCGBiazw/640?wx_fmt=png)image

还有 `synchronized` 这个关键字也是非公平的

### 独享锁 (独占锁)

独自占有锁，不和其他线程共享~ 😄   和 互斥锁，排他锁，悲观锁 同义

#### 概念：

> ❝
>
> 只允许一条线程占有该锁
>
> ❞

#### 实现方式：

`synchronized` ，`ReentrantLock` 还有 `ReentrantReadWriteLock`  中 的 **「写锁」**

### 共享锁

可以和其他线程共享该锁~ 😄   和 乐观锁，读写锁 同义

#### 概念：

> ❝
>
> 锁可被多个线程所持有
>
> ❞

#### 实现方式：

`ReentrantReadWriteLock` ，`ReadWriteLock`  这两个中的 **「读锁」**

### 互斥锁 (同步锁)

可以理解为独占锁的具体实现~😄

#### 概念：

> ❝
>
> 表示该资源只能被一条线程访问，不能被其他访问
>
> ❞

#### 实现方式：

`synchronized` ，`ReentrantLock`

### 读写锁

顾名思义~ 有读锁和写锁 😄

*   读读不互斥

*   读写互斥

*   写写互斥


#### 概念：

> ❝
>
> 表示该资源允许 **「多条持有读锁的线程共同访问，但是只允许一条持有写锁的线程独占」**
>
> ❞

#### 实现方式：

`ReentrantReadWriteLock` ，`ReadWriteLock`

这里还涉及到**「锁的降级」**，还有**「可重入」**等一些有意思的点~ ，埋个坑🕳  后面也会写到的😋

### 可重入锁 (递归锁)

什么是可重入呢~  😄

#### 概念：

> ❝
>
> 当一个线程持有某个锁时，**「可以再次获取该锁而不会导致死锁或者阻塞」**
>
> ❞

#### 特点：

**「获取 n 次 锁 ，也要释放 n 次锁」**

#### 实现方式：

`synchronized` ，`ReentrantLock`

### 分段锁

这个主要是 **「Jdk1.7」** 版本 的 `CurrentHashMap`   😄

#### 概念：

简单回忆下~

> ❝
>
> `CurrentHashMap`   中 的  `Segment` 数组 ，put 操作时会调用 `ReentrantLock`   的 lock 方法，锁住该 `Segment`
>
> ❞

#### 实现方式：

`synchronized` ，`ReentrantLock`

### 自旋锁

哈哈 看了上文之后是不是觉得这个也特眼熟呀~  😄

小伙伴们可以参看下 `CurrentHashMap`     中源码对这块的实现 ，如 `put` 源码

#### 概念：

> ❝
>
> 让线程不断地循环，去尝试获取锁
>
> ❞

#### 实现方式：

`CAS`

这里其实有很多可以扩展的，除了它的优缺点之外，还有  **「自适应自旋」**  这个和 **「虚拟机」** 相关的 ，埋个坑🕳 😋

### 死锁

**「情景模拟」**

线程 1 拥有 资源 A 的锁，线程 2 拥有 资源 B 的锁，但是线程 1 在持有 A 锁的情况下，还想拥有 B 锁。同理 线程 2 在持有 B 锁的情况下，还想拥有 A 锁。他们两就这样僵持着，互相等待对方释放锁🔒

#### 概念：

> ❝
>
> 死锁是指两个或两个以上的进程在执行过程中，由于竞争资源或者由于彼此通信而造成的一种阻塞的现象，若无外力作用，它们都将无法推进下去。此时称系统处于死锁状态或系统产生了死锁，这些永远在互相等待的进程称为死锁进程。
>
> ❞

### 锁升级

**「无锁 -> 偏向锁 -> 轻量级锁 -> 重量级锁」**

这里涉及到 **「锁优化技术」**  ，后面和 **「锁粗化」**，**「锁解除」** 等作为一个专题写写✍

![](https://mmbiz.qpic.cn/mmbiz_jpg/zIjiaGEEKdYEMtm8j5FMx1CYXNE6BRlUVTcibxlVRLBSdiaIADWM32J3fdEePDaiaJLd5wicnOdiasJh71qxBLsIGmyQ/640?wx_fmt=jpeg)img

👉 做了思维导图后发现这里有这么多专题得缕一缕的 o((>ω<))o

只能再加把劲啦！

下期见啦各位！**😝**

![](https://mmbiz.qpic.cn/mmbiz_gif/Ljib4So7yuWh4ovyZcbx0cI6TPupJGxbkmbELQnfQvMbZpicsQuVOoa7PvaUXCNFnKLsl0HFEBSa4WYOJFPcfXrA/640?wx_fmt=gif)

**谢谢可爱又帅气的大佬们的观看！祝您 天天开心！**😄

**感谢您的关注！您的每个关注，都是博主** **肝肝肝****的动力 😝**

![](https://mmbiz.qpic.cn/mmbiz_jpg/zIjiaGEEKdYF2db3KHM5ZAyibV3Idbmia6ZHOaDNaD0PDAIvfKVheD9AGw2OjAjYbr9MNibDZa0asM3HYUjG6qzRoQ/640?wx_fmt=jpeg)

点个 “在看” 表示朕

[

![](https://mmbiz.qpic.cn/mmbiz_jpg/zIjiaGEEKdYEHESZlrbz2fm9z1oQUenkNHeZiaJNAV6FxmibBpCejzlP7z3DgOudJ9pfjnb9iaFGZStlHeG3Sd3RAQ/640?wx_fmt=jpeg)

终于来到 ConcurrentHashMap 了~







](http://mp.weixin.qq.com/s?__biz=Mzg2MjUzODc5Mw==&mid=2247486450&idx=1&sn=0cb3394a90400dbb0c3542f097493e69&chksm=ce0716b4f9709fa2aabab63b2f535b28479dd219ea4b7d923a39e0889d50369f8c96ba0cd894&scene=21#wechat_redirect)



[

![](https://mmbiz.qpic.cn/mmbiz_jpg/zIjiaGEEKdYGbsKjF97ojmPOjahbybwsj8KY3fTy4q7LQ2u2FqJkIRwIpQl2n9KWcPI36ibF8ia1tf81whYXAcwdQ/640?wx_fmt=jpeg)

fail-safe 和 fail-fast 硬核解析，让你和面试官多聊十分钟！







](http://mp.weixin.qq.com/s?__biz=Mzg2MjUzODc5Mw==&mid=2247486318&idx=1&sn=d7769eb74261f9d5c8d2adc0b31a5ce0&chksm=ce071628f9709f3e334da1f96bc9469f615505c9ff907ccdbf00adb420cb80715b6f0fd28a09&scene=21#wechat_redirect)



[

![](https://mmbiz.qpic.cn/mmbiz_jpg/zIjiaGEEKdYEyEggwEW59icTrhaaur0gLAbwoM53RxJelyYv7dx8l73PSML5D7a0VFHuWRickibZKb6Egtjd5oI2mQ/640?wx_fmt=jpeg)

一文带你了解 TreeMap ，LinkedHashMap 的主要特点







](http://mp.weixin.qq.com/s?__biz=Mzg2MjUzODc5Mw==&mid=2247485984&idx=1&sn=dc3d8ee90fd2169d29015790773f01f6&chksm=ce071766f9709e7068e05338ff2fa51942f86488a214896bf765d13fe418cf776b4403ec6c8f&scene=21#wechat_redirect)



[

![](https://mmbiz.qpic.cn/mmbiz_jpg/zIjiaGEEKdYFviclNyvhERFbt4t69W1Zopp0LY1Nw6jTkuricyibtkFJ4mQMfPA40ywG3ectnAru0zEaST9gliaU7Og/640?wx_fmt=jpeg)

面试， HashMap，看？







](http://mp.weixin.qq.com/s?__biz=Mzg2MjUzODc5Mw==&mid=2247485816&idx=1&sn=835a92e16387bc65c560557ecab39604&chksm=ce07143ef9709d28b9c28c585fff4aa4621573b1375633e05a9e0b71455178b727fdc2c9413c&scene=21#wechat_redirect)