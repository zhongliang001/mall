<template>
  <el-container>
    <el-header>修改供应商</el-header>
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
              <zl-dict v-model="formdata.conventional" type="YES_NO"></zl-dict>
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
              <zl-dict v-model="formdata.cooperStatus" type="YES_NO"></zl-dict>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-row>
        <el-col :span="11" :offset="11">
          <el-button type="primary" :loading="loading" @click="doSub(reqForm)">提交</el-button>
          <zl-button type="primary" @click="toBack(reqForm)">返回</zl-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<script setup lang="ts">
import { ElMessage, type FormInstance } from 'element-plus'
import { server, zlaxios } from 'lib/zlaxios'
import { reactive, ref, watch } from 'vue'
import type { Vendor } from './vendor'
const loading = ref(false)
const props = defineProps(['page', 'modData'])

const formdata: Vendor = reactive({})
const reqForm = ref<FormInstance>()

watch(
  () => props.page,
  (newVal) => {
    if (newVal === 'mod') {
      Object.assign(formdata, props.modData)
    }
  }
)

const doSub = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      zlaxios.request({
        url: server.shop + '/vendor/update',
        data: formdata,
        method: 'post',
        loading: loading,
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
