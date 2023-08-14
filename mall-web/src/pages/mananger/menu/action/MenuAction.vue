<template>
  <el-container v-show="actionPage === 'query'">
    <el-header>菜单操作查询</el-header>
    <el-main>
      <el-form ref="reqForm" :model="formdata">
        <el-row>
          <el-col :span="11">
            <el-form-item label="菜单名" prop="menuName">
              <el-input v-model="addActionData.menuName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="菜单中文名" prop="menuCnName">
              <el-input v-model="addActionData.menuCnName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-row>
        <el-col :span="11" :offset="11">
          <zl-query :zltable="zltable" :formRef="reqForm"></zl-query>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="10" :offset="0">
          <el-button type="primary" @click="toAdd">新增</el-button>
          <el-button type="primary" @click="toMod">修改</el-button>
          <el-button type="primary" @click="toDel">删除</el-button>
          <el-button type="primary" @click="toView">查看</el-button>
        </el-col>
      </el-row>
      <zl-table ref="zltable" url="/user/menuAction/" :query-data="formdata">
        <el-table-column label="操作id" prop="actionId" />
        <el-table-column label="菜单id" prop="menuId" />
        <el-table-column label="操作编码" prop="actionCode" />
        <el-table-column label="操作名称" prop="actionName" />
      </zl-table>
      <el-row>
        <el-col :span="11" :offset="11">
          <el-button type="primary" @click="toBack(reqForm)">返回</el-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
  <div v-show="actionPage === 'add'">
    <add-menu-action
      :page="actionPage"
      :addData="addActionData"
      @clickBackAction="back"
    ></add-menu-action>
  </div>
  <div v-show="actionPage === 'mod'">
    <mod-menu-action
      :page="actionPage"
      :mod-data="modAction"
      @clickBackAction="back"
    ></mod-menu-action>
  </div>
  <div v-show="actionPage === 'view'">
    <view-menu-action
      :page="actionPage"
      :mod-data="modAction"
      @clickBackAction="back"
    ></view-menu-action>
  </div>
</template>
<script lang="ts" setup>
import { ElMessage, ElMessageBox, type FormInstance } from 'element-plus'
import { reactive, ref, watch } from 'vue'
import AddMenuAction from './AddMenuAction.vue'
import ModMenuAction from './ModMenuAction.vue'
import ViewMenuAction from './ViewMenuAction.vue'
import type { Action, MenuAction } from './MenuAction'
import zlaxios from 'lib/zlaxios'
const reqForm = ref<FormInstance>()
// emit 获取父组件传错来的方法
const emit = defineEmits(['clickBack'])
const props = defineProps(['page', 'modData'])
let addActionData = reactive({
  menuId: '',
  menuName: '',
  menuCnName: ''
})
const zltable: any = ref(null)
const formdata: Action = reactive({
  actionId: '',
  menuId: '',
  actionName: '',
  actionCode: '',
  state: ''
})

const actionPage = ref('query')
watch(
  () => props.page,
  (newVal) => {
    if (newVal === 'editAction') {
      addActionData.menuName = props.modData.menuName
      addActionData.menuCnName = props.modData.menuCnName
      addActionData.menuId = props.modData.menuId
      formdata.menuId = props.modData.menuId
      zltable.value.query()
    }
  }
)

const back = () => {
  actionPage.value = 'query'
  zltable.value.query()
}

const toBack = (formEl: FormInstance | undefined) => {
  // 调用父组件的方法，emit('clickBack', params)
  emit('clickBack')
  if (!formEl) {
    return
  }
  formEl.resetFields()
}

const toAdd = () => {
  actionPage.value = 'add'
}
let modAction: MenuAction = reactive({
  actionId: '',
  menuId: '',
  actionName: '',
  actionCode: '',
  state: '',
  menuName: '',
  menuCnName: ''
})

const toMod = () => {
  if (zltable.value.currentRow) {
    actionPage.value = 'mod'
    const cur = zltable.value.currentRow
    modAction.menuId = cur.menuId
    modAction.actionId = cur.actionId
    modAction.actionName = cur.actionName
    modAction.actionCode = cur.actionCode
    modAction.state = cur.state
    modAction.menuName = addActionData.menuName
    modAction.menuCnName = addActionData.menuCnName
    console.log(addActionData.menuName)
    console.log(addActionData.menuCnName)
  } else {
    ElMessageBox.alert('请选择一笔数据', '错误信息', {
      confirmButtonText: '确认'
    })
  }
}

const toView = () => {
  if (zltable.value.currentRow) {
    actionPage.value = 'view'
    const cur = zltable.value.currentRow
    modAction.menuId = cur.menuId
    modAction.actionId = cur.actionId
    modAction.actionName = cur.actionName
    modAction.actionCode = cur.actionCode
    modAction.state = cur.state
    modAction.menuName = addActionData.menuName
    modAction.menuCnName = addActionData.menuCnName
    console.log(addActionData.menuName)
    console.log(addActionData.menuCnName)
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
          url: '/user/menuAction/delete',
          params: { actionId: zltable.value.currentRow.actionId },
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
