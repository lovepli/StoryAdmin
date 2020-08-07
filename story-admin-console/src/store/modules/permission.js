import { constantRouterMap } from '@/router' // 引入路由

// 声明一个 permission module组件 ,里面包含三个变量state，mutations，actions
const permission = {
  // 声明一个需要全局维护的状态 state
  // state:共同维护的一个状态，state里面可以是很多个全局状态
  state: {
    routers: constantRouterMap,
    addRouters: [], // 异步加载的路由
    btns: []
  },
  // actions：数据的异步操作
  // 给action注册事件处理函数。当这个函数被触发时候，将状态提交到mutations中处理
  actions: {
    // 获取动态路由
    // GenerateRoutes({ commit }, data) {
    //   return new Promise(resolve => {
    //     const { functions } = data
    //     let accessedRouters
    //     // if (roles.indexOf('admin') >= 0) {
    //     //   accessedRouters = asyncRouterMap
    //     // } else {
    //      匹配前端路由和后台返回的菜单
    //       accessedRouters = filterAsyncRouter(asyncRouterMap, functions)
    //     // }
    //     commit('SET_ROUTERS', accessedRouters)
    //     resolve()
    //   })
    // },
    initMenu(state, menus) {
      state.routes = menus;
    }
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
  }
}

/**
 * 通过meta.role判断是否与当前用户权限匹配
 * @param functions 后台返回的菜单
 * @param route 前端定义好的异步路由中的项
 */

// eslint-disable-next-line no-unused-vars
function hasPermission(functions, route) {
  if (route.meta && route.meta.functions) {
    return functions.some(func => route.meta.functions.indexOf(func) >= 0)
  } else {
    return true
  }
}

/**
 * 递归过滤异步路由表，返回符合用户角色权限的路由表
 * @param asyncRouterMap
 * @param functions
 */
// function filterAsyncRouter(asyncRouterMap, functions) {
//   const accessedRouters = asyncRouterMap.filter(route => {
//     if (hasPermission(functions, route)) {
//       if (route.children && route.children.length) {
//         route.children = filterAsyncRouter(route.children, roles)
//       }
//       return true
//     }
//     return false
//   })
//   return accessedRouters
// }

export default permission
