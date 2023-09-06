<template>
  <el-container>
    <el-header>查看页面</el-header>
    <el-main>
      <el-form ref="reqForm" :model="formdata">
        <el-row>
          <el-col :span="11">
            <el-form-item label="角色名" prop="roleName">
              <el-input type="text" v-model="formdata.roleName" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="角色描述" prop="remark">
              <el-input v-model="formdata.remark" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="角色状态" prop="state">
              <zl-select v-model="formdata.state" type="ONLINE_STATE" :disabled="true"></zl-select>
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
import type { FormInstance } from 'element-plus'

const reqForm = ref<FormInstance>()
const props = defineProps(['page', 'modData'])
const formdata = reactive({
  roleName: '',
  remark: '',
  state: ''
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

watch(
  () => props.page,
  (newVal) => {
    if (newVal === 'view') {
      Object.assign(formdata, props.modData)
    }
  }
)
</script>
