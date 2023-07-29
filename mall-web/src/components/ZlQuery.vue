<template>
  <el-button type="primary" @click="query()">查询</el-button>
  <el-button type="primary" @click="resetform(formRef)">重置</el-button>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
const props = defineProps(['formRef', 'zltable'])

const query = () => {
  props.zltable.query()
}

const resetform = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}
</script>
