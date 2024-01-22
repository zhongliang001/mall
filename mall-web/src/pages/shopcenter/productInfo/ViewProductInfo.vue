<template>
  <el-container>
    <el-header>新增商品</el-header>
    <el-main>
      <el-form ref="reqForm" :model="formdata">
        <el-row>
          <el-col :span="11">
            <el-form-item label="商品名称" prop="prdName">
              <el-input v-model="formdata.prdName" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="平台商品id" prop="platformPrdId">
              <el-input v-model="formdata.platformPrdId" :readonly="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="商品类型" prop="prdType">
              <zl-dict v-model="formdata.prdType" type="PRD_TYPE" :disabled="true"></zl-dict>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="商品上架状态" prop="state">
              <zl-dict v-model="formdata.state" type="PRD_STATE" :disabled="true"></zl-dict>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="11">
            <el-form-item label="供应商id" prop="vendorId">
              <zl-select
                v-model="formdata.vendorId"
                :options="vendors"
                :disabled="true"
              ></zl-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <zl-table
        ref="zltable"
        :add-able="true"
        :url="server.shop + '/productSku/'"
        :show-page="false"
        :query-data="tabledata"
      >
        <el-table-column label="sku名称" prop="skuName"></el-table-column>
        <el-table-column label="销售价格" prop="sellPrice"></el-table-column>
        <el-table-column label="采购价格" prop="purchPrice"></el-table-column>
      </zl-table>
      <el-row :gutter="20" justify="center">
        <el-col :span="6">
          <zl-button type="primary" @click="toBack(reqForm)">返回</zl-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<script setup lang="ts">
import { reactive, ref, watch } from 'vue'
import type { ProductInfo } from './productInfo'
import { server } from 'lib/zlaxios'
import { userStore } from '@/stores/userStore'
import type { FormInstance } from 'element-plus'
import { queryVendorSelect, vendors } from '../vendor/vendor'
const props = defineProps(['page', 'modData'])

const us = userStore()
const formdata: ProductInfo = reactive({
  shopId: us.shopId()
})

const tabledata: ProductInfo = reactive<ProductInfo>({})
const reqForm = ref<FormInstance>()
const zltable: any = ref(null)
watch(
  () => props.page,
  (newVal) => {
    if (newVal === 'view') {
      Object.assign(formdata, props.modData)
      tabledata.prdId = props.modData.prdId
      zltable.value.query()
      queryVendorSelect()
    }
  }
)

// emit 获取父组件传来的方法
const emit = defineEmits(['clickBack'])
const toBack = (formEl: FormInstance | undefined) => {
  // 调用父组件的方法，emit('clickBack', params)
  emit('clickBack')
  if (!formEl) {
    return
  }
  formEl.resetFields()
  zltable.value.clearData()
}
</script>
