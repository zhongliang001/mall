<template>
  <el-table-column
    :label="label"
    :prop="prop"
    :type="type"
    :formatter="formatter"
  ></el-table-column>
</template>
<script lang="ts" setup>
import type { TableColumnCtx } from 'element-plus'
import useCurrentInstance from 'lib/useCurrentInstance'
const props = defineProps(['label', 'prop', 'type'])
const formatter = (row: any, column: TableColumnCtx<any>) => {
  const type = props.type
  if (type) {
    const data = row[props.prop]
    const { proxy } = useCurrentInstance()
    const dict = proxy.dict
    let result
    dict[type].forEach((t: { enName: string; cnName: string }) => {
      if (t.enName === data) {
        result = t.cnName
      }
    })
    if (result) {
      return result
    }
  }
  return row[props.prop]
}
</script>
