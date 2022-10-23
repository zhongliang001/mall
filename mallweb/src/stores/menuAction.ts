import { defineStore } from "pinia";
import {ref} from 'vue'

export const menuActionStore = defineStore("menuAction", {
  state: () => {
      let menuId = ''
      let menuName = ''
    return {
      menuId, menuName
    }
      
  }

})
