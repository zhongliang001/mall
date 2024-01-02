<template>
  <div>
    <el-container>
      <el-header>新增订单</el-header>
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
        <zl-table ref="zlPrdTable" :show-page="false">
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
import { ElMessage, ElMessageBox, type FormInstance, type FormRules } from 'element-plus'
import { reactive, ref, watch } from 'vue'
import type { OrderInfo } from './orderInfo'
import { mod } from '@/components/common'
import { queryProductSelect } from '../productInfo/productInfo'
import { server, zlaxios } from 'lib/zlaxios'
import OrderDetail from './OrderDetail.vue'
import closed from './OrderDetail.vue'

const loading = ref(false)
const props = defineProps(['page'])
const reqForm = ref()

const formdata = reactive<OrderInfo>({})

const rules = reactive<FormRules>({
  buyerId: [{ required: true, message: '请输入购买者id', trigger: 'blur' }],
  purchaseTime: [{ required: true, message: '请输入交易时间', trigger: 'blur' }],
  state: [{ required: true, message: '请输入交易状态', trigger: 'blur' }]
})

const dialogTableVisible = ref(false)

const zlPrdTable: any = ref(null)

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
  const tableData = zlPrdTable.value.getTableData()
  if (tableData.length === 0) {
    ElMessageBox.alert('请录入订单商品', '错误信息', {
      confirmButtonText: '确认'
    })
    return
  }
  await formEl.validate((valid) => {
    if (valid) {
      zlaxios.request({
        url: server.shop + '/orderInfo/addOrder',
        data: {
          orderInfo: formdata,
          orderDetails: tableData
        },
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

const toAddProd = () => {
  dialogTableVisible.value = true
}
const toDelProd = () => {
  zlPrdTable.value.delRow()
}

const closePage = () => {
  dialogTableVisible.value = false
  modFlag.value = false
}

let modDilog = {}
const modFlag = ref(false)
const toModProd = () => {
  dialogTableVisible.value = true
  modDilog = zlPrdTable.value.currentRow
  modFlag.value = true
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
