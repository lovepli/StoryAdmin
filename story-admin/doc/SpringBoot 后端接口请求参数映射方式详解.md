> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/TSZrtwMvCnjF5UMvILPDOA)

在 SpringBoot 项目中，前端 HTTP 请求中的参数如何映射到 Controller 层的接口方法中的参数？这里针对各种方式做一个测试与总结，测试采用的 SpringBoot 版本号为`2.2.10.RELEASE`

QueryString 方式
==============

`QueryString`参数传递的方式为，在请求 URL 中直接拼接请求参数，如`URL?param1=value1&param2=value2`

> `QueryString`参数传递方式对于请求方法`GET`、`POST`、`PUT`、`PATCH`、`DELETE`都适用

1. 映射基本类型参数
-----------

可以在 Controller 的接口中声明基本类型的参数，然后用`@RequestParam`注解修饰，指定前端传递的参数名称

```
@RestController
@RequestMapping("param")
@Slf4j
public class RequestParamTestController {
    @RequestMapping("queryString1")
    public void testQueryString1(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        log.info("name:{}   age:{}", name, age);
    }
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYq1se16bVfXCn1rd9ibLSkQYYmuruevYomIMqWmQsca9icUR0KlnwdwTg/640?wx_fmt=png)

> *   如果请求参数名称和 Controller 中接口方法的参数名称一致，那么可以省略`@RequestParam`注解。不过一般还是建议加上
>
> *   当请求参数名称和 Controller 中接口方法的参数名称一致时，加与不加`@RequestParam`的区别是：
>
>
> *   加上`@RequestParam`，其属性`required`默认为 true，那么当前端不传递对应的参数时将会抛出异常
      >
      >     ![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYILQusnzgXumpPQ8zSJZdiaSibXhuXic0cqvMTbjpzWFicWrUCM4FuDQ29Q/640?wx_fmt=png)
>
> *   不加`@RequestParam`，前端可以不传递对应参数，此时 Controller 接口方法中的参数接收到的值为 null
      >
      >     ![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYnCzicicDwibeMxiag50fgF7YadgiamJ9hj6GfvWa1xhjDJRV6MUU16ia1y9w/640?wx_fmt=png)
>

2. 映射对象类型参数
-----------

定义一个对象，属性名称和前端传递的参数名称一致即可

```
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private Integer age;
}
```

然后将 Controller 接口方法中的参数声明为自定义对象

```
@RequestMapping("queryString2")
public void testQueryString2(User user) {
    log.info("name:{}   age:{}", user.getName(), user.getAge());
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYgtzwbd0ESib0oFK24E6vl8cjFZGUr05m5FL4Rzg5wRDRss5ibOfSqXZw/640?wx_fmt=png)

3. 映射数组、集合类型参数
--------------

前端有 2 种方式针对同一个参数传递多个值：

*   在请求的 QueryString 中，拼接多个参数名称一样的参数即可，如`URL?param=value1&param=value2&param=value3`

*   在请求的 QueryString 中，对同一个参数赋多个值，多个值之间用`,`隔开，如`URL?param=value1,value2,value3`


### 3.1 映射数组

在 Controller 接口方法中声明数组类型参数，用`@RequestParam`指明前端传递的参数名称即可

```
@RequestMapping("queryString3")
public void testQueryString3(@RequestParam("name") String[] nameArray) {
    if (nameArray != null) {
        for (String name : nameArray) {
            log.info(name);
        }
    }
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYFMZ3BfuCzDkcbQyCsNvfoUZrzjabE9iaHibENJOvmVHOQicmX2gJQO9Kg/640?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYgekXEF7mxL9pk7IkrwLhNoc2pziakQCE09eIq8NxMra73QIxnCZZh5w/640?wx_fmt=png)

> 如果请求参数名称和 Controller 中接口方法的参数名称一致，那么可以省略`@RequestParam`注解。不过一般还是建议加上

### 3.2 映射 Collection

在 Controller 接口方法中声明`Collection`类型参数，用`@RequestParam`指明前端传递的参数名称即可

```
@RequestMapping("queryString4")
public void testQueryString4(@RequestParam("name") Collection<String> nameCollection) {
    if (nameCollection != null) {
        log.info("类型：{}", nameCollection.getClass());
        for (String name : nameCollection) {
            log.info(name);
        }
    }
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYw2TeWweyCKb3BpyJoDWyx17RCTE5gxxxPVKXaRuObSWJrOayCV69ibw/640?wx_fmt=png)

可以发现框架默认采用的实现类是`LinkedHashSet`。那我们继续测试传递相同 value 的时候是否也会采用这个实现

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYFwWnJkJhU1JfOaCP7kSD0Z9nolRoyWWjQTsuhn6ZjWzw1HPH7STU1Q/640?wx_fmt=png)

可以发现传递多个相同 value 的时候框架采用的实现类还是`LinkedHashSet`，所以有去重的效果

### 3.3 映射 List

在 Controller 接口方法中声明`List`类型参数，用`@RequestParam`指明前端传递的参数名称即可

```
@RequestMapping("queryString5")
public void testQueryString5(@RequestParam("name") List<String> nameList) {
    if (nameList != null) {
        log.info("类型：{}", nameList.getClass());
        for (String name : nameList) {
            log.info(name);
        }
    }
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYzMKZDp0CKUox6J9YP2OWkXfaMXSZZIpJibYrlzfTlWibUjv1icbXG72KQ/640?wx_fmt=png)

可以发现框架默认采用的实现类是`ArrayList`

### 3.4 映射 Set

在 Controller 接口方法中声明`Set`类型参数，用`@RequestParam`指明前端传递的参数名称即可

```
@RequestMapping("queryString6")
public void testQueryString6(@RequestParam("name") Set<String> nameSet) {
    if (nameSet != null) {
        log.info("类型：{}", nameSet.getClass());
        for (String name : nameSet) {
            log.info(name);
        }
    }
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYc2oZ9mTFhg4hRAGUfuANC7LiaHgGEtoj7EPrL4EBAz7wpaKYybng7Qg/640?wx_fmt=png)

可以发现框架默认采用的实现类是`LinkedHashSet`，与声明为`Collection`类型时一致

> 注意：
>
> *   也可以在自定义对象中将属性声明为数组、集合类型，来接收多个值
>
>
> ![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYSibC8ju4P6g5HhwRVAGE1tnFzNxHksSBnZiczqRTR1L4yic8eALuYrjGw/640?wx_fmt=png)
>
> *   对于映射`Collection`、`List`和`Set`类型参数时，即便前端提交的 QueryString 中的参数名称与 Controller 接口方法的参数名称一致，也不能省略`@RequestParam`注解，否则会抛出如下异常
      >
      >     ![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYtB3B2ooVJqwC6KRBoeTfhzL4E817EUjJI3EATaNwmrfhYUTAO4etiaQ/640?wx_fmt=png)
>

路径参数方式
======

路径传参方式是将参数直接包含在 URL 路径中，比如`URL/paramValue1/paramValue2`

> 路径参数方式对于请求方法`GET`、`POST`、`PUT`、`PATCH`、`DELETE`都适用

1. 映射基本类型参数
-----------

在 Controller 中用如下步骤接收参数：

1.  在接口对应的请求路径中用`{参数名}`形式标出路径参数

2.  在接口方法的参数上标注`@PathVariable`指名对应路径参数的参数名


```
@RequestMapping("path1/{name}/{age}")
public void testPath1(@PathVariable("name") String name, @PathVariable("age") Integer age) {
    log.info("name:{}   age:{}", name, age);
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYthdiaV7EXgAI0Bfly6cnuzkCbypUK753ibl4vcIH2sNutiaf89WROxnJA/640?wx_fmt=png)

> 如果接口方法上`@RequestMapping`中路径参数的参数名称和接口方法的参数名称相同，可以省略`@PathVariable`注解。但一般推荐加上

2. 映射数组、集合类型参数
--------------

直接给路径参数多个值，用`,`隔开即可

### 2.1 映射数组

在 Controller 接口方法中声明数组类型参数，用`@PathVariable`指明路径参数的名称即可

```
@RequestMapping("path2/{name}/{age}")
public void testPath2(@PathVariable("name") String[] nameArray, @PathVariable("age") Integer[] ageArray) {
    for (String name : nameArray) {
        log.info(name);
    }
    for (Integer age : ageArray) {
        log.info(age.toString());
    }
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdY5BEmFtVWgJ0hS23UjPhJQU5SvaqNxNtZl755icTrpg0BKEBd0OdfO4A/640?wx_fmt=png)

### 2.2 映射 Collection

在 Controller 接口方法中声明`Collection`类型参数，用`@PathVariable`指明路径参数的名称即可

```
@RequestMapping("path3/{name}/{age}")
public void testPath3(@PathVariable("name") Collection<String> nameCollection, @PathVariable("age") Collection<Integer> ageCollection) {
    log.info("类型：{}", nameCollection.getClass());
    for (String name : nameCollection) {
        log.info(name);
    }
    for (Integer age : ageCollection) {
        log.info(age.toString());
    }
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYUX3e7xP1OjHpjU2UN5Iy2qqYf31dfOtGby79O0Cg3jmPA0ppJulAlA/640?wx_fmt=png)

可以发现框架默认采用的类型是`LinkedHashSet`

### 2.3 映射 List

在 Controller 接口方法中声明`List`类型参数，用`@PathVariable`指明路径参数的名称即可

```
@RequestMapping("path4/{name}/{age}")
public void testPath4(@PathVariable("name") List<String> nameList, @PathVariable("age") List<Integer> ageList) {
    log.info("类型：{}", nameList.getClass());
    for (String name : nameList) {
        log.info(name);
    }
    for (Integer age : ageList) {
        log.info(age.toString());
    }
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYXOzic3VVDhicdjOQHmCSSmqZq6ofibYlbb9LsYO2K0qRxm18zS30P3mxQ/640?wx_fmt=png)

框架采用的实现类是`ArrayList`

### 2.4 映射 Set

在 Controller 接口方法中声明`Set`类型参数，用`@PathVariable`指明路径参数的名称即可

```
@RequestMapping("path5/{name}/{age}")
public void testPath5(@PathVariable("name") Set<String> nameSet, @PathVariable("age") Set<Integer> ageSet) {
    log.info("类型：{}", nameSet.getClass());
    for (String name : nameSet) {
        log.info(name);
    }
    for (Integer age : ageSet) {
        log.info(age.toString());
    }
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdY5ry2jjLGrNCtzdz6bG8kIh7miceKB4ZdOPooRibzbgN5rrqYnDLG8CBw/640?wx_fmt=png)

框架采用的实现类是`LinkedHashSet`

> 路径参数方式映射到 Controller 接口方法中的`数组`、`Collection`、`List`和`Set`类型参数时，即便路径中的参数名称和接口方法中的参数名称一致，也不能省略`@PathVariable`注解

表单参数方式
======

表单参数方式要求在请求头中携带`Content-Type`，值为`application/x-www-form-urlencoded`，并且请求体中以`param:value`形式携带参数，每行代表一个参数，多个参数就有多行

> 注意：表单参数方式不适用于`GET`请求，适用于`POST`、`PUT`、`PATCH`、`DELETE`

1. 映射基本类型参数
-----------

可以在 Controller 的接口中声明基本类型的参数，然后用`@RequestParam`注解修饰，指定请求体中的参数名称

```
@RequestMapping("form1")
public void testForm1(@RequestParam("name") String name, @RequestParam("age") Integer age) {
    log.info("name:{}   age:{}", name, age);
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYofr7rY8TMeKk0LXwd30lhSt7TgibWL0F2jcqEuNuqsJyrNRgJZyRZzQ/640?wx_fmt=png)

2. 映射对象类型参数
-----------

定义一个对象，属性名称和请求体中的参数名称一致即可

```
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private Integer age;
}
```

然后将 Controller 接口方法中的参数声明为自定义对象

```
@RequestMapping("form2")
public void testForm2(User user) {
    log.info("name:{}   age:{}", user.getName(), user.getAge());
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYvgZWDpo2JlmkQnebia1sBqLoHEwJQddRTMbMFLNgiaV5IKFJJcibVFmdg/640?wx_fmt=png)

3. 映射数组、集合类型参数
--------------

请求体中的参数如果想要传递多个值，有 2 种方式：

*   直接给参数多个值，用`,`隔开即可

*   声明多个参数名一样的参数


### 3.1 映射数组

在 Controller 接口方法中声明数组类型参数，用`@RequestParam`指明请求体中的参数名称即可

```
@RequestMapping("form3")
public void testForm3(@RequestParam("name") String[] nameArray) {
    for (String name : nameArray) {
        log.info(name);
    }
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYickib6pYpKJ8qBLhCcNZr59tibmNShJcbrfsQ5KdGufJA6zSGAda2Iwmg/640?wx_fmt=png)

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYpcG6NoKl5dsNiaspzrVnQuxUicnXicR9DZgBz9TXlnM99mkdibaAHianGng/640?wx_fmt=png)

### 3.2 映射 Collection

在 Controller 接口方法中声明`Collection`类型参数，用`@RequestParam`指明请求体中的参数名称即可

```
@RequestMapping("form4")
public void testForm4(@RequestParam("name") Collection<String> nameCollection) {
    log.info("类型：{}", nameCollection.getClass());
    for (String name : nameCollection) {
        log.info(name);
    }
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYPicLWvMKTS2md77v93kiaaUibXrjbTSQDcCiaPp2pUdxJYhc8w85A6Os1A/640?wx_fmt=png)

### 3.3 映射 List

在 Controller 接口方法中声明`List`类型参数，用`@RequestParam`指明请求体中的参数名称即可

```
@RequestMapping("form5")
public void testForm5(@RequestParam("nameList") List<String> nameList) {
    log.info("类型：{}", nameList.getClass());
    for (String name : nameList) {
        log.info(name);
    }
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYtiacV9LxEmNoJiadLG3kceHMrCQA8PhsXAheXActyWqgQfibMapjgfAWg/640?wx_fmt=png)

### 3.4 映射 Set

在 Controller 接口方法中声明`Set`类型参数，用`@RequestParam`指明请求体中的参数名称即可

```
@RequestMapping("form6")
public void testForm6(@RequestParam("name") Set<String> nameSet) {
    log.info("类型：{}", nameSet.getClass());
    for (String name : nameSet) {
        log.info(name);
    }
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdY3YvzVRc42uzL5PGrib48w94odqjTicqBBc2qoeVa6GABuozKkcbCoh7g/640?wx_fmt=png)

> 也可以在自定义对象中将属性声明为数组、集合类型，来接收多个值

FormData 方式
===========

用于文件上传。要求在请求头中携带`Content-Type`，值为`multipart/form-data`。请求体中可以携带普通参数，也可以携带文件

> Postman 测试发现这种方式适用于`GET`、`POST`、`PUT`、`PATCH`、`DELETE`，但浏览器一般只能用`POST`表单提交进行文件上传，所以建议用`POST`请求

可以在 Controller 的接口中分别声明普通类型参数和文件类型参数，其中文件类型参数必须是`MultipartFile`类型，然后用`@RequestParam`注解修饰，指定请求体中对应的参数名称

```
@RequestMapping("formData1")
public void testFormData1(@RequestParam("fileName") String fileName, @RequestParam("file")MultipartFile file) {
    log.info("{}:{}", fileName, file.getSize());
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYAAVwicMtx0jQ9Cc9l0dO91xFXO09AAWgMibBZQ5BHhibGAiarMduefibVzg/640?wx_fmt=png)

> FormData 方式中的普通参数 (即非文件类型参数) 同样也可以利用数组、集合类型接收多个值，可以封装为自定义对象，方法与表单参数方式类似

请求体 json 方式
===========

要求请求头携带`Content-Type`，值为`application/json`。请求体中的内容为 json 格式

在 Controller 的接口方法映射这种请求参数只能通过自定义对象，自定义对象的属性名称要与请求体中 json 的属性名称一致，然后将自定义对象作为接口方法的参数，并标注`@RequestBody`注解，框架会自动将请求体的 json 转换为自定义对象

```
@RequestMapping("json1")
public void testJson1(@RequestBody User user) {
    log.info(user.toString());
}
```

测试结果如下

![](https://mmbiz.qpic.cn/mmbiz_png/LWw2xqL7JsqWcJ0a4sETwGRp4XRWFhdYnXhycmMwvfFnTh6xGh1mic9uEnic8VAicSHJRNqKJuib3UfcianGGBpVsdQ/640?wx_fmt=png)

> 经过 PostMan 测试发现，用`GET`方法发送这种请求也可以顺利接收到参数，但是一般浏览器不支持`GET`请求携带请求体数据，所以还是建议实际开发中只针对`POST`、`PUT`、`PATCH`、`DELETE`采用这种方式进行参数映射