import request from '@/utils/request'

export function fetchDictGroupList(params) {
  return request({
    url: '/sysmgr/dict/group/list',
    method: 'get',
    params
  })
}

export function createDictGroup(param) {
  return request({
    url: '/sysmgr/dict/group/save',
    method: 'post',
    data: param
  })
}

export function deleteDictGroup(param) {
  return request({
    url: '/sysmgr/dict/group/delete/',
    method: 'post',
    data: param
  })
}

