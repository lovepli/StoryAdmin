> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/kW2mE-G0bkh2jAJodCV8Mg)

最近在做代码重构，发现代码里有很多异味。别的不多说，今天主要讲一下如何重构那些很臭很长的 if...else。

在介绍更优雅的编程之前，让我们回顾一下糟糕的 if...else 代码。

很臭很长的 if...else
===============

废话不多说，先看看下面的代码：

```
public interface IPay {
  void pay();
}
@Service
public class AliaPay implements IPay {
  @Override
  public void pay() {
    System.out.println("===发起支付宝支付===");
  }
}
@Service
public class WeixinPay implements IPay {
  @Override
  public void pay() {
    System.out.println("===发起微信支付===");
  }
}
@Service
public class JingDongPay implements IPay {
  @Override
  public void pay() {
    System.out.println("===发起京东支付===");
  }
}
@Service
public class PayService {
  @Autowired
  private AliaPay aliaPay;
  @Autowired
  private WeixinPay weixinPay;
  @Autowired
  private JingDongPay jingDongPay;
  public void toPay(String code) {
    if ("alia".equals(code)) {
      aliaPay.pay();
    } else if ("weixin".equals(code)) {
      weixinPay.pay();
    } else if ("jingdong".equals(code)) {
      jingDongPay.pay();
    } else {
      System.out.println("找不到支付方式");
    }
  }
}
```

PayService 类的 toPay 方法主要是发起支付。根据不同的 code，决定调用不同支付类（例如：aliaPay）的 pay 方法进行支付。

这段代码有什么问题？也许有些人会这样做。

想象一下，如果支付方式越来越多，比如百度支付、美团支付、银联等，你需要修改 toPay 方式的代码，添加一个新的 else... 更多的逻辑会导致更多 和更多的逻辑？  显然，这里违反了设计模式的六大原则：

*   开闭原则：对扩展开放，对修改封闭。也就是说，要添加新功能，应尽量少改动现有代码。

*   单一职责原则：顾名思义，要求逻辑尽量简单，不要太复杂，易于复用。


有没有办法解决这个问题？

**消除 if...else 花招**

**使用注解**

之所以在代码中使用 code 来决定使用哪个支付类，是因为 code 和支付类之间没有绑定关系。如果存在绑定关系，则无需判断。

先定义一个注解

```
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PayCode {
  String value();
  String name();
}
```

在所有的支付类上都加上该注解：

```
@PayCode(value = "alia", name = "支付宝支付")
@Service
public class AliaPay implements IPay {
  @Override
  public void pay() {
    System.out.println("===发起支付宝支付===");
  }
}
@PayCode(value = "weixin", name = "微信支付")
@Service
public class WeixinPay implements IPay {
  @Override
  public void pay() {
    System.out.println("===发起微信支付===");
  }
}
@PayCode(value = "jingdong", name = "京东支付")
@Service
public class JingDongPay implements IPay {
  @Override
  public void pay() {
    System.out.println("===发起京东支付===");
  }
}
```

然后增加最关键的类：

```
@Service
public class PayService2 implements ApplicationListener<ContextRefreshedEvent> {
  private static Map<String, IPay> payMap = null;
  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
    Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(PayCode.class);
    if (beansWithAnnotation != null) {
      payMap = new HashMap<>();
      beansWithAnnotation.forEach((key, value) ->{
        String bizType = value.getClass().getAnnotation(PayCode.class).value();
        payMap.put(bizType, (IPay) value);
    });
  }
}
  public void pay(String code) {
    payMap.get(code).pay();
  }
}
```

PayService2 类实现了 ApplicationListener 接口，这样就可以在 onApplicationEvent 方法中获取 ApplicationContext 的一个实例。

然后我们获取 PayCode 注释类并将其放入映射中。  map 中 key 为 PayCode 注解中定义的 value，与 code 参数一致，value 为支付类的一个实例。

这样每次都可以直接通过代码获取支付类实例，而不用 if...else 判断。

如果要添加新的支付方式，只需在支付类上添加一个 PayCode 注解，定义一个新的 code 即可。

注：这种方式的代码可以没有商业意义，可以是纯数字，不需要重复。

动态拼接名称

该方法主要针对 code 是有业务含义的场景。

```
@Service
public class PayService3 implements ApplicationContextAware {
  private ApplicationContext applicationContext;
  private static final String SUFFIX = "Pay";
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
  public void toPay(String payCode) {
    ((IPay) applicationContext.getBean(getBeanName(payCode))).pay();
  }
  public String getBeanName(String payCode) {
    return payCode + SUFFIX;
  }
}
```

我们可以看到支付 bean 的名称是由代码和后缀组成的，例如：aliaPay、weixinPay 和 jingDongPay。

这个在命名支付类的时候需要特别注意，上一段要和代码保持一致。

被调用的支付类的实例直接从 ApplicationContext 实例中获取。默认情况下，bean 是单例并放置在内存中的映射中，因此不会出现性能问题。

特别是这个方法实现了 ApplicationContextAware 接口，与上面的 ApplicationListener 接口不同。我想告诉你，获取 ApplicationContext 实例的方法不止一种。

模板方法判断

当然，除了上面介绍的两种方法，Spring 的源码实现还告诉我们另一种解决 if...else 问题的方法。

先看一下 Spring AOP 的一些源码，再看一下

DefaultAdvisorAdapterRegistry 的 wrap 方法：

```
public Advisor wrap(Object adviceObject) throws UnknownAdviceTypeException {
  if (adviceObject instanceof Advisor) {
    return (Advisor) adviceObject;
  }
  if (!(adviceObject instanceof Advice)) {
    throw new UnknownAdviceTypeException(adviceObject);
  }
  Advice advice = (Advice) adviceObject;
  if (advice instanceof MethodInterceptor) {
    return new DefaultPointcutAdvisor(advice);
  }
  for (AdvisorAdapter adapter : this.adapters) {
    if (adapter.supportsAdvice(advice)) {
      return new DefaultPointcutAdvisor(advice);
    }
  }
  throw new UnknownAdviceTypeException(advice);
}
```

重点看看 supportAdvice 方法，有三个类实现了这个方法。

我们随便抽一个类看看：

```
class AfterReturningAdviceAdapter implements AdvisorAdapter, Serializable {
  @Override
  public boolean supportsAdvice(Advice advice) {
    return (advice instanceof AfterReturningAdvice);
  }
  @Override
  public MethodInterceptor getInterceptor(Advisor advisor) {
    AfterReturningAdvice advice = (AfterReturningAdvice) advisor.getAdvice();
    return new AfterReturningAdviceInterceptor(advice);
  }
}
```

这个类的 supportsAdvice 方法很简单，就是判断通知的类型是否为 AfterReturningAdvice。

我们看到这里应该有一些启发。

其实我们可以通过定义一个接口或者抽象类来做到这一点，其中有一个支持方法来判断参数传递的代码是否可以自己处理，如果可以处理，支付逻辑 用来。

```
public interface IPay {
  boolean support(String code);
  void pay();
}
@Service
public class AliaPay implements IPay {
  @Override
  public boolean support(String code) {
    return "alia".equals(code);
  }
  @Override
  public void pay() {
    System.out.println("===发起支付宝支付===");
  }
}
@Service
public class WeixinPay implements IPay {
  @Override
  public boolean support(String code) {
    return "weixin".equals(code);
  }
  @Override
  public void pay() {
    System.out.println("===发起微信支付===");
  }
}
@Service
public class JingDongPay implements IPay {
  @Override
  public boolean support(String code) {
    return "jingdong".equals(code);
  }
  @Override
  public void pay() {
    System.out.println("===发起京东支付===");
  }
}
```

每个支付类都有一个 support 方法，判断传过来的 code 是否和自己定义的相等

```
@Service
public class PayService4 implements ApplicationContextAware, InitializingBean {
  private ApplicationContext applicationContext;
  private List<IPay> payList = null;
  @Override
  public void afterPropertiesSet() throws Exception {
    if (payList == null) {
      payList = new ArrayList<>();
      Map<String, IPay> beansOfType = applicationContext.getBeansOfType(IPay.class);
      beansOfType.forEach((key, value) -> payList.add(value));
    }
  }
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
  public void toPay(String code) {
    for (IPay iPay : payList) {
      if (iPay.support(code)) {
        iPay.pay();
      }
    }
  }
}
```

在这段代码中，实现了 IPay 接口的支付类实例被初始化为一个列表集，调用支付接口时，通过列表集循环返回。如果代码与自己定义的相同，则调用当前支付类实例的支付方法。

策略 + 工厂模式

这种方法也用在代码有业务意义的场景中：

*   策略模式定义了一组算法，将它们一个一个封装起来，使它们可以互换。

*   工厂模式用于封装和管理对象的创建，是一种创建模式


```
public interface IPay {
  void pay();
}
@Service
public class AliaPay implements IPay {
  @PostConstruct
  public void init() {
    PayStrategyFactory.register("aliaPay", this);
  }
  @Override
  public void pay() {
    System.out.println("===发起支付宝支付===");
  }
}
@Service
public class WeixinPay implements IPay {
  @PostConstruct
  public void init() {
    PayStrategyFactory.register("weixinPay", this);
  }
  @Override
  public void pay() {
    System.out.println("===发起微信支付===");
  }
}
@Service
public class JingDongPay implements IPay {
  @PostConstruct
  public void init() {
    PayStrategyFactory.register("jingDongPay", this);
  }
  @Override
  public void pay() {
    System.out.println("===发起京东支付===");
  }
}
public class PayStrategyFactory {
  private static Map<String, IPay> PAY_REGISTERS = new HashMap<>();
  public static void register(String code, IPay iPay) {
    if (null != code && !"".equals(code)) {
      PAY_REGISTERS.put(code, iPay);
    }
  }
  public static IPay get(String code) {
    return PAY_REGISTERS.get(code);
  }
}
@Service
public class PayService3 {
  public void toPay(String code) {
    PayStrategyFactory.get(code).pay();
  }
}
```

这段代码的关键是 PayStrategyFactory 类，它是一个策略工厂，它定义了一个全局映射，并将当前实例注册到所有 IPay 实现类中的映射中。

然后使用 PayStrategyFactory 类根据调用处的代码从地图中获取支付类实例。

责任链模式

这种方法对于在代码重构过程中消除 if...else 非常有效。

责任链模式：将请求的处理对象像长链一样组合成对象链。请求不知道具体的执行请求是哪个对象，从而实现了请求与处理对象的解耦。

常用的 filter 和 spring aop 使用的是责任链模型。这里我稍微改进了一下。具体代码如下：

```
public abstract class PayHandler {
  @Getter
  @Setter
  protected PayHandler next;
  public abstract void pay(String pay);
}
@Service
public class AliaPayHandler extends PayHandler {
  @Override
  public void pay(String code) {
    if ("alia".equals(code)) {
      System.out.println("===发起支付宝支付===");
    } else {
      getNext().pay(code);
    }
  }
}
@Service
public class WeixinPayHandler extends PayHandler {
  @Override
  public void pay(String code) {
    if ("weixin".equals(code)) {
      System.out.println("===发起微信支付===");
    } else {
      getNext().pay(code);
    }
  }
}
@Service
public class JingDongPayHandler extends PayHandler {
  @Override
  public void pay(String code) {
    if ("jingdong".equals(code)) {
      System.out.println("===发起京东支付===");
    } else {
      getNext().pay(code);
    }
  }
}
@Service
public class PayHandlerChain implements ApplicationContextAware, InitializingBean {
  private ApplicationContext applicationContext;
  private PayHandler header;
  public void handlePay(String code) {
    header.pay(code);
  }
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
  @Override
  public void afterPropertiesSet() throws Exception {
    Map<String, PayHandler> beansOfTypeMap = applicationContext.getBeansOfType(PayHandler.class);
    if (beansOfTypeMap == null || beansOfTypeMap.size() == 0) {
      return;
    }
    List<PayHandler> handlers = beansOfTypeMap.values().stream().collect(Collectors.toList());
    for (int i = 0; i < handlers.size(); i++) {
      PayHandler payHandler = handlers.get(i);
        if (i != handlers.size() - 1) {
          payHandler.setNext(handlers.get(i + 1));
        }
      }
     header = handlers.get(0);
    }    
}
```

这段代码的关键是每个 PayHandler 子类定义了下一个需要执行的 PayHandler 子类，形成链式调用，这个链式结构是通过 PayHandlerChain 组装起来的。

其他的消除 if...else 的方法

当然，在实际项目开发中使用 if...else 的场景有很多，以上只是其中的一小部分。下面是一些其他常见的场景。

**①根据不同的数字返回不同的字符串**

代码如下：

```
public String getMessage(int code) {
  if (code == 1) {
    return "成功";
  } else if (code == -1) {
    return "失败";
  } else if (code == -2) {
    return "网络超时";
  } else if (code == -3) {
    return "参数错误";
  }
  throw new RuntimeException("code错误");
}
```

其实，这种判断没有必要，用一个枚举就可以搞定。

```
public enum MessageEnum {
  SUCCESS(1, "成功"),
  FAIL(-1, "失败"),
  TIME_OUT(-2, "网络超时"),
  PARAM_ERROR(-3, "参数错误");
  private int code;
  private String message;
  MessageEnum(int code, String message) {
    this.code = code;
    this.message = message;
  }
  public int getCode() {
    return this.code;
  }
  public String getMessage() {
    return this.message;
  }
  public static MessageEnum getMessageEnum(int code) {
    return Arrays.stream(MessageEnum.values()).filter(x -> x.code == code).findFirst().orElse(null);
  }
}
```

再把调用方法稍微调整一下：

```
public String getMessage(int code) {
  MessageEnum messageEnum = MessageEnum.getMessageEnum(code);
  return messageEnum.getMessage();
}
```

**②集合中的判断**

上面的枚举 MessageEnum 中的 getMessageEnum 方法，如果不用 java8 的语法的话，可能要这样写：

```
public static MessageEnum getMessageEnum(int code) {
  for (MessageEnum messageEnum : MessageEnum.values()) {
    if (code == messageEnum.code) {
      return messageEnum;
    }
  }
  return null;
}
```

对于集合中的过滤数据或搜索方法，java8 有一个更简单的方法来消除 if...else 判断。

```
public static MessageEnum getMessageEnum(int code) {
  return Arrays.stream(MessageEnum.values()).filter(x -> x.code == code).findFirst().orElse(null);
}
```

**③简单的判断**

其实有些简单的 if...else 完全没有必要写，可以用三目运算符代替，比如这种情况：

```
public String getMessage2(int code) {
  if(code == 1) {
    return "成功";
  }
  return "失败";
}
```

改成三目运算符：

```
public String getMessage2(int code) {
  return code == 1 ? "成功" : "失败";
}
```

④Spring 中的判断

对于参数异常，越早发现越好。  Spring 中提供了 Assert 来帮助我们检测参数是否有效。

```
public void save(Integer code，String name) {
  if(code == null) {
    throw Exception("code不能为空");
  } else {
    if(name == null) {
      throw Exception("name不能为空");
    } else {
      System.out.println("doSave");
    }
  }
}
```

如果参数很多，if...else 语句会很长。这时候如果改用 Assert 类来判断，代码会简化很多：

```
public String save2(Integer code，String name) {
  Assert.notNull(code,"code不能为空");
  Assert.notNull(name,"name不能为空");
  System.out.println("doSave");
}
```