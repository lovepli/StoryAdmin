> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/pD6clDQOu4w1VuBpD0jIUg)

![](https://mmbiz.qpic.cn/mmbiz_jpg/TeYk478W36AScAib0OLqcZJuasqLrjNXeVjic9mI1D7tce0yuyfeJd72icynnkZWdJa5l0P8JtBmhtSDXuhpU4O6w/640?wx_fmt=jpeg)

程序员的成长之路

互联网 / 程序员 / 技术 / 资料共享

关注

阅读本文大概需要 15 分钟。

作者：larva-zhh  
来源：www.cnblogs.com/larva-zhh/p/11544317.html

为什么要替换 fastjson
---------------

工程里大量使用了 fastjson 作为序列化和反序列化框架，甚至 ORM 在处理部分字段也依赖 fastjson 进行序列化和反序列化。那么作为大量使用的基础框架，为什么还要进行替换呢？

原因有以下几点：

1.  fastjson 太过于侧重性能，对于部分高级特性支持不够，而且部分自定义特性完全偏离了 json 和 js 规范导致和其他框架不兼容；

2.  fastjson 文档缺失较多，部分 Feature 甚至没有文档，而且代码缺少注释较为晦涩；

3.  fastjson 的 CVE bug 监测较弱，很多 CVE 数据库网站上有关 fastjson 的 CVE 寥寥无几，例如近期的 AutoType 导致的高危漏洞，虽然和 Jackson 的 PolymorphicDeserialization 是同样的 bug，但是 CVE 网站上几乎没有 fastjson 的 bug 报告。


框架选型
----

参考 mvnrepository json libraries，根据流行度排序后前十名框架：

*   jackson2(com.fasterxml.jackson)

*   gson

*   org.json

*   jackson1(com.codehuas.jackson)

*   fastjson

*   cheshire

*   json-simple


![](https://mmbiz.qpic.cn/mmbiz_png/TNUwKhV0JpTojvfm4jhLmFicYnxg4rmSKYu6lHjTmiawIHrJsXdcPPJiaJVsvsFDgXekZicAiaxR5ic5DnqVZNIp6icng/640?wx_fmt=png)

[jackson1 是已经过时的框架，因此可以忽略，cheshire 和 json-simple 排名尚且不如 fastjson，也忽略，剩余 jackson2、gson 以及 org.json，其中 org.json 的使用量 (usage) 远小于 jackson2(方便起见，下文均以 jackson 均指代 jackson2)和 gson，因此 org.json 也可以排除了。](http://mp.weixin.qq.com/s?__biz=MzI3ODcxMzQzMw==&mid=2247524678&idx=3&sn=a153fd4fae16c357d55414e067d7bf25&chksm=eb50e470dc276d6676c923b597cbd28cf29e7a31393f1f03afedaf22f6aaf9e0e5517b9bdb32&scene=21#wechat_redirect)

关于 jackson 和 gson 的比较文章有很多，stackoverflow 上自行搜索，下面仅推荐几篇 blog：

*   jackson vs gson

*   JSON in Java

*   the ultimate json library json-simple vs gson vs jackson vs json


在功能特性支持、稳定性、可扩展性、易用性以及社区活跃度上 jackson 和 gson 差不多，入门教程可以分别参考 baeldung jackson 系列 以及 baeldung gson 系列。但是 jackson 有更多现成的类库兼容支持例如`jackson-datatype-commons-lang3`，以及更丰富的输出数据格式支持例如`jackson-dataformat-yaml`，而且 spring 框架默认使用 jackson，因此最终我选择使用 jackson。

PS: Jackson 2.10.0 开始尝试基于新的 API 使用白名单机制来避免 RCE 漏洞，详见 https://github.com/FasterXML/jackson-databind/issues/2195，效果尚待观察。

替换 fastjson
-----------

fastjson 常见的使用场景就是序列化和反序列化，偶尔会有`JSONObject`和`JSONArray`实例的相关操作。

以下步骤的源码分析基于以下版本：

*   `fastjson v1.2.60`

*   `jackson-core v2.9.9`

*   `jackson-annotations v2.9.0`

*   `jackson-databind v2.9.9.3`


Deserialization
---------------

fastjson 将 json 字符串反序列化成 Java Bean 通常使用`com.alibaba.fastjson.JSON`的静态方法 (`JSONObject`和`JSONArray`的静态方法也是来自于`JSON`)，常用的有以下几个 API：

```
public static JSONObject parseObject(String text);

public static JSONObject parseObject(String text, Feature... features);

public static <T> T parseObject(String text, Class<T> clazz);

public static <T> T parseObject(String text, Class<T> clazz, Feature... features);

public static <T> T parseObject(String text, TypeReference<T> type, Feature... features);

public static JSONArray parseArray(String text);

public static <T> List<T> parseArray(String text, Class<T> clazz);
```

从方法入参就能猜到，fastjson 在执行反序列化时的 Parse 行为由`com.alibaba.fastjson.parser.Feature`指定。研究`parseObject`的源码后，发现底层最终都是使用的以下方法：

```
public static <T> T parseObject(String input, Type clazz, ParserConfig config, ParseProcess processor, int featureValues, Feature... features) {
   if (input == null) {
       return null;
   }

   // featureValues作为基准解析特性开关值
   // 入参features和featureValues取并集得到最终的解析特性
   if (features != null) {
       for (Feature feature : features) {
           featureValues |= feature.mask;
       }
   }

   DefaultJSONParser parser = new DefaultJSONParser(input, config, featureValues);

   if (processor != null) {
       if (processor instanceof ExtraTypeProvider) {
           parser.getExtraTypeProviders().add((ExtraTypeProvider) processor);
       }

       if (processor instanceof ExtraProcessor) {
           parser.getExtraProcessors().add((ExtraProcessor) processor);
       }

       if (processor instanceof FieldTypeResolver) {
           parser.setFieldTypeResolver((FieldTypeResolver) processor);
       }
   }

   T value = (T) parser.parseObject(clazz, null);

   parser.handleResovleTask(value);

   parser.close();

   return (T) value;

}
```

通过 IDE 搜索 usage 后，发现当没有作为基准解析特性开关的`featureValues`入参时，都是使用的`DEFAULT_PARSE_FEATURE`作为基准解析特性开关，以下是`JSON.DEFAULT_PARSE_FEATURE`的实例化代码：

```
static {
        int features = 0;
        features |= Feature.AutoCloseSource.getMask();
        features |= Feature.InternFieldNames.getMask();
        features |= Feature.UseBigDecimal.getMask();
        features |= Feature.AllowUnQuotedFieldNames.getMask();
        features |= Feature.AllowSingleQuotes.getMask();
        features |= Feature.AllowArbitraryCommas.getMask();
        features |= Feature.SortFeidFastMatch.getMask();
        features |= Feature.IgnoreNotMatch.getMask();
        DEFAULT_PARSER_FEATURE = features;
}
```

fastjson 还会从环境变量中读取配置来修改`DEFAULT_PARSER_FEATURE`(虽然很少会有人这么做)，但最好还是通过实际运行一下程序来确认你的环境中的实际解析特性开关。

```
@Test
public void printFastJsonDefaultParserFeature() {
    for (Feature feature : Feature.values()) {
        if (Feature.isEnabled(JSON.DEFAULT_PARSER_FEATURE, feature)) {
            System.out.println(feature);
        }
    }
}
```

fastjson 和 jackson 的反序列化特性对照表
-----------------------------

```
Feature.AutoCloseSource
```

反序列化 fastjson 和 jackson 的特性 TestCase 见 DeserializationUseJacksonReplaceFastJsonTest.java

Serialization
-------------

fastjson 将 Java Bean 序列化成 json 字符串通常也是使用`com.alibaba.fastjson.JSON`的静态方法 (`JSONObject`和`JSONArray`的静态方法也是来自于`JSON`)，常用的有以下几个 API：

```
public static String toJSONString(Object object);

public static String toJSONString(Object object, SerializerFeature... features);

public static String toJSONStringWithDateFormat(Object object, String dateFormat, SerializerFeature... features);

public static String toJSONString(Object object, boolean prettyFormat);

public static void writeJSONString(Writer writer, Object object, SerializerFeature... features);
```

从方法入参也能看出，在序列化时，fastjson 的特性由`SerializerFeature`控制，研究`toJSONString`的源码后，发现最终都会调用以下方法：

```
 public static String toJSONString(Object object, SerializeConfig config, SerializeFilter[] filters, String dateFormat, int defaultFeatures, SerializerFeature... features) {
   SerializeWriter out = new SerializeWriter(null, defaultFeatures, features);

   try {
       JSONSerializer serializer = new JSONSerializer(out, config);

       if (dateFormat != null && dateFormat.length() != 0) {
           serializer.setDateFormat(dateFormat);
           serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
       }

       if (filters != null) {
           for (SerializeFilter filter : filters) {
               serializer.addFilter(filter);
           }
       }

       serializer.write(object);

       return out.toString();
   } finally {
       out.close();
   }
}
```

通过 IDE 搜索 usage 后，发现当没有作为基准解析特性开关的`defaultFeatures`入参时，都是使用的`DEFAULT_GENERATE_FEATURE`作为基准解析特性开关，以下是`JSON.DEFAULT_GENERATE_FEATURE`的实例化代码：

```
static {
    int features = 0;
    features |= SerializerFeature.QuoteFieldNames.getMask();
    features |= SerializerFeature.SkipTransientField.getMask();
    features |= SerializerFeature.WriteEnumUsingName.getMask();
    features |= SerializerFeature.SortField.getMask();

    DEFAULT_GENERATE_FEATURE = features;

    config(IOUtils.DEFAULT_PROPERTIES);
}
```

fastjson 还会从环境变量中读取配置来修改`DEFAULT_GENERATE_FEATURE`(虽然很少会有人这么做)，但最好还是通过实际运行一下程序来确认你的环境中的实际解析特性开关。

```
@Test
public void printFastJsonDefaultGenerateFeature() {
    for (SerializerFeature feature : SerializerFeature.values()) {
        if (SerializerFeature.isEnabled(JSON.DEFAULT_GENERATE_FEATURE, feature)) {
            System.out.println(feature);
        }
    }
}
```

fastjson 和 jackson 的序列化特性对照表
----------------------------

```
SerializerFeature.QuoteFieldNames
```

序列化 fastjson 和 jackson 的特性 TestCase 见 SerializationUseJacksonReplaceFastJsonTest.java。

Annotation
----------

fastjsonzhu 相对于 jackson 来说注解的功能划分的并没有那么细，因此 fastjson 的一个注解可能等价于 jackson 多个注解的组合。

### `@JSONPOJOBuilder`

指定反序列化时创建 java 对象使用的 build 方法，对应 jackson 的`@JsonPOJOBuilder`。

### `@JSONCreator`

指定反序列化时创建 java 对象使用的构造方法，对应 jackson 的`@JsonCreator`。

### `@JSONField`

指定序列化和反序列化 field 时的行为。反序列化时，等价于`@JsonProperty` + `@JsonDeserialize` + `@JsonUnwrapped` + `@JsonFormat`+`@JsonAlias`；序列化时，等价于`@JsonProperty` + `@JsonSerialize` +`@JsonUnwrapped` + `@JsonFormat` + `@JsonRawValue` + `@JsonView`。

```
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
public @interface JSONField {
    // 序列化和反序列化时的字段顺序，等价于jackson的@JsonProperty.index()
    int ordinal() default 0;

    // 序列化和反序列化时的字段名称映射，等价于jackson的@JsonProperty.value()
    String name() default "";

    // 序列化和反序列化时的数据格式（日期格式、16进制等等），等价于jackson的@JsonFormat.shape() + @JsonFormat.pattern()
    String format() default "";

    // 字段是否序列化，等价于jackson的@JsonProperty.access()
    boolean serialize() default true;

    // 字段是否反序列化，等价于jackson的@JsonProperty.access()
    boolean deserialize() default true;

    // 序列化特性，等价于jackson的@JsonProperty.with()
    SerializerFeature[] serialzeFeatures() default {};

    // 反序列化特性，等价于jackson的@JsonFormat.with()
    Feature[] parseFeatures() default {};

    // 对属性进行打标，便于在序列化时进行exclude或include，等价于jackson的@JsonView
    String label() default "";

    // 序列化时将字段内容直接输出，不经过转义，等价于jackson的@JsonRawValue
    boolean jsonDirect() default false;

    // 指定序列化时使用的Serializer Class，等价于jackson的@JsonSerialize
    Class<?> serializeUsing() default Void.class;

    // 指定反序列化时使用的Deserializer Class，等价于jackson的@JsonDeserialize
    Class<?> deserializeUsing() default Void.class;

    // 指定反序列化时使用的字段别名，等价于jackson的@JsonAlias
    String[] alternateNames() default {};

    // 将字段的子属性映射到父节点上，等价于jackson的@JsonUnwrapped
    boolean unwrapped() default false;

    // 指定序列化时字段为null时使用的默认值，等价于jackson的@JsonProperty.defaultValue()
    String defaultValue() default "";
}
```

`unwrapped`的用法可以参考 AnnotationUseJacksonReplaceFastJsonTest.java 中的`testJSONFieldUnwrapped`。

`@JSONType`
-----------

指定序列化和反序列化一个 Java Bean 时的行为。

```
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface JSONType {

    // 是否使用asm优化，jackson无对应特性
    boolean asm() default true;

    // 序列化和反序列化时的field排序，等价于jackson的@JsonPropertyOrder.value()
    String[] orders() default {};

    // 序列化和反序列化时包含的field，等价于jackson的
    String[] includes() default {};

    // 序列化和反序列化时忽略的field，等价于jackson的@JsonIgnoreProperties
    String[] ignores() default {};

    // 序列化特性，等价于jackson的@JsonProperty.with()
    SerializerFeature[] serialzeFeatures() default {};

    // 反序列化特性，等价于jackson的@JsonFormat.with()
    Feature[] parseFeatures() default {};

    // 序列化时是否依据field字母顺序排序，等价于jackson的@JsonPropertyOrder.alphabetic()
    boolean alphabetic() default true;

    // 反序列化多态类型时，如果根据其他typeName等方式无法找到正确的子类时，默认使用的子类，等价于jackson的@JsonTypeInfo.defaultImpl()
    Class<?> mappingTo() default Void.class;

    // 反序列化时指定java bean builder类(必须是@JSONPOJOBuilder注解的类)，等价于jackson的@JsonDeserialize.builder()
    Class<?> builder() default Void.class;

    // 声明这个类型的别名，反序列化多态类型时使用，等价于jackson的@JsonTypeName
    String typeName() default "";

    // 反序列化某个接口或抽象类或父类的子类时指定根据哪个字段的值和子类的typeName相等来决定具体实现类，等价于jackson的@JsonTypeInfo.use() = Id.CUSTOM + @JsonTypeInfo.property()
    String typeKey() default "";

    // 反序列化某个接口或抽象类或父类的子类时指定可以反序列化的子类类型，等价于jackson的@JsonSubTypes
    Class<?>[] seeAlso() default{};

    // 指定序列化时使用的Serializer Class，等价于jackson的@JsonSerialize
    Class<?> serializer() default Void.class;

    // 指定反序列化时使用的Deserializer Class，等价于jackson的@JsonDeserialize
    Class<?> deserializer() default Void.class;

    // 序列化时，如果filed是枚举类型，则和普通的java bean一样输出枚举的filed，而不是通常使用的Enum.name()值，jackson没有对应特性
    boolean serializeEnumAsJavaBean() default false;

    // 指定json和Java bean之间的字段名称映射策略，等价于jackson的@JsonNaming
    PropertyNamingStrategy naming() default PropertyNamingStrategy.CamelCase;

    // 指定序列化时使用的Serialize filter，等价于jackson的@JsonFilter
    Class<? extends SerializeFilter>[] serialzeFilters() default {};
}
```

`JSONObject` & `JSONArray`
--------------------------

首先来看看 fastjon 中`JSONObject`和`JSONArray`的源码：

```
public class JSONObject extends JSON implements Map<String, Object>, Cloneable, Serializable, InvocationHandler {

    private final Map<String, Object> map;
    ...
}
public class JSONArray extends JSON implements List<Object>, Cloneable, RandomAccess, Serializable {

    private static final long  serialVersionUID = 1L;
    private final List<Object> list;
    protected transient Object relatedArray;
    protected transient Type   componentType;
    ...
}
```

从源码就可以发现，`JSONObject`实际是一个`Map<String, Object>`，而`JSONArray`实际是一个`List<JSONObject>`。因此可以将`JSONObject`类型改为`Map<String, Object>`，而`JSONArray`类型改为`List<Object>`。但是这种方式就会导致上层 API 出现大量修改，因为缺少了`JSONObject`和`JSONArray`提供的多种便利的类型转换方法。如果想要暂时保留`JSONObject`和`JSONArray`，此时可以采取一种取巧的方法。

暂时保留`JSONObject` & `JSONArray`的过渡方法
-----------------------------------

jackson 官方提供了对`org.json`库的数据类型支持`jackson-datatype-json-org`，因此可以将`com.alibaba.fastjson.JSONObject`替换为`org.json.JSONObject`，`com.alibaba.fastjson.JSONArray`替换为`org.json.JSONArray`，这两个类库的对象 API 大致相同，当然一些细小的改动还是避免不了的。如果想完全不改上层代码，那也可以参考 jackson-datatype-json-org 和 jackson-datatype-json-lib 自己实现 jackson 对 fastjson 的数据类型的 binder。

> larva-zhang/jackson-datatype-fastjson 欢迎大家使用或提 issues。

JSONPath
--------

使用 json-path/JsonPath 就能轻松替换 fastjson 的 JSONPath，而且功能比 fastjson 更强大。只需参考 JsonProvider SPI 使用`JacksonJsonProvider`替代 json-path/JsonPath 默认的`JsonSmartJsonProvider`即可。

自定义扩展
-----

#### 自定义 Deserializer

fastjson 中实现自定义 Deserializer 的方法通常是实现`ObjectDeserializer`接口的`deserialze`方法

```
<T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName);
```

在 jackson 中实现自定义 Serializer 的方法则通常是继承`StdDeserializer`抽象类，重写`deserialize`方法

```
public abstract T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException;
```

#### 自定义 Serializer

fastjson 中实现自定义 Serializer 的方法通常是实现`ObjectSerializer`接口的`write`方法

```
void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException;
```

在 jackson 中实现自定义 Serializer 的方法则通常是继承`StdSerializer`抽象类，重写`serialize`方法

```
public abstract void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException;
```

#### 自定义 Serialize Filter

fastjson 中提供了 6 种`SerializeFilter`，详见 fastjson/wiki/SerializeFilter。而在 jackson 中则是建议继承`SimpleBeanPropertyFilter`。

<END>

**推荐阅读：**

[虚拟货币，太坑爹了！](http://mp.weixin.qq.com/s?__biz=MzUyNDkzNzczNQ==&mid=2247507723&idx=1&sn=96a32fa40e0080ad5b98c07fc72ab593&chksm=fa272063cd50a975a6557cca3e2d685e7457313044d53b0be41418db931f5806916698fc6d13&scene=21#wechat_redirect)

[Java 这个高级特性，很多人还没用过！](http://mp.weixin.qq.com/s?__biz=MzUyNDkzNzczNQ==&mid=2247507723&idx=2&sn=c42a8e32c2a4b8ee663c21e9024dba49&chksm=fa272063cd50a975f8a1429b0e8ce733f2fd9875ea6e18d53ab49356fbc18679c5da2757c823&scene=21#wechat_redirect)

```
最近面试BAT，整理一份面试资料《Java面试BATJ通关手册》，覆盖了Java核心技术、JVM、Java并发、SSM、微服务、数据库、数据结构等等。
```

公众号

**获取方式：点个「****在看****」，**点击上方小卡片，进入公众号后**回复「****面试题****」领取，更多内容陆续奉上。**

朕已阅 ![](https://mmbiz.qpic.cn/mmbiz_gif/b96CibCt70iaa8r7PJoyAtlfHAKe8RosE3wYVKBac55p1HPBJHZS42ywnG4yYtD3jo9A9e5kawBZs4IE6R1C4wibw/640?wx_fmt=gif)