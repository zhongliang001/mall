<template>
  <el-button
    v-if="
      actions || action === '' || action === undefined || pageName === '' || pageName === undefined
    "
    :type="type"
  >
    <span class="el-button__text--expand"><slot></slot></span>
  </el-button>
</template>
<script setup lang="ts">
const props = defineProps({
  pageName: {
    type: String
  },
  action: {
    type: String
  },
  type: {
    type: String
  }
})
import { routerStore } from '@/stores/routerStore'
import { ref } from 'vue'
const rs = routerStore()
const pageName = ref(props.pageName)
const action = ref(props.action)
let actions: boolean
if (pageName.value && action.value) actions = rs.isHasRight(pageName.value, action.value)
</script>
