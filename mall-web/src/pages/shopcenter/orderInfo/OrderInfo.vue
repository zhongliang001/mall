<template>
  <div v-show="page === 'query'">
    <el-container>
      <el-header>订单信息</el-header>
      <el-main>
        <el-main>
          <el-form ref="ruleFormRef" :model="formdata">
            <el-row>
              <el-col :span="11">
                <el-form-item label="订单编号" prop="orderId">
                  <el-input v-model="formdata.orderId"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <el-row>
            <el-col :span="11" :offset="11">
              <zl-query :zltable="zltable" :formRef="ruleFormRef"></zl-query>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="10" :offset="0">
              <zl-button type="primary" page-name="orderInfo" action="add" @click="toAdd">
                新增
              </zl-button>
            </el-col>
          </el-row>
          <zl-table ref="zltable" :url="server.shop + '/orderInfo/'" :query-data="formdata">
            <el-table-column label="订单编号" prop="orderId" />
            <el-table-column label="产品名称" prop="prdName" />
            <el-table-column label="产品sku名称" prop="skuName" />
            <el-table-column label="销售价格" prop="sellprice" />
            <el-table-column label="购买者id" prop="buyerId" />
            <el-table-column label="购买时间" prop="purchaseTime" />
          </zl-table>
        </el-main>
      </el-main>
    </el-container>
  </div>
  <div v-show="page === 'add'">
    <add-order-info @clickBack="back"></add-order-info>
  </div>
</template>
<script setup lang="ts">
import { reactive, ref } from 'vue'
import { server } from 'lib/zlaxios'
import type { OrderInfo } from './orderInfo'
import AddOrderInfo from './AddOrderInfo.vue'

const zltable: any = ref(null)
const formdata = reactive<OrderInfo>({})
const ruleFormRef = reactive({})
const page = ref('query')
const toAdd = () => {
  page.value = 'add'
}

const back = () => {
  page.value = 'query'
  zltable.value.query()
}
</script>
