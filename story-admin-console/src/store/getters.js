// 获取数据并渲染 说明参考：https://blog.csdn.net/Charissa2017/article/details/105308516
// getter 相当于自定义组件中的computed，getter 的返回值会根据它的依赖被缓存起来，且只有当它的依赖值发生了改变才会被重新计算。
// getter 接受 state 作为其第一个参数，也可以接受其他 getter 作为第二个参数。
// 可以使用 store.getters / this.$store.getters 访问这些值。(通过属性访问)
// 也可以通过让 getter 返回一个函数，来实现给 getter 传参。在你对 store 里的数组进行查询时非常有用。(通过方法访问)
const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,

  token: state => state.user.token, // token
  // 通过属性访问:Getter 会暴露为 store.getters 对象，你可以以属性的形式访问这些值，例如这里我们可以通过store.getters.token访问到token的值,也可以在任何其他组件中使用它：this.$store.getters.token获取到全局状态token的值
  id: state => state.user.id, // 登录id
  avatar: state => state.user.avatar, // 登录图像
  name: state => state.user.name, // 用户名
  erp: state => state.user.erp,
  roles: state => state.user.roles, // 用户角色
  permission_routers: state => state.permission.routers, // 权限路由
  addRouters: state => state.permission.addRouters, // 添加路由
  btns: state => state.permission.btns // 权限按钮？
  // 也可以写成下面这样，上面的写法是es6语法糖
  // btns:(state)=>{
  //   state.permission.btns;
  // }

}
export default getters

// getter说明
// Vuex 允许我们在 store 中定义“getter”（可以认为是 store 的计算属性）。就像计算属性一样，getter 的返回值会根据它的依赖被缓存起来，且只有当它的依赖值发生了改变才会被重新计算。
