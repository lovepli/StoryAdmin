import request from '@/utils/request'

// 获取文件列表
export function getFiles(params) {
  return request({
    url: '/oasys/file/getFiles',
    method: 'get',
    params
  })
}
// 添加文件目录
export function addFolder(param) {
  return request({
    url: '/oasys/file/addFolder',
    method: 'get',
    data: param
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
export function renameFile(param) {
  return request({
    url: '/oasys/file/renameFile',
    method: 'get',
    data: param
  })
}

