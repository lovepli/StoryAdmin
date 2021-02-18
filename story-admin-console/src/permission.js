import router from './router'
import store from './store'
import NProgress from 'nprogress' // Progress 进度条
import 'nprogress/nprogress.css'// Progress 进度条样式
import { Message } from 'element-ui'
import { getToken } from '@/utils/auth' // 验权

// 该项目中权限的实现方式是：通过获取当前用户的权限去比对路由表，生成当前用户具的权限可访问的路由表，
// 通过router.addRoutes动态挂载到router上
// 简单说就是：用户登录之后会返回一个权限凭证Token，用户在根据这个Token去问服务端询问自己的权限，辟如服务端返回权限是['editor']，前端再根据这个权限动态生成他能访问的路由，再通过addRoutes进行动态的路由挂载

const whiteList = ['/login', '/login_erp'] // 不重定向白名单
// router.beforeEach 注册一个全局导航前置守卫
// 当一个导航触发时，全局前置守卫按照创建顺序调用。守卫是异步解析执行，此时导航在所有守卫 resolve 完之前一直处于 等待中。
// to: Route: 即将要进入的目标的路由对象
// from: Route: 当前导航正要离开的路由对象
// next: Function: 一定要调用该方法来 resolve 这个钩子。执行效果依赖 next 方法的调用参数。
router.beforeEach((to, from, next) => {
  NProgress.start()
  if (getToken()) { // 判断是否有token
    if (to.path === '/login' || to.path === '/login_erp' || to.path === '/admin') {
      next({ path: '/' });// 跳转到首页。当前的导航被中断，然后进行一个新的导航。你可以向 next 传递任意位置对象，且允许设置诸如 replace: true、name: 'home' 之类的选项以及任何用在 router-link 的 to prop 或 router.push 中的选项。
      NProgress.done() // if current page is dashboard will not trigger	afterEach hook, so manually handle it
    } else {
      // Getter 会暴露为 store.getters 对象，你可以以属性的形式访问这些值，这里是通过属性访问roles的值
      if (store.getters.roles.length === 0) { // 判断当前用户是否已拉取完user_info信息
      // 通过 store.dispatch(type)方法触发action，参数为事件类型，需要和action中函数名称一致。
      // 判断当前用户是否已拉取完user_info信息
        store.dispatch('GetInfo').then(res => { // 拉取用户信息
          const menus = res.data.menus;
          var fmtRoutes = formatRoutes(menus); // 生成可访问的路由表
          // 根据menus权限生成可访问的路由表
          router.addRoutes(fmtRoutes); // 动态添加可访问路由表
          // 通过 store.commit(type,data)调用 mutation，第一个参数为事件类型，需要和mutation中函数名称一致；第二个参数为要传递的参数。
          store.commit('SET_ROUTERS', fmtRoutes); // store.commit 方法触发状态变更,这里是变更路由表,第一个参数为事件类型,第二个参数为载荷，这里是路由表
          next({ ...to, replace: true }) // hack方法 确保addRoutes已完成 ,set the replace: true so the navigation will not leave a history record
        }).catch((err) => {
          console.log(err);
          // 通过 store.dispatch(type)方法触发action，参数为事件类型，需要和action中函数名称一致。
          store.dispatch('FedLogOut').then(() => { // 前端 登出
            Message.error(err || 'Verification failed, please login again')
            next({ path: '/' }); // 回到首页
          })
        })
      } else { // 页面不需要权限，直接进入
        // 当有用户权限的时候，说明所有可访问路由已生成 如访问没权限的全面会自动进入404页面
        next(); // 进行管道中的下一个钩子。如果全部钩子执行完了，则导航的状态就是 confirmed (确认的)。
      }
    }
  } else {
    if (whiteList.indexOf(to.path) !== -1) { // 在免登录白名单，直接进入
      next();
    } else if (to.path === '/admin') {
      window.location.href = process.env.ERP_LOGIN_HREF;
    } else {
      next(`/login?redirect=${to.path}`); // 否则全部重定向到登录页
      NProgress.done(); // 在hash模式下，手动改变hash,重定向回来，不会触发afterEach 暂时hack方案，ps:history模式下无问题，可删除该行
    }
  }
})

// 注册全局后置钩子
// 和守卫不同的是，这些钩子不会接受 next 函数也不会改变导航本身
router.afterEach(() => {
  NProgress.done() // 结束Progress
})

// 语法：
// 正常我们调用函数会写：name(function(){})，而ES6也提供了一个方式：methodName(() => {})，
// 这种用法的好处就解决了this指向问题，因为如果元素定义在了函数内部，那么其中的this就表示当前函数的对象，
// 如果我们需要使用外部的对象，除了在外部全局定义一个对象，一个简单的方式就是使用ES6提供的=>
/**
  * 格式化路由表
  * @param {*} routes
  */
export const formatRoutes = (routes) => {
  const fmRoutes = [];
  // 遍历routes
  routes.forEach(router => {
    // 自定义路由属性
    let {
      // eslint-disable-next-line prefer-const
      url,
      // eslint-disable-next-line prefer-const
      label,
      // eslint-disable-next-line prefer-const
      iconClass,
      children,
      // eslint-disable-next-line prefer-const
      component
    } = router;
    
    if (children && children instanceof Array && children.length > 0) {
       // 定义children属性，判断children是否存在，且children为数组，children不为空，则递归遍历children路由菜单
      children = formatRoutes(children);
    }

    // 本项目里的侧边栏是根据 router.js 配置的路由并且根据权限动态生成的，这样就省去了写一遍路由还要再手动写侧边栏这种麻烦事，
    // 同时使用了递归组件，这样不管你路由多少级嵌套，都能愉快的显示了。权限验证那里也做了递归的处理。
    // 同时本项目中也封装了一个面包屑导航，它也是通过watch $route动态生成的
    // 自定义路由对象fmRouter
    const fmRouter = {
      path: url,
      // 路由懒加载
      component(resolve) {
        require(['./views' + component + '.vue'], resolve)
      },
      name: label,
      // 路由元数据，里面可以存储一些自自定义的属性，不光只有title和icon,还可以定义一些其他的属性
      meta: {
        title: label,
        icon: iconClass
      },
      children: children
    };

    if (fmRouter.children && !fmRouter.children.length) {
      delete fmRouter['children'];
    }
    fmRoutes.push(fmRouter);
  })
  return fmRoutes;
}
