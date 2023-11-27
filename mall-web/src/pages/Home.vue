<template>
  <el-container width="100%">
    <el-header style="text-align: right; margin-top: 5px">
      <el-dropdown>
        <span class="el-dropdown-link">
          店铺名称{{ shopName }}
          <el-icon class="el-icon--right"><arrow-down /></el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item
              class="el-dropdown-link"
              v-for="item in shops"
              :key="item"
              @click="chooseShop(item)"
            >
              {{ item?.cnName }}
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      <el-dropdown>
        <span class="el-dropdown-link">
          欢迎{{ username }}
          <el-icon class="el-icon--right"><arrow-down /></el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item class="el-dropdown-link">
              <!-- TODO 此处需要修改点击时不需要通过router-link -->
              <router-link to="viewUserInfo">用户个人信息</router-link>
            </el-dropdown-item>
            <el-dropdown-item class="el-dropdown-link">
              <a @click="logout">退出</a>
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-row class="tac">
          <el-col :span="24">
            <zl-menu :menus="menus"></zl-menu>
          </el-col>
        </el-row>
      </el-aside>
      <el-main style="background-color: #1e1e1e; height: 700px">
        <el-tabs v-model="rs.showRouter" type="card" @tab-remove="removeTab">
          <el-tab-pane
            v-for="(item, index) in rs.routerList"
            :label="item.cnName"
            :name="item.name"
            :closable="index !== 0"
            :key="index"
          >
            <component :is="item.component"></component>
          </el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>
  </el-container>
</template>
<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import { ArrowDown } from '@element-plus/icons-vue'
import { zlaxios, server } from 'lib/zlaxios'
import { ElMessage } from 'element-plus'
import { routerStore } from '@/stores/routerStore'
import type { Menu } from '@/components/menu'
import { userStore } from '@/stores/userStore'
const us = userStore()
const rs = routerStore()

type ShopInfo = {
  cnName: string
  enName: string
}

const token = localStorage.getItem('token')
let username = ref()
let logoutdata = { userId: '', token: token }
const shops = ref<ShopInfo[]>()

const shopName = ref()

onMounted(() => {
  const j = token?.split('.')[1]
  if (j) {
    const a = window.atob(j)
    const jsa = JSON.parse(a)
    console.log('被输出值{ jsa }的输出结果是：', jsa)
    us.setUserId(jsa.userId)
    us.setUserName(jsa.username)
    username.value = jsa.username
    shops.value = jsa.shops
    if (!us.shopId() && shops && shops?.value && shops?.value?.length > 0) {
      us.setShopId(shops.value[0]?.enName)
      us.setShopName(shops.value[0]?.cnName)
      shopName.value = us.shopName()
    }
  }
})

let menus: Array<Menu> = reactive<Array<Menu>>([])

const removeTab = (targetName: string) => {
  rs.splice(targetName)
}

onMounted(() => {
  zlaxios.request({
    url: server.base + '/menu/selectMenuByUserId',
    method: 'get',
    params: { userId: us.userId() },
    success: function (data: any) {
      menus.splice(0, menus.length)
      menus.push(...data.data)
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

const logout = function () {
  zlaxios.request({
    url: server.user + '/userAuth/logout',
    method: 'post',
    data: logoutdata,
    success: function (data: any) {
      window.localStorage.setItem('token', '')
      location.reload()
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

const chooseShop = (shop: ShopInfo) => {
  us.setShopId(shop.enName)
  us.setShopName(shop.cnName)
  shopName.value = us.shopName()
}
</script>
