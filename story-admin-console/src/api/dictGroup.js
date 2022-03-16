import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/sysmgr/dict/group/list',
    method: 'get',
    params
  })
}

export function save(param) {
  return request({
    url: '/sysmgr/dict/group/save',
    method: 'post',
    data: param
  })
}

export function drop(param) {
  return request({
    url: '/sysmgr/dict/group/delete/',
    method: 'post',
    data: param
  })
}

