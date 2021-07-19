> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/ZVOiT-_C3f-g7aj3760Q-g)

数据的校验的重要性就不用说了，即使在前端对数据进行校验的情况下，我们还是要对传入后端的数据再进行一遍校验，避免用户绕过浏览器直接通过一些 HTTP 工具直接向后端请求一些违法数据。

最普通的做法就像下面这样。我们通过 `if/else` 语句对请求的每一个参数一一校验。

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBdWpEU5xpKjM5W9bMDXZ1zibE9Fw8bRLezceFbGuWyXcWFfQDXG3vAibg/640?wx_fmt=png)

这样的代码，小伙伴们在日常开发中一定不少见，很多开源项目都是这样对请求入参做校验的。

但是，不太建议这样来写，这样的代码明显违背了 **单一职责原则**。大量的非业务代码混杂在业务代码中，非常难以维护，还会导致业务层代码冗杂！

实际上，我们是可以通过一些简单的手段对上面的代码进行改进的！这也是本文主要要介绍的内容！

废话不多说！下面我会结合自己在项目中的实际使用经验，通过实例程序演示如何在 SpringBoot 程序中优雅地的进行参数验证 (普通的 Java 程序同样适用)。

不了解的朋友一定要好好看一下，学完马上就可以实践到项目上去。

并且，本文示例项目使用的是目前最新的 Spring Boot 版本 2.4.5!（截止到 2021-04-21）

示例项目源代码地址：https://github.com/CodingDocs/springboot-guide/tree/master/source-code/bean-validation-demo 。

添加相关依赖
------

如果开发普通 Java 程序的的话，你需要可能需要像下面这样依赖：

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBfG4I28icRuIYYeUBxGO3JSsWGs90PzOIjGibau8pw8U0Ju4RUBuwjUXQ/640?wx_fmt=png)

不过，相信大家都是使用的 Spring Boot 框架来做开发。

基于 Spring Boot 的话，就比较简单了，只需要给项目添加上 `spring-boot-starter-web` 依赖就够了，它的子依赖包含了我们所需要的东西。另外，我们的示例项目中还使用到了 Lombok。

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBk4ibNvLns5Jf8oseQ1uK03nQUpI5qkXPzjdSyiaqibkACHM5AROnU0HJw/640?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBFwWulYoVw7Q4TcTJylVj3PNOafibMZdM9u8OmUw3h4B1E344RD3RvTg/640?wx_fmt=png)

但是！！！Spring Boot 2.3 1 之后，`spring-boot-starter-validation` 已经不包括在了 `spring-boot-starter-web` 中，需要我们手动加上！

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZB4YtK32HPNvg1J0SmPWhQnKja588Zib2VkcoonaI9XVmhuib4gvia47q4A/640?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBmLmibTHz2XhcCFyZMv0O769TiaACUVWslTtNrTTKibFFAYwZ3CkicBaGJg/640?wx_fmt=png)


验证 Controller 的输入
--------------------

### 验证请求体

验证请求体即使验证被 `@RequestBody` 注解标记的方法参数。

**`PersonController`**

我们在需要验证的参数上加上了`@Valid`注解，如果验证失败，它将抛出`MethodArgumentNotValidException`。默认情况下，Spring 会将此异常转换为 HTTP Status 400（错误请求）。

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBanmicq3tGtIp3kLwRDnJD9ToanECpWRCU1yHyqssyUy1Lbeicfg32rEw/640?wx_fmt=png)

**`PersonRequest`**

我们使用校验注解对请求的参数进行校验！

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBceTdx0bt1mmOXFxWAtrfibUTQ4lMjs0jghJkT7la5vpExhCRrJGXibHg/640?wx_fmt=png)

正则表达式说明：

*   `^string` : 匹配以 string 开头的字符串

*   `string$` ：匹配以 string 结尾的字符串

*   `^string$` ：精确匹配 string 字符串

*   `(^Man$|^Woman$|^UGM$)` : 值只能在 Man,Woman,UGM 这三个值中选择


**`GlobalExceptionHandler`**

自定义异常处理器可以帮助我们捕获异常，并进行一些简单的处理。如果对于下面的处理异常的代码不太理解的话，可以查看这篇文章 [《SpringBoot 处理异常的几种常见姿势》](https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247485568&idx=2&sn=c5ba880fd0c5d82e39531fa42cb036ac&chksm=cea2474bf9d5ce5dcbc6a5f6580198fdce4bc92ef577579183a729cb5d1430e4994720d59b34&token=1924773784&lang=zh_CN&scene=21#wechat_redirect)。

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBMiccLfUZ81LVpFMO3KU0rYxLaJgoGGEArFdYh3lTEzBnrKxicORxcOEw/640?wx_fmt=png)

**通过测试验证**

下面我通过 `MockMvc` 模拟请求 `Controller` 的方式来验证是否生效。当然了，你也可以通过 `Postman` 这种工具来验证。

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZB4PnkUmpNfkoHXoQXKYvxs0PujCnf7NSqEkCTD5f6oUH9ic78Soz2ZLQ/640?wx_fmt=png)

**使用 `Postman` 验证**

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBbiaItdwkXxqwSnNgP5Z3bub6TQVrMyb2WWuUHNic14PJAZSm5JuicdFFw/640?wx_fmt=png)

### 验证请求参数

验证请求参数（Path Variables 和 Request Parameters）即是验证被 `@PathVariable` 以及 `@RequestParam` 标记的方法参数。

**`PersonController`**

**一定一定不要忘记在类上加上 `Validated` 注解了，这个参数可以告诉 Spring 去校验方法参数。**

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBbHPqwr41aVaCFBFLT2nlGictiaLGjnxEZprBAkwtRUlGDSc5M1p5ibVVg/640?wx_fmt=png)

**`ExceptionHandler`**

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBFKkGXM8CBK6BiczByWtR33ITtG0U2nBBljB3t3GtkY6TY5JVXCGjLJA/640?wx_fmt=png)

**通过测试验证**

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBvYjOJE4j9iawyUU0QnsoFOshteuG9bkoF4r4UfCyAPwKCBmVeOXBOcg/640?wx_fmt=png)

**使用 `Postman` 验证**

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBYFTLvxbiaricQCJOl5cu6LthHRwUataFy5277KAAE35V4gN9hR7eHMFQ/640?wx_fmt=png)![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBsLGaxQsKxgcFCDhTRXGzPjOY8nxHqb92ExZf8BRZjR2pO5ukJO6CEA/640?wx_fmt=png)

验证 Service 中的方法
---------------

我们还可以验证任何 Spring Bean 的输入，而不仅仅是 `Controller` 级别的输入。通过使用`@Validated`和`@Valid`注释的组合即可实现这一需求！

一般情况下，我们在项目中也更倾向于使用这种方案。

**一定一定不要忘记在类上加上 `Validated` 注解了，这个参数可以告诉 Spring 去校验方法参数。**

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBXELoSN8nq1lNptnFoK0sSbmblcJaHqzKDCRbIHT9r97EMmdnHriaG7A/640?wx_fmt=png)

**通过测试验证：**

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBIwFckFUI4efAzurdnRRicpK7VGm55TS09piaSPa9cFAj0ejDvQJbbAcA/640?wx_fmt=png)

输出结果如下：

```
name 不能为空
sex 值不在可选范围
```

Validator 编程方式手动进行参数验证
----------------------

某些场景下可能会需要我们手动校验并获得校验结果。

我们通过 `Validator` 工厂类获得的 `Validator` 示例。另外，如果是在 Spring Bean 中的话，还可以通过 `@Autowired` 直接注入的方式。

```
@Autowired
Validator validate
```

具体使用情况如下：

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZB9j8ibBLCv4Kk4ng8PNKNqgQFs06A52tuv7sibDgrDoUvKwKgy9tjL5og/640?wx_fmt=png)

输出结果如下：

```
sex 值不在可选范围
name 不能为空
```

自定以 Validator(实用)
-----------------

如果自带的校验注解无法满足你的需求的话，你还可以自定义实现注解。

### 案例一: 校验特定字段的值是否在可选范围

比如我们现在多了这样一个需求：`PersonRequest` 类多了一个 `Region` 字段，`Region` 字段只能是`China`、`China-Taiwan`、`China-HongKong`这三个中的一个。

**第一步，你需要创建一个注解 `Region`。**

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBVJkNDXW5XS9ibKzn3Pe50bCaB85ufNAehHbiaKTQyVEuLM5kibUE3ojWw/640?wx_fmt=png)

**第二步，你需要实现 `ConstraintValidator`接口，并重写`isValid` 方法。**

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZB5IOPr5ZvPriaqAf4OuohocMJSvoJwdklrh3BEc89UJSHlJichjElJPTg/640?wx_fmt=png)

现在你就可以使用这个注解：

```
@Region
private String region;
```

**通过测试验证**

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBYnkkoNLa74oR2SevnwybhHBjn0Cqic7cF1ib1E614khiae5xsJPydxVeQ/640?wx_fmt=png)

**使用 `Postman` 验证**

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBkyrYibesicC8SE2UA016UeAia8ICNib62kuXu3P5s6uiawLia08xFnpluvww/640?wx_fmt=png)

### 案例二: 校验电话号码

校验我们的电话号码是否合法，这个可以通过正则表达式来做，相关的正则表达式都可以在网上搜到，你甚至可以搜索到针对特定运营商电话号码段的正则表达式。

`PhoneNumber.java`

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZB242wPib70cyUiafoTTm8Or2rbNZpBsb5c0Sugic1PUbL4C2aiao4Gh8Kdg/640?wx_fmt=png)

`PhoneNumberValidator.java`

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBERzS3SX7VnJOd6U7pa0oY9jcYOQz72LSyC334KZbxmY75L4cMbUTJw/640?wx_fmt=png)

搞定，我们现在就可以使用这个注解了。

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZB3oatVLMzIdIiaxbsppKBghLWfxicMmvEGgBQbw1Zd2o7Iv6jNygv7D6g/640?wx_fmt=png)

**通过测试验证**

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBwGtPeQiaJu7bs8jmB9sPniaEGKPcdNDwCNwRqfAe7PNmzotvwQm4HWsQ/640?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBAAT4rOM9pp0Q567HibOaeYIg7ltfz1stibibsauJ9za6uKl0VsozN97tA/640?wx_fmt=png)

使用验证组
-----

验证组我们基本是不会用到的，也不太建议在项目中使用，理解起来比较麻烦，写起来也比较麻烦。简单了解即可！

当我们对对象操作的不同方法有不同的验证规则的时候才会用到验证组。

我写一个简单的例子，你们就能看明白了！

**1. 先创建两个接口，代表不同的验证组**

```
public interface AddPersonGroup {
}
public interface DeletePersonGroup {
}
```

**2. 使用验证组**

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBKPSa846XpLnNNqhZ5Aib93S2ZpeFBvt08me3RBWElbDESiaDmhvn6K3A/640?wx_fmt=png)

通过测试验证：

![](https://mmbiz.qpic.cn/mmbiz_png/iaIdQfEric9TzkMqcvCrEvmJAwVdeHvDZBrpOD44UWCkqlGd3KRzpOh2dacQybElF12HK1P2T3lV4icQFmAibze1lQ/640?wx_fmt=png)

验证组使用下来的体验就是有点反模式的感觉，让代码的可维护性变差了！尽量不要使用！

常用校验注解总结
--------

`JSR303` 定义了 `Bean Validation`（校验）的标准 `validation-api`，并没有提供实现。`Hibernate Validation`是对这个规范 / 规范的实现 `hibernate-validator`，并且增加了 `@Email`、`@Length`、`@Range` 等注解。`Spring Validation` 底层依赖的就是`Hibernate Validation`。

**JSR 提供的校验注解**:

*   `@Null` 被注释的元素必须为 null

*   `@NotNull` 被注释的元素必须不为 null

*   `@AssertTrue` 被注释的元素必须为 true

*   `@AssertFalse` 被注释的元素必须为 false

*   `@Min(value)` 被注释的元素必须是一个数字，其值必须大于等于指定的最小值

*   `@Max(value)` 被注释的元素必须是一个数字，其值必须小于等于指定的最大值

*   `@DecimalMin(value)` 被注释的元素必须是一个数字，其值必须大于等于指定的最小值

*   `@DecimalMax(value)` 被注释的元素必须是一个数字，其值必须小于等于指定的最大值

*   `@Size(max=, min=)` 被注释的元素的大小必须在指定的范围内

*   `@Digits (integer, fraction)` 被注释的元素必须是一个数字，其值必须在可接受的范围内

*   `@Past` 被注释的元素必须是一个过去的日期

*   `@Future` 被注释的元素必须是一个将来的日期

*   `@Pattern(regex=,flag=)` 被注释的元素必须符合指定的正则表达式


**Hibernate Validator 提供的校验注解**：

*   `@NotBlank(message =)` 验证字符串非 null，且长度必须大于 0

*   `@Email` 被注释的元素必须是电子邮箱地址

*   `@Length(min=,max=)` 被注释的字符串的大小必须在指定的范围内

*   `@NotEmpty` 被注释的字符串的必须非空

*   `@Range(min=,max=,message=)` 被注释的元素必须在合适的范围内


拓展
--

经常有小伙伴问到：“`@NotNull` 和 `@Column(nullable = false)` 两者有什么区别？”

我这里简单回答一下：

*   `@NotNull`是 JSR 303 Bean 验证批注, 它与数据库约束本身无关。

*   `@Column(nullable = false)` : 是 JPA 声明列为非空的方法。


总结来说就是即前者用于验证，而后者则用于指示数据库创建表的时候对表的约束。

欢迎准备面试的朋友加入我的星球，[一个纯 Java 面试交流圈子 ！Ready！](https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247500276&idx=2&sn=1932060f99ed7632d3d84dc233306273&scene=21#wechat_redirect)。目前星球已经更新 **3** 个原创小册：**《Java 面试进阶指北》**、**《从零开始写一个 RPC 框架》** 、**《程序员副业赚钱之路》**。累计帮助 **520+** 位球友提供了免费的简历修改服务，回答了 **500+** 个问题，产出了 **1300+** 个主题。

![](https://mmbiz.qpic.cn/mmbiz_jpg/iaIdQfEric9Twg557FqD5dXwpWGcQK8YLAukcAmmgZ35JTbcftxAYap6BEqp5YKe29dkcBFnTBrV3bLlPJeldLtA/640?wx_fmt=jpeg)

**推荐👍** ：[1049 天，100K! 简单复盘！](http://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247501206&idx=1&sn=b04d30f26380602a2b9029ba91169e64&chksm=cea18a5df9d6034be43554ced33a8da4aad9a0037cc955093885bffb85e96d05ea8491b06a75&scene=21#wechat_redirect)

**推荐👍** ：[年薪 40W Java 开发是什么水平？](https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247502604&idx=1&sn=c59eff2b4f95bccc00bdffd46335a15e&chksm=cea184c7f9d60dd18fbd4b44ea9e67d65f2a1c1aaa93d8ebf6d72a123c5c3dda56c4c2df6fce&token=1347013132&lang=zh_CN&scene=21#wechat_redirect)

**推荐👍** ：[Github 掘金计划：Github 上的一些优质项目搜罗](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzIwNDgzMzI3Mg==&action=getalbum&album_id=1571213952619954180&token=2007747701&lang=zh_CN#wechat_redirect&__biz=MzIwNDgzMzI3Mg==#wechat_redirect)

我是 Guide 哥，拥抱开源，喜欢烹饪。Github 接近 10w 点赞的开源项目 JavaGuide 的作者。未来几年，希望持续完善 JavaGuide，争取能够帮助更多学习 Java 的小伙伴！共勉！凎！**[点击查看我的 2020 年工作汇报！](https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247500763&idx=1&sn=8f5bd15a82c45e8dc9216eb57a7f3652&scene=21&token=2072948470&lang=zh_CN#wechat_redirect)**

原创不易，欢迎点赞分享。咱们下期再会!