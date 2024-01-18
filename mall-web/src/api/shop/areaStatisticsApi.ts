import { server, zlaxios } from '../../../lib/zlaxios'
import { ElMessage, type FormInstance } from 'element-plus'
import type { AreaStatistics } from '@/pages/shopcenter/areastatistics/AreaStatistics'
import type { Ref } from 'vue'

export const addAreaStatistics = (
  data: AreaStatistics,
  loading: Ref,
  formEl: FormInstance,
  succFun: Function | undefined
) => {
  zlaxios.request({
    url: server.shop + '/areaStatistics/add',
    data: data,
    method: 'post',
    loading: loading,
    success: function () {
      if (succFun) {
        succFun(formEl)
      }
      ElMessage({
        message: '新增成功',
        grouping: true,
        type: 'success'
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

export const modAreaStatistics = (
  data: AreaStatistics,
  loading: Ref,
  formEl: FormInstance,
  succFun: Function | undefined
) => {
  zlaxios.request({
    url: server.shop + '/areaStatistics/update',
    data: data,
    method: 'post',
    loading: loading,
    success: function () {
      if (succFun) {
        succFun(formEl)
      }
      ElMessage({
        message: '新增成功',
        grouping: true,
        type: 'success'
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

export const deleteAreaStatistics = (id: string, succFun: Function) => {
  zlaxios.request({
    url: server.shop + '/areaStatistics/delete',
    params: { id: id },
    method: 'get',
    success: function () {
      if (succFun) {
        succFun()
      }
      ElMessage({
        message: '删除成功',
        grouping: true,
        type: 'success'
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
