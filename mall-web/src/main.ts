import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zlaxios from "../lib/zlaxios";

import './assets/main.css'

const app = createApp(App)

app.use(ElementPlus, { size: 'small', zIndex: 3000 })
app.use(createPinia())
app.use(router)

app.mount('#app')

zlaxios.request({
  url: "http://localhost:18096/common/dict/queryAllTree",
  data: {},
  method: "post",
  success: function (data: any) {
    app.config.globalProperties.dict = data.data
    app.mount('#app')
  },
});
