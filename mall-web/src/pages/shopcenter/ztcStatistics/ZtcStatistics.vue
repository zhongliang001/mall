<template>
  <div v-show="page==='query'">
    <el-container>
      <el-header>
        直通车数据统计
      </el-header>
      <el-main>
        <el-form ref="reqForm" :model="formData">
          <el-row>
            <el-col :span="11">
              <el-form-item label="商品名" prop="prdId">
                <zl-select
                  v-model="formData.prdId"
                  filterable
                  :options="prds"
                ></zl-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-row>
          <el-col :span="11" :offset="11">
            <zl-query :zltable="zltable"></zl-query>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="10" :offset="0">
            <zl-button type="primary" page-name="ztcStatistics" action="add" @click="toAdd">
              新增
            </zl-button>
          </el-col>
        </el-row>
        <zl-table ref="zltable" :url="server.shop + '/ztcStatistics/'" :query-data="formData">
          <zl-table-column label="商品名" prop="prdId" :format="getPrdNameById"></zl-table-column>
          <el-table-column label="关键词" prop="keyWord" />
          <el-table-column label="统计日期" prop="statDate" />
          <el-table-column label="潜力指数" prop="potentialIdx" />
          <el-table-column label="展现量" prop="impressions" />
          <el-table-column label="点击数" prop="hits" />
          <el-table-column label="点击花费" prop="cost" />
          <el-table-column label="收藏数" prop="collections" />
          <el-table-column label="加购数" prop="addPurchases" />
          <el-table-column label="店铺收藏数" prop="shopCollections" />
          <el-table-column label="支付笔数" prop="payments" />
          <el-table-column label="支付金额" prop="payAmt" />
          <el-table-column label="关键词状态" prop="keyState" />
        </zl-table>
      </el-main>
    </el-container>
  </div>
  <div v-show="page==='add'">
    <add-ztc-statistics></add-ztc-statistics>
  </div>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import type {ZtcStatistics} from '@/pages/shopcenter/ztcStatistics/ztcStatistics'
import { prds, queryProductSelect } from '@/pages/shopcenter/productInfo/productInfo'
import { server } from 'lib/zlaxios'
import ZlTableColumn from '@/components/ZlTableColumn.vue'
import AddZtcStatistics from '@/pages/shopcenter/ztcStatistics/AddZtcStatistics.vue'
const page= ref('query')
const formData= reactive<ZtcStatistics>({})
const zltable: any = ref(null)
onMounted(()=>{
  queryProductSelect()
})
const list = reactive([])
/**
 * 根据商品Id获取商品名
 * @param id 商品id
 */
const getPrdNameById=(id: string)=>{
  let result
  prds.forEach(t=>{
    if(t.value=== id){
      result = t.name
      return
    }
  })
  return result
}

const toAdd=()=>{
  page.value='add'
}
const upload = ref<UploadInstance>()

</script>

<style scoped>

</style>