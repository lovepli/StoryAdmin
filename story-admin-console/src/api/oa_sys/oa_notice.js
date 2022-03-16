import request from '@/utils/request'
import { search } from '@/utils/common'

// 获取文件列表
export function getNotices() {
  return request({
    url: '/oasys/notice/getNotices',
    method: 'get'
  })
}

export function markRead(data) {
  return request({
    url: `/oasys/notice/markRead?${search(data)}`,
    method: 'post',
    data: data
  })
}
