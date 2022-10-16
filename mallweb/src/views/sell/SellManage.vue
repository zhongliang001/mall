<template>
  <el-tabs TabType="border-card">
    <el-tab-pane name="all" label="全部宝贝">
      <h1>您还没有商品，你可以</h1>
      <el-button @click="toNew">发布新商品</el-button>
      <el-button>挑选值得出售的商品</el-button>
      <router-view></router-view>
    </el-tab-pane>
    <el-tab-pane name="onSell" label="出售中的宝贝">2</el-tab-pane>
    <el-tab-pane name="indepot" label="仓库中的宝贝">3</el-tab-pane>
  </el-tabs>
</template>
<script>
export default {
  name: "SellManage",
  methods: {
    toNew: function () {
      this.$router.push({ name: "releaseGoods" }, () => {});
    },
  },
};
</script>
