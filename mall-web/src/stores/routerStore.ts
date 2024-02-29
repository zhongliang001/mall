import type { Action, Menu } from '@/components/menu'
import { defineStore } from 'pinia'
import { defineAsyncComponent, reactive, ref, shallowRef } from 'vue'

type ZlRoute = {
  name: string
  cnName: string
  actions: Array<Action>
  component: any
}

const routerMap: Map<string, any> = new Map()
routerMap.set(
  'Welcome',
  defineAsyncComponent(() => import('../pages/Welcome.vue'))
)
routerMap.set(
  'Dict',
  defineAsyncComponent(() => import('../pages/mananger/dict/Dict.vue'))
)
routerMap.set(
  'Menu',
  defineAsyncComponent(() => import('../pages/mananger/menu/Menu.vue'))
)
routerMap.set(
  'Role',
  defineAsyncComponent(() => import('../pages/mananger/role/Role.vue'))
)
routerMap.set(
  'UserInfo',
  defineAsyncComponent(() => import('../pages/user/UserInfo.vue'))
)

routerMap.set(
  'Template',
  defineAsyncComponent(() => import('../pages/mananger/template/Template.vue'))
)

routerMap.set(
  'Vendor',
  defineAsyncComponent(() => import('../pages/shopcenter/vendor/Vendor.vue'))
)

routerMap.set(
  'ProductInfo',
  defineAsyncComponent(() => import('../pages/shopcenter/productInfo/ProductInfo.vue'))
)

routerMap.set(
  'OrderInfo',
  defineAsyncComponent(() => import('../pages/shopcenter/orderInfo/OrderInfo.vue'))
)

routerMap.set(
  'AreaStatistics',
  defineAsyncComponent(() => import('../pages/shopcenter/areastatistics/AreaStatistics.vue'))
)

routerMap.set(
  'ChargeRecord',
  defineAsyncComponent(() => import('../pages/shopcenter/chargerecord/ChargeRecord.vue'))
)

routerMap.set('ZtcStatistics', defineAsyncComponent(()=>import('../pages/shopcenter/ztcStatistics/ZtcStatistics.vue')))

export const routerStore = defineStore('routerStore', () => {
  const routerList: Array<ZlRoute> = reactive<Array<ZlRoute>>([])
  const showRouter = ref('welcome')
  const zr: ZlRoute = reactive<ZlRoute>({
    name: 'welcome',
    cnName: '首页',
    component: shallowRef(routerMap.get('Welcome')),
    actions: []
  })

  routerList.push(zr)

  function push(r: Menu) {
    let isContain = false
    routerList.forEach((t) => {
      if (r.menuName === t.name) {
        isContain = true
      }
    })
    if (!isContain) {
      const comp = r.comp
      const zr: ZlRoute = reactive<ZlRoute>({
        name: r.menuName,
        cnName: r.menuCnName,
        actions: r.list,
        component: shallowRef(routerMap.get(comp))
      })

      routerList.push(zr)
    }
    if (r.menuName?.toString()) {
      showRouter.value = r.menuName?.toString()
    }
  }

  function splice(name: string) {
    let index = -1
    routerList.forEach((t, i) => {
      if (name === t.name) {
        index = i
      }
    })
    if (index > 0) {
      routerList.splice(index, 1)
      if (index >= 1) {
        if (routerList.length > index + 1) {
          const name = routerList[index].name?.toString()
          showRouter.value = name ? name : ''
        } else {
          const name = routerList[routerList.length - 1].name?.toString()
          showRouter.value = name ? name : ''
        }
      }
    }
  }

  function getActions(name: string) {
    for (let i = 0; i < routerList.length; i++) {
      const menu = routerList[i]
      if (name === menu.name) {
        return menu.actions
      }
    }
  }

  function isHasRight(name: string, actionCode: string) {
    const actions = getActions(name)
    if (!actions) {
      return false
    }
    for (let i = 0; i < actions?.length; i++) {
      if (actionCode === actions[i].actionCode) {
        return true
      }
    }
    return false
  }
  return { routerList, showRouter, push, splice, isHasRight }
})
