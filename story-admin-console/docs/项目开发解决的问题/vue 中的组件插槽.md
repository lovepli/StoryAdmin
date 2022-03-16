> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/_5AjAQL92tERW4Raj5heTg)

```
// 每日前端夜话 第537 篇
// 正文共：1400 字
// 预计阅读时间：7 分钟
```

![](https://mmbiz.qpic.cn/sz_mmbiz_png/ttJazfuZaRj07jH85T6YrLwS7tOh4gyia6HBYrc823AFn688T4MJQOvzdYRziaqdhQUdatJLnCicGwGSzicWZWxticA/640?wx_fmt=png)

在 Vue 中，插槽（Slots）是组件将内容 “注入” 到子组件的另一种方式。是使用模板代码来完成的。

在最终输出时，插槽的执行与 Vue 中的 prop 类似的功能——从父组件到子组件获取数据。插槽还有助于创建可重用的代码。

但是，尽管 prop 将数据值传递给组件，但是插槽只能传递直接模板代码，这在某些情况下有一些好处：

*   你的子组件的可重用性会更好，可以将其传递给其他组件，而不必担心格式和数据的一致性。
    
*   更加灵活，不必总是填充每个值，而使用 prop 的话则需要用 `v-if` 检查值是否存在.
    
*   纯属个人观点，我认为子组件看起来可读性更好。
    

我认为，将头缠在插槽上的最佳方法是看一个如何使用它们以及实际发生情况的示例。

### 一个非常简单的例子

先从一个典型的例子开始，只需在子组件中声明一个 `slot`，然后用父组件注入内容就行了。

首先设置一个名为 `MyContainer.vue` 的父组件：

```
<template>
   <div>
     <my-button>Click Me!</my-button>
   </div>
</template>
```

然后再设置一个名为 `MyButton.vue` 的子组件。

```
<template>
   <div>
     <slot></slot>
   </div>
</template>
```

在 `MyButton.vue` 渲染完成后，点击 `Click Me!` 按钮替换 `<slot>`，即来自父级的内容。

不仅仅是文本，我们可以从父组件传递**任何类型的模板**，可以是字体、图标、图像甚至是其他组件。

![](http://mmbiz.qpic.cn/mmbiz_png/8EiawFexcoQapWLVrOibhk6g0TnOiacMk0a1rHrj56jaw3NDmmzAZkkKgev6XAessC2XylqB5QEBBO1C0jnX9zEPA/640?wx_fmt=png&wxfrom=200) 交易担保 前端面试星球 每天刷三题，面试不用急～ 小程序

### 多个插槽

要更好的组织基于插槽的组件系统，最好方法是对插槽进行命名。这样可以确保将内容注入到组件的正确部分。

这可以通过在子组件的 `slot` 中添加 **name 属性**来完成。要添加来自父级的内容，只需要再添加另一个 `<template>` 元素，并将名称传给名为 `v-slot` 的属性即可。

看一个实际的例子：

```
// BoxElement.vue
<template>
   <div>
      <slot name='header'></slot>
      <slot name='content'></slot>
   </div>
</template>
```

然后是父组件：

```
<template>
   <div> 
      <box-element>    
         <template v-slot:header>
            This will be injected as the header slot.
         </template>
         <template v-slot:content>
            This will be the content of the element
         </template>
      </box-element>  
   </div>
</template>
```

注意：如果没有对插槽命名，则为 `default` 。

### 传递数据作用域

另外，可以通过赋予父组件 “作用域访问权限” 使其能够访问子组件内部的数据。

例如：如果子组件用数据对象确定其显示的内容，则可以使该数据对父对象可见，并在传递注入的内容时使用。

我们看一个例子：

如果我们在子组件 `Article.vue` 中有这个 header 插槽，那么后备数据就是 `article.title`。

```
<div>
   <slot name='header'>
      {{article.title}}
   </slot>
</div>
```

现在继续父组件。如果想要通过修改内容显示文章的描述该怎么办？我们将无法执行这个操作，因为父组件无权访问其子 `Article.vue` 中的 `article` 对象。

不过 Vue 能够很轻松地处理这种情况。可以通过简单的 `v-bind` 将数据从子插槽绑定到父模板。

看一下修改后的 `div`。

```
<div>
   <slot name='header' v-bind:article='article'>
      {{article.title}}
   </slot>
</div>
```

这样父组件就可以访问这个属性了。现在看看是如何访问它的。

这类似于使用 props 将数据传递到组件时，子组件会传递 **props 对象**，并将所有有界属性作为子对象。

我们要做的就是在模板中命名该对象，然后就可以访问它们。现在将其命名为 `articleInfo`，但这只是一个变量，所以可以使用任何你想要的东西。

```
<div>
   <article v-slot:header='articleInfo'>
      // 现在可以访问 article 对象
      {{ articleInfo.article header }}
   </article>
</div>
```

是不是很简单？

### 用插槽使组件更加灵活

prop 是重用组件的好方法，但是也有其局限性。prop 用在格式和内容相同但值不同的组件中效果最好。

有时我们需要使组件更具灵活性和适应性：也许你希望某些组件具有某些部分，而根据其所在的页面，又希望删除掉其他部分。

通过使用插槽插入内容，可以更轻松地切换组件的内容，而不必担心使用诸如 `v-if` 和 `v-else` 之类的模板逻辑来处理渲染。

公众号

  

* * *

**强力推荐前端面试刷题神器**

![](https://mmbiz.qpic.cn/sz_mmbiz_gif/ttJazfuZaRhicyXjTlZhd1BYzPSicArYzVjZeXNfvbSXIEBKCC5IUq8iacDUbwtooY4Jqz2G6MrRVoqRkXoocO01w/640?wx_fmt=gif)  

[![](https://mmbiz.qpic.cn/sz_mmbiz_png/ttJazfuZaRiaibmffk3MM8pQDGeMgP3avSPSia3K9AFfOhSaa7rADybxYm0MksaiaqsZy1hH4KiaAtMfSwsZBeLBBBg/640?wx_fmt=png)](https://mp.weixin.qq.com/s?__biz=MzI3NzIzMDY0NA==&mid=2247489495&idx=2&sn=c1299551dd5bf4ec8e18ea6880f3a82b&scene=21#wechat_redirect)

精彩文章回顾, 点击直达  

* * *

![](https://mmbiz.qpic.cn/sz_mmbiz_png/ttJazfuZaRiaLn2kDibyZPS4qzOXvOgvu5llnTZUodVhKpd6X19rnKTAiaxBHibyicwRia7XUBJWdgSyNuPuOQQmya2g/640?wx_fmt=png)  

![](https://mmbiz.qpic.cn/sz_mmbiz_png/ttJazfuZaRhwoteJKscOG5NksdJXO1Unl0iaoGCuHnfHS1BQlXCa5FsLHuicQBNiaicLrluClictIQZpCHIAbR2e5Yw/640?wx_fmt=png)
