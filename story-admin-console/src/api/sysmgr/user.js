import request from '@/utils/request'
// import md5 from 'js-md5'
import { search } from '@/utils/common'

// 用户管理
// 查询用户列表
export function getList(params) {
  return request({
    url: '/sysmgr/user/list',
    method: 'get',
    params
  })
}
// 根据Id查询
export function findById(param) {
  return request({
    url: '/sysmgr/user/find',
    method: 'post',
    data: param
  })
}
// 更改/保存
export function save(param) {
  return request({
    url: '/sysmgr/user/save',
    method: 'post',
    data: param
  })
}
// 删除
export function drop(param) {
  return request({
    url: '/sysmgr/user/delete',
    method: 'post',
    data: param
  })
}

// 根据用户ID查询用户的角色
export function findUserRole(param) {
  return request({
    url: '/sysmgr/user/findUserRole',
    method: 'post',
    data: param
  })
}

// 根据登录账户获取所有角色
export function findRoleListByAccount(cond) {
  const params = {
    ua: cond.userName
  }
  return request({
    url: '/sysmgr/user/findRolelistByAccount?' + search(params),
    method: 'post',
    data: params
  })
}

// 更改/保存用户角色
export function saveUserRole(param) {
  return request({
    url: '/sysmgr/user/saveUserRole',
    method: 'post',
    data: param
  })
}

// 修改密码
export function editpassword(param) {
  return request({
    url: '/sysmgr/user/editpassword',
    method: 'post',
    data: param
  })
}

/**
 * 查询所有用户名
 */
export function findAllUserList() {
  return request({
    url: '/sysmgr/user/UserNameList',
    method: 'get'
  })
}

/**
 * 修改用户信息
 * @param {*} temp 
 */
// export function updateUserInfo(temp) {
//   var body = {
//     account: temp.account,
//     name: temp.name,
//     password: md5(temp.password).toUpperCase(),
//     new_password: temp.new_password ? md5(temp.new_password).toUpperCase() : undefined,
//     phone: temp.phone
//   }
//   return request({
//     url: '/user',
//     method: 'put',
//     data: body
//   })
// }

