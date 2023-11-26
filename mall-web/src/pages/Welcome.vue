<template>
  <el-container v-if="isview">
    <el-header>店铺信息</el-header>
    <el-main>
      <template v-if="shopInfo?.length !== 0">
        <div v-for="(shop, index) in shopInfo" :key="index">
          店铺名：{{ shop.shopName }} 店铺渠道：{{ shop.channel }}
        </div>
      </template>
      <el-button v-else type="primary" @click="add()">新增店铺</el-button>
    </el-main>
  </el-container>
  <add-shop v-if="!isview" @clickBack="back"></add-shop>
</template>
<script setup lang="ts">
import { ElMessage } from 'element-plus'
import { server, zlaxios } from 'lib/zlaxios'
import { onMounted, ref } from 'vue'
import AddShop from '@/pages/shopcenter/shop/AddShop.vue'
import type { Shop } from './shopcenter/shop/shop'

const shopInfo = ref<Array<Shop>>()
const isview = ref(true)

onMounted(() => {
  queryShop()
})

const queryShop = () => {
  zlaxios.request({
    url: server.shop + '/shop/queryAll',
    method: 'get',
    success: function (data: any) {
      shopInfo.value = data.data
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

const add = () => {
  isview.value = false
}

const back = () => {
  isview.value = true
  queryShop()
}
</script>
