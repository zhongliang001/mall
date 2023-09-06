import type { Action, Menu } from "@/components/menu";
import { defineStore } from "pinia";
import { defineAsyncComponent, reactive, ref, shallowRef } from "vue";
type ZlRoute = {
  name: string
  cnName: string
  actions: Array<Action>
  component: any
}
export const routerStore = defineStore('routerStore', () => {   
  
  const routerList: Array<ZlRoute> = reactive<Array<ZlRoute>>([])
  const showRouter = ref('welcome');
  const welComp = defineAsyncComponent(() =>
        import(/* @vite-ignore */ '../pages/Welcome.vue')
      )
  const zr: ZlRoute = reactive<ZlRoute>({
    name: 'welcome',
    cnName: '首页',
    component: shallowRef(welComp),
    actions:[]
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
      const asyncComp = defineAsyncComponent(() =>
        import(/* @vite-ignore */ '..' +comp)
      )
      const zr: ZlRoute = reactive<ZlRoute>({
        name: r.menuName,
        cnName: r.menuCnName,
        actions: r.list, 
        component: shallowRef(asyncComp)
      })

        routerList.push(zr)      
    }
    if (r.menuName?.toString()) {
      showRouter.value = r.menuName?.toString()
      
    }
  }

  function splice(name: string) {
    let index = -1;
    routerList.forEach((t, i) => {
      if (name === t.name) {
        index = i
      }
    })
    if (index > 0) {
      routerList.splice(index,1)
      if (index >= 1) {
        if (routerList.length > index + 1) {
          const name = routerList[index].name?.toString()
          showRouter.value = name ? name :''
        } else {
          const name = routerList[routerList.length -1].name?.toString()
          showRouter.value = name ? name :''
        }
       
      }
    }
  }

  function getActions(name: string) {
    for (let i = 0; i < routerList.length; i++){
      const menu = routerList[i]
      if (name === menu.name) {
        return menu.actions
      }
    }
  }

  function isHasRight(name: string, actionCode: string) {
    const actions = getActions(name)
    if (!actions) {
      return false;
    }
    for (let i = 0; i < actions?.length; i++){
      if (actionCode === actions[i].actionCode) {
        return true;
      }
    }
    return false;
  }
  return {routerList,showRouter, push,splice,isHasRight}
})