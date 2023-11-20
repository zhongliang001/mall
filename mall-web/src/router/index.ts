import { createRouter, createWebHistory } from 'vue-router'

import Login from '@/pages/Login.vue'
import Home from '@/pages/Home.vue'
import Register from '@/pages/Register.vue'
import AddUserInfo from '@/pages/user/AddUserInfo.vue'
import ViewUserInfo from '@/pages/user/ViewUserInfo.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      name: 'login',
      path: '/login',
      component: Login
    },
    {
      name: 'home',
      path: '/home',
      component: Home,
      children: []
    },
    {
      name: 'register',
      path: '/register',
      component: Register
    },
    {
      name: 'addUserInfo',
      path: '/addUserInfo',
      component: AddUserInfo
    },
    {
      name: 'viewUserInfo',
      path: '/ViewUserInfo',
      component: ViewUserInfo
    }
  ]
})
export default router
