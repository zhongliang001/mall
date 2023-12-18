import type { Item } from '@/components/Item'
import { defineStore } from 'pinia'

export const areaStore = defineStore('areaStore', () => {
  const areaMap = new Map<string, Array<Item>>()
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
