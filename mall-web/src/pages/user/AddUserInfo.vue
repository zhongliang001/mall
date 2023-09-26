<template>
  <el-container>
    <el-header>用户详细信息</el-header>
    <el-main>
      <el-form ref="reqForm" :rules="rules" :model="formdata">
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="formdata.userName" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickName">
          <el-input v-model="formdata.nickName"></el-input>
        </el-form-item>
        <el-form-item label="证件类型" prop="certType">
          <zl-select v-model="formdata.certType" type="CERT_TYPE"></zl-select>
        </el-form-item>
        <el-form-item label="证件号" prop="certCode">
          <el-input v-model="formdata.certCode"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="formdata.phone" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <zl-select v-model="formdata.sex" type="SEX"></zl-select>
        </el-form-item>
      </el-form>
      <el-button type="primary" @click="doSub(reqForm)">提交</el-button>
      <el-button type="primary" @click="goback">返回</el-button>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import { reactive, onMounted, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import { zlaxios, server } from 'lib/zlaxios'
import router from '@/router'
import { ElMessage } from 'element-plus'

let formdata = reactive({
  userId: '',
  userName: '',
  nickName: '',
  certType: '',
  certCode: '',
  sex: '',
  phone: ''
})

onMounted(() => {
  if (router.currentRoute.value.query.userId) {
    formdata.userId = router.currentRoute.value.query.userId as any
  }
  if (router.currentRoute.value.query.userName) {
    formdata.userName = router.currentRoute.value.query.userName as any
  }
})
const reqForm = ref<FormInstance>()
const rules = reactive<FormRules>({
  userName: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  nickName: [{ required: true, message: '请输入昵称', trigger: 'blur' }],
  certType: [{ required: true, message: '请输入证件类型', trigger: 'blur' }],
  certCode: [{ required: true, message: '请输入证件号', trigger: 'blur' }],
  phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }],
  sex: [{ required: true, message: '请输入性别', trigger: 'blur' }]
})

const doSub = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      zlaxios.request({
        url: server.user + '/ViewUserInfo/add',
        data: formdata,
        method: 'post',
        success: function (data: any) {
          router.push({ name: 'login' })
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

const goback = function () {
  router.back()
}
</script>
