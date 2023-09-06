<template>
  角色名：{{ data.roleName }}
  <zl-select-tree ref="tree" :data="menuActionList" :selData="selData"></zl-select-tree>
  <el-row>
    <el-col :span="11" :offset="11">
      <el-button type="primary" @click="save()">保存</el-button>
      <el-button type="primary" @click="toBack()">返回</el-button>
    </el-col>
  </el-row>
</template>
<script lang="ts" setup>
import { ElMessage } from 'element-plus'
import zlaxios from 'lib/zlaxios'
import { ref, watch } from 'vue'
import type { MenuActionList } from '@/pages/mananger/menu/action/MenuAction'
const emit = defineEmits(['clickBackAction'])
const props = defineProps(['page', 'data'])

let menuActionList: MenuActionList | any = ref([])
let selData = ref([])

const tree: any = ref(null)
watch(
  () => props.page,
  (newVal) => {
    if (newVal === 'config') {
      menuActionList = ref([])
      zlaxios.request({
        url: '/user/menuAction/queryAll',
        method: 'post',
        success: function (data: any) {
          menuActionList.value = data.data
          zlaxios.request({
            url: '/user/roleRight/queryRights',
            params: { roleId: props.data.roleId },
            method: 'get',
            success: function (data: any) {
              selData.value = data.data
            },
            failed: function (data: any) {
              ElMessage({
                message: data.msg,
                grouping: true,
                type: 'error'
              })
            }
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
    }
  }
)

const save = () => {
  const a = tree.value.getData()
  zlaxios.request({
    url: '/user/roleRight/config',
    method: 'post',
    data: {
      roleId: props.data.roleId,
      list: a.value
    },
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
  emit('clickBackAction')
}
</script>
