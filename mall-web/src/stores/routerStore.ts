import { defineStore } from 'pinia'
import { computed, reactive, ref } from 'vue'
import type { RouteComponent, RouteRecordName } from 'vue-router'
import Welcome from "@/pages/Welcome.vue"
export type routeType =  {
  name: RouteRecordName | null | undefined
  component: Record<string, RouteComponent> | null | undefined
}
export const routerStore = defineStore('routerList', () => {
  
  const routerList: Array<routeType> = reactive([])

  let showRouter = ref('');
  const cu = computed(() => {
    return Welcome
  })
  let r: routeType = reactive({
    name: 'Welcome',
    component: cu
  })
  routerList.push(r)

  function push(r: routeType) {
    let isContain = false
    routerList.forEach((t) => {
      if (r.name === t.name) {
        isContain = true
      }
    })
    if (!isContain) {
      routerList.push(r)      
    }
    if (r.name?.toString()) {
      showRouter.value = r.name?.toString()
      
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
  return {routerList,showRouter, push, splice}
})