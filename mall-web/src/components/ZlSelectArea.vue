<template>
  <el-select v-model="province" @change="changeProvince(province)">
    <el-option v-for="item in provinces" :key="item.name" :label="item.name" :value="item.value" />
  </el-select>
  <el-select v-model="city" @change="changeCity(city)">
    <el-option v-for="item in cities" :key="item.name" :label="item.name" :value="item.value" />
  </el-select>
  <el-select v-model="area" @change="changeArea(area)">
    <el-option v-for="item in areas" :key="item.name" :label="item.name" :value="item.value" />
  </el-select>
</template>
<script setup lang="ts">
import { ElMessage } from 'element-plus'
import { server, zlaxios } from 'lib/zlaxios'
import { onMounted, reactive, ref, watch } from 'vue'
import type { Item } from './Item'
const props = defineProps({ modelValue: String })
const provinces = reactive<Array<Item>>([])
const cities = reactive<Array<Item>>([])
const areas = reactive<Array<Item>>([])
const province = ref('')
const city = ref('')
const area = ref('')
const emit = defineEmits(['update:modelValue'])
watch(
  () => props.modelValue,
  (newVal) => {
    if (newVal === undefined) {
      province.value = ''
      city.value = ''
      area.value = ''
    }
  }
)
onMounted(() => {
  zlaxios.request({
    url: server.base + '/areaCode/queryProvince',
    method: 'get',
    success: function (data: any) {
      provinces.length = 0
      provinces.push(...data.data)
    },
    failed: function (data: any) {
      ElMessage({
        message: data.msg,
        grouping: true,
        type: 'error'
      })
    }
  })
})

const changeProvince = (value: string | undefined) => {
  city.value = ''
  area.value = ''
  emit('update:modelValue', '')
  zlaxios.request({
    url: server.base + '/areaCode/queryCtiy',
    params: { province: value },
    method: 'get',
    success: function (data: any) {
      cities.length = 0
      cities.push(...data.data)
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

const changeCity = (value: string | undefined) => {
  area.value = ''
  emit('update:modelValue', '')
  zlaxios.request({
    url: server.base + '/areaCode/queryArea',
    params: { city: value },
    method: 'get',
    success: function (data: any) {
      areas.length = 0
      areas.push(...data.data)
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
const changeArea = (value: string | undefined) => {
  emit('update:modelValue', value)
}
</script>
