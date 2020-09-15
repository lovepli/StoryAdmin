import request from '@/utils/request'
import { search } from '@/utils/common'
// 登录日志

export function getList(params) {
  return request({
    url: '/sysmgr/loginlog/list',
    method: 'get',
    params
  })
}

// 删除
export function drop(param) {
  return request({
    url: '/sysmgr/loginlog/delete',
    method: 'post',
    data: param
  })
}

// 批量删除
export function delLoginLogs(ids) {
  return request({
    url: '/sysmgr/loginlog/deletes/' + ids,
    method: 'delete'
  })
}

/**
 *根据账户名查询登录信息
 * @param {*} cond
 * @param {*} page
 */
export function queryLog(cond, page) {
  const params = {
    ua: cond.userAccount,
    page: page.num,
    size: page.limit
  }
  return request({
    url: '/sysmgr/loginlog/querylogsByAccount?' + search(params),
    method: 'get'
  })
}

// 导出登录日志
export function exportLogininfor(query) {
  return request({
    url: '/sysmgr/loginlog/export',
    method: 'get',
    params: query
  })
}

