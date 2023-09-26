<template>
  <el-container>
    <el-header>用户详细信息</el-header>
    <el-main>
      <el-form ref="reqForm" :model="formdata">
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="formdata.userName" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickName">
          <el-input v-model="formdata.nickName" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="证件类型" prop="certType">
          <zl-select v-model="formdata.certType" type="CERT_TYPE" :disabled="true"></zl-select>
        </el-form-item>
        <el-form-item label="证件号" prop="certCode">
          <el-input v-model="formdata.certCode" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="formdata.phone" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <zl-select v-model="formdata.sex" type="SEX" :disabled="true"></zl-select>
        </el-form-item>
      </el-form>
      <el-button type="primary" @click="goback">返回</el-button>
    </el-main>
  </el-container>
</template>
<script lang="ts" setup>
import { reactive, onMounted, ref } from 'vue'
import router from '@/router'
import { zlaxios, server } from 'lib/zlaxios'
import { ElMessage } from 'element-plus'
let formdata = reactive({
  userName: '',
  nickName: '',
  certType: '',
  certCode: '',
  sex: '',
  sex1: '',
  phone: ''
})

onMounted(() => {
  const token = localStorage.getItem('token')
  const j = token?.split('.')[1]
  if (j) {
    const a = window.atob(j)
    const jsa = JSON.parse(a)
    const userId = jsa.userId
    zlaxios.request({
      url: server.user + '/userInfo/queryByUserId',
      params: {
        userId: '1'
      },
      method: 'get',
      success: function (data: any) {
        Object.assign(formdata, data.data)
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
    ElMessage({
      message: '请重新登录',
      grouping: true,
      type: 'error'
    })
    router.push({ name: 'login' })
  }
})
const goback = function () {
  router.back()
}
</script>
