<template>
  <div v-show="rightPage === 'query'">
    <el-container>
      <el-header>角色权限查询</el-header>
      <el-main>
        <el-form :model="formdata">
          <el-row>
            <el-col :span="11">
              <el-form-item label="角色id" prop="menuName">
                <el-input v-model="editData.roleId" :readonly="true"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="角色名" prop="menuName">
                <el-input v-model="editData.roleName" :readonly="true"></el-input>
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
            <el-button type="primary" @click="toConfig">配置权限</el-button>
          </el-col>
        </el-row>
        <zl-table
          ref="zltable"
          :url="server.base + '/roleRight/queryDetailList'"
          :query-data="formdata"
        >
          <el-table-column label="权限id" prop="rightId" />
          <el-table-column label="角色id" prop="roleId" />
          <el-table-column label="角色名" prop="roleName" />
          <el-table-column label="菜单名" prop="menuCnName" />
          <el-table-column label="操作名" prop="actionName" />
        </zl-table>
        <el-row>
          <el-col :span="11" :offset="11">
            <el-button type="primary" @click="toBack(reqForm)">返回</el-button>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
  <div v-show="rightPage === 'config'">
    <config-role-right
      :page="rightPage"
      :data="editData"
      @clickBackAction="back"
    ></config-role-right>
  </div>
</template>
<script lang="ts" setup>
import { reactive, ref, watch } from 'vue'
import type { Role } from '../role'
import type { Right } from './right'
import ConfigRoleRight from './ConfigRoleRight.vue'
import type { FormInstance } from 'element-plus'
import { server } from 'lib/zlaxios'

const rightPage = ref('query')

const reqForm = ref<FormInstance>()
const zltable: any = ref(null)
const props = defineProps(['page', 'modData'])

const formdata: Right = reactive({
  rightId: '',
  roleId: '',
  actionId: ''
})

const editData: Role = reactive({
  roleId: '',
  roleName: ''
})

const back = () => {
  rightPage.value = 'query'
  zltable.value.query()
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

watch(
  () => props.page,
  (newVal) => {
    if (newVal === 'editRight') {
      formdata.roleId = props.modData.roleId
      editData.roleId = props.modData.roleId
      editData.roleName = props.modData.roleName
      zltable.value.query()
    }
  }
)

const toConfig = () => {
  rightPage.value = 'config'
}
</script>
