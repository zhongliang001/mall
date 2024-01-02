<template>
  <div>
    <el-container>
      <el-header>修改订单</el-header>
      <el-main>
        <el-form ref="reqForm" :rules="rules" :model="formdata">
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
              <el-form-item label="购买者id" prop="buyerId">
                <el-input v-model="formdata.buyerId"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="交易状态" prop="state">
                <zl-dict v-model="formdata.state" type="TRANS_STATE"></zl-dict>
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
        </el-form>
        <el-row>
          <el-col :span="10" :offset="0">
            <zl-button type="primary" @click="toAddProd">添加商品</zl-button>
            <zl-button type="primary" @click="mod(zlPrdTable, toModProd)">修改商品</zl-button>
            <zl-button type="primary" @click="mod(zlPrdTable, toDelProd)">删除商品</zl-button>
          </el-col>
        </el-row>
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
            <zl-button type="primary" :loading="loading" @click="save(reqForm)">保存</zl-button>
            <zl-button type="primary" @click="toBack(reqForm)">返回</zl-button>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
    <el-dialog v-model="dialogTableVisible" title="录入商品信息" @close="closed">
      <order-detail
        :mod-data="modDilog"
        @return-data="returnData"
        @close-page="closePage"
        :mod-flag="modFlag"
      ></order-detail>
    </el-dialog>
  </div>
</template>
<script setup lang="ts">
import { ElMessageBox, type FormInstance, type FormRules } from 'element-plus'
import { onMounted, reactive, ref, watch } from 'vue'
import type { OrderInfo } from './orderInfo'
import { queryProductSelect } from '../productInfo/productInfo'
import { mod } from '@/components/common'
import { server } from 'lib/zlaxios'
import closed from './OrderDetail.vue'
import OrderDetail from './OrderDetail.vue'
import { delOrderDetail, updateOrder } from '@/api/shop/order'
const loading = ref(false)
const props = defineProps(['page', 'modData'])
const reqForm = ref()
const formdata = reactive<OrderInfo>({})
const rules = reactive<FormRules>({})
const dialogTableVisible = ref(false)
const zlPrdTable: any = ref(null)
const tabledata = reactive({ orderId: '' })
watch(
  () => props.page,
  (newVal) => {
    if (newVal === 'mod') {
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

const save = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  const tableData = zlPrdTable.value.getTableData()
  await formEl.validate((valid) => {
    if (valid) {
      const data = {
        orderInfo: formdata,
        orderDetails: tableData
      }
      updateOrder(data, loading, formEl, toBack)
    }
  })
}

const toAddProd = () => {
  dialogTableVisible.value = true
}

let modDilog = {}
const modFlag = ref(false)
const toModProd = () => {
  dialogTableVisible.value = true
  modDilog = zlPrdTable.value.currentRow
  modFlag.value = true
}

const toDelProd = () => {
  ElMessageBox.confirm('请确认是否删除数据?', {
    confirmButtonText: ' 确认',
    cancelButtonText: '返回',
    type: 'warning'
  })
    .then(() => {
      delOrderDetail(zlPrdTable.value.currentRow.orderDetailId, zlPrdTable.value.query)
    })
    .catch((e) => {
      console.log(e)
    })
}

const closePage = () => {
  dialogTableVisible.value = false
  modFlag.value = false
}

const returnData = (data: any) => {
  if (modFlag.value) {
    Object.assign(zlPrdTable.value.currentRow, data)
  } else {
    zlPrdTable.value.push(data)
  }
  dialogTableVisible.value = false
  modFlag.value = false
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
</script>
