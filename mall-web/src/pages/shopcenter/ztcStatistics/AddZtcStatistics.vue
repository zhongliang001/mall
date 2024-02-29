<template>
  <input ref="infile" type="file" name="file" v-on:change="ch"/>
  <input type="button" @click="cl">
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { server, zlaxios } from 'lib/zlaxios'
import { userStore } from '@/stores/userStore'
const us = userStore()

let ff=''

const ch = ()=>{
//  console.log(infile.value?.files)
  ff = infile.value?.files[0]

}
const infile = ref(null)

const cl = ()=>{
  console.log(ff)

  const data = new FormData()
  data.append('file', ff)
  data.append('shopId', us.shopId())
  zlaxios.request({
    url: server.shop + '/ztcStatistics/file/upload',
    config: {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    },
    data:data,
    method: 'post',
    success: function (data: any) {
      console.log(data)
    },
    failed: function (data: any) {
      console.log(data)
    }
  })
}

</script>