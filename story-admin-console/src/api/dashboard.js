import request from '@/utils/request'

// 首页的天气接口
export function getWeather(params) {
  return request({
    url: '/weather',
    method: 'get',
    params
  })
}

