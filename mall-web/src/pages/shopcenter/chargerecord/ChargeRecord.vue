<template>
  <div v-show="page === 'query'">
    <el-container>
      <el-header>充值记录查询</el-header>
      <el-main>
        <el-form ref="reqForm" :model="formData">
          <el-row>
            <el-col :span="11">
              <el-form-item label="充值日期" prop="chargeDate">
                <el-date-picker
                  v-model="formData.chargeDate"
                  format="YYYY-MM-DD"
                  value-format="YYYY-MM-DD"
                ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-row>
          <el-col :span="11" :offset="11">
            <zl-query :zltable="zltable" :formRef="reqForm"></zl-query>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="10" :offset="0">
            <zl-button type="primary" page-name="chargeRecord" action="add" @click="toAdd">
              新增
            </zl-button>
            <zl-button
              type="primary"
              page-name="chargeRecord"
              action="mod"
              @click="mod(zltable, toMod)"
            >
              修改
            </zl-button>
            <zl-button type="primary" page-name="chargeRecord" action="del" @click="toDel">
              删除
            </zl-button>
            <zl-button
              type="primary"
              page-name="chargeRecord"
              action="view"
              @click="mod(zltable, toView)"
            >
              查看
            </zl-button>
          </el-col>
        </el-row>
        <zl-table ref="zltable" :url="server.shop + '/chargeRecord/'" :query-data="formData">
          <el-table-column label="日期" prop="chargeDate" />
          <el-table-column label="充值金额" prop="chargeAmt" :formatter="formatCur" />
        </zl-table>
      </el-main>
    </el-container>
  </div>
  <div v-show="page === 'add'">
    <add-charge-record :page="page" @clickBack="back"></add-charge-record>
  </div>
  <div v-show="page === 'mod'">
    <mod-charge-record :page="page" @click-back="back" :mod-data="modData"></mod-charge-record>
  </div>
  <div v-show="page === 'view'">
    <view-charge-record :page="page" @click-back="back" :mod-data="modData"></view-charge-record>
  </div>
</template>
<script setup lang="ts">
import { reactive, ref } from 'vue'
import type { ChargeRecord } from '@/pages/shopcenter/chargerecord/ChargeRecord'
import ZlQuery from '@/components/ZlQuery.vue'
import { server } from 'lib/zlaxios'
import { formatCurrency } from 'lib/Currency'
import AddChargeRecord from '@/pages/shopcenter/chargerecord/AddChargeRecord.vue'
import { mod } from '@/components/common'
import ModChargeRecord from '@/pages/shopcenter/chargerecord/ModChargeRecord.vue'
import ViewChargeRecord from '@/pages/shopcenter/chargerecord/ViewChargeRecord.vue'
import { ElMessageBox } from 'element-plus'
import { deleteChargeRecord } from '@/api/shop/chargeRecordApi'
import { userStore } from '@/stores/userStore'
const us = userStore()
const page = ref('query')
const reqForm = ref(null)
const formData = reactive<ChargeRecord>({shopId: us.shopId()})
const zltable: any = ref(null)
const formatCur = (data: ChargeRecord) => {
  if (!data.chargeAmt) {
    return 0.0
  }
  return formatCurrency(data.chargeAmt)
}

const back = () => {
  page.value = 'query'
  zltable.value.query()
}

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
        deleteChargeRecord(zltable.value.currentRow.id, zltable.value.query)
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
</script>
