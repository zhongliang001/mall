<script setup lang="ts">
import { ref, reactive } from 'vue'
import { zlaxios, server } from 'lib/zlaxios'
import type { FormInstance, FormRules } from 'element-plus'
import { ElMessage } from 'element-plus'
import router from '@/router'
const reqForm = ref<FormInstance>()
const reqdata = reactive({
  userName: '',
  email: '',
  password: '',
  repeatPassword: ''
})

const checkpasswod = (rule: any, value: any, callback: any) => {
  if (value && reqdata.repeatPassword) {
    if (value !== reqdata.repeatPassword) {
      callback(new Error('两次输入的密码不一致!'))
    } else {
      callback()
    }
  }
}
const checkRepeatPassword = (rule: any, value: any, callback: any) => {
  if (value && reqdata.repeatPassword) {
    if (value !== reqdata.password) {
      callback(new Error('两次输入的密码不一致!'))
    } else {
      callback()
    }
  }
}

const rules = reactive<FormRules>({
  userName: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    {
      pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[\s\S]{8,16}$/,
      message: '密码至少8-16个字符，必须包含大写字母，小写字母，数字',
      trigger: 'blur'
    },
    {
      validator: checkpasswod,
      message: '两次输入的密码不一致',
      trigger: 'blur'
    }
  ],
  repeatPassword: [
    { required: true, message: '请重复密码', trigger: 'blur' },
    {
      pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,16}$/,
      message: '密码至少8-16个字符，必须包含大写字母，小写字母，数字',
      trigger: 'blur'
    },
    {
      validator: checkRepeatPassword,
      message: '两次输入的密码不一致',
      trigger: 'blur'
    }
  ],
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    {
      pattern: '^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$',
      message: '请输入正确的邮箱',
      trigger: 'blur'
    }
  ]
})

const regist = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      zlaxios.request({
        url: server.user + '/userAuth/regist',
        data: reqdata,
        method: 'post',
        success: function (data: any) {
          router.push({
            name: 'addViewUserInfo',
            query: { userId: data.userId, userName: data.data.userName }
          })
        },
        failed: function (data: any) {
          ElMessage({
            message: data.msg,
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
</script>

<template>
  <div class="regist">
    <el-container>
      <el-header>
        <h1>注册</h1>
      </el-header>
      <el-main>
        <el-form ref="reqForm" :rules="rules" :model="reqdata">
          <el-form-item label="用户名" prop="userName">
            <el-input v-model="reqdata.userName"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="reqdata.email"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="reqdata.password" type="password"></el-input>
          </el-form-item>
          <el-form-item label="重复密码" prop="repeatPassword">
            <el-input v-model="reqdata.repeatPassword" type="password"></el-input>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="regist(reqForm)">提交</el-button>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped>
.regist {
  margin-left: 36%;
  width: 24%;
  margin-top: 10%;
}
</style>
