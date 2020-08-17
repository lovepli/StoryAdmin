import request from '@/utils/request'

// 部门接口
// 加载树
export function getList(param) {
  return request({
    url: '/sysmgr/dept/list',
    method: 'post',
    data: param
  })
}

export function save(param) {
  return request({
    url: '/sysmgr/dept/save',
    method: 'post',
    data: param
  })
}

export function drop(param) {
  return request({
    url: '/sysmgr/dept/delete',
    method: 'post',
    data: param
  })
}
