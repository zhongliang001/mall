<script setup lang="ts">
import { RouterView } from 'vue-router'
import router from './router'
const token = localStorage.getItem('token')
if (!token) {
  router.push('login')
} else {
  router.push('home')
}
</script>

<template>
  <router-view></router-view>
</template>

<style scoped></style>
