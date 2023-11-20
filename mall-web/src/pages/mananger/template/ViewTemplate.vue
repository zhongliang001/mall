<template>
  <el-container>
    <el-header>修改流水号模板</el-header>
    <el-main>
      <el-form :model="formdata">
        <el-row>
          <el-col :span="11">
            <el-form-item label="模板名称" prop="name">
              <el-input type="text" v-model="formdata.name" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="模板" prop="temp">
              <el-input v-model="formdata.temp" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="模板中文名" prop="cnName">
              <el-input type="text" v-model="formdata.cnName" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-row :gutter="20" justify="center">
        <el-col :span="6">
          <zl-button type="primary" @click="toBack()">返回</zl-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<script setup lang="ts">
import { reactive, watch } from 'vue'
import type { Template } from './template'

const props = defineProps(['data'])
let formdata: Template = reactive<Template>({})

watch(
  () => props.data,
  (newval) => {
    Object.assign(formdata, newval)
  }
)
// emit 获取父组件传来的方法
const emit = defineEmits(['clickBack'])
const toBack = () => {
  // 调用父组件的方法，emit('clickBack', params)
  emit('clickBack')
}
</script>
