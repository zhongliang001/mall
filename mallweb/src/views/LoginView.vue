<template>
  <div>
    <el-container>
      <el-header>
        <img class="image" src="../images/timg.jpg" />
      </el-header>
      <el-main>
        <el-form type="post" url="hi" labelWidth="100px">
          <el-form-item label="用户名" prop="user">
            <el-input name="el-input" type="text" v-model="user.username" placeholder="用户名" />
          </el-form-item>
          <el-form-item label="密码" prop="user">
            <el-input name="el-input" type="password" v-model="user.password" placeholder="密码" />
          </el-form-item>
          <el-form-item label="grant_type" prop="user" hidden="true">
            <el-input name="el-input" type="grant_type" v-model="user.grant_type" placeholder="grant_type" />
          </el-form-item>
          <el-button-group>
            <el-button type="primary" @click="login">登录</el-button>
            <el-button type="primary" @click="register">注册</el-button>
          </el-button-group>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>

export default {
  name: 'loginView',
  components: {
  },
  data: function () {
    return {
      user: {
        username: '',
        password: '',
        grant_type: 'password'
      }
    }
  },
  methods: {
    login: function () {
      const _this = this
      const url = this.service.uaaURL + '/oauth/token'
      const user = this.user
      this.service.request({
        method: 'post',
        url: url,
        config: {
          auth: {
            username: 'app',
            password: '123456'
          },
          params: user
        },
        success: function () {
          _this.$router.push({ name: 'mainView' }, () => {})
        }
      })
    },
    register: function () {
      const url = this.service.uaaURL + '/oauth/token1'
      this.service.request({
        method: 'post',
        url: url,
        config: {
          auth: {
            username: 'app',
            password: '123456'
          },
          params: {}
        }
      })
    }
  }
}
</script>

<style scoped>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.el-container {
  width: 400px;
  margin: 0 auto;
}

.el-form-item {
  margin-right: 10px;
  margin-top: 10px;
}

.el-main {
  border: 0.5px solid grey;
  border-radius: 0.2rem;
}

.el-form {
  margin-top: 30px;
}

.image {
  width: 80px;
  margin-left: 125px;
  margin-top: 20px;
  border-radius: 3rem;
  position: relative;
  -moz-box-shadow: 2px 2px 5px #333333;
  -webkit-box-shadow: 2px 2px 5px #333333;
  box-shadow: 0px 0px 20px #d04a80;
}

.el-form-item__label {
  color: black !important;
}

.el-button-group{
   width: 100%;
}
.el-button--primary{
  width: 50%;
}
</style>
