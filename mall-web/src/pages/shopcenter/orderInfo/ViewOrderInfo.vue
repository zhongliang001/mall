<template>
  <div>
    <el-container>
      <el-header>查看订单</el-header>
      <el-main>
        <el-form ref="reqForm" :rules="rules" :model="formdata">
          <el-row>
            <el-col :span="11">
              <el-form-item label="商品名" prop="prdId">
                <zl-select
                  v-model="formdata.prdId"
                  :options="prds"
                  @change="change(formdata.prdId)"
                  :disabled="true"
                ></zl-select>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="商品sku" prop="skuId">
                <zl-select v-model="formdata.skuId" :options="skus" :disabled="true"></zl-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="11">
              <el-form-item label="销售价格" prop="sellPrice">
                <zl-input-cur v-model="formdata.sellPrice" :readonly="true"></zl-input-cur>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="购买者id" prop="buyerId">
                <el-input v-model="formdata.buyerId" :readonly="true"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
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
              <el-form-item label="销售邮费" prop="sellPostage">
                <zl-input-cur v-model="formdata.sellPostage" :readonly="true"></zl-input-cur>
              </el-form-item>
            </el-col>

            <el-col :span="11">
              <el-form-item label="采购邮费" prop="purchasePostage">
                <zl-input-cur v-model="formdata.purchasePostage" :readonly="true"></zl-input-cur>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="11">
              <el-form-item label="销售数量" prop="sales">
                <el-input v-model="formdata.sales" type="number" :readonly="true"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="交易时间" prop="purchaseTime">
                <el-date-picker
                  v-model="formdata.purchaseTime"
                  type="datetime"
                  format="YYYY-MM-DD HH:mm:ss"
                  value-format="YYYYMMDD HH:mm:ss"
                  :readonly="true"
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
          <el-row>
            <el-col :span="11">
              <el-form-item label="交易状态" prop="state">
                <zl-dict v-model="formdata.state" type="TRANS_STATE" :disabled="true"></zl-dict>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="采购价格" prop="vendorPrice">
                <zl-input-cur v-model="formdata.vendorPrice" :readonly="true"></zl-input-cur>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
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
import { changeProductInfo, queryProductSelect, skus, prds } from '../productInfo/productInfo'
const props = defineProps(['page', 'modData'])
const reqForm = ref()
const formdata = reactive<OrderInfo>({})
const rules = reactive<FormRules>({})

watch(
  () => props.page,
  (newVal) => {
    if (newVal === 'view') {
      Object.assign(formdata, props.modData)
      let prdId = formdata.prdId
      if (prdId) {
        changeProductInfo(prdId)
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

const change = (value: string | undefined) => {
  alert('hi')
  formdata.skuId = ''
  changeProductInfo(value)
}
</script>
