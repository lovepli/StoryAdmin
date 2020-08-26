import request from '@/utils/request'

// 登录接口
// export function login(username, password) {
//   return request({
//     url: '/user/login',
//     method: 'post',
//     data: {
//       username,
//       password
//     }
//   })
// }

// 验证码登录方法
export function login(username, password, code, uuid, rememberMe) {
  const data = {
    username,
    password,
    code,
    uuid,
    rememberMe
  }
  return request({
    url: '/user/login',
    method: 'post',
    data: data
  })
}
// 用户信息
export function getInfo() {
  return request({
    url: '/user/info',
    method: 'post'
    // params: { token } // 传递token作为参数
  })
}

// 登出
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

// erp登录验证ticket
export function loginerp(sso_service_ticket) {
  var params = null;
  if (sso_service_ticket) {
    params = { sso_service_ticket };
  }
  return request({
    url: '/user/valid_erp',
    method: 'post',
    params: params
  })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/captchaImage',
    method: 'get'
  })
}

