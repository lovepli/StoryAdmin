import request from '@/utils/request'
import { search } from '@/utils/common'

// 获取文件列表
export function getFiles(params) {
  return request({
    url: '/oasys/file/getFiles',
    method: 'get',
    params
  })
}

export function addFolder(params) {
  return request({
    url: `/oasys/file/addFolder?${search(params)}`,
    method: 'get',
    data: params
  })
}

// 删除文件
export function deleteFile(param) {
  return request({
    url: '/oasys/file/deleteFiles',
    method: 'post',
    data: param
  })
}

// 给文件重新命名
export function renameFile(params) {
  return request({
    url: `/oasys/file/renameFile?${search(params)}`,
    method: 'get',
    data: params
  })
}

// 下载文件不需要写一个专门的接口
export function downloadFile(value) {
  return request({
    url: value,
    method: 'get',
    responseType: 'blob'
  })
}

