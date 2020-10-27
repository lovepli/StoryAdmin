import request from '@/utils/request'

export function getList(data) {
  return request({
    url: '/test/carmodel/list',
    method: 'post',
    data
  })
}

export function findById(param) {
  return request({
    url: '/test/carmodel/find',
    method: 'post',
    data: param
  })
}

export function save(param) {
  return request({
    url: '/test/carmodel/save',
    method: 'post',
    data: param
  })
}

export function drop(param) {
  return request({
    url: '/test/carmodel/delete',
    method: 'post',
    data: param
  })
}

