import request from '@/utils/request'

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

import { search } from '@/utils/common'

// 上传
export function uploadFile(file, data) {
  var formData = new FormData()
  formData.append('file', file)
  return request({
    url: '/file/upload?' + search(data),
    method: 'post',
    data: formData
  })
}

