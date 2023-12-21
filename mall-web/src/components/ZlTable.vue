<template>
  <el-row :gutter="20" justify="end" v-show="addAble">
    <el-col :span="2">
      <zl-button type="primary" @click="addRow">新增一行</zl-button>
    </el-col>
    <el-col :span="2">
      <zl-button type="primary" @click="delRow">删除一行</zl-button>
    </el-col>
  </el-row>
  <el-table
    highlight-current-row
    v-loading="loading"
    :data="tableData"
    :border="true"
    :url="url"
    :query-data="queryData"
    empty-text="查询数据不存在"
    @current-change="currentChange"
  >
    <slot></slot>
  </el-table>
  <el-pagination
    v-show="showPage"
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
import { onMounted, ref } from 'vue'
import { ElMessage } from 'element-plus'

const { proxy } = useCurrentInstance()

const loading = ref(false)
const zlaxios = proxy.zlaxios
const props = defineProps({
  showPage: {
    default: true
  },
  url: {
    type: String
  },
  queryData: {
    type: Object
  },
  addAble: {
    type: Boolean,
    default: false
  }
})
const currentPage = ref(1)
const total = ref(0)
const pageSize = ref(5)
let tableData = ref<any>([])

onMounted(() => {
  if (!props.showPage) {
    pageSize.value = 0
  }
})

const currentRow = ref()

const currentChange = (val: undefined) => {
  currentRow.value = val
}

const handleSizeChange = (value: number) => {
  pageSize.value = value
  if (pageSize.value * currentPage.value > total.value) {
    currentPage.value = 1
  }
  query()
}
const handleCurrentChange = (value: number) => {
  currentPage.value = value
  query()
}

const query = () => {
  loading.value = true
  zlaxios.request({
    url: props.url,
    data: {
      condition: props.queryData,
      pageNum: currentPage.value,
      pageSize: pageSize.value
    },
    method: 'post',
    success: function (data: any) {
      tableData.value = data.data
      total.value = data.total
      loading.value = false
    },
    failed: function (data: any) {
      loading.value = false
      ElMessage({
        message: data.data.msg,
        grouping: true,
        type: 'error'
      })
    }
  })
}

const addRow = () => {
  tableData.value.push({})
}

const dRow = ref<any>([])

const delRow = async () => {
  if (currentRow.value) {
    const index = tableData.value.indexOf(currentRow.value)
    if (index != -1) {
      dRow.value.push(...tableData.value.splice(index, 1))
    }
  } else {
    ElMessage({
      message: '请选择一笔数据',
      grouping: true,
      type: 'error'
    })
  }
}

const getTableData = () => {
  return tableData.value
}

const getDelData = () => {
  return dRow.value
}

const clearData = () => {
  tableData.value.length = 0
}

defineExpose({
  query,
  currentRow,
  getTableData,
  clearData,
  getDelData
})
</script>

<style>
.right {
  align-content: right !important;
}
</style>
