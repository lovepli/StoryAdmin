> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/Tkjg7t5KNHWuMjrbNX9lqw)

![](https://mmbiz.qpic.cn/mmbiz_jpg/1NOXMW586uvXeWAGmicttGFU7RNTAnysnoCMYWcWoHL3CqpWn8920QIfSM6ia5WRGwv7Id1nlhG4VZXjnQpkIUiag/640?wx_fmt=jpeg)

作者：EasyMoment23  

juejin.cn/post/6966495817792389150

能让你首次加载更快的路由懒加载，怎么能忘？
=====================

路由懒加载可以让我们的包不需要一次把所有的页面的加载进来，只加载当前页面的路由组件就行。

举个🌰，如果这样写，加载的时候会全部都加载进来。

```
const router = new VueRouter({
  routes:[
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/about',
      name: 'About',
      component: About
    }
  ]
})
```

所以，应该避免上面的写法，尽量使用懒加载

懒加载写法, 结合 webpack 的 import 食用

```
const router = new VueRouter({
  routes:[
    {
      path: '/',
      name: 'Home',
      component: () => import(/* webpackChunkName: "home" */ '../views/Home.vue')
    },
    {
      path: '/about',
      name: 'About',
      component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    }
  ]
})
```

你是否还记得有一个叫 Object.freeze 的方法？
=============================

应该所有同学都知道，vue 初始化的时候会将 data 里面的数据都搞成响应式数据吧。但是，我们在写业务逻辑的时候会有些数据一初始化就永远不会改变，它根本就不需要被 vue 做成响应式数据，因此我们应该将这些不用改变的数据通过 Object.freeze 方法冻结它，避免 vue 初始化的时候，做一些无用的操作。

🌰

```
export default {
  data:()=>({
    list:Object.freeze([{title:'我永远不需要改变，我不需要响应式'}])
  })
}
```

异步组件那么强，你是不是没用过？
================

异步组件可以让我们在需要一些组件时才将它加载进来，而不是一初始化就加载进来，这跟路由懒加载时一个概念。

🌰

```
export default {
  components:{
    AsyncComponent:()=>import(/* webpackChunkName: "AsyncComponent" */ './Async')
  }
}
```

异步组件还有一种比较完善的写法

🌰

```
export default {
  components:{
    AsyncComponent:()=>({
      component:import(/* webpackChunkName: "AsyncComponent" */ './Async'),
      delay:200, // 延迟几毫秒，默认200
      timeout:3000, // 加载几毫米之后就超时，触发error组件
      loading:LoadingComponent, // 组件未加载回来前显示
      error:ErrorComponent // 组件超时时显示
    })
  }
}
```

你是不是还在 computed 中使用 this？
=========================

我猜还有很多同学，在 computed 属性中通过 this.xxx 去拿 data 里面的数据，和 methods 里面的方法吧，或许还会通过 this.route 去获取路由里面的数据吧。其实，我们可以避免这些丑陋的 this, 它甚至会给我们带来看不见的性能问题。实现上，我们通过 this 能访问到的数据，在 computed 的第一个参数上都能结构出来。

🌰

```
export default {
   haha({$attrs,$route,$store,$listeners,$ref}){
     // 还能结构很多属性，可自行打印康康
     return 
   }
}
```

如何避免 v-if 和 v-for 一起使用？
=======================

为什么要避免 v-if 和 v-for 在同一个元素上同时使用呢？因为在 vue 的源码中有一段代码时对指令的优先级的处理，这段代码是先处理 v-for 再处理 v-if 的。所以如果我们在同一层中一起使用两个指令，会出现一些不必要的性能问题，比如这个列表有一百条数据，再某种情况下，它们都不需要显示，当 vue 还是会循环这个 100 条数据显示，再去判断 v-if，因此，我们应该避免这种情况的出现。

不好的🌰

```
<h3 v-if="status" v-for="item in 100" :key="item">{{item}}</h3>
```

好的🌰

```
<template v-if="status" >
        <h3 v-for="item in 100" :key="item">{{item}}</h3>
    </template>
```

那么强的. sync 修饰符你为什么不用?
=====================

如果你想要在父组件控制一个子组件的显示隐藏，是不是还在传一个 prop 和一个自定义方法，这样会很麻烦，不妨试一试 sync 修饰符。

🌰

```
// 父组件
 
 template>
  <div>
    <Toggle :show.sync = 'show'></Toggle>
  </div>
</template>

//Toggle 组件

<template>
  <div>
    <div v-if="show">
    展示和隐藏组件
  </div>
  <button @click="test">隐藏组件</button>
  </div>
</template>
<script>

export default {
  props:['show'],
  methods: {
    test(){
      this.$emit('update:show',false)
    }
  }
}
</script>
```

和 listeners 让你封装组件如鱼得水！
=======================

和 listeners 可能很多同学没怎么去使用，其实它们让我们对一些组件库的组件二次封装，非常好用的。

简单介绍一下它们两个：

attr 里面。

listeners 里面。

这里举一个对 ElementUI 的 Tabel 组件简单的二次封装的🌰

```
<el-table 
   v-bind="$attrs"
   v-on="$listeners">
   <template v-for="item in column">
    <el-table-column v-bind="item" />
   </template>
</el-table>
<script>
export default {
  props:{
      column:{
        type:Array,
        required:true
      }
   }
}
<script>
```

v-model 还有这么好的修饰符！
==================

v-model 上有 3 个比较好用的修饰符不知到大家有没有用过，一个是 lazy, 一个是 number, 一个是 trim。

lazy: 可以将 @input 事件变成 @blur 事件

number：只能输入数字值

trim: 清空两边的空格

🌰

```
//lazy
   <input v-model.lazy="msg" />
   //number
   <input v-model.number="msg" />
   //trim
   <input v-model.trim="msg" />
```

你是否知道 v-model 还能自定义属性？
======================

如果想在一个自定义的 Input 组件上使用 v-model，那么就要在子组件，介绍一个 value，和触发 input 事件，v-model 的默认语法糖就是这两个东西的组合。

🌰

```
// 父组件
<template>
  <div>
    <CustomInput v-model='msg' />
  </div>
</template>

//CustomInput

<template>
  <div>
    <input type="text" :value="value" @input="test">
  </div>
</template>
<script>
export default {
  props:['value'],
  methods: {
    test(e){
      this.$emit('input',e.target.value)
    }
  },
}
</script>
```

但是，如果组件里面不是 input, 而是一个 checkbox 或者一个 radio 呢? 我不想接受一个 value 和 input 事件，我想接收一个更加语义化的 checked 和 change 事件，那该怎么办？

🌰

```
// 父组件不需改变
...
//CustomInput
<template>
  <div>
    <input type="checkbox" :checked="checked" @change="test">
  </div>
</template>
<script>
 props:['checked'],
 model:{
    props:'checked',
    event:'change'
  },
  methods: {
    test(e){
      this.$emit('change',e.target.checked)
    }
  }
}
</script>
```

你还在用浏览器的 scrollTop 滚动你的页面吗？
===========================

有些时候我们在操作一下页面的滚动行为，那么我们第一时间就会想到 scrollTop。其实我们还有第二个选择就是 VueRouter 给我们提供的 scrollBehavior 钩子。

🌰

```
const router = new VueRouter({
  routes:[...] ,
  scrollBehavior(to,from,position){
      // position参数可自行打印康康，点击浏览器左右箭头会触发
      return{
          // 这里可以返回很多参数，下面简单列就几个，详情自己康康官网
          x:100,
          y:100,
          selector:#app,
          offset:200,
          //等等
      }
  }
})
```

你在子组件上定义的原生事件不生效？
=================

有时候我们想在子组件上面监听一些事件，比如 click，但是不论你怎么点，它都没反应，为什么呢？

🌰

```
<template>
    <div>
        <Child @click="test"></Child>
    </div>
</template>
<script>
    methods:{
        test(){}
    }
</script>
```

因为这样写 vue 会认为，你自定义了一个 click 事件，要在子组件通过 $emit('click') 触发才行。如果我就是要在父组件触发呢？那就要用到 native 修饰符了。

🌰

```
<template>
    <div>
        <Child @click.native="test"></Child>
    </div>
</template>
<script>
    methods:{
        test(){}
    }
</script>
```

用 keep-alive 缓存一下你的页面状态吧！
=========================

keep-alive 可以帮助我们在切换组件的时候，保留上一个组件不被销毁，它在管理后台系统中比较常用。

🌰

```
<keep-alive>
    <router-view></router-view>
</keep-alive>
```

**推荐阅读：**

```
大文件上传如何做断点续传

微信：5 月  20 日后不再提供小程序打开 App 服务


一文搞懂单点登录三种情况的实现方式

终于有人把 Nginx 说清楚了，图文详解！


推荐 130 个令你眼前一亮的网站，总有一个用得着

深入浅出 33 道 Vue 99% 出镜率的面试题




VUE中文社区 

编程技巧 · 行业秘闻 · 技术动向
```

![](https://mmbiz.qpic.cn/mmbiz_jpg/1NOXMW586utOUfibtYibJdGGY4H4QciaQ8DaVztiaP4aUmmfOjK4xJbJ1DqOuI3P3IWYvFy7Mtiaib8gnHujDCquadAA/640?wx_fmt=jpeg)