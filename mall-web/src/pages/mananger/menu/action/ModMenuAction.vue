<template>
  <el-container>
    <el-header>修改菜单操作</el-header>
    <el-main>
      <el-form ref="reqForm" :rules="rules" :model="formdata">
        <el-row>
          <el-col :span="11">
            <el-form-item label="菜单名" prop="menuName">
              <el-input v-model="menu.menuName" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="菜单中文名" prop="menuCnName">
              <el-input v-model="menu.menuCnName" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="操作名" prop="actionName">
              <el-input v-model="formdata.actionName" />
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="操作编码" prop="actionCode">
              <el-input v-model="formdata.actionCode" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="菜单状态" prop="state">
              <zl-dict v-model="formdata.state" type="ONLINE_STATE"></zl-dict>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-row>
        <el-col :span="11" :offset="11">
          <el-button type="primary" :loading="loading" @click="update(reqForm)">保存</el-button>
          <el-button type="primary" @click="toBack(reqForm)">返回</el-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<script setup lang="ts">
import type { Menu } from '@/components/menu'
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'
import { zlaxios, server } from 'lib/zlaxios'
import { reactive, ref, watch } from 'vue'
import type { Action } from './MenuAction'

const props = defineProps(['page', 'modData'])
const reqForm = ref<FormInstance>()

const loading = ref(false)

const rules = reactive<FormRules>({
  actionName: [{ required: true, message: '请输入操作名称', trigger: 'blur' }],
  actionCode: [{ required: true, message: '请输入操作码', trigger: 'blur' }],
  state: [{ required: true, message: '请输入操作状态', trigger: 'blur' }]
})

const formdata: Action = reactive({
  actionId: '',
  menuId: '',
  actionName: '',
  actionCode: '',
  state: ''
})

watch(
  () => [props.page, props.modData],
  (newVal) => {
    if (newVal[0] === 'mod') {
      formdata.menuId = props.modData.menuId
      formdata.actionId = props.modData.actionId
      formdata.actionName = props.modData.actionName
      formdata.actionCode = props.modData.actionCode
      formdata.state = props.modData.state
    }
    Object.assign(menu, props.modData)
  }
)
const menu: Menu = reactive({
  menuId: '',
  menuCnName: '',
  path: '',
  menuName: '',
  list: [],
  children: [],
  comp: ''
})

const emit = defineEmits(['clickBackAction'])
const toBack = (formEl: FormInstance | undefined) => {
  // 调用父组件的方法，emit('clickBack', params)
  emit('clickBackAction')
  if (!formEl) {
    return
  }
  formEl.resetFields()
}

const update = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      zlaxios.request({
        url: server.base + '/menuAction/update',
        data: formdata,
        method: 'post',
        loading: loading,
        success: function (data: any) {
          toBack(formEl)
        },
        failed: function (data: any) {
          ElMessage({
            message: data.msg,
            grouping: true,
            type: 'error'
          })
        }
      })
    } else {
      console.log('error submit!', fields)
    }
  })
}
</script>
