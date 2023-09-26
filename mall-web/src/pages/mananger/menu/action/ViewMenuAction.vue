<template>
  <el-container>
    <el-header>菜单操作详情</el-header>
    <el-main>
      <el-form ref="reqForm">
        <el-row>
          <el-col :span="11">
            <el-form-item label="菜单名" prop="menuName">
              <el-input v-model="modData.menuName" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="菜单中文名" prop="menuCnName">
              <el-input v-model="modData.menuCnName" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="操作名" prop="actionName">
              <el-input v-model="modData.actionName" :readonly="true" />
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="操作编码" prop="actionCode">
              <el-input v-model="modData.actionCode" :readonly="true" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="菜单状态" prop="state">
              <zl-select v-model="modData.state" type="ONLINE_STATE" :disabled="true"></zl-select>
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
const props = defineProps(['modData', 'page'])
const emit = defineEmits(['clickBackAction'])

const toBack = () => {
  // 调用父组件的方法，emit('clickBack', params)
  emit('clickBackAction')
}
</script>
