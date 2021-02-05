import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui' // 引入element-ui的全部组件
import './styles/element-variables.scss'
import 'element-ui/lib/theme-chalk/index.css' // element-ui的css
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n

import '@/styles/index.scss' // 全局自定义的css样式

import App from './App'
import router from './router' // 全局引入路由
import store from './store' // 全局引入本地存储store,中央存储,store是vuex的核心

import '@/icons' // icon图标
import '@/permission' // permission control权限控制
import { hasPermission } from './utils/hasPermission';
import { getById, download } from './utils/common'
import './utils/constant.js'
import LunarFullCalendar from 'vue-lunar-full-calendar'
import Print from './utils/vue-print-nb/src' // import Print from 'vue-print-nb' //  这里引入的是本地的组件
import filter from '@/utils/example_demo/filter' /* 引入公用filter */
import echarts from 'echarts' // 引入图表
import TreesTable from 'vue-table-with-tree-grid' // 引入分级树形表格

// 全局引入Markdown 依赖
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

// 全局注册指令和依赖
import '@/directive'; // 引入指令，包含copy和拖动的对话框

// 通过全局方法 Vue.use() 安装 Vue.js 插件。它需要在你调用 new Vue() 启动应用之前完成
Vue.use(ElementUI, { locale }) // 安装使用elementUI插件，调用 `ElementUI.install(Vue)`
Vue.use(LunarFullCalendar)
Vue.use(Print)
// Vue.mixin 全局注册一个混入。混入也可以进行全局注册。使用时格外小心！一旦使用全局混入，它将影响每一个之后创建的 Vue 实例。使用恰当时，这可以用来为自定义选项注入处理逻辑。
// 请谨慎使用全局混入，因为它会影响每个单独创建的 Vue 实例 (包括第三方组件)。大多数情况下，只应当应用于自定义选项，就像上面示例一样。推荐将其作为插件发布，以避免重复应用混入。
Vue.mixin(filter) // 混入公用filter 组件方法复用，参考：https://www.cnblogs.com/wjw1014/p/11757452.html
Vue.use(mavonEditor) // 
Vue.component('tree-table', TreesTable) // Vue.component 全局注册组件,注册树形表格组件为全局组件，注册还会自动使用给定的 id 设置组件的名称,局部注册组件就是到需要的vue页面中import组件

// 全局的常量
Vue.prototype.hasPerm = hasPermission
Vue.prototype.$echarts = echarts
require('echarts-wordcloud') // 使用词云

Vue.config.productionTip = false //阻止向控制台打印启动生产消息

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
  render: h => h(App) // 渲染函数render, 运行时构建   // 将 h 作为 createElement 的别名是 Vue 生态系统中的一个通用惯例，实际上也是 JSX 所要求的，如果在作用域中 h 失去作用，在应用中会触发报错。
})
