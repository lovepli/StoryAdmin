import request from '@/utils/request'
import { search } from '@/utils/common'

// 获取文件列表
export function askLeave(param) {
  return request({
    url: '/oasys/leave/askLeave',
    method: 'post',
    param
  })
}

export function checkLeave(param) {
  return request({
    url: `/oasys/leave/askLeave`,
    method: 'post',
    data: param
  })
}

// 删除文件
export function getLeaves(params) {
  return request({
    url: `/oasys/leave/askLeave?${search(params)}`,
    method: 'get',
    data: params
  })
}
