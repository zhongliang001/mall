<template>
  <el-select>
    <el-option
      v-for="item in dict[type]"
      :key="item.enName"
      :label="item.cnName"
      :value="item.enName"
    />
  </el-select>
</template>

<script setup lang="ts">
import useCurrentInstance from '../../lib/useCurrentInstance'
const props = defineProps(['type'])
const { proxy } = useCurrentInstance()
const dict = proxy.dict
</script>
