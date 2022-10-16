import axios from "axios";

// const baseURL = process.env.VUE_APP_BASE_API
const baseURL = "http://192.168.111.129:8092";
const zlaxios = axios.create({
  responseType: "json",
  timeout: 300000,
  timeoutErrorMessage: "请求超时",
  // token: '',
  withCredentials: true, // 支持请求带有cookie
});

zlaxios.interceptors.request.use((config) => {
  return new Promise((resolve) => {
    // 模拟等待refresh_token
    setTimeout(
      function (configParam: Object) {
        resolve(configParam);
      },
      2000,
      config
    );
  });
});

zlaxios.interceptors.response.use(
  function (response) {
    // 对响应数据做点什么
    return response;
  },
  function (error) {
    // 对响应错误做点什么
    return error;
  }
);

interface requestInfo {
  url: string;
  method: string;
  failed: Function;
  success: Function;
  data: {};
  config: {};
}

const service = {
  userURL: baseURL + "/user-centric-server",
  uaaURL: baseURL + "/uaa",
  request: function (requestInfo: requestInfo) {
    const url = requestInfo.url;
    const method = requestInfo.method;
    if (method === undefined || method === "get") {
      zlaxios.get(url).then((reseponse) => {
        if (reseponse.request.status !== 200) {
          requestInfo.failed(reseponse);
        } else {
          const code = reseponse.data.code;
          if (code === "000000") {
            requestInfo.success(reseponse.data);
          } else {
            requestInfo.failed(reseponse.data);
          }
        }
      });
    } else {
      const token = localStorage.getItem("token");
      if (token) {
        document.cookie = "token=" + token;
      }
      zlaxios
        .post(url, requestInfo.data, requestInfo.config)
        .then((reseponse) => {
          if (reseponse.request.status !== 200) {
            requestInfo.failed(reseponse);
          } else {
            if (url && url.indexOf("/oauth/token") !== -1) {
              window.localStorage.setItem("token", reseponse.data.access_token);
              window.localStorage.setItem(
                "userName",
                reseponse.data.loginDto.userName
              );
              requestInfo.success(reseponse.data);
              return;
            }
            const code = reseponse.data.code;
            if (code.startsWith("000")) {
              requestInfo.success(reseponse.data);
            } else {
              requestInfo.failed(reseponse.data);
            }
          }
        });
    }
  },
};

export default service;
