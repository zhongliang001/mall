<template>
  <div v-show="page === 'query'">
    <el-container>
      <el-header>地区数据统计</el-header>
      <el-main>
        <el-form ref="reqForm" :model="formdata">
          <el-row>
            <el-col :span="11">
              <el-form-item label="地区" prop="area">
                <zl-select v-model="formdata.area" filterable :options="options"></zl-select>
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
            <zl-button type="primary" page-name="areaStatistics" action="add" @click="toAdd">
              新增
            </zl-button>
            <zl-button
              type="primary"
              page-name="areaStatistics"
              action="mod"
              @click="mod(zltable, toMod)"
            >
              修改
            </zl-button>
            <zl-button type="primary" page-name="areaStatistics" action="del" @click="toDel">
              删除
            </zl-button>
            <zl-button
              type="primary"
              page-name="areaStatistics"
              action="view"
              @click="mod(zltable, toView)"
            >
              查看
            </zl-button>
          </el-col>
        </el-row>
        <zl-table ref="zltable" :url="server.shop + '/areaStatistics/'" :query-data="formdata">
          <el-table-column label="日期" prop="statisticsDate" />
          <el-table-column label="省份" prop="area" :formatter="gerProvinceName" />
          <el-table-column label="访客数" prop="visitors" />
          <el-table-column label="订单数" prop="orders" />
        </zl-table>
      </el-main>
    </el-container>
  </div>
  <div v-show="page === 'add'">
    <add-area-statistics :page="page" @clickBack="back"></add-area-statistics>
  </div>
  <div v-show="page === 'mod'">
    <mod-area-statistics :page="page" @click-back="back" :mod-data="modData"></mod-area-statistics>
  </div>
  <div v-show="page === 'view'">
    <view-area-statistics
      :page="page"
      @click-back="back"
      :mod-data="modData"
    ></view-area-statistics>
  </div>
</template>
<script setup lang="ts">
import { ref, reactive } from 'vue'
import ZlQuery from '@/components/ZlQuery.vue'
import { server } from 'lib/zlaxios'
import { areaStore } from '@/stores/areaStore'
import AddAreaStatistics from './AddAreaStatistics.vue'
import { type AreaStatistics, options } from '@/pages/shopcenter/areastatistics/AreaStatistics'
import ModAreaStatistics from '@/pages/shopcenter/areastatistics/ModAreaStatistics.vue'
import { mod } from '@/components/common'
import ViewAreaStatistics from '@/pages/shopcenter/areastatistics/ViewAreaStatistics.vue'
import { ElMessageBox } from 'element-plus'
import { deleteAreaStatistics } from '@/api/shop/areaStatisticsApi'
import { userStore } from '@/stores/userStore'
const us = userStore()
const reqForm = ref(null)
const formdata = reactive<AreaStatistics>({shopId: us.shopId()})
const zltable: any = ref(null)
const aStore = areaStore()
const page = ref('query')

/**
 * 获取省份名称
 * @param data 表格数据（单行）
 */
const gerProvinceName = (data: any) => {
  const areas = aStore.getAreas()
  let name = ''
  areas?.forEach((a) => {
    if (data.area === a.value) {
      name = a.name
      return
    }
  })
  return name
}

const back = () => {
  page.value = 'query'
  zltable.value.query()
}
/**
 * 进入新增页面
 */
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
        deleteAreaStatistics(zltable.value.currentRow.id, zltable.value.query)
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

<style scoped></style>
