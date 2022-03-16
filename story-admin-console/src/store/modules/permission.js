import { constantRouterMap } from '@/router/index'// 引入路由

// 声明一个 permission module组件 ,里面包含三个变量state，mutations，actions
// 参考：https://github.com/Heeexy/SpringBoot-Shiro-Vue/blob/master/explain-frontend.md
const permission = {
  // 声明一个需要全局维护的状态 state
  // state:共同维护的一个状态，state里面可以是很多个全局状态
  state: {
    routers: constantRouterMap, // 本用户所有的路由,包括了固定的路由和下面的addRouters
    addRouters: [] // 异步加载的路由 //本用户的角色赋予的新增的动态路由
  },
  // mutations:处理数据的唯一途径，state的改变或赋值只能在这里
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers
      state.routers = constantRouterMap.concat(routers)
    }
  },
  // actions：数据的异步操作
  // 给action注册事件处理函数。当这个函数被触发时候，将状态提交到mutations中处理
  actions: {

  }
}

export default permission

