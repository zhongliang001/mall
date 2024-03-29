import axios from 'axios'
import type { Ref } from 'vue'

const baseUrl = import.meta.env.VITE_BASE_URL
const request = axios.create({
  responseType: 'json',
  timeout: 300000,
  timeoutErrorMessage: '请求超时',
  withCredentials: true // 支持请求带有cookie
})

request.interceptors.request.use(function (config: any) {
  config.headers.common.token = window.localStorage.getItem('token')
  return new Promise((resolve) => {
    // 模拟等待refresh_token
    setTimeout(
      function (configParam: any) {
        resolve(configParam)
      },
      2000,
      config
    )
  })
})
request.interceptors.response.use(
  function (response) {
    // 对响应数据做点什么
    return response
  },
  function (error) {
    // 对响应错误做点什么
    return error
  }
)

interface requestInfo {
  url: string
  method: string
  failed?: Function | any
  success?: Function | any
  data?: Object | any
  config?: Object | any
  params?: Object | any
  loading?: Ref<boolean>
}
const zlaxios = {
  request: function (requestInfo: requestInfo) {
    const url = baseUrl + requestInfo.url
    const method = requestInfo.method
    if (requestInfo.loading !== undefined) {
      requestInfo.loading.value = true
    }
    if (method === undefined || method === 'get') {
      request.get(url, { params: requestInfo.params }).then((reseponse) => {
        if (requestInfo.loading !== undefined) {
          requestInfo.loading.value = false
        }
        if (reseponse.request.status !== 200) {
          if (reseponse.request.status === 401) {
            alert('登录失效，请重新登录')
            window.localStorage.setItem('token', '')
            location.reload()
          } else {
            requestInfo.failed(reseponse)
          }
        } else {
          const code = reseponse.data.code
          if (code.startsWith('000')) {
            requestInfo.success(reseponse.data)
          } else if (code === '111111') {
            alert('登录失效，请重新登录')
            window.localStorage.setItem('token', '')
            location.reload()
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
      request.post(url, requestInfo.data, requestInfo.config).then((reseponse) => {
        if (requestInfo.loading !== undefined) {
          requestInfo.loading.value = false
        }
        if (reseponse.request.status !== 200) {
          if (reseponse.request.status === 401) {
            alert('登录失效，请重新登录')
            window.localStorage.setItem('token', '')
            location.reload()
          } else {
            requestInfo.failed(reseponse)
          }
        } else {
          if (url && url.indexOf('/oauth/token') !== -1) {
            window.localStorage.setItem('token', reseponse.data.access_token)
            requestInfo.success(reseponse.data)
            return
          }
          const code = reseponse.data.code
          if (code.startsWith('000')) {
            requestInfo.success(reseponse.data)
          } else if (code === '111111') {
            alert('登录失效，请重新登录')
            window.localStorage.setItem('token', '')
            location.reload()
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
type serverKey = {
  base: string
  user: string
  uaa: string
  shop: string
}
let server: serverKey
import { devServer } from './server.dev.json'
import { prodServer } from './server.prod.json'
console.log(import.meta.env.MODE)
if (import.meta.env.MODE === 'dev') {
  server = devServer
} else {
  server = prodServer
}

//import.meta.env.VITE_SERVER
//console.log(server);
export { zlaxios, server }
