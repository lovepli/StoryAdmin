import request from '@/utils/request'

// 工单历史记录

export function getList(params) {
  return request({
    url: '/sysmgr/callhistory/list',
    method: 'get',
    params
  })
}

export function findById(param) {
  return request({
    url: '/sysmgr/callhistory/find',
    method: 'post',
    data: param
  })
}

export function save(param) {
  return request({
    url: '/sysmgr/callhistory/save',
    method: 'post',
    data: param
  })
}

export function drop(param) {
  return request({
    url: '/sysmgr/callhistory/delete',
    method: 'post',
    data: param
  })
}

