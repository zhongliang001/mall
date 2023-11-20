<template>
  <el-container>
    <el-header>新增字典项</el-header>
    <el-main>
      <el-form ref="reqForm" :model="formdata" :rules="rules">
        <el-row>
          <el-col :span="11">
            <el-form-item label="字典类型名" prop="dictTypeName">
              <el-input v-model="formdata.dictTypeName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="字典类型" prop="dictType">
              <el-input v-model="formdata.dictType"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <zl-table
        ref="zltable"
        :url="server.base + '/dict/queryList'"
        :query-data="formdata"
        :show-page="false"
        :add-able="true"
      >
        <el-table-column label="字典名" prop="cnName">
          <template #default="scope">
            <el-input v-model="scope.row.cnName"></el-input>
          </template>
        </el-table-column>
        <el-table-column label="字典代码" prop="enName">
          <template #default="scope">
            <el-input v-model="scope.row.enName"></el-input>
          </template>
        </el-table-column>
      </zl-table>
      <el-row :gutter="20" justify="center">
        <el-col :span="6">
          <zl-button type="primary" @click="save(reqForm)">保存</zl-button>
          <zl-button type="primary" @click="toBack(reqForm)">返回</zl-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<script lang="ts" setup>
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'
import { zlaxios, server } from 'lib/zlaxios'
import { reactive, ref } from 'vue'
import type { Dict } from './dict'
const reqForm = ref<FormInstance>()

const formdata: Dict = reactive<Dict>({})
const zltable: any = ref(null)

const rules = reactive<FormRules>({
  dictTypeName: [{ required: true, message: '请输入字典类型名', trigger: 'blur' }],
  dictType: [{ required: true, message: '请输入字典类型', trigger: 'blur' }]
})

const save = async (formEl: FormInstance | undefined) => {
  const tableData = zltable.value.getTableData()
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      zlaxios.request({
        url: server.base + '/dict/modDict',
        data: {
          dictTypeName: formdata.dictTypeName,
          dictType: formdata.dictType,
          list: tableData
        },
        method: 'post',
        success: function (data: any) {
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
  zltable.value.clearData()
}
</script>
