import Cookies from 'js-cookie'

// 声明一个 app module组件 ,里面包含三个变量state，mutations，actions
// 组件以异步方式修改数据时，先通过actions，然后再通过mutations操作state数据改变，再渲染到组件中
const app = {
  // 声明一个需要全局维护的状态 state
  // state:共同维护的一个状态，state里面可以是很多个全局状态
  state: {
    sidebar: {
      opened: !+Cookies.get('sidebarStatus'),
      withoutAnimation: false
    },
    device: 'desktop'
  },
  // mutations:处理数据的唯一途径，state的改变或赋值只能在这里
  // 更改 Vuex 的 store 中的状态的唯一方法是提交 mutation。
  // Vuex 中的 mutation 非常类似于事件：每个 mutation 都有一个字符串的 事件类型 (type) 和 一个 回调函数 (handler)。
  // 这个回调函数就是我们实际进行状态更改的地方，并且它会接受 state 作为第一个参数
  mutations: {
    // 回调函数TOGGLE_SIDEBAR
    TOGGLE_SIDEBAR: state => {
      if (state.sidebar.opened) {
        Cookies.set('sidebarStatus', 1)
      } else {
        Cookies.set('sidebarStatus', 0)
      }
      state.sidebar.opened = !state.sidebar.opened
      state.sidebar.withoutAnimation = false
    },
    CLOSE_SIDEBAR: (state, withoutAnimation) => {
      Cookies.set('sidebarStatus', 1)
      state.sidebar.opened = false
      state.sidebar.withoutAnimation = withoutAnimation
    },
    TOGGLE_DEVICE: (state, device) => {
      state.device = device
    }
  },
  // actions：数据的异步操作
  // Action 类似于 mutation，不同在于：
  // 1、Action 提交的是 mutation，而不是直接变更状态。
  // 2、Action 可以包含任意异步操作。
  actions: {
    ToggleSideBar: ({ commit }) => {
      commit('TOGGLE_SIDEBAR')
    },
    CloseSideBar({ commit }, { withoutAnimation }) {
      commit('CLOSE_SIDEBAR', withoutAnimation)
    },
    ToggleDevice({ commit }, device) {
      commit('TOGGLE_DEVICE', device)
    }
  }
}

export default app // 导出app并在 index.js中引用注册到modules中。
