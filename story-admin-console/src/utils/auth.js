import Cookies from 'js-cookie' // 使用js-cookie插件

// 定义全局常量TokenKey
const TokenKey = 'Admin-Token'

const SSOTokenKey = 'sso.***.com'

//  vuex存储token的方式：登录成功之后将token存储在cookie中，并设置TokenKey
export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

// 从Cookies中获取token
export function getToken() {
  return Cookies.get(TokenKey)
}

// 移除Cookies中的token,erp系统会用到
export function removeToken() {
  if (getERPToken(SSOTokenKey)) {
    // 移除浏览器存储的cookie
    return Cookies.remove(SSOTokenKey)
  }
  return Cookies.remove(TokenKey)
}

export function getERPToken() {
  return Cookies.get(SSOTokenKey)
}

