> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/EfnnZYSxEvyWKRd3D4bq6w)

关注 “Java 后端技术全栈”

回复 “面试” 获取全套面试资料

从 2018 年开始，我的简历上开始有一句很 diao 的话：

> ❝
>
> 研究过 Mybatis 源码
>
> ❞

然后，每次面试都会被问到 Mybatis 里的设计模式。

面试官问：既然你研究过 Mybatis 源码，哪里说说 Mybatis 用了哪些常见的设计模式？

我基本上都是把相关设计模式先回答一遍。

我：单量模式、代理模式、工厂模式、装饰器模式..... 劈哩吧啦的说上一堆设计模式。

面试官：能不能说说装饰器模式在 Mybatis 中的什么场景中会用到？

我一般是先说什么是装饰器模式，有什么好处，Mybatis 中哪里用到了，这样用什么好处。

接下来，面试官一般都是抓住其中两三个问。

也为了防止老铁们被问得更多，今天我就整理一番。

### 建造者设计模式

建造者模式（Builder Pattern）使用多个简单的对象一步一步构建成一个复杂的对象。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。

在 Mybatis 中有 SqlSessionFactoryBuilder，构建 SqlSessionFactory， 这就是使用了建造者模式。

另外在 Mybatis 中类名以 Builder 结尾基本上都是建造者模式。下面是 Mybatis 中一个很完整的建造者模式：

![](https://mmbiz.qpic.cn/mmbiz_png/07BicZywOVtmT1oUggg8WjzIZuADHZYd81ljYuCMY9Xys4dEE9Tk2CgFGDW4hJGI5vh8d0wOrmMA75DChQZMexQ/640?wx_fmt=png)

`XMLConfigBuilder` ：XML 配置构建器，建造者模式，继承 BaseBuilder 。

### 工厂模式

就是专门创建某某对象的工厂，你要什么对象，尽管开口，能创建的我来创建，你无需知道是怎么创建出来的。

在`Mybatis`中以 Factory 结尾的类，基本上都是使用了工厂模式。

生活中案例：很多外包公司，做银行系统，银行只要把需求给他，给我做个什么什么系统，外包公司拼了命的叫老铁们加班，最后赶出来了。外包公司就是工厂，银行就是客户端。客户端不管你是怎么搞出来的，外包公司也不给银行说。

比如说：

`SqlSessionFactory`：创建 SqlSession 对象。

`ObjectFactory`：对象工厂，所有对象都要由工厂来产生 。

`MapperProxyFactory`：创建映射器代理 MapperProxy 对象。

### 单例模式

单例模式（Singleton Pattern）是 Java 中最简单的设计模式之一。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。这种模式涉及到一个单一的类，该类负责创建自己的对象，同时确保只有单个对象被创建。这个类提供了一种访问其唯一的对象的方式，可以直接访问，不需要实例化该类的对象。

生活中案例：习大大就只能有一个，太阳只有一个，月亮只有一个。

`org.apache.ibatis.logging.LogFactory`，日志工厂类。

为什么是单例模式呢？情况下面代码：

![](https://mmbiz.qpic.cn/mmbiz_png/07BicZywOVtmT1oUggg8WjzIZuADHZYd8e1GVYMDAicJlzzHhAa7XKHKedLVOXEZINiccf39waXF0Fy3UMibZBnIJA/640?wx_fmt=png)

`org.apache.ibatis.executor.ErrorContext`,

![](https://mmbiz.qpic.cn/mmbiz_png/07BicZywOVtmT1oUggg8WjzIZuADHZYd82PKAT4t7VAibFg2bTnc9NicpyYGknzUHRO4kqP2bDKuXjLlgZPgOXRKg/640?wx_fmt=png)



### 代理模式

在代理模式（Proxy Pattern）中，一个类代表另一个类的功能。这种类型的设计模式属于结构型模式。

生活中的案例：房产中介、婚介所、黄牛党等都是代理模式。

Mybatis 实现的核心，比如 MapperProxy 为绑定我们开发的 Mapper 和 Mapper.xml 创建代理类、Plugin 为每个插件创建一个代理类等。

Mybatis 中尤其是动态代理使用的是相当的多，建议大家，先学习代理模式，然后在学习动态代理（JDK 和 CGlib 这两种），如果想看 Mybatis 源码，动态代理是必须掌握的。

### 适配器模式

适配器模式（Adapter Pattern）是作为两个不兼容的接口之间的桥梁。这种类型的设计模式属于结构型模式，它结合了两个独立接口的功能。这种模式涉及到一个单一的类，该类负责加入独立的或不兼容的接口功能。

在 Mybatis 中，Log，对于 Log4j、JDK、longging 这些没有直接是想 slf4j 接口的日志组件，需要适配器。

### 模板方法模式

在模板模式（Template Pattern）中，一个抽象类公开定义了执行它的方法的方式 / 模板。它的子类可以按需要重写方法实现，但调用将以抽象类中定义的方式进行。这种类型的设计模式属于行为型模式。定义一个操作中的算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。

在 Mybatis 中，例如父类 BaseExecutor，子类 SimpleExecutor、BatchExecutor、ReuseExecutor。还有 BaseTypeHandler 和所有的子类例如 IntegerTypeHandler；

基本都是在父类里实现一个通用的方法，然后创建一个抽象方法，这个抽象方法留给子类自己去实现。这个抽象方法也叫钩子方法。

### 装饰器模式

装饰器模式（Decorator Pattern）允许向一个现有的对象添加新的功能，同时又不改变其结构。这种类型的设计模式属于结构型模式，它是作为现有的类的一个包装。这种模式创建了一个装饰类，用来包装原有的类，并在保持类方法签名完整性的前提下，提供了额外的功能。

实际开发中，大多数用于对老项目的某些功能进行扩展。新项目中一般不怎么用此模式。

生活中的案例：人靠衣裳马靠鞍。美容照相机、没有摄影机，美图秀秀。

此设计模式重点在于对已有的功能进行扩展。

在 Mybatis 中，Cache 的实现类 LruCache、FifoCache 等都是装饰一个类 PerpetualCache。常见代码格式，就是装饰类中会有个被装饰类的属性，并且这个属性还是构造方法的参数。

### 责任链模式

任链模式（Chain of Responsibility Pattern）为请求创建了一个接收者对象的链。这种模式给予请求的类型，对请求的发送者和接收者进行解耦。这种类型的设计模式属于行为型模式。在这种模式中，通常每个接收者都包含对另一个接收者的引用。如果一个对象不能处理该请求，那么它会把相同的请求传给下一个接收者，依此类推。

生活中的案例：

> ❝
>
> 我们在 OA 系统发起一个审批，显示项目经理，再是部门经理，再是 HR，再是老板。
>
> 面试流程，显示小组长面试里，项目经理面试，部门经理面试，HR 面试。
>
> ❞

在 Mybatis 中，InterceptorChain 中有个属性 interceptors，其中就是保存了所有 Mybatis 的插件，执行插件的时候就是遍历这个 interceptors。A 插件 ->B 插件 ->C 插件....

### 总结

上面一共说了 8 种设计模式。其实在 Mybatis 中还有更多的设计模式，比如说组合模式、迭代器模式 等。

对于文中的 8 种设计模式，我建议一个优先级，由高往低：

单例 -> 工厂 -> 模板方法 -> 代理 -> 装饰器 -> 责任链 -> 适配器 -> 建造者。

前面五个个人强烈推荐掌握。

推荐阅读

[答了 Mybatis 这个问题后，面试官叫我回去等通知……](http://mp.weixin.qq.com/s?__biz=MzU4MDM3MDgyMA==&mid=2247495102&idx=1&sn=069ba107449a0d76ace57b53fab03a12&chksm=fd554555ca22cc432b92eb13e6efd7fce664f6a723fc835850f5aa8538bd19ffb368f297f384&scene=21#wechat_redirect)

[《构建高性能 WEB 站点》.pdf](http://mp.weixin.qq.com/s?__biz=MzU4MDM3MDgyMA==&mid=2247495082&idx=2&sn=5cac66b86521086aa29811776341db4c&chksm=fd554541ca22cc57e60cb4d3da235154ec9bfd4233bd74d2df732003ae4a15715c2785eaded8&scene=21#wechat_redirect)

[《算法导论》.pdf](http://mp.weixin.qq.com/s?__biz=MzU4MDM3MDgyMA==&mid=2247494956&idx=2&sn=ae5a3a565403c347cda348f17bc7c1a7&chksm=fd5545c7ca22ccd1b7c5ce420c750fd59e04af5eb832cd89e2e717f072be5660b73182cbdc29&scene=21#wechat_redirect)

![](https://mmbiz.qpic.cn/mmbiz_png/07BicZywOVtkk5nKM1hOaR0iaXxGH7ibukPJORjopTX8lJ5ElpvrXPmG6J5Fe8eeYBorSibRJym4icyOWuppytx1c9A/640?wx_fmt=png)