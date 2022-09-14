<template>
  <el-container>
    <el-header>
      <img class="image" src="../images/timg.jpg" />
      <div class="head-right">
        <el-input prefixIcon="iconfont icon-seach" placeholder="可以搜索你想要的功能" @blur="sayHi"></el-input>
        <p class="username">{{ userName }}</p>
      </div>
    </el-header>
    <el-container>
      <el-aside width="100px">
        <el-menu router :default-active="$route.path">
          <el-menu-item index="/workbenchHome">首页</el-menu-item>
          <el-menu-item>商品</el-menu-item>
        </el-menu>
      </el-aside>
      <el-aside width="200px">
        <el-menu router :default-active="$route.path">
          <el-submenu>
            <template slot="title">
              商品管理
            </template>
            <el-menu-item v-for="(me, index) in menuChild" v-bind:key="index" :index="me.path">{{ me.name }}
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: 'MainView',
  data: function () {
    return {
      userName: '',
      menuChild: [
        {
          name: '我的宝贝',
          path: '/sellManage'
        }]
    }
  },
  mounted: function () {
    const userName = localStorage.getItem('userName')
    this.userName = userName
  },
  methods: {
    sayHi: function () {
      alert('hi')
    }
  }
}
</script>
<style scoped>
.clear {
  clear: both;
}

.image {
  width: 60px;
  border-radius: 3rem;
}

.el-header {
  background-color: #fffaf0;
}

.el-input {
  width: 250px;
  border-radius: 2.5rem;
  margin-top: 8px;
  float: left;
}

.head-right {
  float: right;
  width: 1000px;
}

.username {
  float: right;
}

.el-menu {
  background-color: #ebf1ff;
}
</style>
