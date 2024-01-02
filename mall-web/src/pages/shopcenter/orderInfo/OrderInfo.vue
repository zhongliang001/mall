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
              <el-col :span="11">
                <el-form-item label="交易状态" prop="state">
                  <zl-dict v-model="formdata.state" type="TRANS_STATE"></zl-dict>
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
              <zl-button
                type="primary"
                page-name="orderInfo"
                action="mod"
                @click="mod(zltable, toMod)"
              >
                修改
              </zl-button>
              <zl-button
                type="primary"
                page-name="orderInfo"
                action="view"
                @click="mod(zltable, toView)"
              >
                查看
              </zl-button>
              <zl-button type="primary" page-name="orderInfo" action="del" @click="toDel">
                删除
              </zl-button>
            </el-col>
          </el-row>
          <zl-table ref="zltable" :url="server.shop + '/orderInfo/'" :query-data="formdata">
            <el-table-column label="订单编号" prop="orderId" />
            <el-table-column label="产品名称" prop="prdName" />
            <el-table-column label="产品sku名称" prop="skuName" />
            <el-table-column label="销售价格" prop="sellPrice" />
            <el-table-column label="购买者id" prop="buyerId" />
            <el-table-column label="购买时间" prop="purchaseTime" />
          </zl-table>
        </el-main>
      </el-main>
    </el-container>
  </div>
  <div v-show="page === 'add'">
    <add-order-info :page="page" @clickBack="back"></add-order-info>
  </div>
  <div v-show="page === 'mod'">
    <mod-order-info :page="page" @clickBack="back" :mod-data="modData"></mod-order-info>
  </div>
  <div v-show="page === 'view'">
    <view-order-info :page="page" @clickBack="back" :mod-data="modData"></view-order-info>
  </div>
</template>
<script setup lang="ts">
import { reactive, ref } from 'vue'
import { server } from 'lib/zlaxios'
import type { OrderInfo } from './orderInfo'
import AddOrderInfo from './AddOrderInfo.vue'
import { mod } from '@/components/common'
import ModOrderInfo from './ModOrderInfo.vue'
import ViewOrderInfo from './ViewOrderInfo.vue'
import { ElMessageBox } from 'element-plus'
import { deleteOrder } from '@/api/shop/order'

const zltable: any = ref(null)
const formdata = reactive<OrderInfo>({})
const ruleFormRef = reactive({})
const page = ref('query')
const toAdd = () => {
  page.value = 'add'
}

const modData = {}
const toMod = () => {
  page.value = 'mod'
  Object.assign(modData, zltable.value.currentRow)
}
const toView = () => {
  page.value = 'view'
  Object.assign(modData, zltable.value.currentRow)
}

const toDel = () => {
  if (zltable.value.currentRow) {
    ElMessageBox.confirm('请确认是否删除数据?', {
      confirmButtonText: ' 确认',
      cancelButtonText: '返回',
      type: 'warning'
    })
      .then(() => {
        deleteOrder(zltable.value.currentRow.orderId, zltable.value.query)
      })
      .catch((e) => {
        console.log(e)
      })
  } else {
    ElMessageBox.alert('请选择一笔数据', '错误信息', {
      confirmButtonText: '确认'
    })
  }
}

const back = () => {
  page.value = 'query'
  zltable.value.query()
}
</script>
