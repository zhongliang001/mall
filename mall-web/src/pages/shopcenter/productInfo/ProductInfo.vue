<template>
  <div v-show="page === 'query'">
    <el-container>
      <el-header>商品信息</el-header>
      <el-main>
        <el-form ref="formRef" :model="formdata">
          <el-row>
            <el-col :span="11">
              <el-form-item label="商品id" prop="prdId">
                <el-input v-model="formdata.prdId"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="平台商品id" prop="platformPrdId">
                <el-input v-model="formdata.platformPrdId"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="11">
              <el-form-item label="商品类型" prop="prdType">
                <zl-dict v-model="formdata.prdType" type="PRD_TYPE"></zl-dict>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-row>
          <el-col :span="11" :offset="11">
            <zl-query :zltable="zltable" :formRef="formRef"></zl-query>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="10" :offset="0">
            <zl-button type="primary" page-name="productInfo" action="add" @click="toAdd">
              新增
            </zl-button>
            <zl-button type="primary" page-name="productInfo" action="mod" @click="toMod">
              修改
            </zl-button>
            <zl-button type="primary" page-name="productInfo" action="del" @click="toDel">
              删除
            </zl-button>
            <zl-button type="primary" page-name="productInfo" action="view" @click="toView">
              查看
            </zl-button>
          </el-col>
        </el-row>
        <zl-table ref="zltable" :url="server.shop + '/productInfo/'" :query-data="formdata">
          <zl-table-column label="商品id" prop="prdId" />
          <zl-table-column label="商品名" prop="prdName" />
          <zl-table-column label="商品类型" prop="prdType" type="PRD_TYPE" />
          <zl-table-column label="商品上架" prop="state" type="PRD_STATE" />
        </zl-table>
      </el-main>
    </el-container>
  </div>
  <div v-show="page === 'add'">
    <add-product-info :page="page" @clickBack="back"></add-product-info>
  </div>
  <div v-show="page === 'mod'">
    <mod-product-info :page="page" :mod-data="modData" @clickBack="back"></mod-product-info>
  </div>
  <div v-show="page === 'view'">
    <view-product-info :page="page" :mod-data="modData" @clickBack="back"></view-product-info>
  </div>
</template>
<script setup lang="ts">
import { reactive, ref } from 'vue'
import { server, zlaxios } from 'lib/zlaxios'
import AddProductInfo from './AddProductInfo.vue'
import ModProductInfo from './ModProductInfo.vue'
import ViewProductInfo from './ViewProductInfo.vue'
import type { ProductInfo } from './productInfo'
import { ElMessage, ElMessageBox, type FormInstance } from 'element-plus'
import { userStore } from '@/stores/userStore'
const us = userStore()
const formdata: ProductInfo = reactive({
  shopId: us.shopId()
})
const zltable: any = ref(null)
const formRef = ref<FormInstance>()
const page = ref('query')
const back = () => {
  page.value = 'query'
  zltable.value.query()
}

const toAdd = () => {
  page.value = 'add'
}

const modData = {}
const toMod = () => {
  if (zltable.value.currentRow) {
    page.value = 'mod'
    console.log(zltable.value.currentRow)
    Object.assign(modData, zltable.value.currentRow)
  } else {
    ElMessageBox.alert('请选择一笔数据', '错误信息', {
      confirmButtonText: '确认'
    })
  }
}

const toDel = () => {
  if (zltable.value.currentRow) {
    ElMessageBox.confirm('请确认是否删除数据?', {
      confirmButtonText: ' 确认',
      cancelButtonText: '返回',
      type: 'warning'
    })
      .then(() => {
        zlaxios.request({
          url: server.shop + '/productInfo/delete',
          params: { prdId: zltable.value.currentRow.prdId },
          method: 'get',
          success: function () {
            zltable.value.query()
            ElMessage({
              message: '删除成功',
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
      })
      .catch((e) => {})
  } else {
    ElMessageBox.alert('请选择一笔数据', '错误信息', {
      confirmButtonText: '确认'
    })
  }
}

const toView = () => {
  if (zltable.value.currentRow) {
    page.value = 'view'
    console.log(zltable.value.currentRow)
    Object.assign(modData, zltable.value.currentRow)
  } else {
    ElMessageBox.alert('请选择一笔数据', '错误信息', {
      confirmButtonText: '确认'
    })
  }
}
</script>
