<template>
  <el-button :type="type" @click="handler">
    <slot></slot>
  </el-button>
</template>
<script>
export default {
  name: "ZlButton",
  props: {
    type: {
      type: String,
      default: "primary",
    },
  },
  methods: {
    handler: function (e) {      
      this.$emit("zl-click", e);
    },
  },
};
</script>

<style scoped>
.el-button {
  border-radius: 0.25rem;
}
</style>
