import axios from 'axios';

const request = axios.create({
  responseType: 'json',
  timeout: 300000,
  timeoutErrorMessage: '请求超时',
  withCredentials: true, // 支持请求带有cookie

})

request.interceptors.request.use(function (config: any) {
  config.headers.common.token = window.localStorage.getItem('token')
  return new Promise(resolve => {
    // 模拟等待refresh_token
    setTimeout(function (configParam:any) {
      resolve(configParam)
    }, 2000, config)
  })
})
request.interceptors.response.use(function (response) {
  // 对响应数据做点什么
  return response
}, function (error) {
  // 对响应错误做点什么
  return error
})

interface requestInfo {
  url: string,
  method: string,
  failed?: Function|any,
  success?: Function|any,
  data: Object,
  config?: Object| any
}
const zlaxios = {
  request: function (requestInfo: requestInfo) {
    const url = requestInfo.url
    const method = requestInfo.method
    if (method === undefined || method === 'get') {
      request.get(url).then(reseponse => {
        if (reseponse.request.status !== 200) {
          requestInfo.failed(reseponse)
        } else {
          const code = reseponse.data.code
          if (code === '000000') {
            requestInfo.success(reseponse.data)
          } else {
            requestInfo.failed(reseponse.data)
          }
        }
      })
    } else {
      const token = localStorage.getItem('token')
      if (token) {
        document.cookie = 'token=' + token
      }
      request.post(url, requestInfo.data, requestInfo.config).then(reseponse => {
        if (reseponse.request.status !== 200) {
          requestInfo.failed(reseponse)
        } else {
          if (url && url.indexOf('/oauth/token') !== -1) {
            window.localStorage.setItem('token', reseponse.data.access_token)
            requestInfo.success(reseponse.data)
            return
          }
          const code = reseponse.data.code
          if (code.startsWith('000')) {
            requestInfo.success(reseponse.data)
          } else {
            if (requestInfo.failed) {
              requestInfo.failed(reseponse.data)              
            } else {
              alert(reseponse.data.msg)
            }
          }
        }
      })
    }
  }
}

export default zlaxios