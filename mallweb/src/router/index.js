import Vue from 'vue'
import VueRouter from 'vue-router'
import MainView from '../views/MainView.vue'
import LoginView from '../views/LoginView.vue'
import WorkbenchHome from '../views/WorkbenchHome.vue'
import SellManage from '../views/sell/SellManage.vue'
import ReleaseGoods from '../views/sell/ReleaseGoods.vue'
import MenuConfig from '../views/config/MenuConfig.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: LoginView
  },
  {
    path: '/mainView',
    name: 'mainView',
    component: MainView,
    children: [
      {
        path: '/workbenchHome',
        name: 'workbenchHome',
        component: WorkbenchHome
      },
      {
        path: '/sellManage',
        name: 'sellManage',
        component: SellManage,
        children: [
          {
            path: '/releaseGoods',
            name: 'releaseGoods',
            component: ReleaseGoods
          }]
      },
      {
        path: '/menuConfig',
        name: 'menuConfig',
        component: MenuConfig
      }
    ]
  }
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
