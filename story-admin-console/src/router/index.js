import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import Layout from '../views/layout/Layout' // 引入页面布局

// import CommonLayout from '../views/layout/commonLayout' //三级路由

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
  // 首页
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
  },
  {
    path: 'Http://ruoyi.vip',
    component: Layout,
    // redirect: 'Http://ruoyi.vip',
    name: 'Http://ruoyi.vip',
    meta: {
      title: '官网地址',
      icon: 'dashboard',
      noCache: true
    },
    children: []
  },
  // 静态菜单功能页面展示 参考：https://github.com/shengbid/vue-demo
  // 例子
  {
    path: '/example_demo',
    name: 'example_demo',
    component: Layout,
    meta: { title: '示例', icon: 'dashboard', noCache: true },
    children: [
      {
        path: 'rotate',
        component: () => import('@/views/example_demo/rotate'),
        name: 'rotateOrangination',
        meta: { title: '旋转', icon: 'el-icon-menu' }
      },
      {
        path: 'magnifer',
        component: () => import('@/views/example_demo/magnifer'),
        name: 'magnifying',
        meta: { title: '放大镜', icon: 'el-icon-search' }
      },
      {
        path: 'print',
        component: () => import('@/views/example_demo/print'),
        name: 'print',
        meta: { title: '打印', icon: 'el-icon-s-grid' }
      },
      {
        path: 'pdf',
        name: 'pdf',
        component: () => import('@/views/example_demo/pdf'),
        meta: { title: 'pdf', icon: 'el-icon-tickets' }
      }
    ]
  },
  { // 编辑图片
    path: '/editImage',
    component: Layout,
    redirect: '/editImage/image',
    meta: { title: '图形编辑', icon: 'dashboard', noCache: true },
    children: [
      {
        path: 'image',
        component: () => import('@/views/example_demo/editImage/editImage'),
        name: 'editImage',
        meta: { title: '图形编辑', icon: 'el-icon-picture' }
      },
      {
        path: 'imageObj',
        component: () => import('@/views/example_demo/editImage/imgobj'),
        name: 'editImageObj',
        meta: { title: '图形编辑(js包引入)', icon: 'el-icon-picture' }
      },
      {
        path: 'upload/file',
        component: () => import('@/views/example_demo/editImage/upload'),
        name: 'uploadFile',
        meta: { title: '文件列表上传', icon: 'el-icon-picture' }
      }
    ]
  },
  {  // 表格
    path: '/Table',
    component: Layout,
    // redirect: '/Table/tree',
    meta: { title: '表格', icon: 'dashboard', noCache: true },
    children: [
      {
        path: 'BaseTable',
        component: () => import('@/views/example_demo/Table/base-table/BaseTable'),
        name: 'BaseTable',
        meta: { title: '基础表格', icon: 'el-icon-s-grid' }
      },
      {
        path: 'tree',
        component: () => import('@/views/example_demo/Table/treeTable'),
        name: 'treeTable',
        meta: { title: '树形表格', icon: 'el-icon-s-grid' }
      },
      {
        path: 'validate',
        component: () => import('@/views/example_demo/Table/validateTable'),
        name: 'validateTable',
        meta: { title: '可验证表格', icon: 'el-icon-notebook-2' }
      },
      {
        path: 'checkbox',
        component: () => import('@/views/example_demo/Table/checkTable'),
        name: 'checkTable',
        meta: { title: '可选择表格', icon: 'el-icon-notebook-2' }
      },
      {
        path: 'spanTable',
        component: () => import('@/views/example_demo/Table/spanTable'),
        name: 'spanTable',
        meta: { title: '表格合并', icon: 'el-icon-film' }
      },
      {
        path: 'Tab',
        component: () => import('@/views/example_demo/tab'),
        name: 'Tab',
        meta: { title: 'Tab选项卡', icon: 'el-icon-s-grid' }
      }
    ]
  },
  {
    path: '/nest',
    component: Layout,
    redirect: '/nest/nest1',
    meta: { title: '嵌套路由', icon: 'dashboard', noCache: true },
    children: [
      {
        path: 'nest1',
        name: 'nest1',
        component: () => import('@/views/example_demo/nest/nest1-1'),
        meta: { title: '嵌套路由1', icon: 'el-icon-connection' }
      },
      {
        path: 'nest1-1',
        name: 'nest1-1',
        component: Layout, // 嵌套路由3级以上有问题
        meta: { title: '嵌套路由1-1', icon: 'el-icon-connection' },
        // redirect: '/nest/nest1-1/nest1-1-1',
        children: [
          {
            path: 'nest1-1-1',
            name: 'nest1-1-1',
            component: () => import('@/views/example_demo/nest/nest1-1/nest1-1-1'),
            meta: { title: '嵌套路由1-1-1', icon: 'el-icon-connection', noCache: true },
            children: []
          }
        ]
      }]
  },
  { // 图表
    path: '/Chat',
    component: Layout,
    // redirect: '/Table/tree',
    meta: { title: '图表', icon: 'dashboard', noCache: true },
    children: [
      {
        path: 'areaChart',
        component: () => import('@/views/example_demo/chart/area-chart/AreaChart'),
        name: 'areaChart',
        meta: { title: '面积图', icon: 'el-icon-s-grid' }
      },
      {
        path: 'barChart',
        component: () => import('@/views/example_demo/chart/bar-chart/BarChart'),
        name: 'barChart',
        meta: { title: '条形图', icon: 'el-icon-notebook-2' }
      },
      {
        path: 'lineChart',
        component: () => import('@/views/example_demo/chart/line-chart/LineChart'),
        name: 'lineChart',
        meta: { title: '折线图', icon: 'el-icon-notebook-2' }
      },
      {
        path: 'pieChart',
        component: () => import('@/views/example_demo/chart/pie-chart/PieChart'),
        name: 'pieChart',
        meta: { title: '饼状图', icon: 'el-icon-film' }
      },
      {
        path: 'PillarChart',
        component: () => import('@/views/example_demo/chart/pillar-chart/PillarChart'),
        name: 'PillarChart',
        meta: { title: '柱状图', icon: 'el-icon-film' }
      },
      {
        path: 'pointChart',
        component: () => import('@/views/example_demo/chart/point-chart/PointChart'),
        name: 'pointChart',
        meta: { title: '点状图', icon: 'el-icon-film' }
      },
      {
        path: 'radarChart',
        component: () => import('@/views/example_demo/chart/radar-chart/RadarChart'),
        name: 'radarChart',
        meta: { title: '雷达图', icon: 'el-icon-film' }
      }
    ]
  },
  { //  表单
    path: '/Form',
    component: Layout,
    // redirect: '/Table/tree',
    meta: { title: '表单', icon: 'dashboard', noCache: true },
    children: [
      {
        path: 'baseForm',
        component: () => import('@/views/example_demo/form/base-form/BaseForm'),
        name: 'baseForm',
        meta: { title: '基础表单', icon: 'el-icon-s-grid' }
      },
      {
        path: 'dynamicForm',
        component: () => import('@/views/example_demo/form/dynamic-form/DynamicForm'),
        name: 'dynamicForm',
        meta: { title: '动态表单', icon: 'el-icon-notebook-2' }
      },
      {
        path: 'stepForm',
        component: () => import('@/views/example_demo/form/step-form/StepForm'),
        name: 'stepForm',
        meta: { title: '步骤表单', icon: 'el-icon-notebook-2' }
      },
      {
        path: 'validForm',
        component: () => import('@/views/example_demo/form/valid-form/ValidForm'),
        name: 'validForm',
        meta: { title: '自定义校验规则', icon: 'el-icon-film' }
      }
    ]
  },
  { //  文章管理
    name: 'Article',
    path: '/article',
    component: Layout,
    redirect: '/article/list',
    meta: { title: '文章管理', icon: 'dashboard', noCache: true },
    children: [
      {
        path: '/article/list',
        component: () => import('@/views/example_demo/article/list'),
        name: 'ArticleList',
        meta: { title: '文章列表', icon: 'el-icon-s-grid',activePath: '/article', noCache: true }
      },
      {
        path: '/article/add',
        component: () => import('@/views/example_demo/article/edit'),
        name: 'articleAdd',
        meta: { title: '新增文章', hiddenInMenu: true, icon: 'el-icon-s-grid',noCache: true }
      },
      {
        path: '/article/edit/:articleId/:articleIndex',
        component: () => import('@/views/example_demo/article/edit'),
        name: 'articleEdit',
        meta: { title: '编辑文章', hiddenInMenu: true, icon: 'el-icon-s-grid',noCache: true },
        props: true,
        beforeEnter: (to, from, next) => {
          to.meta.title = '编辑文章' + '-' + to.params.articleIndex
          next()
        }
      }
  
    ]
  }
]

// 定义实例化路由的方法
const createRouter = () => new Router({
  mode: 'history', // 去掉url中的#
  // 对于所有路由导航，简单地让页面滚动到顶部
  scrollBehavior: () => ({ y: 0 }), // 使用前端路由，当切换到新路由时，想要页面滚到顶部，或者是保持原先的滚动位置，就像重新加载页面那样。 vue-router 能做到，而且更好，它让你可以自定义路由切换时页面如何滚动。
  routes: constantRouterMap // 挂载常规路由
})
// 实例化路由
const router = createRouter()

/**
 * 最终无法匹配到相应路由，重定向到404
 * 异步加载路由时，在生成完异步路由准备挂载时，需要将重定向404的匹配规则定义在最后面，否则刷新会出错。
 */
const notFoundRoutes = [
  {
    path: '*', // 会匹配所有路径, // path: '/user-*', 会匹配以 `/user-` 开头的任意路径
    redirect: '/404',
    hidden: true,
    meta: {
      title: '404'
    }
  }
]
// 路由里添加404路由
router.addRoutes(notFoundRoutes)

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
// 定义重置路由的方法
// export function resetRouter() {
//   const newRouter = createRouter()
//   router.matcher = newRouter.matcher // reset router
// }

export default router
