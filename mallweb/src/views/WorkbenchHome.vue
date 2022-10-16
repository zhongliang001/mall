<template>
  <div>
    <h1>店铺数据</h1>
    <p>数据更新时间：{{ time }}</p>
    <div class="infoDiv">
      <p>支付金额</p>
      <p>-</p>
      <p>昨日</p>
    </div>
    <div class="infoDiv">
      <p>访客数</p>
      <p>-</p>
      <p>昨日</p>
    </div>
    <div class="infoDiv">
      <p>支付子订单书</p>
      <p>-</p>
      <p>昨日</p>
    </div>
    <div class="infoDiv">
      <p>支付转化率</p>
      <p>-</p>
      <p>昨日</p>
    </div>
    <div class="infoDiv">
      <p>浏览量</p>
      <p>-</p>
      <p>昨日</p>
    </div>
    <div class="infoDiv">
      <p>加购人数</p>
      <p>-</p>
      <p>昨日</p>
    </div>
    <div class="infoDiv">
      <p>客单价</p>
      <p>-</p>
      <p>昨日</p>
    </div>
    <div class="infoDiv">
      <p>购买支付人数</p>
      <p>-</p>
      <p>昨日</p>
    </div>
  </div>
</template>
<script>
export default {
  name: "workbenchHome",
  data: function () {
    const date = new Date().format("yyyy-MM-dd HH:mm:ss");
    return {
      time: date,
    };
  },
};
</script>
<style>
.infoDiv {
  background-color: #f7f8fa;
  width: 24%;
  display: inline-block;
  margin: 5px;
}
</style>
