<template>
  <div>
    <el-container>
      <el-header>新增订单</el-header>
      <el-main>
        <el-form ref="reqForm" :rules="rules" :model="formdata">
          <el-row>
            <el-col :span="11">
              <el-form-item label="商品名" prop="prdId">
                <zl-select
                  v-model="formdata.prdId"
                  filterable
                  :options="prds"
                  @change="change(formdata.prdId)"
                ></zl-select>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="商品sku" prop="skuId">
                <zl-select
                  v-model="formdata.skuId"
                  filterable
                  :options="skus"
                  :loading="skuLoading"
                ></zl-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="11">
              <el-form-item label="销售价格" prop="sellPrice">
                <zl-input-cur v-model="formdata.sellPrice"></zl-input-cur>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="购买者id" prop="buyerId">
                <el-input v-model="formdata.buyerId"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="11">
              <el-form-item label="平台订单号" prop="platOrderId">
                <el-input v-model="formdata.platOrderId"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="采购订单号" prop="vendorOrderId">
                <el-input v-model="formdata.vendorOrderId"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="11">
              <el-form-item label="销售邮费" prop="sellPostage">
                <zl-input-cur v-model="formdata.sellPostage"></zl-input-cur>
              </el-form-item>
            </el-col>

            <el-col :span="11">
              <el-form-item label="采购邮费" prop="purchasePostage">
                <zl-input-cur v-model="formdata.purchasePostage"></zl-input-cur>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="11">
              <el-form-item label="销售数量" prop="sales">
                <el-input v-model="formdata.sales" type="number"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="交易时间" prop="purchaseTime">
                <el-date-picker
                  v-model="formdata.purchaseTime"
                  type="datetime"
                  format="YYYY-MM-DD HH:mm:ss"
                  value-format="YYYYMMDD HH:mm:ss"
                ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="16">
              <el-form-item label="地址" for="area" prop="area">
                <zl-select-area v-model="formdata.area"></zl-select-area>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="11">
              <el-form-item label="交易状态" prop="state">
                <zl-dict v-model="formdata.state" type="TRANS_STATE"></zl-dict>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="采购价格" prop="vendorPrice">
                <zl-input-cur v-model="formdata.vendorPrice"></zl-input-cur>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-row :gutter="20" justify="center">
          <el-col :span="6">
            <zl-button type="primary" :loading="loading" @click="save(reqForm)">保存</zl-button>
            <zl-button type="primary" @click="toBack(reqForm)">返回</zl-button>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>
<script setup lang="ts">
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'
import { reactive, ref, watch } from 'vue'
import type { OrderInfo } from './orderInfo'
import {
  changeProductInfo,
  queryProductSelect,
  skus,
  prds,
  skuLoading
} from '../productInfo/productInfo'
import { server, zlaxios } from 'lib/zlaxios'
const loading = ref(false)
const props = defineProps(['page'])
const reqForm = ref()
const formdata = reactive<OrderInfo>({})
const rules = reactive<FormRules>({})

watch(
  () => props.page,
  (newVal) => {
    if (newVal === 'add') {
      queryProductSelect()
    }
  }
)

const save = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid) => {
    if (valid) {
      zlaxios.request({
        url: server.shop + '/orderInfo/add',
        data: formdata,
        method: 'post',
        loading: loading,
        success: function () {
          toBack(formEl)
          ElMessage({
            message: '新增成功',
            grouping: true,
            type: 'success'
          })
        },
        failed: function (data: any) {
          ElMessage({
            message: data.msg,
            grouping: true,
            type: 'error'
          })
        }
      })
    }
  })
}

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
  formdata.skuId = ''
  changeProductInfo(value)
}
</script>
