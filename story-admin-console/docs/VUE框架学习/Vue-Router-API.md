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

// 补充博客知识点：vue2.0里的路由钩子 https://www.cnblogs.com/jin-zhe/p/10313067.html
// 路由钩子
在某些情况下，当路由跳转前或跳转后、进入、离开某一个路由前、后，需要做某些操作，就可以使用路由钩子来监听路由的变化

一、全局路由钩子：
router.beforeEach((to, from, next) => {
    //会在任意路由跳转前执行，next一定要记着执行，不然路由不能跳转了
    // 参数：有to（去的那个路由）、from（离开的路由）、next（一定要用这个函数才能去到下一个路由，如果不用就拦截）
  console.log('beforeEach')
  console.log(to,from)
  //
  next()
})
//
router.afterEach((to, from) => {
    //会在任意路由跳转后执行
  console.log('afterEach')
})
注意：afterEach钩子没有 next 方法，不能改变导航。

二、单个路由钩子：
只有beforeEnter，在进入前执行，to参数就是当前路由
routes: [
    {
      path: '/foo',
      component: Foo,
      beforeEnter: (to, from, next) => {
        // ...
      }
    }
  ]
 三、路由组件钩子：
 beforeRouteEnter (to, from, next) {
    // 在渲染该组件的对应路由被 confirm 前调用
    // 不！能！获取组件实例 `this`
    // 因为当守卫执行前，组件实例还没被创建
  },
  beforeRouteUpdate (to, from, next) {
    // 在当前路由改变，但是该组件被复用时调用
    // 举例来说，对于一个带有动态参数的路径 /foo/:id，在 /foo/1 和 /foo/2 之间跳转的时候，
    // 由于会渲染同样的 Foo 组件，因此组件实例会被复用。而这个钩子就会在这个情况下被调用。
    // 可以访问组件实例 `this`
  },
  beforeRouteLeave (to, from, next) {
    // 导航离开该组件的对应路由时调用
    // 可以访问组件实例 `this`
  }

