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
            <el-button type="primary" @click="toMod">修改</el-button>
            <el-button type="primary" @click="toView">查看</el-button>
            <el-button type="primary" @click="toDel">删除</el-button>
            <el-button type="primary" @click="toEditAction">菜单操作配置</el-button>
          </el-col>
        </el-row>
        <zl-table ref="zltable" url="/user/menu/" :query-data="formdata">
          <el-table-column label="菜单名" prop="menuName" />
          <el-table-column label="菜单中文名" prop="menuCnName" />
          <el-table-column label="菜单路由" prop="path" />
        </zl-table>
      </el-main>
    </el-container>
  </div>
  <div v-show="page === 'add'">
    <add-menu :page="page" @clickBack="back" />
  </div>
  <div v-show="page === 'mod'">
    <mod-menu :page="page" :mod-data="modData" @clickBack="back" />
  </div>
  <div v-show="page === 'view'">
    <view-menu :page="page" :mod-data="modData" @clickBack="back" />
  </div>
  <div v-show="page === 'editAction'">
    <menu-action :page="page" :mod-data="modData" @clickBack="back"></menu-action>
  </div>
</template>
<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormInstance } from 'element-plus'
import AddMenu from './AddMenu.vue'
import ModMenu from './ModMenu.vue'
import ViewMenu from './ViewMenu.vue'
import MenuAction from './action/MenuAction.vue'
import zlaxios from 'lib/zlaxios'
import { ElMessage, ElMessageBox } from 'element-plus'
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

const back = () => {
  page.value = 'query'
  zltable.value.query()
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
          url: '/user/menu/delete',
          params: { menuId: zltable.value.currentRow.menuId },
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

const toEditAction = () => {
  if (zltable.value.currentRow) {
    page.value = 'editAction'
    Object.assign(modData, zltable.value.currentRow)
  } else {
    ElMessageBox.alert('请选择一笔数据', '错误信息', {
      confirmButtonText: '确认'
    })
  }
}
</script>
