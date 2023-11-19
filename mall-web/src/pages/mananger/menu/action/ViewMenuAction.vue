<template>
  <el-container>
    <el-header>菜单操作详情</el-header>
    <el-main>
      <el-form ref="reqForm">
        <el-row>
          <el-col :span="11">
            <el-form-item label="菜单名" prop="menuName">
              <el-input v-model="modAction.menuName" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="菜单中文名" prop="menuCnName">
              <el-input v-model="modAction.menuCnName" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="操作名" prop="actionName">
              <el-input v-model="modAction.actionName" :readonly="true" />
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="操作编码" prop="actionCode">
              <el-input v-model="modAction.actionCode" :readonly="true" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="菜单状态" prop="state">
              <zl-select v-model="modAction.state" type="ONLINE_STATE" :disabled="true"></zl-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-row>
        <el-col :span="11" :offset="11">
          <el-button type="primary" @click="toBack()">返回</el-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<script lang="ts" setup>
import { reactive, watch } from 'vue'
import type { MenuAction } from './MenuAction'

const props = defineProps(['modData', 'page'])
const emit = defineEmits(['clickBackAction'])

const modAction: MenuAction = reactive({
  actionId: '',
  menuId: '',
  actionName: '',
  actionCode: '',
  state: '',
  menuName: '',
  menuCnName: ''
})

console.log('被输出值{ modeAction }的输出结果是：', props.modData)
watch(
  () => props.modData,
  (newval) => {
    console.log('被输出值{ modeAction2222 }的输出结果是：', props.modData)
    Object.assign(modAction, newval)
  },
  { deep: true }
)

const toBack = () => {
  // 调用父组件的方法，emit('clickBack', params)
  emit('clickBackAction')
}
</script>
