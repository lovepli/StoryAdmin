import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/test/car/list',
    method: 'get',
    params
  })
}

export function findById(param) {
  return request({
    url: '/test/car/find',
    method: 'post',
    data: param
  })
}

export function save(param) {
  return request({
    url: '/test/car/save',
    method: 'post',
    data: param
  })
}

export function drop(param) {
  return request({
    url: '/test/car/delete',
    method: 'post',
    data: param
  })
}

