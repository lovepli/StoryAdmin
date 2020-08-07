import request from '@/utils/request'

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
