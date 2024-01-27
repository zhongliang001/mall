<template>
  <div>
    <el-container>
      <el-header>查看订单</el-header>
      <el-main>
        <el-form ref="reqForm" :rules="rules" :model="formdata">
          <el-row>
            <el-col :span="11">
              <el-form-item label="平台订单号" prop="platOrderId">
                <el-input v-model="formdata.platOrderId" :readonly="true"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="采购订单号" prop="vendorOrderId">
                <el-input v-model="formdata.vendorOrderId" :readonly="true"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="11">
              <el-form-item label="购买者id" prop="buyerId">
                <el-input v-model="formdata.buyerId" :readonly="true"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="交易状态" prop="state">
                <zl-dict v-model="formdata.state" type="TRANS_STATE" :disabled="true"></zl-dict>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="11">
              <el-form-item label="交易时间" prop="purchaseTime">
                <el-date-picker
                  v-model="formdata.purchaseTime"
                  type="datetime"
                  format="YYYY-MM-DD HH:mm:ss"
                  value-format="YYYYMMDD HH:mm:ss"
                  :disabled="true"
                ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="16">
              <el-form-item label="地址" for="area" prop="area">
                <zl-select-area v-model="formdata.area" :disabled="true"></zl-select-area>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <zl-table
          ref="zlPrdTable"
          :show-page="false"
          :url="server.shop + '/orderDetail/'"
          :query-data="tabledata"
        >
          <el-table-column label="商品名称" prop="prdName" />
          <el-table-column label="sku名称" prop="skuName" />
          <el-table-column label="销售价格" prop="sellPrice" />
          <el-table-column label="销售邮费" prop="sellPostage" />
          <el-table-column label="采购价格" prop="vendorPrice" />
          <el-table-column label="采购邮费" prop="purchasePostage" />
          <el-table-column label="销售数量" prop="sales" />
        </zl-table>
        <el-row :gutter="20" justify="center">
          <el-col :span="6">
            <zl-button type="primary" @click="toBack(reqForm)">返回</zl-button>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>
<script setup lang="ts">
import type { FormInstance, FormRules } from 'element-plus'
import { onMounted, reactive, ref, watch } from 'vue'
import type { OrderInfo } from './orderInfo'
import { queryProductSelect } from '../productInfo/productInfo'
import { server } from 'lib/zlaxios'
import { userStore } from '@/stores/userStore'
const us = userStore()
const props = defineProps(['page', 'modData'])
const reqForm = ref()
const formdata = reactive<OrderInfo>({})
const rules = reactive<FormRules>({})
const tabledata = reactive({ orderId: '',shopId:us.shopId() })
const zlPrdTable: any = ref(null)
watch(
  () => props.page,
  (newVal) => {
    if (newVal === 'view') {
      Object.assign(formdata, props.modData)
      if (formdata.orderId) {
        tabledata.orderId = formdata.orderId
        zlPrdTable.value.query()
      }
    }
  }
)

onMounted(() => {
  queryProductSelect()
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
</script>
