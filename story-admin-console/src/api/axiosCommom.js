import request from '@/utils/request'
import Secret from "@/utils/Secret"

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


/**
 * post 方法(加密传输)
 */
// export function postSecret(url, params) {
//  // 对业务参数进行加密处理（需将业务参数转为string文本处理，否则加解密异常）
//  var secretString = Secret.encode(JSON.stringify(params));
//  let params2={
//   encryptData:secretString
//  }
//  // console.log(secretString);
//   return request({
//     url: url,
//     method: 'post',
//     data: params2
//   })
// }


