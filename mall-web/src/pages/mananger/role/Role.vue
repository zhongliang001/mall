<template>
  <div v-show="page === 'query'">
    <el-container>
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
            <zl-button type="primary" page-name="role" action="add" @click="toAdd">新增</zl-button>
            <zl-button type="primary" page-name="role" action="mod" @click="toMod">修改</zl-button>
            <zl-button type="primary" page-name="role" action="view" @click="toView">
              查看
            </zl-button>
            <zl-button type="primary" page-name="role" action="del" @click="toDel">删除</zl-button>
            <zl-button type="primary" page-name="role" action="config" @click="toEditRight">
              角色权限配置
            </zl-button>
          </el-col>
        </el-row>
        <zl-table ref="zltable" :url="server.base + '/role/'" :query-data="formdata">
          <zl-table-column label="角色id" prop="roleId" />
          <zl-table-column label="角色名" prop="roleName" />
          <zl-table-column label="角色描述" prop="remark" />
          <zl-table-column label="角色状态" prop="state" type="ONLINE_STATE"></zl-table-column>
        </zl-table>
      </el-main>
    </el-container>
  </div>
  <div v-show="page === 'add'">
    <add-role :page="page" @clickBack="back"></add-role>
  </div>
  <div v-show="page === 'mod'">
    <mod-role :page="page" :mod-data="modData" @clickBack="back"></mod-role>
  </div>
  <div v-show="page === 'view'">
    <view-role :page="page" :mod-data="modData" @clickBack="back"></view-role>
  </div>
  <div v-show="page === 'editRight'">
    <role-right :page="page" :mod-data="modData" @clickBack="back"></role-right>
  </div>
</template>
<script lang="ts" setup>
import { ref, reactive } from 'vue'
import type { FormInstance } from 'element-plus'
import { ElMessageBox, ElMessage } from 'element-plus'
import { zlaxios, server } from 'lib/zlaxios'
import AddRole from './AddRole.vue'
import ModRole from './ModRole.vue'
import ViewRole from './ViewRole.vue'
import RoleRight from './right/RoleRight.vue'
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
  if (zltable.value.currentRow) {
    page.value = 'mod'
    console.log(zltable.value.currentRow)
    Object.assign(modData, zltable.value.currentRow)
  } else {
    ElMessageBox.alert('请选择一笔数据', '错误信息', {
      confirmButtonText: '确认'
    })
  }
}
const toView = () => {
  if (zltable.value.currentRow) {
    page.value = 'view'
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
          url: server.base + '/role/delete',
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

const toEditRight = () => {
  if (zltable.value.currentRow) {
    console.log(zltable.value.currentRow)
    Object.assign(modData, zltable.value.currentRow)
    console.log(modData)
    page.value = 'editRight'
  } else {
    ElMessageBox.alert('请选择一笔数据', '错误信息', {
      confirmButtonText: '确认'
    })
  }
}
</script>
