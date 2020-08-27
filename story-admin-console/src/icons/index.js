import Vue from 'vue'
import SvgIcon from '@/components/SvgIcon' // 引入svg组件

// 参考花裤衩：https://juejin.im/post/6844903517564436493

// register globally全局注册组件
// 定义一个名为 svg-icon 的新组件
Vue.component('svg-icon', SvgIcon)
// 全局注册往往是不够理想的。比如，如果你使用一个像 webpack 这样的构建系统，全局注册所有的组件意味着即便你已经不再使用一个组件了，它仍然会被包含在你最终的构建结果中。这造成了用户下载的 JavaScript 的无谓的增加
// 自动引入 @/src/icons 下面所有的图标了
const requireAll = requireContext => requireContext.keys().map(requireContext)
const req = require.context('./svg', false, /\.svg$/)
requireAll(req)
