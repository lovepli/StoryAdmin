import request from '@/utils/request'

// 登录接口
export function login(username, password) {
  return request({
    url: '/user/login',
    method: 'post',
    data: {
      username,
      password
    }
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

