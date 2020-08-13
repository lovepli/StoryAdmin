import request from '@/utils/request'
// import { search } from '@/utils/common'

// 列表查询
export function getInformList(param) {
  return request({
    url: `/sysmgr/inform/list`,
    method: 'get',
    data: param
  })
}

import { search } from '@/utils/common'
export function queryInform(cond, page) {
  const params = {
    status: cond.status,
    title: cond.title,
    creatorId: cond.creatorId,
    top: cond.topFirst,
    page: page.num,
    limit: page.size
  }
  const { createDate } = cond.createDate
  if (createDate && createDate.length === 2) {
    params.startDate = createDate[0].getTime()
    params.endDate = createDate[1].getTime()
  }
  return request({
    url: `/sysmgr/inform/list?${search(params)}`,
    method: 'get'
  })
}

// 查看详情
export function findById(id) {
  return request({
    url: `/inform/find/${id}`,
    method: 'get'
  })
}

// 过期
export function outdateInform(id) {
  return request({
    url: `/sysmgr/inform/${id}/outdate`,
    method: 'post'
  })
}

// 撤销
export function cancelInform(id) {
  return request({
    url: `/sysmgr/inform/${id}/cancel`,
    method: 'post'
  })
}

// 取消置顶
export function untopInform(id) {
  return request({
    url: `/sysmgr/inform/${id}/untop`,
    method: 'post'
  })
}

// 置顶
export function topInform(id) {
  return request({
    url: `/sysmgr/inform/${id}/top`,
    method: 'post'
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

