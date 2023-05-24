<template>
  <el-container>
    <el-header>用户详细信息</el-header>
    <el-main>
      <el-form ref="reqForm" :model="formdata">
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="formdata.userName"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickName">
          <el-input v-model="formdata.nickName"></el-input>
        </el-form-item>
        <el-form-item label="证件类型" prop="certType">
          <el-select v-model="formdata.certType">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="证件号" prop="certCode">
          <el-input v-model="formdata.certCode"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="formdata.sex">
            <el-option
              v-for="item in sexOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <el-button type="primary" @click="goback">返回</el-button>
    </el-main>
  </el-container>
</template>
<script lang="ts" setup>
import { reactive, onMounted, ref } from 'vue'
import router from '@/router'
import zlaxios from '../../../lib/zlaxios'
import { ElMessage } from 'element-plus'
const options = [{ value: '1', label: '身份证' }]

const sexOptions = [
  { value: 'M', label: '男' },
  { value: 'F', label: '女' }
]

let formdata = reactive({
  userName: '',
  nickName: '',
  certType: '',
  certCode: '',
  sex: ''
})

onMounted(() => {
  const token = localStorage.getItem('token')
  const j = token?.split('.')[1]
  if (j) {
    const a = window.atob(j)
    const jsa = JSON.parse(a)
    const userId = jsa.userId
    zlaxios.request({
      url: '/user/userInfo/queryByUserId',
      params: {
        userId: '1'
      },
      method: 'get',
      success: function (data: any) {
        debugger
        Object.assign(formdata, data.data)
      },
      failed: function (data: any) {
        ElMessage({
          message: data.msg,
          grouping: true,
          type: 'error'
        })
      }
    })
  }
})
const goback = function () {
  router.back()
}
</script>
