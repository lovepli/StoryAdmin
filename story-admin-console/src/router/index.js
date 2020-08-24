import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import Layout from '../views/layout/Layout' // 引入页面布局

// 使用 Vue.js ，我们已经可以通过组合组件来组成应用程序，当你要把 Vue Router 添加进来，我们需要做的是，
// 将组件 (components) 映射到路由 (routes)，然后告诉 Vue Router 在哪里渲染它们。

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading
// 通过全局方法 Vue.use() 使用插件Router。它需要在你调用 new Vue() 启动应用之前完成
Vue.use(Router)

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/

// 定义常规路由，即那些不需要权限就可访问的页面，比如说登录注册、后台主页、404页面,这里需要注意的是404页面一定是最后加载的
export const constantRouterMap = [
  {
    path: '/login',
    // 在本地注册组件的时候，你可以使用 webpack 的异步 import,下面这样写就是路由懒加载,把不同路由对应的组件分割成不同的代码块，然后当路由被访问的时候才加载对应组件，这样就更加高效了
    // 这个动态导入会返回一个 `Promise` 对象。
    component: () => import('@/views/login/index'), // 链接到index界面
    hidden: true
  },
  {
    path: '/login_erp',
    component: () => import('@/views/login/erpIndex'), // 路由懒加载
    hidden: true
  },
  // 个人中心
  {
    path: '/me',
    component: Layout,
    hidden: true,
    redirect: '/me/index',
    children: [
      {
        path: 'index',
        name: 'PersonalCenter',
        component: () => import('@/views/personal_center/index'),
        meta: {
          title: '个人中心'
        }
      }
    ]
  },
  // 进入公告页面
  {
    path: '/inform',
    component: Layout,
    hidden: true,
    redirect: '/inform/:id',
    children: [{
      path: ':id',
      name: 'InformInfo',
      component: () => import('@/views/sysmgr/inform/info'),
      meta: {
        title: '公告'
      }
    }]

  },
  {
    path: '/404',
    component: () => import('@/views/404'), // 链接到404界面
    hidden: true
  },
  {
    path: '',
    component: Layout, // 引入home首页布局组件
    redirect: 'dashboard', // 从‘’重定向到'dashboard'
    children: [ // 子路由 嵌套路由，你会发现，children 配置就是像 routes 配置一样的路由配置数组，所以呢，你可以嵌套多层路由
      {
        // 当 '' 匹配成功，
        // index 会被渲染在 Layout 的 <router-view> 中
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'), // 链接到index界面 这样的写法有助于提高项目路由的加载效率，按需加载。
        name: 'dashboard',
        // 通过meta标签来表示该页面能访问的权限有哪些
        meta: {
          title: '首页',
          icon: 'dashboard',
          noCache: true
        }
      }
    ]
  },
  {
    path: '/pwd',
    component: Layout,
    redirect: '/pwd/edit', // 从/pwd重定向到/pwd/edit
    // “重定向”的意思是，当用户访问 /a时，URL 将会被替换成 /b，然后匹配路由为 /b
    hidden: true,
    children: [ // 子路由
      {
        // 当 /pwd 匹配成功，
        // editpassword 会被渲染在 Layout 的 <router-view> 中
        path: 'edit',
        component: () => import('@/views/sysmgr/user/editpassword'),
        name: '修改密码',
        meta: { title: '修改密码', icon: 'edit', noCache: true }
      }]
  }
]

/**
 * 异步挂载的路由
 * 动态需要根据权限加载的路由表
 */
// const modulesFiles = require.context('./modules', true, /\.js$/)
// const routesModules = []
// // 自动引入modules目录下的所有模块
// modulesFiles.keys().reduce((modules, modulePath) => {
//   const value = modulesFiles(modulePath)
//   routesModules.push(value.default)
// }, {})
// export const asyncRoutes = routesModules

/**
 * 最终无法匹配到相应路由，重定向到404
 * 异步加载路由时，在生成完异步路由准备挂载时，需要将重定向404的匹配规则定义在最后面，否则刷新会出错。
 */
// export const notFoundRoutes = [
//   {
//     path: '*',  //会匹配所有路径, // path: '/user-*', 会匹配以 `/user-` 开头的任意路径
//     redirect: '/404',
//     hidden: true,
//     meta: {
//       title: '404'
//     }
//   }
// ]

// 定义实例化路由的方法
const createRouter = () => new Router({
  mode: 'history', // 去掉url中的#
  // 对于所有路由导航，简单地让页面滚动到顶部
  scrollBehavior: () => ({ y: 0 }), // 使用前端路由，当切换到新路由时，想要页面滚到顶部，或者是保持原先的滚动位置，就像重新加载页面那样。 vue-router 能做到，而且更好，它让你可以自定义路由切换时页面如何滚动。
  routes: constantRouterMap// 挂载常规路由
})
// 实例化路由
const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
// 定义实重置路由的方法
// export function resetRouter() {
//   const newRouter = createRouter()
//   router.matcher = newRouter.matcher // reset router
// }

export default router
