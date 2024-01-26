import { ElMessage, type FormInstance } from 'element-plus'
import { server, zlaxios } from 'lib/zlaxios'
import type { Ref } from 'vue'
import { userStore } from '@/stores/userStore'

const us = userStore()
const updateOrder = (
  data: any,
  loading: Ref<boolean>,
  formEl: FormInstance | undefined,
  succFun: (formEl: FormInstance | undefined) => void
) => {
  zlaxios.request({
    url: server.shop + '/orderInfo/updateOrder',
    data: data,
    method: 'post',
    loading: loading,
    success: function () {
      succFun(formEl)
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

const delOrderDetail = (orderDetailId: string, succFun: Function) => {
  zlaxios.request({
    url: server.shop + '/orderDetail/delete',
    params: { orderDetailId: orderDetailId },
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

const deleteOrder = (orderId: string, succFun: Function) => {
  zlaxios.request({
    url: server.shop + '/orderInfo/deleteOrder',
    params: { orderId: orderId, shopId: us.shopId() },
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
export { delOrderDetail, updateOrder, deleteOrder }
