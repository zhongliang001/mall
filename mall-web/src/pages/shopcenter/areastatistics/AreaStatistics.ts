import { type ExtractPropTypes, reactive } from 'vue'
import type { FormRules } from 'element-plus'
import type { Item } from '@/components/Item'
import { queryProvince } from '@/components/Area'

const areaStatistics = {
  id: {
    type: String
  },
  statisticsDate: {
    type: String
  },
  area: {
    type: String
  },
  visitors: {
    type: Number
  },
  orders: {
    type: Number
  }
}
export type AreaStatistics = ExtractPropTypes<typeof areaStatistics>

export const formRules = reactive<FormRules>({
  statisticsDate: [{ required: true, message: '请选择日期', trigger: 'blur' }],
  area: [{ required: true, message: '请选择地区', trigger: 'blur' }],
  visitors: [{ required: true, message: '请输入访客数', trigger: 'blur' }]
})

const options = reactive<Array<Item>>([])
queryProvince(options)
export { options }
