import axios from 'axios'
import {Notification, Message, MessageBox } from 'element-ui' // 单独引入element-ui的Message消息提示, MessageBox弹框
import store from '../store' // 引入store
import { setToken, getToken } from '@/utils/auth'

// 对axios请求进行全局封装
// axios拦截器的配置与使用
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API, //   // axios中请求配置有baseURL选项，表示请求URL公共部分 ，这里是从config配置文件中读取
  timeout: 20000, // 请求超时时间
  withCredentials: true // 使用Axios默认是不带cookie的，倘若需要，则需要在添加withCredentials: true属性。
})

// request拦截器，设置请求头参数，如用户标识token等
service.interceptors.request.use(config => {
    // 在发送请求之前做点什么
    // config.timeout = 4000;
    // console.log('service.interceptors.request')
    // Getter 会暴露为 store.getters 对象，你可以以属性的形式访问这些值，这里是通过属性访问token的值
    if (store.getters.token) { // 判断toke是否为null
    // 在此处设置请求头参数
    // 让每个请求携带自定义token--['Authorization']为自定义key 请根据实际情况自行修改
      config.headers['Authorization'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改 ，这里的getToken()方法是从Cookies中获取token
    }
    return config
  }, error => {
    // 发送请求失败的时候做点什么
    console.log(error) // for debug
    // MessageBox.alert(error);
    Promise.reject(error)
  }
)

// axios response 响应拦截器，请求接口得到相应后，需要进行一些预处理
service.interceptors.response.use(response => {
    // 在响应请求之前做点什么
    // console.log('service.interceptors.response res=' + JSON.stringify(response.data))

    // token 过期，获取刷新后的access-token
    var token = response.headers['authorization'];
    if (token) {
      setToken(token); // 登录成功之后将token存储在cookie中
      // 通过 store.commit(type,data)调用 mutation，第一个参数为事件类型，需要和mutation中函数名称一致；第二个参数为要传递的参数。
      store.commit('SET_TOKEN', token); // store.commit 方法触发状态变更,这里是变更全局存储的token状态,第一个参数为事件类型,第二个参数为载荷，这里是token
    }

    const res = response.data
    // 响应的拦截器中加入判断是否是附件，主要判断responseType是否是blob。
    // 判断响应类型是否是附件
    // 由于下载的是文件流，所以请求的方法配置参数需要把返回类型设置为blob，eg：responseType: 'blob'，另外注意axios的get和post方法的传参顺序问题。get方法第一个参数是url+ur后面的参数，第二个参数是请求配置参数；post：有三个参数，第一个是url，第二个是请求参数，第三个是请求的配置数据。
    // // judge is Blog response for file download
    if (res && res.constructor && res.constructor.toString()) {
      const constructorName = res.constructor.name
      if (constructorName && constructorName === 'Blob') { return res }
      const str = res.constructor.toString()
      let arr
      if (str.charAt(0) === '[') {
        arr = str.match(/\w+\s∗(\w+)\w+\s∗(\w+)/)
      } else {
        arr = str.match(/function\s*(\w+)/)
      }
      if (arr && arr.length === 2 && arr[1] === 'Blob') return res
    }

    // 下面的代码为通过response自定义code来标示请求状态，当code返回如下情况为权限有问题，登出并返回到登录页
    /**
     * code为非20000是抛错
     */
    console.log('service.interceptors.response res code=' + res.code)
    // 状态已过期 或者 HTTP 状态码为404
    if(res.code === 60003){
      MessageBox.confirm('登录状态已过期，您可以继续留在该页面，或者重新登录', '系统提示', {
        confirmButtonText: '重新登录',
        cancelButtonText: '取消',
        type: 'warning'
      }
    ).then(() => {
      store.dispatch('FedLogOut').then(() => {
        // location.reload() // 为了重新实例化vue-router对象 避免bug
        location.href = '/index';
      })
    })
    } else if(res.code === 50000){
      Message({
        message: res.message,
        type: 'error'
      })
      return Promise.reject(new Error(msg))
    } else if(res.code === 60001){
      Message({
        message: res.message,
        type: 'error'
      })
      return Promise.reject(new Error(msg))
    }else if(res.code === 60002){
      Message({
        message: res.message,
        type: 'error'
      })
      return Promise.reject(new Error(msg))
    }
    // else if(res.code === 60003){
    //   Message({
    //     message: res.message,
    //     type: 'error'
    //   })
    //   return Promise.reject(new Error(msg))
    // } 
    else if(res.code !== undefined && res.code !== null && res.code !== 20000){
      Notification.error({
        title: res.message
      })
      return Promise.reject('error')
    } else {
      // console.log('service.interceptors.response return data')
      // 将后台接口调用的message结果响应显示在前端页面 ,result可以要也可以不要，result表示接口调用结果，true为成功，false为失败
      // if (res.message !== null) {
      if (res.message !== null && res.result === true) {
        Message({
          message: res.message,
          type: 'success',
          duration: 3 * 1000 // 持续事件主动关闭消息弹框
        })
      }
      return res // 返回请求成功结果
    }


    // 成功返回结果的逻辑。根据接口定义的数据返回格式 状态码不是20000的返回的message都是错误提示信息
    // if (res.code !== undefined && res.code !== null && res.code !== 20000) {
    //   // element-ui的消息弹框,因为这里是单独引入Message，所以调用方式不是this.$message()打开消息弹框
    //   // 可以在vm实例中通过this.$message(options)方法来调用出message，也可以通过在文件中单独引入Message,通过Message(options)来调用
    //   Message({
    //     message: res.message ? res.message : '请求错误',
    //     type: 'error',
    //     duration: 3 * 1000 // 持续事件主动关闭消息弹框
    //   })

    //   // 60001:非法的token; 60002:其他客户端登录了;  60003:Token 过期了;
    //   // 如果是token过期的状况，退出登录重定向到登陆页
    //   if (res.code === 60001 || res.code === 60002 || res.code === 60003 || res.code === 401) {
    //     // element-ui的消息弹框,因为这里是单独引入MessageBox，所以调用方式不是this.$confirm()打开消息弹框
    //     MessageBox.confirm(
    //       '登录状态已过期，您可以继续留在该页面，或者重新登录！', '系统提示', {
    //         confirmButtonText: '重新登录',
    //         cancelButtonText: '取消',
    //         type: 'warning'
    //       }
    //     ).then(() => {
    //       // 分发Action 通过 store.dispatch(type)方法触发action，参数为事件类型，需要和action中函数名称一致。
    //       store.dispatch('FedLogOut').then(() => { // 前端登出，移除token
    //         location.reload() // 为了重新实例化vue-router对象 避免bug
    //       })
    //     })
    //   }
    //   return Promise.reject(new Error(res.message || '请求错误'))
    // } else {
    //   // console.log('service.interceptors.response return data')
    //   // 将后台接口调用的message结果响应显示在前端页面 ,result可以要也可以不要，result表示接口调用结果，true为成功，false为失败
    //   // if (res.message !== null) {
    //   if (res.message !== null && res.result === true) {
    //     Message({
    //       message: res.message,
    //       type: 'success',
    //       duration: 3 * 1000 // 持续事件主动关闭消息弹框
    //     })
    //   }
    //   return res // 返回请求成功结果
    // }
  }, error => {
    // 请求失败的时候做点什么
    console.log('err' + error)
    // element-ui的消息弹框,因为这里是单独引入Message，所以调用方式不是this.$message()打开消息弹框
    let { message } = error;
    if (message === 'Network Error') {
      message = '后端接口连接异常';
    } else if (message.includes('timeout')) {
      message = '系统接口请求超时';
    } else if (message.includes('Request failed with status code')) {
      message = '系统接口' + message.substr(message.length - 3) + '异常';
      console.log('err' + message)
    }
    Message({
      message: message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
