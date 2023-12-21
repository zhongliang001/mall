<template>
  <el-container>
    <el-header>修改字典项</el-header>
    <el-main>
      <el-form ref="reqForm" :model="formdata">
        <el-row>
          <el-col :span="11">
            <el-form-item label="字典类型名" prop="dictTypeName">
              <el-input v-model="formdata.dictTypeName" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="字典类型" prop="dictType">
              <el-input v-model="formdata.dictType" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-row>
        <el-col :span="11" :offset="11">
          <zl-query :zltable="zltable" :formRef="reqForm"></zl-query>
        </el-col>
      </el-row>
      <zl-table
        ref="zltable"
        :url="server.base + '/dict/'"
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
          <zl-button type="primary" :loading="loading" @click="save(reqForm)">保存</zl-button>
          <zl-button type="primary" @click="toBack(reqForm)">返回</zl-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<script setup lang="ts">
import { reactive, ref, watch } from 'vue'
import { ElMessage, type FormInstance } from 'element-plus'
import type { Dict } from './dict'
import { zlaxios, server } from 'lib/zlaxios'
const props = defineProps(['data'])
const formdata: Dict = reactive<Dict>({})
const reqForm = ref<FormInstance>()
const loading = ref(false)
watch(
  () => props.data,
  (newval) => {
    Object.assign(formdata, newval)
    zltable.value.query()
  }
)

const zltable: any = ref()

const save = (formEl: FormInstance | undefined) => {
  const tableData = zltable.value.getTableData()
  const delData = zltable.value.getDelData()
  zlaxios.request({
    url: server.base + '/dict/modDict',
    data: {
      dictTypeName: formdata.dictTypeName,
      dictType: formdata.dictType,
      list: tableData,
      delList: delData
    },
    loading: loading,
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
