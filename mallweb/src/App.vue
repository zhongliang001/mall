<template>
  <router-view></router-view>
</template>

<script>

export default {
  name: 'app',
  mounted () {
    const token = localStorage.getItem('token')
    if (token) {
      this.$router.push({
        name: 'mainView'
      }, () => { })
    } else {
      this.$router.push({ name: 'login' }, () => { })
    }
  }
}
</script>

<style scoped>
</style>
