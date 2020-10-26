import request from '@/utils/request'

// 取出字典
export function fetchDicts() {
  return request({
    url: '/sysmgr/dict',
    method: 'get'
  })
}

// 查看列表
export function fetchDictList(params) {
  return request({
    url: '/sysmgr/dict/list',
    method: 'get',
    params
  })
}

// 保存
export function createDict(param) {
  return request({
    url: '/sysmgr/dict/save',
    method: 'post',
    data: param
  })
}

// 删除
export function deleteDict(param) {
  return request({
    url: '/sysmgr/dict/delete',
    method: 'post',
    data: param
  })
}
