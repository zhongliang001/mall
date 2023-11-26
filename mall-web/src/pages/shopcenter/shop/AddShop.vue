<template>
  <el-container>
    <el-header>新增商品</el-header>
    <el-main>
      <el-form ref="reqForm" :rules="rules" :model="formdata">
        <el-row>
          <el-col :span="11">
            <el-form-item label="店铺名称" prop="shopName">
              <el-input v-model="formdata.shopName"></el-input>
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
            <el-form-item label="铺货渠道" prop="channel">
              <el-input v-model="formdata.channel"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="网店地址" prop="path">
              <el-input type="url" v-model="formdata.path"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="店铺管理地址" prop="managePath">
              <el-input type="url" v-model="formdata.managePath"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-button type="primary" @click="doSub(reqForm)">提交</el-button>
      <zl-button type="primary" @click="toBack(reqForm)">返回</zl-button>
    </el-main>
  </el-container>
</template>
<script lang="ts" setup>
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'
import { server, zlaxios } from 'lib/zlaxios'
import { reactive, ref } from 'vue'
import type { Shop } from './shop'

const rules = reactive<FormRules>({
  shopName: [{ required: true, message: '请输入店铺名称', trigger: 'blur' }]
})
const formdata: Shop = reactive<Shop>({})
const reqForm = ref<FormInstance>()

const doSub = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      zlaxios.request({
        url: server.shop + '/shop/add',
        data: formdata,
        method: 'post',
        success: function (data: any) {
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
