import { createRouter, createWebHistory, type RouteComponent } from 'vue-router'

import Login from '@/pages/Login.vue'
import Home from "@/pages/Home.vue"
import Register from '@/pages/Register.vue'
import Welcome from "@/pages/Welcome.vue"
import UserInfo from "@/pages/user/UserInfo.vue"
import AddUserInfo from '@/pages/user/AddUserInfo.vue'
import Menu from '@/pages/mananger/menu/Menu.vue'
import Role from '@/pages/mananger/role/Role.vue'

import { routerStore } from '@/stores/routerStore'
import type {routeType} from  '@/stores/routerStore'
import { computed, reactive, type Component, type ComputedOptions, type ComputedRef, type MethodOptions } from 'vue'

const routerMap: Map<string, Component> = new Map()
routerMap.set('menu', Menu)
routerMap.set('role', Role)

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
router.beforeEach((to, from) => {
  const rs = routerStore()
  const cu: ComputedRef  = computed(() => {
    if (to.name) {
      const rg = routerMap.get(to.name.toString()) 
      return rg
    }
  })
  if (to.name && routerMap.has(to.name?.toString())) {
    let r: routeType = reactive({
      name: to.name.toString(),
      component: cu
    })
    rs.push(r)
  }
  
})
export default router
