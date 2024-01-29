<template>
  <el-container>
    <el-header>新增地域数据</el-header>
    <el-main>
      <el-form ref="reqForm" :model="formData" :rules="formRules">
        <el-row>
          <el-col :span="11">
            <el-form-item label="日期" prop="statisticsDate">
              <el-date-picker
                v-model="formData.statisticsDate"
                format="YYYY-MM-DD"
                value-format="YYYY-MM-DD"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="地区" prop="area">
              <zl-select v-model="formData.area" filterable :options="options"></zl-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="访客数" prop="visitors">
              <el-input type="number" v-model="formData.visitors"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="订单数量" prop="orders">
              <el-input type="number" v-model="formData.orders"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-row>
        <el-col :span="11" :offset="11">
          <el-button type="primary" :loading="loading" @click="add(reqForm)">保存</el-button>
          <el-button type="primary" @click="toBack(reqForm)">返回</el-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<script setup lang="ts">
import { reactive, ref, watch } from 'vue'
import type { FormInstance } from 'element-plus'
import {
  type AreaStatistics,
  formRules,
  options
} from '@/pages/shopcenter/areastatistics/AreaStatistics'
import { addAreaStatistics } from '@/api/shop/areaStatisticsApi'
import { userStore } from '@/stores/userStore'
const us = userStore()
const props = defineProps(['page'])
const reqForm = ref<FormInstance>()
const formData = reactive<AreaStatistics>({shopId: us.shopId()})
const loading = ref(false)

watch(
  () => props.page,
  (newVal) => {
    if (newVal === 'add') {
      reqForm.value?.resetFields()
    }
  }
)
/**
 * 保存
 * @param formEl 表单对象
 */
const add = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      addAreaStatistics(formData, loading, formEl, toBack)
    }
  })
}
// emit 获取父组件传来的方法
const emit = defineEmits(['clickBack'])
/**
 * 返回查询页
 * @param formEl 表单对象
 */
const toBack = (formEl: FormInstance | undefined) => {
  // 调用父组件的方法，emit('clickBack', params)
  emit('clickBack')
  if (!formEl) {
    return
  }
  formEl.resetFields()
}
</script>

<style scoped></style>
