<template>
  <div>
    <el-container v-show="page === 'query'">
      <el-header>角色查询</el-header>
      <el-main>
        <el-form ref="ruleFormRef" :model="formdata">
          <el-row>
            <el-col :span="11">
              <el-form-item label="角色名" prop="menuName">
                <el-input v-model="formdata.roleName"></el-input>
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
            <el-button type="primary" @click="toMod">修改</el-button>
            <el-button type="primary" @click="toView">查看</el-button>
            <el-button type="primary" @click="toDel">删除</el-button>
          </el-col>
        </el-row>
        <zl-table ref="zltable" url="/user/role/" :query-data="formdata">
          <zl-table-column label="角色id" prop="roleId" />
          <zl-table-column label="角色名" prop="roleName" />
          <zl-table-column label="角色描述" prop="remark" />
          <zl-table-column label="角色状态" prop="state" type="ONLINE_STATE"></zl-table-column>
        </zl-table>
      </el-main>
    </el-container>
    <el-container v-show="page === 'add'">
      <add-role :page="page" @clickBack="back"></add-role>
    </el-container>
    <el-container v-show="page === 'mod'">
      <mod-role :page="page" :mod-data="modData" @clickBack="back"></mod-role>
    </el-container>
    <el-container v-show="page === 'view'">
      <view-role :page="page" :mod-data="modData" @clickBack="back"></view-role>
    </el-container>
  </div>
</template>
<script lang="ts" setup>
import { ref, reactive } from 'vue'
import type { FormInstance } from 'element-plus'
import { ElMessageBox, ElMessage } from 'element-plus'
import zlaxios from 'lib/zlaxios'
import AddRole from './AddRole.vue'
import ModRole from './ModRole.vue'
import ViewRole from './ViewRole.vue'
const ruleFormRef = ref<FormInstance>()
let page = ref('query')
const zltable: any = ref(null)
const formdata = reactive({
  roleName: ''
})

const back = () => {
  page.value = 'query'
  zltable.value.query()
}
const toAdd = () => {
  page.value = 'add'
}

const modData = {}
const toMod = () => {
  page.value = 'mod'
  if (zltable.value.currentRow) {
    console.log(zltable.value.currentRow)
    Object.assign(modData, zltable.value.currentRow)
  } else {
    ElMessageBox.alert('请选择一笔数据', '错误信息', {
      confirmButtonText: '确认'
    })
  }
}
const toView = () => {
  page.value = 'view'
  if (zltable.value.currentRow) {
    console.log(zltable.value.currentRow)
    Object.assign(modData, zltable.value.currentRow)
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
          url: '/user/role/delete',
          params: { roleId: zltable.value.currentRow.roleId },
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
</script>
