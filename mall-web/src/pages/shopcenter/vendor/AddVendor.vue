<template>
  <el-container>
    <el-header>新增供应商</el-header>
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
          <el-button type="primary" @click="doSub(reqForm)">提交</el-button>
          <zl-button type="primary" @click="toBack(reqForm)">返回</zl-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<script setup lang="ts">
import { ElMessage, type FormInstance } from 'element-plus'
import { server, zlaxios } from 'lib/zlaxios'
import { reactive, ref } from 'vue'
import type { Vendor } from './vendor'
import { userStore } from '@/stores/userStore'
const us = userStore()
const formdata: Vendor = reactive({})
formdata.shopId = us.shopId()
const reqForm = ref<FormInstance>()
const doSub = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      zlaxios.request({
        url: server.shop + '/vendor/add',
        data: formdata,
        method: 'post',
        success: function () {
          toBack(formEl)
        },
        failed: function (data: any) {
          ElMessage({
            message: data.data.msg,
            grouping: true,
            type: 'error'
          })
        }
      })
    } else {
      console.log('error submit!', fields)
    }
  })
}

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
