import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import service from './lib/service'

Vue.config.productionTip = false
Vue.prototype.service = service

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
