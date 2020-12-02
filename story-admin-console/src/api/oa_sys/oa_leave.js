import request from '@/utils/request'
import { search } from '@/utils/common'

// 获取文件列表
export function askLeave(params) {
  return request({
    url: `/oasys/leave/askLeave?${search(params)}`,
    method: 'post',
    data: params
  })
}

export function checkLeave(param) {
  return request({
    url: `/oasys/leave/checkLeave`,
    method: 'post',
    data: param
  })
}

// 删除文件
export function getLeaves(params) {
  return request({
    url: `/oasys/leave/getLeaves?${search(params)}`,
    method: 'post',
    data: params
  })
}
