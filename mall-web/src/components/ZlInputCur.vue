<template>
  <el-input ref="cur" type="text" @input="inp" @blur="blur" @focus="focus"></el-input>
</template>
<script setup lang="ts">
import { onMounted, ref } from 'vue'

const cur: any = ref(null)
const emit = defineEmits(['update:modelValue'])
const inp = () => {
  let inputValue = cur.value.input.value
  clearStr(inputValue)
}
const blur = () => {
  let inputValue = cur.value.input.value
  if (!inputValue) {
    return
  }
  clearStr(inputValue)
  if (isNaN(inputValue)) {
    cur.value.input.value = ''
  } else {
    const currency = formatCurrency(inputValue)
    cur.value.input.value = currency
  }
}

const focus = () => {
  let inputValue = cur.value.input.value
  inputValue = inputValue.replaceAll(',', '')
  cur.value.input.value = inputValue
  emit('update:modelValue', inputValue)
}

const clearStr = (inputValue: any) => {
  if (isNaN(inputValue)) {
    emit('update:modelValue', '')
  } else {
    emit('update:modelValue', inputValue)
  }
}

const formatCurrency = (data: number) => {
  let ds = data.toString()
  const dss = ds.split('.')
  const intData = dss[0]
  let currency: string = ''
  if (intData.length < 3) {
    currency = intData
  } else {
    const first: number = intData.length % 3
    let pice: number = intData.length / 3
    if (first > 0) {
      currency = intData.substring(0, first)
      if (pice > 0) {
        currency += ','
      }
    }
    let index = 0
    while (pice > 0) {
      currency += intData.substring(first + index * 3, first + (index + 1) * 3)
      pice--
      index++
      if (pice >= 1) {
        currency += ','
      }
    }
  }
  if (dss.length === 1) {
    return currency + '.00'
  } else {
    const dg = dss[1]
    if (dg.length === 1) {
      console.log('被输出值{ dg.length === 1 }的输出结果是：', dg.length === 1)
      return currency + '.' + dg + '0'
    } else if (dg.length === 2) {
      console.log('被输出值{ dg.length === 2 }的输出结果是：', dg.length === 2)
      return currency + '.' + dg
    } else {
      const rd = dg.charAt(2)
      console.log('被输出值{ dg.length === 3 }的输出结果是：', dg.length === 3)
      if (Number(rd) < 5) {
        return currency + '.' + dg.substring(0, 2)
      } else {
        return currency + '.' + (Number(dg.substring(0, 2)) + 1)
      }
    }
  }
}

onMounted(() => {
  blur()
})
</script>
