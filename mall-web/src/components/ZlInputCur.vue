<template>
  <el-input ref="cur" type="text" @input="inp" @blur="blur" @focus="focus"></el-input>
</template>
<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { formatCurrency } from 'lib/Currency'
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
    cur.value.input.value = formatCurrency(inputValue)
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

onMounted(() => {
  blur()
})
</script>
