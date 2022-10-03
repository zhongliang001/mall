<template>
  <el-button :type="type" @click="click">
    <slot></slot>
  </el-button>
</template>
<script>
export default {
  name: 'ZlButton',
  props: {
    type: {
      type: String,
      default: 'primary'
    }
  },
  methods: {
    click: function (e) {
      this.$emit('click', e)
    }
  }
}
</script>

<style scoped>
.el-button {
  border-radius: 0.25rem;
}
</style>
