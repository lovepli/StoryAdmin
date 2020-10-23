import request from '@/utils/request'

export function fetchDictGroupList(params) {
  return request({
    url: '/sys/dict/group/list',
    method: 'get',
    params
  })
}

export function createDictGroup(param) {
  return request({
    url: '/sys/dict/group/save',
    method: 'post',
    data: param
  })
}

export function deleteDictGroup(param) {
  return request({
    url: '/sys/dict/group/delete/' + param,
    method: 'post',
    data: param
  })
}

