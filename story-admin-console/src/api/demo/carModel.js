import request from '@/utils/request'

export function getList(data) {
  return request({
    url: '/sysmgr/carmodel/list',
    method: 'post',
    data
  })
}

export function findById(param) {
  return request({
    url: '/sysmgr/carmodel/find',
    method: 'post',
    data: param
  })
}

export function save(param) {
  return request({
    url: '/sysmgr/carmodel/save',
    method: 'post',
    data: param
  })
}

export function drop(param) {
  return request({
    url: '/sysmgr/carmodel/delete',
    method: 'post',
    data: param
  })
}

