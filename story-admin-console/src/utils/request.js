import axios from 'axios'
import { Message, MessageBox } from 'element-ui' // 单独引入element-ui的Message消息提示, MessageBox弹框
import store from '../store' // 引入store
import { setToken, getToken } from '@/utils/auth'

// 对axios请求进行全局封装
// axios拦截器的配置与使用

// 创建axios实例
const service = axios.create({
  baseURL: process.env.BASE_API, // api的base_url 读取config配置文件
  timeout: 20000, // 请求超时时间
  withCredentials: true // 使用Axios默认是不带cookie的，倘若需要，则需要在添加withCredentials: true属性。
})

// request拦截器，设置请求头参数，如用户标识token等
service.interceptors.request.use(
  config => {
    // 在发送请求之前做点什么
    // console.log('service.interceptors.request')
    // Getter 会暴露为 store.getters 对象，你可以以属性的形式访问这些值，这里是通过属性访问token的值
    if (store.getters.token) { // 判断toke是否为null
    // 在此处设置请求头参数
    // 让每个请求携带自定义token--['Authorization']为自定义key 请根据实际情况自行修改
      config.headers['Authorization'] = getToken() // 从Cookies中获取token
    }
    return config
  },
  error => {
    // 发送请求失败的时候做点什么
    // console.log(error) // for debug
    Promise.reject(error)
  }
)

// axios response 拦截器，请求接口得到相应后，需要进行一些预处理
service.interceptors.response.use(
  response => {
    // 在响应请求之前做点什么
    // console.log('service.interceptors.response res=' + JSON.stringify(response.data))

    // token 过期，获取刷新后的access-token
    var token = response.headers['authorization'];
    if (token) {
      setToken(token); // 登录成功之后将token存储在cookie中
      // 通过 store.commit(type,data)调用 mutation，第一个参数为事件类型，需要和mutation中函数名称一致；第二个参数为要传递的参数。
      store.commit('SET_TOKEN', token); // store.commit 方法触发状态变更,这里是变更全局存储的token状态,第一个参数为事件类型,第二个参数为载荷，这里是token
    }

    // eslint-disable-next-line eqeqeq
    if (response.config && response.config.responseType == 'blob') {
      const blob = new Blob([response.data], { type: 'application/octet-stream;charset=utf-8' }); // application/vnd.openxmlformats-officedocument.spreadsheetml.sheet这里表示xlsx类型
      const filename = decodeURI(response.headers['filename']);
      if ('download' in document.createElement('a')) {
        const downloadElement = document.createElement('a');
        let href = '';
        if (window.URL) href = window.URL.createObjectURL(blob);
        else href = window.webkitURL.createObjectURL(blob);
        downloadElement.href = href;
        downloadElement.download = filename;
        document.body.appendChild(downloadElement);
        downloadElement.click();
        if (window.URL) window.URL.revokeObjectURL(href);
        else window.webkitURL.revokeObjectURL(href);
        document.body.removeChild(downloadElement);
      } else {
        navigator.msSaveBlob(blob, filename);
      }
      return;
    }

    // 下面的代码为通过response自定义code来标示请求状态，当code返回如下情况为权限有问题，登出并返回到登录页
    /**
     * code为非20000是抛错
     */
    const res = response.data
    // console.log('service.interceptors.response res code=' + res.code)
    // 成功返回结果的逻辑。根据接口定义的数据返回格式 修改判断条件
    if (res.code !== 20000) {
      // element-ui的消息弹框,因为这里是单独引入Message，所以调用方式不是this.$message()打开消息弹框
      // 可以在vm实例中通过this.$message(options)方法来调用出message，也可以通过在文件中单独引入Message,通过Message(options)来调用
      Message({
        message: res.message,
        type: 'error',
        duration: 5 * 1000 // 持续事件后主动关闭消息弹框
      })

      // 50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;
      // 如果是token过期的状况，退出登录重定向到登陆页
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // element-ui的消息弹框,因为这里是单独引入MessageBox，所以调用方式不是this.$confirm()打开消息弹框
        MessageBox.confirm(
          '登录已超时，请重新登录！可以取消继续留在该页面，或者重新登录', '确定登出', {
            confirmButtonText: '重新登陆',
            cancelButtonText: '取消',
            type: 'warning'
          }
        ).then(() => {
          // 分发Action 通过 store.dispatch(type)方法触发action，参数为事件类型，需要和action中函数名称一致。
          store.dispatch('FedLogOut').then(() => { // 前端登出，移除token
            location.reload() // 为了重新实例化vue-router对象 避免bug
          })
        })
      }
      return Promise.reject(new Error(res.msg || '请求错误'))
    } else {
      // console.log('service.interceptors.response return data')
      return response.data // 返回请求成功结果
    }
  },
  error => {
  // 请求失败的时候做点什么
    // console.log('err' + error) // for debug
    // element-ui的消息弹框,因为这里是单独引入Message，所以调用方式不是this.$message()打开消息弹框
    Message({
      message: (error && error.response && error.response.status) ? error.response.status + '  错误' : '页面超时，请按F5刷新页面',
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
