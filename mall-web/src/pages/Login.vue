<script setup lang="ts">
import { ref, reactive } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import zlaxios from '../../lib/zlaxios'
import { ElMessage } from 'element-plus'
import router from '@/router'

const reqForm = ref<FormInstance>()
const reqdata = reactive({
  username: '',
  password: '',
  grant_type: 'password'
})
const rules = reactive<FormRules>({
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    {
      pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[\s\S]{8,16}$/,
      message: '密码至少8-16个字符，必须包含大写字母，小写字母，数字',
      trigger: 'blur'
    }
  ]
})

const login = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      zlaxios.request({
        url: 'http://192.168.111.129:8096/uaa/oauth/token',
        // url: 'http://localhost:18096/uaa/oauth/token',
        config: {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          auth: {
            username: 'web',
            password: '123456'
          },
          params: reqdata
        },
        method: 'post',
        success: function (data: any) {
          router.push({ name: 'home', query: { userinfo: data.data } })
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

const toRegister = function () {
  router.push({ name: 'register' })
}
</script>
<template>
  <div class="login">
    <el-container>
      <el-header>
        <img src="../images/timg.jpg" />
        登录
      </el-header>
      <el-main>
        <el-form ref="reqForm" :rules="rules" :model="reqdata">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="reqdata.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="reqdata.password" type="password"></el-input>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="login(reqForm)">提交</el-button>
        <el-button type="primary" @click="toRegister">注册</el-button>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped>
.login {
  margin-left: 36%;
  width: 24%;
  margin-top: 10%;
}
.el-container {
  border: solid grey 1px;
  border-radius: 0.25rem;
  height: 250px;
}

img {
  width: 50px;
  height: 50px;
  border-radius: 150%;
  background-color: #eee;
}
</style>
