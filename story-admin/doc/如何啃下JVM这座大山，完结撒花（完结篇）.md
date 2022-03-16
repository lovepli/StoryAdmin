> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/FQhMAeMxSKUGah6onDpXww)

前言
--

小伙伴们大家好，我是黎杜，这是关于 JVM 的第六篇文章，前面写了五篇关于 JVM 的文章，都是一层一层带着大家来深入的认识 JVM，关于 JVM 的基本用到的知识都讲解过了。

那么这一篇文章将作为 JVM 的最后一篇，有总结，也有补充还没有提及到的知识点。

首先，关于 JVM 的开门篇，关于如何阅读深入 JVM 虚拟机第三版的文章：[如何三天啃下《深入 JVM 虚拟机第三版》](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247488849&idx=1&sn=e19874cffb6f62002bae055272b72807&scene=21#wechat_redirect)，主要是分享个人阅读这本神书的经验，在文章里面划重点，吸收到我们自己所需要的知识点。

然后，是很久之前写的第二篇关于 JVM 的运行时数据区以及 GC 的算法篇：[还在学 JVM？我都帮你总结好了（附脑图）](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483948&idx=1&sn=ee9b3ad4dd1dadd174984d1cedb5760a&scene=21#wechat_redirect)。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaW0hxCFRzekroOErJHvkmqaU5LVBD8LKKEibr2hCQBmdXe9icliad0R7v96bFicAibsZnJeI8GLZ1P7jg/640?wx_fmt=png)

这篇是是作为理论的重点部分，因为这片关于 **JVM 的运行时数据区的各个部分是干嘛的，以及重点 JVM 堆的分代理论和 GC 的基本回收算法**，都是为后面的 Java 堆的调优实战做铺垫。

然后是第三篇也是调优的重中之重，主要聊的就是 GC：[你是不是垃圾，心里没点数吗？](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247488866&idx=1&sn=fcd7ed1114eba05c12363500fbc9f193&scene=21#wechat_redirect)，**如何判断一个对象是否存活、以及常见的 GC 的种类，常见 GC 年轻代和老年代的搭配，各种 GC 的原理和特点、以及适用的场景**，在文章中都有提及到。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaW0hxCFRzekroOErJHvkmqumnicibwPRhB0LVVw0JGOAEpfnnTxiaXKzTIW1ksqibicibOPQa9BWDwg2eg/640?wx_fmt=png)

有了第二篇运行时数据区和第三篇 GC 的理论基础后，然后第四篇的 JVM 调优实战篇一：[JVM 调优实战篇一](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247488916&idx=1&sn=17d07be2bb6c8cf6c8c5a162f37cba62&scene=21#wechat_redirect)，这一篇主要是围绕着 **JVM 调优实战讲解的工具篇，包括线上的 Arthas 工具、GUI 工具（Visual VM）、内存分析工具（mat）以及 linux 的原始命令 jps、jstack、jstat、jmap、jhat 等命令的讲解和使用**。

熟悉了 JVM 调优的工具后，第五篇就是就是最后**实战调优的场景、案例、经典排查 OOM、磁盘不足排查、CPU 飙高的排查、死锁的排查、调优的目的和理论已经调优案例场景解析**：[JVM 性能调优实战篇（二）](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247488995&idx=1&sn=c0343aad85c27439562b335c9b5d7872&scene=21#wechat_redirect)

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaW0hxCFRzekroOErJHvkmqsgA6mTbhgic9egod4kELhPe9YbCO2ibwxyicAxhhquicZBgd44jWicroc1Q/640?wx_fmt=png)

其中，第五篇也是写了我最久的一篇，也是干货非常多的一篇，也是目的性最强的一篇，学习 JVM 就是为了调优，同时也收获不少的新读者的关注。

所以，按照上面帮你们排好的顺序，一篇一篇的往下读，应该对你的调优的技术和理论的深入认识应该是有所帮助的。

然后，自己有时间的话，也可以自己深入的啃啃《深入 JVM 虚拟机第三版》，关于 JVM 的其他书籍，我推荐：**《Java 虚拟机规范》、《垃圾回收算法手册：自动内存管理的艺术》、《Virtual Machines: Versatile Platforms for System and Processes》** 这几本好书，有时间和能力的都可以去看一看。

最后，这是最后一篇也是第六篇，对于 JVM 虚拟机我们所需要的部分，还有一块就是**类加载子系统**还没有讲解，所以为了文章的完整性，这一补充 JVM 的类加载子系统，这一篇还是主要是理论。

类加载子系统概述
--------

首先，我们来聊一聊 JVM 的类加载子系统，我们知道我们的代码敲完后是. java 类，然后经过编译就会变成. class 类型的字节码文件。

这些. class 类型的字节码文件经过类加载系统加载到 JVM 的内存中来供我们使用，这些文件我们也成为了元数据。

下面我画了一张图来大概一个类被类加载系统加载的过程，供大家理解：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaW0hxCFRzekroOErJHvkmqKrkA5JOyibd7x5C1CZtMswz56iaU90r72FJBjTpkg0pkQ5sVBj4IrhRg/640?wx_fmt=png)

就这样一个 java 类经过上面层层的过程来到了我们的 JVM 的虚拟机中，首先在加载过程的. class 文件可以来源终于下面几个方面：

*   **从系统文件中获取，比如从我们本地编译好的 class。**

*   **从网络中获取**

*   **或者运行时计算出来的，比如使用动态代理技术，运行时生成。**

*   **或者由其他的文件生成，比如 jsp 生成对应的 class 文件。**


当由我们的类加载子系统完成了类加载后，**这部分信息（包括类信息、常量、静态变量、方法信息等）就会存在方法区的内存中（jdk 7 以及以前，jdk 8 及以上移动到元空间，本地内存中）**，然后由 JVM 的执行引擎来执行。

在这个过程类加载的过程就好像扮演着中间人的角色，目的为的是 JVM 的执行引擎能够执行这些类：**.class -> JVM -> 元数据模板 -> 实例对象**。

那么，JVM 在加载类的时候，这个过程的主角类加载器，又是怎么工作的呢？下面我们来聊一聊类加载器。

类加载器
----

在 JVM 中经典的类加载器分为如下三层：

1.  **启动类加载器（BootStrap ClassLoader）：该加载器是由 C++ 实现，加载 <JAVA_HOME>\lib 下的文件，这类加载起不会被 Java 程序所直接使用，该类加载器一般加载包名为 java、javax、sun 等开头的类**。

2.  **扩展类加载器（Extension ClassLoader）：扩展类加载器是加载 <JAVA_HOME>\lib\ext 目录下的资源，它可以用来扩展 Java SE 的功能，如果用户创建的 JAR 包放在此目录下，就会被扩展类加载器加载**。

3.  **应用类加载器（Application ClassLoader）：它负责加载用户类路径（ClassPath）上的所有类库，开发者同样可以直接在代码中使用这个类加载器。**




![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaW0hxCFRzekroOErJHvkmqsHMfcvOHv65MDUhqbHYnDV7Ea9VXJMClKuicw60tkb7bBMUcIB4kAZw/640?wx_fmt=png)

除了上面的经典三层还有一个就是**用户自定义类加载器（User ClassLoader）**，它可以在程序中加载自己需要的类，所以完整的 JVM 类加载器如下图所示：

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaW0hxCFRzekroOErJHvkmqMHic9DVgFzd1fvUX92LY82RjpxMkIdibv5JVcF4FGIsiaB41W5E2gZjjg/640?wx_fmt=png)

他们的关系并不是继承的关系，而是通常以**组合**的关系来复用父类加载器的代码。

类加载过程
-----

我们了解完各种类加载器后，接下来详细的了解类加载的过程，一个完整的类加载过程主要包括一下几个阶段：**加载 -> 验证 -> 准备 -> 解析 -> 初始化**。

![](https://mmbiz.qpic.cn/mmbiz_png/IJUXwBNpKliaW0hxCFRzekroOErJHvkmqIHcKicicUgicNic33IpOQlV58zN0Rp9S1cd1ayLEVc3gyf7MJniawkhxIlg/640?wx_fmt=png)

加载阶段是**通过类的全类名，然后通过类加载器将 class 文件的二进制字节流转化为运行时数据区的方法区中。**

并且，会在内存中生成一个 java.lang.Class 对象，作为这个类的各种数据的访问入口。

验证阶段既是**验证字节码 class 文件中的字节码是否服务规范，保证里面的字节码不会对 JVM 自身造成伤害。**

准备阶段既是**为类中的类变量（static 修饰的变量，但没有被 final 修饰一起修饰）分配内存以及初始化类变量的零值，这里并不包括实例变量，实例变量是在对象一起分配在 Java 堆中。**

所谓的零值也就是数据的默认初始值，比如 int 为 0，boolean 默认为 false，float 默认为 0.0f，引用类型的默认为 null。

**解析阶段的作用是将虚拟机内的常量池的符号引用替换为直接引用的过程**。所谓的符号引用就是可以是任何形式的字面量，只要能够定位到目标即可；而直接引用可以是指向目标的指针、相对偏移量间接定位到目标的句柄。

最后是初始化，初始化是类加载的最后一个阶段，也是在这个阶段，Java 虚拟机才真正开始执行类中的 Java 程序代码。

上面说到在准备阶段变量已经初始化一次零值了，那么在这一阶段才会将变量初始化为程序代码中主观设置的值。

对于这一部分，我之前也写过一篇详细的，所以这里做一个大概的介绍，详细的可以参考这一篇文章：[面试官：你知道 java 类是怎么跑起来的吗？问的我一脸懵](https://mp.weixin.qq.com/s?__biz=MzU1MzE4OTU0OQ==&mid=2247483972&idx=1&sn=5e6b01caa6aa8fe7111924dc850e865e&scene=21#wechat_redirect)

双亲委派原则
------

双亲委派原则是各种加载器之前的一种工作方式，它目的是为了实现更加高效的进行类的加载。

当一个类加载器收到加载类的请求时，不会自己去尝试先加载该类，而是把该加载请求委托给父类，若是没有父类就是直接找顶层类加载器，若是有父类，并且父类还有父类加载器，依次向上委托，直到上面的所有父类都无法完成加载是，才会自己去加载，若是加载失败就会抛出异常。

它的好处就是加载一个类时，不用重复加载，当父类已经加载了，就不用加载第二年份，保证内存中只有一份。

我们来看看双亲委派实现的源代码，它的源代码主要实现是 **java.lang.ClassLoader 的 loadClass()** 方法中：

```
protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
 // 检查该类是否已经被加载过了
 Class c = findLoadedClass(name);
 // c为null没有被加载过，则使用双亲委派原则进行类加载
 if (c == null) {
  try {
      // 存在父类加载器
   if (parent != null) {
    c = parent.loadClass(name, false);
   } else {
       // 不存在服务加载器，则直接使用顶层类加载器进行加载
    c = findBootstrapClassOrNull(name);
   }
  } catch (ClassNotFoundException e) {
   // 父类加载抛出异常，说明父类无法完成加载
  }
  if (c == null) {
   // 父类无法完成加载，则尝试自己去完成加载动作
   c = findClass(name);
  }
 }
 if (resolve) {
  resolveClass(c);
 }
 return c;
}
```

好了，这一篇文章是的主要内容也讲完了，文字比较短，没有像以前那样基本都是万字，因为主要的内容都已经讲完了，这一篇还是比较简单的，更加倾向于对以前写的 JVM 的文章的总结。

接下来的文章开始数据库的连载，主要个人看的书籍是 **《Mysql 45 讲》、《MySQL 技术内幕  InnoDB 存储引擎  第 2 版》、《高性能 mysql 第三版》**，好了这一期就到这里，我们下一期间。

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