<template>
  <el-form ref="prdReqForm" :rules="prdRules" :model="prdFormdata">
    <el-row>
      <el-col :span="11">
        <el-form-item label="商品名" prop="prdId">
          <zl-select
            v-model="prdFormdata.prdId"
            filterable
            :options="prds"
            @change="change(prdFormdata.prdId)"
          ></zl-select>
        </el-form-item>
      </el-col>
      <el-col :span="11">
        <el-form-item label="商品sku" prop="skuId">
          <zl-select
            v-model="prdFormdata.skuId"
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
          <zl-input-cur v-model="prdFormdata.sellPrice"></zl-input-cur>
        </el-form-item>
      </el-col>
      <el-col :span="11">
        <el-form-item label="销售邮费" prop="sellPostage">
          <zl-input-cur v-model="prdFormdata.sellPostage"></zl-input-cur>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="11">
        <el-form-item label="采购价格" prop="vendorPrice">
          <zl-input-cur v-model="prdFormdata.vendorPrice"></zl-input-cur>
        </el-form-item>
      </el-col>
      <el-col :span="11">
        <el-form-item label="采购邮费" prop="purchasePostage">
          <zl-input-cur v-model="prdFormdata.purchasePostage"></zl-input-cur>
        </el-form-item>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="11">
        <el-form-item label="销售数量" prop="sales">
          <el-input v-model="prdFormdata.sales" type="number"></el-input>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
  <el-row :gutter="20" justify="center">
    <el-col :span="6">
      <zl-button type="primary" :loading="loading" @click="confirm(prdReqForm)">确定</zl-button>
      <zl-button type="primary" @click="closed">关闭</zl-button>
    </el-col>
  </el-row>
</template>
<script setup lang="ts">
import type { FormInstance, FormRules } from 'element-plus'
import { reactive, ref, watch } from 'vue'
import type { OrderDetail } from './orderInfo'
import { skus, prds, skuLoading, changeProductInfo } from '../productInfo/productInfo'
const props = defineProps({
  modData: {
    type: Object
  },
  modFlag: {
    type: Boolean
  }
})

watch(
  () => props.modFlag,
  (newVal) => {
    if (newVal) {
      Object.assign(prdFormdata, props.modData)
      change(prdFormdata.prdId)
      prdFormdata.skuId = props.modData?.skuId
    }
  }
)

const prdReqForm = ref()

const loading = ref(false)
let prdFormdata = reactive<OrderDetail>({})

const emit = defineEmits(['returnData', 'closePage'])

const prdRules = reactive<FormRules>({
  prdId: [{ required: true, message: '请输入商品', trigger: 'blur' }],
  skuId: [{ required: true, message: '请输入商品sku', trigger: 'blur' }],
  sellPrice: [{ required: true, message: '请输入商品销售价格', trigger: 'blur' }],
  sellPostage: [{ required: true, message: '请输入商品销售邮费', trigger: 'blur' }],
  vendorPrice: [{ required: true, message: '请输入商品采购价格', trigger: 'blur' }],
  purchasePostage: [{ required: true, message: '请输入商品采购邮费', trigger: 'blur' }],
  sales: [{ required: true, message: '请输入销售数量', trigger: 'blur' }]
})

const confirm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid) => {
    if (valid) {
      if (prdFormdata.prdId) {
        const p = prds.filter((t) => {
          if (t.value === prdFormdata.prdId) {
            return t
          }
        })
        if (p.length > 0) {
          prdFormdata.prdName = p[0].name
        }
      }
      if (prdFormdata.skuId) {
        const p = skus.filter((t) => {
          if (t.value === prdFormdata.skuId) {
            return t
          }
        })
        if (p.length > 0) {
          prdFormdata.skuName = p[0].name
        }
      }
      const data = {}
      Object.assign(data, prdFormdata)
      emit('returnData', data)
      formEl.resetFields()
    }
  })
}

const closed = () => {
  prdReqForm.value.resetFields()
  emit('closePage')
}

const change = (value: string | undefined) => {
  prdFormdata.skuId = ''
  changeProductInfo(value)
}
defineExpose({
  closed
})
</script>
