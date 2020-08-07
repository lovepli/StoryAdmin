import request from '@/utils/request'

// 系统备份

export function getList(params) {
  return request({
    url: '/sysmgr/backup/list',
    method: 'get',
    params
  })
}

export function findById(param) {
  return request({
    url: '/sysmgr/backup/find',
    method: 'post',
    data: param
  })
}

export function drop(param) {
  return request({
    url: '/sysmgr/backup/delete',
    method: 'post',
    data: param
  })
}
