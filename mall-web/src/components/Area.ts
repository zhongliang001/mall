import { server, zlaxios } from '../../lib/zlaxios'
import { ElMessage } from 'element-plus'
import { areaStore } from '@/stores/areaStore'
import type { Ref } from 'vue'

/**
 *
 * @param provinces 存储省份的集合
 * @param provineLoading 加载的标志
 */
const queryProvince = (
  provinces: { name: string; value: string }[],
  provineLoading?: Ref | undefined
) => {
  const aStore = areaStore()
  const areasInStore = aStore.getAreas()
  if (areasInStore) {
    provinces.length = 0
    provinces.push(...areasInStore)
  } else {
    zlaxios.request({
      url: server.base + '/areaCode/queryProvince',
      method: 'get',
      loading: provineLoading,
      success: function (data: any) {
        provinces.length = 0
        provinces.push(...data.data)
        aStore.setAreas('province', provinces)
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

export { queryProvince }
