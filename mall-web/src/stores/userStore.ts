import { defineStore } from 'pinia'
import { reactive } from 'vue'

type User = {
  userId: string
  userName: string
  shopId: string
  shopName: string
}

export const userStore = defineStore('userStore', () => {
  const user: User = reactive({
    userId: '',
    userName: '',
    shopId: '',
    shopName: ''
  })
  const userId = () => {
    debugger
    return user.userId
  }
  const userName = () => {
    return user.userName
  }
  const shopId = () => {
    return user.shopId
  }
  const shopName = () => {
    return user.shopName
  }
  const setUserId = (userId: string) => {
    user.userId = userId
  }
  const setUserName = (userName: string) => {
    user.userName = userName
  }
  const setShopId = (shopId: string) => {
    user.shopId = shopId
  }
  const setShopName = (shopName: string) => {
    user.shopName = shopName
  }
  return {
    userId,
    userName,
    shopId,
    shopName,
    setUserId,
    setUserName,
    setShopId,
    setShopName
  }
})
