import request from '@/utils/request'

// 附件管理接口

export function getList(params) {
  return request({
    url: '/sysmgr/att/list',
    method: 'get',
    params
  })
}

export function drop(param) {
  return request({
    url: '/sysmgr/att/delete',
    method: 'post',
    data: param
  })
}

