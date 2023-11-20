<template>
  <div v-show="page === 'query'">
    <el-container>
      <el-header>流水号模板查询</el-header>
      <el-main>
        <el-form ref="formRef" :model="formdata">
          <el-row>
            <el-col :span="11">
              <el-form-item label="模板id" prop="id">
                <el-input v-model="formdata.id"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="模板名" prop="name">
                <el-input v-model="formdata.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-row>
          <el-col :span="11" :offset="11">
            <zl-query :zltable="zltable" :formRef="formRef"></zl-query>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="10" :offset="0">
            <zl-button type="primary" page-name="template" action="add" @click="toAdd">
              新增
            </zl-button>
            <zl-button type="primary" page-name="template" action="mod" @click="toMod">
              修改
            </zl-button>
            <zl-button type="primary" page-name="template" action="del" @click="toDel">
              删除
            </zl-button>
            <zl-button type="primary" page-name="template" action="view" @click="toView">
              查看
            </zl-button>
          </el-col>
        </el-row>
        <zl-table ref="zltable" :url="server.base + '/template/'" :query-data="formdata">
          <el-table-column label="模板id" prop="id" />
          <el-table-column label="模板名" prop="name" />
          <el-table-column label="模板" prop="temp" />
          <el-table-column label="模板中文名" prop="cnName" />
        </zl-table>
      </el-main>
    </el-container>
  </div>
  <div v-show="page === 'add'">
    <add-template @clickBack="back"></add-template>
  </div>
  <div v-show="page === 'mod'">
    <mod-template @clickBack="back" :data="data"></mod-template>
  </div>
  <div v-show="page === 'view'">
    <view-template @clickBack="back" :data="data"></view-template>
  </div>
</template>
<script setup lang="ts">
import { ElMessage, ElMessageBox, type FormInstance } from 'element-plus'
import { reactive, ref } from 'vue'
import { server, zlaxios } from 'lib/zlaxios'
import AddTemplate from './AddTemplate.vue'
import ModTemplate from './ModTemplate.vue'
import ViewTemplate from './ViewTemplate.vue'
import type { Template } from './template'
const zltable: any = ref(null)
const formRef = ref<FormInstance>()
let page = ref('query')

const formdata: Template = reactive<Template>({})

const toAdd = () => {
  page.value = 'add'
}

const data = ref<Template>({})

const toMod = () => {
  const currentRow = zltable.value.currentRow
  if (currentRow) {
    page.value = 'mod'
    data.value = currentRow
  } else {
    ElMessageBox.alert('请选择一笔数据', '错误信息', {
      confirmButtonText: '确认'
    })
  }
}

const toView = () => {
  const currentRow = zltable.value.currentRow
  if (currentRow) {
    page.value = 'view'
    data.value = currentRow
  } else {
    ElMessageBox.alert('请选择一笔数据', '错误信息', {
      confirmButtonText: '确认'
    })
  }
}

const toDel = () => {
  if (zltable.value.currentRow) {
    ElMessageBox.confirm('请确认是否删除数据?', {
      confirmButtonText: ' 确认',
      cancelButtonText: '返回',
      type: 'warning'
    })
      .then(() => {
        zlaxios.request({
          url: server.base + '/template/delete',
          params: { id: zltable.value.currentRow.id },
          method: 'get',
          success: function (data: any) {
            zltable.value.query()
            ElMessage({
              message: '删除成功',
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
      })
      .catch((e) => {})
  } else {
    ElMessageBox.alert('请选择一笔数据', '错误信息', {
      confirmButtonText: '确认'
    })
  }
}

const back = () => {
  page.value = 'query'
  zltable.value.query()
}
</script>
