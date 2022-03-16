import request from '@/utils/request'

export function fetchOrganizationList(query) {
  return request({
    url: '/sysmgr/organization/list',
    method: 'get',
    params: query
  })
}

export function createOrganization(data) {
  return request({
    url: '/sysmgr/organization/add',
    method: 'post',
    data
  })
}

export function updateOrganization(data) {
  return request({
    url: '/sysmgr/organization/update',
    method: 'post',
    data
  })
}

export function deleteOrganization(id) {
  return request({
    url: '/sysmgr/organization/delete/' + id,
    method: 'post'
  })
}

