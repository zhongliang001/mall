<template>
  <el-container>
    <el-header>供应商详情</el-header>
    <el-main>
      <el-form ref="reqForm" :model="formdata">
        <el-row>
          <el-col :span="11">
            <el-form-item label="供应商名称" prop="vendorName">
              <el-input v-model="formdata.vendorName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="是否实体店" prop="conventional">
              <zl-select v-model="formdata.conventional" type="YES_NO"></zl-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="供应商地址" prop="path">
              <el-input v-model="formdata.path"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="是否合作" prop="cooperStatus">
              <zl-select v-model="formdata.cooperStatus" type="YES_NO"></zl-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-row>
        <el-col :span="11" :offset="11">
          <zl-button type="primary" @click="toBack(reqForm)">返回</zl-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<script setup lang="ts">
import type { FormInstance } from 'element-plus'
import { reactive, ref, watch } from 'vue'
import type { Vendor } from './vendor'
const props = defineProps(['page', 'modData'])

const formdata: Vendor = reactive({})
const reqForm = ref<FormInstance>()

watch(
  () => props.page,
  (newVal) => {
    if (newVal === 'view') {
      Object.assign(formdata, props.modData)
    }
  }
)
// emit 获取父组件传来的方法
const emit = defineEmits(['clickBack'])
const toBack = (formEl: FormInstance | undefined) => {
  // 调用父组件的方法，emit('clickBack', params)
  emit('clickBack')
  if (!formEl) {
    return
  }
  formEl.resetFields()
}
</script>
