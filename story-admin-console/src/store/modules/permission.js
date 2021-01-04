import { constantRouterMap } from '@/router/index'// 引入路由

/**
 * 判断用户是否拥有此菜单
 * 通过meta.role判断是否与当前用户权限匹配
 * @param menus 后台返回的菜单
 * @param route 前端定义好的异步路由中的项
 */

function hasPermission(menus, route) {
  // if (route.meta && route.meta.menus) {
  if (route.menu) {
    /*
    * 如果这个路由有menu属性,就需要判断用户是否拥有此menu权限
    */
    // return menus.some(func => route.meta.menus.indexOf(func) >= 0)
    return menus.indexOf(route.menu) > -1;
  } else {
    return true
  }
}

/**
 * 递归过滤异步路由表，返回符合用户角色权限的路由表
 * @param asyncRouterMap
 * @param menus
 */

function filterAsyncRouter(asyncRouterMap, menus) {
  const accessedRouters = asyncRouterMap.filter(route => {
    // filter,js语法里数组的过滤筛选方法
    if (hasPermission(menus, route)) {
      if (route.children && route.children.length) {
        // 如果这个路由下面还有下一级的话,就递归调用
        // eslint-disable-next-line no-undef
        route.children = filterAsyncRouter(route.children, roles)
        // 如果过滤一圈后,没有子元素了,这个父级菜单就也不显示了
        return (route.children && route.children.length)
      }
      return true
    }
    return false
  })
  return accessedRouters
}

// 声明一个 permission module组件 ,里面包含三个变量state，mutations，actions
// 参考：https://github.com/Heeexy/SpringBoot-Shiro-Vue/blob/master/explain-frontend.md
const permission = {
  // 声明一个需要全局维护的状态 state
  // state:共同维护的一个状态，state里面可以是很多个全局状态
  state: {
    routers: constantRouterMap, // 本用户所有的路由,包括了固定的路由和下面的addRouters
    addRouters: [], // 异步加载的路由 //本用户的角色赋予的新增的动态路由
    btns: []
  },
  // mutations:处理数据的唯一途径，state的改变或赋值只能在这里
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers
      state.routers = constantRouterMap.concat(routers)
    },
    SET_BTNS: (state, btns) => {
      state.btns = btns
    }
  },
  // actions：数据的异步操作
  // 给action注册事件处理函数。当这个函数被触发时候，将状态提交到mutations中处理
  actions: {
    // 获取动态路由
    GenerateRoutes({ commit }, userPermission) {
      // 生成路由
      return new Promise(resolve => {
        // roles是后台传过来的角色数组,比如['管理员','文章']
        const roles = userPermission.roles;
        const menus = userPermission.menus;
        let accessedRouters
        // 是否是管理员
        if (roles.indexOf('管理员') >= 0) {
          // 如果角色里包含'管理员',那么所有的路由都可以用
          // 其实管理员也拥有全部菜单,这里主要是利用角色判断,节省加载时间
          // eslint-disable-next-line no-undef
          accessedRouters = asyncRouterMap
        } else {
          // 否则需要通过以下方法来筛选出本角色可用的路由 匹配前端路由和后台返回的菜单
          // eslint-disable-next-line no-undef
          accessedRouters = filterAsyncRouter(asyncRouterMap, menus)
        }
        // 执行设置路由的方法
        commit('SET_ROUTERS', accessedRouters)
        resolve()
      })
    },
    initMenu(state, menus) {
      state.routes = menus;
    }
  }

}

export default permission
