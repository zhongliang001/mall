<template>
  <div v-show="page === 'query'">
    <el-container>
      <el-header>数据字典查询</el-header>
      <el-main>
        <el-form ref="ruleFormRef" :model="formdata">
          <el-row>
            <el-col :span="11">
              <el-form-item label="字典类型中文名" prop="dictTypeName">
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
        <el-row>
          <el-col :span="11" :offset="11">
            <zl-query :zltable="zltable" :formRef="ruleFormRef"></zl-query>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="10" :offset="0">
            <zl-button type="primary" page-name="dict" action="add" @click="toAdd">新增</zl-button>
            <zl-button type="primary" page-name="dict" action="mod" @click="toMod">修改</zl-button>
            <zl-button type="primary" page-name="dict" action="view" @click="toView">
              查看
            </zl-button>
          </el-col>
        </el-row>
        <zl-table
          ref="zltable"
          :url="server.base + '/dict/queryDictTypeList'"
          :query-data="formdata"
        >
          <el-table-column label="字典类型名" prop="dictTypeName" />
          <el-table-column label="字典类型" prop="dictType" />
        </zl-table>
      </el-main>
    </el-container>
  </div>
  <div v-show="page === 'add'">
    <add-dict @clickBack="back"></add-dict>
  </div>
  <div v-show="page === 'mod'">
    <mod-dict @clickBack="back" :data="data"></mod-dict>
  </div>
  <div v-show="page === 'view'">
    <view-dict @clickBack="back" :data="data"></view-dict>
  </div>
</template>
<script setup lang="ts">
import { reactive, ref } from 'vue'
import { ElMessage, type FormInstance } from 'element-plus'
import type { Dict } from './dict'
import AddDict from './AddDict.vue'
import ModDict from './ModDict.vue'
import ViewDict from './ViewDict.vue'
import { server } from 'lib/zlaxios'
const formdata: Dict = reactive<Dict>({})
const ruleFormRef = ref<FormInstance>()
const zltable: any = ref(null)
const page = ref('query')
const back = () => {
  page.value = 'query'
  zltable.value.query()
}

const toAdd = () => {
  page.value = 'add'
}
let data = ref({})
const toMod = () => {
  if (zltable.value.currentRow) {
    page.value = 'mod'
    data.value = zltable.value.currentRow
  } else {
    ElMessage({
      message: '请选择一笔数据',
      grouping: true,
      type: 'error'
    })
  }
}
const toView = () => {
  if (zltable.value.currentRow) {
    page.value = 'view'
    data.value = zltable.value.currentRow
  } else {
    ElMessage({
      message: '请选择一笔数据',
      grouping: true,
      type: 'error'
    })
  }
}
</script>
