'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

// 开发环境配置
module.exports = merge(prodEnv, {
  NODE_ENV: '"development"', //开发环境
  // BASE_API后端请求的URL跟路径，具体的接口请求放在/src/api/*.js中
  BASE_API: '"http://localhost:9430"', 
  // erp登录
  ERP_LOGIN_HREF: '"http://test.***.com/sso/login?ReturnUrl=http://localhost:9428/login_erp"',
  // erp登出
  ERP_LOGOUT_HREF: '"http://test.***.com/sso/logout?ReturnUrl=http://localhost:9428/"'
})
