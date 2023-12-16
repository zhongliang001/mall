<template>
  <el-container>
    <el-header>修改页面</el-header>
    <el-main>
      <el-form :rules="rules" ref="reqForm" :model="formdata">
        <el-row>
          <el-col :span="11">
            <el-form-item label="菜单中文名" prop="menuCnName">
              <el-input type="text" v-model="formdata.menuCnName"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="菜单名" prop="menuName">
              <el-input v-model="formdata.menuName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="菜单路由" prop="path">
              <el-input v-model="formdata.path"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="菜单状态" prop="state">
              <zl-dict v-model="formdata.state" type="ONLINE_STATE"></zl-dict>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="菜单组件" prop="state">
              <el-input v-model="formdata.comp"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="父菜单" prop="parentId">
            <el-select filterable v-model="formdata.parentId">
              <el-option
                v-for="item in parents"
                :key="item.menuId"
                :label="item.menuCnName"
                :value="item.menuId"
              />
            </el-select>
          </el-form-item>
        </el-row>
      </el-form>
      <el-row>
        <el-col :span="11" :offset="11">
          <el-button type="primary" @click="save(reqForm)">保存</el-button>
          <el-button type="primary" @click="toBack(reqForm)">返回</el-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script lang="ts" setup>
import { reactive, ref, watch } from 'vue'
import { zlaxios, server } from 'lib/zlaxios'
import type { FormInstance, FormRules } from 'element-plus'
import { ElMessage } from 'element-plus'
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

const rules = reactive<FormRules>({
  menuCnName: [{ required: true, message: '请输入菜单中文名', trigger: 'blur' }],
  menuName: [{ required: true, message: '请输入菜单名', trigger: 'blur' }],
  state: [{ required: true, message: '请输入菜单状态', trigger: 'blur' }]
})

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
    if (newVal === 'mod') {
      Object.assign(formdata, props.modData)
      zlaxios.request({
        url: server.base + '/menu/selectRoot',
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

const save = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      zlaxios.request({
        url: server.base + '/menu/update',
        data: formdata,
        method: 'post',
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
