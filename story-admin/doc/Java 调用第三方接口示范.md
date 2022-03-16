> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/HWAYGSlpkLexyULTmcOHyg)

（给 ImportNew 加星标，提高 Java 技能）

在项目开发中经常会遇到调用第三方接口的情况，比如说调用第三方的天气预报接口。

### 使用流程

【1】准备工作：

在项目的工具包下导入 HttpClientUtil 这个工具类，或者也可以使用 Spring 框架的 restTemplate 来调用，上面有调用接口的方法【分为 Get 和 Post 方式的有参和无参调用】：

```
package com.njsc.credit.util;
 
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
 
public class HttpClientUtil {
 
 /**
  * 带参数的get请求
  * @param url
  * @param param
  * @return String
  */
 public static String doGet(String url, Map<String, String> param) {
  // 创建Httpclient对象
  CloseableHttpClient httpclient = HttpClients.createDefault();
 
  String resultString = "";
  CloseableHttpResponse response = null;
  try {
   // 创建uri
   URIBuilder builder = new URIBuilder(url);
   if (param != null) {
    for (String key : param.keySet()) {
     builder.addParameter(key, param.get(key));
    }
   }
   URI uri = builder.build();
   // 创建http GET请求
   HttpGet httpGet = new HttpGet(uri);
   // 执行请求
   response = httpclient.execute(httpGet);
   // 判断返回状态是否为200
   if (response.getStatusLine().getStatusCode() == 200) {
    resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
   }
  } catch (Exception e) {
   e.printStackTrace();
  } finally {
   try {
    if (response != null) {
     response.close();
    }
    httpclient.close();
   } catch (IOException e) {
    e.printStackTrace();
   }
  }
  return resultString;
 }
 
 /**
  * 不带参数的get请求
  * @param url
  * @return String
  */
 public static String doGet(String url) {
  return doGet(url, null);
 }
 
 /**
  * 带参数的post请求
  * @param url
  * @param param
  * @return String
  */
 public static String doPost(String url, Map<String, String> param) {
  // 创建Httpclient对象
  CloseableHttpClient httpClient = HttpClients.createDefault();
  CloseableHttpResponse response = null;
  String resultString = "";
  try {
   // 创建Http Post请求
   HttpPost httpPost = new HttpPost(url);
   // 创建参数列表
   if (param != null) {
    List<NameValuePair> paramList = new ArrayList<>();
    for (String key : param.keySet()) {
     paramList.add(new BasicNameValuePair(key, param.get(key)));
    }
    // 模拟表单
    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
    httpPost.setEntity(entity);
   }
   // 执行http请求
   response = httpClient.execute(httpPost);
   resultString = EntityUtils.toString(response.getEntity(), "utf-8");
  } catch (Exception e) {
   e.printStackTrace();
  } finally {
   try {
    response.close();
   } catch (IOException e) {
    e.printStackTrace();
   }
  }
  return resultString;
 }
 
 /**
  * 不带参数的post请求
  * @param url
  * @return String
  */
 public static String doPost(String url) {
  return doPost(url, null);
 }
 
 /**
  * 传送json类型的post请求
  * @param url
  * @param json
  * @return String
  */
 public static String doPostJson(String url, String json) {
  // 创建Httpclient对象
  CloseableHttpClient httpClient = HttpClients.createDefault();
  CloseableHttpResponse response = null;
  String resultString = "";
  try {
   // 创建Http Post请求
   HttpPost httpPost = new HttpPost(url);
   // 创建请求内容
   StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
   httpPost.setEntity(entity);
   // 执行http请求
   response = httpClient.execute(httpPost);
   resultString = EntityUtils.toString(response.getEntity(), "utf-8");
  } catch (Exception e) {
   e.printStackTrace();
  } finally {
   try {
    response.close();
   } catch (IOException e) {
    e.printStackTrace();
   }
  }
  return resultString;
 }
}
```

【2】创建 url 和访问 key 以及参数等：

![](https://mmbiz.qpic.cn/mmbiz_png/JfTPiahTHJhqPHfvxZiafkWSrKIpYaqgjAgYUjicibz4WxVPnxHl7hKha6IHf4WTqpicZkbbLz6hE9t6RnFzGJ9CTEg/640?wx_fmt=png)

代码如下：

```
/**
 * 聚合接口校验身份证
 * @param idCard
 * @param realName
 * @return boolean
 */
public boolean identityCheck(String idCard, String realName){
 logger.info("-----------------调用聚合数据 身份证验证API BEGIN--------------->");
 String key = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
 String url = "http://op.juhe.cn/idcard/query" + "?key=" + key + "&idcard=" + idCard + "&realname=" + realName;
 logger.info("请求url:" + url);
 boolean match = false; //是否匹配
 try {
  String result = HttpClientUtil.doGet(url);
  System.out.println("请求结果：" + result);
  IdentityCheckResult identityCheckResult = JsonUtils.parse(result, IdentityCheckResult.class);
  IdentityCheck identityCheck = JsonUtils.parse(result, "result", IdentityCheck.class);
  logger.info(identityCheckResult);
  logger.info(identityCheck.toString());
  if(identityCheckResult.correct() && identityCheck.getRes() == 1){
   match = true;
  }
 } catch (Exception e) {
  e.printStackTrace();
 }
 logger.info("<-----------------调用聚合数据 身份证验证API END---------------");
 return match;
}
```

【3】请求这个第三方接口：

使用 HttpClientUtil 工具类中的 doGet 方法来请求 URL，得到结果，现在大多数是一个 json 字符串，类型为 String

【4】根据接口返回数据格式来解析数据：

![](https://mmbiz.qpic.cn/mmbiz_png/JfTPiahTHJhqPHfvxZiafkWSrKIpYaqgjAUND51q9dVv9Ier1YQZnuH9p2iaH07uwWDiaEEbbmk2V9okbaUx4dicCNQ/640?wx_fmt=png)

可以看到，返回参数有六个，所以在项目中新建一个 bean，包含以上六个字段，用来接住返回数据，如下：

![](https://mmbiz.qpic.cn/mmbiz_png/JfTPiahTHJhqPHfvxZiafkWSrKIpYaqgjAFyibwLkj7mM3YG1ib7sbibYorweQ6QdUKoM52k5iaBYaMbIV2AoX3xub0g/640?wx_fmt=png)

因为接口返回的数据是一个 json 的字符串，类型实际上是一个 String 字符串，要解析数据，用工具类 JsonUtils 的 parse 方法将字符串转换为 Java 对象，JsonUtils 的代码如下：

```
package com.eqianxian.commons.utils.json;
 
import java.util.List;
import java.util.Map;
 
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
 
/**
 * 在系统中统一使用这个，以方便将来切换不同的JSON生成工具
 * 
 * @author KelvinZ
 * 
 */
public class JsonUtils {
 public static final int TYPE_FASTJSON = 0;
 public static final int TYPE_GSON = 1;
 
 /**
  * <pre>
  * 对象转化为json字符串
  * 
  * @param obj 待转化对象
  * @return 代表该对象的Json字符串
  */
 public static final String toJson(final Object obj) {
  return JSON.toJSONString(obj);
  // return gson.toJson(obj);
 }
 
 /**
  * <pre>
  * 对象转化为json字符串
  * 
  * @param obj 待转化对象
  * @return 代表该对象的Json字符串
  */
 public static final String toJson(final Object obj, SerializerFeature... features) {
  return JSON.toJSONString(obj, features);
  // return gson.toJson(obj);
 }
 
 /**
  * 对象转化为json字符串并格式化
  * 
  * @param obj
  * @param format 是否要格式化
  * @return
  */
 public static final String toJson(final Object obj, final boolean format) {
  return JSON.toJSONString(obj, format);
 }
 
 /**
  * 对象对指定字段进行过滤处理，生成json字符串
  * 
  * @param obj
  * @param fields 过滤处理字段
  * @param ignore true做忽略处理，false做包含处理
  * @param features json特征，为null忽略
  * @return
  */
 public static final String toJson(final Object obj, final String[] fields, final boolean ignore,
   SerializerFeature... features) {
  if (fields == null || fields.length < 1) {
   return toJson(obj);
  }
  if (features == null)
   features = new SerializerFeature[] { SerializerFeature.QuoteFieldNames };
  return JSON.toJSONString(obj, new PropertyFilter() {
   @Override
   public boolean apply(Object object, String name, Object value) {
    for (int i = 0; i < fields.length; i++) {
     if (name.equals(fields[i])) {
      return !ignore;
     }
    }
    return ignore;
   }
  }, features);
 }
 
 /**
  * <pre>
  * 解析json字符串中某路径的值
  * 
  * @param json
  * @param path
  * @return
  */
 @SuppressWarnings("unchecked")
 public static final <E> E parse(final String json, final String path) {
  String[] keys = path.split(",");
  JSONObject obj = JSON.parseObject(json);
  for (int i = 0; i < keys.length - 1; i++) {
   obj = obj.getJSONObject(keys[i]);
  }
  return (E) obj.get(keys[keys.length - 1]);
 }
 
 /**
  * <pre>
  * json字符串解析为对象
  * 
  * @param json 代表一个对象的Json字符串
  * @param clazz 指定目标对象的类型，即返回对象的类型
  * @return 从json字符串解析出来的对象
  */
 public static final <T> T parse(final String json, final Class<T> clazz) {
  return JSON.parseObject(json, clazz);
 }
 
 /**
  * <pre>
  * json字符串解析为对象
  * 
  * @param json json字符串
  * @param path 逗号分隔的json层次结构
  * @param clazz 目标类
  */
 public static final <T> T parse(final String json, final String path, final Class<T> clazz) {
  String[] keys = path.split(",");
  JSONObject obj = JSON.parseObject(json);
  for (int i = 0; i < keys.length - 1; i++) {
   obj = obj.getJSONObject(keys[i]);
  }
  String inner = obj.getString(keys[keys.length - 1]);
  return parse(inner, clazz);
 }
 
 /**
  * 将制定的对象经过字段过滤处理后，解析成为json集合
  * 
  * @param obj
  * @param fields
  * @param ignore
  * @param clazz
  * @param features
  * @return
  */
 public static final <T> List<T> parseArray(final Object obj, final String[] fields, boolean ignore,
   final Class<T> clazz, final SerializerFeature... features) {
  String json = toJson(obj, fields, ignore, features);
  return parseArray(json, clazz);
 }
 
 /**
  * <pre>
  * 从json字符串中解析出一个对象的集合，被解析字符串要求是合法的集合类型
  * （形如:["k1":"v1","k2":"v2",..."kn":"vn"]）
  * 
  * @param json - [key-value-pair...]
  * @param clazz
  * @return
  */
 public static final <T> List<T> parseArray(final String json, final Class<T> clazz) {
  return JSON.parseArray(json, clazz);
 }
 
 /**
  * <pre>
  * 从json字符串中按照路径寻找，并解析出一个对象的集合，例如：
  * 类Person有一个属性name，要从以下json中解析出其集合：
  * {
  *  "page_info":{
  *   "items":{
  *    "item":[{"name":"KelvinZ"},{"name":"Jobs"},...{"name":"Gates"}]
  *  }
  * }
  * 使用方法：parseArray(json, "page_info,items,item", Person.class)，
  * 将根据指定路径，正确的解析出所需集合，排除外层干扰
  * 
  * @param json json字符串
  * @param path 逗号分隔的json层次结构
  * @param clazz 目标类
  * @return
  */
 public static final <T> List<T> parseArray(final String json, final String path, final Class<T> clazz) {
  String[] keys = path.split(",");
  JSONObject obj = JSON.parseObject(json);
  for (int i = 0; i < keys.length - 1; i++) {
   obj = obj.getJSONObject(keys[i]);
  }
  String inner = obj.getString(keys[keys.length - 1]);
  List<T> ret = parseArray(inner, clazz);
  return ret;
 }
 
 /**
  * <pre>
  * 有些json的常见格式错误这里可以处理，以便给后续的方法处理
  * 常见错误：使用了\" 或者 "{ 或者 }"，腾讯的页面中常见这种格式
  * 
  * @param invalidJson 包含非法格式的json字符串
  * @return
  */
 public static final String correctJson(final String invalidJson) {
  String content = invalidJson.replace("\\\"", "\"").replace("\"{", "{").replace("}\"", "}");
  return content;
 }
 
 /**
  * 格式化Json
  * 
  * @param json
  * @return
  */
 public static final String formatJson(String json) {
  Map<?, ?> map = (Map<?, ?>) JSON.parse(json);
  return JSON.toJSONString(map, true);
 }
 
 /**
  * 获取json串中的子json
  * 
  * @param json
  * @param path
  * @return
  */
 public static final String getSubJson(String json, String path) {
  String[] keys = path.split(",");
  JSONObject obj = JSON.parseObject(json);
  for (int i = 0; i < keys.length - 1; i++) {
   obj = obj.getJSONObject(keys[i]);
   System.out.println(obj.toJSONString());
  }
  return obj != null ? obj.getString(keys[keys.length - 1]) : null;
 }
 
}
```

> 转自：qq_35860138
>
> 链接：https://blog.csdn.net/qq_35860138/article/details/82967727

- EOF -

推荐阅读  点击标题可跳转

1、[彻底搞懂 Nginx 的五大应用场景](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651497002&idx=1&sn=91aec182332145c333102d09157f8efa&chksm=bd25ce558a524743249e70ea9b454abbcbc02045604ac453f7d48957a6bdc571e6a646a978c1&scene=21#wechat_redirect)

2、[必须了解的 MySQL 三大日志](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651497002&idx=2&sn=487a4c92a6c52d692e6e4d0edaec8ad8&chksm=bd25ce558a524743706f3440395f5cdefa5dc717a439372d5fe7a0b09dcadd7b63805389686d&scene=21#wechat_redirect)

3、[微软再出手，这次要干翻 IDEA 了。。](http://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651496995&idx=1&sn=5aaee96dcde5fd1b6a11181e9b39e55f&chksm=bd25ce5c8a52474a0e99df408a30b474456c611a504c62b4b2b1c9c0252ded03319e05ccc699&scene=21#wechat_redirect)

看完本文有收获？请转发分享给更多人

**关注「ImportNew」，提升 Java 技能**

![](https://mmbiz.qpic.cn/mmbiz_png/2A8tXicCG8ylbWIGfdoDED35IRRySQZTXUkJ1eop9MHApzFibKnOo0diboXpl0rmS5mH78YJhsWQv0dhv718A6kUA/640?wx_fmt=jpeg)

点赞和在看就是最大的支持❤️