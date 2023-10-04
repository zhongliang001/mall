<template>
  <div id="a"></div>
</template>
<script setup lang="ts">
import { onMounted } from 'vue'
import { ZlText } from 'zltext'
onMounted(() => {
  new ZlText('#a')
})
</script>
