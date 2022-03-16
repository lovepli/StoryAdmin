> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [www.cnblogs.com](https://www.cnblogs.com/tu-0718/p/11177236.html)

　　 先来看一个需求：下图 **div** 用 **v-for** 做了列表循环，现在想要 **span** 也一起循环，应该怎么做？

　　![](https://img2018.cnblogs.com/blog/1033257/201907/1033257-20190712164625460-1928620960.png)

 　　**有 3 种方法可以实现**

　　　　**①：直接用 v-for 对 span 也循环一次（该方法虽然可以使用，但不要用这种方式，因为以后你会哭）**

 **![](https://img2018.cnblogs.com/blog/1033257/201907/1033257-20190712165422856-1347852359.png)** 

　　　 **②：在 div 和 span 外面包裹一个 div，给这个 div 加循环（该方法会额外增加一个多余的 div 标签）**

 **![](https://img2018.cnblogs.com/blog/1033257/201907/1033257-20190712170004482-47785674.png)** 

　　　　**③：若你不想额外增加一个 div，此时应该使用 template 来实现（推荐）**

 　　　![](https://img2018.cnblogs.com/blog/1033257/201907/1033257-20190712170550800-1552793217.png)

　　　　 **template 的作用是模板占位符，可帮助我们包裹元素，但在循环过程当中，template 不会被渲染到页面上**

 **![](https://img2018.cnblogs.com/blog/1033257/201907/1033257-20190712170648925-862181331.png)** 

 　　　**DEMO**

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")

```
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>v-for</title>
        <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    </head>
    <body>
        <div>
            <template v-for="(item, index) in list" :key="item.id">
                <div>{{item.text}}--{{index}}</div>
                <span>{{item.text}}</span>
            </template>
        </div>
        
        <script>
            var vm = new Vue({
                el: '#app',
                data: {
                    list: [
                        {
                            id: "010120",
                            text: "How"
                        },
                        {
                            id: "010121",
                            text: "are"
                        },
                        {
                            id: "010122",
                            text: "you"
                        }
                    ]
                }
            })
        </script>
    </body>
</html>
```

[![](http://common.cnblogs.com/images/copycode.gif)](javascript:void(0); "复制代码")