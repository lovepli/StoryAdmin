import request from '@/utils/request'

export function getList(data) {
  return request({
    url: '/test/treeandtable/list',
    method: 'post',
    data
  })
}

export function save(param) {
  return request({
    url: '/test/treeandtable/save',
    method: 'post',
    data: param
  })
}

export function findById(param) {
  return request({
    url: '/test/treeandtable/find',
    method: 'post',
    data: param
  })
}

export function drop(param) {
  return request({
    url: '/test/treeandtable/delete',
    method: 'post',
    data: param
  })
}

// 批量删除
export function batchDelete(ids) {
  return request({
    url: '/test/treeandtable/batch/delete/' + ids,
    method: 'delete'
  })
}

