<template>
  <el-container width="100%">
    <el-header style="text-align: right; margin-top: 5px">
      <el-dropdown>
        <span class="el-dropdown-link">
          欢迎{{ username }}
          <el-icon class="el-icon--right"><arrow-down /></el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item class="el-dropdown-link">
              <router-link to="ViewUserInfo">用户个人信息</router-link>
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
          <el-col :span="12">
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
          >
            <component :is="item.component"></component>
          </el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>
  </el-container>
</template>
<script setup lang="ts">
import { onMounted, reactive } from 'vue'
import { ArrowDown } from '@element-plus/icons-vue'
import zlaxios from '../../lib/zlaxios'
import { ElMessage } from 'element-plus'
import { routerStore } from '@/stores/routerStore'
import type { Menu } from '@/components/menu'

const rs = routerStore()

let username = ''
const token = localStorage.getItem('token')
let logoutdata = { userId: '', token: token }

let menus: Array<Menu> = reactive<Array<Menu>>([])

const removeTab = (targetName: string) => {
  rs.splice(targetName)
}

onMounted(() => {
  const j = token?.split('.')[1]
  if (j) {
    const a = window.atob(j)
    const jsa = JSON.parse(a)
    logoutdata.userId = jsa.userId
    zlaxios.request({
      url: '/user/menu/selectMenuByUserId',
      method: 'get',
      params: { userId: jsa.userId },
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
  }
})

const logout = function () {
  zlaxios.request({
    url: '/user/userAuth/logout',
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
</script>
