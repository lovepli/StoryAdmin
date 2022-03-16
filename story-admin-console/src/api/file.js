import request from '@/utils/request'
import { search } from '@/utils/common'

// 上传文件
export function uploadFile(file, data) {
  var formData = new FormData()
  formData.append('file', file)
  return request({
    url: '/sysmgr/file/upload?' + search(data),
    method: 'post',
    data: formData
  })
}

// 删除文件
export function deleteFile(fileId) {
  return request({
    url: '/sysmgr/file/' + fileId,
    method: 'delete'
  })
}

// 下载文件 点击下载按钮的事件中加上responseType: 'blob'用于后续拦截响应时的检查判断。
export function downloadFile(id) {
  return request({
    url: '/sysmgr/file/download/' + id,
    method: 'get',
    responseType: 'blob'
  })
}
