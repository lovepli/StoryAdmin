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

export function drop(param) {
  return request({
    url: '/sysmgr/loginlog/delete',
    method: 'post',
    data: param
  })
}

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

