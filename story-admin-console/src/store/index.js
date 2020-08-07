import Vue from 'vue' // 引入vue
import Vuex from 'vuex' // 引入vuex
// import app from './modules/app'
// import user from './modules/user'
// import permission from './modules/permission'
// import tagsView from './modules/tagsView'
// import * as getters from './getters' // 导入响应的模块，*相当于引入了这个组件下所有导出的事例
import getters from './getters' // 简写

// 通过全局方法 Vue.use() 使用插件Vuex。它需要在你调用 new Vue() 启动应用之前完成
// 在一个模块化的打包系统中，您必须显式地通过 Vue.use() 来安装 Vuex
Vue.use(Vuex)
// Vuex是什么？
// Vuex 是一个专为 Vue.js 应用程序开发的状态管理模式。它采用集中式存储管理应用的所有组件的状态，并以相应的规则保证状态以一种可预测的方式发生变化。

// 简单认识vuex：
// 每一个 Vuex 应用的核心就是 store（仓库）。“store”基本上就是一个容器，它包含着你的应用中大部分的状态 (state)。Vuex 和单纯的全局对象有以下两点不同：
// 1、Vuex 的状态存储是响应式的。当 Vue 组件从 store 中读取状态的时候，若 store 中的状态发生变化，那么相应的组件也会相应地得到高效更新。
// 2、你不能直接改变 store 中的状态。改变 store 中的状态的唯一途径就是显式地提交 (commit) mutation。这样使得我们可以方便地跟踪每一个状态的变化，从而让我们能够实现一些工具帮助我们更好地了解我们的应用。

// 由于 store 中的状态是响应式的，在组件中调用 store 中的状态简单到仅需要在计算属性中返回即可。触发变化也仅仅是在组件的 methods 中提交 mutation。
// 由于 Vuex 的状态存储是响应式的，从 store 实例中读取状态最简单的方法就是在计算属性中返回某个状态。

// 使用 Vuex 并不意味着你需要将所有的状态放入 Vuex。虽然将所有的状态放到 Vuex 会使状态变化更显式和易调试，但也会使代码变得冗长和不直观。如果有些状态严格属于单个组件，最好还是作为组件的局部状态。你应该根据你的应用开发需要进行权衡和确定。

// https://webpack.js.org/guides/dependency-management/#requirecontext
// 由于使用单一状态树，应用的所有状态会集中到一个比较大的对象。当应用变得非常复杂时，store 对象就有可能变得相当臃肿。
// 为了解决以上问题，Vuex 允许我们将 store 分割成模块（module）。每个模块拥有自己的 state、mutation、action、getter、甚至是嵌套子模块——从上至下进行同样方式的分割：
// 这里（创建了）一个包含了modules文件夹（包含子目录）下面的，所有文件名以 `.js` 结尾的、能被 require 请求到的文件的上下文。
const modulesFiles = require.context('./modules', true, /\.js$/)

// you do not need `import app from './modules/app'`
// it will auto require all vuex module from modules file
// keys() 方法用于从modules创建一个包含modules里键值的可迭代对象。
const modules = modulesFiles.keys().reduce((modules, modulePath) => {
  // set './app.js' => 'app'
  // 模块名，取文件名
  const moduleName = modulePath.replace(/^\.\/(.*)\.\w+$/, '$1')
  // 获取键名为modulePath的文件内容
  const value = modulesFiles(modulePath)
  // 将文件中的默认导出模块赋值给迭代对象modules
  modules[moduleName] = value.default
  // 返回迭代对象modules
  return modules
  // 默认值是空对象{}
}, {})

// 创建vuex仓库实例  可以参考：https://blog.csdn.net/Charissa2017/article/details/105308516
// 注册上面引入的各大模块
const store = new Vuex.Store({
  modules,
  getters // 获取数据并渲染
})

export default store // 导出store并在 main.js中引用注册
