import request from '@/utils/request'
import { search } from '@/utils/common'

// 获取文件列表
export function getAttendances() {
  return request({
    url: '/oasys/sign/getAttendances',
    method: 'get'
  })
}

export function getAttendance() {
  return request({
    url: `/oasys/sign/getAttendance`,
    method: 'get'
  })
}

// 删除文件
export function signIn() {
  return request({
    url: '/oasys/sign/signIn',
    method: 'get'
  })
}

// 给文件重新命名
export function signOut() {
  return request({
    url: `/oasys/sign/signOut`,
    method: 'get'
  })
}

// 给文件重新命名
export function getAttendanceTime() {
  return request({
    url: `/oasys/sign/getAttendanceTime`,
    method: 'get'
  })
}

export function setAttendanceTime(params) {
  return request({
    url: `/oasys/sign/setAttendanceTime?${search(params)}`,
    method: 'get',
    data: params
  })
}

