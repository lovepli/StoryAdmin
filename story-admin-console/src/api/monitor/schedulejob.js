import request from '@/utils/request'

// 定时任务

// 分页查询
export function getList(params) {
  return request({
    url: '/monitor/schedulejob/list',
    method: 'get',
    params
  })
}

// 根据Id查询
export function findById(param) {
  return request({
    url: '/monitor/schedulejob/find',
    method: 'post',
    data: param
  })
}

// 保存
export function save(param) {
  return request({
    url: '/monitor/schedulejob/save',
    method: 'post',
    data: param
  })
}

// 删除
export function drop(param) {
  return request({
    url: '/monitor/schedulejob/delete',
    method: 'post',
    data: param
  })
}

// 获取下拉选项
export function getJobCombo(params) {
  return request({
    url: '/monitor/schedulejob/job-options',
    method: 'get',
    params
  })
}
