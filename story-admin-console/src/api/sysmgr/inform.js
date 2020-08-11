import request from '@/utils/request'
import { search } from '@/utils/common'

// 列表查询
export function getList(cond, page) {
  const params = {
    status: cond.status,
    title: cond.title,
    creator: cond.operator,
    tf: cond.topFirst,
    // 分页
    pageNo: page.num,
    limit: page.size
  }
  const { createDate } = cond
  if (createDate && createDate.length === 2) {
    params.sd = params[0].getTime()
    params.ed = params[1].getTime()
  }
  return request({
    url: `/sysmgr/inform/list?${search(params)}`,
    method: 'get'
  })
}

// 根据Id查询
export function findById(param) {
  return request({
    url: '/sysmgr/inform/find',
    method: 'post',
    data: param
  })
}

// 过期
export function outdateInform(param) {
  return request({
    url: '/sysmgr/inform/outdate',
    method: 'post',
    data: param
  })
}

// 撤销
export function cancelInform(param) {
  return request({
    url: '/sysmgr/inform/cancel',
    method: 'post',
    data: param
  })
}

// 取消置顶
export function untopInform(param) {
  return request({
    url: '/sysmgr/inform/untop',
    method: 'post',
    data: param
  })
}

// 置顶
export function topInform(param) {
  return request({
    url: '/sysmgr/inform/top',
    method: 'post',
    data: param
  })
}

// // 添加
export function save(param) {
  var expiration = param.expiration
  if (expiration && expiration.getTime) {
    param.expiration = expiration.getTime()
  }
  return request({
    url: '/sysmgr/inform/save',
    method: 'post',
    data: param
  })
}

