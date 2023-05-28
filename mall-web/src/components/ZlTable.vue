<template>
  <el-table :data="tableData" :border="true" :url="url" :query-data="queryData">
    <slot></slot>
  </el-table>
  <el-pagination
    layout="prev, pager, next,sizes"
    :page-sizes="[5, 10, 20, 50]"
    :current-page="currentPage"
    :page-size="pageSize"
    :total="total"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />
</template>
<script setup lang="ts">
import useCurrentInstance from 'lib/useCurrentInstance'
import { ref } from 'vue'
import { ElMessage } from 'element-plus'

const { proxy } = useCurrentInstance()
const zlaxios = proxy.zlaxios
const props = defineProps(['data', 'query', 'url', 'queryData'])
const currentPage = ref(1)
const total = ref(0)
const pageSize = ref(5)
let tableData = ref([])

const handleSizeChange = (value: number) => {
  pageSize.value = value
  if (pageSize.value * currentPage.value > total.value) {
    currentPage.value = 1
  }
  query()
}
const handleCurrentChange = (value: number) => {
  debugger
  currentPage.value = value
  query()
}

const query = () => {
  zlaxios.request({
    url: '/user/menu/',
    data: {
      condition: props.queryData,
      pageNum: currentPage.value,
      pageSize: pageSize.value
    },
    method: 'post',
    success: function (data: any) {
      debugger
      tableData.value = data.data
      total.value = data.total
    },
    failed: function (data: any) {
      ElMessage({
        message: data.data.msg,
        grouping: true,
        type: 'error'
      })
    }
  })
}
defineExpose({
  query
})
</script>
