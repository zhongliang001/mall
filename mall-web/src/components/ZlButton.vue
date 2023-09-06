<template>
  <el-button
    v-show="
      actions || action === '' || action === undefined || pageName === '' || pageName === undefined
    "
    :type="type"
  >
    <span class="el-button__text--expand"><slot></slot></span>
  </el-button>
</template>
<script setup lang="ts">
const props = defineProps(['pageName', 'action', 'type'])
import { routerStore } from '@/stores/routerStore'
const rs = routerStore()
const pageName = props.pageName
const action = props.action
const actions = rs.isHasRight(pageName, action)
</script>
