import { createRouter, createWebHistory } from 'vue-router'

import Login from '@/pages/Login.vue'
import Home from "@/pages/Home.vue"
import Register from '@/pages/Register.vue'
import Welcome from "@/pages/Welcome.vue"
import UserInfo from "@/pages/user/UserInfo.vue"
import AddUserInfo from '@/pages/user/AddUserInfo.vue'
import Menu from '@/pages/mananger/menu/Menu.vue'
import Role from '@/pages/mananger/role/Role.vue'

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
      },
      {
        name: 'menu',
        path: '/menu',
        component: Menu
      },
      {
        name: 'role',
        path: '/role',
        component: Role
      }]
    },{
      name: 'register',
      path: '/register',
      component: Register
    },
    {
      name: 'addUserInfo',
      path: '/addUserInfo',
      component: AddUserInfo
    }
  ]
})

export default router
