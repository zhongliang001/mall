<template>
  <div v-show="page === 'query'">
    <el-container>
      <el-header>供应商信息</el-header>
      <el-main>
        <el-form ref="formRef" :model="formdata">
          <el-row>
            <el-col :span="11">
              <el-form-item label="供应商名称" prop="vendorName">
                <el-input v-model="formdata.vendorName"></el-input>
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
            <zl-button type="primary" page-name="vendor" action="add" @click="toAdd">
              新增
            </zl-button>
            <zl-button type="primary" page-name="vendor" action="mod" @click="toMod">
              修改
            </zl-button>
            <zl-button type="primary" page-name="vendor" action="mod" @click="toDel">
              删除
            </zl-button>
            <zl-button type="primary" page-name="vendor" action="mod" @click="toView">
              查看
            </zl-button>
          </el-col>
        </el-row>
        <zl-table ref="zltable" :url="server.shop + '/vendor/'" :query-data="formdata">
          <zl-table-column label="供应商id" prop="vendorId" />
          <zl-table-column label="供应商名" prop="vendorName" />
          <zl-table-column label="是否合作" prop="cooperStatus" type="YES_NO"></zl-table-column>
        </zl-table>
      </el-main>
    </el-container>
  </div>
  <div v-show="page === 'add'">
    <add-vendor @clickBack="back"></add-vendor>
  </div>
  <div v-show="page === 'mod'">
    <mod-vendor :page="page" :mod-data="modData" @clickBack="back"></mod-vendor>
  </div>
  <div v-show="page === 'view'">
    <view-vendor :page="page" :mod-data="modData" @clickBack="back"></view-vendor>
  </div>
</template>
<script setup lang="ts">
import { reactive, ref } from 'vue'
import type { Vendor } from './vendor'
import { server, zlaxios } from 'lib/zlaxios'
import AddVendor from './AddVendor.vue'
import ModVendor from './ModVendor.vue'
import ViewVendor from './ViewVendor.vue'
import { ElMessage, ElMessageBox } from 'element-plus'

const formdata: Vendor = reactive({})
const zltable: any = ref(null)
const formRef = ref(null)
const page = ref('query')
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

const back = () => {
  page.value = 'query'
  zltable.value.query()
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
          url: server.shop + '/vendor/delete',
          params: { vendorId: zltable.value.currentRow.vendorId },
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
