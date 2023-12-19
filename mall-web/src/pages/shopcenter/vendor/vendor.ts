import type { Item } from "@/components/Item"
import { userStore } from "@/stores/userStore"
import { ElMessage } from "element-plus"
import { server, zlaxios } from "lib/zlaxios"
import { reactive } from "vue"
const us = userStore()

type Vendor = {
  vendorName?: string
  conventional?: string
  path?: string
  cooperStatus?: string
  shopId?: string
}
export type { Vendor }

export const vendors = reactive<Array<Item>>([])

/**
 * 供应商下拉选
 */
export const queryVendorSelect = () => {
  zlaxios.request({
    url: server.shop + '/vendor/queryForSelect',
    params: { shopId: us.shopId() },
    method: 'get',
    success: function (data: any) {
      vendors.length = 0
      vendors.push(...data.data)
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