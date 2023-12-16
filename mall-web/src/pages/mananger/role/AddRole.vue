<template>
  <el-container>
    <el-header>新增页面</el-header>
    <el-main>
      <el-form :rules="rules" ref="reqForm" :model="formdata">
        <el-row>
          <el-col :span="11">
            <el-form-item label="角色名" prop="roleName">
              <el-input type="text" v-model="formdata.roleName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="角色描述" prop="remark">
              <el-input v-model="formdata.remark"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="角色状态" prop="state">
              <zl-dict v-model="formdata.state" type="ONLINE_STATE"></zl-dict>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-row>
        <el-col :span="11" :offset="11">
          <el-button type="primary" @click="add(reqForm)">保存</el-button>
          <el-button type="primary" @click="toBack(reqForm)">返回</el-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import { zlaxios, server } from 'lib/zlaxios'
import type { FormInstance, FormRules } from 'element-plus'
import { ElMessage } from 'element-plus'
const props = defineProps(['page'])
// emit 获取父组件传来的方法
const emit = defineEmits(['clickBack'])
const reqForm = ref<FormInstance>()

const formdata = reactive({
  roleName: '',
  remark: '',
  state: ''
})

const rules = reactive<FormRules>({
  roleName: [{ required: true, message: '请输入角色名', trigger: 'blur' }],
  state: [{ required: true, message: '请输入角色状态', trigger: 'blur' }]
})

const toBack = (formEl: FormInstance | undefined) => {
  // 调用父组件的方法，emit('clickBack', params)
  emit('clickBack')
  if (!formEl) {
    return
  }
  formEl.resetFields()
}

const add = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      zlaxios.request({
        url: server.base + '/role/add',
        data: formdata,
        method: 'post',
        success: function (data: any) {
          toBack(formEl)
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
