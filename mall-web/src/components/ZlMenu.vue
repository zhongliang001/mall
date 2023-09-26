<template>
  <div>
    <el-menu default-active="2" background-color="#545c64">
      <template v-for="(menu, index) in menus">
        <el-sub-menu v-if="menu.children?.length > 0" :index="level + '-' + index">
          <template #title>
            <span>{{ menu.menuCnName }}</span>
          </template>
          <zl-menu :menus="menu.children" :level="level + 1"></zl-menu>
        </el-sub-menu>
        <template v-else>
          <el-menu-item :index="level + '-' + index">
            <a @click="toPath(menu)">{{ menu.menuCnName }}</a>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>
<script lang="ts" setup>
import type { Menu } from './menu'
import { routerStore } from '@/stores/routerStore'
const rs = routerStore()
defineProps({
  menus: Array<Menu>,
  level: { type: Number, default: 0 }
})

const toPath = (menu: Menu) => {
  rs.push(menu)
}
</script>

<style scoped>
li a {
  width: 100%;
}
</style>
