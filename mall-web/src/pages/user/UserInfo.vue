<template>
  <div v-show="page === 'query'">
    <el-container>
      <el-header>用户列表查询</el-header>
      <el-main>
        <el-form ref="reqForm" :model="formdata">
          <el-row>
            <el-col :span="11">
              <el-form-item label="用户id" prop="userId">
                <el-input v-model="formdata.userId"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="用户名" prop="userName">
                <el-input v-model="formdata.userName"></el-input>
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
            <zl-button type="primary" page-name="userinfo" action="config" @click="conf()">
              配置角色
            </zl-button>
          </el-col>
        </el-row>
        <zl-table ref="zltable" url="/user/userInfo/" :query-data="formdata">
          <el-table-column label="用户id" prop="userId" />
          <el-table-column label="用户名" prop="userName" />
          <el-table-column label="昵称" prop="nickName" />
        </zl-table>
      </el-main>
    </el-container>
  </div>
  <div v-show="page === 'config'">
    <right-config :page="page" :mod-data="modData" @clickBack="back"></right-config>
  </div>
</template>
<script setup lang="ts">
import { ElMessageBox, type FormInstance } from 'element-plus'
import { reactive, ref } from 'vue'
import type { UserInfo } from './UserInfo'
import RightConfig from './right/RightConfig.vue'

const formdata: UserInfo = reactive({
  userId: '',
  userName: ''
})

let page = ref('query')

const zltable: any = ref(null)

const reqForm = ref<FormInstance>()

const modData = {}

const back = () => {
  page.value = 'query'
  zltable.value.query()
}

const conf = () => {
  if (zltable.value.currentRow) {
    page.value = 'config'
    Object.assign(modData, zltable.value.currentRow)
  } else {
    ElMessageBox.alert('请选择一笔数据', '错误信息', {
      confirmButtonText: '确认'
    })
  }
}
</script>
