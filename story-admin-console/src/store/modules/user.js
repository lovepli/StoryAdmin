import { login, logout, getInfo, loginerp,secretLogin } from '@/api/login'
// eslint-disable-next-line no-unused-vars
import { getToken, setToken, removeToken } from '@/utils/auth'

// 声明一个 user module组件 ,里面包含三个变量state，actions,mutations，
// state 相当于自定义组件中的data,gatters相当于自定义组件中的computed,mutations相当自定义组件中的methods，只能做同步操作，对state中的数据进行修改，action异步操作，例如ajax请求，使用commit 触发mutations
// user组件以异步方式修改数据时，先通过actions，然后再通过mutations操作state数据改变，再渲染到组件中
const user = {
  // 声明一个需要全局维护的状态 state
  // state:共同维护的一个状态，state里面可以是很多个全局状态
  // 每一个 Vuex 应用的核心就是 store（仓库）。“store”基本上就是一个容器，它包含着你的应用中大部分的状态 (state)。
  // State相当于自定义组件中的data，用来存放数据，页面中所有的数据都是从该对象中进行读取。
  // 在组件中使用 store.token / this.$store.token 来读取vuex中的数据。
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    roles: [], // 初始值为空
    menus: [],
    permissions: [],
    erp: ''
  },

  // 登录接口不同于其他接口，当登录成功后，需要使用vuex将登录接口响应的数据保存，以便维持与后端的会话通信
  // 登录需要后台提供1、登录接口，2、获取用户信息接口
  // actions：数据的异步操作
  // 给action注册事件处理函数。当这个函数被触发时候，将状态提交到mutations中处理
  // action 主要用来操作所有的异步请求。调用异步API
  // action 不能直接对State 中的数据进行操作，只能通过commit(type,data) 方法调用 mutation。
  // action 函数接受一个与 store 实例具有相同方法和属性的 context 对象，因此你可以调用 context.commit 提交一个 mutation，或者通过 context.state 和 context.getters 来获取 state 和 getters。当我们在之后介绍到 Modules 时，你就知道 context 对象为什么不是 store 实例本身了。
  // 通过 store.dispatch(type)方法触发action，参数为事件类型，需要和action中函数名称一致。
  // 在组件中分发Action: 在组件中使用 this.$store.dispatch('xxx') 分发 action，或者使用 mapActions 辅助函数将组件的 methods 映射为 store.dispatch 调用
  actions: {
    
    // 1、通过验证码登录后台
    Login({ commit }, userInfo) { // commit提交调用mutation；userInfo即为点击后传递过来的参数，此时是 用户登录信息
      const username = userInfo.username.trim()
      const password = userInfo.password
      const code = userInfo.code
      const uuid = userInfo.uuid
      const rememberMe = userInfo.rememberMe
      // Action 通常是异步的，那么如何知道 action 什么时候结束呢？更重要的是，我们如何才能组合多个 action，以处理更加复杂的异步流程？
      // 首先，你需要明白 store.dispatch 可以处理被触发的 action 的处理函数返回的 Promise，并且 store.dispatch 仍旧返回 Promise
      return new Promise((resolve, reject) => {
        // 一旦登录接口响应成功，会将返回的Token信息全局设置再请求头中，这样以后所有的请求中都携带这个请求头信息。
        // 具体可以看：src/utils/request.js中这段代码：config.headers['Authorization'] = getToken()
        // 这是全局配置axios实例，因为所有的API请求都需要经过这个request.js文件，所以其中的配置项对所有的请求都有效。
       
        login(username, password, code, uuid, rememberMe).then(response => {
        //  secretLogin(username, password, code, uuid, rememberMe).then(response => { // TODO base64加密接口参数
          // 这里前端登录的时候不要存储token，因为在request.js中已经存储了token，在登录成功的response响应拦截器中存储了token
          // const data = response.data
          // setToken(data.token)  //登录成功后将token存储在cookie之中，这个方法是定义在auth.js中，这个是前端处理存储用户信息的方式，后台是使用jwt验证
          // commit('SET_TOKEN', data.token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })


    },

    // 登录ERP
    LoginERP({ commit }, sso_service_ticket) {
      return new Promise((resolve, reject) => {
        loginerp(sso_service_ticket).then(response => {
          // const data = response.data
          // setToken(data.token) //登录成功后将token存储在cookie之中  这里是vuex储存token信息(setToken(token))，以便在request.js中使用config.headers[]全局定义
          // commit('SET_TOKEN', data.token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 2、获取用户信息 ，如果登录请求响应成功，想要进入系统的第二关就是调用获取用户信息的接口，全局设置用户信息(用户名、头像…) 。所以，vue-admin-template会立即再请求获取用户信息的接口：
    // get user info
    GetInfo({ commit, state }) { // commit提交调用mutation
      return new Promise((resolve, reject) => {
        getInfo().then(response => {
          const data = response.data
         // console.log('用户登录的数据：' + JSON.stringify(response.data))
          if (data.roles && data.roles.length > 0) { // 验证返回的roles是否是一个非空数组
            // 全局设置信息-角色信息，并存储到store中
            commit('SET_ROLES', data.roles)
            commit('SET_PERMISSIONS', data.permissions) // 权限
            //console.log('登录用户角色：' + JSON.stringify(data.roles))
          } else {
            // commit('SET_ROLES', ['ROLE_DEFAULT'])
            reject('getInfo: roles must be a non-null array !')
            // reject('Verification failed, please Login again.')
          }
          // 全局设置信息-姓名 commit将状态信息提交到mutations处理，第一个参数为事件类型，需要和mutation中函数名称一致；第二个参数为要传递的参数。mutation中的函数接受 state 作为其第一个参数
          // commit分发多重 mutation，调用mutation中对应相同名称的方法
          commit('SET_ID', data.id) // 用户ID
          commit('SET_NAME', data.name)// 姓名
          commit('SET_AVATAR', data.avatar) // 头像
          commit('SET_MENUS', data.menus) // 菜单
          commit('SET_ERP', data.erp)
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 登出
    // user logout
    LogOut({ commit, state }) { // commit 提交；state即为传递过来的参数
      return new Promise((resolve, reject) => {
        // 从state中获取token，然后作为logout方法的参数
        logout(state.token).then(() => {
          // commit分发多重 mutation，调用mutation中对应相同名称的方法
          // 重置token值
          commit('SET_TOKEN', '')
          // 重置角色信息
          // commit('SET_ROLES', [])
          // commit('SET_PERMISSIONS', [])
          // 重置用户信息，包括角色信息，菜单信息等。。
          commit('RESET_USER')
          // 移除Cookies中的token， removeToken()方法是auth.js中的方法
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    // remove token
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
       // commit('RESET_USER')
        removeToken()
        resolve()
      })
    }
  },
  // mutations:处理数据的唯一途径，state的改变或赋值只能在这里
  // mutation 相当于自定义组件中的methods
  // mutation是更改 Vuex 的 State 中数据的唯一方法
  // 通过 store.commit(type,data)调用 mutation，第一个参数为事件类型，需要和mutation中函数名称一致；第二个参数为要传递的参数。
  // mutation中的函数接受 state 作为其第一个参数。
  mutations: {
    // 回调函数SET_TOKEN:(),这个回调函数就是我们实际进行状态更改的地方，并且它会接受state作为第一个参数，额外的第二个参数为载荷(Payload)，在大多数情况下,载荷应该是一个对象，这样可以包含多个字段并且记录的 mutation 会更易读
    SET_TOKEN: (state, token) => {
      // 改变stat中的token的值
      state.token = token
    },
    // 回调函数 SET_NAME:() 对数据进行赋值，类似Java中的set方法，存储的数据是存储在vuex的store中，获取值的方式是在getter中获取
    SET_ID: (state, id) => { // 登录id
      state.id = id
    },
    SET_NAME: (state, name) => { // 姓名状态的改变
      state.name = name
    },
    SET_AVATAR: (state, avatar) => { // 头像
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => { // 角色
      state.roles = roles
    },
    SET_MENUS: (state, menus) => { // 菜单
      state.menus = menus
    },
    SET_PERMISSIONS: (state, permissions) => { // 权限
      state.permissions = permissions
    },
    SET_ERP: (state, erp) => { // erp
      state.erp = erp
    },
    // 重置数据
    RESET_USER: (state) => {
      state.id = '';
      state.name = '';
      state.avatar = '';
      state.erp = '';
      state.roles = [];
      state.menus = [];
      state.permissions = [];
    }
  }

  // 在组件中提交 Mutation
  // 1、你可以在组件中使用 this.$store.commit('xxx') 提交 mutation，
  // 2、或者使用 mapMutations 辅助函数将组件中的 methods 映射为 store.commit 调用

}

export default user // 导出user并在 index.js中引用注册到modules中。

// 总结： https://blog.csdn.net/Charissa2017/article/details/105308516
// 父组件和子组件间通信：
// 父向子传递数据是通过props，子向父传递数据是通过event($emit)；
// 通过父链/子链进行数据传递($parent / $children)；
// 通过 ref 也可以访问组件实例；
// 依赖注入：provide / inject；
// 兄弟组件间通信：
// event bus
// Vuex
// 跨级组件间通信：
// event bus；
// Vuex；
// 依赖注入：provide / inject；
