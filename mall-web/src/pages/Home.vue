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
              <router-link to="userInfo">用户个人信息</router-link>
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-row class="tac">
          <el-col :span="12">
            <el-menu default-active="2" background-color="#545c64">
              <el-sub-menu index="1">
                <template #title>
                  <el-icon><location /></el-icon>
                  <span>菜单</span>
                </template>
                <el-menu-item><router-link to="welcome">首页</router-link></el-menu-item>
              </el-sub-menu>
            </el-menu>
          </el-col>
        </el-row>
      </el-aside>
      <el-main style="background-color: #1e1e1e; height: 700px">
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script setup lang="ts">
import { useRoute } from 'vue-router'
import { ref, onMounted } from 'vue'
import { ArrowDown } from '@element-plus/icons-vue'
const route = useRoute()
let username = ref<string>()

onMounted(() => {
  const token = localStorage.getItem('token')
  const j = token?.split('.')[1]
  if (j) {
    const a = window.atob(j)
    const jsa = JSON.parse(a)
    username = jsa.username
  }
})
</script>
