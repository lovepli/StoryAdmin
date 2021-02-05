# 路由对象
一个路由对象 (route object) 表示当前激活的路由的状态信息，包含了当前 URL 解析得到的信息，还有 URL 匹配到的路由记录 (route records)。

路由对象是不可变 (immutable) 的，每次成功的导航后都会产生一个新的对象。

路由对象出现在多个地方:

在组件内，即 this.$route

在 $route 观察者回调内

router.match(location) 的返回值

导航守卫的参数：

router.beforeEach((to, from, next) => {
  // `to` 和 `from` 都是路由对象
})
scrollBehavior 方法的参数:

const router = new VueRouter({
  scrollBehavior(to, from, savedPosition) {
    // `to` 和 `from` 都是路由对象
  }
})
