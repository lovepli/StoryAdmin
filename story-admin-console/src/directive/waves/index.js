import waves from './waves' // 1、引入waves
 
const install = function(Vue) {
  // 自定义指令
  // 2、注册一个全局自定义指令 `v-waves`
  // 注册全局指令
  Vue.directive('waves', waves)
}

// 这个自定义点击波浪特效指令
// 参考：https://www.cnblogs.com/CyLee/p/10239853.html
if (window.Vue) {
  window.waves = waves
  // 通过全局方法 Vue.use() 使用插件。它需要在你调用 new Vue() 启动应用之前完成 https://cn.vuejs.org/v2/guide/plugins.html
  Vue.use(install); // eslint-disable-line
}

// 调用 `waves.install(Vue)`
// Vue.js 的插件应该暴露一个 install 方法。这个方法的第一个参数是 Vue 构造器，第二个参数是一个可选的选项对象：
waves.install = install
export default waves
