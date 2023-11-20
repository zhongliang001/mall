<template>
  <el-container>
    <el-header>新增流水号模板</el-header>
    <el-main>
      <el-form :rules="rules" ref="reqForm" :model="formdata">
        <el-row>
          <el-col :span="11">
            <el-form-item label="模板名称" prop="name">
              <el-input type="text" v-model="formdata.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="模板" prop="temp">
              <el-input v-model="formdata.temp"></el-input>
              <span class="notice">
                提示：模板格式为：X{D(日期格式)|S(序列号名称，最小值，最大值,步长)},X为任意值
              </span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="模板中文名" prop="cnName">
              <el-input type="text" v-model="formdata.cnName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-row :gutter="20" justify="center">
        <el-col :span="6">
          <zl-button type="primary" @click="save(reqForm)">保存</zl-button>
          <zl-button type="primary" @click="toBack(reqForm)">返回</zl-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<script setup lang="ts">
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'
import { server, zlaxios } from 'lib/zlaxios'
import { reactive, ref } from 'vue'
import type { Template } from './template'

const formdata: Template = reactive<Template>({})
const reqForm = ref<FormInstance>()
const rules = reactive<FormRules>({
  name: [{ required: true, message: '请输入模板名称', trigger: 'blur' }],
  temp: [{ required: true, message: '请输入模板', trigger: 'blur' }]
})

const save = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid) => {
    if (valid) {
      zlaxios.request({
        url: server.base + '/template/add',
        data: formdata,
        method: 'post',
        success: function () {
          toBack(formEl)
          ElMessage({
            message: '新增成功',
            grouping: true,
            type: 'success'
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
<style scoped>
.notice {
  color: rgb(234, 104, 104);
  font-size: 0.05rem;
}
</style>
