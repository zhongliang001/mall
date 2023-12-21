import type { Item } from '@/components/Item'
import { userStore } from '@/stores/userStore'
import { ElMessage } from 'element-plus'
import { server, zlaxios } from 'lib/zlaxios'
import { reactive, ref } from 'vue'
const us = userStore()

export type ProductInfo = {
  prdId?: string
  prdName?: string
  platformPrdId?: string
  prdType?: string
  vendorId?: string
  shopId?: string
  state?: string
}
export const skus = reactive<Array<Item>>([])
export const prds = reactive<Array<Item>>([])
export const skuLoading = ref(false)

/**
 * 查询产品下拉选
 */
export const queryProductSelect = () => {
  zlaxios.request({
    url: server.shop + '/productInfo/queryForSelect',
    params: { shopId: us.shopId() },
    method: 'get',
    success: function (data: any) {
      prds.length = 0
      skus.length = 0
      prds.push(...data.data)
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

/**
 * 选择商品后根据商品id查询sku
 * @param value 商品id
 */
export const changeProductInfo = (value: string | undefined) => {
  skuLoading.value = true
  zlaxios.request({
    url: server.shop + '/productSku/queryForSelect',
    params: { prdId: value },
    method: 'get',
    loading: skuLoading,
    success: function (data: any) {
      skus.length = 0
      skus.push(...data.data)
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
