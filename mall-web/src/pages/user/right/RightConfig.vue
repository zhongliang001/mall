<template>
  <el-container>
    <el-header>用户角色配置</el-header>
    <el-main>
      <div>
        <zl-choose ref="zlCh" :unChoosedData="datas" :choosedData="choosedData"></zl-choose>
      </div>
      <el-row>
        <el-col :span="11" :offset="11">
          <el-button type="primary" :loading="loading" @click="add()">保存</el-button>
          <el-button type="primary" @click="toBack()">返回</el-button>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<script setup lang="ts">
import type { Item } from '@/components/Item'
import { ElMessage } from 'element-plus'
import { zlaxios, server } from 'lib/zlaxios'
import { reactive, ref, watch } from 'vue'
const emit = defineEmits(['clickBack'])
const props = defineProps(['page', 'modData'])

let datas: Array<Item> = reactive<Array<Item>>([])

let choosedData: Array<Item> = reactive<Array<Item>>([])
const loading = ref(false)
watch(
  () => props.page,
  (newVal) => {
    console.log(newVal)
    zlaxios.request({
      url: server.base + '/role/queryOwnedRole',
      method: 'get',
      params: { userId: props.modData.userId },
      success: function (data: any) {
        choosedData.splice(0, choosedData.length)
        choosedData.push(...data.data)
      },
      failed: function (data: any) {
        ElMessage({
          message: data.msg,
          grouping: true,
          type: 'error'
        })
      }
    })
    zlaxios.request({
      url: server.base + '/role/queryNotOwnedRole',
      method: 'get',
      params: { userId: props.modData.userId },
      success: function (data: any) {
        datas.splice(0, datas.length)
        datas.push(...data.data)
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
)

const zlCh: any = ref(null)

const add = () => {
  const reqData = zlCh.value.getData()
  zlaxios.request({
    url: server.base + '/userRole/config',
    method: 'post',
    data: { userId: props.modData.userId, list: reqData },
    loading: loading,
    success: function (data: any) {
      toBack()
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

const toBack = () => {
  // 调用父组件的方法，emit('clickBack', params)
  emit('clickBack')
}
</script>
