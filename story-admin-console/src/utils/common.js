/**
 * 对象转search参数
 * @param {参数列表} params
 */
export function search(params) {
  var paramArr = []
  var i = 0
  for (var x in params) {
    var v = params[x]
    if (v !== undefined && v !== null && v !== '') {
      paramArr[i] = x + '=' + v
      i = i + 1
    }
  }
  return paramArr.length ? paramArr.reduce((a, b) => a + '&' + b) : ''
}

/**
 * 通过id获取数组内元素
 * @param {数组} arr
 * @param {id} id
 */
export function getById(arr, id) {
  var ret
  if (arr !== undefined) { // 必须加判断  不然会不识别forEach
    arr.forEach(element => {
      if (element.id === id) ret = element
    });
  }
  return ret
}

const baseURL = process.env.BASE_API
// 通用导出方法
export function download(fileName) {
  window.location.href = baseURL + '/common/download?fileName=' + encodeURI(fileName) + '&delete=' + true;
}

