import { createRouter, createWebHistory } from 'vue-router'
import Login from '@/pages/Login.vue'
import Home from "@/pages/Home.vue"
import Register from '@/pages/Register.vue'
import Welcome from "@/pages/Welcome.vue"
import UserInfo from "@/pages/UserInfo.vue"
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
      children:[{
        name: 'welcome',
        path: '/welcome',
        component: Welcome
      },
      {
        name: 'userInfo',
        path: '/userInfo',
        component: UserInfo
      }]
    },{
      name: 'register',
      path: '/register',
      component: Register
    }
  ]
})

export default router
