import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui' // 引入element-ui
import './styles/element-variables.scss'
// import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n

import '@/styles/index.scss' // 全局自定义的css样式

import App from './App'
import router from './router' // 全局引入路由
import store from './store' // 全局引入本地存储store,中央存储,store是vuex的核心

import '@/icons' // icon图标
import '@/permission' // permission control权限控制
import authority from './utils/authority'
import { hasPermission } from './utils/hasPermission';
import './utils/constant.js'
import LunarFullCalendar from 'vue-lunar-full-calendar'
import { getById, download } from './utils/common'

// 通过全局方法 Vue.use() 使用插件。它需要在你调用 new Vue() 启动应用之前完成
Vue.use(LunarFullCalendar)

Vue.use(ElementUI, { locale })

// 全局的常量
Vue.prototype.hasPerm = hasPermission
Vue.use(authority);
Vue.config.productionTip = false

// 全局方法挂载
Vue.prototype.$getById = getById
Vue.prototype.download = download

// 创建根vue实例，每个vue组件，都是一个vue实例
// 创建Vue实例，这个实例其实就是MVVM中的vm调度者
new Vue({
  el: '#app', // element的简写，表示我们当前new的这个Vue实例的区域
  router, // 路由分发处理（为了保证路由模块的职能单一，router.js只负责分发路由，不负责具体的好业务逻辑处理）
  // 为了在 Vue 组件中访问 this.$store property，你需要为 Vue 实例提供创建好的 store。Vuex 提供了一个从根组件向所有子组件，以 store 选项的方式“注入”该 store 的机制：
  // 通过在根实例中注册 store 选项，该 store 实例会注入到根组件下的所有子组件中，且子组件能通过 this.$store 访问到
  store, // 这样就能全局使用vuex了,Vuex是一个专为Vue.js 应用程序开发的状态管理模式。主要方便用于状态记录以及各组件通信。
  render: h => h(App) // 渲染函数render, 运行时构建
})

// VUE基础语法复习：https://docs.tumo.tycoding.cn/#/docs/vue/vue-2
// 后端的MVC和前端的MVVM之间的区别:
// MVC是后端的分层开发概念
// MVVM是前端视图层的概念，主要关注于：视图层分离；也就是说：MVV将前端分为三个部分Model、View、VM（ViewModel）
// 1、Model： 页面需要展示的数据(这里的M保存的是每个页面中单独的数据)
// 2、View: 视图、HTML(就是每个页面中的HML结构)
// 3、VM: 数据（Model）和视图（View）之间的调度者(他是一个调度者，分割了M和V，每当v层想要获取后保存数据的时候，都要由vm做中间的处理)

// 基础语法：
// v-bind:
// 被v-bind:绑定的属性，其元素不再是字符串，而是被识别为Vue的绑定的变量（同样这个变量必须声明了）。另外v-bind:还有一个简易写法：:
// v-bind:value="msg" 可以简写为 :value="msg"

// v-on:
// Vue提供了事件绑定机制的指令：v-on:；用其我们可以用来绑定一些常见的触发事件：click、mouseover … 另外v-on:还有一个简易写法：@
// v-on:click="show" 可以简写为 @click="show"

// v-model
// 唯一的双向绑定指令：v-model
// 单向绑定指令：v-bing

// v-for
// Vue提供了遍历集合、数组的指令：v-for；用法: v-for="别名 in 集合名"
// 注意：
// 在vue2.0+版本里，当使用v-for渲染数据，必须制定对应的key值（这里的key是一个属性，不是前面迭代的key值）
// 用法:
// <p v-for="item in user" :key="item.id">
// 其中 :key 就说明了key属性必须是通过v-bind绑定的元素，而 :key="" 中指定的值必须是string/number类型的值，比如此处使用的是item.id中ID是number值，并且是唯一的。
// 目的：
//  避免迭代元素时，为循环元素绑定的是列表中的第几个元素（指定位置），而不是指定的某个元素（指定身份）。

// v-show和v-if
// Vue提供了两个指令来实现元素显示状态的切换：v-if v-show
// 区别:
// 1、v-if的特点：每次都会重新删除和创建元素，具有较高的切换性能消耗（因为每次执行都要进行删除和创建元素）。
// 2、v-show的特点：每次不会重建进行DOM的删除和创建操作，只是切换了元素的display:none样式，具有较高的初始渲染消耗（即每次都只是将元素隐藏了，并没有真正的删除掉）

// 事件修饰符
// .stop 阻止冒泡
// .prevent 阻止默认事件
// .capture 添加时间侦听器时使用时间捕获模式
// .self 只当事件在该元素本身（比如不是子元素）触发时触发回调
// .once 事件只触发一次

// 外联样式
// 1、数组 <h2 :class="['italic','color']">涂陌</h2> 其中的italic、color是自定义的类名，需在外部定义CSS样式
// 2、数组中嵌套对象 <h2 :class="['italic',{'color': flag}]">涂陌</h2> 其中的flag是Vue绑定的变量，在data进行声明
// 3、直接使用对象 <h2 :class="{italic:true, color:flag}">涂陌</h2>

// 内联样式
// 将样式对象定义到data中，并在:style中引用
// <h2 :style="styleObj">涂陌</h2>
// data: { styleObj: { ‘color’: ‘red’, ‘font-weight’: ‘200px’} }

// 过滤器
// Vue.js允许你自定义过滤器，可被用作一些常见元素的格式化。过滤器可以用在两个地方：mustache插值{{ val }}和v-bind表达式,通过管道符(|)来连接。
// 写法:
// {{ 过滤器名称 | function }}
// 定义:
// Vue提供了两种方式创建过滤器：
// 1、全局过滤器
// Vue.filter('过滤器名称', function(){}) ,Vue提供的全局过滤器，直接使用Vue调用，而不是定义在Vue实例中
// 2、私有过滤器, 私有过滤器和全局过滤器用法基本相同，仅仅是作用于不同而已。
// new Vue()({
//   el: '',
//   data: {},
//   methods: {},
//   filters: {
//       过滤器名称: function(){}
//   }
// })

// 自定义指令
// 按键修饰符:
// 在我们搜索商品时，在一些网站中我们直接回车后立即进行搜索，而不是点击搜索按钮才会搜索，那么这个功能怎么实现呢？
// 那么我们就需要了解Vue中提供的按键修饰符 用法： @keyup.按键别名 = "要调用的方法名"
// 按键别名有 .enter .tab .esc .delete …
// 自定义按键修饰符:
// 如果Vue提供的按键修饰符不能满足你的需求，你也可以使用Vue提供的自定义按键修饰符来实现，因为每个键盘的按键都对应了一个键盘码值，比如F2对应的键盘码值是：113

// 获取文本焦点
// 获取文本焦点使用了focus属性，那么我们需要定义一个v-focus指令
// Vue.directive('focus', {
//   bind: function(el) {},
//   inserted: function(el) {},
//   updated: function(el) {}
// });
// 如上，使用Vue.directive()实现定义全局指令，需要注意以下几点：
// 1、在directive()方法中包含两个参数：
//  - 参数1：指令的名称，注意，在定义的时候指令名称不需要加v-前缀，但是在使用的时候需要加v-前缀。
//  - 参数2：是一个对象，这个对象包含一些指令相关的函数，这些函数可以在特定的阶段，执行相关的操作。
// 2、在directive()函数的第二个参数中（对象）中又包含了三个实例方法：
// bind: 当指令绑定到元素上的时候，会立即执行这个bind函数，只执行一次；但是需要知道元素绑定了这个指令，若涉及对DOM操作的，并不会立即执行，因为元素不会立即插入到DOM中。所以涉及对元素进行DOM相关操作的，不要定义到这个方法中。
// inserted: 当元素插入到DOM的时候，会立即执行，并只触发一次。
// updated: 当VNode更新的时候，会指定updated，可能触发多次。
// 钩子函数：
// 指令定义函数提供了几个钩子函数（可选）：
// bind inserted update componentUpdated: 所在组件的VNode及其孩子的VNode全部更新的时候调用 unbind: 只调用一次，指令与元素解除绑定时调用
// 钩子函数参数 在上面使用directive()函数的时候我们已经介绍了一些常用的钩子函数，那么既然是函数，就可能需要进行传参，那么为了实现钩子函数传参，Vue提供了几个参数属性来实现对钩子函数参数的一些操作：
// - el: 指令所绑定的元素，可以用来直接操作DOM。
// - binding: 一个对象，包含以下属性：
// name: 指令名，不包含v-前缀
// value: 指令的绑定值，如v-focus="1 + 1"，那么value=2。
// expression: 绑定值的字符串形式，如v-focus="1+1"，那么experssion的值是1+1。
// 定义私有指令：
// 使用私有指令和全局指令的用法基本相同，我们参考上面讲过的私有过滤器和全局过滤器就能猜想到私有指令的用法：



// Vue实例的生命周期
// 什么是声明周期：从Vue实例创建、运行、到销毁期间，伴随着发生的事件的过程成为生命周期。
// 生命周期钩子：就是声明周期事件的别名。
// 主要的声明周期函数分类

// 创建期间的声明周期函数：
// * beforeCreate: 实例刚在内存中被创建，此时，还没有初始化好data和methods属性。
// * created: 实例已经在内存中创建好，此时data和methods已经创建好，但还没有编译模板。
// * beforeMount: 此时已经完成了模板的编译，但是还没有挂载到页面上。
// * mounted: 此时，已经将编译好的模板，挂载到了页面指定的容器中。

// 运行期间的声明周期函数:
// * beforeUpdate: 状态更新之前执行此函数，此时的data数据是最新的，但是此时还没有开始渲染DOM节点
// * updated: 实例更新完毕之后调用此函数，此时data中的状态值和界面上显示的数据都是最新的，界面已经被重新渲染好了。

// 销毁期间的生命周期函数
// * beforeDestory: 实例销毁之前调用，在这一步，实例仍然可以使用。
// * destroyed: Vue实例销毁后调用，调用后，Vue实例指示的所有东西都会解除绑定，所有的事件监听器都会被移除，所所有的子实例也会被销毁。

// 插槽 参考：https://juejin.im/post/5a69ece0f265da3e5a5777ed
// 具名插槽：有时候我们一个组件里需要多个插槽那么怎么办呢？ 对于这样的情况，<slot>元素有一个特殊的特性：name ，这个特性可以用来定义额外的插槽。
// 如果一个<slot>不带name属性的话，那么它的name默认为default,在向具名插槽提供内容的时候，我们可以在<template>元素上使用v-slot或者slot指令，并以参数的形式提供其名称。
// 现在 <template> 元素中的所有内容都将会被传入相应的插槽。任何没有被包裹在带有 v-slot 的 <template> 中的内容都会被视为默认插槽的内容。

// 概念：
// 插槽，也就是slot，是组件的一块HTML模板，这块模板显示不显示、以及怎样显示由父组件来决定。 实际上，一个slot最核心的两个问题在这里就点出来了，是显示不显示和怎样显示。
// 由于插槽是一块模板，所以，对于任何一个组件，从模板种类的角度来分，其实都可以分为非插槽模板和插槽模板两大类。
// 1、非插槽模板指的是html模板，比如‘div、span、ul、table’这些，非插槽模板的显示与隐藏以及怎样显示由组件自身控制；
// 2、插槽模板是slot，它是一个空壳子，因为它的显示与隐藏以及最后用什么样的html模板显示由父组件控制。但是插槽显示的位置却由子
// 组件自身决定，slot写在组件template的什么位置，父组件传过来的模板将来就显示在什么位置。
// 插槽的分类：单个插槽/默认插槽/匿名插槽，具名插槽，作用域插槽/带数据的插槽

