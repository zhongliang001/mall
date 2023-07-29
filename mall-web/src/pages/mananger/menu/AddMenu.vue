<template>
  <el-header>新增页面</el-header>
  <el-main>
    <el-form>
      <el-row>
        <el-col :span="11">
          <el-form-item label="菜单中文名" prop="menuCnName">
            <el-input type="text" v-model="formdata.menuCnName"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="菜单名" prop="menuName">
            <el-input v-model="formdata.menuName"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="11">
          <el-form-item label="菜单路由" prop="path">
            <el-input v-model="formdata.path"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="菜单状态" prop="state">
            <zl-select v-model="formdata.state" type="ONLINE_STATE"></zl-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item label="父菜单" prop="parentId">
          <el-select filterable>
            <el-option
              v-for="item in parents"
              :key="item.menuId"
              :label="item.menuCnName"
              :value="item.menuId"
            />
          </el-select>
        </el-form-item>
      </el-row>
    </el-form>
    <el-button type="primary" @click="toBack">返回</el-button>
  </el-main>
</template>

<script lang="ts" setup>
import { reactive, ref, defineEmits } from 'vue'
import zlaxios from 'lib/zlaxios'
// emit 获取父组件传错来的方法
const emit = defineEmits(['clickBack'])

type pa = {
  menuCnName: string
  menuId: string
}

const parents: pa[] = []

const formdata = reactive({
  menuCnName: '',
  menuName: '',
  path: '',
  state: ''
})

const toBack = () => {
  // 调用父组件的方法，emit('clickBack', params)
  emit('clickBack')
}

zlaxios.request({
  url: '/user/menu/queryAll',
  method: 'get',
  success: function (data: any) {
    app.config.globalProperties.dict = data.data
    app.config.globalProperties.zlaxios = zlaxios
    app.mount('#app')
  }
})
</script>
