import { createApp,ref,computed } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zlaxios from "../lib/zlaxios";
import './assets/main.css'

const app = createApp(App)
const coms: any = import.meta.glob('./components/*.vue', { eager: true });
for (let objname in coms) {
    let myval = coms[objname]; 
    app.component(objname.substring(13).replace('.vue',''),myval.default)
}

app.use(ElementPlus, { size: 'small', zIndex: 3000 })
app.use(createPinia())
app.use(router)

//app.mount('#app')

zlaxios.request({
  url: "/user/dict/queryAll",
  method: "get",
  success: function (data: any) {
    app.config.globalProperties.dict = data.data
    app.config.globalProperties.zlaxios = zlaxios
    app.mount('#app')
  },
});
