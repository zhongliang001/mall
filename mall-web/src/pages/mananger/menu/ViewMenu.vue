<template>
  <el-container>
    <el-header>查看页面</el-header>
    <el-main>
      <el-form ref="reqForm" :model="formdata">
        <el-row>
          <el-col :span="11">
            <el-form-item label="菜单中文名" prop="menuCnName">
              <el-input type="text" v-model="formdata.menuCnName" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="菜单名" prop="menuName">
              <el-input v-model="formdata.menuName" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="菜单路由" prop="path">
              <el-input v-model="formdata.path" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="菜单状态" prop="state">
              <zl-select v-model="formdata.state" type="ONLINE_STATE" :readonly="true"></zl-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="父菜单" prop="parentId">
            <el-select filterable v-model="formdata.parentId" :disabled="true">
              <el-option
                v-for="item in parents"
                :key="item.menuId"
                :label="item.menuCnName"
                :value="item.menuId"
              />
            </el-select>
          </el-form-item>
          <el-col :span="11">
            <el-form-item label="菜单组件" prop="state">
              <el-input v-model="formdata.comp"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-row>
        <el-col :span="11" :offset="11">
          <el-button type="primary" @click="toBack(reqForm)">返回</el-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script lang="ts" setup>
import { reactive, ref, watch } from 'vue'
import zlaxios from 'lib/zlaxios'
import type { FormInstance } from 'element-plus'

const reqForm = ref<FormInstance>()
const props = defineProps(['page', 'modData'])
const formdata = reactive({
  menuCnName: '',
  menuName: '',
  path: '',
  state: '',
  parentId: '',
  comp: ''
})

// emit 获取父组件传错来的方法
const emit = defineEmits(['clickBack'])
const toBack = (formEl: FormInstance | undefined) => {
  // 调用父组件的方法，emit('clickBack', params)
  emit('clickBack')
  if (!formEl) {
    return
  }
  formEl.resetFields()
}
type pa = {
  menuId: string | any
  menuCnName: string | any
  menuName: string | any
  path: string | any
  parentId: string | any
  state: string | any
}

const parents: pa[] = reactive([])
watch(
  () => props.page,
  (newVal) => {
    if (newVal === 'view') {
      Object.assign(formdata, props.modData)
      zlaxios.request({
        url: '/user/menu/selectRoot',
        method: 'get',
        success: function (data: any) {
          parents.length = 0
          data.data.forEach((e: pa) => {
            parents.push(e)
          })
        }
      })
    }
  }
)
</script>
