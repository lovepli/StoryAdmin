import request from '@/utils/request'

// 系统日志

export function getList(params) {
  return request({
    url: '/sysmgr/syslog/list',
    method: 'get',
    params
  })
}

export function findById(param) {
  return request({
    url: '/sysmgr/syslog/find',
    method: 'post',
    data: param
  })
}
