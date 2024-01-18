import type { Item } from '@/components/Item'
import { defineStore } from 'pinia'
import { reactive } from 'vue'

export const areaStore = defineStore('areaStore', () => {
  const areaMap = reactive(new Map<string, Array<Item>>())
  const getAreas = (value: string = 'province'): Array<Item> | undefined => {
    return areaMap.get(value)
  }

  const setAreas = (value: string, list: Array<Item>) => {
    areaMap.set(value, list)
  }

  return {
    getAreas,
    setAreas
  }
})
