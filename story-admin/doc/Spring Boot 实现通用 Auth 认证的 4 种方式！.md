> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s?__biz=MzAxMjU4NzczOA==&mid=2247503209&idx=2&sn=61178aee8a54ed791376938c17b4b19b&chksm=9bad0adcacda83caea1b17e411294a1e9449c83dbbdef9fbc84714f959dfd2e4b19f6411dedf&scene=132#wechat_redirect)

关注 “Java 这点事”，选择 “设为星标”

每晚 10 点与你分享 Java 技术、IT 资讯

公众号

文章介绍了 spring-boot 中实现通用 auth 的四种方式，包括 传统 AOP、拦截器、参数解析器和过滤器，并提供了对应的实例代码，最后简单总结了下他们的执行顺序。

前言
--

最近一直被无尽的业务需求淹没，没时间喘息，终于接到一个能让我突破代码舒适区的活儿，解决它的过程非常曲折，一度让我怀疑人生，不过收获也很大，代码方面不明显，但感觉自己抹掉了 java、Tomcat、Spring 一直挡在我眼前的一层纱。对它们的理解上了一个新的层次。

好久没输出了，于是挑一个方面总结一下，希望在梳理过程中再了解一些其他的东西。由于 Java 繁荣的生态，下面每一个模块都有大量的文章专门讲述。所以我选了另外一个角度，从实际问题出发，将这些分散的知识串联起来，各位可以作为一个综述来看。各个模块的极致详细介绍，大家可以去翻官方文档或看网络上的其他博客。

需求很简单清晰，跟产品们提的妖艳需求一点也不一样：在我们的 web 框架里添加一个`通用`的 appkey 白名单校验功能，希望它的扩展性更好一些。

这个 web 框架是部门前驱者基于 spring-boot 实现的，介于业务和 Spring 框架之间，做一些偏向于业务的通用性功能，如 日志输出、功能开关、通用参数解析等。平常是对业务透明的，最近一直忙于把需求做好，代码写好，甚至从没注意过它的存在。

传统 AOP
------

对于这种需求，首先想到的当然是 Spring-boot 提供的 AOP 接口，只需要在 Controller 方法前添加切点，然后再对切点进行处理即可。

### 实现

其使用步骤如下：

1.  使用 `@Aspect` 声明一下切面类 `WhitelistAspect`；

2.  在切面类内添加一个切点 `whitelistPointcut()`，为了实现此切点灵活可装配的能力，这里不使用 `execution` 全部拦截，而是添加一个注解 `@Whitelist`，被注解的方法才会校验白名单。

3.  在切面类中使用 spring 的 AOP 注解 `@Before` 声明一个通知方法 `checkWhitelist()` 在 Controller 方法被执行之前校验白名单。


切面类伪代码如下

```
@Aspect
public class WhitelistAspect {
   
 @Before(value = "whitelistPointcut() && @annotation(whitelist)")
 public void checkAppkeyWhitelist(JoinPoint joinPoint, Whitelist whitelist) {
     checkWhitelist();
     // 可使用 joinPoint.getArgs() 获取Controller方法的参数
     // 可以使用 whitelist 变量获取注解参数
 }
   
   
 @Pointcut("@annotation(com.zhenbianshu.Whitelist)")
 public void whitelistPointCut() {
 }
}
```

在 Controller 方法上添加 `@Whitelist` 注解实现功能。

### 扩展

本例中使用了 注解 来声明切点，并且我实现了通过注解参数来声明要校验的白名单，如果之后还需要添加其他白名单的话，如通过 UID 来校验，则可以为此注解添加 `uid()` 等方法，实现自定义校验。

此外，spring 的 AOP 还支持 `execution（执行方法） 、bean（匹配特定名称的 Bean 对象的执行方法）`等切点声明方法和 `@Around（在目标函数执行中执行） 、@After（方法执行后）` 等通知方法。

如此，功能已经实现了，但领导并不满意 =_=，原因是项目中 AOP 用得太多了，都用滥了，建议我换一种方式。嗯，只好搞起。

Interceptor
-----------

Spring 的 拦截器 (Interceptor) 实现这个功能也非常合适。顾名思义，拦截器用于在 Controller 内 Action 被执行前通过一些参数判断是否要执行此方法，要实现一个拦截器，可以实现 Spring 的 `HandlerInterceptor` 接口。

### 实现

实现步骤如下：

1.  定义拦截器类 `AppkeyInterceptor` 类并实现 HandlerInterceptor 接口。

2.  实现其 `preHandle()` 方法；

3.  在 preHandle 方法内通过注解和参数判断是否需要拦截请求，拦截请求时接口返回 `false`；

4.  在自定义的 `WebMvcConfigurerAdapter` 类内注册此拦截器；


`AppkeyInterceptor` 类如下：

```
@Component
public class WhitelistInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Whitelist whitelist = ((HandlerMethod) handler).getMethodAnnotation(Whitelist.class);
        // whitelist.values(); 通过 request 获取请求参数，通过 whitelist 变量获取注解参数
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
  // 方法在Controller方法执行结束后执行
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
  // 在view视图渲染完成后执行
    }
}
```

### 扩展

要启用 拦截器还要显式配置它启用，这里我们使用 `WebMvcConfigurerAdapter` 对它进行配置。需要注意，继承它的的 `MvcConfiguration` 需要在 ComponentScan 路径下。

```
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WhitelistInterceptor()).addPathPatterns("/*").order(1);
        // 这里可以配置拦截器启用的 path 的顺序，在有多个拦截器存在时，任一拦截器返回 false 都会使后续的请求方法不再执行
    }
}
```

还需要注意，拦截器执行成功后响应码为 `200`，但响应数据为空。

当使用拦截器实现功能后，领导终于祭出大招了：我们已经有一个 Auth 参数了，appkey 可以从 Auth 参数里取到，可以把在不在白名单作为 Auth 的一种方式，为什么不在 Auth 时校验？emmm… 吐血中。

ArgumentResolver
----------------

参数解析器是 Spring 提供的用于解析自定义参数的工具，我们常用的 `@RequestParam` 注解就有它的影子，使用它，我们可以将参数在进入 Controller Action 之前就组合成我们想要的样子。

Spring 会维护一个 `ResolverList`， 在请求到达时，Spring 发现有自定义类型参数（非基本类型）， 会依次尝试这些 Resolver，直到有一个 Resolver 能解析需要的参数。要实现一个参数解析器，需要实现 `HandlerMethodArgumentResolver` 接口。

### 实现

1.  定义自定义参数类型 `AuthParam`，类内有 appkey 相关字段；

2.  定义 `AuthParamResolver` 并实现 HandlerMethodArgumentResolver 接口；

3.  实现 `supportsParameter()` 接口方法将 AuthParam 与 AuthParamResolver 适配起来；

4.  实现 `resolveArgument()` 接口方法解析 reqest 对象生成 AuthParam 对象，并在此校验 AuthParam ，确认 appkey 是否在白名单内；

5.  在 Controller Action 方法上签名内添加 AuthParam 参数以启用此 Resolver;


实现的 AuthParamResolver 类如下：

```
@Component
public class AuthParamResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(AuthParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Whitelist whitelist = parameter.getMethodAnnotation(Whitelist.class);
        // 通过 webRequest 和 whitelist 校验白名单
        return new AuthParam();
    }
}
```

### 扩展

当然，使用参数解析器也需要单独配置，我们同样在 `WebMvcConfigurerAdapter`内配置：

```
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuthParamResolver());
    }
}
```

这次实现完了，我还有些不放心，于是在网上查找是否还有其他方式可以实现此功能，发现常见的还有 `Filter`。

Filter
------

Filter 并不是 Spring 提供的，它是在 Servlet 规范中定义的，是 Servlet 容器支持的。被 Filter 过滤的请求，不会派发到 Spring 容器中。它的实现也比较简单，实现 `javax.servlet.Filter`接口即可。

由于不在 Spring 容器中，Filter 获取不到 Spring 容器的资源，只能使用原生 Java 的 ServletRequest 和 ServletResponse 来获取请求参数。

另外，在一个 Filter 中要显示调用 FilterChain 的 doFilter 方法，不然认为请求被拦截。实现类似：

```
public class WhitelistFilter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
  // 初始化后被调用一次
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
     // 判断是否需要拦截
       chain.doFilter(request, response); // 请求通过要显示调用
    }

    @Override
    public void destroy() {
     // 被销毁时调用一次
    }
}
```

### 扩展

Filter 也需要显示配置：

```
@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new WhitelistFilter());
        registration.addUrlPatterns("/*");
        registration.setName("whitelistFilter");
        registration.setOrder(1); // 设置过滤器被调用的顺序
        return registration;
    }
}
```

小结
--

四种实现方式都有其适合的场景，那么它们之间的调用顺序如何呢？

Filter 是 Servlet 实现的，自然是最先被调用，后续被调用的是 Interceptor 被拦截了自然不需要后续再进行处理，然后是 参数解析器，最后才是 切面的切点。

我将四种方式在一个项目内全部实现后，输出日志也证明了这个结论。

- END -

_来源__：https://zhenbianshu.github.io/_

![](https://mmbiz.qpic.cn/mmbiz_gif/2LlmEpiamhyq3R85gFyVRicftq36Wxsmic0R0O2nDlh0HMewdDiciavLfuUx3FU6icHeJpS9ZDBrj7UfAVDuNibqZvXDg/640?wx_fmt=gif)

●[分布式定时任务框架选型，写得太好了！](http://mp.weixin.qq.com/s?__biz=MzAxMjU4NzczOA==&mid=2247502749&idx=2&sn=f158a6ec98dcb91454543939b149afd5&chksm=9bad0c28acda853e0380273551678b340fc5c18d6f8e983f73451187f0c1b589025676c447c5&scene=21#wechat_redirect)

●[2021 年技术 5 大趋势](http://mp.weixin.qq.com/s?__biz=MzAxMjU4NzczOA==&mid=2247502591&idx=2&sn=63403d7443d37807c7c083bc87ee147a&chksm=9bad0d4aacda845ca1df2cd9727d4ac4e89ad7a78d599db944925c18195e42bc89d7b44a06dd&scene=21#wechat_redirect)

●[HttpClient 设置不当引发的一次雪崩！](http://mp.weixin.qq.com/s?__biz=MzAxMjU4NzczOA==&mid=2247502496&idx=2&sn=47ef45e959eefe9102a005115e3bcb91&chksm=9bad0d15acda8403ddb1c56c8b7c5d4a795c55bccda7f8268576f479a36650e871010611597e&scene=21#wechat_redirect)

●[放弃 StringBuilder！Java8 的 StringJoiner，真香！](http://mp.weixin.qq.com/s?__biz=MzAxMjU4NzczOA==&mid=2247502466&idx=2&sn=53cb37edf67fc89ae1dbd77f59f00050&chksm=9bad0d37acda8421c4f78a52833a5732cfab1f690d12240f284a3feec46cd57f7dbe9ad4bb61&scene=21#wechat_redirect)

●[20 个实例玩转 Java 8 Stream](http://mp.weixin.qq.com/s?__biz=MzAxMjU4NzczOA==&mid=2247502444&idx=2&sn=28e4406ea04d68051b32454f23442ba8&chksm=9bad0dd9acda84cfa0d4bd5166717b20309cf728381d085dd461dc2762debf3d817b7ae3c959&scene=21#wechat_redirect)

●[Elasticsearch 在各大互联网公司大量真实的应用案例！](http://mp.weixin.qq.com/s?__biz=MzAxMjU4NzczOA==&mid=2247502440&idx=2&sn=7949cfe31ea6c3e707deaec65f530114&chksm=9bad0dddacda84cbf71bc7b78b8c35af0537b5c6720862b8e9a8612ca9d83e56360b0619de06&scene=21#wechat_redirect)

●[4 款 MySQL 调优工具，公司大神都在用！](http://mp.weixin.qq.com/s?__biz=MzAxMjU4NzczOA==&mid=2247502174&idx=2&sn=9569501a5ab0ff269e35b28b49328682&chksm=9bad0eebacda87fdefee25878ba05bb9d6879102f91bbcbb3edca34108cd172a8a9902f85cb2&scene=21#wechat_redirect)









![](https://mmbiz.qpic.cn/mmbiz_jpg/2LlmEpiamhypdwf73jGlzkd20yDpW2mwZaNok9cbibBe2icWeCibATk4iaib1MkFgqJvEicicZP8Ct38DaHiaibYy18wKEUw/640?wx_fmt=jpeg)

Java 这点事

![](https://mmbiz.qpic.cn/mmbiz_gif/2LlmEpiamhypdwf73jGlzkd20yDpW2mwZFfRuboGib9YB2A96ygPNN5PnmHuUtlSviayje0ibBAhTB0zq44VsiaOZxw/640?wx_fmt=gif)

每晚 10 点分享 Java 技术、

IT 资讯，与你一起进步！





如果觉得我的分享不错，就随手 “在看”

![](https://mmbiz.qpic.cn/mmbiz_gif/2LlmEpiamhypdwf73jGlzkd20yDpW2mwZclmUxM7MlmyiaicEaabdtT9E0ibQIkgia15VLqiaeQsxNMPxMUM080j5icSA/640?wx_fmt=gif)