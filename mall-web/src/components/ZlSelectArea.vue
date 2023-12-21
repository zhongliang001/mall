<template>
  <el-select
    v-model="province"
    @change="changeProvince(province)"
    :disabled="disabled"
    :loading="provineLoading"
  >
    <el-option v-for="item in provinces" :key="item.name" :label="item.name" :value="item.value" />
  </el-select>
  <el-select v-model="city" @change="changeCity(city)" :disabled="disabled" :loading="cityLoading">
    <el-option v-for="item in cities" :key="item.name" :label="item.name" :value="item.value" />
  </el-select>
  <el-select v-model="area" @change="changeArea(area)" :disabled="disabled" :loading="areaLoading">
    <el-option v-for="item in areas" :key="item.name" :label="item.name" :value="item.value" />
  </el-select>
</template>
<script setup lang="ts">
import { ElMessage } from 'element-plus'
import { server, zlaxios } from 'lib/zlaxios'
import { onMounted, reactive, ref, watch } from 'vue'
import type { Item } from './Item'
import { areaStore } from '@/stores/areaStore'
const props = defineProps({
  modelValue: { type: String },
  disabled: { type: Boolean }
})
const provinces = reactive<Array<Item>>([])
const cities = reactive<Array<Item>>([])
const areas = reactive<Array<Item>>([])
const province = ref('')
const city = ref('')
const area = ref('')
const provineLoading = ref(false)
const cityLoading = ref(false)
const areaLoading = ref(false)
const emit = defineEmits(['update:modelValue'])
const initflag = ref(true)
const aStore = areaStore()

watch(
  () => props.modelValue,
  (newVal) => {
    console.log('被输出值{ newVal }的输出结果是：', newVal)
    if (newVal === undefined) {
      province.value = ''
      city.value = ''
      area.value = ''
    } else {
      if (initflag.value) {
        if (newVal === '710000' || newVal === '810000' || newVal === '820000') {
          province.value = newVal
          initflag.value = false
          return
        }
        area.value = newVal
        province.value = newVal.substring(0, 2) + '0000'
        changeProvince(province.value)
        city.value = newVal.substring(0, 4) + '00'
        changeCity(city.value)
      }
    }
    initflag.value = false
  }
)
onMounted(() => {
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
})

const changeProvince = (value: string | undefined) => {
  if (!initflag.value) {
    city.value = ''
    area.value = ''
    emit('update:modelValue', '')
  }
  const areasInStore = aStore.getAreas(value)
  if (value === '710000' || value === '810000' || value === '820000') {
    emit('update:modelValue', value)
    return
  }

  if (areasInStore && areasInStore.length > 0) {
    cities.length = 0
    cities.push(...areasInStore)
  } else {
    zlaxios.request({
      url: server.base + '/areaCode/queryCtiy',
      params: { province: value },
      method: 'get',
      loading: cityLoading,
      success: function (data: any) {
        cities.length = 0
        cities.push(...data.data)
        if (value) {
          aStore.setAreas(value, data.data)
        }
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

const changeCity = (value: string | undefined) => {
  if (!initflag.value) {
    area.value = ''
    emit('update:modelValue', '')
  }
  const areasInStore = aStore.getAreas(value)
  if (areasInStore && areasInStore.length > 0) {
    areas.length = 0
    areas.push(...areasInStore)
  } else {
    zlaxios.request({
      url: server.base + '/areaCode/queryArea',
      params: { city: value },
      method: 'get',
      loading: areaLoading,
      success: function (data: any) {
        areas.length = 0
        areas.push(...data.data)
        if (value) {
          aStore.setAreas(value, data.data)
        }
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
const changeArea = (value: string | undefined) => {
  emit('update:modelValue', value)
}
</script>
