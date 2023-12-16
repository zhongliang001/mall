<template>
  <el-select>
    <el-option v-for="item in options" :key="item.name" :label="item.name" :value="item.value" />
  </el-select>
</template>
<script setup lang="ts">
import type { Item } from './Item'
defineProps({
  options: {
    type: Array<Item>
  }
})
</script>
