<template>
  <router-view></router-view>
</template>

<script setup lang="ts">
import { onMounted } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
onMounted(() => {
  const token = localStorage.getItem("token");
  if (token) {
    router.push({
      name: "mainView",
    });
  } else {
    router.push({ name: "login" });
  }
});
</script>

<style scoped></style>
