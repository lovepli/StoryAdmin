import request from '@/utils/request'

// axios通用公共配置 axios用于请求数据接口
// 分别处理get和post请求，get和post请求携带参数的方式是不同的，所以要分开定义。
export function getRequest(url, params) {
  return request({
    url: url,
    method: 'get',
    params
  })
}

export function postRequest(url, params) {
  return request({
    url: url,
    method: 'post',
    data: params
  })
}
