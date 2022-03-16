import request from '@/utils/request'

export function getList(data) {
  return request({
    url: '/test/treetable/list',
    method: 'post',
    data
  })
}

// export function save(data) {
//   return request({
//     url: '/test/treetable/save',
//     method: 'post',
//     data
//   })
// }

export function add(data) {
  return request({
    url: '/test/treetable/add',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/test/treetable/update',
    method: 'post',
    data
  })
}

export function findById(param) {
  return request({
    url: '/test/treetable/find',
    method: 'post',
    data: param
  })
}

export function drop(param) {
  return request({
    url: '/test/treetable/delete',
    method: 'post',
    data: param
  })
}

