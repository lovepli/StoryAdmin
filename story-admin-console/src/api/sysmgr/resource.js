import request from '@/utils/request'

// 菜单接口
// 加载菜单树
export function getList(param) {
  return request({
    url: '/sysmgr/resource/list',
    method: 'post',
    data: param
  })
}

export function save(param) {
  return request({
    url: '/sysmgr/resource/save',
    method: 'post',
    data: param
  })
}

export function drop(param) {
  return request({
    url: '/sysmgr/resource/delete',
    method: 'post',
    data: param
  })
}
