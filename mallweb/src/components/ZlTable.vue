<template>
  <el-table
    :data="data"
    v-loading="loading"
    element-loading-text="拼命加载中"
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
    :border="true"
    highlight-current-row
    stripe
    @current-change="currentChange"
    header-row-class-name="header"
  >
    <slot></slot>
  </el-table>
</template>
<script>
export default {
  name: "ZlTable",
  props: {
    data: {
      type: Array,
    },
    loading: {
      type: Boolean,
    },
  },
  methods: {
    currentChange: function (val) {
      this.$emit("current-change", val);
    },
  },
};
</script>
