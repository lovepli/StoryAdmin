import request from '@/utils/request'
import { search } from '@/utils/common'

// 附件管理接口

export function getList(params) {
  return request({
    url: '/sysmgr/att/list',
    method: 'get',
    params
  })
}

export function drop(param) {
  return request({
    url: '/sysmgr/att/delete',
    method: 'post',
    data: param
  })
}

// 上传
export function uploadFile(file, data) {
  var formData = new FormData()
  formData.append('file', file)
  return request({
    url: '/sysmgr/att/upload?' + search(data),
    method: 'post',
    data: formData
  })
}

export function downloadFile(id) {
  return request({
    url: '/sysmgr/att/download/' + id,
    method: 'get',
    responseType: 'blob'
  })
}

export function findFileInfoDetail(param) {
  return request({
    url: '/sysmgr/att/findFilePdfDetail',
    method: 'post',
    data: param
  })
}