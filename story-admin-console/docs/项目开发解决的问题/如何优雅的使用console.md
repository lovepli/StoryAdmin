> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/yVY3N14Vr9Fl2Y1twH-VJQ)

作为 Web 开发人员，用`console.log`调试代码应该已经是习以为常的事。但是控制台提供了许多其他方法，可以帮助您更好地进行调试。  

如图所示，`console`还有以下这些方法。

![](https://mmbiz.qpic.cn/mmbiz_jpg/tswsiaEpOia6E9sZ1BaezExc4HTdxeSBBCgzbHeRTYM7kwb8GyZNstEOQaU88aAVIXarCWQlSf1Z87yTQq2kiaTOw/640?wx_fmt=jpeg)  

以上这些方法，可以分为两类：

*   标准的特性
    
*   非标准的特性
    

标准的特性
-----

以下七种方法，可用生产环境。

*   显示信息
    
*   占位符
    
*   分组
    
*   表格
    
*   计时功能
    
*   断言
    

### 一、显示信息

1.  `console.log("这是log")` 输出普通信息
    
2.  `console.info("这是info")` 输出提示性信息
    
3.  `console.warn("这是warn")` 输出警示信息
    
4.  `console.error("这是error")` 输出错误信息
    

控制台显示如下

![](https://mmbiz.qpic.cn/mmbiz_jpg/tswsiaEpOia6E9sZ1BaezExc4HTdxeSBBCLZvkgLHhdqRag5XyCBhms2od0lLCxje4ia9lgGO1FR1mCY2uXkVIf8g/640?wx_fmt=jpeg)  

### 二、占位符

上面 4 种显示信息方法都可以使用`printf`风格的占位符，占位符的种类比较少，只支以下这四种。

*   `%s` 字符串
    
*   `%d` `%i` 整数
    
*   `%f` 浮点数
    
*   `%o` 对象
    

**示例**

```
console.log('this color is %s', "red");
console.log("%d年%d月%i日",2021,5,25)
console.log("圆周率是%f",3.1415926)
let dog = { name: "小白", color: "white" }
console.log("%o",dog);
```

控制台显示如下

![](https://mmbiz.qpic.cn/mmbiz_jpg/tswsiaEpOia6E9sZ1BaezExc4HTdxeSBBCKjIBPkziasmO2wibDOsqSs12picl3Owy71rkSqN5YXzwbedzoH3jUpLsw/640?wx_fmt=jpeg)  

### 三、分组

如果信息太多，可以将其分组显示。

```
console.group('用户信息');
console.log('名字: 李白');
console.log('工作: Acmen');
// 嵌套
console.group('地址');
console.log('福建省');
console.log('厦门市');
console.log('思明区');
console.groupEnd();
console.groupEnd();
```

![](https://mmbiz.qpic.cn/mmbiz_jpg/tswsiaEpOia6E9sZ1BaezExc4HTdxeSBBCAdFudh0ojiaLkM2sEbEGG3nfzA75R2nWMmgwBkHb4KCaicr0DfHZiaEUw/640?wx_fmt=jpeg)  

如果要默认折叠组，也可以使用`console.groupCollapsed()`代替`console.group()`

### 四、表格

将数据以表格的形式显示。

```
console.table([
    { id: 12987122, name: '蛋糕', time: '2016-05-02' },
    { id: 12987125, name: '冰淇淋', time: '2016-05-09' }
])
```

![](https://mmbiz.qpic.cn/mmbiz_jpg/tswsiaEpOia6E9sZ1BaezExc4HTdxeSBBC65VjgJOrHJaqRJctemWyy6qxoRuLKenI0icLaII7DuMTdnicgednDWnw/640?wx_fmt=jpeg)  

### 五、计时功能

`console.time()`和`console.timeEnd()`，用来运行某串代码的需要花费的时间。

![](https://mmbiz.qpic.cn/mmbiz_jpg/tswsiaEpOia6E9sZ1BaezExc4HTdxeSBBCs2Q7ibQDFoSrricKgDMoz1qf1ohjyUSbMh8Ybib8t4sCbC6w0g0HOmXsA/640?wx_fmt=jpeg)  

### 六、断言

`console.assert()`用来判断一个表达式或变量是否为真。如果结果为否，则在控制台输出一条相应信息，并且抛出一个异常。

![](https://mmbiz.qpic.cn/mmbiz_jpg/tswsiaEpOia6E9sZ1BaezExc4HTdxeSBBCFr7bQ7pWLtskPFRLXJDQuBR1o9r9FlKbqic1ZG2bmT3zjJib4CBovfIg/640?wx_fmt=jpeg)  

非标准的特性
------

该特性是非标准的，请尽量不要在生产环境中使用它！

### 一、性能分析

分析程序各个部分的运行时间，找出问题所在。

假设有一个函数`Foo()`，里面调用了另外两个函数`funcA()`和`funcB()`，其中`funcA()`调用 10 次，`funcB()`调用 1 次。

```
function Foo() {
  for (let i = 0; i < 10; i++) {
    funcA(1000)
  }
  funcB(10000)
}

function funcA(count) {
  for (let i = 0; i < count; i++) {}
}

function funcB(count) {
  for (let i = 0; i < count; i++) {}
}
```

![](https://mmbiz.qpic.cn/mmbiz_jpg/tswsiaEpOia6E9sZ1BaezExc4HTdxeSBBCKK0nVhBko0hlib8vsehsEr6u1yticg4AKcTM8ickjicAgx1G4G0s6qsh9A/640?wx_fmt=jpeg)  

新的浏览器这个方法已无效。

![](https://mmbiz.qpic.cn/mmbiz_jpg/tswsiaEpOia6E9sZ1BaezExc4HTdxeSBBChFCHJCzic1BGU2aQUnicWy7vXCSw3YzibztRTiblFChpry75ngCzY1Stuw/640?wx_fmt=jpeg)  

### 二、 console.dir()

`console.dir()`可以显示一个对象所有的属性和方法。

![](https://mmbiz.qpic.cn/mmbiz_jpg/tswsiaEpOia6E9sZ1BaezExc4HTdxeSBBCAAIjt87e3pmicnlD5TqGllrclibB2czt1Yvu61XzHQMH0PSzibjMG3oJg/640?wx_fmt=jpeg)  

### 三、计数器

`console.count()`用于计数，输出它被调用了多少次。

![](https://mmbiz.qpic.cn/mmbiz_jpg/tswsiaEpOia6E9sZ1BaezExc4HTdxeSBBCOL7nVW5nPibTw2TsGpMD1I6aOdcgSt6aiaK5OIWJ4mXdvIWHKXPolmaQ/640?wx_fmt=jpeg)  

### 参考文献

*   MDN console
    
*   console 的正确打开方式
    
*   Chrome DevTools 之 console 篇和命令菜单篇
    
*   How to use the JavaScript console: going beyond console.log()
