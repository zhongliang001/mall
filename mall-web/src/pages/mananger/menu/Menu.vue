<template>
  <div>
    <el-container v-show="page === 'query'">
      <el-header>菜单查询</el-header>
      <el-main>
        <el-form ref="ruleFormRef" :model="formdata">
          <el-row>
            <el-col :span="11">
              <el-form-item label="菜单名" prop="menuName">
                <el-input v-model="formdata.menuName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="菜单中文名" prop="menuCnName">
                <el-input v-model="formdata.menuCnName"></el-input>
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
            <el-button type="primary" @click="toAdd">新增</el-button>
          </el-col>
        </el-row>
        <zl-table ref="zltable" url="/user/menu/" :query-data="formdata">
          <el-table-column label="菜单名" prop="menuName" />
          <el-table-column label="菜单中文名" prop="menuCnName" />
          <el-table-column label="菜单路由" prop="path" />
        </zl-table>
      </el-main>
    </el-container>
    <el-container v-show="page === 'add'">
      <add-menu @clickBack="back" />
    </el-container>
  </div>
</template>
<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormInstance } from 'element-plus'
import AddMenu from './AddMenu.vue'
const ruleFormRef = ref<FormInstance>()
const formdata = reactive({
  menuName: '',
  menuCnName: ''
})
let page = ref('query')

const zltable: any = ref(null)

const toAdd = () => {
  page.value = 'add'
}

const back = () => {
  page.value = 'query'
}
</script>
