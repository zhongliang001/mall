import type { Ref } from 'vue'
import { ElMessage, type FormInstance } from 'element-plus'
import { server, zlaxios } from '../../../lib/zlaxios'
import type { ChargeRecord } from '@/pages/shopcenter/chargerecord/ChargeRecord'

const addChargeRecord = (
  data: ChargeRecord,
  loading: Ref,
  formEl: FormInstance,
  succFun: Function | undefined
) => {
  zlaxios.request({
    url: server.shop + '/chargeRecord/add',
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

const updateChargeRecord = (
  data: ChargeRecord,
  loading: Ref,
  formEl: FormInstance,
  succFun: Function | undefined
) => {
  zlaxios.request({
    url: server.shop + '/chargeRecord/update',
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

const deleteChargeRecord = (id: string, succFun: Function) => {
  zlaxios.request({
    url: server.shop + '/chargeRecord/delete',
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

export { addChargeRecord, updateChargeRecord, deleteChargeRecord }
