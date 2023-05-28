<script setup lang="ts">
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import en from 'element-plus/dist/locale/en.mjs'
import { RouterView } from 'vue-router'
import { ref, computed } from 'vue'
import router from './router'
const token = localStorage.getItem('token')
if (!token) {
  router.push('login')
} else {
  router.push('home')
}
const language = ref('zh-cn')
const locale = computed(() => (language.value === 'zh-cn' ? zhCn : en))
</script>

<template>
  <el-config-provider :locale="locale" :button="{ autoInsertSpace: true }">
    <router-view></router-view>
  </el-config-provider>
</template>

<style scoped></style>
