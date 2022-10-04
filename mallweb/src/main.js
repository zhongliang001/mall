import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import 'element-ui/lib/theme-chalk/index.css'
import service from './lib/service'
import './images/icons/iconfont.css'
import './images/icons/iconfont.js'
import './lib/date'

Vue.config.productionTip = false
Vue.prototype.service = service

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
